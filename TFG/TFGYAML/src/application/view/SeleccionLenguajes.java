package application.view;

import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import application.SelectedPath;
import application.controller.Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TextArea;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

public class SeleccionLenguajes extends Pane {
	private Controller c;
	private List<String> lenguajes;
	public SeleccionLenguajes(){
		
	}
	
	public Pane SeleccionLenguajes(ArrayList<String> f,Controller c){
		this.c=c;
		
		GridPane pane = new GridPane(); //Panel principal
		
		Label tittle = new Label("Selecciona un lenguaje"); //Label del titulo de la ventana
		ListView<String> languageList = new ListView<String>(); //Lista de los temas
		ObservableList<String> obsTemas =FXCollections.observableArrayList(f);//permite ver la seleccion
		languageList.setItems(obsTemas);
		
		
		//TextArea languageList = new TextArea();//Para probar TODO borrar
		
		
		HBox botonLabel = new HBox();//Contenedor del boton mas el label de aviso
		Button start = new Button("Comenzar");//Boton para comenzar el tutorial con el lenguaje seleccionado
		Label advise = new Label("Recuerda enlazar los compiladores");
		Button settings = new Button("Ajustes");//Boton de ajustes para seleccionar el compilador
		Label error = new Label("Mensaje de error"); //Label que se mostrara con el mensaje de error cuando no haya lenguaje seleccionado
		
		tittle.setAlignment(Pos.TOP_CENTER);
		
		
		
		botonLabel.getChildren().add(start);
		botonLabel.getChildren().add(advise);
		botonLabel.getChildren().add(settings);
		
		HBox.setMargin(start, new Insets(0, 5, 0, 0));
		HBox.setMargin(settings, new Insets(0, 0, 0, 5));
		
		pane.add(tittle, 0, 0);
		pane.add(languageList, 0, 1);
		pane.add(botonLabel, 1, 2);
		pane.add(error,1,3);
		
		
		
		GridPane.setConstraints(tittle, 0, 0, 2,1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(languageList, 0, 1, 2, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(botonLabel, 0, 2, 2, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(error, 1, 3, 1, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
	
		//GridPane.setConstraints(child, columnIndex, rowIndex, columnspan, rowspan, halignment, valignment, hgrow, vgrow, margin);
		
		
		start.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				MultipleSelectionModel<String> s;
				s= languageList.getSelectionModel();
				if (!s.isEmpty())//Se comprueba que hay alguna opcion seleccionada
					c.selectedLanguage(s.getSelectedItem());//Se carga el tema seleccionado
				else 
					error.setText("Se debe seleccionar un lenguaje");
			}
			
		});
		
		
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
		
		
		pane.getStylesheets().add("/application/view/css/tema.css");
	
		return pane;

	}
}
