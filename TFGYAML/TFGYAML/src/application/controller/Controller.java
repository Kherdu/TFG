package application.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import application.model.Codigo;
import application.model.Leccion;
import application.model.Opciones;
import application.model.Pregunta;
import application.model.Sintaxis;
import application.model.Tema;

public class Controller {
	
	private Tema tema;

	public Controller(){
		this.tema=null;
	}
	
	public void cargaModelo(String cargaTema){
		Yaml yaml = new Yaml();
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();
		InputStream input = null;
		try {
			input = new FileInputStream("resources/"+cargaTema+".yml");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//mapa lectura del yaml
		@SuppressWarnings("unchecked")
		Map<String, Object> mapaObjeto = (Map<String, Object>) yaml.load(input);
		//auxiliares
		ArrayList<Map> l = new ArrayList<Map>();
		ArrayList<Map> p = new ArrayList<Map>();
		l = (ArrayList<Map>) mapaObjeto.get("Lecciones");
		Integer nTema= (Integer) mapaObjeto.get("Tema");
		String tTema= (String) mapaObjeto.get("Titulo");
		String iTema= (String) mapaObjeto.get("Introduccion");
		
		//objetos a rellenar para el tema
		List<Pregunta> preguntas= new ArrayList<Pregunta>();
		List<Leccion> lecciones= new ArrayList<Leccion>();
		
		
		for (Map leccion : l)
		{
			preguntas=new ArrayList<Pregunta>();
			p = (ArrayList<Map>) leccion.get("Preguntas");
			Pregunta pregunta= new Codigo(1,null,null,null);
			for (Map pre : p)
			{
				int num= (Integer) pre.get("Pregunta");
				
				String enunciado = (String) pre.get("Enunciado");
				String pista = (String) pre.get("Pista");
				
				if (pre.get("Tipo").equals("Codigo"))
				{	
					String respuesta = (String) pre.get("Respuesta");
					pregunta = new Codigo(num, enunciado, pista, respuesta); 
					
				}
				else if (pre.get("Tipo").equals("Sintaxis"))
				{
					String sintaxis= (String) pre.get("Gramatica");
					String resultado= (String) pre.get("Resultado");
					pregunta= new Sintaxis(num, enunciado, pista, sintaxis);
				}
				else if (pre.get("Tipo").equals("Opciones"))
				{
					
					pregunta= new Opciones(num,enunciado,pista);
					String opcorrecta= (String) pre.get("Opcion_correcta");
					String[] correctas = opcorrecta.split(",");
					pregunta.setCorrectas(correctas);
					ArrayList<String> opc= new ArrayList<String>();
					opc= (ArrayList<String>) pre.get("Opciones");
					pregunta.setOpciones(opc);
				}
				preguntas.add(pregunta);
				
			}
		int nLeccion=(Integer) leccion.get("Leccion");
		String tLeccion= (String) leccion.get("Titulo_Leccion");
		String eLeccion= (String) leccion.get("Explicacion");
		Leccion lec= new Leccion(nLeccion,tLeccion,eLeccion);
		lec.setPreguntas(preguntas);
		lecciones.add(lec);
		
		}
		
		//rellenado de objetos final
		Tema t= new Tema(nTema, tTema, iTema);
		t.setLecciones(lecciones);
		this.tema=t;
		//objeto cargado
		
	}
	public Tema getTema() {
		return tema;
	}


	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}
