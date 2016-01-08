package application.model;

import java.util.ArrayList;
import java.util.List;

public class Tema {
	private int numero;
	private String titulo;
	private String introduccion;
	private List <Leccion> lecciones;
	
	public Tema(int numero, String titulo, String introduccion) {
		this.numero = numero;
		this.titulo = titulo;
		this.introduccion = introduccion;
		this.lecciones = new ArrayList<Leccion>();
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

	public List<Leccion> getLecciones() {
		return lecciones;
	}

	public void setLecciones(List<Leccion> lecciones) {
		this.lecciones = lecciones;
	}


	
	
	
}
