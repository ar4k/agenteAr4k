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

@Transactional
class AccoppiatoreService {
	GrailsApplication grailsApplication
	def macchine = []

	String configuraPadrone() {
		HostRemoto padrone = nuovoSSH('master','Connessione configurata in file di configurazione iniziale (bootstrap)',grailsApplication.config.padrone.utente,grailsApplication.config.padrone.host,grailsApplication.config.padrone.porta,grailsApplication.config.padrone.password)
		//padrone.tunnel('R','127.0.0.1',2666,'',6666)
		//padrone.tunnel('R','127.0.0.1',6630,'',6666)
		//padrone.tunnel('L','',6668,'127.0.0.1',22)
		return padrone.descrivi()
	}

	Oggetto nuovoSSH(String etichetta,String descrizione,String utente,String target,Integer porta,String password) {
		HostRemoto macchina = new HostRemoto()
		macchina.etichetta = etichetta
		macchina.descrizione = descrizione
		macchina.nomeHost = target
		macchina.portaSSH = porta
		macchina.nomeUtente = utente
		macchina.password = password
		macchina.tipo = 'SSHNODE'
		macchine.add(macchina)
		return macchina
	}

	Channel sessione(String nome) {
		Channel connesione = macchine.find{it.etichetta==nome}.sessione(nome)
		return connesione
	}

	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}

	HostRemoto creaHostRemoto() {
		HostRemoto nuovo = new HostRemoto()
		macchine.add(nuovo)
	}

	HostRemoto getHostRemoto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
	}

	List<Oggetto> listaOggetti() {
		return macchine
	}

	Oggetto getOggetto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
	}

	String esegui(String etichetta,String comando) {
		Processo processo = new Processo()
		processo.comando = comando
		processo.target = macchine.find{it.etichetta==etichetta}
		return processo.esegui()
	}

	String remoteWeb(String etichetta,String target,String portaTarget,String query,String componente) {
		HostRemoto lanciatore = macchine.find{it.etichetta==etichetta}
		return lanciatore.jsoupSshHttp(target,portaTarget,query,componente)
	}
}


