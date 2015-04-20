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

// Class comando
class Processo {
	String etichetta = UUID.randomUUID()
	String descrizione ='Processo AR4K by Rossonet\n'
	Boolean persistente = false
	DateTime scadenza = null
	String tipoEsecuzione = 'bash'
	Oggetto target = null
	Boolean salvaRisultato = true
	String comando = 'ls -a'
	String codaAggiornamento = etichetta
	String stato = 'Inizializzato'
	List<Funzionalita> richieste = []

	String esegui() {
		stato = 'In esecuzione'
		InputStream errore
		return target.esegui('source ~/.bash_profile ; '+comando,errore)
	}

}
