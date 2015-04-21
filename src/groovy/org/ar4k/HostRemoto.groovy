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
	def connessioni = []
	def tunnels = []
	def sessioni = []
	
	def salvataggio() {
		return [
			etichetta:etichetta,
			tipo:tipo,
			stato:stato,
			descrizione:descrizione,
			automatico:automatico,	
			padre:(padre?padre.etichetta:"nessuno"),	
			contestomaster:contestoMaster.etichetta,

			funzionalita:funzionalita*.salvataggio(),
			ricette:ricette*.salvataggio(),
	
			nomeHost:nomeHost,
			nomeUtente:nomeUtente,
			password:password,
			timeout:timeout,
			tunnel:tunnels*.salvataggio()]
	}

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
					log.info("jsoupSshHttp eseguito: "+canale.getExitStatus())
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
		if (sessioni.find{it.etichetta == etichetta} && ! sessioni.find{it.etichetta == etichetta}.canale.isClosed()) {
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
				log.info("sessione "+etichetta+" chiusa")
			}
		}
		return canale
	}

	String collega() {	
		String ritorno
		List<String> attive = []
		connessioni.each{
			if( sshService.getSession(it).isConnected() ) {
				attive.add(it)
			}
		}
		connessioni = attive
		if (connessioni.size() < 1) {
			ritorno = sshService.addConnession(nomeUtente,nomeHost,portaSSH,password)
			log.info("collego "+nomeUtente+"@"+nomeHost+":"+portaSSH+" : "+ritorno)
			connessioni.add(ritorno)
		} else {
			ritorno = connessioni[0]
		}
		return ritorno
	}

	String esegui(String comando) {
		return sshService.esegui(collega(),comando)
	}

	TunnelSsh tunnel(String direzione,String hostLocale,Integer portaLocale,String hostTarget,Integer portaTarget) {
		String ritorno
		TunnelSsh tunnel = new TunnelSsh()
		tunnel.hostRemoto = this
		tunnel.hostLocale = hostLocale
		tunnel.hostTarget = hostTarget
		tunnel.portaLocale = portaLocale
		tunnel.portaTarget = portaTarget
		tunnel.direzione = direzione
		tunnel.crea()
		tunnels.add(tunnel)
		return tunnel
		
	}

	String descrivi() {
		String ritorno = "----------------------------\n"
		ritorno += esegui('uname -a') + "\n"
		ritorno += "----------------------------\n"
		ritorno += "etichetta: "+etichetta+"\n"
		ritorno += "tipo: "+tipo+"\n"
		ritorno += "nome host: "+nomeHost+"\n"
		ritorno += "porta host: "+portaSSH+"\n"
		ritorno += "utente: "+nomeUtente+"\n"
		ritorno += "identificativo connessione: "+connessioni[0]+"\n"
		ritorno += "tunnel:\n"
		tunnels.each {ritorno += it.toString()+"\n"}
		ritorno += "----------------------------\n"
		return ritorno
	}
}

