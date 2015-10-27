package pruebaYAML;

public class Codigo extends Pregunta
{
	private String respuesta;

	public Codigo(String enunciado, String tipo, String respuesta) {
		super(enunciado, tipo);
		this.respuesta = respuesta;
	}
	
	public boolean comprueba(String respuesta, String codigo){
		return true;
	}
	
}
