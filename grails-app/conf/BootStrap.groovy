import org.codehaus.groovy.grails.commons.GrailsApplication;
import grails.util.Environment
import org.ar4k.secure.*

class BootStrap {
	
	GrailsApplication grailsApplication
	def ProcedureService

    def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				println "Sistema in Sviluppo..."
				ProcedureService.creaDemoUtente()
				//startKettle()
				//registraDNS()
				break;
			case Environment.PRODUCTION:
				println "Sistema in produzione..."
				ProcedureService.creaDemoUtente()
				//startKettle()
				//registraDNS()
				break;
		}
		println "\nIn BootStrap.groovy:\n***  "
		for (String property: System.getProperty("java.class.path").split(";")) {
			println property + "\n"
		}
		println "***\n"
    }
    def destroy = {
    }
}
