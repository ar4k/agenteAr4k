/**
 * Stato
 *
 * <p>Uno stato rappresenta una rete e la sua associazione ai vasi</p>
 *
 * <p style="text-justify">
 * Uno stato mappa costantemente la situazione di rete tramite ricette del meme base Rossonet.</br>
 * Lo stato scopre i servizi sulle reti e sui vasi (disponibilità account root, seriali -compresi stampanti ecc...).
 * Uno stato è sempre associato ad un contesto.
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Contesto
 */

package org.ar4k

class Stato {
	/** id univoco stato */
	String idStato = UUID.randomUUID()
	/** etichetta stato */
	String etichetta = ''
	/** descrizione stato */
	String descrizione ='Stato rete AR4K by Rossonet'

}
