/**
 * Associazione Utente Ruolo
 *
 * <p>Associazioni Utenti Ruoli</p>
 *
 * <p style="text-justify">
 * I dati degli utenti sono caricati dal Contesto</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Utente
 * @see org.ar4k.Ruolo
 * @see org.ar4k.Contesto
 */

package org.ar4k

import grails.converters.JSON;

import org.apache.commons.lang.builder.HashCodeBuilder

class UtenteRuolo implements Serializable {

	private static final long serialVersionUID = 1

	Utente utente
	Ruolo ruolo

	/** esporta gli utenti nel contesto */
	def esporta() {
		log.info("Esporta UtenteRuolo: "+utente?.username+" nel ruolo "+ruolo?.authority)
		return [
			utente:utente.esporta(),
			ruolo:ruolo.esporta()
		]
	}
	
	UtenteRuolo importa(Map json){
		log.info("importa() l'utente-ruolo: "+json.utente.username+"-"+json.ruolo.authority)
		UtenteRuolo utenteRuoloCreato = new UtenteRuolo(
			utente:new Utente().importa(json.utente),
			ruolo:new Ruolo().importa(json.ruolo),
			)
		return utenteRuoloCreato
	}

	boolean equals(other) {
		if (!(other instanceof UtenteRuolo)) {
			return false
		}

		other.utente?.id == utente?.id &&
				other.ruolo?.id == ruolo?.id
	}

	int hashCode() {
		def builder = new HashCodeBuilder()
		if (utente) builder.append(utente.id)
		if (ruolo) builder.append(ruolo.id)
		builder.toHashCode()
	}

	static UtenteRuolo get(String utenteId, String ruoloId) {
		UtenteRuolo.where {
			utente == Utente.load(utenteId) &&
					ruolo == Ruolo.load(ruoloId)
		}.get()
	}

	static boolean exists(String utenteId, String ruoloId) {
		UtenteRuolo.where {
			utente == Utente.load(utenteId) &&
					ruolo == Ruolo.load(ruoloId)
		}.count() > 0
	}

	static UtenteRuolo create(Utente utente, Ruolo ruolo, boolean flush = false) {
		def instance = new UtenteRuolo(utente: utente, ruolo: ruolo)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(Utente u, Ruolo r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = UtenteRuolo.where {
			utente == Utente.load(u.id) &&
					ruolo == Ruolo.load(r.id)
		}.deleteAll()

		if (flush) {
			UtenteRuolo.withSession { it.flush()
			} }

		rowCount > 0
	}

	static void removeAll(Utente u, boolean flush = false) {
		if (u == null) return

			UtenteRuolo.where {
				utente == Utente.load(u.id)
			}.deleteAll()

		if (flush) {
			UtenteRuolo.withSession { it.flush()
			} }
	}

	static void removeAll(Ruolo r, boolean flush = false) {
		if (r == null) return

			UtenteRuolo.where {
				ruolo == Ruolo.load(r.id)
			}.deleteAll()

		if (flush) {
			UtenteRuolo.withSession { it.flush()
			} }
	}

	static constraints = {
		ruolo validator: { Ruolo r, UtenteRuolo ur ->
			if (ur.utente == null) return
				boolean existing = false
			UtenteRuolo.withNewSession {
				existing = UtenteRuolo.exists(ur.utente.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['ruolo', 'utente']
		version false
	}
}
