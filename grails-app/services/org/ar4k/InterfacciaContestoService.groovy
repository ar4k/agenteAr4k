/**
 * Interfaccia Contesto
 *
 * <p>Gestisce la factory centrale dell'interfaccia per la costruzione dei servizi.</p>
 *
 * <p style="text-justify">
 * Tutti gli eventi passano per questo service (una volta completato il bootstrap), in particolare questo service distribuisce gli eventi ai memi e alle code 
 * utente (messaggi, task, eventi)</br>
 * Questa interfaccia permette ai memi la gestione di operazioni complesse quali il load balancer, i dns e i QR</br>
 * La configurazione di Quartz è in PingJob.
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Interfaccia
 * @see org.ar4k.Contesto
 */

package org.ar4k

import org.codehaus.groovy.grails.commons.GrailsApplication

class InterfacciaContestoService {
	
	// Tramite Camel garantire l'accesso da vari protocolli
	//static expose = ['jmx']

	/** Contesto applicativo Grails */	
	GrailsApplication grailsApplication
	/** Contesto in esecuzione */
	Contesto contesto
	/** Stato in esecuzione */
	Stato stato
	/** Interfaccia corrente */
	Interfaccia interfaccia
	/** eventi in controllo */
	List<Evento> eventi = []

	/** Descrizione a toString() */
	String toString() {
		String risultato = "CONTESTO ["+contesto.etichetta+"]\n"
		risultato += contesto.descrizione+"\n"
		risultato += contesto.toString()+"\n"
		risultato += "--------------------------------------\n"
		risultato += stato.toString()+"\n"
		risultato += "--------------------------------------\n"
		return risultato
	}

	/** Stampa la memoria con Camel */
	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}

	/** esegue le procedura ogni 5 min. tramite Quartz */
	void battito() {
		log.warn("Errore nel salvataggio del contesto!")
		freeMemory()
	}
	
	Processo eseguiMetodo(Metodo metodoTarget) {
		
	}
}

/**
 * Eventi Monitorati	
 * 
 * <p style="text-justify">
 * La gestione degli eventi è la base per la propagazione delle informazioni di configurazione (aggiornamento ruoli autenticazioni in particolare), 
 * di monitoraggio e segnalazione e per l'autoscale dei memi</br>
 * </p>
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */
class Evento {
	/** check attivatori */
	def testTrigger = [] // closure ?
	/** azione se trigger positivo */
	List<Metodo> listaMetodi = []
	/** controllo eventi attivato ? */
	Boolean attivato = false
}

