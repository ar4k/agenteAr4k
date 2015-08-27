package org.ar4k

import grails.plugin.springsecurity.SpringSecurityService

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService
import org.activiti.engine.repository.ProcessDefinition

class Ar4kActivitiController {

	InterfacciaContestoService interfacciaContestoService
	RepositoryService repositoryService
	FormService formService
	RuntimeService runtimeService
	SpringSecurityService springSecurityService

	def testConnessione() {
		render "Connessione con utente "+springSecurityService.currentUser.username+" id: "+springSecurityService.currentUser.id
	}

	def nuovoProcessoTest(String idProcesso) {
		String ritorno = 'Processo NON trovato...'
		render ritorno
	}

	/**
	 *
	 * Da integrare in Angular
	 * @return form inizio processo
	 */
	def avvioProcessoForm(String idProcesso) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(idProcesso).singleResult()
		String deploymentId = processDefinition.getDeploymentId()
		String templateForm = formService.getStartFormData(idProcesso).getFormKey()
		InputStream formStream = repositoryService.getResourceAsStream(deploymentId,templateForm)
		render file: formStream, contentType: 'text/html'
	}

	def avviaProcesso() {
		String idProcesso = request.JSON.idProcesso
		log.info "Richiesto avvio processo: "+idProcesso
		Map<String, Object> variables = new HashMap<String, Object>()
		//variables.put("employeeName", "Kermit")
		runtimeService.startProcessInstanceById(idProcesso)
	}
	
	def diagrammaStatoProcesso(String idProcesso) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(idProcesso).singleResult()
		String diagramResourceName = processDefinition.getDiagramResourceName()
		InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName)
		render file: imageStream, contentType: 'image/png'
	}
	
	/**
	 *
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
