//Package
package fciencias.edatos.motorBusqueda;

//Imports
import java.util.Scanner;
import java.lang.Math.*;
import java.io.*;
import java.util.*;

/**
 * Clase donde haremos los calculos respectivos para el TF e IDF de una
 * palabra y un documento.
 * @author Axel Daniel Malváez Flores
 * @version 2.0
 */
public class TFIDFcalculator {

    /**
     * Método que nos devuelve una lista de un documento, en una lista de parejas de
     * String y Boolean, inicializadas todas en false.
     * @param list - Una ArrayList de tipo String.
     * @return ArrayList<Pair<String, Boolean>> - Un ArrayList de tipo Pair<String, Boolean>.
     */
    private static ArrayList<Pair<String, Boolean>> listToFalse(ArrayList<String> list){
	if(list.isEmpty())
	    return new ArrayList<Pair<String, Boolean>>();
	//Lista que devolveremos
	ArrayList<Pair<String, Boolean>> fileList = new ArrayList<>();
	//Iterador de una lista.
	Iterator<String> iterador = list.iterator();
	String word = "";
	while(iterador.hasNext()){
	    Pair<String, Boolean> pair = new Pair<String, Boolean>((String)iterador.next(), false);
	    fileList.add(fileList.size(), pair);
	}
	return fileList;
    }
    
    /**
     * Método que nos devuelve una lista con elementos de tipo Pair<String, Integer>
     * utilizando la lista pasada como método.
     * @param list - Una ArrayList de tipo String.
     * @return ArrayList<Pair<String, Integer>> - Una ArrayList de tipo Pair<String, Integer>.
     */
    public static ArrayList<Pair<String, Integer>> listOcurrencias(ArrayList<String> lista){
	if(lista.isEmpty())
	    return new ArrayList<Pair<String, Integer>>();

	//Conversion de una lista con simples palabras a una listToFalse.
	ArrayList<Pair<String, Boolean>> list = listToFalse(lista);
	
	//Lista de ocurrencias y que devolveremos.
	ArrayList<Pair<String, Integer>> listOcurr = new ArrayList<>();
	
	/** Verificar las ocurrencias */
	//Nuevo objeto para añadir a listOcurr.
	Pair<String, Integer> nuevo = null;
	//Objeto Pair auxiliar para guardar una pareja temporal.
	Pair<String, Integer> auxInt = null;
	//Objeto Pair auxiliar para guardar una pareja temporal.
	Pair<String, Boolean> auxBool = null;
	//Variables auxiliares.
	String word = "";
	String wordComparable = "";
	for(int i = 0; i < list.size(); i++){
	    //Crear un Pair si es que aun el key de la pareja actual esta en estado false.
	    if(list.get(i).getKey() == false){
		nuevo = new Pair<String, Integer>(list.get(i).getValue(), 1);
		listOcurr.add(listOcurr.size(), nuevo);
		word = nuevo.getValue();
		//Verifica si hay palabras repetidas y de ser así las cuenta y las cambia a true.
		for(int j = i+1; j < list.size(); j++){
		    wordComparable = list.get(j).getValue();
		    //Aqui se hace la verificacion de las palabras repetidas y que esten en estado false.
		    if(word.equals(wordComparable) && list.get(j).getKey() == false){
			auxInt = listOcurr.get(listOcurr.size() - 1);
			auxInt.setKey(auxInt.getKey() + 1);
			auxBool = list.get(j);
			auxBool.setKey(true);
		    }
		}
	    }
	}
	return listOcurr;
    }

    /**
     * Método que calcula el IDF de cada palabra dentro de un arreglo que contiene a los 
     * documentos en forma de lista de Strings y nos regresa una HashTable con el IDF de cada 
     * palabra.
     * @param docsList - Un arreglo de ArrayList de Strings.
     * @return Hashtable<String, Pair<String, Double>> - Un HashTable con clave String y valores
     * de tipo Pair<String, Double>.
     */
    public Hashtable<String, Pair<String, Double>> calcularIDF(ArrayList<String>[] docsList){
	//Hashtable que devolveremos.
	Hashtable<String, Pair<String, Double>> ht = new Hashtable<>();

	//Variables auxiliares que necesitaremos para hacer los calculos.
	int contador = 0;
	Iterator<String> iterador = null;
	String word = "";
	double totalDocs = docsList.length + 1.0;
	double idfWord = 0;
	//Calcular el valor para cada palabra de cada elemento de docsList.
	for(int i = 0; i < docsList.length; i++){
	    //Verificacion si la lista es vacia.
	    if(docsList[i].isEmpty())
		continue;
	    //Creacion de un iterador para cada lista en docsList
	    iterador = docsList[i].iterator();
	    /* Mientras la lista tenga elementos, el contador se inicializa en 0 y
	     *  y se obtiene el elemento en ese nodo de la lista y posteriormente
	     *  se obtiene el valor de la pareja.
	     */
	    while(iterador.hasNext()){
		contador = 0;
		word = (String)iterador.next();
		//Verificacion si a word ya se calculo el IDF.
		if(ht.get(word) != null){
		    continue;
		}
		//Verificacion de cuantos documentos tienen la palabra word.
		for(int k = 0; k < docsList.length; k++){
		    if(docsList[k].isEmpty())
			continue;
		    if(docsList[k].contains(word)){
			contador++;
		    }
		}
		//Calculo del IDF de word.		
		if(contador > 0 && docsList.length > 0){
		    idfWord= Math.log(totalDocs / contador) / Math.log(2.0);
		}
		//Agregar a word y su idf al HashTable, con clave word y value la pareja.
		ht.put(word, new Pair<String, Double>(word, idfWord));
	    }
	}
	return ht;
    }

