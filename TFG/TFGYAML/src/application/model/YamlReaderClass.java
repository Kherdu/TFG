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
			input = new FileInputStream("resources/" + cargar + ".yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		// auxiliares
		ArrayList<Map> l = new ArrayList<Map>();
		ArrayList<Map> p = new ArrayList<Map>();
		l = (ArrayList<Map>) mapaObjeto.get("Lecciones");
		Integer nTema = (Integer) mapaObjeto.get("Tema");
		String tTema = (String) mapaObjeto.get("Titulo");
		String iTema = (String) mapaObjeto.get("Introduccion");

		// objetos a rellenar para el tema
		List<Pregunta> preguntas = new ArrayList<Pregunta>();
		List<Leccion> lecciones = new ArrayList<Leccion>();

		for (Map leccion : l) {
			preguntas = new ArrayList<Pregunta>();
			p = (ArrayList<Map>) leccion.get("Preguntas");
			Pregunta pregunta = new Codigo(1, null, null, null);
			for (Map pre : p) {
				int num = (Integer) pre.get("Pregunta");

				String enunciado = (String) pre.get("Enunciado");
				String pista = (String) pre.get("Pista");

				if (pre.get("Tipo").equals("Codigo")) {
					String respuesta = (String) pre.get("Respuesta");
					pregunta = new Codigo(num, enunciado, pista, respuesta);

				} else if (pre.get("Tipo").equals("Sintaxis")) {
					String sintaxis = (String) pre.get("Gramatica");
					String resultado = (String) pre.get("Resultado");
					pregunta = new Sintaxis(num, enunciado, pista, sintaxis, resultado);
				} else if (pre.get("Tipo").equals("Opciones")) {
					Boolean is = false;
					pregunta = new Opciones(num, enunciado, pista);
					if ((Boolean) pre.get("Multiple")) {
						is = true;
					}
					String opcorrecta = (String) pre.get("Opcion_correcta");
					String[] correctas = opcorrecta.split(",");
					ArrayList<Integer> correctasAux = StringToInt(correctas);
					pregunta.setSolucion(correctasAux);
					ArrayList<String> opc = new ArrayList<String>();
					opc = (ArrayList<String>) pre.get("Opciones");
					pregunta.setOpciones(opc);
					pregunta.setMulti(is);
				}
				preguntas.add(pregunta);

			}
			int nLeccion = (Integer) leccion.get("Leccion");
			String tLeccion = (String) leccion.get("Titulo_Leccion");
			String eLeccion = (String) leccion.get("Explicacion");
			Leccion lec = new Leccion(nLeccion, tLeccion, eLeccion);
			lec.setPreguntas(preguntas);
			lecciones.add(lec);

		}

		// rellenado de objetos final
		Tema t = new Tema(nTema, tTema, iTema);
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
