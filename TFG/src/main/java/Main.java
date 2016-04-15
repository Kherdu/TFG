package main.java;
	


import javafx.application.Application;
import javafx.stage.Stage;
import main.java.controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


import javafx.application.Application;
import javafx.stage.Stage;


/**
 * Clase principal de la aplicación
 * @author Carlos
 *
 */
public class Main extends Application{

	private Stage primaryStage;
	//private BorderPane rootLayout;
	private static Controller c;
	
	

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("TutoFG");
		c = new Controller(primaryStage);
		c.start();
				
	}
}
