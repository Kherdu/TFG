package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

public final class YamlReaderClass {
	private YamlReaderClass() {

	}

	public static Tema cargaTema(String cargar) {
		Yaml yaml = new Yaml();
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();
		InputStream input = null;
		try {
			String path= "resources/yaml/" + cargar;
			input = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		// mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		// auxiliares
		ArrayList<Map> l = new ArrayList<Map>();
		ArrayList<Map> e = new ArrayList<Map>();
		l = (ArrayList<Map>) mapaObjeto.get("Lecciones");
		Integer nTema = (Integer) mapaObjeto.get("Tema");
		String tTema = (String) mapaObjeto.get("Titulo");
		String iTema = (String) mapaObjeto.get("Introduccion");
		String aTema = (String) mapaObjeto.get("Archivo");

		// objetos a rellenar para el tema
		List<Elemento> elementos = new ArrayList<Elemento>();
		List<Leccion> lecciones = new ArrayList<Leccion>();
		
		for (Map leccion : l) {
			elementos = new ArrayList<Elemento>();
			e = (ArrayList<Map>) leccion.get("Elementos");
			
			for (Map pre : e) {
				Elemento elem = new Explicacion(null);
				if (pre.get("Elem").equals("pregunta"))
				{
					int num = (Integer) pre.get("Numero");
	
					String enunciado = (String) pre.get("Enunciado");
					String pista = (String) pre.get("Pista");
	
					if (pre.get("Tipo").equals("Codigo")) {
						String respuesta = (String) pre.get("Respuesta");
						elem = new Codigo(num, enunciado, pista, respuesta);
	
					} else if (pre.get("Tipo").equals("Sintaxis")) {
						String sintaxis = (String) pre.get("Gramatica");
						String resultado = (String) pre.get("Resultado");
						elem = new Sintaxis(num, enunciado, pista, sintaxis, resultado);
					} else if (pre.get("Tipo").equals("Opciones")) {
						Boolean is = false;
						elem = new Opciones(num, enunciado, pista);
						if ((Boolean) pre.get("Multiple")) {
							is = true;
						}
						String opcorrecta = (String) pre.get("Opcion_correcta");
						String[] correctas = opcorrecta.split(",");
						ArrayList<Integer> correctasAux = StringToInt(correctas);
						elem.setSolucion(correctasAux);
						ArrayList<String> opc = new ArrayList<String>();
						opc = (ArrayList<String>) pre.get("Opciones");
						elem.setOpciones(opc);
						elem.setMulti(is);
					}
				}else{
					String explicacion = (String) pre.get("Contenido");
					elem.setTexto(explicacion);
				}
				elementos.add(elem);

			}
			int nLeccion = (Integer) leccion.get("Leccion");
			String tLeccion = (String) leccion.get("Titulo_Leccion");
			String eLeccion = (String) leccion.get("Intro_leccion");
			Leccion lec = new Leccion(nLeccion, tLeccion, eLeccion);
			lec.setElementos(elementos);
			lecciones.add(lec);

		}

		// rellenado de objetos final
		Tema t = new Tema(nTema, tTema, iTema, aTema);
		t.setLecciones(lecciones);
		return t;
	}

	private static ArrayList<Integer> StringToInt(String[] sol) {
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (String s : sol) {
			ret.add(Integer.parseInt(s));
		}
		return ret;

	}
}
