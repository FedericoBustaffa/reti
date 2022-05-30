public class Person implements Runnable {
	
	private int num;

	public Person(int num) {
		this.num = num;
	}

	public void run() {
		try {
			System.out.printf("Numero %d allo sportello\n", num);
			Thread.sleep((long)Math.random() * 1000);
			System.out.printf("Servito il numero %d\n", num);
		} catch (InterruptedException e) {
			System.out.println("Interrupt Excepition");
		}
	}
}
