package org.ar4k

import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster

import javax.swing.text.html.HTML

class AdminController {
	SshService sshService

	def index() {
		[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
	}

	def headerNotification() {
		render(template: "headerNotification")
	}

	def terminale() {
		def atmosphereMeteor = grails.util.Holders.applicationContext.getBean("atmosphereMeteor")
		def lista = []
		def canali = []
		String sessionRandom = UUID.randomUUID()

		lista = sshService.listConnession()
		sshService.console(lista[0])
		canali = sshService.listChannel()
		def canale = sshService.getChannel(canali[0])

		println "ok 1"
		/*
		canale.getInputStream().withStream(){ out ->
			Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class,sessionRandom )
			def testo = out.getText('UTF-8')
			broadcaster.broadcast(testo)
			println 'ok '+testo
		}
        */
		println lista
		println canale
		render(template: "terminale", mappa: sessionRandom )
	}
}

