//Package
//package ProyectoFinal;

//Imports
import java.util.Scanner;
/*
import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;
import java.io.IOException;
import java.lang.String;
import java.text.Normalizer;

*/

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
    public static LinkedList<Pair<String, Boolean>> listaFalse(LinkedList<String> list){
	LinkedList<Pair<String, Boolean>> fileList = new LinkedList<>();
	while(list.hasNext()){
	    Pair<String, Boolean> pair = new Pair<>(io.next(), false);
	    fileList.add(fileList.size(), pair);
	}
    }

    /**
     * Metodo que nos devuelve una lista con elementos de tipo Pair<String, Integer>
     * utilizando la lista pasada como método.
     * @param list - Una lista simplemente ligada de tipo Pair<String, Boolean>.
     * @return LinkedList<Pair<String, Integer>> - Una lista simplemente ligada de tipo Pair<String, Integer>.
     */
    public static LinkedList<Pair<String, Integer>> listaOcurrencias(LinkedList<Pair<String, Boolean>> list){
	if(list.isEmpty())
	    return null;

	//Lista de ocurrencias.
	LinkedList<Pair<String, Integer>> listOcurr = new LinkedList<>();
	
	/** Verificar las ocurrencias*/
	Pair<String, Integer> nuevo = null;
	
	for(int i = 0; i < list.size()-1; i++){
	    //Crear un pair si es que aun esta en estado false.
	    if(list.get(i).getKey() == false){
		nuevo = new Pair<>(list.get(i).getValue(), 1);
		listOcurr.add(listOcurr.size(), nuevo);
	    }
	    for(int j = i+1; j < list.size(); j++){
		if(list.get(i).getValue().equals(list.get(j).getValue()) && list.get(j).getKey() == false){
		    Pair<String, Integer> aux = listOcurr.get(i);
		    int claveAnterior = listOcurr.get(i).getKey();
		    aux.setKey(claveAnterior + 1);
		    list.get(j).setKey(true);
		}
	    }
	    if(i == list.size() - 2 && list.get(i+1).getKey() == false){
		nuevo = new Pair<>(list.get(i+1).getValue(), 1);
		listOcurr.add(listOcurr.size(), nuevo);
	    }
	}
	return listOcurr;
    }//listaOcurrencias method.

    

    
    











    public static void main(String[] args){
	LinkedList<Pair<String, Boolean>> list = new LinkedList<>();
	list.add(list.size(), new Pair<String, Boolean>("hola", false));
	list.add(list.size(), new Pair<String, Boolean>("que", false));
	list.add(list.size(), new Pair<String, Boolean>("haces", false));
	list.add(list.size(), new Pair<String, Boolean>("como", false));
	list.add(list.size(), new Pair<String, Boolean>("estas", false));
	list.add(list.size(), new Pair<String, Boolean>("hola", false));
	list.add(list.size(), new Pair<String, Boolean>("estas", false));
	list.add(list.size(), new Pair<String, Boolean>("espero", false));
	list.add(list.size(), new Pair<String, Boolean>("que", false));
	list.add(list.size(), new Pair<String, Boolean>("si", false));
	list.add(list.size(), new Pair<String, Boolean>("pasear", false));
	list.add(list.size(), new Pair<String, Boolean>("por", false));
	list.add(list.size(), new Pair<String, Boolean>("el", false));
	list.add(list.size(), new Pair<String, Boolean>("parque", false));
      
	LinkedList<Pair<String, Integer>> miLista2 = listaOcurrencias(list);
	miLista2.show();
      
    }
}
