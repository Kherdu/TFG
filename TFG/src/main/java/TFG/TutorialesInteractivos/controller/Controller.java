package TFG.TutorialesInteractivos.controller;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;
import java.util.stream.Stream;

import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import TFG.TutorialesInteractivos.Main;
import TFG.TutorialesInteractivos.model.Correction;
import TFG.TutorialesInteractivos.model.Elemento;
import TFG.TutorialesInteractivos.model.Explicacion;
import TFG.TutorialesInteractivos.model.Pregunta;
import TFG.TutorialesInteractivos.model.Tema;
import TFG.TutorialesInteractivos.utilities.CargaConfig;
import TFG.TutorialesInteractivos.utilities.PreferencesHandler;
import TFG.TutorialesInteractivos.utilities.Utilities;
import TFG.TutorialesInteractivos.utilities.YamlReaderClass;
import TFG.TutorialesInteractivos.view.Contenido;
import TFG.TutorialesInteractivos.view.FirstConfiguration;
import TFG.TutorialesInteractivos.view.Inicio;
import TFG.TutorialesInteractivos.view.MenuLeccion;
import TFG.TutorialesInteractivos.view.MenuTema;
import TFG.TutorialesInteractivos.view.SelectedPath;
import TFG.TutorialesInteractivos.view.Settings;

/**
 * Clase controlador. Ejecuta todas las variaciones de la aplicación
 * 
 * @author Carlos
 * @param <K>
 * @param <V>
 *
 */
public class Controller<K, V> {
	public static String executable;// ejecutable del lenguaje para ejecutar
									// código
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
	private Preferences pref;
	private String externalResourcesPath;

	public Controller(Stage primaryStage) {
		this.tema = null;
		this.primaryStage = primaryStage;
		this.files = new ArrayList<String>();
		this.c = new Correction();
		this.pref = Preferences.systemNodeForPackage(Controller.class);
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
			final Path path = Paths.get("externalResources/languages/" + selectedLanguage);
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

	public void start() {
		// se pueden poner de usuario en vez de de sistema, pero en usuario
		// serán para el usuario concreto, de sistema funciona para todo
		String pathResources = pref.get("ExternalResources", null);
		if (pathResources != null) {
			externalResourcesPath = pathResources;
			Pane p = new Inicio();
			String languageNames = pref.get("Languages", null);
			languageNames.split(",");
			ArrayList<String> a = languageNames();
			changeView(p, a, 0, selectedLanguage, null);
		} else {
			Pane p = new FirstConfiguration();
		}

	}

	public ArrayList<String> languageNames() {

		String lang = pref.get("Languages", null);
		String[] langArray = lang.split(",");
		ArrayList<String> s = (ArrayList<String>) Arrays.asList(langArray);
		return s;
	}

	/**
	 * Muestra la primera ventana de la aplicaci�n
	 */
	public void showStart() {
		primaryStage.setTitle(this.selectedLanguage); // el titulo se podria
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

			// TODO elems.size tiene que ir con un +1 para que llegue a la
			// ultima pregunta, ¿y si se quiere la notificación de que has
			// terminado??
			root = ((Contenido) p).contenido(e, this, elems.size() + 1, enabledSteps, actualStep + 2);
		}

		root.setPrefSize(600, 600);
		scene.setRoot(root);

		primaryStage.setX(scene.getX());
		primaryStage.setY(scene.getY());
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
		this.executable = sp.getPath();
		CargaConfig.saveConfig(this.executable);

	}

	/**
	 * Modifica el path del ejecutable del lenguaje
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		// TODO esto tiene que hacer un get
		this.executable = path;
		modifyMap();
		YamlReaderClass.saveConfig((Map<String, Object>) this.lenguajes);
	}

	public void selectedLanguage(String selectedItem) {
		this.selectedLanguage = selectedItem;
		this.executable = pathSelected();
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
				o.put("ruta", this.executable);
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
		this.changeView(new Settings(), null, 0, this.executable, null);
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
		// ya y es explicación, se llama cuando se corrige bien, y se llama aquí
		// y no a stepchange por pregunta... hay alguna forma mejor?
		if (!visited[actual - 1]) {
			visited[actual - 1] = true;
			enabledSteps += 1;
		}
	}

	// Creo que no va a hacer falta
	/*
	 * public void refreshWindow(){ changeView(new Contenido(), null,
	 * actualLesson, len, actualStep); }
	 */
}
