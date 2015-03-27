package org.ar4k

import org.apache.commons.lang.builder.HashCodeBuilder

class UtenteRuolo implements Serializable {

	private static final long serialVersionUID = 1

	Utente utente
	Ruolo ruolo

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

	static UtenteRuolo get(long utenteId, long ruoloId) {
		UtenteRuolo.where {
			utente == Utente.load(utenteId) &&
			ruolo == Ruolo.load(ruoloId)
		}.get()
	}

	static boolean exists(long utenteId, long ruoloId) {
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

		if (flush) { UtenteRuolo.withSession { it.flush() } }

		rowCount > 0
	}

	static void removeAll(Utente u, boolean flush = false) {
		if (u == null) return

		UtenteRuolo.where {
			utente == Utente.load(u.id)
		}.deleteAll()

		if (flush) { UtenteRuolo.withSession { it.flush() } }
	}

	static void removeAll(Ruolo r, boolean flush = false) {
		if (r == null) return

		UtenteRuolo.where {
			ruolo == Ruolo.load(r.id)
		}.deleteAll()

		if (flush) { UtenteRuolo.withSession { it.flush() } }
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
