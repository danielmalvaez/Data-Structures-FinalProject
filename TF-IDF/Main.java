//Package
//package ProyectoFinal;

//Imports
import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;
import java.io.IOException;
import java.lang.String;

/**
 * Clase donde encontraremos el Main del proyecto.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Main{
    public static void main(String[] args){
	Scanner sc = new Scanner(System.in);
	Auxiliar aux = new Auxiliar();
	FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
		    return !file.isDirectory() && file.getName().endsWith(".txt");
		}
	    };
	
	System.out.println("-----------------------------");
	System.out.println("         MALVIOGLE");
	System.out.println("-----------------------------\n");

	boolean validPath = false;
	while(!validPath){
	    try{
		System.out.println("Buscar en ... (e.g. /Users/dan/docs/):");
		String pathName = sc.nextLine().trim();

		File dir = aux.animationProgressBar(pathName);

		File[] listDocs = dir.listFiles(filter);
		if(list.length == 0){
		    System.out.println("\nNo hay archivos .txt en esta ruta.\n");
		    continue;
		}
		validPath = true;
	    }catch(Exception e){
		System.out.println("\n\nRuta Invalida, intenta de nuevo\n");
	    }
	} //While validPath
	
	System.out.println("\nArchivos cargados con exito!");

	boolean terminarPrograma = false;
	while(!terminarPrograma){
	    System.out.println("\n\n------------");
	    System.out.println("    MENU");
	    System.out.println("------------");
	    System.out.println("1) Buscar");
	    System.out.println("2) Historial de Busqueda");
	    System.out.println("3) Salir");

	    boolean inputValida = false;
	    int hacer = -1;
		while(!inputValida){
		    try{
			System.out.print("Input: ");
			hacer = sc.nextInt();
			sc.nextLine();
			inputValida = true;
		    }catch(Exception e){
			System.out.println("Entrada incorrecta, intenta de nuevo.");
		    }
		}
	
	    switch(hacer){
	    case 1:
		boolean validLength = false;
		while(!validLength){
		    System.out.print("\nBusqueda: ");
		    String search = sc.nextLine();
		    if(search.length() < 200)
			validLength = true;
		    else
			System.out.println("Busqueda invalida, rebasa el rango permitido.");
		}
		break;
	    case 2:
		break;
	    case 3:
		System.out.println("Hasta luego...");
		terminarPrograma = true;
		break;
	    }//switch
	}//while terminarPrograma
    }// Main
}// Read Documents Class
