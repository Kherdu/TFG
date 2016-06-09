package TFG.TutorialesInteractivos.view;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Muestra un FileChooser para seleccionar el path del compilador
 * 
 * @authors Carlos, Rafa
 *
 */
public class SelectedPath 
{
	private String path;
	
	public SelectedPath(Stage stage, String lenguaje){
		FileChooser fc = new FileChooser();
		this.path = fc.showOpenDialog(stage).getAbsolutePath();
	}
	
	public SelectedPath(Stage stage){
		DirectoryChooser chooser = new DirectoryChooser();
		this.path = chooser.showDialog(stage).getAbsolutePath();
	}
	
	public String getPath(){
		return this.path;
	}
}
