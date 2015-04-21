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

// Classe contesti (per i permessi sui tunnel)
class Contesto {
	String etichetta = UUID.randomUUID()
	String descrizione ='Contesto sistema AR4K by Rossonet'
	String cliente ='Rossonet'
	String idContabilita = 'RIC_OpenSource'
	List<Oggetto> oggetti = []
	List<Rete> reti = []
	List<Memoria> archiviMemi = [] 
	// Oggetto necessario per un contesto
	String ambiente = "contesto="+etichetta+"\n"
	
	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			cliente:cliente,
			idcontabilita:idContabilita,
			oggetti:oggetti*.etichetta,
			reti:reti*.salvataggio(),
			archivimemi:archiviMemi*.salvataggio()
			]
	}
}
