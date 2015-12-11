package application.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Pregunta<T> 
{
	protected int numero;
	protected String enunciado;
	protected T solucion;
	protected String pista;
	
	public Pregunta(){
		this.numero=0;
		this.enunciado=null;
	}
	
	public Pregunta(int numero, String enunciado, String pista, T solucion) {
		super();
		this.numero = numero;
		this.enunciado = enunciado;
		this.solucion = solucion;
		this.pista = pista;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
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

	public T getSolucion() {
		return solucion;
	}
	public abstract void setSolucion(T solucion);

	public abstract void setOpciones(ArrayList<String> opciones);
	
	public abstract boolean corrige(T respuesta);

	public abstract void setMulti(Boolean is);
	
	
}
