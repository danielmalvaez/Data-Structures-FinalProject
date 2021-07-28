//Package
//package fciencias.edatos.motorBusqueda;

//Imports
import java.io.*;
/**
 * Clase que nos permite escribir sobre un archivo .txt y leer
 * un archivo txt.
 * @author Axel Daniel Malváez Flores.
 * @version 1.0.
 */
public class Text{
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
	    System.out.println("Archivo no encontrado.");
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
     * Metodo que nos permite leer  un documento con extension txt y pasarlo a un
     * String.
     * @return String - El texto que hay dentro del archivo.
     * @param historial - El nombre del archivo.
     */
    public String readString(String historial){
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
            System.out.println("Archivo no encontrado.");
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
            }else {
                System.out.println("No hay ningun archivo abierto.");
            }
        }
	    return hst;
    }
}
