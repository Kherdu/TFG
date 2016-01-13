package application.view;

import java.util.ArrayList;
import java.util.List;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;

import application.controller.Controller;
import application.model.Leccion;
import application.model.Tema;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MenuLeccion extends Pane{
	
	private Tema t;
	private Controller c;
	
	public MenuLeccion(){
		
	}
	
	
	public Pane menuLeccion(Tema t, Controller c){
		
		this.c=c;
		this.t=t;
		VBox box = new VBox(10);
		//El grupo que se desea agregar, y el tamaño ancho y alto
		Scene scene = new Scene( box, 300, 300 );
		
		box.setMaxSize(600, 600);
		///Elementos 
		PegDownProcessor pro = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		WebView titulo = new WebView();
		WebEngine engine = titulo.getEngine();
		engine.loadContent(pro.markdownToHtml(t.getTitulo() + "\n" + t.getIntroduccion()));
		
		ListView<String> leccionList = new ListView<String>();
		ObservableList<String> obsLecciones =FXCollections.observableArrayList (t.getNameLecciones());
		leccionList.setItems(obsLecciones);
		
		// TODO cambiar nombre... te odio
		Button alTurron = new Button("Al turron!");
		alTurron.setOnAction(new EventHandler<ActionEvent>(){
		MultipleSelectionModel<String> s;
			@Override
			public void handle(ActionEvent event) {
				s= leccionList.getSelectionModel();
				c.selectedLeccion(s.getSelectedIndex());
				
			}
			
		});
		///Añadir elementos a la vista
		box.getChildren().addAll(titulo);
		box.getChildren().addAll(leccionList);
		box.getChildren().addAll(alTurron);
		
		box.setPadding(new Insets(20));
		
		return box;
		
	}
}
