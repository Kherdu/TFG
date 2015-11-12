package pruebaYAML;

public class Pregunta 
{
	protected String enunciado;
	protected String tipo;
	protected String pista;
	
	public Pregunta(String enunciado, String tipo, String pista) {
		super();
		this.enunciado = enunciado;
		this.tipo = tipo;
		this.pista = pista;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getPista() {
		return pista;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}
	
	
	
}
