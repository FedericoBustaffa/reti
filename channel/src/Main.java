import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("../file/file.txt");

            FileOutputStream fout = new FileOutputStream(file);
            FileChannel fc = fout.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            byte[] b = "ciao".getBytes();
            for (int i = 0; i < b.length; i++) {
                buffer.put(b[i]);
            }
            fc.write(buffer);
            System.out.println(buffer.toString());

            FileInputStream fin = new FileInputStream(file);
            fc = fin.getChannel();
            fc.read(buffer);
            System.out.println(new String(buffer.array(), 0, buffer.array().length));
            System.out.println(buffer.toString());

            fc = fout.getChannel();
            buffer.clear();
            buffer.put("hello".getBytes());
            String msg = new String(buffer.array(), 0, buffer.array().length);
            System.out.println(msg);
            fc.write(buffer);
            System.out.println(buffer.toString());

            fout.close();
            fin.close();
            fc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
