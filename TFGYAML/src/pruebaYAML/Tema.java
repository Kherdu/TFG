package pruebaYAML;

import java.util.ArrayList;

public class Tema {
	private int numero;
	private String titulo;
	private String introduccion;
	private ArrayList<Leccion> lecciones;
	
	public Tema(int numero, String titulo, String introduccion, ArrayList<Leccion> lecciones) {
		this.numero = numero;
		this.titulo = titulo;
		this.introduccion = introduccion;
		this.lecciones = lecciones;
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

	public String getIntroduccion() {
		return introduccion;
	}

	public void setIntroduccion(String introduccion) {
		this.introduccion = introduccion;
	}

	public ArrayList<Leccion> getLecciones() {
		return lecciones;
	}

	public void setLecciones(ArrayList<Leccion> lecciones) {
		this.lecciones = lecciones;
	}
	
	
	
}
