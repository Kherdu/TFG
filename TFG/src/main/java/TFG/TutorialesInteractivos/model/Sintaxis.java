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

import TFG.TutorialesInteractivos.controller.Controller;

/**
 * Pregunta de tipo Sintaxis
 * 
 * @authors Carlos, Rafa
 *
 */
public class Sintaxis extends Pregunta<String> {
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
			// falta el nombre de la carpeta en concreto, que irá tambien en los
			// de abajo y en el getmethod lowercase
			String s = "file:" + Controller.externalResourcesPath + "/" + Controller.selectedLanguage + "/gramaticas/"
					+ sintaxis + "/";
			classUrl = new URL(s);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		URL[] classUrls = { classUrl };
		URLClassLoader ucl = new URLClassLoader(classUrls);

		try {

			Class<? extends Lexer> c = (Class<? extends Lexer>) Class.forName(sintaxis + "Lexer", true, ucl);
			Constructor<? extends Lexer> constructor = c.getConstructor(CharStream.class);
			
			Lexer l = constructor.newInstance(new ANTLRInputStream(respuesta));

			Class<? extends Parser> cParser = (Class<? extends Parser>) Class.forName(sintaxis + "Parser", true, ucl);
			Constructor<? extends Parser> constructorParser = cParser.getConstructor(TokenStream.class);
			Parser p = constructorParser.newInstance(new CommonTokenStream(l));

			// Aqui empieza lo bueno
			Class<? extends ParserRuleContext> inner = (Class<? extends ParserRuleContext>) Class
					.forName(sintaxis + "Parser$" + sintaxis + "Context", true, ucl);
			Constructor<? extends ParserRuleContext> constInn = inner.getConstructor(ParserRuleContext.class,
					int.class);
			ParserRuleContext prc = constInn.newInstance(new ParserRuleContext(), 5);

			prc = (ParserRuleContext) cParser.getMethod(sintaxis.toLowerCase()).invoke(p);
			System.out.println(prc.exception);
			if (prc.exception == null) {
				//System.out.println("Terminado con exito");
				return true;
			}

			else {
				//System.out.println("Está mal");
				return false;
			}

		} catch (Exception e1) {
			
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