    /**
     * Método que calcula el IDF de una consulta. (Sobreescritura del metodo).
     * @param docsList - Un arreglo de ArrayLists de Strings (arreglo con los documentos en forma
     * de Strings).
     * @param consulta - La consulta vista en un ArrayList de Strings.
     * @return Hashtable<String, Pair<String, Double>> - Un HashTable con clave String y valores
     * de tipo Pair<String, Double>.
     */
    public Hashtable<String, Pair<String, Double>> calcularIDF(ArrayList<String>[] docsList,
							       ArrayList<String> consulta){
	//Hashtable que devolveremos.
	Hashtable<String, Pair<String, Double>> ht = new Hashtable<>();

	//Variables auxiliares que necesitaremos para hacer los calculos.
	int contador = 0;
	Iterator<String> iterador = null;
	String word = "";
	double totalDocs = docsList.length + 1.0;
	double idfWord = 0.0;
	//Creacion de un iterador para cada palabra en la consulta.
	iterador = consulta.iterator();
	/* Mientras la consulta tenga elementos, seguiremos haciendo el calculo.
	 */
	while(iterador.hasNext()){
	    contador = 0;
	    word = (String)iterador.next();
	    //Verificacion si a word ya se calculo el IDF.
	    if(ht.get(word) != null){
		    continue;
	    }
	    //Verificacion de cuantos documentos tienen la palabra word.
	    for(int k = 0; k < docsList.length; k++){
		if(docsList[k].isEmpty())
		    continue;
		if(docsList[k].contains(word))
		    contador++;
	    }
	    //Calculo del IDF de word.		
	    if(contador > 0 && docsList.length > 0){
		idfWord= Math.log(totalDocs / contador) / Math.log(2.0);
	    }
	    //Agregar a word y su idf al IDFTree,con clave word y element idfWord.
	    ht.put(word, new Pair<String, Double>(word, idfWord));
	}
	return ht;
    }

