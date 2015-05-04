/**
 * Kettle Service
 *
 * <p>Gestisce le lavorazioni Kettle dei Memi</p>
 *
 * <p style="text-justify">
 * Da valutare l'integrazione con la factory di controller AngularJS</br>
 * </p>
 * 
 * valutare l'inserimento di activity qui.
 * http://alfrescoblog.com/2014/06/07/angular-js-activiti-webapp-part-i/
 * http://alfrescoblog.com/2014/06/17/angular-js-activiti-webapp-part-ii/
 * http://alfrescoblog.com/2014/09/04/angular-js-activiti-webapp-part-iii-final/
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Meme
 * @see org.ar4k.Interfaccia
 */

package org.ar4k

import org.pentaho.di.core.KettleEnvironment
import org.pentaho.di.core.database.DatabaseMeta
import org.pentaho.di.core.logging.LogChannel
import org.pentaho.di.core.logging.LogChannelInterface
import org.pentaho.di.core.plugins.PluginRegistry
import org.pentaho.di.core.plugins.RepositoryPluginType
import org.pentaho.di.job.Job
import org.pentaho.di.job.JobMeta
import org.pentaho.di.repository.RepositoriesMeta
import org.pentaho.di.repository.Repository
import org.pentaho.di.repository.RepositoryDirectoryInterface
import org.pentaho.di.repository.RepositoryMeta
import org.pentaho.di.repository.filerep.KettleFileRepositoryMeta
import org.pentaho.di.repository.RepositoryElementMetaInterface
import org.pentaho.di.core.exception.KettleException


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Document
import org.w3c.dom.Node
import org.w3c.dom.Element

//import org.activiti.engine.ProcessEngine

import org.codehaus.groovy.grails.commons.GrailsApplication

class KettleService {
	
	//ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine()
	
	def GrailsApplication grailsApplication
	//def servletContext

	Repository repository = null
	RepositoryDirectoryInterface directory = null
	def inLavorazione = []
	//static expose = ['EndpointType.JAX_WS'];
	//@WebMethod( operationName="schedulerRun" )
	//@WebResult( name="risultato" )
	//def String schedulerRun(@WebParam(name = "valore") String valore) {
	//static expose = ['jmx']
	public static final String STRING_LOG = "LogService";
	// Inizializzazione Kettle
	def initKettle() {
		//LogChannelInterface log = new LogChannel(STRING_LOG);
		KettleEnvironment.init()
		repoInit()
	}

	def repoInit() {
		RepositoriesMeta repositoriesMeta = new RepositoriesMeta()
		if  ( grailsApplication.config.kettle.repoConfFile != '' ) {
			InputStream xmlFile
			xmlFile = new FileInputStream(new File(grailsApplication.parentContext.getResource("/kettle").file.toString()+'/'+grailsApplication.config.kettle.repoConfFile))
			repositoriesMeta.readDataFromInputStream(xmlFile)
		} else {
			repositoriesMeta.readData()
		}
		RepositoryMeta repositoryMeta = null
		if (grailsApplication.config.kettle.repoFS == 'si') {
			repositoryMeta = (KettleFileRepositoryMeta) repositoriesMeta.findRepository(grailsApplication.config.kettle.repository)
			repositoryMeta.setBaseDirectory(grailsApplication.parentContext.getResource("/kettle/repository").file.toString())
		} else {
			repositoryMeta = (RepositoryMeta) repositoriesMeta.findRepository(grailsApplication.config.kettle.repository)
		}
		PluginRegistry registry = PluginRegistry.getInstance()
		repository = registry.loadClass(RepositoryPluginType.class,
				repositoryMeta, Repository.class)
		repository.init(repositoryMeta)
		repository.connect(grailsApplication.config.kettle.username, grailsApplication.config.kettle.password)
		directory = repository.loadRepositoryDirectoryTree()

		directory = directory.findDirectory(grailsApplication.config.kettle.directory)

		directory = directory.findDirectory(grailsApplication.config.kettle.directory)

	}

	def verificaRipristina() {
		try {
			repository.getJobAndTransformationObjects(directory.getObjectId(), false)
		} catch (Exception e){
			println "Repository sconnesso, provo la riconnesione"
			repoInit()
			println "Repository connesso"
		}
	}

	// Metodo per ottenere la lista dei job eseguibili
	def List<RepositoryElementMetaInterface> listJobs(){
		verificaRipristina()
		List<RepositoryElementMetaInterface> lavorazioni = repository.getJobAndTransformationObjects(directory.getObjectId(), false)
		def risultati = []
		for ( RepositoryElementMetaInterface lavoro in lavorazioni ) {
			if ( lavoro.getObjectType().getTypeDescription() == 'job' ) {
				//println "Trovato: "+lavoro.getName()+" , tipo: "+lavoro.getObjectType().getTypeDescription()
				risultati.add(lavoro)
			}
		}
		/*
		 for ( RepositoryElementMetaInterface lavoro in lavorazioni ) {
		 if ( lavoro.getObjectType().getTypeDescription() == 'job' ) {
		 JobMeta jobMeta = new JobMeta();
		 jobMeta = repository.loadJob(lavoro.getName(), directory, null, null);
		 Job job = new Job(repository, jobMeta);
		 risultati.add(lavoro)
		 }
		 }
		 */
		return risultati;
	}


