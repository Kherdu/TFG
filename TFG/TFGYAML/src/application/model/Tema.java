package application.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Tema con el que se trabaja
 * @author Carlos
 *
 */
public class Tema {
	private int numero; //Numero del tema
	private String archivo; //Nombre del archivo de correccion
	private String titulo; //Titulo del tema
	private String introduccion; //Introduccion del Tema
	private List <Leccion> lecciones; //Lista de las lecciones que componen el tema
	
	public Tema(int numero, String titulo, String introduccion, String archivo) {
		this.numero = numero;
		this.titulo = titulo;
		this.archivo = archivo;
		this.introduccion = introduccion;
		this.lecciones = new ArrayList<Leccion>();
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
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

	/**
	 * Devuelve un array con solo el nombre de las lecciones
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getNameLecciones() {
		//devuelve arraylist de nombres de lecciones
		ArrayList<String> names= new ArrayList<String>();
		for (Leccion l: lecciones){
			names.add(l.getTitulo());
		}
		return names;
	}

	public ArrayList<Elemento> getLeccion(String selectedItem) {
		
		int i = 0;
		while (!this.lecciones.get(i).getTitulo().equals(selectedItem))
			i++;
		return (ArrayList<Elemento>) this.lecciones.get(i).getElementos();
	}


	
	
	
}
