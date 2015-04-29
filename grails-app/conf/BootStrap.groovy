import org.codehaus.groovy.grails.commons.GrailsApplication;

import grails.util.Environment

import org.ar4k.secure.*

class BootStrap {

	GrailsApplication grailsApplication
	def bootStrapService

	def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				log.info("Sistema in Sviluppo... ")
				if ( verificaPresenzaConfigurazioni() ) log.info("Connesso a vaso master...")
				break;
			case Environment.PRODUCTION:
				if ( verificaPresenzaConfigurazioni() ) log.info("Connesso a vaso master...")
				break;
		}

		log.info("\nIn BootStrap.groovy:\n***  ")
		for (String property: System.getProperty("java.class.path").split(";")) {
			log.info(property + "\n")
		}
		log.info("***\n")
	}

	def destroy = {
	}
	
	Boolean verificaPresenzaConfigurazioni() {
		if (grailsApplication.config.master.host) {
			log.info("Configuro i parametri di configurazione trovati su file")
			bootStrapService.macchinaMaster = grailsApplication.config.master.host
			bootStrapService.portaMaster = grailsApplication.config.master.port
			bootStrapService.utenteMaster = grailsApplication.config.master.user
			bootStrapService.keyMaster = grailsApplication.config.master.key
			return bootStrapService.provaConnessione()
		}
	}
}
