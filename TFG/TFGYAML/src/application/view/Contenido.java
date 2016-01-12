package application.view;

import java.util.ArrayList;
import java.util.List;


import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.pegdown.ast.TextNode;

import application.controller.Controller;
import application.model.Elemento;
import application.model.Explicacion;
import application.model.Opciones;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	
	public Contenido(){
		
	}

	public Pane contenido(Elemento e, Controller c){
		this.e=e;
		this.c=c;
		VBox box = new VBox();
		
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		PegDownProcessor pro = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		String content=null;
		// TODO Falta Añadir un area de texto para las preguntas de codigo. No se me ocurre como hacerlo
		if (e instanceof Explicacion){
			content = pro.markdownToHtml(((Explicacion) e).getTexto()); 
		}
		
		TextArea codigo = new TextArea("pene");
		HBox buttons = new HBox();
		
		//TODO listeners
		Button prior = new Button("Atras");
		Button next = new Button("Siguiente");
		Button help = new Button("Ayuda");
		Button resolve = new Button("Resolver");
		
		buttons.getChildren().addAll(prior);
		buttons.getChildren().addAll(next);
		buttons.getChildren().addAll(help);
		buttons.getChildren().addAll(resolve);
		
		WebView text=new WebView();
		WebEngine engine= text.getEngine();
		engine.loadContent(content);
		text.setMaxHeight(100);
		box.getChildren().addAll(text);
		box.getChildren().addAll(codigo);
		box.getChildren().addAll(buttons);
		scene.setRoot(box);
		return box;
		
		
	}

}
