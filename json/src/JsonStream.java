import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonStream {
	public static void main(String[] args) {
		JsonFactory factory = new JsonFactory();
		File file = new File("json_files/file.json");
		ObjectMapper mapper = new ObjectMapper();
		mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
		mapper.enable(SerializationFeature.INDENT_OUTPUT);

		Persona fede = new Persona("Federico", "Bustaffa");
		Persona mario = new Persona("Mario", "Rossi");
		try (JsonGenerator generator = factory.createGenerator(file, JsonEncoding.UTF8)) {
			generator.setCodec(mapper);
			generator.useDefaultPrettyPrinter();

			generator.writeStartArray(); // parentesi quadra aperta
			generator.writeObject(fede);
			generator.writeObject(mario);
			generator.writeEndArray(); // parentesi quadra chiusa
		} catch (IOException e) {
			e.printStackTrace();
		}

		try (JsonParser parser = factory.createParser(file)) {
			parser.setCodec(mapper);
			if (parser.nextToken() != JsonToken.START_ARRAY) {
				System.out.println("Il file Json non contiene un array");
			} else {
				while (parser.nextToken() == JsonToken.START_OBJECT) {
					Persona p = parser.readValueAs(Persona.class);
					System.out.printf("****\nNome: %s\nCognome: %s\n", p.nome(), p.cognome());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
