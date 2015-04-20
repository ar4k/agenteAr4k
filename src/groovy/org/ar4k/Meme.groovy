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
	String descrizione ='Meme AR4K by Rossonet\n'
	Processo testPreparazione
	Processo installazione
	Processo monitoraggio
	Processo rilevaStato
	Processo sospensione
	Processo avvio
	Processo distruzione
	Processo dump
	List<Schedulazione> schedulazioni = []
	String maschera = ''
	String stato = 'inattivo'
}

