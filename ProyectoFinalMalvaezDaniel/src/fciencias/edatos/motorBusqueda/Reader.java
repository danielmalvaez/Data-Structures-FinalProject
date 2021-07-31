//Package
//package fciencias.edatos.motorBusqueda;
package fciencias.edatos.motorBusqueda;

//Imports
import java.io.*;
import java.util.Scanner;
import java.text.Normalizer;
import java.util.ArrayList;

/**
 * Clase que nos ayudara a leer documentos con extension .txt  y los almacenará
 * en un ArrayList de Strings.
 * @author Axel Daniel Malváez Flores
 * @version 2.0
 */
public class Reader {
    
    /**
     * Metodo que utiliza como parametro un file de extension .txt y lo guarda en un
     * String para despues pasarlo a un ArrayList y posteriormente regresarla con 
     * cada palabra en una casilla de la lista.
     * @param File - Un archivo con extensión .txt ya convertido en File.
     * @return ArrayList<String> - Una ArrayList con cada palabra del texto en 
     * un nodo.
     */
    public ArrayList<String> readDocument(File file){
	//Creacion de objetos
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
     * Metodo que nos permite leer  un documento con extension txt y pasarlo a un
     * String.
     * @return String - El texto que hay dentro del archivo.
     * @param historial - El nombre del archivo.
     */
    public String readDocument(String historial){
	BufferedReader lector = null;
        FileReader archivo = null;
        String linea = null;
	String hst = "";
        String nombreArchivo = historial;
        try{
	    //Creamos el archivo en donde leeremos
	    archivo=new FileReader(nombreArchivo);
	    //Creamos el objeto de lectura
	    lector=new BufferedReader(archivo);
	    //ciclo para leer todo el archivo
	    do {
		//recuperamos el objeto
		linea = lector.readLine();
		if(linea != null)
		    hst = hst + linea + "\n";
	    } while (linea != null);
        } catch(FileNotFoundException e) {
            linea = "No tienes historial aún.";
            return linea;
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            if (lector!= null) {
                try {		    
                    lector.close();                    
                    archivo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	    return hst;
    }

    /**
     * Metodo que nos permite escribir un String en un documento con extension txt.
     * @param historial - El string que queremos escribir en un archivo txt.
     */
    public void writeString(String historial){
	//Objeto para escribir
        BufferedWriter escritor = null;
        //Objeto del archivo
        FileWriter archivo = null;
	//Nombre del archivo en el que se eescribira.
	String nombreArchivo = "HistorialDeBusqueda.txt";
	File file = null;
	String historial1 = historial + System.lineSeparator();
	String recuperar = "";
	try{
	    //Creamos el archivo en donde leeremos
	    file = new File(nombreArchivo);
            //Si ya existe lo sobreescribe por el contrario lo crea
	    archivo= new FileWriter(file.getAbsoluteFile(), true);
	    //Creamos el objeto de lectura
	    escritor=new BufferedWriter(archivo);
	    //escribimos en el archivo
	    escritor.write(historial);
	    escritor.newLine();
	}catch(FileNotFoundException e){
	    System.out.println("No tienes historial.");
	}catch(IOException e){
	    e.printStackTrace();
	}finally{
	    if(escritor != null){
		try{
		    escritor.close();
		    archivo.close();
		}catch(IOException e){
		    e.printStackTrace();
		}
	    }else{
		System.out.println("No hay ningun archivo abierto");
	    }
	}
    }

    /**
     * Método que utiliza como parámetro un string y lo convierte en un ArrayList.
     * @param busqueda - Un String con la busqueda a realizar.
     * @return ArrayList<String> - Una lista ligada con cada palabra del texto en 
     * un nodo.
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
    
    /**
     * Método que nos limpia un documento txt.
     * @param name - Nombre del documento del que queremos obtener su respectiva
     * información.
     */
    public void cleanDocument(String name){
        //Objeto para escribir
        BufferedWriter escritor = null;
        //Objeto del archivo
        FileWriter archivo = null;
	//Nombre del archivo en el que se eescribira.
	String nombreArchivo = name;
	File file = null;
        try{
	    //Creamos el archivo en donde leeremos
	    file = new File(nombreArchivo);
            //Si ya existe lo sobreescribe por el contrario lo crea
	    archivo= new FileWriter(file);
	    //Creamos el objeto de lectura
	    escritor=new BufferedWriter(archivo);
	    //escribimos en el archivo
	    escritor.write("");
	}catch(FileNotFoundException e){
	    System.out.println("No tienes historial.");
	}catch(IOException e){
	    e.printStackTrace();
	}finally{
	    if(escritor != null){
		try{
		    escritor.close();
		    archivo.close();
		}catch(IOException e){
		    e.printStackTrace();
		}
	    }else{
		System.out.println("No hay ningun archivo abierto");
	    }
	}
    }
    
}//Class
