/**
* @deprecated vecchia implementazione
*/

package org.rossonet.kettle

import org.ar4k.KettleService
import org.pentaho.di.core.logging.Log4jBufferAppender
import org.pentaho.di.job.Job
import org.pentaho.di.core.logging.CentralLogStore

class KettleTagLib {
	KettleService kettleService

	def standardKettle = { attrs, body ->
		//kettleService.initKettle()
		def immagineEsegui = g.img(dir:"images",file:"kettleEsegui.png")
		def oggetto = kettleService.getJob(attrs.jobName)
		def divRitorno = 'ritorno_'+(oggetto.getJobMeta().getObjectId().id =~ '[/,., ]' ).replaceAll("_")
		def divMaster = 'master_'+(oggetto.getJobMeta().getObjectId().id =~ '[/,., ]' ).replaceAll("_")
		def divNew = 'new_'+(oggetto.getJobMeta().getObjectId().id =~ '[/,., ]' ).replaceAll("_")
		def divList = 'lista_'+(oggetto.getJobMeta().getObjectId().id =~ '[/,., ]' ).replaceAll("_")
		def pulsante = g.submitToRemote(url:[controller:'ETL',action:'esegui'],update:divRitorno,value:"Esegui",onLoading:'document.getElementById(\''+divRitorno+'\').innerHTML=\'Attendere...\'')
		def aggiorna = g.submitToRemote(url:[controller:'ETL',action:'listRun',params:[jobName:oggetto.getJobMeta().getName()]],update:divList,value:"Aggiorna",onLoading:'document.getElementById(\''+divList+'\').innerHTML=\'Attendere...\'')
		out << '<div id="'+divMaster+'" >'
		out << '<table style="border-color: #DFDFDF; border-style:none;">'
		out << '<tr>'
		if ( attrs.table == "yes" ) {
			out << '<td>'
			out << '<table border="1" style="border-color: #DFDFDF; border-style:solid;">'
			out << '<tr>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">ID:</td>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">'
			out << '<b>'+oggetto.getJobMeta().getObjectId().id+'</b>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">Nome:</td>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">'
			out << '<b>'+oggetto.getJobMeta().getName()+'</b>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">Utente:</td>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">'
			out << '<b>'+oggetto.getJobMeta().getModifiedUser()+'</b>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">Ultima modifica:</td>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">'
			out << '<b>'+oggetto.getJobMeta().getModifiedDate().toString()+'</b>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">Parametri:</td>'
			out << '<td style="border-color: #DFDFDF; border-style:solid;">'
			out << oggetto.getJobMeta().listParameters().toList()
			out << '</td>'
			out << '</tr>'
			out << '</table>'
			out << '</td>'
			out << '<td width="70%">'
		} else {
			out << '<td>'
		}

		out << "<li>"
		out << oggetto.getJobMeta().getDescription()
		out << '</li>'
		out << '<input type="button" value="Nuova esecuzione job '+oggetto.getJobMeta().getName()+'" onClick="document.getElementById(\''+divNew+'\').style.display=\'block\';"/>'
		out << '&nbsp;'+aggiorna
		out << '<div id="'+divNew+'" style="display:none;">'
		out << '<form action="'+resource(dir:'ETL', file:'esegui')+'" method="post" name="kettleAction" id="kettleAction">'
		out << "<table>"
		for ( String parametro in oggetto.getJobMeta().listParameters().toList())  {
			out << "<tr>"
			out << '<td colspan="2">'
			out << '<font size="2"><li>'+oggetto.getJobMeta().getParameterDescription(parametro)+'</li></font>'
			out << "</td>"
			out << "</tr>"
			out << "<tr>"
			out << '<td><div align="right">'
			out << parametro+' :'
			out << "</div></td>"
			out << "<td>"
			out << '<input type="text" name="'+parametro+'" value="'+oggetto.getJobMeta().getParameterDefault(parametro)+'" id="'+parametro+'">'
			out << "</td>"
			out << "</tr>"
		}
		out << '<tr>'
		out << '<td colspan="2" style="text-align:right;">'
		out << '<input type="button" value="Nascondi" onClick="document.getElementById(\''+divNew+'\').style.display=\'none\';"/>'
		out << '<input type="hidden" name="jobName" value="'+oggetto.getJobMeta().getName()+'" />'
		out << '&nbsp;'+pulsante
		out << '<div name="'+divRitorno+'" id="'+divRitorno+'"></div>'
		out << '</td>'
		out << '</tr>'
		out << "</table>"
		out << '</form>'
		out << '</div>'
		out << '<div id="'+divList+'" >'

		out << '</div>'
		out << '</td>'
		out << '</tr>'
		out << '</table>'
		out << '</div>'
		//out << g.formRemote(name:"esecuzioneJob_"+attrs.idJob,on404:"alert('not found!')",update:"contenuto_"+attrs.idJob,url:[controller:'ETL',action:'esegui',params:[idJob:attrs.idJob]])
		//out << '<div id="contenuto_'+attrs.idJob+'">...</div>'
	}

