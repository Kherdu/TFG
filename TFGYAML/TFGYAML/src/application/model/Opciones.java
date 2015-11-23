package application.model;


import java.util.ArrayList;
import java.util.List;

public class Opciones extends Pregunta<List<Integer>>
{
	//private List<Integer> solucion;
	private List<String> opciones;
	
	public Opciones(int numero, String enunciado, String pista) {
		super(numero, enunciado, pista, new ArrayList());
		//this.solucion = new ArrayList<Integer>();
		this.opciones = new ArrayList<String>();
	}

	public List<Integer> getSolucion() {
		return solucion;
	}

	public List<String> getOpciones() {
		return opciones;
	}

	@Override
	public void setSolucion(List<Integer> solucion) {
		// TODO Auto-generated method stub
		this.solucion = solucion;
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean corrige(List<Integer> respuesta)
	{
		boolean sol = true;
		int tam = respuesta.size();
		int i = 0;
		
		if (this.solucion.size() == tam)//Se comprueba que haya el mismo numero de opciones marcadas como opciones correctas
		{
			do{
				if(!this.solucion.contains(respuesta.get(i)))
					sol = false;
				i++;
			}while(sol && i<tam);
		}
		else
			sol = false;
		
		return sol;
	}

	
	
	
}
