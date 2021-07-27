//Package

//Imports
import java.util.NoSuchElementException;

/**
 * Implementacion de una cola basada en LinkedList y con ayuda del TDAQueue
 * implementado por Emmanuel Cruz Hernandez.
 * @author Axel Daniel Malvaez Flores
 * @version 1.0.
 */
public class Queue<T> implements TDAQueue<T>{
    
    // Lista que representa y almacena los elementos de la cola.
    private LinkedList<T> lista = new LinkedList<>();

    /**
     * Metodo que limpia la cola.
     */
    @Override
    public void clear(){
        lista.clear();
    }

    /**
     * Metodo que saca al primer elemento de la cola, es decir, el que lleva
     * mas tiempo almacenado.
     * @return T - elemento que sacamos.
     * @throws NoSuchElementException
     */
    @Override
    public T dequeue() throws NoSuchElementException{
        if(lista.isEmpty())
            throw new NoSuchElementException();
	
        return lista.remove(0);
    }

    /**
     * Metodo que agrega un elemento a la cola, es decir, al final de ella.
     */
    @Override
    public void enqueue(T e){
        lista.add(lista.size(), e);
    }

    /**
     * Metodo que nos regresa el primer elemento de la lista sin sacarlo de
     * la cola.
     * @return T - primer elemento de la cola.
     */
    @Override
    public T first() throws NoSuchElementException{
        if(lista.isEmpty())
            throw new NoSuchElementException();
        return lista.get(0);
    }

    /**
     * Metodo que nos dice si la cola es una cola vacia o no.
     * @return boolean - true si es vacia, de otro modo false.
     */
    @Override
    public boolean isEmpty(){
        return lista.isEmpty();
    }
}
