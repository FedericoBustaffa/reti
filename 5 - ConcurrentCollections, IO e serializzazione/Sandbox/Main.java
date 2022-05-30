import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

class Main {
	public static void main(String[] args) {
		Counter counter = new Counter(10);

		counter.increment();

		try (FileOutputStream fos = new FileOutputStream("counter.ser")) {
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeUnshared(counter);
			counter.increment();
			out.writeUnshared(counter);
		} catch (IOException ex) {
			System.out.println("IOException");
		}

		try (FileInputStream fis = new FileInputStream("counter.ser")) {
			ObjectInputStream in = new ObjectInputStream(fis);
			Counter counter2 = (Counter) in.readUnshared();
			Counter counter3 = (Counter) in.readUnshared();

			System.out.println(counter2.getCounter());
			System.out.println(counter3.getCounter());
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
	}
}
