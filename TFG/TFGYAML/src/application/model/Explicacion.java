package application.model;

import java.util.ArrayList;

public class Explicacion extends Elemento{
	
	private String texto;
	
	public Explicacion(String texto)
	{
		this.texto = texto;
	}
	
	public void setTexto(String texto)
	{
		this.texto = texto;
	}
	
	public String getTexto()
	{
		return this.texto;
	}
	

	@Override
	public void setOpciones(ArrayList<String> opc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMulti(Boolean is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSolucion(ArrayList<Integer> correctasAux) {
		// TODO Auto-generated method stub
		
	}

}
