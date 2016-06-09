package TFG.TutorialesInteractivos.model;

import java.util.ArrayList;

/**
 * Clase abstracta que representa los Componentes de una lección
 * 
 * @authors Carlos, Rafa
 *
 */
public abstract class Elemento {
	
	protected String texto; //Enunciado en preguntas o explicacion en explicacion
	
	public Elemento(String texto)
	{
		this.texto = texto;
	}
	
	public String getTexto(){
		return this.texto;
	}

	
	public abstract void setOpciones(ArrayList<String> opc);

	public abstract void setMulti(Boolean is);

	public abstract void setSolucion(ArrayList<Integer> correctasAux);

	public abstract void setTexto(String explicacion);

	public abstract String getPista();

	
}
