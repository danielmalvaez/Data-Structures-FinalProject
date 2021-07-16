//package FinalProject.TF-IDF;

/**
 * Implementación de una Pareja basada en la clase Pair de JAVA.
 * @author Axel Daniel Malváez Flores.
 * @vesion 1.0 Julio 2021.
 */
public class Pair<K, V>{
    private V value;
    private K key;

    public Pair(V value, K key){
	this.value = value;
	this.key = key;
    }
    
    public K getValue(){
	return value;
    }
    
    public K getKey(){
	return key;
    }

    public String toString(){
	return "(" + this.getValue() + ", " + this.getKey() + ")"
    }
}
