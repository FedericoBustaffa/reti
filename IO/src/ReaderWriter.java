import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class ReaderWriter {
	private String path;

	public ReaderWriter(String path) {
		this.path = path;
	}

	public void write(String text) {
		try {
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path)));
			out.write(text);
			out.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Writing error");
		}
	}

	public void read() {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
			System.out.println(in.readLine());
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Reading error");
		}
	}
}
