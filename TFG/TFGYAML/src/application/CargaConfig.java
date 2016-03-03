package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class CargaConfig 
{
	public static String loadConfig()
	{
		try {
			InputStream is = new FileInputStream("resources/config/carga.yml");
			Scanner sc = new Scanner(is);
			String ruta = sc.nextLine();
			sc.close();
			return ruta;
		} catch (FileNotFoundException e) {
			//TODO diferenciar SO
			return "c:\\Python34\\python.exe";
		}
	}
	
	/**
	 * Guarda el nuevo path en el archivo de configuracion
	 * @param np
	 */
	
	
	public static void saveConfig(String np)
	{
		try {
			FileWriter os = new FileWriter("resources/config/carga.txt");
			os.write(np);
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
