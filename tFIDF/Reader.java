//Package
//package fciencias.edatos.motorBusqueda;

//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.text.Normalizer;

/**
 * Clase que nos ayudara a leer documentos con extension .txt  y los almacenará
 * en una lista.
 * @author Axel Daniel Malvaez Flores
 * @version 2.0
 */
public class Reader{
    
    /**
     * Metodo que utiliza como parametro un file de extension .txt y lo guarda en un
     * String para despues pasarlo a una lista simplemente ligada y posteriormente
     * regresarla con cada palabra en un nodo de la lista.
     * @param File - Un archivo con extensión .txt.
     * @return LinkedList<String> - Una lista ligada con cada palabra del texto en un nodo.
     */
    public LinkedList<String> readDocument(File file){
	FileReader reader = null;
	BufferedReader lector = null;
	
	String line = "";
	String document = "";
	try{
	    reader = new FileReader(file);
	    lector = new BufferedReader(reader);
	    
	    do{
		line = lector.readLine();
		if(line != null)
		    document = document + line;
	    }while(line != null);
	}catch(FileNotFoundException e){
	    e.printStackTrace();
	}catch(IOException e){
	    e.printStackTrace();
	}finally{
	    if(lector != null){
		try{
		    lector.close();
		    reader.close();
		}catch(IOException e){
		    e.printStackTrace();
		}
	    }else{
		System.out.println("No hay ningun archivo abierto");
	    }
	}
	
	//Normalize the String.
	document = Normalizer.normalize(document.toLowerCase(), Normalizer.Form.NFD);
	String documentDepured = document.replaceAll("[^a-z\\s*]", "");
	
	Scanner io = new Scanner(documentDepured).useDelimiter("\\s*\\s");
	LinkedList<String> fileList = new LinkedList<>();
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	}
	return fileList;
    }//Method readDocument

    /**
     * Metodo que utiliza como parametro un string y lo convierte en una lista 
     * simplemente ligada.
     * @param busqueda - Un String con la busqueda a realizar.
     * @return LinkedList<String> - Una lista ligada con cada palabra del texto en un nodo.
     */
    public LinkedList<String> readString(String busqueda){
	String busquedaDepured = "";
	char letter = ' ';
	//Verificar que la busqueda no tenga caracteres invalidos.
	for(int i = 0; i < busqueda.length(); i++){
	    letter = busqueda.charAt(i);
	    if(!Character.isLetter(letter)){
		busquedaDepured = busquedaDepured + " ";
		continue;
	    }
	    busquedaDepured = busquedaDepured + letter;
	}
	
	busquedaDepured = Normalizer.normalize(busquedaDepured.toLowerCase(), Normalizer.Form.NFD);
	String noAccents = busquedaDepured.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	Scanner io = new Scanner(noAccents).useDelimiter("\\s*\\s");
	LinkedList<String> fileList = new LinkedList<>();
	
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	}
	return fileList;
    }//Method readString
}//Class
