package application.model;

import java.util.ArrayList;
import java.util.List;

public class Opciones extends Pregunta<List<Integer>> {
	// private List<Integer> solucion;
	private List<String> opciones;
	private Boolean multi;
	public Opciones(int numero, String enunciado, String pista) {
		super(numero, enunciado, pista, new ArrayList());
		// this.solucion = new ArrayList<Integer>();
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
	public void setOpciones(ArrayList<String> opciones) {
		this.opciones=opciones;

	}

	public boolean corrige(List<Integer> respuesta) {
		boolean sol = true;
		int tam = respuesta.size();
		int i = 0;

		if (this.solucion.size() == tam)// Se comprueba que haya el mismo numero
										// de opciones marcadas como opciones
										// correctas
		{
			do {
				if (!this.solucion.contains(respuesta.get(i)))
					sol = false;
				i++;
			} while (sol && i < tam);
		} else
			sol = false;

		return sol;
	}

	@Override
	public void setMulti(Boolean is) {
		this.multi=is;
		
	}

	public Boolean getMulti() {
		return multi;
	}

}
