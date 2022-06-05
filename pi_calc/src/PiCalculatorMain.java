
public class PiCalculatorMain {

	public static void main(String[] args) {

		// prendo l'input da riga di comando e lo converto in double e int
		double accuracy = Double.parseDouble(args[0]); // precisione
		int waiting_time = Integer.parseInt(args[1]); // tempo di timeout in millisecondi

		PiCalculator calc = new PiCalculator(accuracy);
		Thread t = new Thread(calc);
		t.start();

		try {
			// l'esecuzione del main procede se scade il tempo contenuto in "waiting_time"
			t.join(waiting_time);
		} catch (InterruptedException e) {
			System.out.println(e.toString());
		}
		// interruzione del thread calcolatore
		t.interrupt();
	}

}
