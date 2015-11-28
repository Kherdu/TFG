package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import org.pegdown.PegDownProcessor;

import com.github.rjeschke.txtmark.Processor;

public class Main2 {

	public static void main(String[] args) {
		try {
			InputStream inp = new FileInputStream("resources/tabla.txt");
			Scanner sc = new Scanner(inp);
			String tabla = "";
			String aux = "";
			do
			{
				aux = sc.nextLine();
				tabla += aux + "\n";
			}while(sc.hasNext());
			PegDownProcessor processor= new PegDownProcessor();
			System.out.println(tabla);
			String tablaway = processor.markdownToHtml(tabla);
			System.out.println(tablaway);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
