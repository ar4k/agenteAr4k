/**
 * Utente
 *
 * <p>Utente</p>
 *
 * <p style="text-justify">
 * I dati degli utenti sono caricati dal Contesto</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Ruolo
 * @see org.ar4k.UtenteRuolo
 * @see org.ar4k.Contesto
 */

package org.ar4k

class Utente {

	transient springSecurityService

	String username
	String password
	String email
	String sms
	String jabber
	Date dateCreated
	Date lastUpdated
	byte[] avatar
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static transients = ['springSecurityService']

	static hasMany = [oAuthIDs: OAuthID]

	static constraints = {
		username blank: false, unique: true
		email email: true,nullable: true
		sms nullable: true
		jabber nullable: true
		avatar(nullable:true, maxSize:1073741824) // max of 4GB
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Ruolo> getAuthorities() {
		UtenteRuolo.findAllByUtente(this).collect { it.ruolo }
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	String toString() {
		return this.username
	}
}
