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
				println "Sistema in Sviluppo... "
				ProcedureService.creaDemoUtente()
				startKettle()
				tunnelControllo()
				break;
			case Environment.PRODUCTION:
				println "Sistema in produzione... "
				ProcedureService.creaDemoUtente()
				startKettle()
				tunnelControllo()
				break;
		}
		/*
		println "\nIn BootStrap.groovy:\n***  "
		for (String property: System.getProperty("java.class.path").split(";")) {
			println property + "\n"
		}
		println "***\n"
		*/
    }
	
	def startKettle = {
		println "Se esiste un job BootStrap lo eseguo.."
		kettleService.initKettle()
		if ( "BootStrap" in kettleService.listJobs()*.getName() ) {
			println "Eseguo BootStrap"
			kettleService.jobRun("BootStrap")
		} else {
			println "Non esiste il job BootStrap nella directory del repository.."
		}
	}
	
	def tunnelControllo = {
		println AccoppiatoreService.configuraPadrone()
	}
	
    def destroy = {
    }
}
