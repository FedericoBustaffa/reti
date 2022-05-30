public class Computer {

	private int id;
	private boolean disponibile;

	public Computer(int id) {
		this.id = id;
		disponibile = true;
	}

	public int id() {
		return id;
	}

	public boolean disponibile() {
		return disponibile;
	}

	public void occupa() {
		disponibile = false;
	}

	public void libera() {
		disponibile = true;
	}
}
