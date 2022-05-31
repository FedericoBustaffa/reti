public class MainClass {
	public static void main(String[] args) {

		if (args.length != 3) {
			System.out.println("USAGE: java MainClass <num_studenti> <num_tesisti> <num_professori>");
			return;
		}

		// numero utenti
		int n_studenti = Integer.parseInt(args[0]);
		int n_tesisti = Integer.parseInt(args[1]);
		int n_professori = Integer.parseInt(args[2]);

		// Tutor
		Tutor tutor = new Tutor();

		// Creo un thread per ogni utente
		Thread[] studenti = new Thread[n_studenti];
		for (int i = 0; i < n_studenti; i++) {
			studenti[i] = new Thread(new Studente(tutor));
			studenti[i].start();
		}

		Thread[] tesisti = new Thread[n_tesisti];
		for (int i = 0; i < n_tesisti; i++) {
			tesisti[i] = new Thread(new Tesista(tutor));
			tesisti[i].start();
		}

		Thread[] professori = new Thread[n_professori];
		for (int i = 0; i < n_professori; i++) {
			professori[i] = new Thread(new Professore(tutor));
			professori[i].start();
		}

		// JOIN
		for (int i = 0; i < n_studenti; i++) {
			try {
				studenti[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < n_tesisti; i++) {
			try {
				tesisti[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (int i = 0; i < n_professori; i++) {
			try {
				professori[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
