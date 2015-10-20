/**
 * Utente Ar4k
 *
 * <p>Utente</p>
 *
 * <p style="text-justify">
 * Gli utenti possono avere più ruoli (tramite UtenteRuolo) e possono
 * essere agganciati a più utenti su fonti di autenticazione OAuth.
 * In pratica ad un utente Ar4k possono corrispondere più account social. 
 * L'interfaccia grafica di ar4k guida l'utente nelle procedure di gestione 
 * necessarie.</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Ruolo
 * @see org.ar4k.UtenteRuolo
 * @see org.ar4k.Contesto
 */

package org.ar4k

import grails.converters.JSON;

import org.activiti.engine.identity.User

class Utente implements User{

	transient springSecurityService

	String id
	String username
	String password
	String firstName
	String lastName
	String email
	String sms
	String jabber
	/** Stringa per rappresentare i periodi in cui gli allert sono attivi -A tendere potrebbe diventare un json con le configurazioni per classe di log-*/
	String workingTime = 'h24'
	Date dateCreated
	Date lastUpdated
	/** Immagine avatar utente -da valutare l'autogenerazione algoritmica- */
	byte[] avatar
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	boolean esportatoConPasswordInChiaro = true

	static transients = ['springSecurityService']


	static hasMany = [oAuthIDs: OAuthID]

	static constraints = {
		username blank: false, unique: true
		email email: true,nullable: true
		firstName nullable:true
		lastName nullable:true
		sms nullable: true
		jabber nullable: true
		avatar(nullable:true, maxSize:1073741824) // max of 4GB
		password blank: false

	}

	static mapping = {
		password column: '`password`'
		id generator: 'uuid'
	}

	/** esporta gli utenti nel contesto */
	def esporta() {
		return [
			username:username,
			password:password,
			firstName:firstName,
			lastName:lastName,
			email:email,
			sms:sms,
			jabber:jabber,
			workingTime:workingTime,
			dateCreated:dateCreated,
			lastUpdated:lastUpdated,
			avatar:avatar,
			enabled:enabled,
			accountExpired:accountExpired,
			accountLocked:accountLocked,
			passwordExpired:passwordExpired,
			esportatoConPasswordInChiaro:esportatoConPasswordInChiaro,
			oAuthIDs:oAuthIDs*.esporta()
		]
	}

	static Utente importa(Map json){
		Utente utente =Utente.findAllByUsername(json.username)[0]
		if(!utente){
		utente = new Utente(
				username:json.username,
				firstName:json.firstName,
				lastName:json.lastName,
				email:json.email,
				sms:json.sms,
				jabber:json.jabber,
				workingTime:json.workingTime,
				//dateCreated:json.dateCreated,
				//lastUpdated:json.lastUpdated,
				avatar:json.avatar,
				enabled:json.enabled,
				accountExpired:json.accountExpired,
				accountLocked:json.accountLocked,
				passwordExpired:json.passwordExpired,
				password:json.password,
				esportatoConPasswordInChiaro:json.esportatoConPasswordInChiaro
				)
		json.oAuthIDs.each{utenteCreato.oAuthIDs.add(new OAuthID(it))}
		}
		//utenteCreato.save(flush:true)
		return utente
	}

	Set<Ruolo> getAuthorities() {
		UtenteRuolo.findAllByUtente(this).collect { it.ruolo }
	}

	def beforeInsert() {
		if (esportatoConPasswordInChiaro) {
			encodePassword()
			esportatoConPasswordInChiaro = false
			log.info("Cripto la password per l'utente (INSERT) "+username)
		} else {
			esportatoConPasswordInChiaro = false
			log.info("Importo l'utente "+username+" con password criptate")
		}
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
			esportatoConPasswordInChiaro = false
			log.info("Cripto la password per l'utente (UPDATE) "+username)
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	static String ritrovaPassword(String testo) {
		return testo.replace('-crypto-pwd-exported-AR4K-', '')
	}

	String toString() {
		return this.username
	}

	boolean isPictureSet() {
		// TODO Auto-generated method stub
		return false
	}
}
