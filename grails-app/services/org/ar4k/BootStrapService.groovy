/**
 * Bootstrap
 *
 * <p>Service per la gestione del boot dell'interfaccia</p>
 *
 * <p style="text-justify">
 * Questo service istanzia il primo vaso a cui si collega l'interfaccia via SSH.
 * Questo vaso, per l'interfaccia è considerato master, il bootstrap permette la creazione di
 * un vaso su OpenShift,Factory Rossonet,AWS (tramite immagine) per poi utilizzarlo</br>
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
	
	/** vasoMaster */
	Vaso vasoMaster

	/** codice attivazione ar4k, se null assente */
	String codiceAttivazioneAr4k = null

	/** configurazione proxy tra Grails e il vaso master, se null assente */
	String proxyVersoMaster = null

	/** configurazione repository ricettari */
	List<RepositoryGitBootStrap> ricettari = []

	/** configurazione proxy tra il vaso e internet, se null assente */
	String proxyMasterInternet = null

	/** utente amministratore applicativo -se non configurato nel reposirory- */
	String utenteAmministratore = 'admin'

	/** password utente amministratore applicativo */
	String passwordAmministratore = 'rossonet2012'

	/** eventuale chat Olark interfaccia */ 
	String olarkKey='1445-771-10-6904'

	/** eventuale chat Google Analytics interfaccia */
	String googleAnalytics='UA-55822070-1'

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
	Contesto contesto = new Contesto()

	/** oggetto monitoraggio */
	Stato stato = new Stato()

	/** test parametri correnti */
	Boolean provaConnessione() {
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
		}
		return risultato
	}

	/** avvia o ripristina */
	Boolean provaVaso() {
		Boolean risultato = false
		if (provaConnessione()) risultato = vasoMaster.provaVaso()
		if (risultato) {
			vasoConnesso = true
			contesto.avviaMaster(vasoMaster)
			risultato = contesto.verifica()
		}
		return risultato
	}

	/** Esprime la situazione per il debug */
	String rapporto() {
		String risultato = ''
		risultato +='configurato: '+configurato+"\n"
		risultato +='raggiungibile: '+raggiungibile+"\n"
		risultato +='contesto scelto: '+contestoScelto+"\n"
		risultato +='interfaccia scelta: '+interfacciaScelta+"\n"
		risultato +='SISTEMA PRONTO: '+inAvvio+"\n"
		risultato +='[ CONFIGURAZIONE SSH : '+utenteMaster+'@'+macchinaMaster+':'+portaMaster+' ]\n'
		risultato +=keyMaster+'\n'
		return risultato
	}
}

/**
 * RepositoryGitBootStrap
 * 
 * Struttura dati per array repository GIT 
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */
class RepositoryGitBootStrap {
	/** utente, se null accesso anonimo */
	String utente = null
	/** password utente */
	String password = null
	/** URL Repository */
	String indirizzo = 'da completare con github'
	/** stato */
	Boolean configurato = false
	/** codice errore */
	String codiceErrore = 'Nessun Errore'
}
