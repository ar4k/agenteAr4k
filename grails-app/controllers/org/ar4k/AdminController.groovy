package org.ar4k

class AdminController {

    def index() { 
		[messaggioOlark: "Benvenuto nel template di sviluppo applicativo AR4K!"]
		}

    def headerNotification() {
		render(template: "headerNotification")
    }
}
