
public class Professore extends Utente {

	public Professore(Tutor tutor) {
		super(tutor);
	}

	public void ingresso() {
		tutor.richiesta(this);
	}

	public void uscita() {
		tutor.rilascio(this);
	}
}
