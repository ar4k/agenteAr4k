/**
 * Meme (seme)
 * 
 * Meme contiene una ricetta eseguibile su nodi gestiti da Ar4k.
 * 
 * La parola <a href="http://it.wikipedia.org/wiki/Meme" target="_new">Meme (simile a "seme")</a> si riferisce al concetto espresso
 * per la prima volta nel 1976 dall'evoluzionista <a href="http://it.wikipedia.org/wiki/Richard_Dawkins" target="_new">Richard Dawkins</a> (rif: <a href="http://it.wikipedia.org/wiki/Il_gene_egoista" target="_new">"Gene Egoista"</a>) 
 *
 * Per ogni ricetta sono definiti i requesiti del vaso, il consumo di risorse,
 * i connettori resi disponibili e la loro etichetta.
 * 
 * Un meme è parte di un ricettario (git), più memi possono svilupparsi
 * nello stesso vaso (compatibilmente con le risorse), per ogni meme, sul file system del vaso,
 * viene definita una variabile d'ambiente con la home. Partendo da questo percorso il meme utilizza script
 * nella directory <path_meme>/bin/<comando> e mappa tutte le risorse secondo questo schema:
 * 
 * / - directory base meme
 * /bin/ - directory eseguibili del meme
 * /data/ - parte rw per il meme
 * /eventi/ - trigger per il vaso e il lifecycle
 * /ambiente.conf - parametri aggiornati sul contesto ecc...
 * /run/ - socket su file e annessi
 * /cron/ - stringhe per crontab vaso
 * /secret - i dati riservati -girano sul sistema criptografati-
 * 
 * 
 * Nella directory web del meme sono presenti i file AngularJS per la rappresentazione in maschera.
 * 
 * Ogni meme dispone di metodi per l'esportazione come map groovy, la propria istanziazione base (life cycle oggetto),
 * e la comunicazione via Json tramite l'interfaccia web.
 * Alla sua creazione Ar4k deposita un file di configurazione compilato con parametri definiti dal meme in formato Json,
 * il meme implementa i propri controller AngularJs autonomamente.
 * 
 * Per implementatare le proprie funzionalità il meme si appoggia ad altri memi (attivi) nel contesto.
 * 
 * La piattaforma Ar4k, mette a disposizioni vari servizi per permettere al meme l'erogazioni dei servizi:
 * - gestione del lifecycle (test-installazione,installazioni,monitor,riavvio,stop,resume,salvataggio,migrazione,salva in ricettario,carica da ricettario);
 * - factory servizi web (dump ssh -comando-,stream sessione ssh via Meteor,stream TCP via Meteor,JSoup proxy -con supporto autenticazione-,dump Job Kettle con passaggio di array di configurazione);
 * - Factory tunnelssh (R tunnel e L tunnel e sync filesystem -monitorati con autossh e cron sui vasi-);
 * - API vaso;
 * - API registrazione nuovi oggetti (per memi "factory");
 * - API recupero informazioni contesto e il suo stato -configurazione del contesto inline- (in particolare sulle sessioni -user space- connesse al contesto);
 * - API messaggi,task(ciclo di interazione con utente) e log verso gli utenti;
 * 
 * 
 * TODO:
 * Verificare l'integrazione con Activiti
 * Integrare rotte Camel tra i servizi erogati dai memi (come tunnel estensione delle connessioni)
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * 
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


// Ricetta
class Meme {
	String id = UUID.randomUUID()
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

