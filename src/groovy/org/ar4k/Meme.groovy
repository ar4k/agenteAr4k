/**
 * Meme (seme)
 * 
 * <p>Meme contiene una ricetta eseguibile su nodi gestiti da Ar4k</p>
 * 
 * <strong>valutare Docker, il seme contiene un template per la directory (come openshift)</strong>
 * 
 * <p style="text-justify">
 * La parola <a href="http://it.wikipedia.org/wiki/Meme" target="_new">Meme (simile a "seme")</a> si riferisce al concetto espresso
 * per la prima volta nel 1976 dall'evoluzionista <a href="http://it.wikipedia.org/wiki/Richard_Dawkins" target="_new">Richard Dawkins</a> (rif: <a href="http://it.wikipedia.org/wiki/Il_gene_egoista" target="_new">"Gene Egoista"</a>) 
 * </br>
 * Per ogni ricetta sono definiti i requesiti del vaso, il consumo di risorse,
 * i connettori resi disponibili e la loro etichetta.
 * </br>
 * Un meme è parte di un ricettario (git), più memi possono svilupparsi
 * nello stesso vaso (compatibilmente con le risorse), per ogni meme, sul file system del vaso,
 * viene definita una variabile d'ambiente con la home. Partendo da questo percorso il meme utilizza script
 * nella directory <path_meme>/bin/<comando> e mappa tutte le risorse secondo questo schema:
 * </br></br>
 * / - directory base meme</br>
 * /bin/ - directory eseguibili del meme</br>
 * /data/ - parte rw per il meme</br>
 * /eventi/ - trigger per il vaso e il lifecycle</br>
 * /ambiente.conf - parametri aggiornati sul contesto ecc...</br>
 * /run/ - socket su file e annessi</br>
 * /cron/ - stringhe per crontab vaso</br>
 * /secret - i dati riservati -girano sul sistema criptografati-</br>
 * </br></br>
 * Nella directory web del meme sono presenti i file AngularJS per la rappresentazione in maschera.
 * </br>
 * Ogni meme dispone di metodi per l'esportazione come map groovy, la propria istanziazione base (life cycle oggetto),
 * e la comunicazione via Json tramite l'interfaccia web.
 * Alla sua creazione Ar4k deposita un file di configurazione compilato con parametri definiti dal meme in formato Json,
 * il meme implementa i propri controller AngularJs autonomamente.
 * </br>
 * Per implementatare le proprie funzionalità il meme si appoggia ad altri memi (attivi) nel contesto.
 * </br>
 * La piattaforma Ar4k, mette a disposizioni vari servizi per permettere al meme l'erogazioni dei servizi:</br>
 * - gestione del lifecycle (test-installazione,installazioni,monitor,riavvio,stop,resume,salvataggio,migrazione,salva in ricettario,carica da ricettario);</br>
 * - factory servizi web (dump ssh -comando-,stream sessione ssh via Meteor,stream TCP via Meteor,JSoup proxy -con supporto autenticazione-,dump Job Kettle con passaggio di array di configurazione);</br>
 * - Factory tunnelssh (R tunnel e L tunnel e sync filesystem -monitorati con autossh e cron sui vasi-);</br>
 * - API vaso;</br>
 * - API registrazione nuovi oggetti (per memi "factory");</br>
 * - API recupero informazioni contesto e il suo stato -configurazione del contesto inline- (in particolare sulle sessioni -user space- connesse al contesto);</br>
 * - API messaggi,task(ciclo di interazione con utente) e log verso gli utenti;</br>
 * </br></br>
 * Integrare rotte Camel tra i servizi erogati dai memi (come tunnel estensione delle connessioni)</br>
 * </p>
 * 
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Ricettario
 * @see org.ar4k.Vaso
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

class Meme {
	/** id univoco meme */
	String idMeme = UUID.randomUUID()
	/** etichetta meme */
	String etichetta = ''
	/** descrizione meme */
	String descrizione ='Meme AR4K by Rossonet'
	String autore = 'Andrea Ambrosini'
	String gestore = 'Rossonet s.c.a r.l.'
	String versione = ''
	List<String> versioniCompatibili = []
	String mascheraAvvio = '<p>Maschera non presente</p>'
	String mascheraBanner = '<p>Maschera non presente</p>'
	String icona = ''
	String stato = 'inattivo'
	List<String> fileGestiti = []
	List<String> metodiGestiti = []
	List<String> dipendenze = []
	List<String> funzionalita = []
	List<String> variabiliAmbiente = []
	String verificaMd5Sum = 'xxx'
	
	// Come gestire le chiamate del ciclo di vita? Per esempio prima del backup di un MySQL lo script per il dump del db.
	// Come gestire la registrazione di eventi su altre cartucce durante il ciclo di vita?
	// Elencare i vari servizi (JCloud,JSoup & Proxy ecc..). Da valutare bene la generazione di controller Angular.
	 
	/** dump meme */
	def esporta() {
		log.info("esporta() il meme: "+idMeme)
		return [
			idMeme:idMeme,
			etichetta:etichetta,
			descrizione:descrizione,
			mascheraConfigurazione:mascheraConfigurazione,
			stato:stato
		]
	}
}

