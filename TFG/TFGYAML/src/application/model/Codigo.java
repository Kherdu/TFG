package application.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
		File correccion = new File("resources/exec/p.py");
		ProcessBuilder pb = new ProcessBuilder(Utilities.PATH + "python.exe", correccion.getAbsolutePath(), this.solucion, respuesta);
		try {//No estalla pero no hace nada
			System.out.println("lanza el proceso");
		
			Process p = pb.start();
			//pb.command(this.solucion+"("+respuesta+")");
			//pb.command("exit()"); //salir de la consola
			//Map<String, String> env = pb.environment();
	
			boolean errCode = p.waitFor(2, TimeUnit.SECONDS);
			System.out.println("Echo command executed, any errors? " + (errCode ? "Yes" : "No"));
			System.out.println("Echo Output:\n");
			
			InputStream is = p.getInputStream();
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        String line;
             
	        while ((line = br.readLine()) != null) {
	            System.out.println(line);
	        }
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
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
