package application.model;

import java.util.ArrayList;
import java.util.List;

public class Opciones extends Pregunta
{
	private List<Integer> solucion;
	private List<String> opciones;
	
	public Opciones(int numero, String enunciado, String pista) {
		super(numero, enunciado, pista);
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

	@Override
	public void setCorrectas(String[] correctas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}
	
	
}
