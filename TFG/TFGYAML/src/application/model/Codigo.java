package application.model;

import java.util.ArrayList;

import org.python.core.PyInstance;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;



/**
 * Pregunta de tipo Codigo
 * @author Carlos
 *
 */
public class Codigo extends Pregunta <String>
{
	//private String respuesta;

	public Codigo(int numero, String enunciado, String pista, String solucion) {
		super(numero, enunciado, pista, solucion);
		//this.respuesta = respuesta;
	}
	
	//respuesta es la funcion a la que llamar, código es lo que escribe el usuario
	public boolean comprueba(String respuesta, String codigo){
		return true;
	}


	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean corrige(String respuesta, Tema tema) {
		PythonInterpreter interp = new PythonInterpreter();
		//ejecutamos el archivo python pertinente
		System.out.println("/exec/"+tema.getArchivo());
		interp.execfile("resources/exec/"+tema.getArchivo());
		String t= "Tema"+tema.getNumero();
		PyInstance func = (PyInstance) interp.eval(t);//nombre de clase del .py
		String f= this.solucion+"("+respuesta+")"; //función a ejecutar (nombre y entre paréntesis codigo del usuario)
		func.invoke(f);
		// Obtain the value of an object from the PythonInterpreter
		// and store it
		// into a PyObject.
		String result= (String) func.__getattr__("x").__tojava__(String.class);
		System.out.println(result);
		// execute a function that takes a string and returns a
		// string
//		PyObject someFunc = interp.eval(respuesta);
//		PyObject result = someFunc.__call__(new PyString("Test!"));
//		String realResult = (String) result.__tojava__(String.class);
//		System.out.println(realResult);
		return false;
	}

	@Override
	public void setSolucion(String solucion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMulti(Boolean is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSolucion(ArrayList<Integer> correctasAux) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTexto(String explicacion) {
		// TODO Auto-generated method stub
		
	}
	
}
