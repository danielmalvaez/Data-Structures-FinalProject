//Package
package fciencias.edatos.motorBusqueda;

/**
 * Interfaz que define las operaciones sobre un arbol rojinegro.
 * @author Axel Daniel Malváez Flores
 * @version 1.0 Julio 2021.
 */
public interface TDARedBlackTree<K extends Comparable, T>{
    /**
     * Recupera el objeto con clave k.
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    public T retrieve(K k);

    /**
     * Inserta un nuevo elemento al árbol.
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    public void insert(T e, K k);

    /**
     * Elimina el nodo con clave k del árbol.
     * @param k la clave perteneciente al nodo a eliminar.
     * @return el elemento almacenado en el nodo a eliminar.
     * null si el nodo con clave k no existe.
     */
    public T delete(K k);

    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @return el elemento con llave de peso mínimo.
     */
    public T findMin();

    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     * @return el elemento con llave de peso máximo.
     */
    public T findMax();

    /**
     * Implementacion de un recorrido preorden sobre un BST y lo imprime.
     */
    public void preorden();

    /**
     * Implementacion de un recorrido inorden sobre un BST y lo imprime.
     */
    public void inorden();

    /**
     * Implementacion de un recorrido postorden sobre un BST y lo imprime.
     */
    public void postorden();
}
