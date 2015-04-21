package org.ar4k

import static groovyx.gpars.dataflow.Dataflow.task
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster

import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Holders

import java.util.Formatter.DateTime

import com.jcraft.jsch.*

// Classi accessorie

// Class funzionalità
class Funzionalita {
	String etichetta = 'Gestione Master SSH'
	String descrizione ='Funzionalità sistema AR4K by Rossonet\n'
	String valoreUnico = UUID.randomUUID()
	String icona = 'fa-neuter'
	List<Funzionalita> dipendenze = []
	
	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			valoreUnico:valoreUnico,
			icona:icona,
			dipendenze:dipendenze*.etichetta
			]
	}
}

