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

import org.activiti.engine.ProcessEngine
import org.activiti.engine.ProcessEngineConfiguration
import org.activiti.engine.RepositoryService
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

	/** engine Activiti BPM */
	ProcessEngine processEngine

	/** attiva il processengine Activiti */
	void attivaActiviti() {
		//SpringProcessEngineConfiguration
		processEngine = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration()
				.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_CREATE_DROP)
				.setJdbcUrl("jdbc:h2:mem:activitiDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE")
				.setAsyncExecutorEnabled(true)
				.setAsyncExecutorActivate(true)
				.buildProcessEngine()
	}

	String processoTest() {
		InputStream xmlFile = new FileInputStream(new File(grailsApplication.parentContext.getResource("/activiti/VacationRequest.bpmn20.xml").file.toString()))
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
				.addInputStream(grailsApplication.parentContext.getResource("/activiti/VacationRequest.bpmn20.xml").file.toString(),xmlFile)
				.deploy();

		return repositoryService.createProcessDefinitionQuery().count()
	}

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
		freeMemory()
	}
}

