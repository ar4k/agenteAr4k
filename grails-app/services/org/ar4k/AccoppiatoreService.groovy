package org.ar4k

import static groovyx.gpars.dataflow.Dataflow.task
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster

import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Holders

import java.util.Formatter.DateTime

import com.jcraft.jsch.*

@Transactional
class AccoppiatoreService {
	GrailsApplication grailsApplication
	def macchine = []

	String configuraPadrone() {
		HostRemoto padrone = nuovoSSH('master','Connessione configurata in file di configurazione iniziale (bootstrap)',grailsApplication.config.padrone.utente,grailsApplication.config.padrone.host,grailsApplication.config.padrone.porta,grailsApplication.config.padrone.password)
		//padrone.tunnel('R','127.0.0.1',2666,'',6666)
		//padrone.tunnel('R','127.0.0.1',6630,'',6666)
		//padrone.tunnel('L','',6668,'127.0.0.1',22)
		return padrone.descrivi()
	}

	Oggetto nuovoSSH(String etichetta,String descrizione,String utente,String target,Integer porta,String password) {
		HostRemoto macchina = new HostRemoto()
		macchina.etichetta = etichetta
		macchina.descrizione = descrizione
		macchina.nomeHost = target
		macchina.portaSSH = porta
		macchina.nomeUtente = utente
		macchina.password = password
		macchina.tipo = 'SSHNODE'
		macchine.add(macchina)
		return macchina
	}

	Channel sessione(String nome) {
		Channel connesione = macchine.find{it.etichetta==nome}.sessione(nome)
		return connesione
	}

	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}

	HostRemoto creaHostRemoto() {
		HostRemoto nuovo = new HostRemoto()
		macchine.add(nuovo)
	}

	HostRemoto getHostRemoto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
	}

	List<Oggetto> listaOggetti() {
		return macchine
	}

	Oggetto getOggetto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
	}

	String esegui(String etichetta,String comando) {
		Processo processo = new Processo()
		processo.comando = comando
		processo.target = macchine.find{it.etichetta==etichetta}
		return processo.esegui()
	}

	String remoteWeb(String etichetta,String target,String portaTarget,String query,String componente) {
		HostRemoto lanciatore = macchine.find{it.etichetta==etichetta}
		return lanciatore.jsoupSshHttp(target,portaTarget,query,componente)
	}
}







// Classi accessorie
//

// Host remoto
class HostRemoto extends Oggetto{
	def atmosphereMeteor = Holders.applicationContext.getBean("atmosphereMeteor")
	def sshService = Holders.applicationContext.getBean("sshService")
	String nomeHost = 'localhost'
	Integer portaSSH = 22
	String nomeUtente = 'root'
	String password = ''
	Integer timeout = 60000
	List<String> connessioni = []
	List<String> tunnel = []
	def sessioni = []

	String jsoupSshHttp(String target,String porta,String query,String componente) {
		Channel canale = sshService.stream(collega(),target,porta.toInteger())
		String richiesta = "GET "+query+" HTTP/1.1\n"
		richiesta += "Accept: text/html\n"
		richiesta += "Accept-Language: it\n"
		richiesta += "Accept-Encoding: deflate\n"
		richiesta += "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)\n"
		richiesta += "Host: "+target+"\n"
		richiesta += "\n"
		InputStream inputHtml = canale.getInputStream()
		OutputStream out = canale.getOutputStream()
		Integer time = 0
		out<<richiesta
		out.flush()
		String risultato = ''
		int ultimotime = 0
		while(true){
			byte[] buf=new byte[1024]
			int conto = 0
			while (inputHtml.available() > 0) {
				conto = inputHtml.read(buf,0,1024)
				risultato+=new String(buf,0,conto)
				ultimotime = time
			}
			if(canale.isClosed() || time > timeout || (ultimotime > 0 && ultimotime + 1000 < time )){
				if(inputHtml.available()>0) continue
					println("jsoupSshHttp eseguito: "+canale.getExitStatus())
				break
			}
			time += 50
			try{Thread.sleep(50);}catch(Exception ee){}
		}

		Document doc = Jsoup.parse(risultato)
		return doc.getElementById(componente)
	}

	Channel sessione(String etichetta) {
		Channel canale
		if (sessioni.find{it.etichetta == etichetta}) {
			canale = (sessioni.find{it.etichetta == etichetta}).canale
		} else {
			canale = sshService.console(collega())
			sessioni.add([canale:canale,etichetta:etichetta])
			task {
				byte[] buf=new byte[1024]
				int conto = 0
				def canal = canale
				InputStream input = canal.getInputStream()
				Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, '/wsa/def/'+etichetta)
				while(!canal.isClosed()){
					while (input.available() > 0) {
						conto = input.read(buf,0,1024)
						String testo = new String(buf,0,conto)
						broadcaster.broadcast(testo)
					}
					try{
						Thread.sleep(250);
					}catch(Exception ee){}
				}
				println "sessione "+etichetta+" chiusa"
			}
		}
		return canale
	}

	String collega() {
		String ritorno
		List<String> attive = []
		connessioni.each{
			if( sshService.getSession(it).isConnected ) {
				attive.add(it)
			}
		}
		connessioni = attive
		if (connessioni.size() < 1) {
			ritorno = sshService.addConnession(nomeUtente,nomeHost,portaSSH,password)
			println "collego "+nomeUtente+"@"+nomeHost+":"+portaSSH+" : "+ritorno
			connessioni.add(ritorno)
		} else {
			ritorno = connessioni[0]
		}
		return ritorno
	}

	String esegui(String comando,InputStream erroreStream) {
		return sshService.esegui(collega(),comando,erroreStream)
	}

	String tunnel(String direzione,String hostLocale,Integer portaLocale,String hostTarget,Integer portaTarget) {
		String ritorno
		TunnelSsh tunnel = new TunnelSsh()
		tunnel.hostRemoto = this
		tunnel.hostLocale = hostLocale
		tunnel.hostTarget = hostTarget
		tunnel.portaLocale = portaLocale
		tunnel.portaTarget = portaTarget
		tunnel.direzione = direzione
		return tunnel.crea()
	}

	String descrivi() {
		String ritorno = "----------------------------\n"
		ritorno += esegui('uname -a',null) + "\n"
		ritorno += "----------------------------\n"
		ritorno += "tipo: "+etichetta+"\n"
		ritorno += "tipo: "+tipo+"\n"
		ritorno += "nome host: "+nomeHost+"\n"
		ritorno += "porta host: "+portaSSH+"\n"
		ritorno += "utente: "+nomeUtente+"\n"
		ritorno += "identificativo connessione: "+connessioni[0]+"\n"
		ritorno += "tunnel:\n"
		tunnel.each {ritorno += it.toString()+"\n"}
		ritorno += "----------------------------\n"
		return ritorno
	}
}


