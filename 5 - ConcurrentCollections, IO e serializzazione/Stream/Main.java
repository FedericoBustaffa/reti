import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {

		try (FileInputStream input_file = new FileInputStream("./Main.java");
				FileOutputStream output_file = new FileOutputStream("./copy.java");) {

			int b;
			while ((b = input_file.read()) != -1) {
				output_file.write(b);
			}
		}

	}
}