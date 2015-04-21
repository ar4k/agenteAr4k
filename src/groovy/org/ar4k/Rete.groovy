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

// Class rete
class Rete {
	String etichetta = UUID.randomUUID()
	String descrizione ='Rete gestita da AR4K by Rossonet'
	String rete = '127.0.0.1/24'
	List<Oggetto> presenti = []
	
	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			rete:rete,
			presenti:presenti*.etichetta
			]
	}
}
