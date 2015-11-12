package pruebaYAML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import javafx.*;

public class Main {

	public static void main(String[] args) {
		/*
		 * Constructor c = new Constructor(Tema.class); TypeDescription
		 * temaDescription = new TypeDescription(Tema.class);
		 * temaDescription.putListPropertyType("Leccion", Leccion.class);
		 * 
		 * Yaml yaml = new Yaml(new Constructor(Tema.class)); ArrayList<String>
		 * key = new ArrayList<String>(); ArrayList<String> value = new
		 * ArrayList<String>(); InputStream input = null; try { input = new
		 * FileInputStream("Tema1.yml"); } catch (FileNotFoundException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 * 
		 * 
		 * @SuppressWarnings("unchecked")
		 * 
		 * Tema t = (Tema) yaml.load(input);
		 * 
		 * 
		 * // for (Object name : t.keySet()){ // // key.add(name.toString()); //
		 * value.add(yamlParsers.get(name).toString()); // // }
		 * 
		 * System.out.println(t);
		 */

		/*InputStream input = null;
		try {
			input = new FileInputStream("Tema1.yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Yaml yaml = new Yaml();
		
		for (Object data : yaml.loadAll(input)) {
			System.out.println(data);
			
		}
		
		Yaml yaml2 = new Yaml();
		InputStream input2= null;
		try {
			input2=new FileInputStream("prueba.yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		@SuppressWarnings("unchecked")
		Map<String, Object> mapa =  (Map<String, Object>) yaml2.load(input2);
		System.out.println(mapa);
		*/
		
		//yamlbeans
	/*YamlReader reader = null;
		Tema tema= null;
		try {
			reader = new YamlReader(new FileReader("Tema1.yml"));
			try {
				 tema= reader.read(Tema.class);
			} catch (YamlException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object=null;
		try {
			object = reader.read();
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(object);
		Map map = (Map)object;
		*/
		Yaml yaml = new Yaml();
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();
		InputStream input = null;
		try {
			input = new FileInputStream("Tema1.yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		//auxiliares
		ArrayList<Map> l = new ArrayList<Map>();
		ArrayList<Map> p = new ArrayList<Map>();
		l = (ArrayList<Map>) mapaObjeto.get("Lecciones");
		int nTema= (int) mapaObjeto.get("Tema");
		String tTema= (String) mapaObjeto.get("Titulo");
		String iTema= (String) mapaObjeto.get("Introduccion");
		
		//objetos a rellenar para el tema
		List<Pregunta> preguntas= new ArrayList<Pregunta>();
		List<Leccion> lecciones= new ArrayList<Leccion>();
		
		
		for (Map leccion : l)
		{
			preguntas=new ArrayList<Pregunta>();
			p = (ArrayList<Map>) leccion.get("Preguntas");
			Pregunta pregunta= new Codigo(1,null,null,null);
			for (Map pre : p)
			{
				
				int num = (int) pre.get("Pregunta");
				String enunciado = (String) pre.get("Enunciado");
				String pista = (String) pre.get("Pista");
				
				if (pre.get("Tipo").equals("Codigo"))
				{	
					String respuesta = (String) pre.get("Respuesta");
					pregunta = new Codigo(num, enunciado, pista, respuesta); 
					
				}
				else if (pre.get("Tipo").equals("Sintaxis"))
				{
					String sintaxis= (String) pre.get("Gramatica");
					String resultado= (String) pre.get("Resultado");
					pregunta= new Sintaxis(num, enunciado, pista, sintaxis);
				}
				else if (pre.get("Tipo").equals("Opciones"))
				{
					
					pregunta= new Opciones(num,enunciado,pista);
					String opcorrecta= (String) pre.get("Opcion_correcta");
					String[] correctas = opcorrecta.split(",");
					pregunta.setCorrectas(correctas);
					ArrayList<String> opc= new ArrayList<String>();
					opc= (ArrayList<String>) pre.get("Opciones");
					pregunta.setOpciones(opc);
				}
				preguntas.add(pregunta);
				
			}
		int nLeccion= (int) leccion.get("Leccion");
		String tLeccion= (String) leccion.get("Titulo_Leccion");
		String eLeccion= (String) leccion.get("Explicacion");
		Leccion lec= new Leccion(nLeccion,tLeccion,eLeccion);
		lec.setPreguntas(preguntas);
		lecciones.add(lec);
		
		}
		
		//rellenado de objetos final
		Tema t= new Tema(nTema, tTema, iTema);
		t.setLecciones(lecciones);
			//System.out.println(o.toString());
		System.out.println("somos  genios");
		
		/*for (Object name : yamlParsers.keySet()){
			
			key.add(name.toString());
			value.add(yamlParsers.get(name).toString());
			
		}
		
		System.out.println(key + " " + value);
		*/
		//System.out.println("\n------------------\n FIN del objeto"+"\n -------------------------");
		//ArrayList<String> leccion = (ArrayList<String>) mapaObjeto.get("Lecciones");
		//System.out.println(leccion.get(1));
	}

}
