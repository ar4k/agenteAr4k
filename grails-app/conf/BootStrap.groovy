import org.codehaus.groovy.grails.commons.GrailsApplication;
import grails.util.Environment
import org.ar4k.secure.*

class BootStrap {

	GrailsApplication grailsApplication
	def ProcedureService
	def KettleService
	def AccoppiatoreService

	def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				log.info("Sistema in Sviluppo... ")
				break;
			case Environment.PRODUCTION:
				log.info("Sistema in produzione... ")
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
}
