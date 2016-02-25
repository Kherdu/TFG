package application.view;



import application.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class Portada extends Pane
{
	Controller c;
	
	
	public Portada(){
		
	}
	
	public Pane portada(Controller c) 
	{
		this.c = c;
		VBox container;
		Label titulo;
		HBox selectedPath;
		TextField ruta;
		Button select;
		Button next;
	
		
		container = new VBox();
		titulo = new Label("Aprende Python");
		ruta = new TextField();
		select = new Button("Seleccion de python");
		selectedPath = new HBox();
		next = new Button ("Comenzar tutorial");
		
		selectedPath.getChildren().addAll(ruta);
		selectedPath.getChildren().addAll(select);
		selectedPath.getChildren().addAll(next);
		
		container.getChildren().addAll(titulo);
		container.getChildren().addAll(selectedPath);
		
		select.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				c.muestraSeleccion();
			}
		});
		
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				c.showStart();
				
			}
			
		});
		
		container.setPrefSize(600, 600);
		
		return container;
		
	}
	
}
