package org.ar4k

import com.ecwid.consul.v1.QueryParams
import grails.converters.JSON
import grails.plugin.springsecurity.SpringSecurityService
import grails.util.Holders
import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService
import org.activiti.engine.TaskService
import org.activiti.engine.repository.ProcessDefinition

/** Controller per le chiamate fatte dalle maschere di Activiti */
class Ar4kActivitiController {

	InterfacciaContestoService interfacciaContestoService
	RepositoryService repositoryService
	FormService formService
	TaskService taskService
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
		String formStream = formService.getRenderedStartForm(idProcesso)
		render formStream
	}
	
	def taskProcessoForm(String idTask) {
		String formStream = formService.getRenderedTaskForm(idTask)
		render formStream
	}

	/**
	 * Restituisce un percorso relativo al repository di un Meme
	 */
	def ritornaFile(String idMeme,String target) {
		String fileTarget = interfacciaContestoService.contesto.memi.find{it.idMeme == idMeme}.pathVaso + target
		render interfacciaContestoService.contesto.vasoMaster.leggiConCat(fileTarget)
	}

	/**
	 * Restituisce la maschera dello stato attuale di un meme
	 * @return maschera meme
	 */

	def mascheraMeme(String idMeme) {
		log.info "Richiesta maschera per meme "+idMeme
		render interfacciaContestoService.contesto.memi.find{it.idMeme == idMeme}.maschera()
	}
	
	def cancellaMeme(String idMeme) {
		log.info "Richiesta disinstallazione per meme "+idMeme
		render interfacciaContestoService.contesto.memi.find{it.idMeme == idMeme}.disinstallazione
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

	def apiAr4k() {
		def daCaricare  = [
			'admin/bower_components/jquery/dist/jquery.js',
			'admin/bower_components/angular/angular.js',
			'admin/bower_components/bootstrap/dist/js/bootstrap.js',
			'admin/bower_components/oclazyload/dist/ocLazyLoad.js',
			'admin/bower_components/restangular/dist/restangular.js',
			'admin/bower_components/lodash/dist/lodash.js'
		]
		String dipendenze = ''
		daCaricare.each{
			dipendenze+= Holders.getServletContext().getResource(it).getContent().text
		}

		render(template: "apiAr4k",contentType: "application/javascript", encoding: "UTF-8",model:[dipendenze:dipendenze])
	}


	def listaIstanze(String idProcesso){
		def variabili = []
		runtimeService.createProcessInstanceQuery().processDefinitionId(idProcesso).list().each{
			String taskid = taskService.createTaskQuery().processInstanceId(it.getId()).list().last().id
			variabili.add([
				id:it.getId(),
				businessKey:it.getBusinessKey(),
				activityId:it.getActivityId(),
				deploymentId:it.getDeploymentId(),
				name:it.getName(),
				processInstanceId:it.getProcessInstanceId(),
				sospeso:it.isSuspended(),
				taskid:taskid
			])
		}
		def incapsulato = [istanze:variabili]
		render incapsulato as JSON
	}

	def listaTask(){
		def risultato = []
		taskService.createTaskQuery().list().each{
			String icona = interfacciaContestoService.contesto.memi.find{ meme->
				ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(it.getProcessDefinitionId()).singleResult()
				String depId = processDefinition.getDeploymentId()
				meme.idMeme == repositoryService.createDeploymentQuery().deploymentId(depId).singleResult().getName()
			}.icona
			
			
			it.getProcessDefinitionId()
			risultato.add([id:it.id,
				icona:icona,
				assignee:it.getAssignee(),
				category:it.getCategory(),
				createTime:it.getCreateTime(),
				delegationState:it.getDelegationState(),
				description:it.getDescription(),
				dueDate:it.getDueDate(),
				executionId:it.getExecutionId(),
				formKey:it.getFormKey(),
				name:it.getName(),
				owner:it.getOwner(),
				parentTaskId:it.getParentTaskId(),
				priority:it.getPriority(),
				processDefinitionId:it.getProcessDefinitionId(),
				processInstanceId:it.getProcessInstanceId(),
				processVariables:it.getProcessVariables(),
				taskDefinitionKey:it.getTaskDefinitionKey(),
				tenantId:it.getTenantId()
			])
		}
		def incapsulato = [tasks:risultato,conto:risultato.size()]
		render incapsulato as JSON
	}
	/**
	 *
	 * @return Processi e oggetti collegati in JSON
	 */
	def listaProcessi() {
		def risultato = []
		interfacciaContestoService.stato.consulBind.getAgentServices().getValue().each{
			String stato = interfacciaContestoService.stato.consulBind.getHealthChecksForService(it.getValue().service,new QueryParams(interfacciaContestoService.contesto.datacenterConsul)).getValue()
			risultato.add(processo:it.getValue(),stato:stato)
		}
		def incapsulato = [processi:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Datacenters e oggetti collegati in JSON
	 */
	def listaDataCenters() {
		def risultato = []
		interfacciaContestoService.stato.consulBind.getCatalogDatacenters().getValue().each{
			List<String> nodi = interfacciaContestoService.stato.consulBind.getCatalogNodes(new QueryParams(it)).getValue()
			def nodiElaborati = []
			nodi.each{ nodo ->
				def stato = interfacciaContestoService.stato.consulBind.getHealthChecksForNode(nodo.node,new com.ecwid.consul.v1.QueryParams(it))
				nodiElaborati.add([nodo:nodo,stato:stato])
			}
			risultato.add(datacenter:it,nodi:nodiElaborati)
		}
		def incapsulato = [datacenters:risultato]
		render incapsulato as JSON
	}


	/**
	 *
	 * @return Store dati JCloud collegati in JSON
	 */
	def listaStore() {
		def risultato = []
		def risultatoBin = []
		interfacciaContestoService.stato.consulBind.getKVValues('').getValue().each{ risultato.add([key:it.key,createIndex:it.createIndex,modifyIndex:it.modifyIndex,value:new String(it.value.decodeBase64())]) }
		def incapsulato = [storedati:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * Salva un valore in consul
	 */
	def salvaValoreKV() {
		String chiave = request.JSON.chiave
		String valore = request.JSON.valore
		interfacciaContestoService.stato.consulBind.setKVValue(chiave, valore)
		sendMessage("activemq:topic:interfaccia.eventi",([tipo:'KVAGGIUNTO',chiave:chiave,valore:valore] as JSON).toString())
		render "ok"
	}
}
