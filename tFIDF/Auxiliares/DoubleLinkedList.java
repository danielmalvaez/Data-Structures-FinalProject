//package FinalProject.TF-IDF;

//Imports
import java.util.Iterator;

/**
 * Implementación de una lista soblemente ligada.
 *  Implementa las operaciones del TDAList.
 * @author Emmanuel Cruz Hernández.
 * @version 1.0 Junio 2021.
 * @since Estructuras de datos 2021-2.
 */
public class DoubleLinkedList<T> implements TDAList<T>{

    /**
     * Nodo de una DoubleList.
     */
    private class Nodo{

        /** Nodo anterior. */
        public Nodo anterior;

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
     * Iterador de la lista doblemente ligada.
     */
    private class IteradorLista implements Iterator<T>{

        /** Punto de inicio del recorrido. */
        private Nodo iteradorLista;

        /**
         * Crea un nuevo iterador.
         * @param cabeza de la lista para obtener todos los
         * elementos.
         */
        public IteradorLista(Nodo cabeza){
            iteradorLista = new Nodo(null);
            iteradorLista.siguiente = cabeza;
        }

        @Override
        public boolean hasNext(){
            return iteradorLista.siguiente != null;
        }

        @Override
        public T next(){
            iteradorLista = iteradorLista.siguiente;
            return iteradorLista.elemento;
        }

    }


    /** Nodo cabeza. */
    private Nodo cabeza;

    /** Nodo cola. */
    private Nodo cola;

    /** Cantidad de elementos agregados. */
    private int longitud;
	
    @Override
    public void add(int i, T e) throws IndexOutOfBoundsException{
    	if(i < 0 || i > longitud)
    		throw new IndexOutOfBoundsException("Index out of bound");

    	Nodo nuevo = new Nodo(e);

    	// Caso para lista vacia
    	if(cabeza == null){
    		cabeza = nuevo;
    		cola = nuevo;
    		longitud++;
    		return;
    	}

    	// Caso para primero y ultimo
    	if(i == 0){
    		nuevo.siguiente = cabeza;
    		cabeza.anterior = nuevo;
    		cabeza = nuevo;
    		longitud++;
    		return;
    	} else if(i == longitud){
    		nuevo.anterior = cola;
    		cola.siguiente = nuevo;
    		cola = nuevo;
    		longitud++;
    		return;
    	}

    	int pos = longitud/2;

    	boolean derecha = i<=pos;
    	
        // Derecha
    	if(derecha){
    		Nodo iterador = cabeza;

    		for(int j = 0; j < i-1; j++)
    			iterador = iterador.siguiente;

    		nuevo.siguiente = iterador.siguiente;
    		nuevo.anterior = iterador;
    		iterador.siguiente.anterior = nuevo;
    		iterador.siguiente = nuevo;
    	} else{ // Izquierda
    		Nodo iterador = cola;
    		for(int j = longitud-1 ; j > i ; j--)
                iterador = iterador.anterior;

            nuevo.siguiente = iterador;
            nuevo.anterior = iterador.anterior;
            iterador.anterior.siguiente = nuevo;
            iterador.anterior = nuevo;
    	}
        longitud++;
    } // Si tenemos n elementos, en el peor caso recorremos n/2.

    @Override
    public void clear(){
        cabeza = null;
        cola = null;
        longitud = 0;
    }

    @Override
    public boolean contains(T e){
        // Cuando es vacia
        if(isEmpty())
            return false;

        // Solo 1 elementos
        if(size() == 1)
            return e.equals(cabeza.elemento);

        Nodo iteradorCabeza = cabeza;
        Nodo iteradorCola = cola;
        for(int i = 0; i <= longitud/2 ; i++){
            if(e.equals(iteradorCabeza.elemento) || e.equals(iteradorCola.elemento))
                return true;
            iteradorCabeza = iteradorCabeza.siguiente;
            iteradorCola = iteradorCola.anterior;
        }

        return false;
    } // Si tenemos n elementos, se recorre en el peor caso (n/2)+1

    @Override
    public T get(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= longitud)
            throw new IndexOutOfBoundsException("Index out of bound");

        boolean derecha = i < longitud/2;
        Nodo iterador = null;
        if(derecha){
            iterador = cabeza;
            for(int j = 0; j < i; j++)
                iterador = iterador.siguiente;
        } else{
            iterador = cola;
            for(int j = longitud-1; j > i ; j--)
                iterador = iterador.anterior;
        }

        return iterador.elemento;
    }

    @Override
    public boolean isEmpty(){
        //return cabeza == null;
        return longitud == 0;
    }

    @Override
    public T remove(int i) throws IndexOutOfBoundsException{
        if(i < 0 || i >= longitud)
            throw new IndexOutOfBoundsException("Index out of bound");

        T eliminado = null;

        // Solo un elemento en la lista.
        if(size() == 1){
            eliminado = cabeza.elemento;
            cabeza = null;
            cola = null;
            longitud--;
            return eliminado;
        }

        // Eliminar el primer elemento.
        if(i == 0){
            eliminado = cabeza.elemento;
            cabeza = cabeza.siguiente;
            cabeza.anterior = null;
            longitud--;
            return eliminado;
        } else if(i == longitud-1){ // Eliminar el ultimo
            eliminado = cola.elemento;
            cola = cola.anterior;
            cola.siguiente = null;
            longitud--;
            return eliminado;
        }

        boolean derecha = i<longitud/2;
        Nodo iterador = null;
        if(derecha){
            iterador = cabeza;
            for(int j = 0; j < i ; j++)
                iterador = iterador.siguiente;
        } else{
            iterador = cola;
            for(int j = longitud-1 ; j > i; j--)
                iterador = iterador.anterior;
        }

        eliminado = iterador.elemento;
        iterador.anterior.siguiente = iterador.siguiente;
        iterador.siguiente.anterior = iterador.anterior;
        longitud--;

        return eliminado;
    }

    @Override
    public int size(){
        return longitud;
    }

    public void show(){
    	Nodo iterador = cabeza;
    	while(iterador != null){
    		System.out.println(iterador.elemento);
    		iterador = iterador.siguiente;
    	}
    }

    public Iterator iterador(){
        return new IteradorLista(this.cabeza);
    }
}
