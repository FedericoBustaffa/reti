import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer {
    public static void main(String[] args) throws Exception {
        Value value = new Value();
        System.out.println(value.get());
        value.set(90);

        FileOutputStream out = new FileOutputStream("value.ser");
        ObjectOutputStream obj_out = new ObjectOutputStream(out);
        obj_out.writeObject(value);
        obj_out.close();

    }
}
