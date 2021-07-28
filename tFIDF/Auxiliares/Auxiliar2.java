//Package
//package ProyectoFinal;

//Imports
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Clase con metodos auxiliares que nos ayudaran en el proyecto y que
 * no tienen una clase definida.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Auxiliar{

    /**
     * Metodo auxiliar para la Progress Bar.
     * @param pathName una ruta dada como input.
     * @return Objeto de tipo File
     */
    public File animationProgressBar(String pathName){
	File dir2 = new File("example");
	File dir = null;
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		/** Declaracion del directorio a buscar pasado como input*/
		if(x == 98)
		    dir = new File(pathName);
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
	return dir;
    }

    /**
     * Metodo Auxiliar para la Progress Bar en el calculo del TF-IDF de todos los
     * documentos de un arreglo de Files.
     * @param arrDocs - Arreglo con todos los documentos txt ya convertidos en File.
     * @return LinkedList<String> - Un arreglo con los documentos vistos como una 
     * LinkedList.
     */
    public LinkedList<String>[] animationProgressBar(File[] arrDocs){
	//Arreglo en donde se guardaran los documentos.
	LinkedList<String>[] docsList = new LinkedList[arrDocs.length];
	//Lista Auxiliar de recuperacion de una LinkedList.
	LinkedList<String> auxList = null;
	//Lector
	Reader reader = new Reader();
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		//Condicion para empezar a leer los documentos.
		if(x == 70){
		    //Lectura de todos los documentos.
		    for(int i = 0; i < arrDocs.length; i++){
			auxList = reader.readDocument(arrDocs[i]);
			docsList[i] = auxList;
		    }
		}
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
	return docsList;
    }

    /**
     * Metodo Auxiliar para la Progress Bar en el calculo del IDF dado un arreglo de listas.
     * @param arrList - Arreglo con todos los documentos txt ya convertidos en listas.
     * @return RedBlackTree<Double, String> - Un Arbol Rojinegro con los valores IDF de cada
     * palabra, sin admitir repetidos.
     */
    public RedBlackTree<Double, String> animationProgressBarIDF(LinkedList<String>[] arrList){
	//Arbol que devolveremos
	RedBlackTree<Double, String> arbol = new RedBlackTree<Double, String>();
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo del IDF.
		if(x == 70){
		    //Obtener el arbol de IDF's.
		    arbol = c.calcularIDF(arrList);
		}
		Thread.sleep(20);
	    }
	}catch(Exception e){
	}
	return arbol;
    }

    /**
     * Metodo Auxiliar para la Progress Bar en el calculo del TF dado un arreglo de listas.
     * @param arrList - Arreglo con todos los documentos txt ya convertidos en listas.
     * @return LinkedList<Pair<String,Double>>[] - Un areglo de listas con cada palabra de 
     * cada documento con su respectivo valor TF.
     */
    public LinkedList<Pair<String,Double>>[] animationProgressBar(LinkedList<String>[] arrList){
	//Lista que devolveremos
	LinkedList<Pair<String,Double>>[] listPair = new LinkedList[arrList.length];
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo TF
		if(x == 70){
		    //Obtener el arreglo de TF's.
		    listPair = c.calcularTF(arrList);
		}
		Thread.sleep(20);
	    }
	}catch(Exception e){
	}
	return listPair;
    }

    /**
     * Metodo Auxiliar para la Progress Bar en el calculo del TF-IDF dado un arreglo con los 
     * valores TF y un arbol rojinegro con los valores IDF.
     * @param arrList - Arreglo con todos los documentos txt ya convertidos en listas.
     * @return LinkedList<Pair<String,Double>>[] - Un areglo de listas con cada palabra de 
     * cada documento con su respectivo valor TF-IDF.
     */
    public LinkedList<Pair<String,Double>>[] animationProgressBar(LinkedList<Pair<String,Double>>[] arrTF, RedBlackTree<Double, String> treeIDF){
	//Lista que devolveremos.
	LinkedList<Pair<String,Double>>[] listPair = new LinkedList[arrTF.length];
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo TF-IDF
		if(x == 70){
		    //Obtener el arreglo de TF-IDF's
		    listPair = c.calcularTFIDF(arrTF, treeIDF);
		}
		Thread.sleep(20);
	    }
	}catch(Exception e){
	}
	return listPair;
    }


    /**
     * Funcion que nos permite cambiar de posiciones en un arreglo al hacer algun
     * tipo de ordenamiento.
     * @param arr el arreglo del cual cambiar la posición de los elementos.
     * @param i el índice del primer elemento a cambiar.
     * @param j el índice del segundo elemento a cambiar.
     */
    private void swap(Pair<Integer, Double>[] sim, int i, int j){
	Pair<Integer, Double> tmp = sim[i];
	sim[i] = sim[j];
	sim[j] = tmp;
    }
    
    /**
     * Sorter para un arreglo de tipo Pair<String, Double>
     * @param sim - Arreglo de tipo Pair<Integer, Double> con
     * el numero del documento y la similitud con la busqueda realizada.
     */
    public void insertionParejas(Pair<Integer, Double>[] sim){
	for(int i = 0; i < sim.length - 1; i++)
	    for(int j = i+1; j > 0 && sim[j-1].getKey() < sim[j].getKey();j--)
		swap(sim,j,j-1);	
    }
}
