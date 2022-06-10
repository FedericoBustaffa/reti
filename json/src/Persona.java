import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Persona {

	private String nome;
	private String cognome;

	@JsonCreator
	public Persona(@JsonProperty("nome") String nome, @JsonProperty("cognome") String cognome) {
		this.nome = nome;
		this.cognome = cognome;
	}

	public String nome() {
		return nome;
	}

	public String cognome() {
		return cognome;
	}
}
