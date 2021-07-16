//Package
//package ProyectoFinal;

//Imports
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

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
	    reader = new FileReader();
	    lector = new BufferedReader(new FileReader(file));
	    
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
	char letter = null;
	for(int i = 0; i < document.length(); i++){
	    letter = charAt(i);
	    if(){
	    }
	}
	
	LinkedList<String> fileList = new LinkedList<>();
	

    }//Method readDocument
    













}//Class
