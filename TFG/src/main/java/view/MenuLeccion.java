package main.java.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import main.java.controller.Controller;
import main.java.model.Tema;
import main.java.model.Utilities;

public class MenuLeccion extends Pane{
	
	
	
	public MenuLeccion(){
		
	}
	
	public Pane menuLeccion(Tema t, Controller c){
		
		
		GridPane box = new GridPane();
		
		//Elementos
		
		//Creacion de webview
		String content = c.markToHtml(t.getTitulo() + "\n" + t.getIntroduccion());
		WebView tittle = Utilities.creaBrowser(content);
		WebEngine engine = tittle.getEngine();
		engine.loadContent(content);
		
		Label tittleList = new Label("Lecciones");//TItulo de la lista de lecciones
		//Lista de lecciones
		ListView<String> leccionList = new ListView<String>();
		ObservableList<String> obsLecciones =FXCollections.observableArrayList (t.getNameLecciones());
		leccionList.setItems(obsLecciones);
		
		HBox botonLabel = new HBox();//Contenedor del boton mas el label de aviso
		
		Button start = new Button("Comenzar leccion");//Boton para comenzar la leccion
		Label error = new Label();//Label de error
		
		botonLabel.getChildren().add(start);
		botonLabel.getChildren().add(error);
		
		//Colocacion de elementos en el panel
		box.add(tittle, 0, 0);
		box.add(tittleList, 0, 1);
		box.add(leccionList, 0, 2);
		box.add(botonLabel, 0, 3);
		
		
		GridPane.setConstraints(tittle, 0, 0, 2,1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(tittleList, 0, 1, 2, 1, HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(leccionList, 0, 2, 1, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(botonLabel, 0, 3, 1, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		 
		
		//Listeners
		start.setOnAction(new EventHandler<ActionEvent>(){
			MultipleSelectionModel<String> s;
				@Override
				public void handle(ActionEvent event) {
					s= leccionList.getSelectionModel();
					if (!s.isEmpty()) //Se comprueba que haya una leccion seleccionada
						c.selectedLeccion(s.getSelectedIndex());
					else 
					{
						error.setText("Se debe seleccionar una leccion");
					}
					
				}
				
			});
		
		
		//Parte estetica
		start.getStyleClass().add("start");
		error.getStyleClass().add("error");
		tittleList.getStyleClass().add("tittle");
		box.getStylesheets().add(getClass().getResource("/main/resources/css/menu.css").toExternalForm());
		return box;
		
	}
}
