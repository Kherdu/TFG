package main.java.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import main.java.controller.Controller;

/**
 * Pregunta de tipo Codigo
 * 
 * @author Carlos
 *
 */
public class Codigo extends Pregunta<String> {
	
	private Correction c;
	public Codigo(int numero, String enunciado, String pista, String solucion) {
		super(numero, enunciado, pista, solucion);
		Correction c;
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
		JSONParser jsonParser = new JSONParser();
		respuesta = respuesta.replace("\"", "'");
		this.c = new Correction("" , null );
		boolean answer = false;

		try {
			File temp = File.createTempFile("json_Data", null);
			String nombre = temp.getName();
			//String pathprueba= "C:/python34/python.exe";
			ProcessBuilder pb = new ProcessBuilder(Controller.executable, cor, this.solucion, respuesta, nombre);
			Process p = pb.start();
			InputStream is = p.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;

			while ((line = br.readLine()) != null) {
				// esto es para la salida de texto de python... en principio
				// no hace falta
				System.out.println(line);
			}
			boolean errCode = p.waitFor(2, TimeUnit.SECONDS);
			if (!errCode) { // si ocurre esto es que el python está mal escrito,
							// o bucle infinito
				System.out.println("yo no he sido, ha sido ->python<-, o bien bucle infinito");
				// TODO añadir aviso visual
			} else {

				

				int salida = p.exitValue();
				// SOLO EN CASO
				// DE ERROR DE LA FUNCION CORRECTORA
				// DEVOLVER UN VALOR DISTINTO DE 0,
				// en cualquier otro caso devolver 0, en
				// caso de error de la función correctora se
				// mostrará una excepción de java
				// en caso de 0, devolver json o similar,
				// diciendo el error y demás
				switch (salida) {

				case 1: {// si devuelve 1 no es nuestro problema
					c.setMessage("No compila");
					break;
				}

				case 0: {
					// comprobar si están vacios
					FileReader fileReader = new FileReader(temp);
					JSONObject json = (JSONObject) jsonParser.parse(fileReader);
					Boolean correct = (Boolean) json.get("isCorrect");
					if (correct != null) {
						if (correct) {
							answer = true;
							// solo si ocurre esto devuelve
							// true, en cualquier otro caso
							// tiene que mirar qué pasa.
							// si se pueden meter mas cosas en la
							// corrección meterlas
						} else {
						 
							String error = (String) json.get("typeError");
							if (error != null){
								c.setMessage(error);
							}
							@SuppressWarnings("unchecked")
							List<String> hints = (List<String>) json.get("Hints");
							if (hints != null){
								c.setHints(hints);
							}
							
						}
						fileReader.close();
						break;
					}
				}

				}
				temp.delete();

			}
		} catch (IOException e) {
			// TODO mensaje de que peta el archivo
		} catch (InterruptedException e) {
			// TODO proceso interrumpido
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return answer;
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
	public Correction getCorrection() {
		return c;
	}

}
