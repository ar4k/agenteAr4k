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

// Ricetta
class Meme {
	String etichetta = UUID.randomUUID()
	String descrizione ='Meme predefinito AR4K by Rossonet'
	Processo testPreparazione
	Processo installazione
	Processo monitoraggio
	Processo rilevaStato
	Processo sospensione
	Processo avvio
	Processo distruzione
	Processo dump
	List<Schedulazione> schedulazioni = []
	List<Funzionalita> funzionalita = []
	List<Processo> processi = []
	String maschera = '<p>Maschera non presente</p>'
	String stato = 'inattivo'
	
	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			maschera:maschera,
			testPreparazione:testPreparazione.etichetta,
			installazione:installazione.etichetta,
			monitoraggio:monitoraggio.etichetta,
			rilevastato:rilevaStato.etichetta,
			sospensione:sospensione.etichetta,
			avvio:avvio.etichetta,
			distruzione:distruzione.etichetta,
			dump:dump.etichetta,
			funzionalita:funzionalita*.salvataggio(),
			schedulazioni:schedulazioni*.salvataggio(),
			processi:processi*.salvataggio(),
			stato:stato
			]
	}
}

