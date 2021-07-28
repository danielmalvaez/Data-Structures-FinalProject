//Package

//Imports
import java.text.Normalizer;
import java.io.*;
import java.util.*;

/**
 * Clase de prueba
 */
public class Main2{
    public static String depurar(String texto){
	String documentDepured = "";
	char letter = ' ';
	//Verificar si es letra o no.
	for(int i = 0; i < texto.length(); i++){
	    letter = texto.charAt(i);
	    if(!Character.isLetter(letter)){
		documentDepured = documentDepured + " ";
		continue;
	    }
	    documentDepured = documentDepured + letter;
	}
	
	documentDepured = Normalizer.normalize(documentDepured.toLowerCase(), Normalizer.Form.NFD);
	String noAccents = documentDepured.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	return noAccents;
    }

    public static void main(String[] args){
	//Un String muy corto.
	String corto = "BBÉÉàáèéìíòóùú hólá éstá és úná prúébá?¿)(/&%$·42435 !&/&(hola";
	//Un String considerable.
	String largo = "hólá cómó éstás éspéró qúé éstés bíén 98432752 %()( pérféctó";

	//Normalize
	corto = Normalizer.normalize(corto.toLowerCase(), Normalizer.Form.NFD);
	largo = Normalizer.normalize(largo.toLowerCase(), Normalizer.Form.NFD);
	
	//Depurar un String corto.
	long inicioCorto = System.currentTimeMillis();
	String cortoDep = corto.replaceAll("[^a-z\\s*]", "");
	long finCorto = System.currentTimeMillis();

	/*
	//Depurar un String largo.
	long inicioLargo = System.currentTimeMillis();
	String cortoDep3 = largo.replaceAll("[^a-z\\s*]", "");
	long finLargo = System.currentTimeMillis();

	//Depurar un String corto.
	long in1 = System.currentTimeMillis();
	String other1 = depurar(corto);
	long fin1 = System.currentTimeMillis();

	//Depurar un String largo.
	long in3 = System.currentTimeMillis();
	String other3 = depurar(largo);
	long fin3 = System.currentTimeMillis();
	
	System.out.println("--------------");
	System.out.println("con replaceAll");
	System.out.println("--------------\n");
	
	System.out.println("String Corto: " + cortoDep);
	System.out.println("Tiempo: "+ (finCorto-inicioCorto));

	System.out.println("String Largo");
	System.out.println("Tiempo: "+ (finLargo-inicioLargo));

	System.out.println("--------------");
	System.out.println("contando cada letra");
	System.out.println("--------------");

	System.out.println("String Corto: " + other1);
	System.out.println("Tiempo: "+ (fin1-in1));

	System.out.println("String Largo");
	System.out.println("Tiempo: "+ (fin3-in3));
	*/
	
	System.out.println("-------------------");
	System.out.println("HASHTABLE EXAMPLES");
	System.out.println("-------------------");

	Scanner io = new Scanner(cortoDep).useDelimiter("\\s*\\s");
	LinkedList<String> fileList = new LinkedList<>();
	ArrayList<String> arrList = new ArrayList<>();
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	    arrList.add(io.next());
	}

	int size = arrList.size();
	Hashtable<Integer, Pair<String, Integer>> ht = new Hashtable(size);
	Pair<String, Integer> parejaAdd = null;
	Pair<String, Integer> parejaAux = null;
	
	for(int index = arrList.size()-1; index >=0; index--){
	    String key = arrList.get(index);
	    parejaAux = ht.get(key);
	    if(ht.get(key) == null){
		parejaAdd = new Pair<String, Integer>(key, 1);
		ht.put(key, parejaAdd);
	    }else{
		parejaAux = gt.get(key);
		parejaAux.setKey(parejaAux.getKey() + 1);
		ht.put(key, parejaAux);
	    }
	}
    }
}
