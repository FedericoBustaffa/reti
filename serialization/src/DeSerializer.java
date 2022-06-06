import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class DeSerializer {
	public static void main(String[] args) throws Exception {
		FileInputStream in = new FileInputStream("value.ser");
		ObjectInputStream obj_in = new ObjectInputStream(in);
		Value value = (Value) obj_in.readObject();
		obj_in.close();

		System.out.println(value.get());
	}
}
