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
import java.util.ArrayList;

/**
 * Clase que nos ayudara a leer documentos con extension .txt  y los almacenará
 * en un ArrayList de Strings.
 * @author Axel Daniel Malváez Flores
 * @version 2.0
 */
public class Reader{
    
    /**
     * Metodo que utiliza como parametro un file de extension .txt y lo guarda en un
     * String para despues pasarlo a un ArrayList y posteriormente regresarla con 
     * cada palabra en una casilla de la lista.
     * @param File - Un archivo con extensión .txt ya convertido en File.
     * @return ArrayList<String> - Una lista ligada con cada palabra del texto en 
     * un nodo.
     */
    public ArrayList<String> readDocument(File file){
	//Creacion de objetod
	FileReader reader = null;
	BufferedReader lector = null;
	//Variables auxiliares para recuperar el texto.
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
	
	//Normalizar el String.
	document = Normalizer.normalize(document.toLowerCase(), Normalizer.Form.NFD);
	String documentDepured = document.replaceAll("[^a-z\\s*]", "");
	
	Scanner io = new Scanner(documentDepured).useDelimiter("\\s*\\s");
	ArrayList<String> fileList = new ArrayList<>();
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	}
	return fileList;
    }//Method readDocument

    /**
     * Metodo que utiliza como parametro un string y lo convierte en una lista 
     * simplemente ligada.
     * @param busqueda - Un String con la busqueda a realizar.
     * @return ArrayList<String> - Una lista ligada con cada palabra del texto en un nodo.
     */
    public ArrayList<String> readString(String busqueda){
	//Normalizar la busqueda.
	busqueda = Normalizer.normalize(busqueda.toLowerCase(), Normalizer.Form.NFD);
	String busquedaDepured = busqueda.replaceAll("[^a-z\\s*]", "");
	
	Scanner io = new Scanner(busquedaDepured).useDelimiter("\\s*\\s");
	ArrayList<String> fileList = new ArrayList<>();
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	}
	return fileList;
    }//Method readString
}//Class
