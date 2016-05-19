package TFG.TutorialesInteractivos.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStream;





/**
 * Pregunta de tipo Sintaxis
 * @author Carlos
 *
 */
public class Sintaxis extends Pregunta<String>
{
	private String sintaxis;
	
	public Sintaxis(int numero, String enunciado, String pista, String sintaxis, String solucion) {
		super(numero, enunciado, pista, solucion);
		this.sintaxis = sintaxis;
	}
	

	@Override
	public void setOpciones(ArrayList<String> opciones) {
		
		
	}

	@Override
	public boolean corrige(String respuesta, Tema tema) {
		URL classUrl = null;
    	try {
    		//TODO Hay que borrarlo porque lo tiene el controlador
			classUrl = new URL("file:C:/Users/Carlos/Desktop/TFG/prueba/resources/");
		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	URL[] classUrls = { classUrl };
    	URLClassLoader ucl = new URLClassLoader(classUrls);
    	
    	
		try {
			//TODO main.prue.... hay que cambiarlo a lo que tiene el yml
			Class<? extends Lexer> c = (Class<? extends Lexer>) Class.forName("main.prueba.TrianguloLexer",true,ucl);
			Constructor<? extends Lexer> constructor = c.getConstructor(CharStream.class);
	    	//String respuesta = "h=2\nb=4\na=h*b/2";
	    	Lexer l = constructor.newInstance(new ANTLRInputStream(respuesta));
	    	
	    	Class<? extends Parser> cParser = (Class<? extends Parser>) Class.forName("main.prueba.TrianguloParser",true,ucl);
	    	Constructor<? extends Parser> constructorParser = cParser.getConstructor(TokenStream.class);
	    	Parser p = constructorParser.newInstance(new CommonTokenStream(l));
	    	
	    	//Aqui empieza lo bueno
	    	Class<? extends ParserRuleContext> inner = (Class<? extends ParserRuleContext>) Class.forName("main.prueba.TrianguloParser$TrianguloContext",true,ucl);
	    	Constructor<? extends ParserRuleContext> constInn = inner.getConstructor(ParserRuleContext.class, int.class);
	    	ParserRuleContext prc = constInn.newInstance(new ParserRuleContext(), 5);
	    	
	    	prc =  (ParserRuleContext) cParser.getMethod("triangulo").invoke(p);
	    	System.out.println(prc.exception);
	    	if (prc.exception==null)
	    	{
	    		System.out.println("Terminado con exito");
	    		return true;
	    	}
	    	
	    	else
	    	{
	    		System.out.println("Está mal");
	    		return false;
	    	}
	    		
	    	
	    	
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

	public void setSolucion(String solucion) {
		this.solucion = solucion;
	}


	@Override
	public void setMulti(Boolean is) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setSolucion(ArrayList<Integer> correctasAux) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void setTexto(String explicacion) {
		// TODO Auto-generated method stub
		
	}
		
	
}
