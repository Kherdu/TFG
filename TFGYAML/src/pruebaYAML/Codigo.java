package pruebaYAML;

public class Codigo extends Pregunta
{
	private String respuesta;

	public Codigo(String enunciado, String tipo, String respuesta, String pista) {
		super(enunciado, tipo, pista);
		this.respuesta = respuesta;
	}
	
	public boolean comprueba(String respuesta, String codigo){
		return true;
	}
	
}