    /**
     * Método que calcula el TF de cada palabra dado un arreglo de ArrayLists de Strings y nos
     * regresa un arreglo de ArrayLists de tipo Pair<String, Double> con String equivalente a
     * la palabra y Double equivalente al valor TF de la palabra en ese documento.
     * @param docsList - Un arreglo de ArrayLists de tipo String.
     * @return ArrayList<Pair<String,Double>>[] - Arreglo de ArrayLists de tipo Pair<String, Double>.
     */
    public ArrayList<Pair<String,Double>>[] calcularTF(ArrayList<String>[] docsList){
	//Conversion de cada lista del docsList a una listOcurrencias.
	ArrayList<Pair<String, Integer>>[] docsListOcurrencias = new ArrayList[docsList.length];
	//ArrayList aux que agregaremos a docsListOcurrencias.
	ArrayList<Pair<String, Integer>> listOcurr = null;
	//Llenamos el arreglo docsListOcurrencias con listas de Ocurrencias de cada lista contenida
	//en docsList.
	for(int i = 0; i < docsList.length; i++){
	    listOcurr = listOcurrencias(docsList[i]);
	    docsListOcurrencias[i] = listOcurr;
	}
	//Arreglo de listas que devolveremos con los valores TF de cada una.
	ArrayList<Pair<String, Double>>[] tfList = new ArrayList[docsList.length];
	//ArrayList aux para agregar a tfList.
	ArrayList<Pair<String, Double>> tfListI = null;
	//Calculo del TF de cada palabra en cada lista.
	//Variables que necesitaremos para el calculo del TF.
	Iterator<Pair<String, Integer>> iterador = null;
	Pair<String, Integer> pareja = null;
	String word = "";
	int ocurrencia = 0;
	double tfWord = 0.0;
	//Verificamos para cada documento en el arreglo de Ocurrencias.
	for(int i = 0; i < docsListOcurrencias.length; i++){
	    //Lista que agregaremos a tfList.
	    tfListI = new ArrayList<>();
	    if(docsListOcurrencias[i].isEmpty()){
		tfList[i] = tfListI;
		continue;
	    }
	    //Creacion del iterador que recorrera la lista que esta en la posicion i.
	    iterador = docsListOcurrencias[i].iterator();
	    while(iterador.hasNext()){
		pareja = (Pair<String, Integer>)iterador.next();
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
     * Metodo que calcula el TF de cada palabra de una consulta dada. (Sobreescritura del metodo).
     * @param docsList - Un arreglo de ArrayLists de tipo String.
     * @param consulta - Una consulta vista como un ArrayList.
     * @return ArrayList<Pair<String,Double>>[] - Arreglo de ArrayLists de tipo Pair<String, Double> 
     * con los valores TF de cada palabra almacenada en el ArrayList consulta.
     */
    public ArrayList<Pair<String,Double>>[] calcularTF(ArrayList<String>[] docsList,
						       ArrayList<String> consulta){
	//Conversion de cada lista del docsList a una listOcurrencias.
	ArrayList<Pair<String, Integer>>[] docsListOcurrencias = new ArrayList[docsList.length];
	ArrayList<Pair<String, Integer>> listOcurr = null;
	//Lleando el docsListOcurrencias con cada listOcurr obtenida con el metodo listOcurrencia.
	for(int i = 0; i < docsList.length; i++){
	    listOcurr = listOcurrencias(docsList[i]);
	    docsListOcurrencias[i] = listOcurr;
	}
	/*Arreglo de listas que devolveremos con los valores TF de cada palabra de la
	 *consulta ya con listas.*/
	ArrayList<Pair<String, Double>>[] tfList = new ArrayList[docsList.length];
	ArrayList<Pair<String, Double>> reserva = null;
	//Llenar tfList de listas vacías para mas comodidad en el procesamiento.
	for(int j = 0; j < tfList.length; j++){
	    reserva = new ArrayList<Pair<String, Double>>();
	    tfList[j] = reserva;
	}

	/*Calculo del TF de cada palabra de consulta.*/
	//Variables que necesitaremos para el calculo del TF.
	Iterator<String> iterador = consulta.iterator();
	String word = "";
	Boolean contains = false;
	Pair<String, Integer> pareja= null;
	String valorPareja = "";
	int ocurrenciaPareja = 0;
	double tfWord = 0.0;

	//Mientras haya palabras en la consulta
	while(iterador.hasNext()){
	    //Obtenermos palabra por palabra de la consulta.
	    word = (String)iterador.next();
	    //Obtener el TF para cada documento de cada palabra de la consulta.
	    for(int k = 0; k < docsListOcurrencias.length; k++){
		/*Verificacion si alguna lista es vacia dentro del arreglo, automaticamente
		 *  pone el TF en 0.0 y añade la pareja a tfList[k] al final.
		 */
		if(docsListOcurrencias[k].isEmpty()){
		    //Establecemos el tf de word como 0.0.
		    tfWord = 0.0;
		    //Agregamos a word con su respectivo TF para el documento k, en la lista k de tfList.
		    tfList[k].add(tfList[k].size(), new Pair<String, Double>(word, tfWord));
		    continue;
		}
		//Iterador para la lista del elemento k de docsListOcurrencias.
		Iterator<Pair<String, Integer>> it = docsListOcurrencias[k].iterator();
		//Mientras haya elementos en la lista docsListOcurrencias[k].
		while(it.hasNext()){
		    //Obtenemos pareja por pareja
		    pareja = (Pair<String, Integer>)it.next();
		    //Obtenemos el valor de la pareja
		    valorPareja = pareja.getValue();
		    //Comparamos si nuestra word es igual al valor de la pareja.
		    if(word.equals(valorPareja)){
			//Obtenemos la clave de la pareja, para saber la ocurrencia.
			ocurrenciaPareja = pareja.getKey();
			//Calculamos el tf de word.
			if(ocurrenciaPareja > 0)
			    tfWord = (Math.log(ocurrenciaPareja) / Math.log(2)) + 1;
			//Agregamos a word con su respectivo TF para el documento k, en la lista k de tfList.
			tfList[k].add(tfList[k].size(), new Pair<String, Double>(word, tfWord));
			break;
		    }
		    //Si llegamos al final de la iteracion y ningun valor de alguna pareja es igual a word
		    if(!it.hasNext()){
			//Establecemos el tf de word como 0.0.
			tfWord = 0.0;
			//Agregamos a word con su respectivo TF para el documento k, en la lista k de tfList.
			tfList[k].add(tfList[k].size(), new Pair<String, Double>(word, tfWord));
		    }
		}
	    }
	}
	return tfList;
    }
    
    /**
     * Método para hacer el cálculo conjunto TF-IDF de una palabra en un conjunto de documentos.
     * @param tf - Arreglo de ArrayLists de tipo Pair<String, Double>.
     * @param idf - Hashtable con key String y value Pair<String, Double>.
     * @return ArrayList<Pair<String, Double>>[] - Arreglo de ArrayList de tipo Pair<String, Double>.
     * con cada palabra y su valor TF-IDF de cada documento.
     */
    public ArrayList<Pair<String, Double>>[] calcularTFIDF(ArrayList<Pair<String,Double>>[] tf,
							    Hashtable<String, Pair<String, Double>> idf){
	//Arr de listas que devolvera el metodo
	ArrayList<Pair<String, Double>>[] devolver = new ArrayList[tf.length];
	//Lista Auxiliar para añadirla al arreglo Devolver.
	ArrayList<Pair<String, Double>> tfIdfList = null;
	//Calculo del TF-IDF de cada palabra de cada documento.
	//Variables auxiliares temporales.
	Iterator<Pair<String,Double>> iterador = null;
	Pair<String, Double> pareja = null;
	Pair<String, Double> pareja2 = null;
	String word = "";
	double tfValue = 0.0;
	double idfValue = 0.0;
	double tfIdfValue = 0.0;
	//Para cada palabra en cada documento hacer el calculo TF-IDF.
	for(int i = 0; i < tf.length; i++){
	    tfIdfList = new ArrayList<Pair<String, Double>>();
	    if(tf[i].isEmpty()){
		devolver[i] = tfIdfList;
		continue;
	    }
	    iterador = tf[i].iterator();
	    while(iterador.hasNext()){
		pareja = (Pair<String, Double>)iterador.next();
		//TF
		word = pareja.getValue();
		tfValue = pareja.getKey();
		//IDF
		pareja2 = idf.get(word);
		idfValue = pareja2.getKey();
		//Calculo
		tfIdfValue = tfValue * idfValue;
		tfIdfList.add(tfIdfList.size(), new Pair<String, Double>(word, tfIdfValue));
	    }
	    devolver[i] = tfIdfList;
	}
	return devolver;
    }


    
    //AQUI NOS QUEDAAAAAMOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS
    
    
    
    /**
     * Método para verificar la similitud entre una consulta y un conjunto de
     * archivos.
     * @param tfIdf - Los valores tfIdf de cada palabra de todos los documentos.
     * @param tfIdfConsulta - Los valores tfIdf de cada palabra de la consulta con 
     * respecto a todos los documentos.
     * @return Pair<Integer, Double>[] - Un arreglo de tipo Pair<Integer, String> con el numero del 
     * documento y su similitud con la busqueda.
     */
    public Pair<Integer, Double>[] similitud(ArrayList<Pair<String, Double>>[] tfIdf,
					     ArrayList<Pair<String, Double>>[] tfIdfConsulta){
	//Arreglo que devolveremos
	Pair<Integer, Double>[] arr = new Pair[tfIdf.length];
	
	/*Calculo de la similitud entre la consulta y los documentos.*/
	//Suma de los tfIdfConsulta.
	double sum = 0.0;
	double sum2 = 0.0;
	double parejaKey = 0.0;
	double square = 0.0;
	double squareRoot = 0.0;
	double resultado = 0.0;
	Pair<String, Double> pareja = null;
	Pair<Integer, Double> pareja2 = null;
	//Inicio del calculo para cada documento.
	for(int k = 0; k < tfIdfConsulta.length; k++){
	    
	    /*Verificamos si alguna lista del arreglo tfIdf es vacia 
	      y de ser asi entramos en el siguiente if*/
	    if(tfIdf[k].isEmpty()){
		resultado = 0.0;
		//Añadir el resultado a la posicion k.
		pareja2 = new Pair<Integer, Double>(k, resultado);
		arr[k] = pareja2;
		continue;
	    }
	    
	    //Suma para tfIdfConsulta
	    sum = 0.0;
	    Iterator<Pair<String, Double>> iterador = tfIdfConsulta[k].iterator();
	    while(iterador.hasNext()){
		pareja = (Pair<String, Double>)iterador.next();
		parejaKey = pareja.getKey();
		sum = sum + parejaKey;
	    }
		
	    //Suma para tfIdf
	    sum2 = 0.0;
	    Iterator<Pair<String, Double>> iterador2 = tfIdf[k].iterator();
	    while(iterador2.hasNext()){
		pareja = (Pair<String, Double>)iterador2.next();
		parejaKey = pareja.getKey();
		square = Math.pow(parejaKey, 2);
		sum2 = sum2 + square;
	    }
	    //Squareroot para tfIdf.
	    squareRoot = Math.sqrt(sum2);
	    resultado = sum / squareRoot;

	    //Añadir el resultado a la posicion k.
	    pareja2 = new Pair<Integer, Double>(k, resultado);
	    arr[k] = pareja2;
	}
	return arr;
    }
}
