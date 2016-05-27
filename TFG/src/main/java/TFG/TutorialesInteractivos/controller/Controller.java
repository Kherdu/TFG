package TFG.TutorialesInteractivos.controller;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import TFG.TutorialesInteractivos.model.Correction;
import TFG.TutorialesInteractivos.model.Elemento;
import TFG.TutorialesInteractivos.model.Explicacion;
import TFG.TutorialesInteractivos.model.Lenguaje;
import TFG.TutorialesInteractivos.model.Pregunta;
import TFG.TutorialesInteractivos.model.Tema;
import TFG.TutorialesInteractivos.utilities.InternalUtilities;
import TFG.TutorialesInteractivos.utilities.YamlReaderClass;
import TFG.TutorialesInteractivos.view.Contenido;
import TFG.TutorialesInteractivos.view.Configuration;
import TFG.TutorialesInteractivos.view.Inicio;
import TFG.TutorialesInteractivos.view.MenuLeccion;
import TFG.TutorialesInteractivos.view.MenuTema;
import TFG.TutorialesInteractivos.view.SelectedPath;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Clase controlador. Ejecuta todas las variaciones de la aplicación
 * 
 * @author Carlos
 * @param <K>
 * @param <V>
 *
 */
public class Controller {
	public static String executable;// ejecutable del lenguaje para ejecutar
									// código
	private Tema tema; // Tema que se está ejecutando
	private Stage primaryStage;// Vista principal de la aplicación
	private Pane root;// Panel con los elementos de la vista
	private Scene scene;
	private ArrayList<Elemento> elems; // Lista de elementos de un tema
	private int actualStep; // contador de el elemento del contenido en el que
							// estamos
	private int enabledSteps; // Elementos habilitados
	private boolean[] visited; // Array con los elementos de una lección que se
								// han visitado
	private int actualLesson; // Lección en la que se encuentra el tutorial
	private List<String> files;// temas del lenguaje
	public static String selectedLanguage; // lenguaje seleccionado
	private Correction c;
	private Preferences pref;
	public static String externalResourcesPath;
	private URLClassLoader ucl;
	private Lenguaje obsLenguaje;