	// Lista job in memoria del service per nome Job
	def List<Job> listJobsActive(String jobName){
		verificaRipristina()
		def risultati = []
		for ( Job lavoro in inLavorazione ) {
			if ( lavoro.getJobname() == jobName ) {
				risultati.add(lavoro)
				// Più avanti verificare se distruggere l'oggetto lavoro
			}
		}
		return risultati
	}

	// Mette in pausa un Job
	def Job pausa(String jobID){
		verificaRipristina()
		Job risultato
		for ( Job lavoro in inLavorazione ) {
			//println lavoro.getId().toString()+" == "+jobID
			if ( lavoro.getId().toString() == jobID ) {
				risultato = lavoro
				lavoro.stopAll()
				// Più avanti verificare se distruggere l'oggetto lavoro
			}
		}
		return risultato

	}


	// ripristina un Job
	def Job ripristina(String jobID){
		verificaRipristina()
		Job risultato
		for ( Job lavoro in inLavorazione ) {
			//println lavoro.getId().toString()+" == "+jobID
			if ( lavoro.getId().toString() == jobID ) {
				risultato = lavoro
				lavoro.run()
				// Più avanti verificare se distruggere l'oggetto lavoro
			}
		}
		return risultato
	}

	// elimina un job
	def Job elimina(String jobID){
		verificaRipristina()
		Job risultato
		for ( Job lavoro in inLavorazione ) {
			//println lavoro.getId().toString()+" == "+jobID
			if ( lavoro.getId().toString() == jobID ) {
				risultato = lavoro
				lavoro.stopAll()
				// Più avanti verificare se distruggere l'oggetto lavoro
			}
			def temporanei = []
			for ( job in inLavorazione ) {
				if ( job.getId().toString() != jobID) {
					temporanei.add(job)
				}
			}
			inLavorazione = temporanei
		}
		return risultato
	}

	// Metodo per eseguire i jobs. In Config.groovy ci sono i parametri da configurare
	// Da verificare la configurazione esterna
	def String jobRun(String jobName) {
		verificaRipristina()
		JobMeta jobMeta = new JobMeta();
		jobMeta = repository.loadJob(jobName, directory, null, null);
		//println "-----------------------------------------------------------------------"
		//println "JobMeta Descrizione: " + jobMeta.getDescription();
		//println "JobMeta Versione: " + jobMeta.getJobversion();
		//println "JobMeta Data di modifica: " + jobMeta.getModifiedDate();
		//println "JobMeta Id: " + jobMeta.getObjectId().getId();
		Job job = new Job(repository, jobMeta);
		println "Job Nome: " + job.getJobname();
		inLavorazione.add(job)
		job.start();
		job.waitUntilFinished();
		//println "-----------------------------------------------------------------------"
		if (job.getErrors() != 0) {
			return job.getErrors();
		} else {
			return job.getResult();
		}
	}

	def Job getJob(String jobName) {
		verificaRipristina()
		JobMeta jobMeta = new JobMeta();
		jobMeta = repository.loadJob(jobName, directory, null, null);
		//println "-----------------------------------------------------------------------"
		//println "JobMeta Descrizione: " + jobMeta.getDescription();
		//println "JobMeta Versione: " + jobMeta.getJobversion();
		//println "JobMeta Data di modifica: " + jobMeta.getModifiedDate();
		//println "JobMeta Id: " + jobMeta.getObjectId().getId();
		Job job = new Job(repository, jobMeta);
		println "Job Nome: " + job.getJobname();
		return job
	}


	def Job jobRunFull(String jobName, def parametri) {
		verificaRipristina()
		JobMeta jobMeta = new JobMeta();
		jobMeta = repository.loadJob(jobName, directory, null, null);
		for ( parametro in jobMeta.listParameters().toList() ) {
			jobMeta.setParameterValue(parametro, parametri.getAt(parametro))
			println parametro + " = " + parametri.getAt(parametro)
		}
		//println "-----------------------------------------------------------------------"
		//println "JobMeta Descrizione: " + jobMeta.getDescription();
		//println "JobMeta Versione: " + jobMeta.getJobversion();
		//println "JobMeta Data di modifica: " + jobMeta.getModifiedDate();
		//println "JobMeta Id: " + jobMeta.getObjectId().getId();
		Job job = new Job(repository, jobMeta);
		println "Job Nome: " + job.getJobname();
		inLavorazione.add(job)
		job.start();
		//println "-----------------------------------------------------------------------"
		return job

	}

}

