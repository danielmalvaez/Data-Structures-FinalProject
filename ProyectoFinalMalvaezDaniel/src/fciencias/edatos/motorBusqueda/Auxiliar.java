//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.io.*;
import java.util.*;

/**
 * Clase con metodos auxiliares que nos ayudaran en el proyecto y que
 * no tienen una clase definida.
 * @author Axel Daniel Malvaez Flores
 * @version 2.0
 */
public class Auxiliar{

    /**
     * Metodo auxiliar para la Progress Bar en la carga de archivos..
     * @param pathName una ruta dada como input.
     */
    public void progressBar(File[] arr, String pathName){
	try{
	    for (int x =0 ; x < arr.length ; x++){
		String data = "\r" + "Archivos cargados..." + " " + (x+1);
		System.out.write(data.getBytes());
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
    }

    /**
     * Metodo Auxiliar para la Progress Bar en la conversión de los documentos tipo
     * File a ArrayLists.
     * @param arrDocs - Arreglo con todos los documentos txt ya convertidos en File.
     * @return ArrayList<String> - Un arreglo con los documentos vistos como una 
     * ArrayList.
     */
    public ArrayList<String>[] progressBar(File[] arrDocs){
	//Arreglo en donde se guardaran los documentos.
	ArrayList<String>[] docsList = new ArrayList[arrDocs.length];
	//Lista Auxiliar de recuperacion de un ArrayList.
	ArrayList<String> auxList = null;
	//Lector
	Reader reader = new Reader();
	try{
	    for (int x = 0; x < arrDocs.length; x++){
		String data = "\r" + "Archivos cargados..." + " " + (x+1);
		System.out.write(data.getBytes());
		auxList = reader.readDocument(arrDocs[x]);
		docsList[x] = auxList;
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
	return docsList;
    }
    
    /**
     * Método Auxiliar para la Progress Bar en el cálculo del IDF dado un arreglo de listas.
     * @param arrList - Arreglo con todos los documentos txt ya convertidos en listas.
     * @return Hashtable<String, Pair<String, Double>> - Tabla hash con un String como key y con un
     * Pair<String, Double> como value.
     */
    public Hashtable<String, Pair<String, Double>> progressBarIDF(ArrayList<String>[] arrList){
	//Arbol que devolveremos
	Hashtable<String, Pair<String, Double>> ht = null;
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    for (int x =0 ; x < arrList.length ; x++){
		String data = "\r" + "Archivos cargados..." + " " + (x+1);
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo del IDF.
		if(x == arrList.length-1){
		    //Obtener el hashtable de IDF's.
		    ht = c.calcularIDF(arrList);
		}
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
	return ht;
    }

    /**
     * Método Auxiliar para la Progress Bar en el cálculo del TF dado un arreglo de listas.
     * @param arrList - Arreglo con todos los documentos txt ya convertidos en listas.
     * @return ArrayList<Pair<String,Double>>[] - Un areglo de listas con cada palabra de 
     * cada documento con su respectivo valor TF.
     */
    public ArrayList<Pair<String,Double>>[] progressBar(ArrayList<String>[] arrList){
	//Lista que devolveremos
	ArrayList<Pair<String,Double>>[] listPair = new ArrayList[arrList.length];
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    for (int x =0 ; x < arrList.length ; x++){
		String data = "\r" + "Archivos cargados..." + " " + (x+1);
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo TF
		if(x == arrList.length - 1){
		    //Obtener el arreglo de TF's.
		    listPair = c.calcularTF(arrList);
		}
		Thread.sleep(30);
	    }
	}catch(Exception e){
	}
	return listPair;
    }

    /**
     * Método Auxiliar para la Progress Bar en el cálculo del TF-IDF dado un arreglo con los 
     * valores TF para cada lista y un Hashtable con los valores IDF de cada palabra en los documentos.
     * @param arrTF - Arreglo con los valores TF de cada palabra en cada documento.
     * @param ht - HashTable con los valores IDF de cada palabra en todos los documentos.
     * @return ArrayList<Pair<String,Double>>[] - Un areglo de listas con cada palabra de 
     * cada documento con su respectivo valor TF-IDF.
     */
    public ArrayList<Pair<String,Double>>[] progressBar(ArrayList<Pair<String,Double>>[] arrTF,
								  Hashtable<String, Pair<String, Double>>
								  ht){
	//Lista que devolveremos.
	ArrayList<Pair<String,Double>>[] listPair = new ArrayList[arrTF.length];
	//Creacion de un objeto tipo calculator.
	TFIDFcalculator c = new TFIDFcalculator();
	//Animacion.
	try{
	    for (int x =0 ; x < arrTF.length ; x++){
		String data = "\r" + "Archivos cargados..." + " " + (x+1);
		System.out.write(data.getBytes());
		//Condicion para empezar el calculo TF-IDF
		if(x == arrTF.length - 1){
		    //Obtener el arreglo de TF-IDF's
		    listPair = c.calcularTFIDF(arrTF, ht);
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
