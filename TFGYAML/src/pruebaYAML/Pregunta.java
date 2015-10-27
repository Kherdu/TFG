package pruebaYAML;

public class Pregunta 
{
	protected String enunciado;
	protected String tipo;
	
	public Pregunta(String enunciado, String tipo) {
		super();
		this.enunciado = enunciado;
		this.tipo = tipo;
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
	
	
	
}
