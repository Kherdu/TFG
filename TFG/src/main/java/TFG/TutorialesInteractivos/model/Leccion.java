package TFG.TutorialesInteractivos.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase con los elementos de leccion
 * @author Carlos
 *
 */
public class Leccion 
{
	private int numero; //Numero de la leccion
	private String titulo; //Titulo de la leccion
	private List<Elemento> elementos; //Array con los elementos de la leccion
	private String explicacion; //Introducción de la lección
	

	public Leccion(int numero, String titulo, String explicacion){
		this.numero=numero;
		this.titulo=titulo;
		this.elementos = new ArrayList<Elemento>();
		this.explicacion=explicacion;
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

	public List<Elemento> getElementos() {
		return this.elementos;
	}

	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}
	
	
	/*public void addElemento(Elemento elem)
	{
		this.elementos.add(elem);
	}*/
	
}