import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) throws Exception {
        InputStream in = new FileInputStream("file.txt");
        OutputStream out = new FileOutputStream("copy.txt");

        try (BufferedInputStream buf_in = new BufferedInputStream(in);
                OutputStream buf_out = new BufferedOutputStream(out)) {

            int c;
            long start = System.nanoTime();
            while ((c = buf_in.read()) != -1) {
                buf_out.write(c);
            }
            long end = System.nanoTime();

            System.out.println(end - start);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } finally {
            in.close();
            out.close();
        }
    }
}
