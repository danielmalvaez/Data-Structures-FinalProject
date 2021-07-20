/**
* Implementación de un árbol rojinegro.
* @author Emmanuel Cruz Hernández y Axel Daniel Malváez Flores.
* @version 1.0 Julio 2021.
* @since Estructuras de Datos 2021-2.
*/
public class RedBlackTree<K extends Comparable, T> extends BinarySearchTree<K, T>{

	/**
	 * Nodo para árboles rojinegros.
	 */
	private class RedBlackNode extends BinarySearchTree.BinaryNode{

		/** Color del nodo. */
		public boolean red;

		/**
		 * Crea un nuevo nodo para árboles rojinegros.
		 * @param key la llave del nodo.
		 * @param e el elemento a agregar.
		 * @param parent el padre del nodo.
		 */
		public RedBlackNode(K key, T e, RedBlackNode parent){
			super(key, e, parent);
			red = true;
		}

		/**
		 * Modifica el color del nodo.
		 */
		public void setColor(){
			red = !red;
		}

		/**
		 * Verifica si el color del nodo es rojo.
		 * @return true si el nodo es rojo, false en otro caso.
		 */
		public boolean isRed(){
			return red;
		}

	}

	/**
	 * Agrega un nuevo elemento al árbol.
	 * @param e el elemento a agregar.
	 * @param k la clave del elemento.
	 */
	public void insert(T e, K k){}

	/**
	 * Elimina un elemento del árbol.
	 * @param k la clave del elemento a eliminar.
	 * @return el elemento eliminado o null si no existe.
	 */
	public T delete(K k){
		return null;
	}

	public static void main(String[] args) {
		
	}
}
