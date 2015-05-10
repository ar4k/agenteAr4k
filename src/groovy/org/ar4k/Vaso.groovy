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
import grails.converters.JSON

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
	/** da definire: rappresenta le funzionalità del vaso root/user space, memoria,capacità computazionale,spazio store. */
	List<String> funzionalita = []
	/** Stringa proxy (esportata come http_proxy) */
	String proxy = null
	/** tollera errori nelle procedure */
	Boolean tolleranza = false
	/** memi attivati sul vaso */
	List<Meme> memi = []
	/** l'utenza ha l'accesso sudo sulla macchina */
	Boolean sudo = false

	/** esporta il vaso */
	def esporta() {
		return [
			idVaso:idVaso,
			descrizione:descrizione,
			macchina:macchina,
			porta:porta,
			utente:utente,
			key:key,
			sudo:sudo,
			path:path,
			uname:uname,
			proxy:proxy,
			tolleranza:tolleranza
		]
	}

	/** verifica la raggiungibilità di internet dal vaso */
	Boolean verificaConnettivita() {
		log.info("Verifico se "+etichetta+" raggiunge l'esterno.")
		String comando = 'wget hc.rossonet.name -O - 2>/dev/null | grep hera | wc -l'
		String atteso = '1\n'
		String risultato = esegui(comando)
		log.info("risultato "+comando+" = "+risultato+" (atteso: "+atteso+')')
		return risultato == atteso?true:false
	}

	/** salva un contesto sul vaso*/
	Boolean salvaContesto(Contesto contesto) {
		String file = contesto.esporta() as JSON
		String interruzione = "EOF-"+UUID.randomUUID()+"-AR4K"
		String comandoEsecuzione="cat > ~/.ar4k/contesti/"+contesto.idContesto+".xml << "+interruzione+"\n"+file+"\n"+interruzione+"\n"
		String comandoControllo="cat ~/.ar4k/contesti/"+contesto.idContesto+".xml"
		esegui(comandoEsecuzione)
		String risultato=esegui(comandoControllo)
		return risultato == file+"\n"?true:false
	}

	/** scrive un file */
	Boolean scrivi(String file, String path) {
		return false
	}

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
			log.warn("Errore connesione SSH: "+e.printStackTrace())
		}
		return risultato
	}

	/** esegui comando nel contesto specifico*/
	String esegui(Contesto contesto,String comando) {
		return null
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

	/** recupera i contesti salvati nel vaso */
	List<Contesto> listaContesti() {
		List<Contesto> contesti = []
		String lista = esegui("listaContesti")
		//for (String item in lista) {
		Contesto creato = new Contesto()
		contesti.add(creato)
		//}
		return contesti
	}

	/** lista semi */
	List<Seme> listaSemi() {

	}

	/** stringa predefinita */
	String toString() {
		return etichetta+" => "+utente+"@"+macchina+":"+porta
	}

	/** scarica un ricettario sul nodo se attivo */
	Boolean avviaRicettario(Ricettario ricettario) {
		if (ricettario.repositoryGit.configurato == true) {
			String comando = "cd ~/.ar4k/ricettari ; if [ -e "+ricettario.repositoryGit.nomeCartella+" ] ; then cd "+ricettario.repositoryGit.nomeCartella+"; git pull; else  git clone "+ricettario.repositoryGit.indirizzo+" "+ricettario.repositoryGit.nomeCartella+"; fi"
			esegui(comando)
		}
		String comandoCont = "cd ~/.ar4k/ricettari ; if [ -e "+ricettario.repositoryGit.nomeCartella+"/LICENSE ] ; then echo -n 43; fi"
		String atteso = '43'
		String risultato = esegui(comandoCont)
		log.info("risultato "+comandoCont+" = "+risultato+" (atteso: "+atteso+')')
		Boolean esito = (risultato == atteso?true:false)
		if (esito) ricettario.aggiornato = new Date()
		if (esito) log.info("Esito aggiornamento ricettario "+ricettario+" su "+etichetta+": (ok)")
		return esito
	}

	/** legge i semi e popola i dati */
	Boolean caricaSemi(Ricettario ricettario) {
		if (ricettario.repositoryGit.configurato == true) {
			String comandoRicerca = "cd ~/.ar4k/ricettari ;"
			comandoRicerca += "cd "+ricettario.repositoryGit.nomeCartella+" ;"
			comandoRicerca += "find . -name '*.ar4k'"
			def ricerca = esegui(comandoRicerca).tokenize('\n')
			ricettario.semi = []
			Meme memeCaricamento = new Meme(etichetta:ricerca)
			ricerca.each{ricettario.semi.add(percorso:ricerca,meme:memeCaricamento)}
			log.info("Nel ricettario "+ricettario+" sono presenti "+ricettario.semi.size()+" semi")
			return true
		} else {
			return false
		}
	}

}
