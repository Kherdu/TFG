package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Codigo;
import application.model.Elemento;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Sintaxis;
import application.model.Utilities;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Contenido extends Pane {

	private Elemento e;
	private Controller c;
	// private int leccion;
	private final ToggleGroup group = new ToggleGroup();

	public Contenido() {
	}

	/**
	 * @param e
	 * @param c
	 * @param leccion
	 * @return
	 */
	public Pane contenido(Elemento e, Controller c, int leccion) {
		this.e = e;
		this.c = c;

		Label tipo = new Label("Explicacion");

		if (e instanceof Pregunta)
			tipo.setText("Pregunta");

		VBox box = new VBox(10);// Contenido de toda la ventana
		VBox contenedor = new VBox(5); // Texto y campo de respuesta si es una pregunta
		String content = null;

		content = c.markToHtml(e.getTexto());
		WebView text = Utilities.creaBrowser(content);//Campo donde se escribe el enunciado o la explicacion de la pregunta
		
		WebEngine engine = text.getEngine();
		engine.loadContent(content);

		text.setMaxHeight(300);

		contenedor.getChildren().addAll(tipo);
		contenedor.getChildren().addAll(text);
		
		Label codigoLab = new Label ("CODIGO");
		TextArea codigo = new TextArea("Escriba aqui su codigo");
		
		HBox result = new HBox();//Contenedor donde se muestra la resolucion de la pregunta
		Label pista = new Label();//Indica si la pregunta se ha respondido bien o no
		pista.setPrefWidth(300);
		MenuButton pistas = new MenuButton("INFO"); //TODO el pene de congost
		MenuItem hintsContent = new MenuItem();
		result.getChildren().addAll(pista);
		result.getChildren().addAll(pistas);
		pistas.setAlignment(Pos.BOTTOM_RIGHT);
		pistas.setVisible(false);
		
		
		HBox respuestaBox = new HBox(10);//Contenedor con el campo de respuesta y los botones de la pregunta
		
		//Botones para el envio/ayuda de respuestas
		VBox buttonsCode = new VBox(5);
		MenuButton help = new MenuButton("Ayuda");
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
			contenedor.getChildren().addAll(respuestaBox);
			contenedor.getChildren().addAll(result);
		} else {
			if (e instanceof Pregunta) {
				contenedor.getChildren().addAll(codigoLab);
				respuestaBox.getChildren().addAll(codigo);
				respuestaBox.getChildren().addAll(buttonsCode);
				contenedor.getChildren().addAll(respuestaBox);
				contenedor.getChildren().addAll(result);
			}
		}

		contenedor.setMinHeight(500);
		box.getChildren().addAll(contenedor);
		HBox buttons = new HBox(10);
		
		

		Button prior = new Button("Atras");
		Button next = new Button("Siguiente");
		Button menu = new Button("Menu principal");

		buttonsCode.getChildren().addAll(resolve);
		buttonsCode.getChildren().addAll(help);
		
		/// listeners
		prior.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				c.prevElem(leccion);

			}

		});

		next.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				c.nextElem(leccion);

			}

		});

		menu.setOnAction(new EventHandler<ActionEvent>() {

			public void handle(ActionEvent event) {
				c.refresh();
			}

		});

		help.getItems().setAll(new MenuItem(e.getPista()));//Añade el deplegable al button
		
		resolve.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (e instanceof Opciones) {
					ArrayList<Integer> resp = new ArrayList<Integer>();
					if (!((Opciones) e).getMulti()) // Si la pregunta no es
													// multirespuesta
					{
						int i = 0;// Contador de la posicion de la opcion que se analiza
						for (Node o : opciones.getChildren()) // Recorre el
																// array de
																// RadioButtons
						{
							i++;
							if (((RadioButton) o).isSelected()) // Comprueba si la opcion está seleccionada
								resp.add(i);// Se añade al array de respuestas
						}
					} else // La pregunta es multirespuesta
					{
						int i = 0;// Contador de la posicion de la opcion que se
									// analiza
						for (Node o : opciones.getChildren()) // Recorre array de CheckBox
						{
							i++;
							if (((CheckBox) o).isSelected())
								resp.add(i);// meter respuestas elegidas en array
						}
					}
					
					if (c.corrige(resp, (Pregunta) e))// Se corrige la pregunta
					{
						pista.setText("CORRECTO");
						pistas.setVisible(false);
					}
					else{
						pista.setText("HAS FALLADO");
						pistas.setVisible(true);
					}
																		
				} // Fin de opciones
				
				else if (e instanceof Codigo) //La pregunta es de tipo codigo
				{
					Codigo p= (Codigo) e;
					String code = codigo.getText();
					//System.out.println(code);

					if(c.corrige(code, p))//Se manda el codigo al controlador para que el modelo lo compruebe
					{
						pista.setText("CORRECTO");
					}
					else{//asd
						pista.setText("HAS FALLADO: "+p.getCorrection().getMessage());
						List<String> hints = p.getCorrection().getHints();
						String txt = "";
						for (String h: hints){
							txt += (h + "\n"); 
						};
						hintsContent.setText(txt);
						pistas.getItems().setAll(hintsContent);
						pistas.setVisible(true);
					}
				} else if (e  instanceof Sintaxis){
					//TODO cuando estén las de sintaxis
				}
			}//fin de tipo codigo
		});

		// poner botones en el panel
		buttons.getChildren().addAll(prior);
		buttons.getChildren().addAll(next);
		//buttons.getChildren().addAll(help);
		//buttons.getChildren().addAll(resolve);
		buttons.getChildren().addAll(menu);
		
		/*if (e instanceof Explicacion)
		{
			resolve.setVisible(false);
			help.setVisible(false);
		}*/

		box.setPadding(new Insets(20));
		box.getChildren().addAll(buttons);
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		
		codigoLab.getStyleClass().add("labcode");
		tipo.getStyleClass().add("tipo");
		box.getStylesheets().add("/application/view/css/contenido.css");

		box.setPrefSize(600, 600);
		return box;
	}

}
