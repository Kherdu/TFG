package TFG.TutorialesInteractivos.view;

import java.util.ArrayList;

import com.sun.media.jfxmedia.events.NewFrameEvent;

import TFG.TutorialesInteractivos.controller.Controller;
//import TFG.TutorialesInteractivos.controller.Controller;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class FirstConfiguration extends Pane {

	public  FirstConfiguration(){
		
	}
	
	public Pane firstConfiguration(Controller c){
		//TODO vendrá dado por el fichero. Hay que borrarlo
		String[] lang = {"python34", "python27", "java"};
		String[] rutas = {"path34", "path27", "pathjava"};
		
		GridPane root = new GridPane(); //Panel principal
		
		Label dependencies = new Label("Ruta fichero dependencias: ");//label de dependencias
		TextField pathDep = new TextField();//Campo donde se muestra la ruta del archivo dependences
		Button search = new Button("Buscar");//Boton para buscar el archivo de dependencias
		
		dependencies.setMnemonicParsing(true);
		dependencies.setLabelFor(pathDep);
		
		Label languagesLabel = new Label("LENGUAJES");
		
		//Sec Combinan los dos arrays
		ArrayList<String> langMorePath = new ArrayList<>();
		for (int i = 0; i < lang.length; i++)
		{
			langMorePath.add(lang[i]+ "-> Path: "+rutas[i]);
		}
		//Lista con los lenguajes y la ruta de su compilador 
		ListView<String> languagesList = new ListView<String>();
		ObservableList<String> obsLanguages =FXCollections.observableArrayList (langMorePath);
		languagesList.setItems(obsLanguages);
		
		Button change = new Button("Cambiar compilador");
		Button back = new Button("Atras");
		Button accept = new Button("Aceptar");
		
		root.add(dependencies, 0, 0);
		root.add(pathDep, 1, 0);
		root.add(search, 2, 0);
		root.add(languagesLabel, 0, 1);
		root.add(languagesList, 0, 2);
		root.add(back, 0, 3);
		root.add(change, 1, 3);
		root.add(accept, 2, 3);
		
		languagesLabel.setAlignment(Pos.TOP_CENTER);
		change.setAlignment(Pos.TOP_CENTER);
		GridPane.setConstraints(dependencies, 0, 0, 1,1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(pathDep, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(search, 2, 0, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(languagesLabel, 0, 1, 3, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(languagesList, 0, 2, 3, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(back, 0, 3, 1, 1, HPos.LEFT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(change, 1, 3, 1, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(accept, 2, 3, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
	
		
		//Listeners
		back.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				c.goMenu();
			}
		});
		
		accept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				
			}
		});
		
		change.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				new SelectedPath(c.getPrimaryStage(), "");
				
			}
		});
		
		
		languagesLabel.getStyleClass().add("tittle");
		back.getStyleClass().add("start");
		back.getStyleClass().add("change");
		back.getStyleClass().add("back");
		root.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());		
		
		return root;
	}

	
}
