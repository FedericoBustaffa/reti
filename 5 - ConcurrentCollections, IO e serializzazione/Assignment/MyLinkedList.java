import java.util.LinkedList;

public class MyLinkedList {

	private LinkedList<String> list = new LinkedList<String>(); // lista contenente i path delle directories
	private volatile boolean finished = false; // variabile che indica la fine della visita del produttore

	/**
	 * l'aggiunta di una nuova stringa dev'essere fatto in mutua esclusione
	 * 
	 * @param s
	 */
	public void add(String s) {
		synchronized (this) {
			list.add(s);
			this.notify();
		}
	}

	/**
	 * l'estrazione dalla lista ha una fase di attesa nel caso in cui la lista sia
	 * vuota o la visita non sia ancora finita.
	 * 
	 * @return una stringa se la lista non è vuota oppure null se la visita termina
	 *         e la lista è vuota
	 */
	public String pop() {
		String s = null;
		synchronized (this) {
			while (list.isEmpty() && !finished) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					System.out.println(e.getStackTrace());
				}
			}

			if (!list.isEmpty()) {
				s = list.pop();
			}
		}

		return s;
	}

	/**
	 * @param index
	 * @return stringa in posizione index
	 */
	public String get(int index) {
		return list.get(index);
	}

	/**
	 * setta la variabile di fine visita a true e risveglia tutti i consumatori in
	 * attesa
	 */
	public void finish() {
		synchronized (this) {
			finished = true;
			this.notifyAll();
		}
	}

	/**
	 * @return il valore di fine visita
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * 
	 * @return se la lista è vuota true, false altrimenti
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

}
