package TFG.TutorialesInteractivos.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import TFG.TutorialesInteractivos.controller.Controller;

public class Settings extends Pane {

	public Settings() {

	}

	public Pane settings(Controller c, String path) {

		// TODO, aparte de modificar las rutas de los compiladores en el archivo
		// de preferencias, poder modificar la carpeta de recursos externos
		GridPane root = new GridPane();

		Label tittle = new Label("Configuracion de Path");// Label de titulo de
															// la vista
		Label lPath = new Label("Ruta del compilador: ");// label de titulo al
															// textfield
		TextField tfPath = new TextField(path); // Campo con el path del
												// compilador
		Button back = new Button("Volver al menu principal");// Boton de volver
																// al menu
																// principal
		Button select = new Button("Seleccion de ruta");// Muestra la seleccion
														// del compilador
		Button accept = new Button("Aceptar");// Confirmacion de la seleccion de
												// compilador
		Label error = new Label();

		tittle.setAlignment(Pos.TOP_CENTER);

		root.add(tittle, 0, 0);
		root.add(lPath, 0, 1);
		root.add(tfPath, 1, 1);
		root.add(select, 2, 1);
		root.add(accept, 0, 2);
		root.add(back, 1, 2);
		root.add(error, 1, 3);

		lPath.setMnemonicParsing(true);
		lPath.setLabelFor(tfPath);

		GridPane.setConstraints(tittle, 0, 0, 2, 1, HPos.CENTER, VPos.TOP, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(lPath, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, Priority.NEVER, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(tfPath, 1, 1, 1, 1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(select, 2, 1, 2, 1, HPos.RIGHT, VPos.BOTTOM, Priority.NEVER, Priority.NEVER,
				new Insets(5));
		GridPane.setConstraints(accept, 0, 2, 2, 1, HPos.LEFT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(back, 2, 2, 1, 1, HPos.RIGHT, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));
		GridPane.setConstraints(error, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, Priority.ALWAYS, Priority.ALWAYS,
				new Insets(5));

		select.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				c.muestraSeleccion();
				tfPath.setText(Controller.executable);
			}
		});

		accept.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String r = tfPath.getText();
				if (r.equalsIgnoreCase("")) {
					error.setText("DEBE HABER UN COMPILADOR SELECCIONADO");
				} else {
					c.setPath(r);
					c.start();
				}
			}

		});

		back.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				c.start();

			}
		});

		tittle.getStyleClass().add("tittle");
		accept.getStyleClass().add("start");
		back.getStyleClass().add("start");
		select.getStyleClass().add("start");
		error.getStyleClass().add("error");
		root.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());

		return root;

	}

}
