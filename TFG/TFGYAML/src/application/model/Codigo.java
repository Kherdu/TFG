package application.model;

import java.util.ArrayList;

/**
 * Pregunta de tipo Codigo
 * @author Carlos
 *
 */
public class Codigo extends Pregunta <String>
{
	//private String respuesta;

	public Codigo(int numero, String enunciado, String pista, String solucion) {
		super(numero, enunciado, pista, solucion);
		//this.respuesta = respuesta;
	}
	
	//respuesta es la funcion a la que llamar, código es lo que escribe el usuario
	public boolean comprueba(String respuesta, String codigo){
		return true;
	}


	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean corrige(String respuesta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setSolucion(String solucion) {
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
		// TODO Auto-generated method stub
		
	}
	
}
