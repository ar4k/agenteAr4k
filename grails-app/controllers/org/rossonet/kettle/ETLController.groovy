/**
 * @deprecated vecchia implementazione
 */

package org.rossonet.kettle

import org.rossonet.kettle.KettleService
import grails.converters.JSON
import org.pentaho.di.job.Job;

class ETLController {

	def KettleService kettleService
	def grailsApplication

	def index() {
		//kettleService.initKettle()
		[jobsList: kettleService.listJobs() ]
	}

	def esegui(String jobName) {
		Job job = kettleService.jobRunFull(jobName,params)
		if ( job.getState() ) {
			render "ID: "+job.getId()+" Stato: "+job.getState()
		}
		else {
			render "Errore..."
		}
	}

	def attivi(String jobName) {
		[jobAttivi: kettleService.listJobsActive(jobName)]
	}

	def listRun(String jobName) {
		def g = grailsApplication.mainContext.getBean('org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib')
		render g.listRunKettle(jobName:jobName)
	}

	def pausa(String jobID) {
		Job job = kettleService.pausa(jobID)
		if ( job.getState() ) {
			render "ID: "+job.getId()+" Stato: "+job.getState()
		}
		else {
			render "Errore..."
		}
	}

	def ripristina(String jobID) {
		Job job = kettleService.ripristina(jobID)
		if ( job.getState() ) {
			render "ID: "+job.getId()+" Stato: "+job.getState()
		}
		else {
			render "Errore..."
		}
	}
	
	def elimina(String jobID) {
		Job job = kettleService.elimina(jobID)
		if ( job.getState() ) {
			render "ID: "+job.getId()+" Stato: "+job.getState()
		}
		else {
			render "Errore..."
		}
	}
}
