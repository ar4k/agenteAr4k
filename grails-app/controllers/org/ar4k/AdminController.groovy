package org.ar4k
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
		render(template: "terminale", model:[mappa: 'padrone',comandoAvvio:'export TERM=xterm-256color; sleep 2 ; clear'] )
	}
}

