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
		render(template: "headerNotification",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def headerHtml() {
		render(template: "headerHtml",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def appjs() {
		render(template: "appjs",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def sidebar() {
		render(template: "sidebar",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

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

	def quartz() {
		render(template: "quartz",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def quartzCtrl() {
		render(template: "quartzCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
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

	def kettle() {
		render(template: "kettle",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def kettleCtrl() {
		render(template: "kettleCtrl",model:[grafica: interfacciaContestoService.interfaccia.grafica])
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

	def listaVasi() {
		def risultato = []
		interfacciaContestoService.contesto.vasi.each{ risultato.add(it) }
		def incapsulato = [vasi:risultato]
		render incapsulato as JSON
	}

	def listaRicettari() {
		def risultato = []
		interfacciaContestoService.contesto.ricettari.each{ risultato.add(it) }
		def incapsulato = [ricettari:risultato]
		render incapsulato as JSON
	}

	def aggiungiRicettario() {
		def ricettario = request.JSON.ricettario
		log.info("Richiesta aggiunta ricettario: "+ricettario)
		RepositoryGit repositoryGit = new RepositoryGit(indirizzo:ricettario.repo,nomeCartella:ricettario.cartella)
		if (
			interfacciaContestoService.contesto.ricettari.add(
				new Ricettario(etichetta:ricettario.etichetta,descrizione:ricettario.descrizione,repositoryGit:repositoryGit)
			)
		)
		{
			render "ok"
		} else {
			render "errore"
		}
	}

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
		)
		{
			render "ok"
		} else {
			render "errore"
		}
	}
	
	def sbadmin2() {
		render(template: "sbadmin2",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def main() {
		render(template: "main",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}

	def timeline() {
		render(template: "timeline",contentType:"text/css",model:[grafica: interfacciaContestoService.interfaccia.grafica])
	}
}

