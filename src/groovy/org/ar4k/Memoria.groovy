/**
 * Per lo scambio di dati nel contesto tra Memi, Interfacce e Utenti 
 *
 *
 */
package org.ar4k

class Memoria {
	String idMemoria = UUID.randomUUID()
	String etichetta = 'Locazione di Memoria nel Contesto'
	String descrizione ='Locazione di memoria in un contesto AR4K by Rossonet'
	String tipo = 'directory' // da verificare le varie opzioni cloud/camel/s3 per database con database ecc con storico
	List<AccessoMeme> memi = []
	List<AccessoInterfaccia> interfacce = []
	List<AccessoRuolo> ruoli = []
	
	// Quando non c'è incrocio
	String accessoPredefinitoMemi = 'ALL'
	String accessoPredefinitoInterfaccia = 'ALL'
	String accessoPredefinitoRuolo = 'ALL'

	def esporta() {
		return [
			idMemoria:idMemoria,
			etichetta:etichetta,
			descrizione:descrizione,
		]
	}
}

class AccessoMeme {
	Meme meme
	String accesso = 'ALL'
}

class AccessoInterfaccia {
	Interfaccia interfaccia
	String accesso = 'ALL'
}

class AccessoRuolo {
	Ruolo ruolo
	String accesso = 'ALL'
}