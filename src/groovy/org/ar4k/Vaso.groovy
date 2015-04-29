/**
 * Vaso
 *
 * <p>Un vaso rappresenta un container SSH</p>
 *
 * <p style="text-justify">
 * Il vaso è l'unita base in cui operano i memi, garantisce l'esecuzione degli stessi, ospita il loro file system,
 * coordina i tunnel con autossh, gesisce il proprio cron locale ed espone le API per il ciclo di vita dei propri oggetti</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Meme
 * @see org.ar4k.Ricettario
 * @see org.ar4k.Interfaccia
 * @see org.ar4k.Contesto
 */

package org.ar4k

import com.jcraft.jsch.*

class Vaso {
	/** id univoco vaso */
	String idVaso = UUID.randomUUID()
	/** etichetta vaso */
	String etichetta = ''
	/** descrizione vaso */
	String descrizione ='Vaso AR4K by Rossonet'
	/** host vaso */
	String macchina = '127.0.0.1'
	/** porta vaso */
	Integer porta = 22
	/** utente vaso */
	String utente = 'ar4k'
	/** chiave privata ssh */
	String key = null
	/** variabile PATH sulla macchina */
	String path = '/usr/local/bin:/usr/bin:/bin'
	/** sistema rilavato */
	String uname = ''
	/** indirizzi delle schede
	List<puntoRete> indirizziFisici = []

	/** esegui un comando ssh sul vaso */
	String esegui(String comando) {
		log.info("connessione al vaso: "+etichetta)
		String risultato = ''
		comando = 'export PATH='+path+' ; '+comando
		try {
			JSch jsch = new JSch()
			Channel canale
			// Aggiunge la chiave privata
			jsch.addIdentity(utente,key.getBytes(),null,null)
			Session sessione=jsch.getSession(utente, macchina, porta)
			sessione.setConfig("StrictHostKeyChecking","no")
			sessione.setConfig("UserKnownHostsFile","/dev/null")
			sessione.connect()
			canale=sessione.openChannel("exec")
			log.info("comando controllo sessione SSH :"+comando)
			((ChannelExec)canale).setCommand(comando)
			canale.setInputStream(null)
			((ChannelExec)canale).setErrStream(System.err)
			InputStream input=canale.getInputStream()
			canale.connect()
			byte[] tmp=new byte[1024]
			while(true){
				while(input.available()>0){
					int i=input.read(tmp, 0, 1024)
					if(i<0)break
						risultato += new String(tmp, 0, i)
				}
				if(canale.isClosed()){
					if(input.available()>0) continue
						log.info("Comando: "+comando+" [stato:"+canale.getExitStatus()+"]")
					break
				}
				try{Thread.sleep(500);}catch(Exception ee){}
			}
			canale.disconnect()
		}catch(JSchException e) {
			log.warn("Errore connesione SSH: "+e.toString())
		}
		return risultato
	}


	/** prova la connesione ssh al vaso */
	Boolean provaConnessione() {
		log.info("Provo connessione a "+etichetta)
		String comando = 'echo -n $((6+4))'
		String atteso = '10'
		String risultato = esegui(comando)
		log.info("risultato "+comando+" = "+risultato+" (atteso: "+atteso+')')
		return risultato == atteso?true:false
	}

	/** prova la funzionalità del vaso come nodo ar4k ed eventualmente configura il filesystem */
	Boolean provaVaso() {
		String creaDirectory = """
		cd ~
		mkdir -p .ar4k
		mkdir -p .ar4k/contesti
		mkdir -p .ar4k/ricettari
		mkdir -p .ar4k/dati
		"""
		String battezza = "echo '"+idVaso+"'>>~/.ar4k/dati/prove.log"
		String controllo = """
		if [ -e ~/.ar4k/dati/prove.log ]
		then
		echo -n 42
		fi
		"""
		String atteso = '42'
		
		esegui(creaDirectory)
		esegui(battezza)
		
		String risultato=esegui(controllo)
		log.info("risultato "+controllo+" = "+risultato+" (atteso: "+atteso+')')
		return risultato == atteso?true:false
	}

}

/**
 * PuntoRete rappresenta un interfaccia di rete su un vaso
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */
class puntoRete {
	String indirizzoIp = ''
	String sottoMaschera = ''
	String mac = ''
}