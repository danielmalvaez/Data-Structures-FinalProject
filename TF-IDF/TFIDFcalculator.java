//Package
//package ProyectoFinal;

//Imports
import java.util.Scanner;
import java.lang.Math.*;
import java.io.File;
import java.util.Iterator;
import java.util.Arrays;


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
	while(iterador.hasNext()){
	    Pair<String, Boolean> pair = new Pair<String, Boolean>((String)iterador.next(), false);
	    fileList.add(fileList.size(), pair);
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
     * Metodo que calcula el IDF de una consulta. (Sobreescritura del metodo).
     * @param docsList - Un arreglo de LinkedLists de Strings (arreglo con los documentos en forma
     * de Strings).
     * @param consulta - La consulta vista en una lista de strings.
     * @return RedBlackTree<Double, String> - Un Arbol Rojinegro con el IDF de cada palabra, sin
     * admitir repetidos.
     */
    public RedBlackTree<Double, String> calcularIDF(LinkedList<String>[] docsList, LinkedList<String> consulta){
	//IDFTree que devolveremos.
	RedBlackTree<Double, String> idfTree = new RedBlackTree<>();

	//Variables auxiliares que necesitaremos para hacer los calculos.
	int contador = 0;
	Iterator iterador = null;
	String word = "";
	double totalDocs = docsList.length + 1.0;
	double idfWord = 0;
	//Creacion de un iterador para cada lista en docsList
	iterador = consulta.iterador();
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
	LinkedList<Pair<String, Integer>>[] docsListOcurrencias = new LinkedList[docsList.length];
	
	for(int i = 0; i < docsList.length; i++){
	    LinkedList<Pair<String, Integer>> listOcurr = listOcurrencias(docsList[i]);
	    docsListOcurrencias[i] = listOcurr;
	}
	//Arreglo de listas que devolveremos con los valores TF de cada una.
	LinkedList<Pair<String, Double>>[] tfList = new LinkedList[docsList.length];
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
     * @param docsList - Un arreglo de LinkedLists de tipo String.
     * @param consulta - Una consulta vista como una LinkedList.
     * @return LinkedList<Pair<String,Double> - Arreglo de LinkedLists de tipo Pair<String, Double> con los valores TF de cada 
     * palabra de consulta.
     */
    public LinkedList<Pair<String,Double>>[] calcularTF(LinkedList<String>[] docsList, LinkedList<String> consulta){
	//Conversion de cada lista del docsList a una listOcurrencias.
	LinkedList<Pair<String, Integer>>[] docsListOcurrencias = new LinkedList[docsList.length];
	for(int i = 0; i < docsList.length; i++){
	    LinkedList<Pair<String, Integer>> listOcurr = listOcurrencias(docsList[i]);
	    docsListOcurrencias[i] = listOcurr;
	}
	//Arreglo de listas que devolveremos con los valores TF de cada palabra de la consulta ya con listas.
	LinkedList<Pair<String, Double>>[] tfList = new LinkedList[docsList.length];
	LinkedList<Pair<String, Double>> reserva = null;
	//Llenar tfList de listas vacías para mas comodidad en el procesamiento.
	for(int j = 0; j < tfList.length; j++){
	    reserva = new LinkedList<Pair<String, Double>>();
	    tfList[j] = reserva;
	}

	//Calculo del TF de cada palabra de consulta.
	//Variables que necesitaremos para el calculo del TF.
	Iterator iterador = consulta.iterador();
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
		//Iterador para la lista del elemento k de docsListOcurrencias.
		Iterator it = docsListOcurrencias[k].iterador();
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
     * Metodo para hacer el calculo conjunto TF-IDF de una palabra en un conjunto de documentos.
     * @param tf - Arreglo de LinkedLists de tipo Pair<String, Double>.
     * @param idf - Arbol Rojinegro de tipo Double y String.
     * @return LinkedList<Pair<String, Double>>[] - Arreglo de LinkedLists de tipo Pair<String, Double>
     * con cada palabra y su valor TF-IDF de cada documento.
     */
    public LinkedList<Pair<String, Double>>[] calcularTFIDF(LinkedList<Pair<String,Double>>[] tf, RedBlackTree<Double, String> idf){
	//Arr de listas que devolvera el metodo
	LinkedList<Pair<String, Double>>[] devolver = new LinkedList[tf.length];
	//Lista Auxiliar para añadirla al arreglo Devolver.
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
		pareja = (Pair<String, Double>)iterador.next();
		word = pareja.getValue();
		tfValue = pareja.getKey();
		idfValue = idf.retrieve(word);
		tfIdfValue = tfValue * idfValue;
		tfIdfList.add(tfIdfList.size(), new Pair<String, Double>(word, tfIdfValue));
	    }
	    devolver[i] = tfIdfList;
	}
	return devolver;
    }
    
    public double[] similitud(LinkedList<Pair<String, Double>>[] tfIdf, LinkedList<Pair<String, Double>>[] tfIdfConsulta){
	if(tfIdf.length == 0 || tfIdfConsulta.length == 0)
	    return null;
	else if(tfIdf.length != tfIdfConsulta.length)
	    return null;
	    
	
	//Arreglo que devolveremos
	double[] arr = new double[tfIdf.length];
	
	/*Calculo de la similitud entre la consulta y los documentos.*/
	//Suma de los tfIdfConsulta.
	double sum = 0.0;
	double sum2 = 0.0;
	double parejaKey = 0.0;
	double square = 0.0;
	double squareRoot = 0.0;
	double resultado = 0.0;
	Pair<String, Double> pareja = null;
	for(int k = 0; k < tfIdfConsulta.length; k++){
	    //Suma para tfIdfConsulta
	    sum = 0.0;
	    Iterator iterador = tfIdfConsulta[k].iterador();
	    while(iterador.hasNext()){
		pareja = (Pair<String, Double>)iterador.next();
		parejaKey = pareja.getKey();
		sum = sum + parejaKey;
	    }

	    //Suma para tfIdf
	    sum2 = 0.0;
	    Iterator iterador2 = tfIdf[k].iterador();
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
	    arr[k] = resultado;
	}
	Arrays.sort(arr);
	return arr;
    }
    
    public static void main(String[] args){
	Reader read = new Reader();
	TFIDFcalculator aux = new TFIDFcalculator();
	File file = null;
	File file2 = null;
	File file3 = null;
	File file4 = null;
	File file5 = null;
	try{
	    file = new File("ejemplo.txt");
	    file2 = new File("ejemplo2.txt");
	    file3 = new File("ejemplo3.txt");
	    file4 = new File("ejemplo4.txt");
	    file5 = new File("ejemplo5.txt");
	    
	}catch(Exception e){
	    e.printStackTrace();
	}
	LinkedList<String> prueba1 = read.readDocument(file);
	LinkedList<String> prueba2 = read.readDocument(file2);
	LinkedList<String> prueba3 = read.readDocument(file3);
	LinkedList<String> prueba4 = read.readDocument(file4);
	LinkedList<String> prueba5 = read.readDocument(file5);
	LinkedList<String>[] docsList = new LinkedList[5];
	docsList[0] = prueba1;
	docsList[1] = prueba2;
	docsList[2] = prueba3;
	docsList[3] = prueba4;
	docsList[4] = prueba5;

	System.out.println("Lista 1 documento ejemplo.txt");
	prueba1.show();
	
	System.out.println();
	
	System.out.println("Lista 2 documento ejemplo2.txt");
	prueba2.show();

	System.out.println();

	System.out.println("Lista 3 documento ejemplo3.txt");
	prueba3.show();

	System.out.println();

	System.out.println("Lista 4 documento ejemplo4.txt");
	prueba4.show();

	System.out.println();

	System.out.println("Lista 5 documento ejemplo5.txt");
	prueba5.show();

	System.out.println();

	RedBlackTree<Double, String> idfTree = aux.calcularIDF(docsList);
	LinkedList<Pair<String,Double>>[] tf = aux.calcularTF(docsList);
	LinkedList<Pair<String, Double>>[] tfIdf = aux.calcularTFIDF(tf, idfTree);

	System.out.println("IDF Tree:");
	idfTree.preorden();
	System.out.println();
	
	System.out.println("Lista de los TF");
	for(int i = 0; i < tf.length; i++)
	    tf[i].show();
	System.out.println();

	System.out.println("Lista de los TF-IDF");
	for(int i = 0; i < tfIdf.length; i++){
	    tfIdf[i].show();
	    System.out.println("Fin de la lista " + i);
	}
	System.out.println();
    }
}
