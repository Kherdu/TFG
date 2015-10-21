package pruebaYAML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import net.sourceforge.yamlbeans.YamlConfig;

/*
import net.sourceforge.yamlbeans.YamlConfig;
import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;
*/

public class MainpruebaYAML {

	public static void main(String[] args) {
		
		/*
		 * Con yamlbeans
		YamlReader reader = null;
		try {
			reader = new YamlReader(new FileReader("Tema2.yml"));
		} catch (FileNotFoundException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Object object = null;
		try {
			object = reader.read();
		} catch (YamlException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(object);
		if (object == null)
			System.out.println("No hay objeto");
		 if (object instanceof Map) {
			    object=(Map<String,Object>)object;
			  }
		Map map = (Map)object;
		System.out.println(map.get("Introducción"));*/
		
		///Con snakeyaml
		Yaml yaml = new Yaml();
		InputStream input = null;
		try {
			input = new FileInputStream("Tema1.yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		@SuppressWarnings("unchecked")
		Map<String, String> yamlParsers = (Map<String, String>) yaml.load(input);
		System.out.println(yamlParsers.get("Lecciones"));
		
		
	}

}
