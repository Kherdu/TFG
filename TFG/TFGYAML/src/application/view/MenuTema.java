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
		//El grupo que se desea agregar, y el tamaño ancho y alto
		//Scene scene = new Scene( box, 300, 300 );
				
		
		///Elementos 
		Label leng = new Label("PYTHON"); //Noombre del lenguaje
		ListView<String> temasList = new ListView<String>(); //Lista de los temas
		ObservableList<String> obsTemas =FXCollections.observableArrayList(temas);//permite ver la seleccion
		temasList.setItems(obsTemas);
		Button comenzar = new Button("Comenzar");//Boton para cargar el tema seleccionado y avanzar en la aplicacion
		comenzar.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= temasList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.selectedTema(s.getSelectedItem());//Se carga el tema seleccionado
				else //TODO cambiar forma de aviso
					System.out.println("No hay tema seleccionado");
			}
			
		});
		box.setPadding(new Insets(20));
		
		comenzar.getStyleClass().add("comenzar");
		box.getStylesheets().add("/application/view/css/tema.css");
		
		///Añadir elementos a la vista
		box.getChildren().addAll(leng);
		box.getChildren().addAll(temasList);
		box.getChildren().addAll(comenzar);
		
		box.setPrefSize(600, 600);
		return box;
		
		
		
	}

}
