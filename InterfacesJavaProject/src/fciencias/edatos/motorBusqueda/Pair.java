//Package
//package fciencias.edatos.motorBusqueda;
package fciencias.edatos.motorBusqueda;
/**
 * Implementación de una Pareja basada en la clase Pair de JAVA. Estilo diccionario.
 * @author Axel Daniel Malváez Flores.
 * @vesion 1.0 Julio 2021.
 */
public class Pair<V, K>{

    /** Atributo valor*/
    private V value;
    /** Atributo clave*/
    private K key;

    /** Metodo contructor de un Objeto tipo Pair
     * @param Value - valor que guardara el objeto tipo Pair.
     * @param Key - clave del objeto tipo pair.
     */
    public Pair(V value, K key){
	this.value = value;
	this.key = key;
    }

    /**
     * Metodo con el cual poemos obtener el valor del 
     * atributo Value.
     * @return V - valor del atributo Value.
     */
    public V getValue(){
	return value;
    }

    /**
     * Metodo con el cual poemos obtener el valor del 
     * atributo Key.
     * @return K - valor del atributo Key.
     */
    public K getKey(){
	return key;
    }

    public void setKey(K newKey){
	this.key = newKey;
    }

    public void setValue(V newValue){
	this.value = newValue;
    }

    /** Metodo toString que nos ayuda a visualizar un objeto de tipo 
     * Pair.
     * @return String - Visualizacion en un String de los objetos tipo Pair.
     */
    public String toString(){
	return "(" + this.getValue() + ", " + this.getKey() + ")";
    }
    
}
