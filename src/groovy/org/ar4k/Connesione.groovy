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
 * Estendere il concetto di connesione alla gestione del file System (è possibile fare un proxy HTTP di un intero filesytem utilizzabile con FUSE?);
 * eventualmente rsync via ssh periodico?
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
	/** dal punto rete */
	PuntoRete da
	/** al punto rete */
	PuntoRete a
	
	/** esporta la connessione*/
	def esporta() {
		return [
			idConnessione:idConnessione,
			etichetta:etichetta,
			descrizione:descrizione
			]
	}
}

