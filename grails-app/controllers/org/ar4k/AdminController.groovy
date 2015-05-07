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

	def index() {
		//[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
	}

	def headerNotification() {
		render(template: "headerNotification")
	}
	
	def headerHtml() {
		render(template: "headerHtml")
	}
	
	def appjs() {
		render(template: "appjs")
	}
	
	def sidebar() {
		render(template: "sidebar")
	}

	def terminale(String host) {
		render(template: "terminale", model:[mappa: host?:'master',comandoAvvio:'sleep 3 ; clear'] )
	}
	
	def oggetti() {
		render(template: "oggetti")
	}
	
	def oggettiCtrl() {
		render(template: "oggettiCtrl")
	}
	
	def rossonet() {
		render(template: "rossonet")
	}
	
	def rossonetCtrl() {
		render(template: "rossonetCtrl")
	}
	
	def quartz() {
		render(template: "quartz")
	}
	
	def quartzCtrl() {
		render(template: "quartzCtrl")
	}
	
	def processi() {
		render(template: "processi")
	}
	
	def processiCtrl() {
		render(template: "processiCtrl")
	}
	
	def kettle() {
		render(template: "kettle")
	}
	
	def kettleCtrl() {
		render(template: "kettleCtrl")
	}
	
	def dashrossonet() {
		render(template: "dashRossonet")
	}
	
	def dashrossonetCtrl() {
		render(template: "dashRossonetCtrl")
	}
}

