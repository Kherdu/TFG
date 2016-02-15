package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Codigo;
import application.model.Elemento;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Sintaxis;
import javafx.collections.ObservableMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Contenido extends Pane{
	
	private Elemento e;
	private Controller c;
	//private int leccion;
	private final ToggleGroup group = new ToggleGroup();
	
	public Contenido(){	
	}

	public Pane contenido(Elemento e, Controller c, int leccion){
		this.e=e;
		this.c=c;
		
		Label tipo = new Label("Explicacion");
		
		
		
		if (e instanceof Pregunta)
			tipo.setText("Pregunta");
		
		tipo.setAlignment(Pos.TOP_CENTER);

		VBox box = new VBox(10);//Contenido de toda la ventana
		VBox contenedor = new VBox(5); //Texto y campo de respuesta si es una pregunta
		
		
		String content=null;
		
		content = c.markToHtml(e.getTexto());
		WebView text=new WebView();//Campo donde va la explicacion o el enunciado
		WebEngine engine= text.getEngine();
		engine.loadContent(content);
		
		text.setMaxHeight(300);
		
		contenedor.getChildren().addAll(tipo);
		contenedor.getChildren().addAll(text);
		
		TextArea codigo = new TextArea("Escriba aqui su codigo");
		
		Label pista = new Label("pista");
		
		
		VBox opciones = new VBox();
		
		if (e instanceof Opciones)
		{
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
			contenedor.getChildren().addAll(opciones);
			contenedor.getChildren().addAll(pista);
		}
		else{
			if (e instanceof Pregunta)
			{
				contenedor.getChildren().addAll(codigo);
				contenedor.getChildren().addAll(pista);
			}
		}
		
		contenedor.setMinHeight(500);
		box.getChildren().addAll(contenedor);
		HBox buttons = new HBox(10);
		
		
		Button prior = new Button("Atras");
		Button next = new Button("Siguiente");
		Button help = new Button("Ayuda");
		Button resolve = new Button("Resolver");
		Button menu = new Button("Menu principal");
		
		///listeners
		prior.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				c.prevElem(leccion);
				
			}
			
		});
		
		next.setOnAction(new EventHandler<ActionEvent>(){
		
			public void handle(ActionEvent event) {
				c.nextElem(leccion);
				
			}
			
		});
		
		
		
		menu.setOnAction(new EventHandler<ActionEvent>(){
			
			public void handle(ActionEvent event) {
				c.refresh();
			}
			
		});
		
		help.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (e instanceof Pregunta)
					pista.setText(c.muestraPista());				
			}
		});
		
		//TODO faltan las preguntas de tipo codigo y sintaxis
		resolve.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (e instanceof Opciones)
				{
					ArrayList<Integer> resp = new ArrayList<Integer>();
					if (!((Opciones)e).getMulti()) //Si la pregunta no es multirespuesta
					{
						int i = 0;//Contador de la posicion de la opcion que se analiza
						for (Node o : opciones.getChildren()) //Recorre el array de RadioButtons
						{
							i++;
							if (((RadioButton) o).isSelected()) //Comprueba si la opcion está seleccionada
								resp.add(i);//Se añade al array de respuestas
						}
					}
					else //La pregunta es multirespuesta
					{	
						int i = 0;//Contador de la posicion de la opcion que se analiza
						for (Node o : opciones.getChildren()) //Recorre el array de CheckBox
						{
								i++;
								if (((CheckBox) o).isSelected()) 
									resp.add(i);// meter respuestas elegidas en array
						}			
					}
					//TODO cambiar la forma de notificacion
					System.out.println(c.corrige(resp,(Pregunta)e));//Se corrige la pregunta
				}//Fin de opciones
				else if (e instanceof Codigo || e instanceof Sintaxis)
				{
					String code = codigo.getText();
					System.out.println(code);
				}
			}
		});
		
		//poner botones en el panel
		buttons.getChildren().addAll(prior);
		buttons.getChildren().addAll(next);
		buttons.getChildren().addAll(help);
		buttons.getChildren().addAll(resolve);
		buttons.getChildren().addAll(menu);
		
		box.setPadding(new Insets(20));
		box.getChildren().addAll(buttons);
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		
		tipo.getStyleClass().add("tipo");
		box.getStylesheets().add("/application/view/css/contenido.css");
		
		box.setPrefSize(600, 600);
		return box;		
	}
	
	

}
