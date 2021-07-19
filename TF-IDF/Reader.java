//Package
//package ProyectoFinal;

//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.text.Normalizer;

/**
 * Clase que nos ayudara a leer documentos con extension .txt  y los almacenara
 * en una lista.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Reader{

    /**
     * Metodo que utiliza como parametro un file de extension .txt y lo guarda en un
     * String para despues pasarlo a una lista simplemente ligada y posteriormente
     * regresarla con cada palabra en la lista.
     * @param File - Un archivo con extensión .txt.
     * @return LinkedList<Pair<String, Boolean>> - Una lista ligada con cada palabra del texto en un nodo.
     */
    public LinkedList<Pair<String, Boolean>> readDocument(File file){
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

	String documentDepured = "";
	char letter = ' ';
	for(int i = 0; i < document.length(); i++){
	    letter = document.charAt(i);
	    if(!Character.isLetter(letter)){
		documentDepured = documentDepured + " ";
		continue;
	    }
	    documentDepured = documentDepured + letter;
	}
	
	documentDepured = Normalizer.normalize(documentDepured.toLowerCase(), Normalizer.Form.NFD);
	String noAccents = documentDepured.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	Scanner io = new Scanner(noAccents).useDelimiter("\\s*\\s");
	LinkedList<Pair<String, Boolean>> fileList = new LinkedList<>();
	
	while(io.hasNext()){
	    Pair<String, Boolean> pair = new Pair<>(io.next(), false);
	    fileList.add(fileList.size(), pair);
	}
	return fileList;
    }//Method readDocument

}//Class
