package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.controller.Controller;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Tema;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

import org.pegdown.Extensions;
import org.pegdown.Parser;
import org.pegdown.PegDownProcessor;

import com.github.rjeschke.txtmark.*;
import javafx.scene.web.*;

public class Main extends Application{

	private Stage primaryStage;
	private BorderPane rootLayout;
	private static Controller c;

	public static void main(String[] args) {

		
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		c = new Controller();
		c.cargaModelo("yaml/paGuarrearPreguntas");
		c.launch();
		
	}

	
}
