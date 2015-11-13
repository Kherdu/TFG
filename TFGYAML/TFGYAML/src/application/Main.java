package application;
	
import java.io.File;
import java.io.IOException;

import application.controller.Controller;
import application.model.Tema;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import com.github.rjeschke.txtmark.*;
import javafx.scene.web.*;

public class Main extends Application {
	
	private Stage primaryStage;
	private VBox root;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			Controller c= new Controller();
			c.cargaModelo("Tema1");
			Tema t= c.getTema();
			// Este objeto se deberia pasar a la vista para que lo muestre
			//temporal para pruebas
			
			//obtenemos los atributos
			String tituloPreProcesado= t.getTitulo();
			String introPreProcesado= t.getIntroduccion();
			//pasamos txtmark
			String tituloProcesado= Processor.process(tituloPreProcesado);
			String introProcesado= Processor.process(introPreProcesado);
			
			String htmlProcesado= tituloProcesado+introProcesado;
			//fin temporal para pruebas
			this.primaryStage=primaryStage;
			this.primaryStage.setTitle("Prueba");
			//initLayout();
			//showTemas();
			showIntroTema(htmlProcesado);
			
			
			int num= new File("resources").list().length;
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void showIntroTema(String html) {
		
		Scene scene= new Scene(new Group());
		root= new VBox();
		WebView browser= new WebView();
		WebEngine engine= browser.getEngine();
		
		ScrollPane panelTexto = new ScrollPane();
		panelTexto.setContent(browser);
		engine.loadContent(html);
		root.getChildren().addAll(panelTexto);
		scene.setRoot(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	private void showTemas() {
		try{
			FXMLLoader loader= new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/Initial.fxml"));
			AnchorPane initial = loader.load();
			
			
			
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}

	private void initLayout() {
		try{
		FXMLLoader loader= new FXMLLoader();
		loader.setLocation(Main.class.getResource("view/Raiz.fxml"));
		root = (VBox) loader.load();
		
		Scene scene= new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
		}catch (IOException e){
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}