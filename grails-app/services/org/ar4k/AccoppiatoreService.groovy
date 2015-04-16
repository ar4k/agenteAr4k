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
	List<HostRemoto> macchine
	HostRemoto masterIstanza

	String configuraPadrone() {
		HostRemoto padrone = new HostRemoto()
		padrone.etichetta = 'master'
		padrone.nomeHost = grailsApplication.config.padrone.host
		padrone.portaSSH = grailsApplication.config.padrone.porta
		padrone.nomeUtente = grailsApplication.config.padrone.utente
		padrone.password = grailsApplication.config.padrone.password
		//padrone.tunnel('R','127.0.0.1',2666,'',6666)
		padrone.tunnel('R','127.0.0.1',8080,'',6667)
		//padrone.tunnel('L','',6668,'127.0.0.1',22)
		masterIstanza = padrone
		//sessionePadrone()
		return padrone.descrivi()
	}

	Channel sessionePadrone() {
		Channel connesionePadrone = masterIstanza.sessioneMaster()
		return connesionePadrone
	}
	
	def freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}
}

class HostRemoto {
	def atmosphereMeteor = Holders.applicationContext.getBean("atmosphereMeteor")
	def sshService = Holders.applicationContext.getBean("sshService")
	String etichetta = UUID.randomUUID()
	String nomeHost = 'localhost'
	Integer portaSSH = 22
	String nomeUtente = 'root'
	String password = ''
	List<String> connessioni = []
	List<String> tunnel = []
	List<Channel> sessioni = []

	Channel sessioneMaster() {
		Channel canale
		if ( sessioni.size() < 1) {
			canale = sshService.console(collega())
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
		canale = sessioni[0]
		return canale
	}

	String collega() {
		if (connessioni.size() < 1) {
			println "collego "+nomeUtente+"@"+nomeHost+":"+portaSSH
			connessioni.add(sshService.addConnession(nomeUtente,nomeHost,portaSSH,password))
		}
		return connessioni[0]
	}

	String esegui(String comando) {
		return sshService.esegui(collega(),comando,null)
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

class TunnelSsh {
	def sshService = Holders.applicationContext.getBean("sshService")
	String direzione = 'L'
	HostRemoto hostRemoto
	Integer portaLocale = 22
	Integer portaTarget = 6666
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
