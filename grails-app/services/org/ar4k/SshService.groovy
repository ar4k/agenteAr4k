/**
 * SSH Service
 *
 * <p>Gestisce le connessioni SSH dalla maschera</p>
 *
 * <p style="text-justify">
 * ...</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Meme
 * @see org.ar4k.Interfaccia
 */

package org.ar4k

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.h2.util.New;

import grails.transaction.Transactional

import com.jcraft.jsch.*


@Transactional
class SshService {
	def GrailsApplication grailsApplication
	def servletContext
	def connessioniSSH = []
	def canaliSSH = []
	def proxySSH = []
	JSch jsch

	// Aggiunge una sessione ed eventualmente istanzia la classe JSch
	def String addConnession(String username, String host, int port, String password ) {
		if (jsch == null) {
			jsch = new JSch()
		}
		Session session=jsch.getSession(username, host, port)
		UserInfo ui=new SSHUserInfo()
		ui.setPassword(password)
		session.setUserInfo(ui)
		session.connect()
		connessioniSSH.add(session)
		return session.getSessionId().encodeAsMD5()
	}

	// Lista le sessioni
	def List<String> listConnession() {
		def lista = []
		connessioniSSH.each{
			lista.add(it.getSessionId().encodeAsMD5().toString())
		}
		return lista
	}

	// ritrova i dati di una sessione per SessionId
	def Session getSession(String ricerca) {
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				return sessione
				break
			}
		}
	}

	// rimuove una sessione per SessionId
	def boolean removeConnession(String ricerca) {
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				sessione.disconnect()
				if (sessione.isConnected)
				{
					return false
				} else {
					def temporanei = []
					for(Session ses in connessioniSSH) {
						if (ses.getSessionId().encodeAsMD5() != ricerca) {
							temporanei.add(ses)
						}
					}
					connessioniSSH = temporanei
					return true
				}
				break
			}
		}
	}

	//Gestisce i tunnel R

	def List<String> listPortForwardingR(String sessione) {
		for(Session ses in connessioniSSH) {
			if (ses.getSessionId().encodeAsMD5() == sessione) {
				return ses.getPortForwardingR()*.toString()
			}
		}
	}

	def void addRTunnel(String ricerca, int rport, String lhost, int lport) {
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				sessione.setPortForwardingR(rport, lhost, lport)
			}
		}
	}

	def boolean removeRTunnel(String ricerca, int rport) {
		String risultato
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				sessione.delPortForwardingR(rport)
				risultato = sessione.isConnected()
			}
			return risultato
		}
	}

	//Gestisce i tunnel L

	def List<String> listPortForwardingL(String sessione) {
		for(Session ses in connessioniSSH) {
			if (ses.getSessionId().encodeAsMD5() == sessione) {
				return ses.getPortForwardingL()*.toString()
			}
		}
	}

	def Integer addLTunnel(String ricerca, int lport, String rhost, int rport) {
		Integer risultato
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				risultato = sessione.setPortForwardingL(lport, rhost, rport)
			}
			return risultato
		}
	}

	def boolean removeLTunnel(String ricerca, int lport) {
		String risultato
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				sessione.delPortForwardingL(lport)
				risultato = sessione.isConnected()
			}
			return risultato
		}
	}

	// Gestisce l'esecuzione di comandi
	def String esegui(String ricerca,String comando) {
		String risultato = ""
		Channel channel
		for(Session sessione in connessioniSSH) {
			log.debug("Test: "+sessione.getSessionId().encodeAsMD5() +' == ' + ricerca)
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				channel=sessione.openChannel("exec")
				((ChannelExec)channel).setCommand(comando)
				channel.setInputStream(null)
				((ChannelExec)channel).setErrStream(System.err)
				InputStream input=channel.getInputStream()
				channel.connect()
				byte[] tmp=new byte[1024]
				while(true){
					while(input.available()>0){
						int i=input.read(tmp, 0, 1024)
						if(i<0)break
							risultato += new String(tmp, 0, i)
					}
					if(channel.isClosed()){
						if(input.available()>0) continue
							log.info("Comando: "+comando+" [stato:"+channel.getExitStatus()+"]")
						break
					}
					try{Thread.sleep(500);}catch(Exception ee){}
				}
				channel.disconnect()
			}
			return risultato
		}
	}

	// connette la console
	def Channel console(String ricerca) {
		Channel channel
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				channel=sessione.openChannel("shell")
				channel.connect()
				canaliSSH.add(channel)
			}
			break
		}
		return channel
	}
	
	// connette la console
	def Channel stream(String ricerca,String hostTarget,Integer porta) {
		Channel channel
		for(Session sessione in connessioniSSH) {
			if (sessione.getSessionId().encodeAsMD5() == ricerca) {
				channel=sessione.getStreamForwarder(hostTarget, porta)
				channel.connect()
				canaliSSH.add(channel)
			}
			break
		}
		return channel
	}

	def List<Integer> listChannel() {
		def lista = []
		canaliSSH.each{
			lista.add(it.getId())
		}
		return lista
	}

	// ritrova i dati di una sessione per SessionId
	def Channel getChannel(int ricerca) {
		for(canale in canaliSSH) {
			if (canale.getId() == ricerca) {
				return canale
				break
			}
		}
	}

}

public class SSHUserInfo implements UserInfo {
	String passwd

	public void showMessage(String message){
		log.info(message)
	}

	public String getPassword() {
		return passwd;
	}

	public void setPassword(String pass) {
		passwd = pass;
	}

	public String getPassphrase(){return null}
	public boolean promptPassphrase(String message){return true} // Per chiedere la chiave di decodifica della password
	public boolean promptPassword(String message) {return true} // Per chiedere la password
	public boolean promptYesNo(String str) {return true} // Per accetare le key
}