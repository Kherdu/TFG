package application.view;

import application.CargaConfig;
import application.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
		VBox root; //Contenedor de los elementos 
		Label titulo;//Titulo de la aplicacion
		HBox selectedPath; //Contenedor de los elementos de seleccion del path
		Label rut; //Label con indicacion de campo
		TextField ruta; //Campo de texto en el que se escribe la ruta seleccionada
		Button select; //Boton para seleccionar el compilador del lenguaje
		Button next; //Boton para comezar el tutorial
		Label error; //Etiqueta para mostrar error en el caso de que no haya ruta seleccionada
	
		//Inicialización de los elementos
		root = new VBox(10);
		titulo = new Label("Aprende Python");
		rut = new Label("Path del lenguaje: ");
		ruta = new TextField(CargaConfig.loadConfig());
		select = new Button("Seleccion de python");
		selectedPath = new HBox(20);
		next = new Button ("Comenzar tutorial");
		error = new Label();
		
		//Añadir los elementos 
		selectedPath.getChildren().addAll(rut);
		selectedPath.getChildren().addAll(ruta);
		selectedPath.getChildren().addAll(select);
		selectedPath.getChildren().addAll(next);
		
		//Añadir los elementos al panel principal
		root.getChildren().addAll(titulo);
		root.getChildren().addAll(selectedPath);
		root.getChildren().addAll(error);
		
		select.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				c.muestraSeleccion();
				ruta.setText(Controller.path);
			}
		});
		
		next.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String r = ruta.getText();
				if (null == r)
				{
					error.setText("DEBE HABER UN COMPILADOR SELECCIONADO");
				}
				else
				{
					CargaConfig.saveConfig(r);
					c.setPath(r);
					c.showStart();
				}
			}
			
		});
		
		root.setPadding(new Insets(20));
		root.setPrefSize(600, 600);
		
		return root;
		
	}
	
}
