package pruebaYAML;

import java.util.ArrayList;
import java.util.List;

public class Opciones extends Pregunta
{
	private List<Integer> solucion;
	private List<String> opciones;
	
	public Opciones(String enunciado, String tipo) {
		super(enunciado, tipo);
		this.solucion = new ArrayList<Integer>();
		this.opciones = new ArrayList<String>();
	}

	public List<Integer> getSolucion() {
		return solucion;
	}

	public void setSolucion(List<Integer> solucion) {
		this.solucion = solucion;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
	
}
