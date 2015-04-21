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
import groovy.json.JsonBuilder

import java.util.Formatter.DateTime

import com.jcraft.jsch.*

// Classi accessorie

// Classe contesti (per i permessi sui tunnel)
class Configurazione {
	String etichetta = UUID.randomUUID()
	String descrizione ='Configurazione sistema AR4K by Rossonet\n'
	String cliente ='Rossonet'
	String idContabilita = 'xx'
	def oggetti
	def cont
	Date dataCreazione = new Date()
	Date ultimaModifica = new Date()


	Boolean prepara(def macchine,def contesti) {
		log.error("Carica: "+macchine*.etichetta)
		oggetti = macchine
		cont = contesti
		return true
	}

	Boolean target(String archivio) {
		if (!archivio) archivio = 'base'
		log.error("Salva la configurazione su "+archivio)
		log.error("Lista: "+oggetti)
		String risultato = [
			nodi:new JsonBuilder(oggetti*.salvataggio()).toPrettyString(),
			contesti:new JsonBuilder(cont*.salvataggio()).toPrettyString()
		]
		log.error(risultato)
		return true
	}


}
