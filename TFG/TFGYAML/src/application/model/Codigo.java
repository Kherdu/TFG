package application.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
		ProcessBuilder pb = new ProcessBuilder(Utilities.PATH + "python.exe", "\\resources\\exec\\tema1.py");
		try {//No estalla pero no hace nada
			Process p = pb.start();
			InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
	        System.out.printf("Output of running is:\n");
	               
	        while ((line = br.readLine()) != null) {
	            System.out.println(line);
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
