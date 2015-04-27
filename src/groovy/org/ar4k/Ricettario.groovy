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
	String repositoryGit = 'https://github.com/rossonet/agenteAr4k.git'
	/** utente git */
	String utenteGit = 'utente'
	/** password git */
	String passwordGit = 'password'

}
