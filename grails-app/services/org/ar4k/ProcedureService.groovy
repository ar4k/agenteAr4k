package org.ar4k

import org.codehaus.groovy.grails.commons.GrailsApplication

import grails.transaction.Transactional

@Transactional
class ProcedureService {

	GrailsApplication grailsApplication
	static expose = ['jmx']

	def creaDemoUtente(){
		if ( grailsApplication.config.demoUser.utente != '' && grailsApplication.config.demoUser.password != '' ) {
			def adminRole = new Ruolo(authority: 'ROLE_ADMIN').save(flush: true)
			def userRole = new Ruolo(authority: 'ROLE_USER').save(flush: true)
			def goRole = new Ruolo(authority: 'ROLE_MASTER').save(flush: true)
			def registraRole = new Ruolo(authority: 'ROLE_REGISTRATO').save(flush: true)

			def testUser = new Utente(username: grailsApplication.config.demoUser.utente, password: grailsApplication.config.demoUser.password)
			testUser.save(flush: true)

			UtenteRuolo.create testUser, userRole, true
			UtenteRuolo.create testUser, adminRole, true
			UtenteRuolo.create testUser, goRole, true
			UtenteRuolo.create testUser, registraRole, true

			assert Utente.count() > 0
			assert Ruolo.count() > 3
			assert UtenteRuolo.count() > 3
		}
	}
}
