/**
 * Bootstrap
 *
 * <p>Service per la gestione del boot dell'interfaccia</p>
 *
 * <p style="text-justify">
 * Questo service istanzia il primo vaso a cui si collega l'interfaccia via SSH.
 * Questo vaso, per l'interfaccia è considerato master, il bootstrap permette la creazione di
 * un vaso su OpenShift,Factory Rossonet,AWS (tramite immagine) per poi utilizzarlo.</br>
 * 
 * Il service, per tutto il ciclo di vita dell'interfaccia grafica, conserva lo stato e rende disponibili 
 * i metodi per cambiare il vaso master e/o il contesto chiudendo correttamente il precedente.</b>
 * 
 * Questo service è invocato nel bootstrap per avviare l'interfaccia.</b>
 *
 * <strong>TODO:</strong>
 * Immagine macchina base su ks e OVA
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Interfaccia
 */

package org.ar4k

import grails.transaction.Transactional
import com.jcraft.jsch.*

@Transactional
class BootStrapService {

	/** iniezione service principale */
	InterfacciaContestoService interfacciaContestoService

	/** host accesso ssh */
	String macchinaMaster = null
	/** porta accesso ssh */
	Integer portaMaster = null
	/** utente ssh */
	String utenteMaster = null
	/** certificato privato per accesso ssh */
	String keyMaster = null
	/** contesto scelto */
	String idContestoScelto = null
	/** interfaccia scelta */
	String idInterfacciaScelta = null

	/** vasoMaster */
	Vaso vasoMaster

	/** se vero esclude i controlli di raggiungibilità */
	Boolean escludiProveConnessione = false
	/** se vero esclude i controlli di raggiungibilità del vaso */
	Boolean escludiProveConnessioneVaso = false

	/** codice attivazione ar4k, se null assente */
	String codiceAttivazioneAr4k = null

	/** configurazione proxy tra Grails e il vaso master, se null assente */
	String proxyVersoMaster = null
	/** configurazione proxy tra il vaso e internet, se null assente */
	String proxyMasterInternet = null
	/** configurazione proxy tra Grails e il vaso master password*/
	String passwordProxyVersoMaster = null
	/** configurazione proxy tra il vaso e internet password */
	String passwordProxyMasterInternet = null

	/** vero se tutti i parametri per accedere sono configurati */
	Boolean configurato = false
	/** vero se il nodo ssh è raggiungibile dall'applicativo */
	Boolean raggiungibile = false
	/** vero se il vaso è funzionante*/
	Boolean vasoConnesso = false
	/** vero se il contesto è scelto */
	Boolean contestoScelto = false
	/** vero se l'interfaccia è scelta */
	Boolean interfacciaScelta = false
	/** vero se il nodo master è funzionante completamente  */
	Boolean inAvvio = true

	/** contesto interfaccia */
	Contesto contesto

	/** Interfaccia apllicativa del contesto */
	Interfaccia interfaccia

	/** contesti disponibili */
	List<Contesto> contestiInMaster = []
	/** interfacce disponibili nel contesto */
	List<Interfaccia> interfacceInContesto = []
	/** utenti disponibili nel contesto */
	List<UtenteRuolo> utentiInContesto = []

	/** verifica se l'interfaccia raggiunge il gw ar4k */
	Boolean verificaConnettivitaInterfaccia() {
		String indirizzoTest='http://hc.rossonet.name'
		Boolean risultato=false
		if (escludiProveConnessione==true) {
			log.info("Test di rete esclusi! (escludiProveConnessione==true)")
			risultato=true
		} else {
			log.info("verificaConnettivitaInterfaccia() verso "+indirizzoTest)
			try {
				URL url = new URL(indirizzoTest)
				HttpURLConnection con = (HttpURLConnection)url.openConnection()
				log.debug(con.responseCode)
				if (con.responseCode==200){
					risultato = true
					log.info(indirizzoTest+"verificaConnettivitaInterfaccia(): (ok)")
				} else {
					log.warn(indirizzoTest+"verificaConnettivitaInterfaccia(): ERRORE")
				}
				con.disconnect()
			} catch (MalformedURLException e) {
				log.warn("verificaConnettivitaInterfaccia(): "+e.printStackTrace())
			} catch (IOException e) {
				log.warn("verificaConnettivitaInterfaccia(): "+e.printStackTrace())
			}
		}
		return risultato
	}

