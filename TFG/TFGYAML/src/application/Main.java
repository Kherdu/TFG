package application;

import application.controller.Controller;
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
		this.primaryStage.setTitle("Prueba");
		c = new Controller(primaryStage);
		c.launch();
		
		
	}

	

	
}
