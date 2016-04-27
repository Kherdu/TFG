package TFG.TutorialesInteractivos;
	


import javafx.application.Application;
import javafx.stage.Stage;
import TFG.TutorialesInteractivos.controller.Controller;


/**
 * Clase principal de la aplicación
 * @author Carlos
 *
 */
public class Main extends Application{

	private Stage primaryStage;
	//private BorderPane rootLayout;
	
	
	

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage=primaryStage;
		this.primaryStage.setTitle("Tutoriales Interactivos");
		Controller c = new Controller(primaryStage);
		c.start();
				
	}
}
