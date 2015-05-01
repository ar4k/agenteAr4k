/**
 * Connessioni
 *
 * <p>Una connessione è un tunnel ssh (gestito sui nodi con autossh)</p>
 *
 * <p style="text-justify">
 * Le connesioni uniscono due memi tra loro interagendo con i relativi vasi.
 * Tramite lo Stato possono essere definiti circuiti di connessione e gli eventuali circuiti di backup.
 * Le connesioni tra memi possono essere da porta a porta (ssh port forward R e L tramite autossh) o rsync tra directory periodico</br>
 * 
 * <strong>TODO:</strong>
 * Connessione stealth (tramite TOR)
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Contesto
 */

package org.ar4k

class Connesione {
	/** id univoco connessione */
	String idConnessione = UUID.randomUUID()
	/** etichetta connessione */
	String etichetta = ''
	/** descrizione connessione */
	String descrizione ='Connessione AR4K by Rossonet'
	
	/** esporta la connessione*/
	def esporta() {
		return [
			idConnessione:idConnessione,
			etichetta:etichetta,
			descrizione:descrizione
			]
	}
}

/**
 * Porta TCP su un vaso
 *  
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 *
 */

class PortaVaso {
	/** porta tcp */
	Integer porta = 0
	String etichetta = ''
	/** vaso che ospita il servizio o il tunnel ssh verso un altro vaso */
	Vaso vaso
	Boolean installata = false
	Boolean attiva = false
	Boolean libera = false
	
	def esporta() {
		
	}
}
