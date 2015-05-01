/**
 * Ruolo
 *
 * <p>Ruolo utente</p>
 *
 * <p style="text-justify">
 * Il ruolo utente viene caricato dal contesto Ar4k</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Utente
 * @see org.ar4k.UtenteRuolo
 * @see org.ar4k.Contesto
 */

package org.ar4k

class Ruolo {

	String authority

	/** dump dati */
	def esporta() {
		return [
			authority:authority
		]
	}

	static mapping = { cache true }

	static constraints = {
		authority blank: false, unique: true
	}
}
