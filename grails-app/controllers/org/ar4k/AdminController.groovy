package org.ar4k
import javax.swing.text.html.HTML

import grails.converters.JSON
import groovy.json.*

class AdminController {
	AccoppiatoreService accoppiatoreService
	
	static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

	def index() {
		//[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
	}

	def headerNotification() {
		render(template: "headerNotification")
	}
	
	def listaoggetti(){
		def ritorno = []
		accoppiatoreService.listaOggetti().each{
			//Strutturare qui la lista
			ritorno.add([etichetta:it.etichetta,tipo:it.tipo])
			}
		render (ritorno as JSON)
	}
	
	def salvassh (){
		Oggetto stato 
		def jsonObject = request.JSON
		stato = accoppiatoreService.nuovoSSH(jsonObject.etichetta,jsonObject.descrizione,jsonObject.utente,jsonObject.target,jsonObject.porta.toInteger(),jsonObject.password)
		render (stato as JSON)
	}

	def terminale() {
		render(template: "terminale", model:[mappa: 'master',comandoAvvio:'sleep 3 ; clear'] )
	}
	
	def eseguiProcesso(String host,String comando) {
		render accoppiatoreService.esegui(host,comando)
	}
	
	def parseRemote(String host,String target,String portaTarget,String query,String componente) {
		render accoppiatoreService.remoteWeb(host,target,portaTarget,query,componente)
	}
	
	def oggetto() {
		render(template: "oggetto")
	}
	
	def oggettoCtrl() {
		render(template: "oggettoCtrl")
	}

	def sidebar() {
		render(template: "sidebar")
	}
	
	def rossonet() {
		render(template: "rossonet")
	}
	
	def quartz() {
		render(template: "quartz")
	}
	
	def processi() {
		render(template: "processi")
	}
	
	def kettle() {
		render(template: "kettle")
	}
	
	def dashrossonet() {
		render(template: "dashrossonet")
	}
}

