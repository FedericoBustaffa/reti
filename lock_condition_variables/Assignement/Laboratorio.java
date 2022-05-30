import java.util.concurrent.ThreadLocalRandom;

public class Laboratorio {

	/* Il laboratorio dispone di 20 comuter di cui uno per i tesisti */
	private Computer[] computers;
	private int posti_occupati = 0;
	private int id_pc_tesisti;

	public Laboratorio() {
		computers = new Computer[20];
		for (int i = 0; i < 20; i++) {
			computers[i] = new Computer(i);
		}

		/* il computer dei tesisti viene scelto casualmente a inizio programma */
		ThreadLocalRandom random = ThreadLocalRandom.current();
		id_pc_tesisti = random.nextInt(20);
	}

	public int postiOccupati() {
		return posti_occupati;
	}

	public boolean Pieno() {
		return posti_occupati == 20;
	}

	public boolean Vuoto() {
		return posti_occupati == 0;
	}

	public boolean pcTesiLibero() {
		return computers[id_pc_tesisti].disponibile();
	}

	public int idPCTesisti() {
		return id_pc_tesisti;
	}

	/* ritorna l'indice relativo al primo computer libero nel laboratorio */
	public int primoLibero() {
		for (int i = 0; i < 20; i++) {
			if (computers[i].disponibile()) {
				return i;
			}
		}

		return -1;
	}

	/**
	 * Il pc in posizione id viene occupato
	 * 
	 * @param id
	 */
	public void assegna(int id) {
		posti_occupati++;
		computers[id].occupa();
	}

	/**
	 * il pc in posizione id viene liberato
	 * 
	 * @param id
	 */
	public void rilascia(int id) {
		posti_occupati--;
		computers[id].libera();
	}

	/* tutti i pc vengono occupati */
	public void assegnaTutto() {
		posti_occupati += 20;
		for (int i = 0; i < 20; i++) {
			computers[i].occupa();
		}
	}

	/* tutti i pc vengono liberati */
	public void rilasciaTutto() {
		posti_occupati -= 20;
		for (int i = 0; i < 20; i++) {
			computers[i].libera();
		}
	}
}
