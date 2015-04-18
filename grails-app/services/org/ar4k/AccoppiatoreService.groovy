package org.ar4k

import static groovyx.gpars.dataflow.Dataflow.task
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster
import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Holders
import com.jcraft.jsch.*

@Transactional
class AccoppiatoreService {
	GrailsApplication grailsApplication
	def macchine = []
	HostRemoto masterIstanza

	String configuraPadrone() {
		HostRemoto padrone = nuovoSSH('master','Connessione configurata in file di configurazione iniziale (bootstrap)',grailsApplication.config.padrone.utente,grailsApplication.config.padrone.host,grailsApplication.config.padrone.porta,grailsApplication.config.padrone.password)
		//padrone.tunnel('R','127.0.0.1',2666,'',6666)
		padrone.tunnel('R','127.0.0.1',6630,'',6666)
		//padrone.tunnel('L','',6668,'127.0.0.1',22)
		masterIstanza = padrone
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

	Channel sessionePadrone() {
		Channel connesionePadrone = masterIstanza.sessione(1)
		return connesionePadrone
	}

	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}

	HostRemoto creaHostRemoto() {
		HostRemoto nuovo = new HostRemoto()
		macchine.add(nuovo)
	}

	HostRemoto getHostRemoto(String etichetta) {
	}

	List<Oggetto> listaOggetti() {
		return macchine
	}

	Oggetto getOggetto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
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
	List<String> connessioni = []
	List<String> tunnel = []
	List<Channel> sessioni = []

	Channel sessione(Integer num) {
		Channel canale
		if ( sessioni.size() < num) {
			canale = sshService.console(collega(1))
			sessioni.add(canale)
			task {
				byte[] buf=new byte[1024]
				int conto = 0
				def canal = canale
				InputStream input = canal.getInputStream()
				Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, '/wsa/def/padrone')
				while(!canal.isClosed()){
					//broadcaster.broadcast("Stream: "+input+" input: "+input.available())
					//println "Stream: "+input+" input: "+input.available()
					while (input.available() > 0) {
						conto = input.read(buf,0,1024)
						broadcaster.broadcast(new String(buf,0,conto))
					}
					try{
						Thread.sleep(250);
					}catch(Exception ee){}
				}
			}
		}
		//println sessioni*.getId()
		canale = sessioni.find{it.getId()==num}
		return canale
	}
	/*
	 Channel sessioneMailTest() {
	 Channel canale
	 if ( sessioni.size() < 2) {
	 canale = sshService.stream(collega(),'mail.rossonet.net',25)
	 sessioni.add(canale)
	 task {
	 byte[] buf=new byte[1024]
	 int conto = 0
	 def canal = canale
	 InputStream input = canal.getInputStream()
	 Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, '/wsa/def/mailtest')
	 while(!canal.isClosed()){
	 //broadcaster.broadcast("Stream: "+input+" input: "+input.available())
	 //println "Stream: "+input+" input: "+input.available()
	 while (input.available() > 0) {
	 conto = input.read(buf,0,1024)
	 broadcaster.broadcast(new String(buf,0,conto))
	 }
	 try{
	 Thread.sleep(250);
	 }catch(Exception ee){}
	 }
	 }
	 }
	 canale = sessioni[1]
	 return canale
	 }
	 */
	String collega(Integer num) {
		if (connessioni.size() < num) {
			String ritorno = sshService.addConnession(nomeUtente,nomeHost,portaSSH,password)
			println "collego "+nomeUtente+"@"+nomeHost+":"+portaSSH+" : "+ritorno
			connessioni.add(ritorno)
		}
		return connessioni[0]
	}

	String esegui(String comando) {
		return sshService.esegui(collega(1),comando,null)
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
		ritorno += "tipo: "+etichetta+"\n"
		ritorno += "tipo: "+tipo+"\n"
		ritorno += "nome host: "+nomeHost+"\n"
		ritorno += "porta host: "+portaSSH+"\n"
		ritorno += "utente: "+nomeUtente+"\n"
		ritorno += "identificativo connessione: "+connessioni[0]+"\n"
		ritorno += "----------------------------\n"
		ritorno += esegui('uname -a') + "\n"
		ritorno += "----------------------------\n"
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
			sshService.addLTunnel(hostRemoto.collega(1),portaLocale,hostTarget,portaTarget)
			attiva = 1
			identificatore = 'L:'+portaLocale+":"+hostTarget+":"+portaTarget
		} else {
			println "avvio tunnel: R:"+portaTarget+":"+hostLocale+":"+portaLocale
			sshService.addRTunnel(hostRemoto.collega(1),portaTarget,hostLocale,portaLocale)
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
	Oggetto memoriaAr4k
}

// Class funzionalità
class Funzionalita {
	String etichetta = ''
	String descrizione ='Funzionalità sistema AR4K by Rossonet\n'
	String valoreUnico = UUID.randomUUID()
	String icona = 'fa-neuter'
	List<Funzionalita> dipendenze = []
}

class Rete {
	String etichetta = UUID.randomUUID()
	String descrizione ='Rete gestita da AR4K by Rossonet\n'
	String rete = '127.0.0.1/24'
	Contesto contesto
	List<Oggetto> accessi = []
}
