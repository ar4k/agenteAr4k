/**
 * Contesto
 *
 * <p>Un contesto operativo gestisce la correlazione tra vasi, ricettari e utenti</p>
 *
 * <p style="text-justify">
 * Il contesto rappresenta un progetto, un insieme di risorse, utenti e codice che interagiscono.</br>
 * E' compito del contesto esporre tutte le funzioni di coordinamento dei vasi per un progetto e la relativa maschera che parte di default in
 * un contesto locale, scaricando solo i ricettari base.</br>
 * il contesto espone la factory dei dns,QR e risorse di calcolo fornite da Provider.</br>
 * i memi possono associare degli eventi ai contesti.</br>
 * </br>
 * Un contesto parte sempre con un ricettario base che contiene i meme base per la gestione dei suoi eventi.
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Meme
 * @see org.ar4k.Vaso
 * @see org.ar4k.Ricettario
 * @see org.ar4k.Interfaccia
 * @see org.ar4k.Connesione
 * @see org.ar4k.Utente
 */

package org.ar4k


// Classe contesti (per i permessi sui tunnel)
class Contesto {
	/** id univoco contesto */
	String idContesto = UUID.randomUUID()
	/** etichetta contesto */
	String etichetta = ''
	/** descrizione contesto */
	String descrizione ='Contesto operativo AR4K by Rossonet'
	/** campo per link a CRM (progetto)*/
	String idProgetto ='00 - LAB Rossonet'
	/** campo per l'avanzamanto del bootstrap (boot,parametri collegamento ssh,parametri proxy interfaccia,parametri proxy vaso,funzionante,destruzione)*/
	private String statoBootStrap = 'boot'
	/** ricettari a disposizione */
	List<Ricettario> ricettari= []
	/** interfacce collegate */
	List<Interfaccia> interfacce= []
	/** utenti del contesto da importare in configurazione*/
	List<Utente> utenti = []
	/** vasi disponibili nel contesto*/
	List<Vaso> vasi =[]
	/** connessioni tra i vasi disponibili nel contesto*/
	List<Connesione> connessioni =[]
	/** dati operativi situazione oggetti collegati in rete (a livello di porte e socket -mappa anche i dispositivi seriali collegati ai vasi-), reti e vasi,proxy e gw operativi, mappatura discovery*/
	Stato statoRete = null
	Vaso vasoMaster
	
	/** costruttore di classe (responsabile della costruzione di bootstrap del primo vaso)*/
	def Contesto() {
		// Attività di bootstrap
	}
	
	/** ditruttore di classe (utile per la gestione della pulizia dei vasi)*/
	def destroy() {
		// Pulizia dei vasi
	}
	
		
	/** verifica ed eventualmente prova a riavviare tutti gli oggetti collegati*/
	def Verifica() {
		
	}
	
	/** esporta l'intero contesto su un altro vaso*/
	def costruisceVasoMaster() {
		
	}
	
	/** dump oggetto per funzioni di salvataggio*/
	def esporta() {
		return [
	idContesto:idContesto,
	etichetta:etichetta,
	descrizione:descrizione,
	idProgetto:idProgetto,
	ricettari:ricettari,
	interfacce:interfacce,
	utenti:utenti,
	vasi:vasi,
	connessioni:connessioni,
	statoRete:statoRete
			]
	}
}
