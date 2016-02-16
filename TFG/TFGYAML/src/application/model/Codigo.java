package application.model;

import java.util.ArrayList;

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
	public boolean corrige(String respuesta) {
		PythonInterpreter interp = new PythonInterpreter();
		interp.exec("import sys");
		interp.exec("print sys");
		
		// Set variable values within the PythonInterpreter instance
		interp.set("a", new PyInteger(42));
		interp.exec("print a");
		interp.exec("x = 2+2");

		// Obtain the value of an object from the PythonInterpreter
		// and store it
		// into a PyObject.
		PyObject x = interp.get("x");
		System.out.println("x: " + x);
		// execute a function that takes a string and returns a
		// string
		PyObject someFunc = interp.eval(respuesta);
		PyObject result = someFunc.__call__(new PyString("Test!"));
		String realResult = (String) result.__tojava__(String.class);
		System.out.println(realResult);
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
