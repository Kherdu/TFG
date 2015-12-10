package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.yaml.snakeyaml.Yaml;

import application.Main;
import application.model.Codigo;
import application.model.Leccion;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Sintaxis;
import application.model.Tema;
import application.model.YamlReaderClass;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Controller {

	private Tema tema;
	private Stage primaryStage;
	private TilePane root;
	private VBox buttons;
	
	
	public Controller() {
		this.tema = null;
	}

	public void cargaModelo(String cargaTema) {
		tema=YamlReaderClass.cargaTema(cargaTema);
	
			
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	
	public boolean corrige(ArrayList<Integer> resp, Pregunta p ) {
		return p.corrige(resp);
		
	}

	public void launch() {

		PegDownProcessor processor = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		// Este objeto se deberia pasar a la vista para que lo muestre
		// temporal para pruebas

		// obtenemos los atributos
		String tituloPreProcesado = tema.getTitulo();
		String introPreProcesado = tema.getIntroduccion();
		// pasamos txtmark
		String tituloProcesado = processor.markdownToHtml(tituloPreProcesado);
		String introProcesado = processor.markdownToHtml(introPreProcesado);

		// otro
		String tituloLeccionPreProc = tema.getLecciones().get(0).getTitulo();
		String explicacionLeccionPreProc = tema.getLecciones().get(0).getExplicacion();

		String tituloLeccionProc = processor.markdownToHtml(tituloLeccionPreProc);
		String explicacionLeccionProc = processor.markdownToHtml(explicacionLeccionPreProc);
		// html ejemplos varios
		String htmlProcesado = tituloProcesado + introProcesado;
		String htmlProcesado2 = tituloLeccionProc + explicacionLeccionProc;

		// pregunta con imagen
		String preguntaPreProc = tema.getLecciones().get(0).getPreguntas().get(2).getEnunciado();

		String tab = "First Header  | Second Header" + "\n" + "------------- | ------------- 	\n"
				+ "Content Cell  | Content Cell\n" + "Content Cell  | Content Cell\n";

		System.out.println(System.getProperty("user.dir"));
		String preguntaProc = processor.markdownToHtml(preguntaPreProc);
		
		// initLayout();
		// showTemas();
		// showImg(img);
		Pregunta p = tema.getLecciones().get(0).getPreguntas().get(1);
		showOptions(p, processor);
		// showIntroTema(preguntaProc);
		// int num = new File("resources").list().length;
	}
	private void showImg(Image img) {

		Scene scene = new Scene(new Group());
		StackPane sp = new StackPane();
		ImageView imgView = new ImageView(img);
		sp.getChildren().add(imgView);

		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void showOptions(final Pregunta p, PegDownProcessor proc) {
		final Opciones o = (Opciones) p;
		Scene scene = new Scene(new Group());
		//scene.getStylesheets().add("resources/css/prueba");
		
		root();
		buttons = new VBox();
		
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();
		if (!o.getMulti()) {
			List<RadioButton> l = new ArrayList<RadioButton>();
			List<String> opciones = o.getOpciones();
			final ToggleGroup group = new ToggleGroup();
			for (Object op : opciones) {
				RadioButton cb = new RadioButton();
				cb.setText(op.toString());
				cb.setToggleGroup(group);
				l.add(cb);
				
			}
			buttons.getChildren().addAll(l);
			
		} else {
			List<CheckBox> l = new ArrayList<CheckBox>();
			List<String> opciones = o.getOpciones();
			for (Object op : opciones) {
				CheckBox cb = new CheckBox();
				cb.setText(op.toString());
				l.add(cb);
			}
			buttons.getChildren().addAll(l);
		}
		Button envio = new Button("Resolver");
		envio.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				buttons.getChildren();
				ArrayList<Integer> resp = new ArrayList<Integer>();

				int i = 0;
				for (Node n : buttons.getChildren()) {
					i++;
					if (o.getSolucion().size() == 1) {
						if (((RadioButton) n).isSelected()) {

							resp.add(i);
							// meter respuestas elegidas en array
						}
					}else if (((CheckBox) n).isSelected()) {

						resp.add(i);
						// meter respuestas elegidas en array
					}
				}
				System.out.println(corrige(resp, p));
				// comprobar respuestas correctas y escribir en ventana
			}
		});

		engine.loadContent(proc.markdownToHtml(p.getEnunciado()));
		root.setPrefSize(200, 200);
		root.setMaxWidth(200);
		browser.setMaxHeight(100);
		root.getChildren().addAll(browser);
		root.getChildren().addAll(buttons);
		root.getChildren().addAll(envio);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showIntroTema(String html) {
		System.out.println(html);
		Scene scene = new Scene(new Group());
		root();
		WebView browser = new WebView();
		WebEngine engine = browser.getEngine();

		ScrollPane panelTexto = new ScrollPane();
		panelTexto.setContent(browser);
		engine.loadContent(html);
		root.getChildren().addAll(panelTexto);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void showTemas() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Initial.fxml"));
			AnchorPane initial = loader.load();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public TilePane root() {

		root = new TilePane();
		root.setPrefColumns(1);
		return root;

	}
}