	/** test parametri correnti e connesione al vaso master*/
	Boolean provaConnessioneMaster() {
		vasoMaster= new Vaso(
				etichetta:utenteMaster+'@'+macchinaMaster+':'+portaMaster,
				descrizione:'Nodo Master Ar4k',
				macchina:macchinaMaster,
				porta:portaMaster.toInteger(),
				utente:utenteMaster,
				key:keyMaster
				)
		log.info("provaConnessioneMaster() verso "+vasoMaster)
		Boolean risultato = vasoMaster.provaConnessione()
		if (risultato) {
			log.info("provaConnessioneMaster() verso "+vasoMaster+" (ok)")
			configurato = true
			raggiungibile = true
		} else {
			log.warn("provaConnessioneMaster() verso "+vasoMaster+" NON RIUSCITA!")
			configurato = false
			raggiungibile = false
		}
		return risultato
	}

	/** avvia o ripristina la connesione ssh al vaso master*/
	Boolean caricaVasoMaster() {
		Boolean risultato = false
		log.info("caricaVasoMaster() su "+vasoMaster)
		if (provaConnessioneMaster()) risultato = vasoMaster.provaVaso()
		if (risultato) risultato = vasoMaster.avviaConsul(interfacciaContestoService.connessione)
		if (risultato) {
			log.info("caricaVasoMaster() su "+vasoMaster+" (ok)")
			vasoConnesso = true
			contestiInMaster = vasoMaster.listaContesti()
			Contesto demo = creaContestoAr4kBoot()
			contestiInMaster.add(demo)
			log.info("Contesti disponibili dopo la creazione del contesto demo: "+contestiInMaster)
		} else {
			log.warn("caricaVasoMaster() su "+vasoMaster+" ERRORE")
			risultato = false
			vasoConnesso = false
		}
		return risultato
	}

	/** cerifica la connettività verso internet del vaso master */
	Boolean verificaConnettivitaVasoMaster() {
		log.info("verificaConnettivitaVasoMaster()")
		Boolean risultato = false
		if(vasoMaster.verificaConnettivita()) {
			risultato = true
			log.info("Il vaso master accede ad Internet")
		} else {
			log.warn("Il vaso master NON accede ad Internet")
		}
		if (escludiProveConnessioneVaso==true) {
			log.info("Escluso il test di raggiungibilità di Internet dal vaso master")
			risultato = true
		}
		return risultato
	}

	/** carica il contesto per id contesto*/
	Boolean caricaContesto(String contestoSceltaConf) {
		log.info("caricaContesto("+contestoSceltaConf+")")
		Boolean ritorno = false
		if (caricaVasoMaster()) {
			String primarioContesto = idContestoScelto
			if (contestoSceltaConf) primarioContesto = contestoSceltaConf
			log.info("Provo il caricamento di "+primarioContesto)
			log.debug("Contesti disponibili: "+contestiInMaster)
			Contesto contestoTarget = contestiInMaster.find{it.idContesto==primarioContesto}
			log.info("Caricato "+contestoTarget)
			if (contestoTarget) {
				contesto = contestoTarget
				if(contesto.avviaContesto()) {
					contestoScelto = true
					ritorno = true
					log.info("Attestato sul contesto "+contesto)
					idContestoScelto = contesto.idContesto
					interfacceInContesto = contesto.interfacce
					log.info("Interfacce trovate nel contesto "+interfacceInContesto)
					contesto.utentiRuoli.each{utentiInContesto.add(it)}
					log.info("UtentiRuolo trovati nel contesto "+utentiInContesto)
				}
			}
		}
		return ritorno
	}

