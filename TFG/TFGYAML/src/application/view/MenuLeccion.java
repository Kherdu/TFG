package application.view;

import java.awt.Color;

import application.controller.Controller;
import application.model.Tema;
import application.model.Utilities;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MenuLeccion extends Pane{
	
	private Tema t;
	private Controller c;
	
	public MenuLeccion(){
		
	}
	
	public Pane menuLeccion(Tema t, Controller c){
		
		this.c=c;
		this.t=t;
		VBox box = new VBox(10);
		HBox botonLabel = new HBox(5);//Contenedor del boton mas el label de aviso
		
		
		///Elementos 
		String content = c.markToHtml(t.getTitulo() + "\n" + t.getIntroduccion());
		WebView titulo = Utilities.creaBrowser(content);
		WebEngine engine = titulo.getEngine();
		engine.loadContent(content);
		
		Label lista = new Label("Lista de lecciones");
		ListView<String> leccionList = new ListView<String>();
		ObservableList<String> obsLecciones =FXCollections.observableArrayList (t.getNameLecciones());
		leccionList.setItems(obsLecciones);
		
		Label error = new Label();
		Button alTurron = new Button("Comenzar leccion");
		alTurron.setOnAction(new EventHandler<ActionEvent>(){
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
		///Añadir elementos a la vista
		box.getChildren().addAll(titulo);
		box.getChildren().addAll(lista);
		box.getChildren().addAll(leccionList);
		botonLabel.getChildren().addAll(alTurron);
		botonLabel.getChildren().addAll(error);
		box.getChildren().addAll(botonLabel);
		
		//Parte estetica
		box.setPadding(new Insets(20));
		error.getStyleClass().add("error");
		lista.getStyleClass().add("lista");
		box.getStylesheets().add("/application/view/css/leccion.css");
		
		
		
		
		box.setPrefSize(600, 600);
		
		return box;
		
	}
}
