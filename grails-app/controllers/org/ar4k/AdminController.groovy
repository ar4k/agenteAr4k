package org.ar4k
import javax.swing.text.html.HTML

import grails.converters.JSON
import groovy.json.*

class AdminController {
	AccoppiatoreService accoppiatoreService

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
	
	def oggetti() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/oggetti')
	}
	
	def oggettoCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/oggettiCtrl')
	}
	
	def rossonet() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/rossonet')
	}
	
	def rossonetCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/rossonetCtrl')
	}
	
	def quartz() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/quartz')
	}
	
	def quartzCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/quartzCtrl')
	}
	
	def processi() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/processi')
	}
	
	def processiCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/processiCtrl')
	}
	
	def kettle() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/kettle')
	}
	
	def kettleCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/kettleCtrl')
	}
	
	def dashrossonet() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/dashboard')
	}
	
	def dashrossonetCtrl() {
		render accoppiatoreService.esegui('master','bash $AR4K_ANGULAR/dashboardCtrl')
	}
}

