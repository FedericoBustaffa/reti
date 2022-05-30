public class Tutor {

	private Laboratorio laboratorio;

	// variabili di monitoraggio
	private int studenti_a_lavoro = 0;
	private int tesisti_a_lavoro = 0;
	private int prof_a_lavoro = 0;
	private int studenti_in_attesa = 0;
	private int tesisti_in_attesa = 0;
	private int prof_in_attesa = 0;

	public Tutor() {
		laboratorio = new Laboratorio();
	}

	/*
	 * stampe di monitoraggio del laboratorio
	 */
	public void statistiche() {
		System.out.printf("Studenti in attesa: %d\n", studenti_in_attesa);
		System.out.printf("Tesisti in attesa: %d\n", tesisti_in_attesa);
		System.out.printf("Professori in attesa: %d\n", prof_in_attesa);

		System.out.println("PC tesisti libero: " + laboratorio.pcTesiLibero());
		System.out.printf("Studenti a lavoro: %d\n", studenti_a_lavoro);
		System.out.printf("Tesisti a lavoro: %d\n", tesisti_a_lavoro);
		System.out.printf("Professori a lavoro: %d\n\n", prof_a_lavoro);
	}

	/**
	 * Lo studente richiede l'accesso. Se le condizioni di priorità non sono
	 * soddisfatte rimane in attesa. Una volta entrato svolge il lavoro. Lo studente
	 * ha priorità minima quindi se ci sono professori in attesa o tesisti in attesa
	 * sul computer per la tesi, lo studente verrà messo in attesa.
	 * 
	 * @param studente
	 */
	public void richiesta(Studente studente) {
		synchronized (this) {
			studenti_in_attesa++;
			this.statistiche();
			while (laboratorio.Pieno() || prof_in_attesa != 0 || (laboratorio.postiOccupati() == 19
					&& tesisti_in_attesa != 0 && laboratorio.primoLibero() == laboratorio.idPCTesisti())) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			// assegnazione pc
			studenti_in_attesa--;
			studenti_a_lavoro++;
			studente.assegnaPC(laboratorio.primoLibero());
			laboratorio.assegna(studente.idPC());
			this.statistiche();
		}
	}

	/**
	 * Lo studente rilascia il computer che gli è stato assegnato e risvegli tutti i
	 * thread in attesa
	 * 
	 * @param studente
	 */
	public void rilascio(Studente studente) {
		synchronized (this) {
			// rilascio pc
			studenti_a_lavoro--;
			laboratorio.rilascia(studente.idPC());
			this.statistiche();

			this.notifyAll();
		}
	}

	/**
	 * Il tesista controlla che non ci siano prof in attesa e che il pc per fare la
	 * tesi sia disponibile. In tal caso può accedere al laboratorio.
	 * 
	 * @param tesista
	 */
	public void richiesta(Tesista tesista) {
		synchronized (this) {

			tesisti_in_attesa++;
			this.statistiche();
			while (laboratorio.Pieno() || prof_in_attesa != 0 || !laboratorio.pcTesiLibero()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			tesisti_in_attesa--;
			tesisti_a_lavoro++;
			laboratorio.assegna(laboratorio.idPCTesisti());
			this.statistiche();
		}
	}

	/**
	 * Il tesista rilascia il pc per la tesi e risveglia tutti gli altri utenti in
	 * attesa
	 * 
	 * @param tesista
	 */
	public void rilascio(Tesista tesista) {
		synchronized (this) {
			// rilascio
			tesisti_a_lavoro--;
			laboratorio.rilascia(laboratorio.idPCTesisti());
			this.statistiche();

			this.notifyAll();
		}
	}

	/**
	 * Il professore ha come unica condizione che il laboratorio sia completamente
	 * vuoto per entrare. Una volta dentro occupa tutti i pc.
	 * 
	 * @param professore
	 */
	public void richiesta(Professore professore) {
		synchronized (this) {
			prof_in_attesa++;
			this.statistiche();
			while (!laboratorio.Vuoto()) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// assegnazione
		prof_in_attesa--;
		prof_a_lavoro++;
		laboratorio.assegnaTutto();
		this.statistiche();
	}

	/**
	 * il professore rilascia l'intero laboratorio
	 * 
	 * @param professore
	 */
	public void rilascio(Professore professore) {
		synchronized (this) {
			// rilascio
			prof_a_lavoro--;
			laboratorio.rilasciaTutto();
			this.statistiche();

			this.notifyAll();
		}
	}
}
