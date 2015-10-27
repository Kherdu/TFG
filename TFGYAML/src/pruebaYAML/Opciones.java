package pruebaYAML;

import java.util.ArrayList;

public class Opciones extends Pregunta
{
	private ArrayList<Integer> solucion;
	private ArrayList<String> opciones;
	
	public Opciones(String enunciado, String tipo) {
		super(enunciado, tipo);
		this.solucion = new ArrayList<Integer>();
		this.opciones = new ArrayList<String>();
	}

	public ArrayList<Integer> getSolucion() {
		return solucion;
	}

	public void setSolucion(ArrayList<Integer> solucion) {
		this.solucion = solucion;
	}

	public ArrayList<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(ArrayList<String> opciones) {
		this.opciones = opciones;
	}
	
}
