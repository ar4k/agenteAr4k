/**
 * @deprecated vecchia implementazione
 */

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

// Ricettario
class Memoria {
	String etichetta = UUID.randomUUID()
	String descrizione ='Ricettario AR4K by Rossonet'
	String repositoryGit = ''
	String utente = 'agentear4k'
	String password = ''
	String ramo = 'master'
	String stato = 'repositoryDefault'
	Boolean archivio = false
	List<Meme> memi = []

	def salvataggio() {
		return [
			etichetta:etichetta,
			descrizione:descrizione,
			repositorygit:repositoryGit,
			utente:utente,
			password:password,
			ramo:ramo,
			stato:stato,
			archivio:archivio,
			memi:memi*.salvataggio()
		]
	}
}