	public Controller(Stage primaryStage) {
		this.tema = null;
		this.primaryStage = primaryStage;
		this.files = new ArrayList<String>();
		this.c = new Correction();
		this.pref = Preferences.userNodeForPackage(this.getClass());

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

	/**
	 * Función correctora de las preguntas de tipo Codigo y sintaxis
	 * 
	 * @param resp
	 * @param p
	 * @return
	 */
	public boolean corrige(String resp, Pregunta p) {
		boolean ret = false;
		if (p.corrige(resp, tema)) {
			ret = true;
		}
		return ret;
	}

	/**
	 * Llama a changeview pasandole la lista de temas disponibles para un
	 * lenguaje
	 */
	public void showSubject() {

		Pane p = new MenuTema();

		try {
			DirectoryStream.Filter<Path> filter = new DirectoryStream.Filter<Path>() {

				@Override
				public boolean accept(Path entry) throws IOException {
					String fileName = entry.getFileName().toString();
					return fileName != null && fileName.endsWith(".yml");
				}

			};
			final Path path = Paths.get(externalResourcesPath + "/languages/" + selectedLanguage);
			final DirectoryStream<Path> dirStream = Files.newDirectoryStream(path, filter);
			for (Path file : dirStream) {
				// después de aplicar el filtro de extensión .yml, por si acaso
				// comprobamos si es un directorio
				if (!Files.isDirectory(file))
					files.add(file.getFileName().toString());
			}

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

	/**
	 * Lanza la aplicacion
	 */
	public void start() {
		// se pueden poner de usuario en vez de de sistema, pero en usuario
		// serán para el usuario concreto, de sistema funciona para todo
		Pane p = new Pane();
		List<String> a = new ArrayList<String>();

		String pathResources = pref.get("ExternalResources", null);

		if (pathResources != null) {
			externalResourcesPath = pathResources;
			a = languageNames();
			if (loadLanguagePaths(a)) { // comprobamos si los compiladores están
				p = new Inicio();
			} else
				p = new Configuration(); // hace falta configurar compiladores

		} else {
			p = new Configuration(); // hace falta configurar directorio y
										// compiladores
		}
		changeView(p, a, 0, selectedLanguage, null);

	}

	/**
	 * 
	 * @return true si y solo si los lenguajes que hay en el directorio externo
	 *         tienen su path configurado (esté bien o mal)
	 */
	private boolean loadLanguagePaths(List<String> a) {
		boolean ret = true;
		for (String s : a) {
			String check = pref.get(s, null);
			if (check == null)
				ret = false;
		}
		return ret;
	}

	/**
	 * Obtiene la lista de lenguajes disponibles
	 * 
	 * @return
	 */
	public List<String> languageNames() {
		// hay que sacarlos del directorio, es decir, ir a
		// externalresources/languages y mirar las carpetas que hay, esos son
		// los lenguajes disponibles
		return InternalUtilities.getDirectoryList(externalResourcesPath + "/languages");

	}

	/**
	 * Muestra la primera ventana de la aplicacion
	 */
	public void showStart() {
		primaryStage.setTitle(this.selectedLanguage);
		// el titulo se podria
		// poner de la app,
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

		if (p instanceof Configuration) {
			root = ((Configuration) p).firstConfiguration(this);
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

			// TODO elems.size tiene que ir con un +1 para que llegue a la
			// ultima pregunta, ¿y si se quiere la notificación de que has
			// terminado??
			root = ((Contenido) p).contenido(e, this, elems.size() + 1, enabledSteps, actualStep + 2);
		}

		root.setPrefSize(600, 600);
		scene.setRoot(root);

		primaryStage.setWidth(scene.getWidth());
		primaryStage.setHeight(scene.getHeight());
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
	 * 
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
		return new InternalUtilities().parserMarkDown(mark);
	}

	/**
	 * Muesta el FileChooser para seleccionar donde se encuentra python en el
	 * equipo
	 */
	public void muestraSeleccion(Lenguaje l) {
		// diferenciar si l tiene lenguaje o no... en funcion de eso es el path
		// de directorio o el de lenguaje
		SelectedPath sp;
		if (l == null) { // si no llega lenguaje es que hemos cambiado el path
							// del directorio
							// lo guardamos en la variable y en preferences
			sp = new SelectedPath(this.primaryStage);
			this.externalResourcesPath = sp.getPath();
		} else {
			sp = new SelectedPath(this.primaryStage, l.getLanguage());
			obsLenguaje = new Lenguaje(l.getLanguage(), sp.getPath());
		}
	}

	/**
	 * Modifica el path del ejecutable del lenguaje
	 * 
	 * @param path
	 */

	/**
	 * Actualiza el lenguaje seleccionado y el path del archivo de ejecucion
	 * 
	 * @param selectedItem
	 */
	public void selectedLanguage(String selectedItem) {
		this.selectedLanguage = selectedItem;
		this.executable = pathSelected();
		showSubject();
	}

	public String pathSelected() {

		return pref.get(selectedLanguage, null);
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	/*
	 * public void showPortada(String lenguaje) { // Map<K, V> l =
	 * YamlReaderClass.languages(); this.selectedLanguage = lenguaje;
	 * setPath(this.pathSelected()); this.changeView(new Settings(), null, 0,
	 * this.executable, null); }
	 */

	public Scene getScene() {

		return this.scene;
	}

	/**
	 * 
	 * @return Elemento actual de la leccion
	 */
	public int getActualStep() {

		return this.actualStep;
	}

	/**
	 * 
	 * @return Lista de elementos de una leccion
	 */
	public ArrayList<Elemento> getElems() {
		return this.elems;
	}

	/**
	 * Modifica la vista de Contenido
	 * 
	 * @param newStep
	 */
	public void lessonPageChange(Number newStep) {
		actualStep = (int) newStep - 2;
		changeView(new Contenido(), null, actualLesson, selectedLanguage, newStep);
	}

	/**
	 * Actualiza el estado del Paginator
	 * 
	 * @param newStep
	 * @param isQuestion
	 */
	public void stepChange(Number newStep, boolean isQuestion) {
		// -2 porque en nuestra indexación hay -1 que es la intro y elemento 0
		if (!isQuestion)
			enableNextStep((int) newStep);
	}

	/**
	 * Habilita el siguiente elemento del actual en el Paginator. Solo si no es
	 * pregunta
	 */
	public void enableNextStep(int actual) {

		if (!visited[actual - 1]) {
			visited[actual - 1] = true;
			enabledSteps += 1;
		}
	}

	/**
	 * 
	 * @param path
	 *            externalResources path
	 * @param data
	 *            lista de lenguajes disponibles en la carpeta
	 * 
	 *            Este metodo guarda en preferences el path, los lenguajes y las
	 *            rutas de los compiladores y pone en el classpath la ruta para
	 *            usar con antlr/reflections
	 */
	public void savePrefs(String path, List<Lenguaje> data) {

		if (path != null && !data.isEmpty()) {
			pref.put("ExternalResources", path);
			for (Lenguaje l : data) {
				pref.put(l.getLanguage(), l.getPath());
			}
		//	saveClassPath(path);
		} else {
			// TODO error que diga que no hay directorio o compiladores (aqui o
			// en la vista?)
		}
	}

	public void vistaSettings() {
		Pane p = new Configuration();
		List<String> a = languageNames();
		changeView(p, a, 0, selectedLanguage, null);
	}

	
	/*private void saveClassPath(String path) {
		URL classUrl;
		try {
			classUrl = new URL("file:" + path);
			URL[] classUrls = { classUrl };
			ucl = new URLClassLoader(classUrls);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public String getExternalPath() {
		return externalResourcesPath;
	}

	public List<Lenguaje> getLanguagesList() {
		List<String> lanL = InternalUtilities.getDirectoryList(externalResourcesPath + "/languages");
		List<Lenguaje> l = new ArrayList<Lenguaje>();
		if (!lanL.isEmpty()) {
			for (String s : lanL) {
				Lenguaje addedL = new Lenguaje(s, pref.get(s, null));
				l.add(addedL);
			}

		}
		return l;
	}

	public Lenguaje getLanguageAttributes() {
		return obsLenguaje;
	}
}