	/** 
	 * carica tutto e avvia
	 * 
	 * @param contestoSceltaConf Contesto scelto per l'avvio (id)
	 * @param interfacciaSceltaConf Interfaccia scelta per l'avvio (id)
	 */
	Boolean avvia(String contestoSceltaConf,String interfacciaSceltaConf) {
		log.info("avvia("+contestoSceltaConf+","+interfacciaSceltaConf+")")
		Boolean ritorno = false
		interfacciaContestoService.connessione = new JSch()
		if (caricaContesto(contestoSceltaConf)) {
			String primarioInterfaccia = idInterfacciaScelta
			if (interfacciaSceltaConf) primarioInterfaccia = interfacciaSceltaConf
			log.info("Provo il caricamento dell'interfaccia "+primarioInterfaccia)
			log.debug("Interfacce disponibili: "+interfacceInContesto)
			Interfaccia interfacciaTarget = interfacceInContesto.find{it.idInterfaccia==primarioInterfaccia}
			log.info("Caricata "+interfacciaTarget)
			if (interfacciaTarget) {
				interfaccia = interfacciaTarget
				if(interfaccia.avviaInterfaccia()) {
					interfacciaScelta = true
					inAvvio = false
					ritorno = true
					log.info("Attestato sull'interfaccia "+interfaccia)
					idInterfacciaScelta = interfaccia.idInterfaccia
					interfacciaContestoService.contesto=contesto
					interfacciaContestoService.interfaccia=interfaccia
					interfacciaContestoService.stato=new Stato(etichetta:'Stato Contesto '+contesto.idContesto)
					log.info("Attiva il gestore di processo Activiti")
					interfacciaContestoService.attivaActiviti()
					log.info("Attiva Consul")
					interfacciaContestoService.connettiConsul()
					log.info("Attiva il builder JCloud")
					interfacciaContestoService.builderJCloud()
					log.info("Delegato il controllo a InterfacciaContestoService")
					log.info("Grafica caricata")
					log.info(interfacciaContestoService)
				}
			}
		}
		return ritorno
	}

	/** metodo senza parametri avvia */
	Boolean avvia() {
		avvia(idContestoScelto,idInterfacciaScelta)
	}

	/** ritorna il rapporto della situazione del processo di boot */
	String toString() {
		String risultato = '[ STATO ]\n'
		risultato +='configurato: '+configurato+"\n"
		risultato +='raggiungibile: '+raggiungibile+"\n"
		risultato +='contesto scelto: '+contestoScelto+" ("+idContestoScelto+")\n"
		risultato +='interfaccia scelta: '+interfacciaScelta+" ("+idInterfacciaScelta+")\n"
		risultato +='SISTEMA IN CONFIGURAZIONE: '+inAvvio+"\n"
		risultato +='Codice commerciale Ar4k: '+codiceAttivazioneAr4k+"\n"
		risultato +='Escludi prove di raggiungibilità della rete: '+escludiProveConnessione+"\n"
		risultato +='Escludi prove di raggiungibilità della rete vaso master: '+escludiProveConnessioneVaso+"\n"
		risultato +='Configurazione proxy Java -> Vaso Master: '+proxyVersoMaster+"\n"
		risultato +='Configurazione proxy Vaso Master -> Internet: '+proxyMasterInternet+"\n"
		risultato +='Contesto: '+contesto+"\n"
		risultato +='[ VASO MASTER SSH : '+utenteMaster+'@'+macchinaMaster+':'+portaMaster+' ]\n'
		risultato +=keyMaster+'\n'
		return risultato
	}

	/** Crea il contesto iniziale per il bootstrap AR4K */
	Contesto creaContestoAr4kBoot() {
		log.info("Creo il contesto di base per il boot su "+vasoMaster)
		Contesto contestoCreato = new Contesto(
				idContesto:'Bootstrap-Ar4k',
				etichetta:"Contesto generato per avvio Ar4k"
				)
		contestoCreato.configuraMaster(vasoMaster)
		Interfaccia interfacciaDemo = creaInterfacciaAr4k()
		Ricettario ricettario = new Ricettario()
		contestoCreato.interfacce.add(interfacciaDemo)
		contestoCreato.ricettari.add(ricettario)
		log.debug("Contesto base creato: "+contestoCreato)
		return contestoCreato
	}

	/** Crea l'interfaccia iniziale per il boot */
	Interfaccia creaInterfacciaAr4k() {
		Interfaccia interfacciaCreata = new Interfaccia(idInterfaccia:'Bootstrap-Ar4k')
		log.info("Ho creato l'interfaccia base:"+interfacciaCreata)
		return interfacciaCreata
	}

	/** Aggiunge un utente al contesto */
	Boolean aggiungiUtente(String nome,String password) {
		log.debug("Creo l'utente "+nome)
		Boolean risultato = false
		def adminRole = Ruolo.create()
		adminRole.authority='ROLE_ADMIN'
		def testUser = Utente.create()
		testUser.username=nome
		testUser.password=password
		UtenteRuolo utenteRuolo = UtenteRuolo.create()
		utenteRuolo.utente=testUser
		utenteRuolo.ruolo=adminRole
		testUser.save(flush:true)
		adminRole.save(flush:true)
		utenteRuolo.save(flush:true)
		utentiInContesto.add(utenteRuolo)
		if (utentiInContesto.size() > 0) {
			log.info("Creato l'utente "+nome)
			risultato = true
		}
		return risultato
	}
}


