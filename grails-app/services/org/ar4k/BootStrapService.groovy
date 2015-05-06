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

@Transactional
class BootStrapService {

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
	List<Utente> utentiInContesto = []

	/** verifica se l'interfaccia raggiunge il gw ar4k */
	Boolean verificaConnettivitaInterfaccia() {
		String indirizzoTest='http://hc.rossonet.name'
		Boolean risultato=false
		if (escludiProveConnessione==true) {
			log.info("Test di rete esclusi!")
			risultato=true
		} else {
			log.info("Prova la connessione a "+indirizzoTest)
			try {
				URL url = new URL(indirizzoTest)
				HttpURLConnection con = (HttpURLConnection)url.openConnection()
				log.debug(con.responseCode)
				if (con.responseCode==200){
					risultato = true
					log.info(indirizzoTest+": Connessione OK")
				}
				con.disconnect()
			} catch (MalformedURLException e) {
				log.warn(e.printStackTrace())
			} catch (IOException e) {
				log.warn(e.printStackTrace())
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
		log.info("Provo la connesione master")
		Boolean risultato = vasoMaster.provaConnessione()
		if (risultato) {
			configurato = true
			raggiungibile = true
		} else {
			configurato = false
			raggiungibile = false
		}
		return risultato
	}

	/** avvia o ripristina la connesione ssh al vaso master*/
	Boolean caricaVasoMaster() {
		Boolean risultato = false
		if (provaConnessioneMaster()) risultato = vasoMaster.provaVaso()
		if (risultato) {
			vasoConnesso = true
			contestiInMaster = vasoMaster.listaContesti()
			Contesto demo = creaContestoAr4kBoot()
			contestiInMaster.add(demo)
			log.info("Contesti disponibili dopo la creazione del contesto demo: "+contestiInMaster)
		} else {
			vasoConnesso = false
		}
		return risultato
	}

	/** cerifica la connettività verso internet del vaso master */
	Boolean verificaConnettivitaVasoMaster() {
		Boolean risultato = false
		if(vasoMaster.verificaConnettivita()) {
			risultato = true
			log.info("Il vaso master accede ad Internet")
		} else {
			log.info("Il vaso master NON accede ad Internet")
		}
		if (escludiProveConnessioneVaso==true) {
			log.info("Escluso il test di configurazione del vaso")
			risultato = true
		}
		return risultato
	}

	/** carica il contesto per id contesto*/
	Boolean caricaContesto(String contestoSceltaConf) {
		Boolean ritorno = false
		if (caricaVasoMaster()) {
			String primarioContesto = idContestoScelto
			if (contestoSceltaConf) primarioContesto = contestoSceltaConf
			log.info("Provo il caricamento di "+primarioContesto)
			log.info("Contesti disponibili: "+contestiInMaster)
			Contesto contestoTarget = contestiInMaster.find{it.idContesto==primarioContesto}
			log.info("Trovato "+contestoTarget)
			if (contestoTarget) {
				contesto = contestoTarget
				if(contesto.avviaContesto()) {
					contestoScelto = true
					ritorno = true
					log.info("Attestato sul contesto "+contesto)
					idContestoScelto = contesto.idContesto
					interfacceInContesto = contesto.interfacce
					utentiInContesto = contesto.utentiRuoli*.utente
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
		Boolean ritorno = false
		if (caricaContesto(contestoSceltaConf)) {
			String primarioInterfaccia = idInterfacciaScelta
			if (interfacciaSceltaConf) primarioInterfaccia = interfacciaSceltaConf
			log.info("Provo il caricamento dell'interfaccia "+primarioInterfaccia)
			log.info("Interfacce disponibili: "+interfacceInContesto)
			Interfaccia interfacciaTarget = interfacceInContesto.find{it.idInterfaccia==primarioInterfaccia}
			if (interfacciaTarget) {
				interfaccia = interfacciaTarget
				if(interfaccia.avviaInterfaccia()) {
					interfacciaScelta = true
					inAvvio = false
					ritorno = true
					log.info("Attestato sull'interfaccia "+interfaccia)
					idInterfacciaScelta = interfaccia.idInterfaccia
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
		risultato +='Configurazione proxy Java -> Vaso Master: '+proxyVersoMaster+"\n"
		risultato +='Configurazione proxy Vaso Master -> Internet: '+proxyMasterInternet+"\n"
		risultato +='Contesto: '+contesto+"\n"
		risultato +='[ VASO MASTER SSH : '+utenteMaster+'@'+macchinaMaster+':'+portaMaster+' ]\n'
		risultato +=keyMaster+'\n'
		return risultato
	}

	/** Crea il contesto iniziale per il bootstrap AR4K */
	Contesto creaContestoAr4kBoot() {
		log.info("Creo il contesto di demo per il boot su "+vasoMaster)
		Contesto contestoCreato = new Contesto(
				idContesto:'Bootstrap-Ar4k',
				etichetta:"Contesto generato per avvio Ar4k"
				)
		contestoCreato.configuraMaster(vasoMaster)
		Interfaccia interfacciaDemo = creaInterfacciaAr4k()
		contestoCreato.interfacce.add(interfacciaDemo)
		log.info("Contesto demo creato: "+contestoCreato)
		return contestoCreato
	}

	/** Crea l'interfaccia iniziale per il boot */
	Interfaccia creaInterfacciaAr4k() {
		Interfaccia interfacciaCreata = new Interfaccia(idInterfaccia:'Bootstrap-Ar4k')
		log.info("Ho creato l'interfaccia demo:"+interfacciaCreata)
		return interfacciaCreata
	}

	/** Aggiunge un utente al contesto */
	Boolean aggiungiUtente(String nome,String password) {
		log.info("Creo l'utente "+nome)
		Boolean risultato = false
		def adminRole = new Ruolo(authority:'ROLE_ADMIN')
		def testUser = new Utente(username:nome, password:password)
		UtenteRuolo utenteRuolo = new UtenteRuolo(utente:testUser,ruolo:adminRole)
		contesto.utentiRuoli.add(utenteRuolo)
		if (contesto.utentiRuoli.size() > 0) {
			risultato = true
		}
		return risultato
	}
}