	def listRunKettle = { attrs, body ->
		def oggetto = kettleService.getJob(attrs.jobName)

		out << '<table style="border-style:none;">'
		out << '<tr>'
		out << '<th>ID</th><th>Status</th><th>Data</th><th>...</th>'
		out << '</tr>'
		for ( Job lavAttivo in kettleService.listJobsActive(oggetto.getJobMeta().getName())) {
			def divJob = 'jobManage_'+lavAttivo.getId()
			def divRitorno = 'RitJob_'+lavAttivo.getId()
			def pausa = g.submitToRemote(url:[controller:'ETL',action:'pausa',params:[jobID:lavAttivo.getId()]],update:divRitorno,value:"Stop",onLoading:'document.getElementById(\''+divRitorno+'\').innerHTML=\'Attendere...\'')
			def ripristina = g.submitToRemote(url:[controller:'ETL',action:'ripristina',params:[jobID:lavAttivo.getId()]],update:divRitorno,value:"Run",onLoading:'document.getElementById(\''+divRitorno+'\').innerHTML=\'Attendere...\'')
			def elimina = g.submitToRemote(url:[controller:'ETL',action:'elimina',params:[jobID:lavAttivo.getId()]],update:divRitorno,value:"Elimina",onLoading:'document.getElementById(\''+divRitorno+'\').innerHTML=\'Attendere...\'')
			out << '<tr>'
			out << '<td style="border-style:none;">'
			out << ''+lavAttivo.getId()
			out << '</td>'
			out << '<td style="border-style:none;">'
			out << ''+lavAttivo.getStatus()
			out << '</td>'
			out << '<td style="border-style:none;">'
			out << ''+lavAttivo.getCurrentDate()
			out << '</td>'
			out << '<td style="border-style:none;text-align:right;">'
			out << '<input type="button" value="Dettagli" onClick="document.getElementById(\''+divJob+'\').style.display=\'block\';"/>'
			if ( lavAttivo.isActive() ) {
				out << '&nbsp;'+pausa
				//out << annulla
			}
			if ( lavAttivo.isStopped()) {
				out << '&nbsp;'+ripristina
			}
			if ( lavAttivo.isFinished()) {
				out << '&nbsp;'+elimina
			}

			out << '<div id="'+divRitorno+'"></div>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td colspan="4">'
			out << '<div id="'+divJob+'" style="display:none;">'
			//out << lavAttivo.toString() + ' ' +lavAttivo.getActiveJobEntryJobs() + ' ' +lavAttivo.getActiveJobEntryTransformations() + ' ' +lavAttivo.getBatchId() + ' ' +lavAttivo.getContainerObjectId() + ' ' +lavAttivo.getCurrentDate() + ' ' +lavAttivo.getDepDate() + ' ' +lavAttivo.getEndDate() + ' ' +lavAttivo.getErrors() + ' ' +lavAttivo.getFilename() + ' ' +lavAttivo.getJobEntryListeners() + ' ' +lavAttivo.getJobEntryResults() + ' ' +lavAttivo.getJobListeners() + ' ' +lavAttivo.getJobMeta() + ' ' +lavAttivo.getJobTracker() + ' ' +lavAttivo.getJobname() + ' ' +lavAttivo.getLogChannel() + ' ' +lavAttivo.getLogChannelId() + ' ' +lavAttivo.getLogDate() + ' ' +lavAttivo.getLogLevel() + ' ' +lavAttivo.getLoggingHierarchy() + ' ' +lavAttivo.getObjectCopy() + ' ' +lavAttivo.getObjectId() + ' ' +lavAttivo.getObjectName() + ' ' +lavAttivo.getObjectRevision() + ' ' +lavAttivo.getObjectType() + ' ' +lavAttivo.getParameterDefault() + ' ' +lavAttivo.getParameterDescription() + ' ' +lavAttivo.getParameterValue() + ' ' +lavAttivo.getParent() + ' ' +lavAttivo.getParentJob() + ' ' +lavAttivo.getParentLoggingObject() + ' ' +lavAttivo.getParentVariableSpace() + ' ' +lavAttivo.getPassedBatchId() + ' ' +lavAttivo.getRegistrationDate() + ' ' +lavAttivo.getRep() + ' ' +lavAttivo.getRepositoryDirectory() + ' ' +lavAttivo.getResult() + ' ' +lavAttivo.getSocketRepository() + ' ' +lavAttivo.getSourceRows() + ' ' +lavAttivo.getStartDate() + ' ' +lavAttivo.getStartJobEntryCopy() + ' ' +lavAttivo.getStatus() + ' ' +lavAttivo.getThread() + ' '+lavAttivo.isActive() + ' ' +lavAttivo.isFinished() + ' ' +lavAttivo.isInitialized() + ' ' +lavAttivo.isInteractive() + ' ' +lavAttivo.isStopped() + ' ' +lavAttivo.listParameters() + ' ' +lavAttivo.listVariables() + ' ' +lavAttivo.toString()
			out << '<table>'
			out << '<tr>'
			out << '<td>'
			out << lavAttivo.getResult()
			out << '</td>'
			out << '<td>'
			out << 'Status : <b>'+lavAttivo.getStatus()+'</b>'
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td>'
			out << 'End date : '+lavAttivo.getEndDate()
			out << '</td>'
			out << '<td>'
			out << 'Current date : '+lavAttivo.getCurrentDate()
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td colspan="2">'
			for ( String parametro in lavAttivo.listParameters().toList())  {
				out << '<li>'
				out << parametro + " = '" + lavAttivo.getParameterValue(parametro) +"'"
				out << '</li>'
			}
			out << '</td>'
			out << '</tr>'
			out << '<tr>'
			out << '<td colspan="2">'
			out << '<textarea style="width:96%;">'
			out << CentralLogStore.getAppender().getBuffer(lavAttivo.getLogChannelId(), false).toString()
			out << '</textarea>'
			out << '</td>'
			out << '</tr>'
			out << '</table>'
			out << '<input type="button" value="Nascondi" onClick="document.getElementById(\''+divJob+'\').style.display=\'none\';"/>'
			out << '</div>'
			out << '</td>'
			out << '</tr>'
		}
		out << '</table>'

	}

}
