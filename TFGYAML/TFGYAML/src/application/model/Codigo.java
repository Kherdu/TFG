package application.model;

import java.util.ArrayList;

public class Codigo extends Pregunta
{
	private String respuesta;

	public Codigo(int numero, String enunciado, String respuesta, String pista) {
		super(numero, enunciado, pista);
		this.respuesta = respuesta;
	}
	
	//respuesta es la funcion a la que llamar, código es lo que escribe el usuario
	public boolean comprueba(String respuesta, String codigo){
		return true;
	}

	@Override
	public void setCorrectas(String[] correctas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}
	
}
