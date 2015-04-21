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

// Schedulazione Cron
class Schedulazione {
	String etichetta = UUID.randomUUID()
	String descrizione ='Schedulazione AR4K by Rossonet\n'
	Processo processo
	String cronConf

	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			processo:processo.etichetta,
			cronconf:cronConf
		]
	}
}
