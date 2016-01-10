package application.view;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuLeccion extends Application
{
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox box = new VBox();
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		//Titulo de la ventana
		primaryStage.setTitle("TEMA 1");
		//Se agrega la scena
		primaryStage.setScene( scene );
		
		///Elementos 
		PegDownProcessor pro = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		WebView titulo = new WebView();
		WebEngine engine = titulo.getEngine();
		engine.loadContent(pro.markdownToHtml("#TEMA 1"));
		
		ListView<String> leccionList = new ListView<String>();
		ObservableList<String> lecciones =FXCollections.observableArrayList (
		    "Leccion 1", "Leccion 2");
		leccionList.setItems(lecciones);
		
		Button alTurron = new Button("Al turron!");
		
		///Añadir elementos a la vista
		box.getChildren().addAll(titulo);
		box.getChildren().addAll(leccionList);
		box.getChildren().addAll(alTurron);
		
		primaryStage.show();
		
	}
}
