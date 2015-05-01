/**
 * Interfaccia Contesto
 *
 * <p>Gestisce il collegamento tra il Contesto e l'interfaccia</p>
 *
 * <p style="text-justify">
 * ...</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Interfaccia
 * @see org.ar4k.Contesto
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

import java.util.List;
import java.util.Formatter.DateTime

import com.jcraft.jsch.*

@Transactional

class InterfacciaContestoService {
	/** Contesto applicativo Grails */	
	GrailsApplication grailsApplication
	/** servizio bootStrap attivato */
	BootStrapService bootStrapService
		
	/** Stampa la memoria con Camel */
	void freeMemory() {
		sendMessage("seda:input", "Memoria libera: "+Runtime.getRuntime().freeMemory())
	}
	
	/** esegue le procedura ogni 5 min. tramite Quartz */
	void battito() {
		if(!bootStrapService.contesto?.salva()) log.warn("Errore nel salvataggio del contesto!")
		freeMemory()
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// Vecchia implementazione
	
	/** Host gestiti in memoria */
	def macchine = []
	/** Contesti operativi gestiti in memoria */
	def contesti = []

	/**
	 * Carica la configurazione da via ssh dal nodo master.
	 * 
	 * Imposta gli host e i contesti in funzione della configurazione recuperata
	 * 
 	 * @author      Andrea Ambrosini -Rossonet-
  	 * @version     0.1
 	 * @since       2015-04-01
	 * @see org.ar4k.Configurazione
	 * @param archivio 	archivio
	 * @return 		risultato operazione
	 */
	Boolean caricaConfigurazione(String archivio) {
		log.info("Carica la configurazione dal nodo Master via SSH")
		Boolean risultato = false
		Configurazione configurazione = new Configurazione()
		if ( configurazione.recupera(archivio,macchine.find{it.etichetta=='master'})) {
			if (configurazione.carica(macchine,contesti)) {
				risultato = true
			}
		}
		log.info([nodi:macchine*.etichetta,contesti:contesti*.etichetta])
		return risultato
	}

	String salvaConfigurazione(String archivio) {
		log.info("Salva la configurazione sul nodo Master via SSH")
		Configurazione configurazione = new Configurazione()
		log.info("Macchine da salvare: "+macchine*.etichetta)
		log.info("Contesti da salvare: "+contesti*.etichetta)
		String risultato
		if (configurazione.prepara(macchine,contesti)) {
			log.info("Preparazione completata correttamente")
			risultato=configurazione.target(archivio)
		}
		return risultato
	}

	String configuraPadrone() {
		log.info("Configura il nodo Master")

		if (!macchine.find{it.etichetta == 'master'}) {

			HostRemoto padrone = nuovoSSH('master','Connessione configurata in file di configurazione iniziale (bootstrap)',grailsApplication.config.padrone.utente,grailsApplication.config.padrone.host,grailsApplication.config.padrone.porta,grailsApplication.config.padrone.password)

			Funzionalita funzionalita = new Funzionalita()

			Processo processo = new Processo()
			processo.richieste.add(funzionalita)
			processo.target = padrone

			Schedulazione schedulazione = new Schedulazione()
			schedulazione.processo=processo

			Meme meme = new Meme()
			meme.testPreparazione = processo
			meme.installazione = processo
			meme.monitoraggio = processo
			meme.rilevaStato = processo
			meme.sospensione = processo
			meme.avvio = processo
			meme.distruzione = processo
			meme.dump = processo
			meme.schedulazioni.add(schedulazione)
			meme.funzionalita.add(funzionalita)
			meme.processi.add(processo)

			Memoria memoria = new Memoria()
			memoria.memi.add(meme)

			Rete rete = new Rete()
			rete.presenti.add(padrone)

			Contesto contesto = new Contesto()
			contesto.oggetti.add(padrone)
			contesto.reti.add(rete)
			contesto.archiviMemi.add(memoria)
			padrone.contestoMaster = contesto

			contesti.add(contesto)

			padrone.funzionalita.add(funzionalita)
			padrone.ricette.add(meme)

			padrone.tunnel('R','127.0.0.1',6630,null,6630)
		}
		return macchine.find{it.etichetta == 'master'}.descrivi()
	}

	Oggetto nuovoSSH(String etichetta,String descrizione,String utente,String target,Integer porta,String password) {
		log.info("Nuovo nodo SSH: "+etichetta)
		HostRemoto macchina = new HostRemoto()
		macchina.etichetta = etichetta
		macchina.descrizione = descrizione
		macchina.nomeHost = target
		macchina.portaSSH = porta
		macchina.nomeUtente = utente
		macchina.password = password
		if ( etichetta == 'master') {
			macchina.tipo = 'MASTER'
		} else {
			macchina.tipo = 'SSH'
		}
		macchine.add(macchina)
		log.info("Aggiunta macchina: "+macchina.etichetta)
		return macchina
	}

	Channel sessione(String nome) {
		log.info("Richiesta sessione: "+nome)
		Channel connesione = macchine.find{it.etichetta==nome}.sessione(nome)
		return connesione
	}


	HostRemoto creaHostRemoto() {
		log.info("Richiesta creaHostRemoto")
		HostRemoto nuovo = new HostRemoto()
		macchine.add(nuovo)
	}

	HostRemoto getHostRemoto(String etichetta) {
		log.info("Chiamata getHostRemoto"+etichetta)
		return macchine.find{it.etichetta==etichetta}
	}

	List<Oggetto> listaOggetti() {
		return macchine
	}

	List<Contesto> listaContesti() {
		return contesti
	}

	Oggetto getOggetto(String etichetta) {
		return macchine.find{it.etichetta==etichetta}
	}

	String esegui(String etichetta,String comando) {
		Processo processo = new Processo()
		processo.comando = comando
		processo.target = macchine.find{it.etichetta==etichetta}
		log.info(processo.comando +" su "+processo.target.etichetta)
		return processo.esegui()
	}

	String remoteWeb(String etichetta,String target,String portaTarget,String query,String componente) {
		log.info("Chiamata remoteWeb da host: "+etichetta+" verso target: "+target+":"+portaTarget+" (selezionando "+componente+")")
		HostRemoto lanciatore = macchine.find{it.etichetta==etichetta}
		return lanciatore.jsoupSshHttp(target,portaTarget,query,componente)
	}
}


