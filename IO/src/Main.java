
public class Main {
    public static void main(String[] args) {

        FileReadWrite file_rw = new FileReadWrite("text.txt");
        file_rw.write("Ciao sono Federico");
        file_rw.read();

        BufferedReadWrite buffered_rw = new BufferedReadWrite("text.txt");
        buffered_rw.write("Ciao sono Lucia");
        buffered_rw.read();

        DataReadWrite data_rw = new DataReadWrite("text.txt");
        int l = data_rw.writeString("Ciao sono Ernesto");
        data_rw.readString(l);

        data_rw.writeInt(10);
        data_rw.readInt();
    }
}
