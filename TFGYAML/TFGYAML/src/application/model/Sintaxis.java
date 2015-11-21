package application.model;

import java.util.ArrayList;

public class Sintaxis extends Pregunta<String>
{
	private String sintaxis;
	
	public Sintaxis(int numero, String enunciado, String pista, String sintaxis, String solucion) {
		super(numero, enunciado, pista, solucion);
		this.sintaxis = sintaxis;
	}

	@Override
	public void setCorrectas(String[] correctas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean corrige(String respuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
}
