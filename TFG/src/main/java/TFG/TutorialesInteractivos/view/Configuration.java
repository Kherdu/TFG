package TFG.TutorialesInteractivos.view;

import java.io.File;
import java.util.List;

import TFG.TutorialesInteractivos.controller.Controller;
import TFG.TutorialesInteractivos.model.Lenguaje;
import TFG.TutorialesInteractivos.utilities.InternalUtilities;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Vista para modificar la configuración de la aplicacion
 * 
 * @authors Carlos, Rafa
 *
 */
public class Configuration extends Pane {

	public Pane firstConfiguration(Controller c) {

		GridPane root = new GridPane(); // Panel principal

		Label dependencies = new Label("Ruta fichero dependencias: ");// label
																		// de
																		// dependencias
		TextField pathDep = new TextField();// Campo donde se muestra la ruta
											// del archivo dependences
		Button search = new Button("Buscar");// Boton para buscar el archivo de
												// dependencias
		Button acceptPath = new Button("aceptarPath"); // boton para aceptar el
														// path una vez se añade
														// por primera vez

		// Lista con los lenguajes y la ruta de su compilador
		TableView<Lenguaje> languageList = new TableView<Lenguaje>();
		ObservableList<Lenguaje> data = FXCollections.observableArrayList();
		List<Lenguaje> ls = null;
		if (c.getExternalPath() != null) {
			pathDep.setText(c.getExternalPath());
			ls = c.getLanguagesList();
			data.setAll(ls);
		}
		dependencies.setMnemonicParsing(true);
		dependencies.setLabelFor(pathDep);

		Label languagesLabel = new Label("LENGUAJES");

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
		
		Label warning = new Label("Para avanzar todos los lenguajes han de estar configurados");

		root.add(dependencies, 0, 0);
		root.add(pathDep, 1, 0);
		root.add(search, 2, 0);
		root.add(acceptPath, 3, 0);
		root.add(languagesLabel, 0, 1);
		root.add(languageList, 0, 2);
		root.add(back, 0, 3);
		root.add(change, 1, 3);
		root.add(accept, 2, 3);
		root.add(warning, 0, 4);

		languagesLabel.setAlignment(Pos.TOP_CENTER);
		warning.setAlignment(Pos.TOP_CENTER);
		
		GridPane.setConstraints(dependencies, 0, 0, 1, 1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(pathDep, 1, 0, 1, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(search, 2, 0, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(acceptPath, 3, 0, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(languagesLabel, 0, 1, 4, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(languageList, 0, 2, 4, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(back, 0, 3, 1, 1, HPos.LEFT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(change, 1, 3, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(accept, 3, 3, 1, 1, HPos.RIGHT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(warning, 0, 4, 4, 1, HPos.CENTER, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));

		// Listeners
		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				c.start();
			}
		});
		
		acceptPath.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// comprobamos si la ruta está seleccionada y el si el arbol
				// está bien hecho
				System.out.println(pathDep.getText());
				if (pathDep.getText()=="") {
					File f = new File(pathDep.getText());
					if (f.exists() && f.isDirectory()) {						
							List<String> lanL = InternalUtilities.getDirectoryList(pathDep.getText());
							// añadimos los lenguajes a la lista
							
							for (String s : lanL) {
								Lenguaje addedL = new Lenguaje(s, null);
								
								data.add(addedL);
							}
							 
							data.setAll(c.getLanguagesList());
						}
				} else {
					warning.setText("Primero selecciona directorio de recursos");
				}

			}
		});
		accept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				c.savePrefs(pathDep.getText(), data);
				c.start();
			}
		});

		change.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// este es para el lenguaje
				Lenguaje l = languageList.getSelectionModel().getSelectedItem();
				if (l != null) {

					c.muestraSeleccion(l);
					data.set(data.indexOf(l), c.getLanguageAttributes());
				} else {
					warning.setText("Selecciona un lenguaje antes");
				}
				
			}
		});

		search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// este es para el directorio
				c.muestraSeleccion(null);
				pathDep.setText(c.getExternalPath());
			}
		});
		languagesLabel.getStyleClass().add("tittle");
		accept.getStyleClass().add("start");
		change.getStyleClass().add("start");
		back.getStyleClass().add("start");
		languageList.setId("table");
		warning.getStyleClass().add("error");
		root.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());

		return root;
	}

}
