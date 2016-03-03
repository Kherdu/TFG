package application.model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

/**
 * Clase que contiene las funciones de carga de un fichero yml
 * @author Carlos
 *
 */
public final class YamlReaderClass {
	private YamlReaderClass() {

	}
	
	/**
	 * Carga el tema seleccionado (con el que se trabajara)
	 * @param cargar Nombre del archivo del tema
	 * @return Tema seleccionado
	 */
	public static Tema cargaTema(String language,String cargar) {
		Yaml yaml = new Yaml();
		//ArrayList<String> key = new ArrayList<String>();//Lista de claves del arbol
		//ArrayList<String> value = new ArrayList<String>();//lista de valores asociados a las claves 
		InputStream input = null;
		try {
			String path= "resources/yaml/" +language+"/"+ cargar; //Concatenamos con la ruta relativa
			input = new FileInputStream(path); //carga el fichero
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		// mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		// auxiliares
		ArrayList<Map> l = new ArrayList<Map>();//lista de lecciones
		ArrayList<Map> e = new ArrayList<Map>();//Lista de elementos
		l = (ArrayList<Map>) mapaObjeto.get("Lecciones");
		
		//Elementos para rellenar el objeto Tema
		Integer nTema = (Integer) mapaObjeto.get("Tema");//numero del tema
		String tTema = (String) mapaObjeto.get("Titulo");//Nombre del tema
		String iTema = (String) mapaObjeto.get("Introduccion");//introduccion del tema
		String aTema = (String) mapaObjeto.get("Archivo");//Nombre del archivo con las funciones de solucion

		// objetos a rellenar para el tema
		List<Elemento> elementos = new ArrayList<Elemento>();//lista de elementos
		List<Leccion> lecciones = new ArrayList<Leccion>();//lista de lecciones
		
		for (Map leccion : l) //Recorremos la lista de lecciones y parseamos los elementos
		{
			elementos = new ArrayList<Elemento>();//Lista de elementos de una leccion
			e = (ArrayList<Map>) leccion.get("Elementos");//Vuelca los elementos en una Lista de Maps
			
			for (Map pre : e)//Recorre los elementos de la leccion a cargar y los parsea
			{
				Elemento elem = new Explicacion(null); //Crea un nuevo elem
				
				if (pre.get("Elem").equals("pregunta"))//El elemento es de tipo pregunta
				{
					//Elementos generales a todo tipo de preguntas
					int num = (Integer) pre.get("Numero"); //Numero de la pregunta
					String enunciado = (String) pre.get("Enunciado");//Enunciado
					String pista = (String) pre.get("Pista");//Pista
	
					if (pre.get("Tipo").equals("Codigo")) //Pregunta de tipo codigo
					{
						String respuesta = (String) pre.get("Resultado");//
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
						String opcorrecta = (String) pre.get("Opcion_correcta");//Cogemos el texto de las correctas
						String[] correctas = opcorrecta.split(","); //Separamos la cadena en las respuestas
						ArrayList<Integer> correctasAux = StringToInt(correctas); //Camibia el tipo de respuesta a Integer
						elem.setSolucion(correctasAux); //Añade las respuestas correctas a la lista de respuestas del objeto
						ArrayList<String> opc = new ArrayList<String>(); //Array de las opciones
						opc = (ArrayList<String>) pre.get("Opciones"); //Carga las opciones
						elem.setOpciones(opc);//Las mete en el objeto 
						elem.setMulti(is);//Modifica el parametro de multirrespuesta
					}
				}else{
					String explicacion = (String) pre.get("Contenido");//Carga el contenido de la explicación
					elem.setTexto(explicacion);//Modifica el texto del elemento
				}
				elementos.add(elem);//Añade el elemento al array de elementos de Leccion

			}
			int nLeccion = (Integer) leccion.get("Leccion"); //numero de leccion
			String tLeccion = (String) leccion.get("Titulo_Leccion");//titulo de leccion
			String eLeccion = (String) leccion.get("Intro_leccion");//Introduccion de la leccion
			Leccion lec = new Leccion(nLeccion, tLeccion, eLeccion);//Crea la leccion
			lec.setElementos(elementos);//Modifica el array de elementos de una leccion
			lecciones.add(lec);//Añade la leccion al array de lecciones
		}
		// rellenado de objetos final
		Tema t = new Tema(nTema, tTema, iTema, aTema);//Crea el tema con todos los elementos cargados
		t.setLecciones(lecciones);//Modifica el Array de lecciones de Tema
		return t;
	}

	/**
	 * Funcion auxiliar que pasa los elementos de tipo String de un Array a tipo Int y los mete en un ArrayList
	 * @param sol
	 * @return
	 */
	private static ArrayList<Integer> StringToInt(String[] sol) {
		ArrayList<Integer> ret = new ArrayList<Integer>();

		for (String s : sol) {
			ret.add(Integer.parseInt(s));
		}
		return ret;

	}
	
	public static ArrayList<String> languages(){
		Yaml yaml = new Yaml();
		//ArrayList<String> key = new ArrayList<String>();//Lista de claves del arbol
		//ArrayList<String> value = new ArrayList<String>();//lista de valores asociados a las claves 
		InputStream input = null;
		try {
			String path= "resources/config/carga.yml"; //Concatenamos con la ruta relativa
			input = new FileInputStream(path); //carga el fichero
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		// mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		ArrayList<Map>l = (ArrayList<Map>) mapaObjeto.get("lenguajes");
		ArrayList<String> s= new ArrayList<String>();
		for (Map o: l) s.add((String) o.get("nombre"));
		
		
		return s;
		
	}
}
