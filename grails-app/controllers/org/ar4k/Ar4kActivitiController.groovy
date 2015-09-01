package org.ar4k

import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService
import org.activiti.engine.repository.ProcessDefinition

/** Controller per le chiamate fatte dalle maschere di Activiti */
class Ar4kActivitiController {

	InterfacciaContestoService interfacciaContestoService
	RepositoryService repositoryService
	FormService formService
	RuntimeService runtimeService
	SpringSecurityService springSecurityService

	/** verifica connettività e utente*/
	def testConnessione() {
		try {
			render "Connessione con utente "+springSecurityService.currentUser.username+" id: "+springSecurityService.currentUser.id
		} catch (Exception e) {
			render "utente anonimo"
		}
	}

	/**
	 * Restituisce il form di avvio per il processo
	 * @return form inizio processo
	 */
	def avvioProcessoForm(String idProcesso) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(idProcesso).singleResult()
		String deploymentId = processDefinition.getDeploymentId()
		//String templateForm = formService.getStartFormData(idProcesso).getFormKey()
		//InputStream formStream = repositoryService.getResourceAsStream(deploymentId,templateForm)
		String formStream = formService.getRenderedStartForm(idProcesso)
		render formStream
	}

	/** ritorna l'elenco di parametri necessari nella maschera di avvio */
	def variabiliAvvioProcesso(String idProcesso) {
		def variabili = []
		formService.getStartFormData(idProcesso).getFormProperties().each{
			variabili.add([id:it.getId(),name:it.getName(),type:it.getType(),
				value:it.getValue(),readable:it.isReadable(),writable:it.isWritable(),
				required:it.isRequired()])
		}
		def incapsulato = [variabili:variabili]
		render incapsulato as JSON
	}

	/** Avvia il processo -chiamata POST-*/
	def avviaProcesso() {
		String idProcesso = request.JSON.idProcesso
		String dati = request.JSON.dati
		log.info "Richiesto avvio processo: "+idProcesso
		def processo = runtimeService.startProcessInstanceById(idProcesso,dati)
		render processo?'avviato...':'errore!'
	}

	def diagrammaStatoProcesso(String idProcesso) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(idProcesso).singleResult()
		String diagramResourceName = processDefinition.getDiagramResourceName()
		InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName)
		render file: imageStream, contentType: 'image/png'
	}

	/**
	 * @return immagine processo
	 */
	def diagrammaProcesso(String idProcesso) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(idProcesso).singleResult()
		String diagramResourceName = processDefinition.getDiagramResourceName()
		InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName)
		render file: imageStream, contentType: 'image/png'
	}
	
}
