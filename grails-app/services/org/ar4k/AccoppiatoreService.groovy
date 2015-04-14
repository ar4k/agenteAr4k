package org.ar4k

import grails.transaction.Transactional
import grails.util.Holders

@Transactional
class AccoppiatoreService {
	List<HostRemoto> macchine

	String configuraPadrone(String utente,String host,Integer porta,String password) {
		HostRemoto padrone = new HostRemoto()
		padrone.etichetta = 'master'
		padrone.nomeHost = host
		padrone.portaSSH = porta
		padrone.nomeUtente = utente
		padrone.password = password
		padrone.tunnel('R','127.0.0.1',2666,'',6666)
		padrone.tunnel('R','127.0.0.1',8080,'',6667)
		padrone.tunnel('L','',6668,'127.0.0.1',22)
		return padrone.descrivi()
	}
}

class HostRemoto {
	def sshService = Holders.applicationContext.getBean("sshService")
	String etichetta = UUID.randomUUID()
	String nomeHost = 'localhost'
	Integer portaSSH = 22
	String nomeUtente = 'root'
	String password = ''
	List<String> connessioni = []
	List<String> tunnel = []

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
