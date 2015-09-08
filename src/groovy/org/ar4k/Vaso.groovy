/**
 * Vaso
 *
 * <p>Un vaso rappresenta un container SSH</p>
 *
 * <p style="text-justify">
 * Il vaso è l'unita base in cui operano i memi, garantisce l'esecuzione degli stessi, ospita il loro file system e
 * ospita il demone Consul</br>
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

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService
import org.jclouds.json.config.GsonModule.ByteArrayAdapter;

import com.google.common.io.ByteStreams.ByteArrayDataInputStream
import com.google.common.io.ByteStreams.ByteArrayDataOutputStream;
import com.jcraft.jsch.*

import grails.converters.JSON
import groovy.json.JsonSlurper

import java.util.zip.ZipInputStream
import grails.util.Holders

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
	/** sistema rilevato */
	String uname = ''
	/** da definire: rappresenta le funzionalità del vaso root/user space, memoria, capacità computazionale, spazio store. */
	List<String> funzionalita = []
	/** Stringa proxy (esportata come http_proxy) */
	String proxy = null
	/** l'utenza ha l'accesso root sulla macchina */
	Boolean sudo = false
	/** versione java */
	String javaVersion = null
	/** porta tunnel consul */
	int portaConsul = 8501
	/** porta tunnel ActiveMQ */
	int portaActiveMQ = 61616

	InterfacciaContestoService interfacciaContestoService

	/** esporta il vaso */
	def esporta() {
		return [
			idVaso:idVaso,
			etichetta:etichetta,
			descrizione:descrizione,
			macchina:macchina,
			porta:porta,
			utente:utente,
			key:key,
			sudo:sudo,
			path:path,
			uname:uname,
			funzionalita:funzionalita,
			javaVersion:javaVersion,
			proxy:proxy,
			portaConsul:portaConsul,
			portaActiveMQ:portaActiveMQ
		]
	}

	/** verifica la raggiungibilità di internet dal vaso */
	Boolean verificaConnettivita() {
		log.info("Verifico se "+etichetta+" raggiunge l'esterno.")
		InterfacciaContestoService.codaMessaggi("Verifico se "+etichetta+" raggiunge l'esterno.")
		String comando = 'wget hc.rossonet.name -O - 2>/dev/null | grep hera | wc -l'
		String atteso = '1\n'
		String risultato = esegui(comando)
		log.info("risultato "+comando+" = "+risultato+" (atteso: "+atteso+')')
		interfacciaContestoService.codaMessaggi("risultato "+comando+" = "+risultato+" (atteso: "+atteso+')')
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
		try {
			Holders.applicationContext.getBean("interfacciaContestoService").sendMessage("activemq:topic:interfaccia.eventi",[tipo:'CONTESTOSALVATO',messaggio:risultato.toString()])
		} catch (Exception ee){
			log.info "Evento da vaso non comunicato: "+ee.toString()
		}
		return risultato == file+"\n"?true:false
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
			sessione.disconnect()
		}catch(JSchException e) {
			log.warn("Errore connesione SSH: "+e.printStackTrace())
		}
		return risultato
	}

	/** carica un file .bar dal vaso all'interfaccia */
	String leggiBinarioProcesso(String percorso,String target) {
		String ritorno = '/tmp/'+UUID.randomUUID()+'.bar'
		FileOutputStream ritornoStream = new FileOutputStream(new File(ritorno))
		log.info("leggo: "+target+" in "+percorso)
		try {
			JSch jsch = new JSch()
			Channel canale
			ChannelSftp channelSftp
			// Aggiunge la chiave privata
			jsch.addIdentity(utente,key.getBytes(),null,null)
			Session sessione=jsch.getSession(utente, macchina, porta)
			sessione.setConfig("StrictHostKeyChecking","no")
			sessione.setConfig("UserKnownHostsFile","/dev/null")
			sessione.connect()
			canale = sessione.openChannel("sftp")
			canale.connect()
			channelSftp = (ChannelSftp)canale
			channelSftp.cd(percorso)
			log.info("directory sftp: "+channelSftp.pwd())
			InputStream risultato = channelSftp.get(target)
			int c
			byte[] buffer=new byte[1024]
			while ((c=risultato.read(buffer))!=-1 ) {
				ritornoStream.write(buffer,0,c)
			}
			risultato.close()
			ritornoStream.close()
			canale.disconnect()
			sessione.disconnect()
		}catch(JSchException e) {
			log.warn("Errore connesione SSH: "+e.printStackTrace())
		}
		return ritorno
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

	/** Avvia il demone consul sul vaso e configura una sessione ssh permanente per accedere tramite le API JAVA */
	Boolean avviaConsul(JSch connessione) {
		String comando = '~/.ar4k/ricettari/ar4k_open/i386/consul_i386 agent -data-dir ~/.ar4k/dati -bootstrap -server -dc ar4kPrivate </dev/null &>/dev/null &'
		String verifica = "~/.ar4k/ricettari/ar4k_open/i386/consul_i386 info | grep 'revision = 9a9cc934' | wc -l"
		String comandoEventiNodi = '~/.ar4k/ricettari/ar4k_open/i386/consul_i386 watch -type nodes ~/.ar4k/ricettari/ar4k_open/bin/eventoConsul.sh "nodi aggiornati"</dev/null &>/dev/null &'
		String comandoEventiService = '~/.ar4k/ricettari/ar4k_open/i386/consul_i386 watch -type services ~/.ar4k/ricettari/ar4k_open/bin/eventoConsul.sh "servizi aggiornati"</dev/null &>/dev/null &'
		String comandoEventiChecks = '~/.ar4k/ricettari/ar4k_open/i386/consul_i386 watch -type checks ~/.ar4k/ricettari/ar4k_open/bin/eventoConsul.sh "checks aggiornati"</dev/null &>/dev/null &'
		if ( esegui(verifica) != '1\n') {
			esegui('killall consul_i386')
			esegui(comando)
			esegui('sleep 6')
			esegui(comandoEventiNodi)
			esegui(comandoEventiService)
			esegui(comandoEventiChecks)
		}
		addLTunnel(connessione,portaConsul,'127.0.0.1',8500)
		return esegui(verifica) == '1\n'?true:false
	}

	/** Avvia broker ActiveMQ sul nodo Master, configura una sessione ssh permanente per connettere l'Apache Camel integrato nell'Interfaccia  */
	Boolean avviaActiveMQ(JSch connessione) {
		String comando = '~/.ar4k/ricettari/ar4k_open/apache-activemq-5.12.0/bin/activemq start'
		String verifica = "~/.ar4k/ricettari/ar4k_open/apache-activemq-5.12.0/bin/activemq status | grep 'ActiveMQ is running' | wc -l"
		if ( esegui(verifica) != '1\n') {
			esegui(comando)
		}
		addLTunnel(connessione,portaActiveMQ,'127.0.0.1',61616)
		return esegui(verifica) == '1\n'?true:false
	}

	/** Aggiunge un tunnel SSH Left*/
	void addLTunnel(JSch connessione, int lport, String rhost, int rport) {
		try {
			Channel canale
			// Aggiunge la chiave privata
			connessione.addIdentity(utente,key.getBytes(),null,null)
			Session sessione=connessione.getSession(utente, macchina, porta)
			sessione.setConfig("StrictHostKeyChecking","no")
			sessione.setConfig("UserKnownHostsFile","/dev/null")
			sessione.connect()
			sessione.setPortForwardingL(lport, rhost, rport)
		}	catch(JSchException e){
			log.warn("Errore nel tunnel SSH: "+e.printStackTrace())
		}
	}

	/** recupera i contesti salvati sul vaso */
	List<Contesto> listaContesti() {
		List<Contesto> contesti = []
		String lista = esegui("listaContesti")
		//for (String item in lista) {
		Contesto creato = new Contesto()
		contesti.add(creato)
		//}
		return contesti
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
		try {
			Holders.applicationContext.getBean("interfacciaContestoService").sendMessage("activemq:topic:interfaccia.eventi",[tipo:'SCARICORICETTARIO',messaggio:ricettario.esporta().toString()])
		} catch (Exception ee){
			log.info "Evento da vaso non comunicato: "+ee.toString()
		}
		return esito
	}

	/** legge i semi e popola i dati dal file JSON */
	Boolean caricaSemi(Ricettario ricettario) {
		if (ricettario.repositoryGit.configurato == true) {
			String comandoRicerca = "cd ~/.ar4k/ricettari ;"
			comandoRicerca += "cd "+ricettario.repositoryGit.nomeCartella+" ;"
			comandoRicerca += "find . -name '*.ar4k'"
			ricettario.semi = []
			def ricerca = esegui(comandoRicerca).tokenize('\n')
			ricerca.each{
				def jsonSlurper = new JsonSlurper()
				String fileJson = esegui("cd ~/.ar4k/ricettari/"+ricettario.repositoryGit.nomeCartella+" ; cat "+it)
				log.info("lettura "+it+" \n"+fileJson+"\n")
				if (!fileJson) fileJson = '{"etichetta":"vuoto","descrizione":"nessun seme configurato in '+it+'"}'
				try {
					def semeTarget = jsonSlurper.parseText(fileJson)
					Seme semeOggetto = new Seme(
							percorso:it,
							meme:new Meme(
							etichetta:semeTarget.etichetta,
							descrizione:semeTarget.descrizione,
							autore:semeTarget.autore,
							gestore:semeTarget.gestore,
							versione:semeTarget.versione,
							icona:semeTarget.icona,
							stato:semeTarget.stato,
							dipendenze:semeTarget.dipendenze,
							funzionalita:semeTarget.funzionalita
							))
					semeTarget.metodi.each{semeOggetto.meme.metodi.add(new Metodo(it))}
					semeTarget.stati.each{semeOggetto.meme.stati.add(new StatoMeme(it))}
					ricettario.semi.add(semeOggetto)
					log.info("Aggiungo "+semeTarget.etichetta)

				} catch(Exception e) {
					log.warn(e.printStackTrace())
				}

			}
			log.info("Nel ricettario "+ricettario+" sono presenti "+ricettario.semi.size()+" semi")
			try {
				Holders.applicationContext.getBean("interfacciaContestoService").sendMessage("activemq:topic:interfaccia.eventi",[tipo:'CARICASEMI',messaggio:"Numero semi: "+ricettario.semi.size().toString()])
			} catch (Exception ee){
				log.info "Evento da vaso non comunicato: "+ee.toString()
			}
			return true
		} else {
			return false
		}
	}

	/** legge i processi e li carica nell engine */
	Boolean caricaProcesso(ProcessEngine processEngine,String processo,String etichetta,String idMeme) {
		Boolean ritorno = false
		try {
			String comandoRicerca = "cd ~/.ar4k/ricettari ; find . -name '"+processo+"'"
			File ricerca = new File(esegui(comandoRicerca).tokenize('\n').last())
			if (ricerca) {
				String percorso =  ".ar4k/ricettari/"+ricerca.getParent()
				File definizione = new File(leggiBinarioProcesso(percorso,ricerca.getName()))
				log.info("carico processo da copia locale: "+definizione)
				ZipInputStream processoDefinizione = new ZipInputStream(new FileInputStream(definizione))
				RepositoryService repositoryService = processEngine.getRepositoryService()
				//def targetEngine = processEngine.getRepositoryService().createDeploymentQuery().deploymentName(idMeme).singleResult() {
				repositoryService.createDeployment().name(idMeme)
						.addZipInputStream(processoDefinizione)
						.deploy()
				definizione.delete()
				ritorno = true
				log.info("caricamento completato: "+percorso+"/"+ricerca.getName())
			}
		} catch(Exception e) {
			log.warn(e.printStackTrace())
		}
		return ritorno
	}

}


