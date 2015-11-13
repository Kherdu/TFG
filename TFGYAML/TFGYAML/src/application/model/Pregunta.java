package application.model;

import java.util.ArrayList;

public abstract class Pregunta 
{
	protected int numero;
	protected String enunciado;
	
	protected String pista;
	
	public Pregunta(){
		this.numero=0;
		this.enunciado=null;
	}
	
	public Pregunta(int numero, String enunciado,  String pista) {
		super();
		this.numero = numero;
		this.enunciado = enunciado;
		
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

	public abstract void setCorrectas(String[] correctas);

	public abstract void setOpciones(ArrayList<String> opciones);
	
	
}
