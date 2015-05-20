/**
 * Mappa processo in esecuzione
 */

package org.ar4k

class Processo {
	String idProcesso = UUID.randomUUID()
	String descrizione ='Processo aggiornamento AR4K by Rossonet'
	Metodo metodo
	List<ProcessoVaso> esecuzioni = []
}

class ProcessoVaso {
	Vaso vaso
	Integer pid = 0
	String stato = 'inattivo'
}
