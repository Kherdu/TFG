﻿package application.view;

import java.util.ArrayList;
import java.util.List;
import application.controller.Controller;
import application.model.Codigo;
import application.model.Elemento;
import application.model.Explicacion;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Sintaxis;
import application.model.Utilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;

public class Contenido extends Pane {

	private final ToggleGroup group = new ToggleGroup();

	public Contenido() {
	}

	/**
	 * @param e
	 * @param c
	 * @param leccion
	 * @return
	 */
	public Pane contenido(Elemento e, Controller c, int steps, int enabled, int selected) {

		Label tipo = new Label(null);

		if (selected == 1)
			tipo.setText("Introducción");

		if (e instanceof Pregunta) {
			tipo.setText("Pregunta");

		} else if (e instanceof Explicacion) {
			tipo.setText("Explicación");
		}
		GridPane mainPane = new GridPane();
		VBox container = new VBox(5); // Texto y campo de respuesta si es una
										// pregunta
		Pagination p = new Pagination(steps); // paginador
		String content = null;
		// por defecto se habilitan 2, la intro y el siguiente
		p.enabledProperty().setValue(enabled);
		p.currentProperty().setValue(selected);
		// listener para saber cual ha seleccionado.
		p.currentProperty().addListener((prop, oldV, newV) -> {
			// llamamos al método que vale para cambiar de un contenido a otro
			c.lessonPageChange(newV);
		});
		content = c.markToHtml(e.getTexto());

		// Campo donde se escribeel enunciado o la explicacion de la pregunta
		WebView text = Utilities.creaBrowser(content);

		WebEngine engine = text.getEngine();
		engine.loadContent(content);

		container.getChildren().addAll(tipo);
		container.getChildren().addAll(text);

		Label codigoLab = new Label("CODIGO");
		TextArea codigo = new TextArea("Escriba aqui su codigo");

		HBox result = new HBox();// Contenedor donde se muestra la resolucion de
									// la pregunta
		Label pista = new Label();// Indica si la pregunta se ha respondido bien
									// o no
		Button pistas = new Button("INFO"); // TODO el pene de congost
		MenuItem hintsContent = new MenuItem();
		result.getChildren().addAll(pista);
		result.getChildren().addAll(pistas);
		pistas.setAlignment(Pos.BOTTOM_RIGHT);
		pistas.setVisible(false);

		HBox respuestaBox = new HBox(10);// Contenedor con el campo de respuesta
											// y los botones de la pregunta

		// Botones para el envio/ayuda de respuestas
		VBox buttonsCode = new VBox(5);
		Button help = new Button("Ayuda");
		Button resolve = new Button("Resolver");
		buttonsCode.setAlignment(Pos.CENTER_RIGHT);

		VBox opciones = new VBox();

		if (e instanceof Opciones) {
			final Opciones o = (Opciones) e;

			if (!o.getMulti()) {
				List<RadioButton> l = new ArrayList<RadioButton>();
				List<String> opc = o.getOpciones();

				for (Object op : opc) {
					RadioButton rb = new RadioButton();
					rb.setText(op.toString());
					rb.setToggleGroup(group);
					l.add(rb);
				}

				opciones.getChildren().addAll(l);

			} else {
				List<CheckBox> l = new ArrayList<CheckBox>();
				List<String> opc = o.getOpciones();
				for (Object op : opc) {
					CheckBox cb = new CheckBox();
					cb.setText(op.toString());

					l.add(cb);
				}
				opciones.getChildren().addAll(l);
			}
			respuestaBox.getChildren().addAll(opciones);
			respuestaBox.getChildren().addAll(buttonsCode);
			container.getChildren().addAll(respuestaBox);
			container.getChildren().addAll(result);
		} else {
			if (e instanceof Pregunta) {
				container.getChildren().addAll(codigoLab);
				respuestaBox.getChildren().addAll(codigo);
				respuestaBox.getChildren().addAll(buttonsCode);
				container.getChildren().addAll(respuestaBox);
				container.getChildren().addAll(result);
			}
		}

		// contenedor.setMinHeight(500);
		Button menu = new Button("Menu principal");

		buttonsCode.getChildren().addAll(resolve);
		buttonsCode.getChildren().addAll(help);

		menu.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				c.goMenu();
			}

		});

		// help.getItems().setAll(new MenuItem(e.getPista()));// Añade el
		// deplegable al
		// button
		help.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				final Popup popup = new Popup();
				String helpText = e.getPista();
				Label popupLabel = new Label(helpText);
				popup.setAutoHide(true);
				popupLabel.setStyle("-fx-border-color: black; -fx-background-color: white");
				// popup.setAutoFix(true);
				// Calculate popup placement coordinates.
				Node eventSource = (Node) event.getSource();
				Bounds sourceNodeBounds = eventSource.localToScreen(eventSource.getBoundsInLocal());
				popup.setX(sourceNodeBounds.getMinX() - 5.0);
				popup.setY(sourceNodeBounds.getMaxY() + 5.0);
				popup.getContent().addAll(popupLabel);
				popup.show(c.getPrimaryStage());

			}
		});

		resolve.setOnAction(new EventHandler<ActionEvent>() {

			@SuppressWarnings({ "unchecked", "rawtypes" })
			@Override
			public void handle(ActionEvent event) {
				if (e instanceof Opciones) {
					ArrayList<Integer> resp = new ArrayList<Integer>();
					if (!((Opciones) e).getMulti()) // Si la pregunta no es
													// multirespuesta
					{
						int i = 0;// Contador de la posicion de la opcion que se
									// analiza
						for (Node o : opciones.getChildren()) // Recorre el
																// array de
																// RadioButtons
						{
							i++;
							if (((RadioButton) o).isSelected()) // Comprueba si
																// la opcion
																// está
																// seleccionada
								resp.add(i);// Se añade al array de respuestas
						}
					} else // La pregunta es multirespuesta
					{
						int i = 0;// Contador de la posicion de la opcion que se
									// analiza
						for (Node o : opciones.getChildren()) // Recorre array
																// de CheckBox
						{
							i++;
							if (((CheckBox) o).isSelected())
								resp.add(i);// meter respuestas elegidas en
											// array
						}
					}

					if (c.corrige(resp, (Pregunta) e))// Se corrige la pregunta
					{
						pista.setText("CORRECTO");
						c.enableNextStep(selected);
						pistas.setVisible(false);

					} else {
						pista.setText("HAS FALLADO");
						pistas.setVisible(false);
					}

				} // Fin de opciones

				else if (e instanceof Codigo) // La pregunta es de tipo codigo
				{
					Codigo p = (Codigo) e;
					String code = codigo.getText();
					// System.out.println(code);

					if (c.corrige(code, p))// Se manda el codigo al controlador
											// para que el modelo lo compruebe
					{
						pista.setText("CORRECTO");
						c.enableNextStep(selected);

					} else {//
						pista.setText("HAS FALLADO: " + p.getCorrection().getMessage());

						pistas.setVisible(true);
					}

				} else if (e instanceof Sintaxis) {
					// TODO cuando estén las de sintaxis
				}
				// TODO refrescar ventana
				c.refreshWindow();
			}
			

		});

		pistas.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Popup popup = new Popup();
				List<String> hints = ((Codigo) e).getCorrection().getHints();
				String txt = "";
				if (hints != null) {
					for (String h : hints) {
						txt += (h + "\n");
					}
					;
					// hintsContent.setText(txt);

					Label popupLabel = new Label(txt);
					popupLabel.setStyle("-fx-border-color: black; -fx-background-color: white");
					popup.setAutoHide(true);
					popup.setAutoFix(true);
					popup.setOpacity(1.00);
					// Calculate popup placement coordinates.
					Node eventSource = (Node) event.getSource();
					Bounds sourceNodeBounds = eventSource.localToScreen(eventSource.getBoundsInLocal());
					popup.setX(sourceNodeBounds.getMinX() + 5.0);
					popup.setY(sourceNodeBounds.getMaxY() + 1.0);
					popup.getContent().addAll(popupLabel);
					popup.show(c.getPrimaryStage());
				}

			}
		});

		RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		row1.setPercentHeight(75);
		row2.setPercentHeight(25);
		mainPane.getRowConstraints().addAll(row1, row2);
		mainPane.add(container, 1, 0);
		mainPane.add(p, 1, 1);
		codigoLab.getStyleClass().add("labcode");
		tipo.getStyleClass().add("tipo");
		respuestaBox.getStyleClass().add("respuestaBox");
		mainPane.getStylesheets().add(getClass().getResource("/css/contenido.css").toExternalForm());
		mainPane.setPrefSize(600, 600);
		return mainPane;
	}

}
