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
		redirect(action: "boot")
	}

	/** Gestisce il workflow di bootstrap */
	def bootFlow = {
		entrata {
			action {
				[verifica:bootStrapService.verificaConnettivitaInterfaccia()]
			}
			on ("success").to "showBenvenuto"
			on (Exception).to "showBenvenuto"
		}

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
			on ("fallita"){ [messaggioOlark:"Qualcosa non funziona bene nella configurazione. Serve aiuto per configurare la piattaforma?"] }.to("fallita")
		}

		configuraMaster {
			on ("indietro").to "showBenvenuto"
			on ("configuraProxyJvm").to "configuraProxyJvm"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
			on ("verificaMaster").to "verificaMaster"
		}

		verificaMaster {
			action {
				bootStrapService.macchinaMaster = params.indirizzoMaster?:''
				bootStrapService.portaMaster = params.portaMaster?.toInteger()?:0 // Per evitare problemi in caso di Integer null
				bootStrapService.utenteMaster = params.utenteMaster?:''
				bootStrapService.keyMaster = params.chiaveMaster?:''
				log.info("Verifica l'accesso a "+bootStrapService.utenteMaster+"@"+bootStrapService.macchinaMaster+":"+bootStrapService.keyMaster)
				if (bootStrapService.caricaVasoMaster()) {
					log.info("Verifica connessione master completata...")
					if(bootStrapService.verificaConnettivitaVasoMaster()) {
						return scegliContesto()
					} else {
						return configuraProxyMaster()
					}
				} else {
					return configuraMaster()
				}

			}
			on ("scegliContesto"){
				def lista = []
				bootStrapService.contestiInMaster.each{lista.add([descrizione:it.etichetta,id:it.idContesto])}
				[listaContesti:lista]
			}.to "scegliContesto"
			on ("configuraProxyMaster").to "configuraProxyMaster"
			on ("configuraMaster").to "configuraMaster"
		}

		configuraProxyMaster {
			on ("indietro").to "configuraMaster"
			on ("scegliContesto").to "scegliContesto"
		}

		scegliContesto {
			on ("indietro").to "configuraMaster"
			on ("scegliInterfaccia").to "provaContesto"
		}

		provaContesto {
			action {
				if (bootStrapService.caricaContesto(params.contesto)) {
					return scegliInterfaccia()
				} else {
					return errore()
				}
			}
			on ("scegliInterfaccia"){
				def lista = []
				bootStrapService.interfacceInContesto.each{lista.add([descrizione:it.etichetta,id:it.idInterfaccia])}
				[listaInterfacce:lista]
			}.to "scegliInterfaccia"
			on ("errore").to "scegliContesto"
		}

		scegliInterfaccia {
			on ("provaUtente").to "provaUtente"
		}

		provaUtente {
			action {
				bootStrapService.idInterfacciaScelta = params.interfaccia
				if(bootStrapService.utentiInContesto.size()>0) {
					return completata()
				} else {
					return configuraAmministratore()
				}
			}
			on ("configuraAmministratore").to "configuraAmministratore"
			on ("completata").to("testFinale")
		}

		configuraAmministratore {
			on ("completata").to("testFinale")
		}

		testFinale {
			action {
				if(bootStrapService.avvia()) {
					return completata()
				} else {
					return fallita()
				}
			}
			on ("completata").to("completata")
			on ("fallita"){[rapporto:bootStrapService.toString()]}.to("fallita")
		}

		completata {  redirect controller: 'admin'  }

		fallita {
			on ("indietro").to "showBenvenuto"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
		}
	}
}
