/**
 * Ricettario
 *
 * <p>Un ricettario corrisponde ad un archivio git accessibile da un vaso e contiene memi utilizzabili nel vaso</p>
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

class Ricettario {
	/** id univoco ricettario */
	String idRicettario = UUID.randomUUID()
	/** etichetta ricettario */
	String etichetta = ''
	/** descrizione ricettario */
	String descrizione ='Ricettario (git) AR4K by Rossonet'
	/** repository git */
	RepositoryGit repositoryGit = new RepositoryGit()
	/** semi disponibili sul ricettario */
	List<Seme> semi = []
	
	/** esporta il ricettario */
	def esporta() {
		log.info("esporta() il ricettario: "+idRicettario)
		return [
			idRicettario:idRicettario,
			etichetta:etichetta,
			descrizione:descrizione,
			repositoryGit:repositoryGit
			]
	}

}

/**
 * RepositoryGit
 *
 * Struttura dati per array repository GIT
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
	/** stato */
	Boolean configurato = false
	/** codice errore */
	String codiceErrore = 'Nessun Errore'
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
	
}