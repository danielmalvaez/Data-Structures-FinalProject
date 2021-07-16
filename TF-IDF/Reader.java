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
 * Clase que nos ayuda a leer documentos .txt y almacenarlos en una lista.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Reader{
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
	
	String normalDoc = Normalizer.normalize(documentDepured.toLowerCase(), Normalizer.Form.NFD);
	String noAccents = normalDoc.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	Scanner io = new Scanner(noAccents).useDelimiter("\\s*\\s");
	LinkedList<String> fileList = new LinkedList<>();
	
	while(io.hasNext()){
	    fileList.add(fileList.size(), io.next());
	}
	return fileList;
    }//Method readDocument

}//Class
