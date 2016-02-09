package application.model;

import java.util.ArrayList;

/**
 * Clase generica del elemento pregunta
 * @author Carlos
 *
 * @param <T>
 */
public abstract class Pregunta<T> extends Elemento
{
	protected int numero; //Numero de la pregunta
	protected T solucion; //Solucion a la pregunta
	protected String pista; //Pista para resolver la pregunta(opcional)
	
	public Pregunta(){
		super(null);
		this.numero=0;
		//this.enunciado=null;
	}
	
	public Pregunta(int numero, String enunciado, String pista, T solucion) {
		super(enunciado);
		this.numero = numero;
		//this.enunciado = enunciado;
		this.solucion = solucion;
		this.pista = pista;
	}

	public String getPista() {
		return pista;
	}

	public void setPista(String pista) {
		this.pista = pista;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	
	public abstract void setSolucion(T solucion);

	public abstract void setOpciones(ArrayList<String> opciones);
	
	public abstract boolean corrige(T respuesta);

	public abstract void setMulti(Boolean is);
	
	
}
