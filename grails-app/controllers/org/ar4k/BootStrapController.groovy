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
				if (bootStrapService.inAvvio == false) {
					return completata()
				} else {
					if (bootStrapService.inReset == true) {
						return reset()
					} else {
						return success()
					}
				}
			}
			on ("success"){[verifica:bootStrapService.verificaConnettivitaInterfaccia()]}.to "showBenvenuto"
			on (Exception).to "showBenvenuto"
			on ("completata").to("completata")
			on ("reset").to("verificaMaster")
		}

		showBenvenuto {
			on ("configuraProxyJvm").to "aggiungiProxy"
			on ("configuraCodCommerciale").to "inizioCod"
			on ("configuraMaster").to "inizio"
		}

		inizioCod {
			action { bootStrapService.verificaConnettivitaInterfaccia() }
			on ("success").to "configuraCodCommerciale"
			on (Exception).to "showBenvenuto"
		}

		inizio {
			action { bootStrapService.verificaConnettivitaInterfaccia() }
			on ("success").to "configuraMaster"
			on (Exception).to "showBenvenuto"
		}

		aggiungiProxy {
			action {
				bootStrapService.proxyVersoMaster=params.proxyJvm?:''
				bootStrapService.passwordProxyVersoMaster=params.passwordJvm?:''
				if (bootStrapService.proxyVersoMaster=='NO NETWORK TEST') bootStrapService.escludiProveConnessione = true // per disabilitare test di rete
			}
			on ("success").to "inizio"
			on (Exception).to "showBenvenuto"
		}

		configuraCodCommerciale {
			on ("indietro").to "showBenvenuto"
			on ("completata").to("provaCodiceCommerciale")
			on ("fallita"){ [messaggioOlark:"Salve. Serve aiuto per configurare la piattaforma?"] }.to("fallita")
		}

		provaCodiceCommerciale {
			action {
				bootStrapService.codiceAttivazioneAr4k=params.codCommerciale?:''
				bootStrapService.impostaCodiceCommerciale(bootStrapService.codiceAttivazioneAr4k)
				return completata()
			}
			on ("completata").to("completata")
			on ("errore").to("configuraCodCommerciale")
		}

		configuraMaster {
			on ("indietro").to "showBenvenuto"
			on ("configuraProxyJvm").to "configuraProxyMaster"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
			on ("verificaMaster").to "verificaMaster"
		}

		verificaMaster {
			action {
				bootStrapService.macchinaMaster = params.indirizzoMaster?:bootStrapService.macchinaMaster
				if (params.portaMaster == "") {
					bootStrapService.portaMaster = 0
				}	else {
					bootStrapService.portaMaster = params.portaMaster?.toInteger()?:bootStrapService.portaMaster // Per evitare problemi in caso di Integer null
				}
				bootStrapService.utenteMaster = params.utenteMaster?:bootStrapService.utenteMaster
				bootStrapService.keyMaster = params.chiaveMaster?:bootStrapService.keyMaster
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
			on ("success").to "testProxyMaster"
		}

		testProxyMaster {
			action {
				bootStrapService.proxyMasterInternet=params.proxyMaster?:bootStrapService.proxyMasterInternet
				bootStrapService.passwordProxyMasterInternet=params.passwordProxyMaster?:bootStrapService.passwordProxyMasterInternet
				Boolean risultato = false
				if (bootStrapService.proxyMasterInternet=='NO NETWORK TEST') {
					bootStrapService.escludiProveConnessioneVaso = true // per disabilitare test di rete
					risultato= true
				} else {
					risultato = bootStrapService.verificaConnettivitaVasoMaster()
				}
				if (risultato == true ) {
					return success()
				}
				else {
					return sconnesso()
				}
			}
			on ("success").to "inizio"
			on ("sconnesso").to "configuraProxyMaster"
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
			on ("fallita"){ [messaggioOlark:"Salve. Serve aiuto per configurare la piattaforma?"] }.to("fallita")
		}

		testFinale {
			action {
				if(params.utenteDemo ) {
					if ( params.passwordDemo1 == params.passwordDemo2 && params.passwordDemo1 != '') {
						log.info("Creo l'utente "+params.utenteDemo)
						bootStrapService.aggiungiUtente(params.utenteDemo,params.passwordDemo1)
					} else {
						log.info("Le password NON corrispondo per creare l'utente "+params.utenteDemo)
						if ( params.utenteDemo) {
							return configuraAmministratore()
						}
					}
				}

				if(bootStrapService.avvia()) {
					return completata()
				} else {
					return fallita()
				}
			}
			on ("completata").to("completata")
			on ("configuraAmministratore").to "configuraAmministratore"
			on ("fallita"){[rapporto:bootStrapService.toString()]}.to("fallita")
		}

		completata { redirect controller:'admin' }

		// Implementare pagina aiuto via Olark
		fallita {
			on ("indietro").to "showBenvenuto"
			on ("configuraCodCommerciale").to "configuraCodCommerciale"
		}
	}
}
