import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;

public class FileChannelBufferRW {

    private FileOutputStream fout;
    private FileInputStream fin;
    private FileChannel fc;
    private ByteBuffer buffer;

    public FileChannelBufferRW(int capacity) {
        buffer = ByteBuffer.allocate(capacity);
    }

    public String read(File file) {
        try {
            fin = new FileInputStream(file);
            fc = fin.getChannel();

            buffer.clear();
            fc.read(buffer);
            buffer.flip();

            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] b = buffer.array();

        return new String(b, 0, buffer.limit());
    }

    public void write(File file, String s) {
        try {
            fout = new FileOutputStream(file);
            fc = fout.getChannel();

            buffer.clear();
            byte[] b = s.getBytes();
            for (int i = 0; i < b.length; i++) {
                buffer.put(b[i]);
            }
            buffer.flip();
            fc.write(buffer);

            fout.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        FileChannelBufferRW rw = new FileChannelBufferRW(256);
        File file = new File("../file/file.txt");

        Scanner scanner = new Scanner(System.in);
        String s;
        while (!(s = scanner.nextLine()).equals("fine")) {
            rw.write(file, s);
            System.out.println(rw.read(file));
        }
        scanner.close();
    }
}
