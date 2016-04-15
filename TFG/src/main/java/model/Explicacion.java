package main.java.model;

import java.util.ArrayList;

/**
 * Clase con el elemento Explicación
 * @author Carlos
 *
 */
public class Explicacion extends Elemento{
	
	public Explicacion(String texto)
	{
		super(texto);
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

	@Override
	public void setTexto(String explicacion) {
		this.texto = explicacion;
		
	}

	@Override
	public String getPista() {
		// TODO Auto-generated method stub
		return null;
	}
}
