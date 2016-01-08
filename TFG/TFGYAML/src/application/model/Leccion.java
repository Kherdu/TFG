package application.model;

import java.util.List;
import java.util.ArrayList;

public class Leccion 
{
	private int numero;
	private String titulo;
	private List<Elemento> elementos;
	//private String explicacion;
	//private List<Pregunta> preguntas;

	public Leccion(int numero, String titulo){//, String explicacion){
		this.numero=numero;
		this.titulo=titulo;
		this.elementos = new ArrayList<Elemento>();
		//this.explicacion=explicacion;
		//this.preguntas= new ArrayList<Pregunta>();
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

	/*public String getExplicacion() {
		return explicacion;
	}

	public void setExplicacion(String explicacion) {
		this.explicacion = explicacion;
	}
*/
	public List<Elemento> getElementos() {
		return this.elementos;
	}

	public void setElementos(List<Elemento> elementos) {
		this.elementos = elementos;
	}
	
	public void addElemento(Elemento elem)
	{
		this.elementos.add(elem);
	}
	
}