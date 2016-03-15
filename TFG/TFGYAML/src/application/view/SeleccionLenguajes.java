package application.view;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import application.SelectedPath;
import application.controller.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SeleccionLenguajes extends Pane {
	private Controller c;
	private List<String> lenguajes;
	public SeleccionLenguajes(){
		
	}
	
	public Pane SeleccionLenguajes(ArrayList<String> f,Controller c){
		this.c=c;
		VBox box = new VBox(10);//Contenedor de los elementos
		HBox botonLabel = new HBox(5);//Contenedor del boton mas el label de aviso
		///Elementos 
		
		Label leng = new Label("Elige el lenguaje"); //Noombre del lenguaje
		ListView<String> languageList = new ListView<String>(); //Lista de los temas
		ObservableList<String> obsTemas =FXCollections.observableArrayList(f);//permite ver la seleccion
		languageList.setItems(obsTemas);
		Label error = new Label();
		Label aviso = new Label("Recuerda enlazar los compiladores");
		Button comenzar = new Button("Comenzar");//Boton para cargar el tema seleccionado y avanzar en la aplicacion
		comenzar.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= languageList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.selectedLanguage(s.getSelectedItem());//Se carga el tema seleccionado
				else 
					error.setText("Se debe seleccionar un lenguaje");
			}
			
		});
		
		
		Button settings = new Button("Ajustes");//Boton de ajustes para seleccionar el compilador
		
		
		
		settings.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= languageList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.showPortada(s.getSelectedItem());//Se carga el tema seleccionado
				else 
					error.setText("Se debe seleccionar un lenguaje");
				
			}
		});
		
		
		//Parte estetica
		box.setPadding(new Insets(20));
		
		leng.getStyleClass().add("leng");
		comenzar.getStyleClass().add("comenzar");
		error.getStyleClass().add("error");
		box.getStylesheets().add("/application/view/css/tema.css");
		
		
		///Añadir elementos a la vista
		box.getChildren().addAll(leng);
		box.getChildren().addAll(languageList);
		botonLabel.getChildren().addAll(comenzar);
		botonLabel.getChildren().addAll(error);
		botonLabel.getChildren().addAll(aviso);
		botonLabel.getChildren().addAll(settings);
		box.getChildren().addAll(botonLabel);
		
		box.setPrefSize(600, 600);
		
		
		return box;

	}
}
