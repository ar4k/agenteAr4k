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

    def index() { 
		redirect action:'boot'
	}
	
	
	def bootFlow = {
		showBenvenuto {
			on ("completato").to "configurazioneCompletata"
			on ("fallita").to "configurazioneFallita"
		}
		configurazioneCompleta {
		}
		configurazioneFallita {
		}
	}
}
