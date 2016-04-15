package main.java.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Pregunta de tipo opciones
 * @author Carlos
 *
 */
public class Opciones extends Pregunta<ArrayList<Integer>> {
	private List<String> opciones; //Lista de las opciones a la pregunta
	private Boolean multi; //La pregunta es multipocion o no
	
	
	public Opciones(int numero, String enunciado, String pista) {
		super(numero, enunciado, pista, new ArrayList());
		// this.solucion = new ArrayList<Integer>();
		this.opciones = new ArrayList<String>();
	}

	public ArrayList<Integer> getSolucion() {
		return solucion;
	}


	public List<String> getOpciones() {
		return opciones;
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		this.opciones=opciones;

	}

	public boolean corrige(ArrayList<Integer> respuesta, Tema tema) {
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

	@Override
	public void setSolucion(ArrayList<Integer> correctasAux) {
		this.solucion = correctasAux;
		
	}

	@Override
	public void setTexto(String explicacion) {
		// TODO Auto-generated method stub
		
	}

		

}
