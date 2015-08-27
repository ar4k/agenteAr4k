/**
 * Stato
 *
 * <p>Lo stato rappresenta la situazione attuale dell'infrastruttura di rete</p>
 *
 * <p style="text-justify">
 * Uno stato rappresenta una connesione al demone Consul tramite le API Java.
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.2-alpha
 * @see org.ar4k.Contesto
 */

package org.ar4k
 
 
import com.ecwid.consul.v1.ConsulClient
 


class Stato {
	/** id univoco stato */
	String idStato = UUID.randomUUID()
	/** Client Consul collegato allo stato*/
	ConsulClient consulBind = null
	
	String toString() {
		return consulBind.getStatusLeader()
	}
}
