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

import application.Main;
import application.controller.Controller;

/**
 * Pregunta de tipo Codigo
 * 
 * @author Carlos
 *
 */
public class Codigo extends Pregunta<String> {
	public Codigo(int numero, String enunciado, String pista, String solucion) {
		super(numero, enunciado, pista, solucion);

	}

	// respuesta es la funcion a la que llamar, código es lo que escribe el
	// usuario
	public boolean comprueba(String respuesta, String codigo) {
		return true;
	}

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean corrige(String respuesta, Tema tema) {
		File correccion = new File(tema.getArchivo());
		String cor = correccion.getAbsolutePath();
		//metodo de la clase file createTempFile para crear el temporal
		respuesta = respuesta.replace("\"", "'");
		ProcessBuilder pb = new ProcessBuilder(Controller.path, cor, this.solucion, respuesta);
		try {// No estalla pero no hace nada
			System.out.println("lanza el proceso");

			Process p = pb.start();
			// pb.command(this.solucion+"("+respuesta+")");
			// pb.command("exit()"); //salir de la consola
			// Map<String, String> env = pb.environment();

			boolean errCode = p.waitFor(2, TimeUnit.SECONDS);
			//si errcode true mirar salida de python json
			//si es false
			System.out.println("Echo command executed, any errors? " + (errCode ? "No" : "Yes"));
			System.out.println("Echo Output:\n");

			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			int salida = p.exitValue();
			System.out.print("Java dice: ");
			switch (salida) { // a definir mas adelante, se pueden añadir mas
							  // valores y tener otras salidas, SOLO EN CASO DE ERROR DE LA FUNCION CORRECTORA DEVOLVER UN VALOR DISTINTO DE 0,
							  // en cualquier otro caso devolver 0, en caso de error de la función correctora se mostrará una excepción de java
							  // en caso de 0, devolver json o similar, diciendo el error y demás
			
			case 1: {//si devuelve 1 no es nuestro problema
				System.out.println("Variable generada pero sin valor correcto");
				break;
			}
			case -1: {//esto sobra
				System.out.println("No existe la variable solicitada");
				break;
			}
			case 0: {//json en un fichero temporal a parte, a definir la estructura, leer tempfile
				System.out.println("Todo esta bien");
				break;
			}
			}

			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			return salida ==0;
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
		
		//borrar tempfile
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
