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
	String descrizione ='Configurazione sistema AR4K by Rossonet'
	String cliente ='Rossonet'
	String idContabilita = 'xx'
	def oggetti
	def contesti
	Date dataCreazione = new Date()
	Date ultimaModifica = new Date()


	Boolean prepara(def macchine,def cont) {
		log.info("Carica: "+macchine*.etichetta)
		oggetti = macchine
		contesti = cont
		return true
	}

	String target(String archivio) {
		if (!archivio) archivio = 'base'
		log.info("Salva la configurazione su "+archivio)
		log.debug("Lista oggetto: "+oggetti)
		log.debug("Lista contesti: "+oggetti)
		String stringaBlocco = 'EOF_'+UUID.randomUUID()
		String risultato = 'salvaConfigurazione << '+stringaBlocco+'\n'
		risultato += [
			nodi:new JsonBuilder(oggetti*.salvataggio()).toPrettyString(),
			contesti:new JsonBuilder(contesti*.salvataggio()).toPrettyString()
		]
		risultato += '\n'+stringaBlocco+'\n'
		OutputStream errore = new PipedOutputStream()
		log.debug(risultato)
		Processo processo = new Processo()
		processo.comando = risultato
		processo.target = oggetti.find{it.etichetta == 'master'}
		return processo.esegui()
	}


}
