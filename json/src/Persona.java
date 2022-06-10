
public class Persona {

	private String nome;
	private String cognome;

	public Persona() {
		this.nome = null;
		this.cognome = null;
	}

	public Persona(String nome, String cognome) {
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
