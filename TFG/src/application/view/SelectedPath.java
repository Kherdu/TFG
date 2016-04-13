package application.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SelectedPath 
{
	private FileChooser fc;
	private String path;
	
	public SelectedPath(Stage stage, String lenguaje)
	{
		fc = new FileChooser();
		fc.setTitle(lenguaje);
		this.path = fc.showOpenDialog(stage).getAbsolutePath();
	}
	
	public String getPath()
	{
		return this.path;
	}
}
