package TFG.TutorialesInteractivos.view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Popup;
import TFG.TutorialesInteractivos.controller.Controller;
import TFG.TutorialesInteractivos.model.Codigo;
import TFG.TutorialesInteractivos.model.Elemento;
import TFG.TutorialesInteractivos.model.Explicacion;
import TFG.TutorialesInteractivos.model.Opciones;
import TFG.TutorialesInteractivos.model.Pregunta;
import TFG.TutorialesInteractivos.model.Sintaxis;
import TFG.TutorialesInteractivos.utilities.Utilities;

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
		Button pistas = new Button("INFO"); 
		
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

		Button menu = new Button("Menu principal");

		buttonsCode.getChildren().addAll(resolve);
		buttonsCode.getChildren().addAll(help);

		menu.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				c.goMenu();
			}

		});

		
		help.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				final Popup popup = new Popup();
				String helpText = e.getPista();
				Label popupLabel = new Label(helpText);
				popup.setAutoHide(true);
				popupLabel.setStyle("-fx-border-color: black; -fx-background-color: white");
				
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
						pista.setStyle("-fx-background-color: green");
						c.enableNextStep(selected);
						p.enabledProperty().setValue(enabled+1);
						pistas.setVisible(false);

					} else {
						pista.setText("HAS FALLADO");
						pista.setStyle("-fx-background-color: red");
						pistas.setVisible(false);
					}

				} // Fin de opciones

				else if (e instanceof Codigo) // La pregunta es de tipo codigo
				{
					Codigo pc = (Codigo) e;
					String code = codigo.getText();

					if (c.corrige(code, pc))// Se manda el codigo al controlador
											// para que el modelo lo compruebe
					{
						pista.setText("CORRECTO");
						pista.setStyle("-fx-background-color: green");
						pistas.setVisible(false);
						c.enableNextStep(selected);
						p.enabledProperty().setValue(enabled+1);
						
						
					} else {//
						pista.setText("HAS FALLADO: " + pc.getCorrection().getMessage());
						pista.setStyle("-fx-background-color: red");
						pistas.setVisible(true);
					}

				} else if (e instanceof Sintaxis) {
					// TODO cuando estén las de sintaxis
				}
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

		/*RowConstraints row1 = new RowConstraints();
		RowConstraints row2 = new RowConstraints();
		row1.setPercentHeight(75);
		row2.setPercentHeight(25);
		
		mainPane.getRowConstraints().addAll(row1, row2);*/
		mainPane.add(container, 0, 0);
		mainPane.add(p, 0, 1);
		GridPane.setConstraints(container, 0, 0, 2,1, HPos.LEFT, VPos.TOP, Priority.ALWAYS, Priority.NEVER, new Insets(5));
		GridPane.setConstraints(p, 0, 1, 2, 1, HPos.LEFT, VPos.BOTTOM, Priority.ALWAYS, Priority.ALWAYS, new Insets(5));
		
		
		codigoLab.getStyleClass().add("labcode");
		tipo.getStyleClass().add("tipo");
		respuestaBox.getStyleClass().add("respuestaBox");
		mainPane.getStylesheets().add(getClass().getResource("/css/contenido.css").toExternalForm());
		
		return mainPane;
	}

}
