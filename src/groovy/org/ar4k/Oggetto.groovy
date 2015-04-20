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
// Classe astratta Oggetto
abstract class Oggetto {
	String etichetta = UUID.randomUUID()
	String tipo = 'astratto'
	String stato = 'Nuovo'
	String descrizione ='Oggetto sistema AR4K by Rossonet\n'
	Boolean automatico = false
	Oggetto padre = null
	Contesto contestoMaster
	List<Contesto> contesti = []
	List<Funzionalita> funzionalita = []
	List<Meme> ricette = []

	String descrivi() {
		String ritorno = "----------------------------\n"
		ritorno += "etichetta: "+etichetta+"\n"
		ritorno += "tipo: "+tipo+"\n"
		return ritorno
	}

}
