import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Mapper {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        Persona p = new Persona("Mario", "Rossi");

        try {
            File file = new File("json_files/file.json");
            mapper.writeValue(file, p);
            System.out.println(mapper.writeValueAsString(p));

            Persona p2 = mapper.readValue(file, Persona.class);
            System.out.printf("Nome: %s\nCognome: %s\n", p2.nome(), p2.cognome());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
