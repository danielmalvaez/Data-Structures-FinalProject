//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.io.*;
import java.util.*;

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
	System.out.println("      MOTOR DE BUSQUEDA");
	System.out.println("        (mini google)");
	System.out.println("-----------------------------\n");

	boolean validPath = false;
	//Array donde guardaremos los files obtenidos de la ruta.
	File[] arrDocs = null;	
	//Mientras la ruta no sea valida se volvera a pedir una.
	while(!validPath){
	    try{
		System.out.println("Ingrese ruta ... (e.g. /Users/dan/docs/): ");
		String pathName = sc.nextLine().trim();

		//Cargar los archivos guardados en el pathName.
		File dir = new File(pathName);
		
		//Guardar los documentos en un arreglo de tipo File.
		arrDocs = dir.listFiles(filter);
		if(arrDocs.length == 0){
		    System.out.println("\nNo hay archivos .txt en esta ruta.\n");
		    continue;
		}else if(arrDocs.length > 0){
		    //Progreso de carga de archivos.
		    aux.progressBar(arrDocs, pathName);
		    System.out.println();
		}
		validPath = true;
	    }catch(Exception e){
		System.out.println("\n\nRuta Invalida, intenta de nuevo\n");
	    }
	} //While validPath

	System.out.println("\nProcesando Archivos...");
	//Convertirlos a ArrayLists.
	ArrayList<String>[] stringList = aux.progressBar(arrDocs);
	System.out.println();
	//Calcular su IDF
	Hashtable<String, Pair<String, Double>> ht = aux.progressBarIDF(stringList);
	System.out.println();
	//Calcular su TF
	ArrayList<Pair<String,Double>>[] arrTF = aux.progressBar(stringList);
	System.out.println();
	//Calcular su TFIDF
	ArrayList<Pair<String,Double>>[] arrTFIDF = aux.progressBar(arrTF, ht);
	System.out.println("\nArchivos cargados con exito!");

	/** Menu para interactuar con el motor de busqueda, se encuentran las
	 siguientes opciones: buscar, historial, salir del programa.*/
	boolean terminarPrograma = false;

	// Implementacion del cache como una LinkedList.
	ArrayList<Pair<ArrayList<String>, ArrayList<String>>> cache = new ArrayList<>();
	
	while(!terminarPrograma){
	    System.out.println("\n\n------------");
	    System.out.println("    MENU");
	    System.out.println("------------");
	    System.out.println("1) Buscar");
	    System.out.println("2) Historial de Busqueda");
	    System.out.println("3) Salir");

	    /** Verificar si el número pasado como input es valido i.e. si esta
	     dentro del rango permitido 1<=x<=3*/
	    boolean inputValida = false;
	    int hacer = -1;
	    while(!inputValida){
		try{
		    System.out.println("\nInput:: ");
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
		ArrayList<String> busqueda = null;
		//Hashtable para el IDF de la busqueda.
		Hashtable<String, Pair<String, Double>> htBusqueda = null;
		//Arreglo para el TF de la busqueda.
		ArrayList<Pair<String,Double>>[] arrTFBusqueda = null;
		//Arreglo para el TF-IDF de la busqueda.
		ArrayList<Pair<String, Double>>[] arrTFIDFBusqueda = null;
		//Similitud de la busqueda.
		Pair<Integer, Double>[] similitud = null;
		//Documentos mas relevantes para la busqueda.
		ArrayList<String> docsRelevantes = null;

		//Algoritmo para la busqueda.
		while(!validLength){
		    System.out.println("\nBusqueda: ");
		    search = sc.nextLine();
		    //Verificar si la longitud es menor a 200.
		    if(search.length() < 200){
			reader.writeString(search);
			busqueda = reader.readString(search);
			//Verificar si la longitud es 0
			if(busqueda.size() == 0){
			    System.out.println("Busqueda invalida.");
			    continue;
			}
			//Buscar en el cache
			if(!cache.isEmpty()){
			    //Pareja auxiliar.
			    Pair<ArrayList<String>, ArrayList<String>> pareja = null;
			    //Iterador para recorrer el cache.
			    Iterator iteradorCache = cache.iterator();
			    //Verificar si son iguales.
			    boolean iguales = true;
			    //Mientras el cache tenga siguiente.
			    while(iteradorCache.hasNext()){
				pareja = (Pair<ArrayList<String>, ArrayList<String>>)iteradorCache.next();
				ArrayList<String> guardada = pareja.getValue();
				/*Si el tamaño de las listas no coincide sabemos que no es
				 * la misma busqueda. */
				if(guardada.size() != busqueda.size()){
				    iguales = false;
				    continue;
				}
				
				Iterator iteradorGuardada = guardada.iterator();
				Iterator iteradorBusqueda = busqueda.iterator();

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
				    ArrayList<String> simi = pareja.getKey();
				    //Imprimir los documentos relevantes.
				    if(simi.isEmpty()){
					System.out.println("Ningun documento es relevante para su busqueda.");
				    }else{
					Iterator iteradorSimi = simi.iterator();
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
			htBusqueda = calculator.calcularIDF(stringList, busqueda);
			arrTFBusqueda = calculator.calcularTF(stringList, busqueda);
			arrTFIDFBusqueda = calculator.calcularTFIDF(arrTFBusqueda, htBusqueda);

			//Calcular la similitud de los documentos y la busqueda.
			similitud = calculator.similitud(arrTFIDF, arrTFIDFBusqueda);
			//Ordenamos las parejas con InsertionSort.
			aux.insertionParejas(similitud);
			
			//Guardar los documentos mas relevantes para la busqueda.
			docsRelevantes = new ArrayList<String>();
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
			    cache.add(cache.size(), new Pair<ArrayList<String>,
				      ArrayList<String>>(busqueda, docsRelevantes));
			}else if(cache.size() < 10){
			    cache.add(cache.size(), new Pair<ArrayList<String>,
				      ArrayList<String>>(busqueda, docsRelevantes));
			}

			//Imprimir los documentos relevantes.
			if(docsRelevantes.isEmpty()){
			    System.out.println("Ningun documento es relevante para su busqueda.");
			}else{
			    Iterator iterador = docsRelevantes.iterator();
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
		String historial = reader.readDocument("HistorialDeBusqueda.txt");
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
