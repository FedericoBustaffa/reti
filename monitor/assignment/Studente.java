
public class Studente extends Utente {

	private int id_pc; // id del pc assegnato all'utente

	public Studente(Tutor tutor) {
		super(tutor);
		id_pc = -1;
	}

	/**
	 * assegnamento del pc in posizione id_pc
	 * 
	 * @param id_pc
	 */
	public void assegnaPC(int id_pc) {
		this.id_pc = id_pc;
	}

	/**
	 * ritorna l'id del pc assegnato
	 * 
	 * @return id_pc
	 */
	public int idPC() {
		return id_pc;
	}

	/* richiesta di ingresso al tutor */
	public void ingresso() {
		tutor.richiesta(this);
	}

	/* avviso d'uscita al tutor */
	public void uscita() {
		tutor.rilascio(this);
	}
}
