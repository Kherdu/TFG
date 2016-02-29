package application.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.w3c.dom.Document;

import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

/**
 * Clase que contiene los metodos de modificaion de elementos dentro de la aplicacion
 * @author Carlos
 *
 */
public class Utilities {	
	/**
	 * Modifica el la ruta de la imagen dentro del HTML
	 * @param html
	 * @return
	 */
	public static String  modifyImg(String html){
		String pattern = "(<img src=\"file:///)(.*?)(\".*?>)"; //Patron de la cadena a buscar. 
		//En el yaml el formato ha de ser "file:///"+ ruta relativa a la imagen
		
		// Compilar el patron ignorando si esta en mayusculas o minusculas
		Pattern p = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
		Matcher matcher = p.matcher(html);
		
		while(matcher.find()){
			File im = new File(matcher.group(2));//Me quedo con la ruta de la imagen
			html = html.replace(matcher.group(), matcher.group(1)+im.getAbsolutePath()+matcher.group(3));
		
		}
		return html;
	}
	
	//TODO cambiar este metodo para que devuelva el codigo HTML
	// 	Permitir llamar a esta funcion y modificar el html que se le pasa o modificar el webview desde donde se hae la llamada
	public static WebView creaBrowser(String html) {
		InputStream file;
		String aux="";
		try {
			file = new FileInputStream("resources/css/prueba.css");
			aux = fileToString(file);
		} catch (FileNotFoundException e) {
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

	                //System.out.println(webEngine.executeScript("document.documentElement.innerHTML"));
	            }
	        });
	        webEngine.loadContent(html);

	        
		return browser;
	}
	
	/**
	 * Pasa todo el contenido de un fichero a una cadena
	 * @param file Nomobre del fichero 
	 * @return
	 */
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
	
	/**
	 * Parsea el texto en markdown y lo pasa a texto HTML 
	 * @param mark
	 * @return
	 */
	public static String parserMarkDown(String mark)
	{
		String html;
		PegDownProcessor pro = new PegDownProcessor(Extensions.ALL - Extensions.EXTANCHORLINKS);
		html = pro.markdownToHtml(mark); 
		html = Utilities.modifyImg(html);
		return html;
	}

}
