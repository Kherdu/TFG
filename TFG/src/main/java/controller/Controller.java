package main.java.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;
import main.java.model.CargaConfig;
import main.java.model.Correction;
import main.java.model.Elemento;
import main.java.model.Explicacion;
import main.java.model.Pregunta;
import main.java.model.Tema;
import main.java.model.Utilities;
import main.java.model.YamlReaderClass;
import main.java.view.Contenido;
import main.java.view.Inicio;
import main.java.view.MenuLeccion;
import main.java.view.MenuTema;
import main.java.view.SelectedPath;
import main.java.view.Settings;

/**
 * Clase controlador. Ejecuta todas las variaciones de la aplicación
 * 
 * @author Carlos
 * @param <K>
 * @param <V>
 *
 */
public class Controller<K, V> {
	public static String path;// ejecutable de python
	private Tema tema;
	private Stage primaryStage;
	private Pane root;
	private Scene scene;
	private ArrayList<Elemento> elems;
	private int actualStep; // contador de el elemento del contenido en el que
							// estamos
	private int enabledSteps;
	private boolean[] visited;
	private int actualLesson;
	private List<String> files;// temas del lenguaje
	private String selectedLanguage; // lenguaje seleccionado
	private Map<K, V> lenguajes; // Map con los lenguajes posibles
	private Correction c;

	public Controller(Stage primaryStage) {
		this.tema = null;
		this.primaryStage = primaryStage;
		this.files = new ArrayList<String>();
		this.lenguajes = YamlReaderClass.languages();
		this.c = new Correction();

	}

	/**
	 * Llama a la función del modelo encargada de cargar un tema
	 * 
	 * @param cargaTema
	 *            Nombre del fichero
	 */

	public Correction getCorrection() {
		return c;
	}

	/**
	 * Devuelve el tema que está abierto
	 * 
	 * @return tema
	 */
	public Tema getTema() {
		return tema;
	}

	/**
	 * Modifica el tema
	 * 
	 * @param tema
	 */
	public void setTema(Tema tema) {
		this.tema = tema;
	}

	/**
	 * Corrige las preguntas de tipo Opciones
	 * 
	 * @param resp
	 * @param p
	 * @return
	 */
	public boolean corrige(ArrayList<Integer> resp, Pregunta p) {
		boolean ret = false;
		if (p.corrige(resp, tema)) {
			ret = true;
		}
		return ret;
	}

	public boolean corrige(String resp, Pregunta p) {
		boolean ret = false;
		if (p.corrige(resp, tema)) {
			ret = true;
		}
		return ret;
	}

	/**
	 *  Llama a changeview pasandole la lista de temas disponibles para un lenguaje
	 */
	public void showSubject() {

		Pane p = new MenuTema();
		//obtenemos el path del proyecto TODO probar en linux
		//String folderpath = new File("").getAbsolutePath();
		//sacamos la lista de archivos en dicho path
		//String path= folderpath+"/main/src/resources/yaml/"+selectedLanguage +"/";
		/*Path path = Paths.get(getClass().getResource("/yaml/" + selectedLanguage).toURI());
		File folderFile = new File(path);
		String[] stingray = folderFile.list(); 
		files = Arrays.asList(stingray);*/
		try{
	          Path path = Paths.get(getClass().getResource("/main/resources/yaml/" + selectedLanguage).toURI());
	          Stream<Path> listOfFiles = Files.list(path);
	        
	          listOfFiles.forEach( (f) -> {
	            System.err.println(f);
	            if (!Files.isDirectory(f)) {
	              files.add(f.getFileName().toString());
	            }
	          });
	        
	        } catch (Exception e) {
	        e.printStackTrace();
	      }
		
		changeView(p, files, 0, selectedLanguage, null);
	}

	/**
	 * Vuelve a mostrar el menú de temas
	 */
	public void goMenu() {
		showStart();
	}

	
	public void start() {
		Pane p = new Inicio();

		// Map<K, V> l = YamlReaderClass.languages();
		ArrayList<String> a = languageNames();
		changeView(p, a, 0, selectedLanguage, null);
	}

	public ArrayList<String> languageNames() {
		ArrayList<Map> p = (ArrayList<Map>) this.lenguajes.get("lenguajes");
		ArrayList<String> s = new ArrayList<String>();
		for (Map o : p)
			s.add((String) o.get("nombre"));

		return s;
	}

	/**
	 * Muestra la primera ventana de la aplicaci�n
	 */
	public void showStart() {
		primaryStage.setTitle(this.selectedLanguage); // el titulo se podria poner de la app,
											// o del lenguaje, pero obteniendo
											// en la primera lectura de
											// ficheros...
		// este es el encargado de hacer el setroot que tiene los contenidos
		// necesarios
		Pane p = new MenuTema();

		changeView(p, files, 0, selectedLanguage, null);

	}

