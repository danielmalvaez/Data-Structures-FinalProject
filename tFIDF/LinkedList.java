//Package
//package fciencias.edatos.motorBusqueda;

//Imports
import java.util.Iterator;

/**
 * Implementación de una LinkedList basada en arreglos y usando
 * el TDAList de Emmanuel Cruz Hernández.
 * @author Axel Daniel Malváez Flores.
 * @vesion 1.0 Julio 2021.
 */
public class LinkedList<T> implements TDAList<T>{

    /**
     * Nodo de una LinkedList.
     */
    private class Nodo{

        /** Nodo siguiente. */
        public Nodo siguiente;

        /** Elemento a almacenar. */
        public T elemento;

        /**
         * Crea un nuevo nodo.
         * @param elemento el elemento a almacenar en el nodo.
         */
        public Nodo(T elemento){
            this.elemento = elemento;
        }
    }

    /**
     * Iterador de una LinkedList.
     */
    private class IteradorLista implements Iterator<T>{
	
	/** Punto de inicio del recorrido */
	private Nodo nodoIterador;

	/**
         * Crea un nuevo iterador.
         * @param cabeza de la lista para obtener todos los
         * elementos.
         */
        public IteradorLista(Nodo cabeza){
            nodoIterador = new Nodo(null);
            nodoIterador.siguiente = cabeza;
        }

        @Override
        public boolean hasNext(){
            return nodoIterador.siguiente != null;
        }

        @Override
        public T next(){
            nodoIterador = nodoIterador.siguiente;
            return nodoIterador.elemento;
        }
    }

    /**Nodo cabeza*/
    private Nodo cabeza;

    /**Nodo cabeza*/
    private Nodo ultimo;

    /** Cantidad de elementos agregados*/ 
    private int length;

    /**
     * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
     * @param i la posición donde agregar el elemento.
     * @param e el elemento a insertar.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
	if(i < 0 || i > length)
	    throw new IndexOutOfBoundsException("Index out of Bounds");
	
	//Nodo a agregar
	Nodo nuevo = new Nodo(e);

	//Caso en lista vacía
	if(cabeza == null){
	    cabeza = nuevo;
	    ultimo = nuevo;
	    length++;
	    return;
	}

	//Caso para primero, ultimo o distinto del primero.
	if(i == 0){
	    nuevo.siguiente = cabeza;
	    cabeza = nuevo;
	}else if(i == length){
	    ultimo.siguiente = nuevo;
	    ultimo = nuevo;
	}else{
	    Nodo iterador = cabeza;
	    for(int k = 0; k < i-1; k++){
		iterador = iterador.siguiente;
	    }
	    nuevo.siguiente = iterador.siguiente;
	    iterador.siguiente = nuevo;
	}
	length++;
    }

    /**
     * Limpia la lista. Elimina todos los elementos.
     */
    @Override
    public void clear(){
	cabeza = null;
	ultimo = null;
	length = 0;
    }

    /**
     * Verifica si un elemento está contenido en la lista.
     * @param e el elemento a verificar si está contenido.
     * @return true si el elemento está contenid, false en otro caso.
     */
    @Override
    public boolean contains(T e){
	//Si la lista es vacia.
	if(isEmpty())
	    return false;

	//Si solo contiene un elemento
	if(size() == 1)
	    return e.equals(cabeza.elemento);
	
	
	//Si contiene más de 1 elemento
	Nodo iterador = cabeza;
	for(int i = 0; i < length; i++){
	    if(e.equals(iterador.elemento))
		return true;
	    iterador = iterador.siguiente;
	}
	return false;
    }

    /**
     * Obtiene el elemento en la posición <i>i</i>.
     * @param i el índice a obtener elemento.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T get(int i) throws IndexOutOfBoundsException{
	if(i < 0 || i >= length)
	    throw new IndexOutOfBoundsException("Index out of Bounds");
	if(i == (size() - 1)){
	    return ultimo.elemento;
	}
	Nodo iterador = cabeza;
	for(int j = 0; j < i; j++){
	    iterador = iterador.siguiente;
	}
	return iterador.elemento;
    }

    /**
     * Verifica si la lista está vacía.
     * @return true si la lista no contiene elementos, false en otro caso.
     */
    @Override
    public boolean isEmpty(){
	return length == 0;
    }

    /**
     * Elimina el elemento en la posición <i>i</i>.
     * @param i el índice del elemento a eliminar.
     * @return el elemento eliminado.
     * @throws IndexOutOfBoundException si el índice está fuera de rango.
     */
    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
	if(i < 0 || i >= length)
	    throw new IndexOutOfBoundsException("Index out of Bounds");

	T borrado = null;
	
	//Si solo hay un elemento en la lista.
	if(size() == 1){
	    borrado = cabeza.elemento;
	    cabeza = null;
	    ultimo = null;
	    length--;
	    return borrado;
	}
	
	//Primer Elemento, Ultimo elemento u otros.
	if(i == 0){
	    borrado = cabeza.elemento;
	    cabeza = cabeza.siguiente;
	    length--;
	}else if(i == length-1){
	    Nodo iterador = cabeza;
	    for(int j = 0; j < length-2; j++){
		iterador = iterador.siguiente;
	    }
	    borrado = ultimo.elemento;
	    ultimo = iterador;
	    ultimo.siguiente = null;
	    length--;
	}else{
	    Nodo iterador1 = cabeza;
	    Nodo iterador2 = cabeza;
	    for(int j = 0; j < i; j++){
		if(j != 0)
		    iterador2 = iterador2.siguiente;
		iterador1 = iterador1.siguiente;
	    }
	    borrado = iterador1.elemento;
	    iterador2.siguiente = iterador1.siguiente;
	    iterador1.siguiente = null;
	    length--;
	}
	return borrado;
    }

    /**
     * Regresa la cantidad de elementos contenidos en la lista.
     * @return la cantidad de elementos contenidos.
     */
    @Override
    public int size(){
	return length;
    }

    /**
     * Imprime los elementos de la lista.
     */
    public void show(){
	Nodo iterador = cabeza;
	while(iterador != null){
	    System.out.println(iterador.elemento);
	    iterador = iterador.siguiente;
	}
    }
    
    /**
     * Creador de un iterador para la lista.
     * @return Iterador de la lista comenzando en la cabeza.
     */    
    public IteradorLista iterador(){
	return new IteradorLista(this.cabeza);
    }
}
