/**
 * Gestisce la registrazione e la console utenti
 */

package org.ar4k.secure

class AuthController {
	def userManager() {
		[linkManager:g.resource(dir:'user')]
	}

	def registerUser() {
		[linkManager:g.resource(dir:'register')]
	}
}
