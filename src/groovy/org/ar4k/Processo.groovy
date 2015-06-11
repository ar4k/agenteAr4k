/**
 * Mappa processo in esecuzione
 */

package org.ar4k

class Processo {
	String idProcesso = UUID.randomUUID()
	String descrizione ='Processo aggiornamento AR4K by Rossonet'
	Metodo metodo
	String dati
	List<ProcessoVaso> esecuzioni = []
	
	/**
	 * Esegue il metodo
	 */
	void avvia() {
		log.info("test avvio")
	}
	
	void ferma() {
		log.info("test ferma")
	}
	
	void riavvia() {
		log.info("test riavvia")
	}
	
	void verifica() {
		log.info("test verifica")
	}
}

class ProcessoVaso {
	Vaso vaso
	Integer pid = 0
	String stato = 'inattivo'
}
