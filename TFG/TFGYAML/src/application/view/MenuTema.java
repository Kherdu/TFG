package application.view;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuTema extends Application{
	
	public static void main(String[] args) {
		launch(args);
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox box = new VBox();
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		//Titulo de la ventana
		primaryStage.setTitle("PYTHON");
		//Se agrega la scena
		primaryStage.setScene( scene );
		
		scene.getStylesheets().add("/TFG/TFGYAML/src/application/view/tema.css");
		
		///Elementos 
		Label leng = new Label("PYTHON");
		ListView<String> temasList = new ListView<String>();
		ObservableList<String> temas =FXCollections.observableArrayList (
		    "Tema 1", "Tema 2");
		temasList.setItems(temas);
		Button comenzar = new Button("Comenzar");
		
		///Añadir elementos a la vista
		box.getChildren().addAll(leng);
		box.getChildren().addAll(temasList);
		box.getChildren().addAll(comenzar);
		
		primaryStage.show();
		
	}

}
