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
/*
		InputStream input = null;
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
	/*	YamlReader reader = null;
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

		@SuppressWarnings("unchecked")
		Map<String, Object> yamlParsers = (Map<String, Object>) yaml.load(input);
		for (Object name : yamlParsers.keySet()){
			
			key.add(name.toString());
			value.add(yamlParsers.get(name).toString());
			
		}
		
		System.out.println(key + " " + value);
	}

}