// Tunnel SSH
class TunnelSsh {
	def sshService = Holders.applicationContext.getBean("sshService")
	String direzione = 'L'
	HostRemoto hostRemoto
	Integer portaLocale = 22
	Integer portaTarget = 6631
	String hostLocale = '127.0.0.1'
	String hostTarget = ''
	String identificatore = ''
	Integer attiva = 0

	String crea() {
		if (direzione == 'L') {
			println "avvio tunnel: L:"+portaLocale+":"+hostTarget+":"+portaTarget
			sshService.addLTunnel(hostRemoto.collega(),portaLocale,hostTarget,portaTarget)
			attiva = 1
			identificatore = 'L:'+portaLocale+":"+hostTarget+":"+portaTarget
		} else {
			println "avvio tunnel: R:"+portaTarget+":"+hostLocale+":"+portaLocale
			sshService.addRTunnel(hostRemoto.collega(),portaTarget,hostLocale,portaLocale)
			attiva = 1
			identificatore = 'R:'+portaTarget+":"+hostLocale+":"+portaLocale
		}
		hostRemoto.tunnel.add(identificatore)
		return identificatore
	}
}


// Classe astratta Oggetto
abstract class Oggetto {
	String etichetta = UUID.randomUUID()
	String tipo = 'astratto'
	String stato = 'Nuovo'
	String descrizione ='Oggetto sistema AR4K by Rossonet\n'
	Boolean automatico = false
	Oggetto padre = null
	Contesto contestoMaster
	List<Contesto> contesti = []
	List<Funzionalita> funzionalita = []
	List<Meme> ricette = []

	String descrivi() {
		String ritorno = "----------------------------\n"
		ritorno += "etichetta: "+etichetta+"\n"
		ritorno += "tipo: "+tipo+"\n"
		return ritorno
	}

}

// Classe contesti (per i permessi sui tunnel)
class Contesto {
	String etichetta = UUID.randomUUID()
	String descrizione ='Contesto sistema AR4K by Rossonet\n'
	String cliente ='Rossonet'
	String idContabilita = 'xx'
	List<Oggetto> oggetti = []
	List<Rete> reti = []
	// Oggetto necessario per un contesto
	String ambiente = "contesto="+etichetta+"\n"
}

// Class funzionalità
class Funzionalita {
	String etichetta = ''
	String descrizione ='Funzionalità sistema AR4K by Rossonet\n'
	String valoreUnico = UUID.randomUUID()
	String icona = 'fa-neuter'
	List<Funzionalita> dipendenze = []
}

// Class rete
class Rete {
	String etichetta = UUID.randomUUID()
	String descrizione ='Rete gestita da AR4K by Rossonet\n'
	String rete = '127.0.0.1/24'
	List<Oggetto> accessi = []
}

// Class comando
class Processo {
	String etichetta = UUID.randomUUID()
	String descrizione ='Processo AR4K by Rossonet\n'
	Boolean persistente = false
	DateTime scadenza = null
	String tipoEsecuzione = 'bash'
	Oggetto target = null
	Boolean salvaRisultato = true
	String comando = 'ls -a'
	String codaAggiornamento = etichetta
	String stato = 'Inizializzato'
	List<Funzionalita> richieste = []

	String esegui() {
		stato = 'In esecuzione'
		InputStream errore
		return target.esegui('source ~/.bash_profile ; '+comando,errore)
	}

}

// Schedulazione Cron
class Schedulazione {
	String etichetta = UUID.randomUUID()
	String descrizione ='Schedulazione AR4K by Rossonet\n'
	Processo processo
	String configurazione
}

// Ricetta
class Meme {
	String etichetta = UUID.randomUUID()
	String descrizione ='Meme AR4K by Rossonet\n'
	Processo testPreparazione
	Processo installazione
	Processo monitoraggio
	Processo rilevaStato
	Processo sospensione
	Processo avvio
	Processo distruzione
	Processo dump
	List<Schedulazione> schedulazioni = []
	String maschera = ''
	String stato = 'inattivo'
}

// Ricettario
class Memoria {
	String etichetta = UUID.randomUUID()
	String descrizione ='Ricettario AR4K by Rossonet\n'
	String repositoryGit = ''
	String utente = 'agentear4k'
	String password = ''
	String ramo = 'master'
	String stato = 'configurazione'
	Boolean archivio = false
	List<Meme> memi = []
}