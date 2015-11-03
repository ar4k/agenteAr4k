/**
 * Ricettario
 *
 * <p>Un ricettario corrisponde ad un archivio git accessibile da un vaso e contiene semi utilizzabili nel vaso per la creazione dei memi.</p>
 *
 * <p style="text-justify">
 * In un vaso sono presenti più ricettari. Un vaso può utilizzare i ricettari compatibili con le funzionalità di cui dispone (base e aggiunte con memi).</br>
 * Un ricettario espone tutti i metodi per operare con il repository git di riferimento.</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Meme
 * @see org.ar4k.Vaso
 * @see org.ar4k.Contesto
 */

package org.ar4k

import grails.converters.JSON;

class Ricettario {
	/** id univoco ricettario */
	String idRicettario = UUID.randomUUID()
	/** etichetta ricettario */
	String etichetta = 'Ar4k base'
	/** descrizione ricettario */
	String descrizione ='Ricettario (git) AR4K by Rossonet'
	/** repository git */
	RepositoryGit repositoryGit = new RepositoryGit()
	/** semi disponibili sul ricettario */
	List<Seme> semi = []
	/** ultimo caricamento */
	String aggiornato
	/** se vero, carica il repository su tutti i vasi  */
	Boolean clonaOvunque = true
	/** se vero, esegue in caricamento del repository sui vasi in bootstrap */
	Boolean caricaInBootstrap = true



	/** esporta il ricettario */
	def esporta() {
		log.info("esporta() il ricettario: "+idRicettario)
		return [
			idRicettario:idRicettario,
			etichetta:etichetta,
			descrizione:descrizione,
			repositoryGit:repositoryGit.esporta(),
			aggiornato: aggiornato.toString(),
			clonaOvunque:clonaOvunque,
			semi:semi*.esporta(),
			caricaInBootstrap:caricaInBootstrap
		]
	}

	Ricettario importa(Map json){
		log.info("importa() ricettario: "+json.idRicettario)
		Ricettario ricettarioCreato = new Ricettario(
				idRicettario:json.idRicettario,
				etichetta:json.etichetta,
				descrizione:json.descrizione,
				repositoryGit:new RepositoryGit(json.repositoryGit),
				aggiornato: json.aggiornato,
				clonaOvunque:json.clonaOvunque,
				semi:semi*.esporta(),
				caricaInBootstrap:json.caricaInBootstrap
				)
		json.semi.each{ricettarioCreato.semi.add(new Seme().importa(it))}
		return ricettarioCreato
	}
}

/**
 * RepositoryGit
 *
 * Struttura dati per lista repository GIT
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */
class RepositoryGit {
	/** utente, se null accesso anonimo */
	String utente = null
	/** password utente */
	String password = null
	/** URL Repository */
	String indirizzo = 'https://github.com/rossonet/templateAr4k.git'
	/** directory ricettario in vaso */
	String nomeCartella = 'ar4k_open'
	/** stato */
	Boolean configurato = true

	def esporta() {
		log.info("esporta: "+indirizzo )
		return [
			utente:utente,
			password:password,
			indirizzo:indirizzo,
			nomeCartella:nomeCartella,
			configurato:configurato
		]
	}
}

/**
 * Classe store per il meme, rappresenta una directory nel ricettario.
 *
 * in particolare mette a disposizione del sistema i metadati per la gestione
 * dei memi istanziabili.
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */
class Seme {
	/** dati meme germinante*/
	Meme meme
	String percorso

	/** esporta il seme */
	def esporta() {
		log.info("esporta() il seme in : "+percorso)
		return [
			meme:meme.esporta(),
			percorso:percorso
		]
	}

	Seme importa(Map json){
		log.info("importa() il seme: "+json.meme.idMeme)
		Seme semeCreato = new Seme(
				percorso:json.percorso,
				meme:new Meme().importa(json.meme)
				)
		return semeCreato
	}
}