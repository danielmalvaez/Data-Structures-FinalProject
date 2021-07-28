//Package

//Imports
import java.text.Normalizer;

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


    }
}
