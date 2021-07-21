//Package
//package ProyectoFinal;

//Imports
import java.util.Scanner;
import java.lang.Math.*;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

/**
 * Clase donde haremos los calculos respectivos para el TF e IDF de una
 * palabra y un documento.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class TFIDFcalculator {

    /**
     * Método que nos devuelve una lista de un documento, en una lista de parejas de
     * String y Boolean, inicializadas todas en false.
     * @param list - Una lista simplemente ligada de tipo String
     * @return LinkedList<Pair<String, Boolean>> - Una lista simplemente ligada de tipo Pair<String, Boolean>.
     */
    public static LinkedList<Pair<String, Boolean>> listToFalse(LinkedList<String> list){
	//Lista que devolveremos
	LinkedList<Pair<String, Boolean>> fileList = new LinkedList<>();
	//Iterador de una lista.
	Iterator iterador = list.iterador();
	String word = "";
	int contador = 0;
	while(iterador.hasNext()){
	    Pair<String, Boolean> pair = new Pair<String, Boolean>(list.get(contador++), false);
	    fileList.add(fileList.size(), pair);
	    contador++;
	}
	return fileList;
    }

    /**
     * Metodo que nos devuelve una lista con elementos de tipo Pair<String, Integer>
     * utilizando la lista pasada como método.
     * @param list - Una lista simplemente ligada de tipo Pair<String, Boolean>.
     * @return LinkedList<Pair<String, Integer>> - Una lista simplemente ligada de tipo Pair<String, Integer>.
     */
    public static LinkedList<Pair<String, Integer>> listOcurrencias(LinkedList<String> lista){
	if(lista.isEmpty())
	    return null;

	//Conversion de una lista con simples palabras a una listToFalse.
	LinkedList<Pair<String, Boolean>> list = listToFalse(lista);

	//Lista de ocurrencias.
	LinkedList<Pair<String, Integer>> listOcurr = new LinkedList<>();
	
	/** Verificar las ocurrencias */
	//Nuevo objeto para añadir a listOcurr.
	Pair<String, Integer> nuevo = null;
	//Objeto auxiliar para guardar una pareja temporal.
	Pair<String, Integer> aux = null;
	int claveAnterior = 0;
	for(int i = 0; i < list.size()-1; i++){
	    //Crear un pair si es que aun el key de la pareja actual esta en estado false.
	    if(list.get(i).getKey() == false){
		nuevo = new Pair<String, Integer>(list.get(i).getValue(), 1);
		listOcurr.add(listOcurr.size(), nuevo);
	    }
	    //Verifica si hay palabras repetidas y de ser así las cuenta y las cambia a true.
	    for(int j = i+1; j < list.size(); j++){
		//Aqui se hace la verificacion de las palabras repetidas y que esten en estado false.
		if(list.get(i).getValue().equals(list.get(j).getValue()) && list.get(j).getKey() == false){
		    aux = listOcurr.get(i);
		    claveAnterior = listOcurr.get(i).getKey();
		    aux.setKey(claveAnterior + 1);
		    list.get(j).setKey(true);
		}
	    }
	    //Para crear la pareja del ultimo elemento si este esta en estado false.
	    if(i == list.size() - 2 && list.get(i+1).getKey() == false){
		nuevo = new Pair<>(list.get(i+1).getValue(), 1);
		listOcurr.add(listOcurr.size(), nuevo);
	    }
	}
	return listOcurr;
    }

    /**
     * Metodo que calcula el IDF de cada palabra dentro de un arreglo que contiene a los documentos
     * en forma de lista de Strings y nos regresa un Arbol Rojinegro con el IDF de cada palabra sin
     * admitir repetidos.
     * @param docsList - Un arreglo de LinkedLists de Strings (arreglo con los documentos en forma
     * de Strings).
     * @return RedBlackTree<Double, String> - Un Arbol Rojinegro con el IDF de cada palabra, sin
     * admitir repetidos.
     */
    public RedBlackTree<Double, String> calcularIDF(LinkedList<String>[] docsList){
	//IDFTree que devolveremos.
	RedBlackTree<Double, String> idfTree = new RedBlackTree<>();

	//Variables auxiliares que necesitaremos para hacer los calculos.
	int contador = 0;
	Iterator iterador = null;
	String word = "";
	double totalDocs = docsList.length + 1.0;
	double idfWord = 0;
	//Calcular el valor para cada palabra de cada elemento de docsList.
	for(int i = 0; i < docsList.length; i++){
	    //Creacion de un iterador para cada lista en docsList
	    iterador = docsList[i].iterador();
	    /* Mientras la lista tenga elementos, el contador se inicializa en 0 y
	     *  y se obtiene el elemento en ese nodo de la lista y posteriormente
	     *  se obtiene el valor de la pareja.
	     */
	    while(iterador.hasNext()){
		contador = 0;
		word = (String)iterador.next();
		//Verificacion si a word ya se calculo el IDF.
		if(idfTree.retrieve(word) != null){
		    continue;
		}
		//Verificacion de cuantos documentos tienen la palabra word.
		for(int k = 0; k < docsList.length; k++){
		    if(docsList[k].contains(word)){
			contador++;
		    }
		}
		//Calculo del IDF de word.		
		if(contador > 0 && docsList.length > 0){
		    idfWord= Math.log(totalDocs / contador) / Math.log(2.0);
		}
		//Agregar a word y su idf al IDFTree,con clave word y element idfWord.
		idfTree.insert(idfWord, word);
	    }
	}
	return idfTree;
    }
    /**
     * Metodo que calcula el TF de cada palabra dado un arreglo de LinkedLists de Strings y nos
     * regresa un arreglo de LinkedLists de tipo Pair<String, Double> con String equivalente a
     * la palabra y Double equivalente al valor TF de la palabra en el documento.
     * @param docsList - Un arreglo de LinkedLists de tipo String.
     * @return LinkedList<Pair<String,Double>>[] - Arreglo de LinkedLists de tipo Pair<String, Double>.
     */
    public LinkedList<Pair<String,Double>>[] calcularTF(LinkedList<String>[] docsList){
	//Conversion de cada lista del docsList a una listOcurrencias.
	LinkedList<Pair<String, Integer>>[] docsListOcurrencias = new LinkedList<Pair<String, Integer>>()[docList.length];
	LinkedList<Pair<String, Integer>> listOcurr = null;
	for(int i = 0; i < docsList.length; i++){
	    listOcurr = listOcurrencias(docsList[i]);
	    docsListOcurrencias[i] = listOcurr;
	}

	//Arreglo de listas que devolveremos con los valores TF de cada una.
	LinkedList<Pair<String, Double>>[] tfList = new LinkedList<Pair<String, Double>>()[docsList.length];
	//Calculo del TF de cada palabra en cada lista.
	//Variables que necesitaremos para el calculo del TF.
	Iterator iterador = null;
	Pair<String, Integer> pareja = null;
	String word = "";
	int ocurrencia = 0;
	double tfWord = 0.0;
	//Verificamos para cada documento en el arreglo de Ocurrencias.
	for(int i = 0; i < docsListOcurrencias.length; i++){
	    //Lista que agregaremos a tfList.
	    LinkedList<Pair<String, Double>> tfListI = new LinkedList<>(); 
	    //Creacion del iterador que recorrera la lista que esta en la posicion i.
	    iterador = docsListOcurrencias[i].iterador();    
	    while(iterador.hasNext()){
		pareja = iterador.next();
		word = pareja.getValue();
		ocurrencia = pareja.getKey();
		//Verificacion, si la ocurrencia es mayor a 0, hace el calculo del TF.
		if(ocurrencia > 0)
		    tfWord = (Math.log(ocurrencia) / Math.log(2)) + 1;
		tfListI.add(tfListI.size(), new Pair<String, Double>(word, tfWord));
	    }
	    tfList[i] = tfListI;
	}
	return tfList;
    }

    /**
     * Metodo para hacer el calculo conjunto TF-IDF de una palabra en un conjunto de documentos.
     * @param tf - Arreglo de LinkedLists de tipo Pair<String, Double>.
     * @param idf - Arbol Rojinegro de tipo Double y String.
     * @return LinkedList<Pair<String, Double>>[] - Arreglo de LinkedLists de tipo Pair<String, Double>
     * con cada palabra y su valor TF-IDF de cada documento.
     */
    public LinkedList<Pair<String, Double>>[] calcularTFIDF(LinkedList<Pair<String,Double>>[] tf, RedBlackTree<Double, String> idf){
	//Arr de listas que devolvera el metodo
	LinkedList<Pair<String, Double>>[] devolver = new LinkedList<Pair<String, Double>>()[tf.length];
	//Lista Auxiliar para meter al arreglo Devolver.
	LinkedList<Pair<String, Double>> tfIdfList = null;
	//Calculo del TF-IDF de cada palabra de cada documento.
	//Variables auxiliares temporales.
	Iterator iterador = null;
	Pair<String, Double> pareja = null;
	String word = "";
	double tfValue = 0.0;
	double idfValue = 0.0;
	double tfIdfValue = 0.0;
	//Para cada palabra en cada documento hacer el calculo TF-IDF.
	for(int i = 0; i < tf.length; i++){
	    iterador = tf[i].iterador();
	    tfIdfList = new LinkedList<Pair<String, Double>>();
	    while(iterador.hasNext()){
		pareja = iterador.next();
		word = pareja.getValue();
		tfValue = pareja.getKey();
		idfValue = retrieve(word);
		tfIdfValue = tfValue * idfValue;
		tfIdfList.add(tfIdfList.size(), new Pair<String, Double>(word, tfIdfValue));
	    }
	    devolver[i] = tfIdfList;
	}
	return devolver;
    }
    
    public static void main(String[] args){
	Reader read = new Reader();
	try{
	    File file = new File("ejemplo.txt");
	}catch(IOException e){
	    e.printStackTrace();
	}
	LinkedList<String> prueba1 = read.readDocument("ejemplo.tx");
	LinkedList<String> prueba2 = read.readDocument("ejemplo2.tx");
	LinkedList<String>[] docsList = new LinkedList<>()[2];
	docsList[0] = prueba1;
	docsList[1] = prueba2;

	LinkedList<Pair<String,Double>>[] tf = calcularTF(docsList);
	RedBlackTree<Double, String> idfTree = calcularIDF(docsList);
	LinkedList<Pair<String, Double>>[] tfIdf = calcularTFIDF(tf, idftree);
    }
}
