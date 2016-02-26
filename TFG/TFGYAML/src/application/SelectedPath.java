package application;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectedPath 
{
	private FileChooser fc;
	private String path;
	
	public SelectedPath(Stage stage)
	{
		fc = new FileChooser();
		fc.setTitle("Seleccion de pyhton");
		this.path = fc.showOpenDialog(stage).getAbsolutePath();
		
	}
	
	public String getPath()
	{
		return this.path;
	}
}
