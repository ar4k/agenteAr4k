/**
 * Interfaccia Contesto
 *
 * <p>Gestisce la struttura dati del contesto attivo.</p>
 *
 * <p style="text-justify">
 * Il servizio viene attivato da BootStrapService.</br>
 * La configurazione di Quartz è in PingJob.
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.2-alpha
 * @see org.ar4k.BootStrapService
 * @see org.ar4k.Interfaccia
 * @see org.ar4k.Contesto
 */

package org.ar4k

import java.util.zip.ZipInputStream

import org.activiti.engine.ProcessEngine
import org.activiti.engine.ProcessEngineConfiguration
import org.activiti.engine.RepositoryService
import org.codehaus.groovy.grails.commons.GrailsApplication
import org.jclouds.Context
import org.jclouds.ContextBuilder
import org.jclouds.compute.*
import org.jclouds.compute.domain.NodeMetadata
import org.jclouds.compute.domain.Template
import org.jclouds.rest.config.SetCaller.Module
import org.pentaho.di.repository.Repository;

import com.ecwid.consul.v1.ConsulClient
import com.jcraft.jsch.JSch;
import com.ecwid.consul.v1.agent.model.NewService
import org.pentaho.di.core.KettleEnvironment

class InterfacciaContestoService {

	// Tramite Camel garantire l'accesso da vari protocolli
	//static expose = ['jmx']

	/** Contesto applicativo Grails */	
	GrailsApplication grailsApplication
	/** Contesto in esecuzione */
	Contesto contesto
	/** Stato in esecuzione -Consul- */
	Stato stato
	/** Interfaccia corrente */
	Interfaccia interfaccia
	/** connessione ssh consul */
	JSch connessioneConsul = null
	/** connessione ssh activemq */
	JSch connessioneActiveMQ = null

	/** engine Activiti BPM -dipendenza iniettata */
	ProcessEngine processEngine
	/** lista contesti JCloud operativi */
	List<Context> jCloudServer = []
	/** lista repositories Kettle operativi */
	List<Repository> kettleRepositories = []

	/** connette il demone Consul al nodo ssh master alle API JAVA **/
	void connettiConsul() {
		try {
			stato.consulBind = new ConsulClient('http://127.0.0.1',8501)
			String risposta = stato.consulBind.getCatalogDatacenters()
			log.info("Datacenter disponibili: "+risposta)
			NewService nodoMaster = new NewService()
			nodoMaster.setId("consulAPI")
			nodoMaster.setName("consulAPI")
			nodoMaster.setPort(8500)
			nodoMaster.setAddress(stato.consulBind.getStatusLeader().getValue().split(":")[0])
			nodoMaster.setTags(["consulApiInterfaccia"])
			stato.consulBind.agentServiceRegister(nodoMaster)
		} catch (Exception ee) {
			log.warn("Errore avvio Consul: "+ee.printStackTrace())
		}
	}

	/** attiva il sottosistema Kettle **/
	void initKettle() {
		KettleEnvironment.init()
	}

	/** collega un hypervisor Docker **/
	void dockerJCloudCompute(String endpoint,String cert,String key) {
		// per i self-signed
		// openssl s_client -connect external.com:2376 < /dev/null | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > public.crt
		// sudo $JAVA_HOME/bin/keytool -import -alias 54.155.20.120 -keystore $JAVA_HOME/jre/lib/security/cacerts -file public.crt
		// password default "changeit"
		try {
			ComputeServiceContext context = ContextBuilder.newBuilder("docker")
					.credentials(cert, key)
					.endpoint(endpoint)
					.buildView(ComputeServiceContext.class)
			ComputeService client = context.getComputeService()
			jCloudServer.add(context)
			log.info("Immagini disponibili su Docker: "+client.listImages())
		} catch (Exception e){
			log.warn("Errore avvio JCloud Docker: "+e.printStackTrace())
		}
	}

	/** collega un hypervisor EC2 AWS **/
	void ec2JCloudCompute(String endpoint,String cert,String key) {
		try {
			ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
					.credentials(cert, key)
					.endpoint(endpoint)
					.buildView(ComputeServiceContext.class)
			ComputeService client = context.getComputeService()
			jCloudServer.add(context)
			log.info("Immagini disponibili su AWS EC2: "+client.listNodes())
		} catch (Exception e){
			log.warn("Errore avvio JCloud EC2: "+e.printStackTrace())
		}
	}
	
	/** collega uno storage S3 AWS **/
	void s3JCloudStore(String endpoint,String cert,String key) {
		try {
			ComputeServiceContext context = ContextBuilder.newBuilder("aws-ec2")
					.credentials(cert, key)
					.endpoint(endpoint)
					.buildView(ComputeServiceContext.class)
			ComputeService client = context.getComputeService()
			jCloudServer.add(context)
			log.info("Immagini disponibili su AWS EC2: "+client.listNodes())
		} catch (Exception e){
			log.warn("Errore avvio JCloud EC2: "+e.printStackTrace())
		}
	}

	/** carica un processo in Activiti **/
	String caricaProcesso(String processo) {
		InputStream zipFile = new FileInputStream(new File(grailsApplication.parentContext.getResource(processo).file.toString()))
		ZipInputStream inputStream = new ZipInputStream(zipFile)
		RepositoryService repositoryService = processEngine.getRepositoryService();
		repositoryService.createDeployment()
				//.addInputStream(grailsApplication.parentContext.getResource(processo).file.toString(),xmlFile)
				.addZipInputStream(inputStream)
				.deploy()
		return repositoryService.createProcessDefinitionQuery().count()
	}

	/** descrizione contesto */
	String toString() {
		String risultato = "CONTESTO ["+contesto.etichetta+"]\n"
		risultato += contesto.descrizione+"\n"
		risultato += contesto.toString()+"\n"
		risultato += "--------------------------------------\n"
		risultato += stato.toString()+"\n"
		risultato += "--------------------------------------\n"
		return risultato
	}

	/** stampa la memoria con Camel */
	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}

	/** esegue le procedura ogni 5 min. tramite Quartz */
	void battito() {
		freeMemory()
	}
}