	/**
	 * Modifica la vista que se muestra en el momento
	 * 
	 * @param p
	 *            Panel a mostrar
	 * @param files
	 *            Lista de los ficheros que componen el temario
	 * @param selected
	 *            Lección seleccionada
	 * @param lenSelect
	 *            Lenguaje seleccionado
	 */
	private void changeView(Pane p, List<String> files, int selected, String lenSelect, Number newStep) {
		scene = new Scene(new Group());
		// root = new GridPane();

		if (p instanceof Settings) {
			root = ((Settings) p).settings(this, lenSelect);
		} else if (p instanceof Inicio) {
			root = ((Inicio) p).inicio(files, this);
		} else if (p instanceof MenuTema) {
			root = ((MenuTema) p).menuTema(files, lenSelect, this);
		} else if (p instanceof MenuLeccion) {
			root = ((MenuLeccion) p).menuLeccion(tema, this);
		} else if (p instanceof Contenido) {
			Elemento e;
			// TODO añadir preguntas de ambos tipos aquí, solo deberia usarse la
			// primera vez en contenido, luego se deberia modificar, si no, en
			// cada paso hacemos un objeto nuevo...

			if (actualStep == -1) {
				e = new Explicacion(tema.getLecciones().get(selected).getExplicacion());

			} else if (actualStep == this.elems.size()) {
				e = new Explicacion("FIN DE LA LECCION");
			} else {
				e = elems.get(actualStep);
				stepChange(newStep, e instanceof Pregunta);
			}
			// habilitados empezaremos con 1, y el paso actual es el 1 para la
			// vista (comienza en -1 aquí)
			// el que estás mas algo
			
			//TODO elems.size tiene que ir con un +1 para que llegue a la ultima pregunta, ¿y si se quiere la notificación de que has terminado??
			root = ((Contenido) p).contenido(e, this, elems.size()+1, enabledSteps, actualStep + 2);
		}

		root.setPrefSize(600,600);
		scene.setRoot(root);

		primaryStage.setScene(scene);

		primaryStage.show();
	}

	/**
	 * Caraga el tema seleccionado y carga el menu de seleccion de temas
	 * 
	 * @param selectedItem
	 */
	public void selectedTema(String selectedItem) {

		this.tema = YamlReaderClass.cargaTema(selectedLanguage, selectedItem);
		changeView(new MenuLeccion(), null, 0, selectedLanguage, null);
	}

	/**
	 * Carga los componenetes del tema, y muestra la ventana con la primera
	 * explicación, se llamará cuando elijamos una leccion
	 * 
	 * @param selectedItem
	 *            es la lección seleccionada
	 */
	public void selectedLeccion(int selectedItem) {
		this.actualLesson = selectedItem;
		this.elems = (ArrayList<Elemento>) tema.getLecciones().get(selectedItem).getElementos();
		actualStep = -1;
		enabledSteps = 2;
		visited = new boolean[elems.size()];
		Arrays.fill(visited, Boolean.FALSE);
		visited[0] = true;
		changeView(new Contenido(), null, actualLesson, selectedLanguage, 0);
	}

	/**
	 * Parseaa el texto en markdown y lo transforma en texto HTML
	 * 
	 * @param mark
	 * @return texto en formato HTML
	 */
	public String markToHtml(String mark) {
		return new Utilities().parserMarkDown(mark);
	}

	/**
	 * Muesta el FileChooser para seleccionar donde se encuentra python en el
	 * equipo
	 */
	public void muestraSeleccion() {
		SelectedPath sp = new SelectedPath(this.primaryStage, this.selectedLanguage);
		this.path = sp.getPath();
		CargaConfig.saveConfig(this.path);

	}

	/**
	 * Modifica el path del ejecutable del lenguaje
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		//TODO esto tiene que hacer un get
		this.path = path;
		modifyMap();
		YamlReaderClass.saveConfig((Map<String, Object>) this.lenguajes);
	}

	public void selectedLanguage(String selectedItem) {
		this.selectedLanguage = selectedItem;
		showSubject();
	}

	/**
	 * Modifica el path del lenguaje seleccionado
	 */
	private void modifyMap() {
		ArrayList<Map> p = (ArrayList<Map>) this.lenguajes.get("lenguajes");
		String ret = null;

		for (Map o : p) {
			if (o.get("nombre").equals(this.selectedLanguage)) {
				o.put("ruta", this.path);
			}
		}

	}

	public String pathSelected() {
		ArrayList<Map> p = (ArrayList<Map>) this.lenguajes.get("lenguajes");
		String ret = null;

		for (Map o : p) {
			if (o.get("nombre").equals(this.selectedLanguage)) {
				ret = (String) o.get("ruta");
				break;
			}
		}
		return ret;
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public void showPortada(String lenguaje) {
		// Map<K, V> l = YamlReaderClass.languages();
		this.selectedLanguage = lenguaje;
		setPath(this.pathSelected());
		this.changeView(new Settings(), null, 0, this.path, null);
	}

	public Scene getScene() {

		return this.scene;
	}

	public int getActualStep() {

		return this.actualStep;
	}

	public ArrayList<Elemento> getElems() {
		return this.elems;
	}

	public void lessonPageChange(Number newStep) {
		actualStep = (int) newStep - 2;
		changeView(new Contenido(), null, actualLesson, selectedLanguage, newStep);
	}

	public void stepChange(Number newStep, boolean isQuestion) {
		// -2 porque en nuestra indexación hay -1 que es la intro y elemento 0
		if (!isQuestion)
			enableNextStep((int) newStep);
	}

	public void enableNextStep(int actual) {
		// TODO habilitar el siguiente si en el que estamos no ha sido visitado
		// ya y es explicación, se llama cuando se corrige bien, y se llama aquí y no a stepchange por pregunta... hay alguna forma mejor?
		if (!visited[actual - 1]) {
			visited[actual - 1] = true;
			enabledSteps += 1;
		}
	}
	
	//Creo que no va a hacer falta
	/*public void refreshWindow(){
		changeView(new Contenido(), null, actualLesson, len, actualStep);
	}*/
}
