package pruebaYAML;

import java.util.ArrayList;
import java.util.Map;

public class Tema {
	private int numero;
	private String titulo;
	private String introduccion;
	private Map<String, Leccion> lecciones;
	
	public Tema(int numero, String titulo, String introduccion, Map<String,Leccion> lecciones) {
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

	public Map<String, Leccion> getLecciones() {
		return lecciones;
	}

	public void setLecciones(Map<String, Leccion> lecciones) {
		this.lecciones = lecciones;
	}

	
	
	
}
