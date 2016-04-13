package application.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Panel que muestra el menu con los temas que se compone el tutorial
 * @author Carlos
 *
 */
public class MenuTema extends Pane{
		
	public MenuTema(){
	}
	
	public Pane menuTema(List<String> files, String lenSelect, Controller c){
		GridPane box = new GridPane();
		
		Label language = new Label(lenSelect);
		ListView<String> temasList = new ListView<String>(); //Lista de los temas
		ObservableList<String> obsTemas =FXCollections.observableArrayList(files);//permite ver la seleccion
		temasList.setItems(obsTemas);
		
		Button start = new Button("Comenzar");
		Label error = new Label ();
		
		language.setAlignment(Pos.TOP_CENTER);
		
		
		HBox botonLabel = new HBox();
		botonLabel.getChildren().add(start);
		botonLabel.getChildren().add(error);
		
		
		HBox.setMargin(start, new Insets(0, 5, 0, 0));
		
		box.add(language, 0, 0);
		box.add(temasList, 0, 1);
		box.add(botonLabel, 0, 2);
		
		GridPane.setConstraints(language, 0, 0, 2,1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(temasList, 0, 1, 2, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(botonLabel, 0, 2, 1, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		
		start.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= temasList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.selectedTema(s.getSelectedItem());//Se carga el tema seleccionado
				else 
					error.setText("Se debe seleccionar un tema");
			}
			
		});
		
		//Parte estetica
		language.getStyleClass().add("title");
		start.getStyleClass().add("start");
		error.getStyleClass().add("error");
		String folderpath = new File("").getAbsolutePath();
		String csspath= folderpath+ "/src/resorces/css/menu.css";
//		box.getStylesheets().add(getClass().getResource(csspath).toExternalForm());
		
		
		
		
		return box;
		
		
		
	}

}
