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
	/** password accesso ssh */
	String passwordMaster = null
	
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
	
	/** chiave accesso AWS EC2 per generare l'host con il vaso Master */
	String awsAccessKey = null
	
	/** secret kay AWS */
	String awsSecretKey = null
	
	/** chiave accesso OpenShift RH */
	String OpenShiftUtente = ''
	
	/** password openShift RH */
	String OpenShiftPassword = ''
	
	/** vero se tutti i parametri per accedere sono configurati */
	Boolean configurato = false

	/** vero se il nodo ssh è raggiungibile dall'applicativo */
	Boolean raggiungibile = false
	
	/** vero se il nodo ssh ha la directory struttura directory configurata */
	Boolean strutturaDirectory = false
	
	/** vero se il vaso utilizza un proxy e il proxy funziona*/
	Boolean provaProxyVaso = false

	/** vero se il nodo master è funzionante completamente  */
	Boolean funzionante = false
	
	/** contesto interfaccia */
	Contesto contesto = new Contesto()
	
	/** oggetto monitoraggio */
	Stato stato = new Stato()
	
	/** test parametri correnti */
	Boolean provaConnessione() {
		return false
	}
	
	/** avvia o ripristina */
	Boolean avvia() {
		return false
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
