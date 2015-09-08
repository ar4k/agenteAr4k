/**
 * Meme (istanza del Seme)
 * 
 * <p>Meme contiene i gruppo funzionale di processi Activiti</p>
 * 
 * <p style="text-justify">
 * Un meme è un insieme di definizioni di processo Activiti le cui funzionalità sono tra di loro correlate.
 * Il meme rende disponibile al contesto funzionalità e dipende da quelle messe a disposizione da altri memi nel contesto.
 * </p>
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.2-alpha
 * @see org.ar4k.Ricettario
 * @see org.ar4k.Vaso
 */
package org.ar4k

import org.activiti.engine.ProcessEngine;
import grails.converters.JSON
import grails.util.Holders

import groovy.transform.AutoClone

@AutoClone
class Meme {
	/** id univoco meme */
	String idMeme = UUID.randomUUID()
	/** etichetta meme */
	String etichetta = 'Meme generato atomaticamente'
	/** descrizione meme */
	String descrizione ='Meme AR4K by Rossonet'
	/** autore meme */
	String autore = 'Andrea Ambrosini'
	/** gestore meme */
	String gestore = 'Rossonet s.c.a r.l.'
	/** versione meme */
	String versione = '0.2'
	/** lista versioni compatibili -per aggiornamento- */
	List<String> versioniCompatibili = []
	/** icona generale meme */
	String icona = 'fa-thumb-tack'
	/** stato del meme */
	String stato = 'inattivo'
	/** processo lanciato alla creazione del meme */
	String autoStart = ''
	/** lista funzionalità di cui è necessaria la presenza nel contesto per operare */
	List<String> dipendenze = []
	/** lista funzionalità rese disponibili al contesto */
	List<String> funzionalita = []
	/** lista archivi bar (deployment Activiti) da caricare */
	List<Metodo> metodi = []
	/** stati possibili del meme */
	List<StatoMeme> stati = []

	/** carica i processi disponibili nel motore Activiti */
	Boolean caricaProcessiActiviti(Vaso vasoMaster,ProcessEngine processEngine) {
		Boolean ritorno = true
		metodi.each{ processo ->
			if ( vasoMaster.caricaProcesso(processEngine,processo.riferimento,processo.etichetta,idMeme) == false ) {
				ritorno = false
			}
		}
		try {
			Holders.applicationContext.getBean("interfacciaContestoService").sendMessage("activemq:topic:interfaccia.eventi",[tipo:'CARICORISORSEACTIVITI',messaggio:'Caricati i processi con risultato: '+ritorno.toString()])
		} catch (Exception ee){
			log.info "Evento da vaso non comunicato: "+ee.toString()
		}
		return ritorno
	}

	/** dump dati del meme */
	def esporta() {
		log.info("esporta() il meme: "+idMeme)
		return [
			idMeme:idMeme,
			etichetta:etichetta,
			descrizione:descrizione,
			autore:autore,
			gestore:gestore,
			versione:versione,
			versioniCompatibili:versioniCompatibili,
			icona:icona,
			stato:stato,
			autoStart:autoStart,
			stati:stati*.esporta(),
			dipendenze:dipendenze,
			funzionalita:funzionalita,
			metodi:metodi*.esporta()
		]
	}

	String toString() {
		return idMeme +' ('+etichetta+') '+descrizione
	}

	/**
	 * Chiamato quando si avvia un interfaccia nel contesto
	 */
	Boolean verificaAvvia() {
		return false
	}

	/** ritorna la maschera html relativa allo stato corrente del meme */
	String maschera() {
		return stati.find{it.etichetta == stato}.angularMaschera
	}

	/** ritorna il pannello per la dashboard relativo allo stato corrente del meme */
	String dashboard() {
		return stati.find{it.etichetta == stato}.angularDashboard
	}

	/** ritorna la maschera html ridotta relativa allo stato corrente del meme */
	String box() {
		return stati.find{it.etichetta == stato}.angularBox
	}

	/** ritorna il tooltip relativo allo stato corrente del meme */
	String tooltip() {
		return stati.find{it.etichetta == stato}.tooltip
	}

	/** ritorna l'icona relativa allo stato corrente del meme */
	String iconaStato() {
		return stati.find{it.etichetta == stato}.icona
	}
}


/**
 * Metodo sul meme (da sviluppare aggancio verso i vari Service)
 *
 * @author Andrea Ambrosini
 *
 */
class Metodo {
	/** id univoco metodo */
	String idMetodo = UUID.randomUUID()
	String etichetta = 'metodo senza etichetta'
	String descrizione = 'metodo creato in automatico'
	/** percorso file bar (Activiti) nel repository git -Ricettario- */
	String riferimento = ''

	def esporta() {
		return [
			idMetodo:idMetodo,
			etichetta:etichetta,
			descrizione:descrizione,
			riferimento:riferimento
		]
	}
}

/** 
 * Stato possibile per il Meme
 */
class StatoMeme {
	String etichetta = 'non implementato'
	/** maschera del meme nello stato */
	String angularMaschera = '<div>INTERFACCIA NON IMPLEMENTATA</div>'
	/** html per dashboard del meme nello stato */
	String angularDashboard = '<div>INTERFACCIA NON IMPLEMENTATA</div>'
	/** maschera piccola meme nello stato */
	String angularBox = '<div>INTERFACCIA NON IMPLEMENTATA</div>'
	/** icona pulsante nello stato -Maschera Memi- */
	String icona = 'fa-thumb-tack'
	/** tooltip pulsante nello stato -Maschera Memi- */
	String tooltip = 'interfaccia non implementata'

	String toString() {
		return etichetta
	}

	def esporta() {
		return [
			etichetta:etichetta,
			angularMaschera:angularMaschera,
			angularDashboard:angularDashboard,
			angularBox:angularBox,
			icona:icona,
			tooltip:tooltip
		]
	}
}