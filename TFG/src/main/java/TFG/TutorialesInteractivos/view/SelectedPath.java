package TFG.TutorialesInteractivos.view;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * Muestra un FileChooser para seleccionar el path del compilador
 * @author Carlos
 *
 */
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
