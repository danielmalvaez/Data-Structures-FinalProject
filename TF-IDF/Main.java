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
import java.util.Iterator;
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
	Reader reader = new Reader();
	TFIDFcalculator calculator = new TFIDFcalculator();

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
	File[] arrDocs = null;
	while(!validPath){
	    try{
		System.out.println("Buscar en ... (e.g. /Users/dan/docs/):");
		String pathName = sc.nextLine().trim();

		//Animacion para simular la barra de porcentage de carga.
		File dir = aux.animationProgressBar(pathName);

		//Guardar los documentos en un arreglo de tipo File.
		arrDocs = dir.listFiles(filter);
		if(arrDocs.length == 0){
		    System.out.println("\nNo hay archivos .txt en esta ruta.\n");
		    continue;
		}
		validPath = true;
	    }catch(Exception e){
		System.out.println("\n\nRuta Invalida, intenta de nuevo\n");
	    }
	} //While validPath

	System.out.println("\nProcesando Archivos...");
	LinkedList<String>[] stringList = aux.animationProgressBar(arrDocs);
	RedBlackTree<Double, String> treeIDF = aux.animationProgressBarIDF(stringList);
	LinkedList<Pair<String,Double>>[] arrTF = aux.animationProgressBar(stringList);
	LinkedList<Pair<String,Double>>[] arrTFIDF = aux.animationProgressBar(arrTF, treeIDF);
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
		//Variables y objetos de ayuda.
		boolean validLength = false;
		String search = null;
		LinkedList<String> busqueda = null;
		RedBlackTree<Double, String> treeIDFBusqueda = null;
		LinkedList<Pair<String,Double>>[] arrTFBusqueda = null;
		LinkedList<Pair<String, Double>>[] arrTFIDFBusqueda = null;
		while(!validLength){
		    System.out.print("\nBusqueda: ");
		    search = sc.nextLine();
		    if(search.length() < 200){
			busqueda = reader.readString(search);
			if(busqueda.size() == 0){
			    System.out.println("Busqueda invalida.");
			    continue;
			}
			System.out.println("\nBuscando...");
			treeIDFBusqueda = calculator.calcularIDF(stringList, busqueda);
			arrTFBusqueda = calculator.calcularTF(stringList, busqueda);
			arrTFIDFBusqueda = calculator.calcularTFIDF(arrTFBusqueda, treeIDFBusqueda);
			validLength = true;
		    }else{
			System.out.println("Busqueda invalida, rebasa el rango permitido.");
		    }
		}
		//Calcular la similitud de los documentos y la busqueda.
		Pair<Integer, Double>[] similitud = calculator.similitud(arrTFIDF, arrTFIDFBusqueda);
		aux.insertionParejas(similitud);
		//Documentos mas reelevantes para la busqueda..
		LinkedList<String> docsRelevantes = new LinkedList<String>();
		int indice = -1;
		for(int i = 0; i < 10; i++){
		    if(i < similitud.length){
			if(similitud[i].getKey() == 0.0)
			    continue;
			indice = similitud[i].getValue();
			docsRelevantes.add(docsRelevantes.size(), arrDocs[indice].getName());
		    }
		}

		if(docsRelevantes.isEmpty()){
		    System.out.println("Ningun documento es relevante para su busqueda.");
		}else{
		    Iterator iterador = docsRelevantes.iterador();
		    while(iterador.hasNext()){
			String docName = (String)iterador.next();
			System.out.println("Document name: " + docName);
		    }
		}
		break;
	    case 2:
		LinkedList<String> fileList = new LinkedList<>();
		break;
	    case 3:
		System.out.println("Hasta luego...");
		terminarPrograma = true;
		break;
	    }//switch
	}//while terminarPrograma
    }// Main
}// Read Documents Class
