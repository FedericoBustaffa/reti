
public class PiCalculator implements Runnable {
	
	private double accuracy;
	
	public PiCalculator(double accuracy) {
		this.accuracy = accuracy;
	}
	
	public void run() {
		double pi = 0.0; // valore del pi greco
		int c = 1; // divisore nella serie di Gregory-Leibniz
		int i = 0; // indice di iterazione
		
		/*
		Prendo il valore assoluto poiché la serie alterna valori maggiori a valori minori di Math.PI
		dunque la sottrazione in certi casi avrebbe valore negativo e farebbe terminare scorrettamente il calcolo.
		Le condizioni di uscita dal ciclo sono il raggiungimento della precisione richiesta oppure l'interruzione del thread.
		Quest'ultima visibile tramite il metodo "isInterrupted"
		*/
		while(Math.abs(pi - Math.PI) > accuracy && !Thread.currentThread().isInterrupted()) {
			// implementazione della serie
			if(i % 2 == 0) {
				pi += 4.0 / c;
			}
			else {
				pi -= 4.0 / c;
			}
			
			i++; // incremento l'iteratore
			c += 2; // incremento il divisore della serie
		}

		// se il thread viene interrotto prematuramente stampo un messaggio
		if(Thread.currentThread().isInterrupted()) {
			System.out.println("Thread interrotto");
		}
		
		System.out.println(pi);
	}
}
