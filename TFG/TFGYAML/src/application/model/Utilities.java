package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Utilities {
	
	public static String  modifyImg(String html){
		String        pattern = "(<img src=\")(file:)(.*?)(\".*?>)"; //Patron de la cadena a buscar
		
		 
		// Compilar el patron ignorando si esta en mayusculas o minusculas
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(html);
		
		
		
		System.out.println("ENCONTRADOS: ");
		while(matcher.find()){
			System.out.println(matcher.group());
			File im = new File(matcher.group(3));//Me quedo con la ruta de la imagen
			System.out.println("La ruta es: "+im.getAbsolutePath());
			html = html.replace(matcher.group(), matcher.group(1)+im.getAbsolutePath()+matcher.group(4));
		
		}
		return html;
	}
	
	public static WebView creaBrowser(String html) {
		InputStream file;
		String aux="";
		try {
			file = new FileInputStream("resources/css/estilo.css");
			aux = fileToString(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    
		final String CSS = aux;
		 WebView browser = new WebView();
	        final WebEngine webEngine = browser.getEngine();

	        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
	            if (newState == State.SUCCEEDED) {
	                Document doc = webEngine.getDocument() ;
	                org.w3c.dom.Element styleNode = doc.createElement("style");
	                org.w3c.dom.Text styleContent = doc.createTextNode(CSS);
	                styleNode.appendChild(styleContent);
	                doc.getDocumentElement().getElementsByTagName("head").item(0).appendChild(styleNode);

	                System.out.println(webEngine.executeScript("document.documentElement.innerHTML"));
	            }
	        });
	        webEngine.loadContent(html);

	        
		return browser;
	}
	
	private static String fileToString(InputStream file)
	{
		String style ="";
		Scanner sc = new Scanner(file);
		
		while (sc.hasNextLine()) {			
			 style += sc.nextLine()+"\n";
		}
		sc.close();
		
		return style;
	}

}
