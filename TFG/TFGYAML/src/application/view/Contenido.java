package application.view;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextArea;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.TextNode;

import application.model.Opciones;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Contenido extends Application
{
	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		PegDownProcessor proc = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		VBox box = new VBox();
		
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		//Titulo de la ventana
		primaryStage.setTitle("Leccion");
		//Se agrega la scena
		primaryStage.setScene( scene );
		WebView texto = new WebView();
		WebEngine engine = texto.getEngine();
		engine.loadContent(proc.markdownToHtml("Texto del enunciado de la pregunta o la explicacion"));
		
		/*Falta Añadir un area de texto para las preguntas de codigo. No se me ocurre como hacerlo
		 * 
		 * AnchorPane anchor = new AnchorPane();
		final TextArea textArea = new TextArea();
		anchor.getChildren().addAll(textArea);
		HBox buttons = new HBox();*/
		
		//Botones
		Button prior = new Button("Atras");
		Button next = new Button("Siguiente");
		Button help = new Button("Ayuda");
		Button resolve = new Button("Resolver");
		
		buttons.getChildren().addAll(prior);
		buttons.getChildren().addAll(next);
		buttons.getChildren().addAll(help);
		buttons.getChildren().addAll(resolve);
		
		
		texto.setMaxHeight(100);
		box.getChildren().addAll(texto);
		
		box.getChildren().addAll(buttons);
		scene.setRoot(box);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
