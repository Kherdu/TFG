package application.model;

import java.util.List;
import java.util.ArrayList;

public class Leccion 
{
	private int numero;
	private String titulo;
	private String explicacion;
	private List<Pregunta> preguntas;

	public Leccion(int numero, String titulo, String explicacion){
		this.numero=numero;
		this.titulo=titulo;
		this.explicacion=explicacion;
		this.preguntas= new ArrayList<Pregunta>();
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}

	public List<Pregunta> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<Pregunta> preguntas) {
		this.preguntas = preguntas;
	}
	
}