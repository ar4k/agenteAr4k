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
 */

package org.ar4k
import javax.swing.text.html.HTML
import grails.converters.JSON
import groovy.json.*

class AdminController {
	InterfacciaContestoService interfacciaContestoService
	
	def salvaConfigurazione(String archivio) {
		render interfacciaContestoService.salvaConfigurazione(archivio)
	}
	
	def caricaConfigurazione(String archivio) {
		render interfacciaContestoService.caricaConfigurazione(archivio)
	}

	def index() {
		//[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
	}

	def headerNotification() {
		render(template: "headerNotification")
	}
	
	def sidebar() {
		render(template: "sidebar")
	}
	
	def listaoggetti(){
		def ritorno = []
		interfacciaContestoService.listaOggetti().each{
			//Strutturare qui la lista
			ritorno.add([etichetta:it.etichetta,tipo:it.tipo])
			}
		render (ritorno as JSON)
	}
	
	def creaSshHost (){
		Oggetto stato 
		def jsonObject = request.JSON
		stato = interfacciaContestoService.nuovoSSH(jsonObject.etichetta,jsonObject.descrizione,jsonObject.utente,jsonObject.target,jsonObject.porta.toInteger(),jsonObject.password)
		render (stato as JSON)
	}

	def terminale(String host) {
		render(template: "terminale", model:[mappa: host?:'master',comandoAvvio:'sleep 3 ; clear'] )
	}
	
	def eseguiProcesso(String host,String comando) {
		render interfacciaContestoService.esegui(host,comando)
	}
	
	def parseRemote(String host,String target,String portaTarget,String query,String componente) {
		render interfacciaContestoService.remoteWeb(host,target,portaTarget,query,componente)
	}
	
	def oggetti() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/oggetti')
	}
	
	def oggettiCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/oggettiCtrl')
	}
	
	def rossonet() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/rossonet')
	}
	
	def rossonetCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/rossonetCtrl')
	}
	
	def quartz() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/quartz')
	}
	
	def quartzCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/quartzCtrl')
	}
	
	def processi() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/processi')
	}
	
	def processiCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/processiCtrl')
	}
	
	def kettle() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/kettle')
	}
	
	def kettleCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/kettleCtrl')
	}
	
	def dashrossonet() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/dashboard')
	}
	
	def dashrossonetCtrl() {
		render interfacciaContestoService.esegui('master','bash $AR4K_ANGULAR/dashboardCtrl')
	}
}

