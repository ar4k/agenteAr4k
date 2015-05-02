/**
 * Interfaccia
 *
 * <p>Un interfaccia rappresenta i dati di un istanza Ar4k</p>
 *
 * <p style="text-justify">
 * Per funzionare, un istanza è sempre parte di un Contesto collegato ad un Vaso</br>
 * Il processo di creazione dell'interfaccia prevede il collegamento SSH ad un vaso tramite file
 * di configurazione o wizard iniziale.</br>
 * Ogni interfaccia dispone di propria associazione a gli account social in funzione delle password centrali, un contesto può ospitare più interfacce
 * ed ha un unico store degli utenti (con nome assoluto) e dei relativi ruoli.</br>
 * </br>
 * Un interfaccia recupera le informazioni del suo funzionamento dal contesto a cui è collegata ed espone al contesto i metodi per operare
 * sui service</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Contesto
 * @see org.ar4k.SshService
 * @see org.ar4k.CamelService
 */

package org.ar4k

class Interfaccia {

	// Oggetti iniettati da Grails - INIZIO -
	
	/** service per connessioni SSH utilizzato dall'interfaccia*/
	SshService sshService
	/** service Apache Camel utilizzato dall'interfaccia*/
	CamelService camelService
	/** service Kettle utilizzato dall'interfaccia*/
	KettleService kettleService
	/** service JCloud utilizzato dall'interfaccia*/
	JCloudService jCloudService
	/** service JSoup utilizzato dall'interfaccia*/
	JSoupService jSoupService
	/** service WebSocket utilizzato dall'interfaccia*/
	WebSocketService webSocketService
	/** service per interagire con il contesto */
	InterfacciaContestoService interfacciaContestoService

	// Oggetti iniettati da Grails - FINE -
	
	/** oggetto monitoraggio e stato reale*/
	Stato stato = new Stato()
	
	/** id univoco interfaccia */
	String idInterfaccia = UUID.randomUUID()
	/** etichetta interfaccia */
	String etichetta = ''
	/** descrizione interfaccia */
	String descrizione ='UI AR4K by Rossonet'
	
	/** esporta la configurazione dell'interfaccia */
	def esporta() {
		log.info("esporta() l'interfaccia: "+idInterfaccia)
		return [
			idInterfaccia:idInterfaccia,
			etichetta:etichetta,
			descrizione:descrizione
			]
	}
	
	/** metodo descrizione */
	String toString() {
		return "["+idInterfaccia+"] "+etichetta+" ("+stato+")"
	}
	
	/** configura i service in funzione dell'interfaccia */
	Boolean avviaInterfaccia() {
		return true
	}
}
