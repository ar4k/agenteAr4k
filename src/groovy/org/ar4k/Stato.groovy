/**
 * Stato
 *
 * <p>Uno stato rappresenta una rete e la sua associazione ai vasi</p>
 *
 * <p style="text-justify">
 * Uno stato mappa costantemente la situazione di rete tramite ricette del meme base Rossonet.</br>
 * Lo stato scopre i servizi sulle reti e sui vasi (disponibilità account root, seriali -compresi stampanti ecc...).
 * Uno stato è sempre associato ad un contesto.
 * Uno stato è responsabile della gestione degli eventi.
 * </p>
 * 
 * <strong>TODO:</strong></br>
 * Verificare l'integrazione con Activiti</br>
 * Verificare l'integrazione con </br>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Contesto
 */

package org.ar4k
 
 
import com.ecwid.consul.v1.ConsulClient
 


class Stato {
	/** id univoco stato */
	String idStato = UUID.randomUUID()
	/** Client Consul*/
	ConsulClient consulBind = null
	
	String toString() {
		return consulBind.getStatusLeader()
	}
}
