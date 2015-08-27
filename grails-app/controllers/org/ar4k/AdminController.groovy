/**
 * Controller interfaccia web principale
 * 
 * <p>
 * AdminController gestisce tutte le richieste dell'interfaccia utente tranne autenticazione e bootstrap
 * </p>
 * 
 * <p style="text-justify">
 * In generale questo è il controller utilizzato dall'amministratore
 * dell'applicativo, espone tutte le funzionalità, per eventuali affinamenti
 * della sicurezza, utilizzare i dati di Contesto
 * </p>
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Contesto
 * @see org.ar4k.InterfacciaContestoService
 */

package org.ar4k
import javax.swing.text.html.HTML

import org.activiti.engine.FormService
import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity
import org.activiti.engine.repository.ProcessDefinition

import com.ecwid.consul.v1.QueryParams

import grails.converters.JSON
import groovy.json.*


class AdminController {

	InterfacciaContestoService interfacciaContestoService
	RepositoryService repositoryService
	FormService formService
	RuntimeService runtimeService

	/**
	 * 
	 * @return Interfaccia principale
	 */
	def index() {
		//[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
	}

	/**
	 * In generale le funzionalità legate allo scope session e il menù dell'utente.
	 * @return Pannello in alto a destra
	 */
	def headerNotification() {
		render(template: "headerNotification",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 * 
	 * @return Testata
	 */
	def headerHtml() {
		render(template: "headerHtml",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 * 
	 * @return Definizione applicazione AngularJS 
	 */
	def appjs() {
		render(template: "appjs",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 * 
	 * @return Barra laterale sinistra
	 */
	def sidebar() {
		render(template: "sidebar",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 * 
	 * @return Componente html con terminale legato ai websocks
	 */
	def terminale() {
		render(template: "terminale", model:[testoIntro:'...visualizzatore log vaso master...',mappa: 'master',descrizione: 'descrizione di prova',comandoAvvio:'sleep 3 ; clear ; tail -F ~/.ar4k/terminale.log',grafica: interfacciaContestoService.interfaccia.grafica] )
	}

	def oggetti() {
		render(template: "oggetti",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def oggettiCtrl() {
		render(template: "oggettiCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def rossonet() {
		render(template: "rossonet",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def rossonetCtrl() {
		render(template: "rossonetCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def apiAr4k() {
		render(template: "apiAr4k",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def apiAr4kCtrl() {
		render(template: "apiAr4kCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def memoria() {
		render(template: "memoria",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def memoriaCtrl() {
		render(template: "memoriaCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def utenti() {
		render(template: "utenti",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def utentiCtrl() {
		render(template: "utentiCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}
	def processi() {
		render(template: "processi",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def processiCtrl() {
		render(template: "processiCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def memi() {
		render(template: "memi",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def memiCtrl() {
		render(template: "memiCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def reti() {
		render(template: "reti",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def retiCtrl() {
		render(template: "retiCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def ricettari() {
		render(template: "ricettari",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def ricettariCtrl() {
		render(template: "ricettariCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}
	def dashrossonet() {
		render(template: "dashRossonet",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def dashrossonetCtrl() {
		render(template: "dashRossonetCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 * 
	 * @return Vasi e oggetti collegati in JSON
	 */
	def listaVasi() {
		def risultato = []
		interfacciaContestoService.contesto.vasi.each{ vaso ->
			String macchina = vaso.macchina
			def stato = interfacciaContestoService.stato.consulBind.getHealthChecksForNode(macchina,new QueryParams('ar4kprivate')).getValue()
			risultato.add([vaso:vaso,stato:stato])
		}
		def incapsulato = [vasi:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Processi e oggetti collegati in JSON
	 */
	def listaProcessi() {
		def risultato = []
		interfacciaContestoService.stato.consulBind.getAgentServices().getValue().each{
			String stato = interfacciaContestoService.stato.consulBind.getHealthChecksForService(it.getValue().service,new QueryParams('ar4kprivate')).getValue()
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

	/** @return dettagli per un nodo Consul */
	def nodo(String identificativo,String datacenter) {
		def risultato = interfacciaContestoService.stato.consulBind.getCatalogNode(identificativo,new com.ecwid.consul.v1.QueryParams(datacenter))
		def stato = interfacciaContestoService.stato.consulBind.getHealthChecksForNode(identificativo,new com.ecwid.consul.v1.QueryParams(datacenter))
		def incapsulato = [nodo:risultato,stato:stato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Ricettari collegati in JSON
	 */
	def listaRicettari() {
		def risultato = []
		interfacciaContestoService.contesto.ricettari.each{ risultato.add(it) }
		def incapsulato = [ricettari:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Memi collegati in JSON
	 */
	def listaMemi() {
		def risultato = []
		interfacciaContestoService.contesto.memi.each{ meme ->
			String idMeme = meme.idMeme
			String idRep = repositoryService.createDeploymentQuery().deploymentName(idMeme).singleResult().getId()
			//String datiRep = repositoryService.createDeploymentQuery().deploymentName(idMeme).singleResult()
			List<String> processiID = repositoryService.createProcessDefinitionQuery().deploymentId(idRep).list()*.getId()
			def processi = []
			processiID.each{
				processi.add([processo:it,istanze:runtimeService.createExecutionQuery().processDefinitionId(it).count()])
			}
			def calcolati = [tooltip:meme.tooltip(),maschera:meme.maschera(),dashboard:meme.dashboard(),box:meme.box(),iconaStato:meme.iconaStato()]
			risultato.add([meme:meme,processi:processi,calcolati:calcolati])
		}
		def incapsulato = [memi:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Store dati JCloud collegati in JSON
	 */
	def listaStore() {
		def risultato = []
		def risultatoBin = []
		interfacciaContestoService.stato.consulBind.getKVValues('').getValue().each{ risultato.add(it) }
		def incapsulato = [storedati:risultato]
		render incapsulato as JSON
	}

	/**
	 *
	 * @return Chiamata JSON per creare un nuovo vaso da parametri
	 */
	def aggiungiVaso() {
		def vaso = request.JSON.vaso
		log.info("Richiesta aggiunta vaso: "+vaso)
		Vaso vasoAggiunto= new Vaso(
				etichetta:vaso.etichetta,
				descrizione:vaso.descrizione,
				macchina:vaso.hostssh,
				porta:vaso.porta.toInteger(),
				utente:vaso.utente,
				key:vaso.key
				)
		if (
		interfacciaContestoService.contesto.vasi.add(vasoAggiunto)
		) {
			render "ok"
		} else {
			render "errore"
		}
	}

	/**
	 * 
	 * @return Chiamata JSON per aggiungere un ricettario al contesto
	 */
	def aggiungiRicettario() {
		def ricettario = request.JSON.ricettario
		log.info("Richiesta aggiunta ricettario: "+ricettario)
		RepositoryGit repositoryGit = new RepositoryGit(indirizzo:ricettario.repo,nomeCartella:ricettario.cartella)
		if (
		interfacciaContestoService.contesto.ricettari.add(
		new Ricettario(etichetta:ricettario.etichetta,descrizione:ricettario.descrizione,repositoryGit:repositoryGit)
		)
		) {
			render "ok"
		} else {
			render "errore"
		}
	}

	/**
	 *
	 * @return Chiamata JSON per aggiornare un ricettario. Aggiorna i repository git e ricarica i semi
	 */
	def aggiornaRicettario() {
		String idRicettario = request.JSON.idricettario
		interfacciaContestoService.contesto.ricettari.each{
			if (it.idRicettario == idRicettario) {
				interfacciaContestoService.contesto.vasi*.avviaRicettario(it)
				interfacciaContestoService.contesto.vasoMaster.caricaSemi(it)
				it.aggiornato = new Date()
			}
		}
		render "ok"
	}

	/**
	 *
	 * @return Chiamata JSON per creare un meme da un seme.
	 */
	def creaMeme() {
		String idSeme = request.JSON.seme
		log.info("Richiesta installazione seme "+idSeme)
		interfacciaContestoService.contesto.ricettari.each{ ricettario ->
			ricettario.semi.each{
				if (it.meme.idMeme == idSeme) {
					log.info("il seme "+it.meme+" corrisponde")
					Meme nuovoMeme = new Meme()
					nuovoMeme = it.meme.clone()
					// Rigenera tutti gli id casuali per non rischiare riferimenti errati nelle fasi di esecuzione
					nuovoMeme.idMeme = UUID.randomUUID()
					nuovoMeme.metodi.each{it.idMetodo=UUID.randomUUID()}
					nuovoMeme.caricaProcessiActiviti(interfacciaContestoService.contesto.vasoMaster,interfacciaContestoService.processEngine)
					interfacciaContestoService.contesto.memi.add(nuovoMeme)
				}
			}
		}
		render "ok"
	}

	/**
	 *
	 * @return Chiamata JSON per ottenere la lista di semi in un ricettario.
	 */
	def listaSemi() {
		String idRicettario = request.JSON.idricettario
		def incapsulato
		List<Seme> risultato = []
		interfacciaContestoService.contesto.ricettari.each{
			if (it.idRicettario == idRicettario) {
				it.semi.each{ seme -> risultato.add(seme) }
				incapsulato = [semi:risultato]
			}
		}
		if ( risultato.size() > 0 ) {
			render incapsulato as JSON
		} else {
			render 'none'
		}
	}

	/**
	 * 
	 * @return Foglio di stile AngularJS. Tramite la configurazione grafica dell'interfaccia è possibile variare lo stile
	 */
	def sbadmin2() {
		render(template: "sbadmin2",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 *
	 * @return Foglio di stile AngularJS. Tramite la configurazione grafica dell'interfaccia è possibile variare lo stile
	 */
	def main() {
		render(template: "main",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	/**
	 *
	 * @return Foglio di stile AngularJS. Tramite la configurazione grafica dell'interfaccia è possibile variare lo stile
	 */
	def timeline() {
		render(template: "timeline",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

}

