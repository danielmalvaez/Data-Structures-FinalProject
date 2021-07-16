//Package
//package ProyectoFinal;

//Imports
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

/**
 * Clase con metodos auxiliares que nos ayudaran en el proyecto y que
 * no tienen una clase definida.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0
 */
public class Auxiliar{

    /**
     * Metodo auxiliar para la Progress Bar.
     * @param pathName una ruta dada como input.
     * @return Objeto de tipo File
     */
    public File animationProgressBar(String pathName){
	File dir = new File("example");
	try{
	    String anim= "|/-\\";
	    for (int x =0 ; x <= 100 ; x++){
		String data = "\r" + anim.charAt(x % anim.length()) + " " + x + "%";
		System.out.write(data.getBytes());
		/** Declaracion del directorio a buscar pasado como input*/
		if(x == 98)
		    dir = new File(pathName);
		Thread.sleep(40);
	    }
	}catch(Exception e){
	}
	return dir;
    }
}
