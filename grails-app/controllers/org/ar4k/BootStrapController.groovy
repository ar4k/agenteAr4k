/**
 * Controller bootstrap del Contesto
 *
 * <p>
 * BootStrapController gestisce tutte le fasi di avvio applicativo.
 * Accessibile a tutti all'inizio, diventa accessibile solo all'amministratore
 * dopo la configurazione iniziale.
 * </p>
 *
 * <p style="text-justify">
 * Il Controller lavora insieme al relativo service
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.BootStrapService
 */

package org.ar4k

class BootStrapController {

	BootStrapService bootStrapService

	def index() {
		redirect action:'boot'
	}

	/** Gestisce il workflow di bootstrap */
	def bootFlow = {
		showBenvenuto {
			on ("configuraProxyJvm").to "configuraProxyJvm"
			on ("configuraCodCommerciale").to "inizioCod"
			on ("configuraMaster").to "inizio"
		}

		inizioCod {
			action { bootStrapService.verificaConnettivitaInterfaccia() }
			on ("success").to "configuraCodCommerciale"
			on (Exception).to "configuraProxyJvm"
		}
		
		inizio {
			action { bootStrapService.verificaConnettivitaInterfaccia() }
			on ("success").to "configuraMaster"
			on (Exception).to "configuraProxyJvm"
		}

		configuraProxyJvm {
			on ("indietro").to "showBenvenuto"
			on ("configuraCodCommerciale").to "inizioCod"
			on ("configuraMaster").to "inizio"
		}

		configuraCodCommerciale {
			on ("indietro").to "showBenvenuto"
			on ("completata").to("completata")
			on ("fallita"){[messaggioOlark:"Qualcosa non funziona bene nella configurazione. Serve aiuto per configurare la piattaforma?"]}.to("fallita")
		}

		configuraMaster {
			on ("indietro").to "showBenvenuto"
			on ("configuraProxyJvm").to "configuraProxyJvm"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
			on ("configuraProxyMaster").to "configuraProxyMaster"
			on ("scegliContesto").to "scegliContesto"
		}

		configuraProxyMaster {
			on ("indietro").to "configuraMaster"
			on ("scegliContesto").to "scegliContesto"
		}

		scegliContesto {
			on ("indietro").to "configuraMaster"
			on ("scegliInterfaccia").to "scegliInterfaccia"
		}

		scegliInterfaccia {
			on ("configuraAmministratore").to "configuraAmministratore"
			on ("completata").to("completata")
			on ("fallita"){[messaggioOlark:"Qualcosa non funziona bene nella configurazione. Serve aiuto per configurare la piattaforma?"]}.to("fallita")
		}

		configuraAmministratore {
			on ("completata").to("completata")
			on ("fallita"){[messaggioOlark:"Qualcosa non funziona bene nella configurazione. Serve aiuto per configurare la piattaforma?"]}.to("fallita")
		}

		completata { redirect controller: 'admin' }

		fallita {
			on ("indietro").to "showBenvenuto"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
		}
	}
}
