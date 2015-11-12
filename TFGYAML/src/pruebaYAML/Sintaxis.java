package pruebaYAML;

import java.util.ArrayList;

public class Sintaxis extends Pregunta
{
	private String sintaxis;
	
	public Sintaxis(int numero, String enunciado, String pista, String sintaxis) {
		super(numero, enunciado, pista);
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

	
	
	
}
