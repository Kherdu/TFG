package application.view;

import java.util.ArrayList;
import java.util.List;


import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.TextNode;

import application.controller.Controller;
import application.model.Codigo;
import application.model.Elemento;
import application.model.Explicacion;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Utilities;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

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
		VBox box = new VBox();
		
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		PegDownProcessor pro = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		String content=null;
		box.setMaxSize(600, 600);
		
		if (e instanceof Explicacion){
			content = pro.markdownToHtml(((Explicacion) e).getTexto()); 
			content = Utilities.modifyImg(content);
		}
		if (e instanceof Pregunta)
		{
			content = pro.markdownToHtml(((Pregunta) e).getEnunciado());
			content = Utilities.modifyImg(content);
		
		}
		WebView text=new WebView();
		WebEngine engine= text.getEngine();
		engine.loadContent(content);
		text.setMaxHeight(100);
		
		box.getChildren().addAll(text);
		
		TextArea codigo = new TextArea("Escriba aqui su codigo");
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
			box.getChildren().addAll(opciones);
		}
		else{
			box.getChildren().addAll(codigo);
		}
			
			
		HBox buttons = new HBox();
		
		//TODO listeners ayuda y resolver
		Button prior = new Button("Atras");
		Button next = new Button("Siguiente");
		Button help = new Button("Ayuda");
		Button resolve = new Button("Resolver");
		
		
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
		
		
		
		//poner botones en el panel
		buttons.getChildren().addAll(prior);
		buttons.getChildren().addAll(next);
		buttons.getChildren().addAll(help);
		buttons.getChildren().addAll(resolve);
		
		box.getChildren().addAll(buttons);
		scene.setRoot(box);
		return box;
		
		
	}

}
