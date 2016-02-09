package application.view;

import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Elemento;
import application.model.Opciones;
import application.model.Pregunta;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	private int leccion;
	
	
	
	public Contenido(){	
	}

	public Pane contenido(Elemento e, Controller c, int leccion){
		this.e=e;
		this.c=c;
		this.leccion = leccion;
		
		VBox box = new VBox(10);//Contenido de toda la ventana
		VBox contenedor = new VBox(5); //Texto y campo de respuesta si es una pregunta
		
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		String content=null;
		box.setMaxSize(600, 600);
		
		
		content = c.markToHtml(e.getTexto());
		WebView text=new WebView();
		WebEngine engine= text.getEngine();
		engine.loadContent(content);
		text.setMaxHeight(100);
		
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
				final ToggleGroup group = new ToggleGroup();
				for (Object op : opc) {
					RadioButton cb = new RadioButton();
					cb.setText(op.toString());
					cb.setToggleGroup(group);
					l.add(cb);
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
			
		box.getChildren().addAll(contenedor);
		HBox buttons = new HBox(10);
		
		//TODO listener resolver
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
				pista.setText(c.muestraPista());				
			}
		});
		
		resolve.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (e instanceof Opciones){
					buttons.getChildren();
					ArrayList<Integer> resp = new ArrayList<Integer>();

					int i = 0;
					for (Node n : buttons.getChildren()) {
						i++;
						if (!((Opciones) e).getMulti()) {
							if (((RadioButton) n).isSelected()) {
								resp.add(i);
								// meter respuestas elegidas en array
							}
						}else if (((CheckBox) n).isSelected()) {

							resp.add(i);
							// meter respuestas elegidas en array
						}
					}
					System.out.println(c.corrige(resp, (Pregunta)e));
					// comprobar respuestas correctas y escribir en ventana
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
		scene.setRoot(box);
		return box;		
	}

}
