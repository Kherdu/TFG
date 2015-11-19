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
	
	public boolean corrige(List<Integer> marcadas)
	{
		boolean sol = true;
		int tam = marcadas.size();
		int i = 0;
		
		if (i == tam)//Se comprueba que haya el mismo numero de opciones marcadas como opciones correctas
		{
			do{
				if(!this.solucion.contains(marcadas.get(i)))
					sol = false;
				i++;
			}while(sol && i<tam);
		}
		
		return sol;
	}
	
	
}
