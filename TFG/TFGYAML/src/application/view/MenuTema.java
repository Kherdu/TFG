package application.view;

import java.util.ArrayList;
import java.util.List;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Panel que muestra el menu con los temas que se compone el tutorial
 * @author Carlos
 *
 */
public class MenuTema extends Pane{
	
	private List<String> temas;
	private Controller c;
	
	public MenuTema(){
	}
	
	public Pane menuTema(ArrayList<String> f, Controller c){
		
		this.c=c;//Controlador
		this.temas=f;//Lista de temas
		VBox box = new VBox(10);//Contenedor de los elementos
		HBox botonLabel = new HBox(5);//Contenedor del boton mas el label de aviso
				
		
		///Elementos 
		Label leng = new Label("PYTHON"); //Noombre del lenguaje
		ListView<String> temasList = new ListView<String>(); //Lista de los temas
		ObservableList<String> obsTemas =FXCollections.observableArrayList(temas);//permite ver la seleccion
		temasList.setItems(obsTemas);
		Label error = new Label();
		Button comenzar = new Button("Comenzar");//Boton para cargar el tema seleccionado y avanzar en la aplicacion
		comenzar.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= temasList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.selectedTema(s.getSelectedItem());//Se carga el tema seleccionado
				else 
					error.setText("Se debe seleccionar un tema");
			}
			
		});
		box.setPadding(new Insets(20));
		
		leng.getStyleClass().add("leng");
		comenzar.getStyleClass().add("comenzar");
		error.getStyleClass().add("error");
		box.getStylesheets().add("/application/view/css/tema.css");
		
		///Añadir elementos a la vista
		box.getChildren().addAll(leng);
		box.getChildren().addAll(temasList);
		botonLabel.getChildren().addAll(comenzar);
		botonLabel.getChildren().addAll(error);
		box.getChildren().addAll(botonLabel);
		
		box.setPrefSize(600, 600);
		return box;
		
		
		
	}

}
