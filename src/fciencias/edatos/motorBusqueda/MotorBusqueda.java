//Package
package fciencias.edatos.motorBusqueda;

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
public class MotorBusqueda{
    public static void main(String[] args){

	/** Creacion de objetos que necesitaremos para la obtencion de
	    datos con el usuario y manipulacion de Strings.*/
	Scanner sc = new Scanner(System.in);
	Auxiliar aux = new Auxiliar();
	Reader reader = new Reader();
	TFIDFcalculator calculator = new TFIDFcalculator();
	Text io = new Text();

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

	// Implementacion del cache como una LinkedList.
	LinkedList<Pair<LinkedList<String>, LinkedList<String>>> cache = new LinkedList<>();
	
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
		    System.out.print("\nInput: ");
		    hacer = sc.nextInt();
		    sc.nextLine();
		    if (hacer >= 1 && hacer <= 3){
			inputValida = true;
		    }else{
			System.out.println("Número fuera de rango, intenta de nuevo.\n");
			continue;
		    }
		}catch(Exception e){
		    sc.nextLine();
		    System.out.println("Entrada incorrecta, intenta de nuevo.");
		}
	    }

	    /** Switch para verificar si se desea hacer una busqueda, ver el historial
	     o bien salir del programa.*/
	    switch(hacer){
	    case 1:
		//Variable para salir del while
		boolean validLength = false;
		//variable para la busqueda en String.
		String search = null;
		//Busqueda vista como una lista.
		LinkedList<String> busqueda = null;
		//Arbol para el IDF de la busqueda.
		RedBlackTree<Double, String> treeIDFBusqueda = null;
		//Arreglo para el TF de la busqueda.
		LinkedList<Pair<String,Double>>[] arrTFBusqueda = null;
		//Arreglo para el TF-IDF de la busqueda.
		LinkedList<Pair<String, Double>>[] arrTFIDFBusqueda = null;
		//Similitud de la busqueda.
		Pair<Integer, Double>[] similitud = null;
		//Documentos mas relevantes para la busqueda.
		LinkedList<String> docsRelevantes = null;

		//Algoritmo para la busqueda.
		while(!validLength){
		    System.out.print("\nBusqueda: ");
		    search = sc.nextLine();
		    //Verificar si la longitud es menor a 200.
		    if(search.length() < 200){
			io.writeString(search);
			busqueda = reader.readString(search);
			//Verificar si la longitud es 0
			if(busqueda.size() == 0){
			    System.out.println("Busqueda invalida.");
			    continue;
			}
			//Buscar en el cache
			if(!cache.isEmpty()){
			    //Pareja auxiliar.
			    Pair<LinkedList<String>, LinkedList<String>> pareja = null;
			    //Iterador para recorrer el cache.
			    Iterator iteradorCache = cache.iterador();
			    //Verificar si son iguales.
			    boolean iguales = true;
			    //Mientras el cache tenga siguiente.
			    while(iteradorCache.hasNext()){
				pareja = (Pair<LinkedList<String>, LinkedList<String>>)iteradorCache.next();
				LinkedList<String> guardada = pareja.getValue();
				/*Si el tamaño de las listas no coincide sabemos que no es
				 * la misma busqueda. */
				if(guardada.size() != busqueda.size()){
				    iguales = false;
				    continue;
				}
				
				Iterator iteradorGuardada = guardada.iterador();
				Iterator iteradorBusqueda = busqueda.iterador();

				while(iteradorGuardada.hasNext()){
				    iguales = true;
				    String guar = (String)iteradorGuardada.next();
				    String bus = (String)iteradorBusqueda.next();
				    if(!guar.equals(bus)){
					iguales = false;
					break;
				    }
				}
				//Si son iguales imprimir
				if(iguales == true){
				    LinkedList<String> simi = pareja.getKey();
				    //Imprimir los documentos relevantes.
				    if(simi.isEmpty()){
					System.out.println("Ningun documento es relevante para su busqueda.");
				    }else{
					Iterator iteradorSimi = simi.iterador();
					while(iteradorSimi.hasNext()){
					    String documentName = (String)iteradorSimi.next();
					    System.out.println("Document name: " + documentName);
					}
				    }
				    validLength = true;
				    break;
				}
			    }//While iterador cache.
			    //If para cerrar el while y ya no hacer calculos.
			    if(iguales){
				System.out.println("Utiliza cache...");
				break;
			    }
			}//Termina de buscar en el cache.
			
			//Si no esta en cache, hacer los calculos de la busqueda.
			System.out.println("\nBuscando...");
			treeIDFBusqueda = calculator.calcularIDF(stringList, busqueda);
			arrTFBusqueda = calculator.calcularTF(stringList, busqueda);
			arrTFIDFBusqueda = calculator.calcularTFIDF(arrTFBusqueda, treeIDFBusqueda);

			//Calcular la similitud de los documentos y la busqueda.
			similitud = calculator.similitud(arrTFIDF, arrTFIDFBusqueda);
			aux.insertionParejas(similitud);
		
			//Documentos mas relevantes para la busqueda..
			docsRelevantes = new LinkedList<String>();
			int indice = -1;
			for(int i = 0; i < 10; i++){
			    if(i < similitud.length){
				if(similitud[i].getKey() == 0.0)
				    continue;
				indice = similitud[i].getValue();
				docsRelevantes.add(docsRelevantes.size(), arrDocs[indice].getName());
			    }
			}

			// Añadir la busqueda y sus resultados al cache.
			if(cache.size() == 10){
			    cache.remove(0);
			    cache.add(cache.size(), new Pair<LinkedList<String>, LinkedList<String>>(busqueda, docsRelevantes));
			}else if(cache.size() < 10){
			    cache.add(cache.size(), new Pair<LinkedList<String>, LinkedList<String>>(busqueda, docsRelevantes));
			}

			//Imprimir los documentos relevantes.
			if(docsRelevantes.isEmpty()){
			    System.out.println("Ningun documento es relevante para su busqueda.");
			}else{
			    Iterator iterador = docsRelevantes.iterador();
			    while(iterador.hasNext()){
				String docName = (String)iterador.next();
				System.out.println("Document name: " + docName);
			    }
			}
			//Salir del while.
			validLength = true;
		    }else{
			System.out.println("Busqueda invalida, rebasa el rango permitido.");
		    }
		}
		break;
	    case 2:
		System.out.println("\n---------------------");
		System.out.println("HISTORIAL DE BUSQUEDA");
		System.out.println("---------------------");
		String historial = io.readString("HistorialDeBusqueda.txt");
		System.out.println(historial);
		break;
	    case 3:
		System.out.println("Hasta luego...");
		terminarPrograma = true;
		break;
	    }//switch
	}//while terminarPrograma
    }// Main
}// Read Documents Class
