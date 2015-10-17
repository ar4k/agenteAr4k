import org.codehaus.groovy.grails.commons.GrailsApplication;

import grails.util.Environment

import org.ar4k.Vaso;
import org.ar4k.secure.*

class BootStrap {

	GrailsApplication grailsApplication
	def bootStrapService

	def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				log.info("Sistema in configurazione di sviluppo... ")
				if ( provaConfigurazioni() ) log.info("Sistema configurato e pronto...")
				break;
			case Environment.PRODUCTION:
				if ( provaConfigurazioni() ) log.info("Sistema configurato e pronto...")
				break;
		}

		log.debug("\nIn BootStrap.groovy:\n***  ")
		for (String property: System.getProperty("java.class.path").split(";")) {
			log.debug(property + "\n")
		}
		log.debug("***\n")
	}

	def destroy = {
	}
	
	Boolean provaConfigurazioni() {
		if (grailsApplication.config.master.host) {
			log.debug("Configuro i parametri di configurazione trovati su file")
			if (grailsApplication.config.utente && grailsApplication.config.password) {
				bootStrapService.aggiungiUtente(grailsApplication.config.utente?:'',grailsApplication.config.password?:'')
			}
			bootStrapService.macchinaMaster = grailsApplication.config.master.host?:null
			bootStrapService.portaMaster = grailsApplication.config.master.port?:null
			bootStrapService.utenteMaster = grailsApplication.config.master.user?:null
			bootStrapService.keyMaster = grailsApplication.config.master.key?:null
			bootStrapService.idContestoScelto = grailsApplication.config.contesto?:null
			bootStrapService.idInterfacciaScelta = grailsApplication.config.interfaccia?:null
			bootStrapService.codiceAttivazioneAr4k = grailsApplication.config.codiceAttivazione?:null
			bootStrapService.proxyVersoMaster = grailsApplication.config.proxyVersoMaster?:null
			bootStrapService.proxyMasterInternet = grailsApplication.config.proxyMasterInternet?:null
			return bootStrapService.avvia()
		}
	}
}
