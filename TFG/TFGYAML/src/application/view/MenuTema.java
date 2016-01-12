package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class MenuTema extends Pane{
	
	private List<String> temas;
	private Controller c;
	
	public MenuTema(){
	}
	
	public Pane menuTema(ArrayList<String> f, Controller c){
		
		this.c=c;
		this.temas=f;
		VBox box = new VBox();
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
				
		scene.getStylesheets().add("/TFG/TFGYAML/src/application/view/tema.css");
		
		///Elementos 
		Label leng = new Label("PYTHON");
		ListView<String> temasList = new ListView<String>();
		ObservableList<String> obsTemas =FXCollections.observableArrayList(temas);
		temasList.setItems(obsTemas);
		Button comenzar = new Button("Comenzar");
		comenzar.setOnAction(new EventHandler<ActionEvent>(){
		MultipleSelectionModel<String> s;
			@Override
			public void handle(ActionEvent event) {
				s= temasList.getSelectionModel();
				c.selectedTema(s.getSelectedItem());
				
			}
			
		});
		
		///Añadir elementos a la vista
		box.getChildren().addAll(leng);
		box.getChildren().addAll(temasList);
		box.getChildren().addAll(comenzar);
		return box;
		
		
		
	}

}
