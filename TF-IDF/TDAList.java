package fciencias.edatos.stack;

/**
* TDA para listas.
* @author Emmanuel Cruz Hernández.
* @version 2.0 Junio 2021.
* @since Estructuras de datos 2021-2.
*/
public interface TDAList<T>{

	/**
	 * Inserta un nuevo elemento <i>e</i> en la posición <i>i</i>.
	 * @param i la posición donde agregar el elemento.
	 * @param e el elemento a insertar.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
	public void add(int i, T e) throws IndexOutOfBoundsException;

	/**
	 * Limpia la lista. Elimina todos los elementos.
	 */
	public void clear();

	/**
	 * Verifica si un elemento está contenido en la lista.
	 * @param e el elemento a verificar si está contenido.
	 * @return true si el elemento está contenid, false en otro caso.
	 */
	public boolean contains(T e);

	/**
	 * Obtiene el elemento en la posición <i>i</i>.
	 * @param i el índice a obtener elemento.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
	public T get(int i) throws IndexOutOfBoundsException;

	/**
	 * Verifica si la lista está vacía.
	 * @return true si la lista no contiene elementos, false en otro caso.
	 */
	public boolean isEmpty();

	/**
	 * Elimina el elemento en la posición <i>i</i>.
	 * @param i el índice del elemento a eliminar.
	 * @return el elemento eliminado.
	 * @throws IndexOutOfBoundException si el índice está fuera de rango.
	 */
	public T remove(int i) throws IndexOutOfBoundsException;

	/**
	 * Regresa la cantidad de elementos contenidos en la lista.
	 * @return la cantidad de elementos contenidos.
	 */
	public int size();

}