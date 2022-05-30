import java.util.concurrent.ThreadLocalRandom;

public abstract class Utente implements Runnable {

	protected Tutor tutor;
	protected int k; // numero di volte che l'utente accede al laboratorio
	protected int tempo_riposo;
	protected int tempo_lavoro;

	public Utente(Tutor tutor) {
		this.tutor = tutor;

		ThreadLocalRandom random = ThreadLocalRandom.current();
		k = 1 + random.nextInt(5);
		tempo_lavoro = random.nextInt(1000);
		tempo_riposo = random.nextInt(2000);
	}

	/* richiesta di accesso al laboratorio */
	public abstract void ingresso();

	/* avviso di uscita dal laboratorio */
	public abstract void uscita();

	/* Ogni utente entra k volte e lavora. Dopo ogni ciclo riposa */
	public void run() {
		try {
			for (int i = 0; i < k; i++) {
				this.ingresso();
				Thread.sleep(tempo_lavoro);
				this.uscita();
				Thread.sleep(tempo_riposo);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
