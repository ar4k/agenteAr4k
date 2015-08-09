/**
 * Contesto
 *
 * <p>Un contesto operativo gestisce la correlazione tra vasi, ricettari e utenti</p>
 *
 * <p style="text-justify">
 * Il contesto rappresenta un progetto, un insieme di risorse, utenti e codice che interagiscono.</br>
 * E' compito del contesto esporre tutte le funzioni di coordinamento dei vasi per un progetto e la relativa maschera che parte di default in
 * un contesto locale, scaricando solo i ricettari base.</br>
 * il contesto espone la factory dei dns, QR e risorse di calcolo fornite da Provider.</br>
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

class Contesto {
	/** id univoco contesto */
	String idContesto = UUID.randomUUID()
	/** etichetta contesto */
	String etichetta = ''
	/** descrizione contesto */
	String descrizione ='Contesto operativo AR4K by Rossonet'
	/** campo per link a CRM (progetto)*/
	String idProgetto ='00 - LAB Rossonet'
	/** campo per l'avanzamanto del bootstrap (nascita,vita,virus,morte)*/
	private String statoBootStrap = 'nascita'
	/** se salva il contesto su tutti i vasi connessi tranne i disabilitati */
	Boolean clonaOvunque = false
	/** ricettari a disposizione */
	List<Ricettario> ricettari= []
	/** interfacce collegate */
	List<Interfaccia> interfacce= []
	/** utenti del contesto da importare in configurazione*/
	List<UtenteRuolo> utentiRuoli = []
	/** memi attivi nel contesto */
	List<Meme> memi = []
	/** vasi disponibili nel contesto*/
	List<Vaso> vasi =[]
	
	Vaso vasoMaster

	/** ditruttore di classe (utile per la gestione della pulizia dei vasi)*/
	def destroy() {
		// Pulizia dei vasi
	}

	/** aggiunge il vaso master al contesto */
	Boolean configuraMaster(Vaso vaso) {
		vasoMaster = vaso
		vasi.add(vaso)
		return true
	}


	/** verifica ed eventualmente prova a riavviare tutti gli oggetti collegati*/
	Boolean avviaContesto() {
		Boolean risultato = true
		for (Vaso vaso in vasi ) {
			if (!vaso.provaVaso()) risultato = false
			for (Ricettario ricettario in ricettari) {
				if (!vaso.avviaRicettario(ricettario)) risultato = false
			}
		}
		for (Meme meme in memi) {
			if (!meme.verificaAvvia()) risultato = false
		}
		log.info("Importa "+utentiRuoli.size()+" utenti/ruoli")
		for (UtenteRuolo utenteRuolo in utentiRuoli) {
			utenteRuolo.utente.save(flush:true)
			utenteRuolo.ruolo.save(flush:true)
			utenteRuolo.save(flush:true)
		}
		log.info("Importati utenti e ruoli")
		if (risultato) statoBootStrap = 'avviato'
		return risultato
	}

	/** salva il contesto sul nodo master */
	Boolean salva() {
		Boolean risultato = false
		if (clonaOvunque) {
			vasi*.salvaContesto(this)
			risultato = true // da definire
		} else {
			if (vasoMaster.salvaContesto(this)) risultato = true
		}
		return risultato
	}

	/** dump oggetto per funzioni di salvataggio*/
	def esporta() {
		log.info("esporta() il contesto: "+idContesto)
		return [
			idContesto:idContesto,
			etichetta:etichetta,
			descrizione:descrizione,
			idProgetto:idProgetto,
			interfacce:interfacce*.esporta(),
			memi:memi*.esporta(),
			utentiRuoli:utentiRuoli*.esporta(),
			vasi:vasi*.esporta(),
			vasoMaster:vasoMaster.esporta(),
			ricettari:ricettari*.esporta()
		]
	}

	String toString() {
		return etichetta + " su "+vasoMaster+" ("+statoBootStrap+")"
	}
}
