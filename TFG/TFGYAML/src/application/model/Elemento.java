package application.model;

import java.util.ArrayList;

public abstract class Elemento {
	
	public Elemento()
	{
		
	}

	public abstract void setOpciones(ArrayList<String> opc);

	public abstract void setMulti(Boolean is);

	public abstract void setSolucion(ArrayList<Integer> correctasAux);

	public abstract void setTexto(String explicacion);

}
