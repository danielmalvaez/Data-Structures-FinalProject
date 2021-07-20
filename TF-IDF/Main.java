//Package
//package ProyectoFinal;

//Imports
import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;
import java.io.IOException;
import java.lang.String;
import java.text.Normalizer;
import java.util.Scanner;

/**
 * Clase donde encontraremos el metodo Main del proyecto.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Main{
    public static void main(String[] args){

	/** Creacion de objetos que necesitaremos para la obtencion de
	    datos con el usuario y manipulacion de Strings.*/
	Scanner sc = new Scanner(System.in);
	Auxiliar aux = new Auxiliar();

	/**Clase abstracta que nos permite definir el metodo de la
	 la interface FileFilter*/
	FileFilter filter = new FileFilter() {
		public boolean accept(File file) {
		    return !file.isDirectory() && file.getName().endsWith(".txt");
		}
	    };

	/** Menu de busqueda, aqui es donde pide como entrada una ruta
	    valida.*/
	System.out.println("-----------------------------");
	System.out.println("         MALVIOGLE");
	System.out.println("-----------------------------\n");

	boolean validPath = false;
	while(!validPath){
	    try{
		System.out.println("Buscar en ... (e.g. /Users/dan/docs/):");
		String pathName = sc.nextLine().trim();

		//Animacion para simular la barra de porcentage de carga.
		File dir = aux.animationProgressBar(pathName);

		File[] listDocs = dir.listFiles(filter);
		if(listDocs.length == 0){
		    System.out.println("\nNo hay archivos .txt en esta ruta.\n");
		    continue;
		}
		validPath = true;
	    }catch(Exception e){
		System.out.println("\n\nRuta Invalida, intenta de nuevo\n");
	    }
	} //While validPath
	
	System.out.println("\nArchivos cargados con exito!");

	/** Menu para interactuar con el motor de busqueda, se encuentran las
	 siguientes opciones: buscar, historial, salir del programa.*/
	boolean terminarPrograma = false;
	while(!terminarPrograma){
	    System.out.println("\n\n------------");
	    System.out.println("    MENU");
	    System.out.println("------------");
	    System.out.println("1) Buscar");
	    System.out.println("2) Historial de Busqueda");
	    System.out.println("3) Salir");

	    /** Verificar si el numero pasado como input es valido i.e. si esta
	     dentro del rango permitido 1<=x<=3*/
	    boolean inputValida = false;
	    int hacer = -1;
	    while(!inputValida){
		try{
		    System.out.print("Input: ");
		    hacer = sc.nextInt();
		    sc.nextLine();
		    if (hacer >= 1 && hacer <= 3){
			inputValida = true;
		    }else{
			System.out.println("Número fuera de rango, intenta de nuevo.\n");
			continue;
		    }
		}catch(Exception e){
		    System.out.println("Entrada incorrecta, intenta de nuevo.");
		}
	    }

	    /** Switch para verificar si se desea hacer una busqueda, ver el historial
	     o bien salir del programa.*/
	    switch(hacer){
	    case 1:
		boolean validLength = false;
		String search = null;
		while(!validLength){
		    System.out.print("\nBusqueda: ");
		    search = sc.nextLine();
		    if(search.length() < 200){
			validLength = true;
		    }else{
			System.out.println("Busqueda invalida, rebasa el rango permitido.");
		    }	    
		}
		
		/*Busqueda de manera normal y con minusculas.
		search = Normalizer.normalize(search.toLowerCase(), Normalize.Form.NFD);
		//Busqueda sin acentos.
		String searchNA = search.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
		*/
		
		break;


	    case 2:
		LinkedList<String> fileList = new LinkedList<>();
		/**
		File myFile = new File("ejemplo.txt");
		String ejem = "Este és un ejémplo de un String % con acentos y & con símbolos";
		Scanner io = new Scanner(ejem).useDelimiter("\");
		ejem = ejem.replaceAll("\\W+"," ");
		System.out.println(ejem);
				
		while(io.hasNext()){
		    fileList.add(fileList.size(),io.next());
		}

		fileList.show();
		*/
		break;


	    case 3:
		System.out.println("Hasta luego...");
		terminarPrograma = true;
		break;
	    }//switch
	}//while terminarPrograma
    }// Main
}// Read Documents Class
