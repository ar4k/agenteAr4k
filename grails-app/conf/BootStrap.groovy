import org.codehaus.groovy.grails.commons.GrailsApplication;
import grails.util.Environment
import org.ar4k.secure.*

class BootStrap {
	
	GrailsApplication grailsApplication
	def ProcedureService
	def KettleService
	def SSHService

    def init = { servletContext ->
		switch (Environment.current) {
			case Environment.DEVELOPMENT:
				println "Sistema in Sviluppo..."
				ProcedureService.creaDemoUtente()
				startKettle()
				tunnelControllo()
				break;
			case Environment.PRODUCTION:
				println "Sistema in produzione..."
				ProcedureService.creaDemoUtente()
				startKettle()
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
		println "Collego il tunnel SSH di controllo."
		def sessione =  SSHService.addConnession('utente','localhost',22,'westing')
		println "Id sessione: " + sessione.toString()
		println "Lista Connessioni configurate: " + SSHService.listConnession()
		println "Apro canale R ssh: " + SSHService.addRTunnel(sessione,6666,'localhost',2666)
		println "Apro canale R web: " + SSHService.addRTunnel(sessione,6667,'localhost',8080)
		//println "Apro canale L web: " + SSHService.addLTunnel(sessione,6668,'localhost',8080)
		println "Lista tunnel R: " + SSHService.listPortForwardingR(sessione)
		println "Lista tunnel L: " + SSHService.listPortForwardingL(sessione)
		println "Risultato esecuzione uname -a: " + SSHService.esegui(sessione,'uname -a',System.err)
	}
	
    def destroy = {
    }
}
