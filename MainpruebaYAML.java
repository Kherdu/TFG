package pruebaYAML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

import net.sourceforge.yamlbeans.YamlException;
import net.sourceforge.yamlbeans.YamlReader;

public class Main {

	public static void main(String[] args) {
		YamlReader reader = null;
		try {
			reader = new YamlReader(new FileReader("Tema1.yml"));
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
		 if (object instanceof Map) {
			    object=(Map<String,Object>)object;
			  }
		Map map = (Map)object;
		System.out.println(map.get("Resultado"));
	}

}
