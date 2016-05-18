package TFG.TutorialesInteractivos.view;

import java.util.ArrayList;



import TFG.TutorialesInteractivos.controller.Controller;
import TFG.TutorialesInteractivos.model.Lenguaje;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Vista para modificar la configuración de la aplicacion
 * @author Carlos
 *
 */
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
		
		//Sec Combinan los dos arrays se podria borrar
		/*ArrayList<String> langMorePath = new ArrayList<>();
		for (int i = 0; i < lang.length; i++)
		{
			langMorePath.add(lang[i]+ "-> Path: "+rutas[i]);
		}*/
		
		//Lista con los lenguajes y la ruta de su compilador 
		TableView languageList = new TableView();
		ObservableList<Lenguaje> data = FXCollections.observableArrayList(
				new Lenguaje("Python34", "Perez"), 
				new Lenguaje("Python27","Garcia"), 
				new Lenguaje("Java", "Juanes")
				);

		languageList.setEditable(true);
		languageList.setVisible(true);
		TableColumn firstNameCol = new TableColumn("Lenguaje");
		firstNameCol.setCellValueFactory(new PropertyValueFactory<Lenguaje, String>("language"));
		TableColumn secondNameCol = new TableColumn("Ruta");
		secondNameCol.setCellValueFactory(new PropertyValueFactory<Lenguaje, String>("path"));
		
		languageList.setItems(data);
		languageList.getColumns().addAll(firstNameCol, secondNameCol);
		
		languageList.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		Button change = new Button("Cambiar compilador");
		Button back = new Button("Atras");
		Button accept = new Button("Aceptar");
		
		root.add(dependencies, 0, 0);
		root.add(pathDep, 1, 0);
		root.add(search, 2, 0);
		root.add(languagesLabel, 0, 1);
		root.add(languageList, 0, 2);
		root.add(back, 0, 3);
		root.add(change, 1, 3);
		root.add(accept, 2, 3);
		
		languagesLabel.setAlignment(Pos.TOP_CENTER);
		change.setAlignment(Pos.TOP_CENTER);
		GridPane.setConstraints(dependencies, 0, 0, 1,1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(pathDep, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(search, 2, 0, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(languagesLabel, 0, 1, 3, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		GridPane.setConstraints(languageList, 0, 2, 3, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
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
		accept.getStyleClass().add("start");
		change.getStyleClass().add("start");
		back.getStyleClass().add("start");
		languageList.setId("table");
		root.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());		
		
		return root;
	}
	
}
