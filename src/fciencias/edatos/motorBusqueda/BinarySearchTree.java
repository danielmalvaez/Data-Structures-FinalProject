//package.

//imports

/**
 * Implementación de un arbol binario de búsqueda, utilizando como referencia el
 * TDABinarySearchTree implementado por Emmanuel Cruz Hernández.
 * @author Axel Daniel Malvaez Flores.
 * @version 1.0 Julio 2021. 
 */
public class BinarySearchTree<K extends Comparable, T> implements TDABinarySearchTree<K, T>{

    public class BinaryNode{

	/** Clave. */
	public K key;

	/** Elemento. */
	public T element;

	/** Padre del nodo. */
	public BinaryNode parent;

	/** Hijo Izquierdo. */
	public BinaryNode left;

	/** Hijo Derecho. */
	public BinaryNode right;

	/** Crear un nuevo nodo.
	 * @param key - la clave del nodo
	 * @param element - elemento a almacenar
	 * @param parent - padre del nodo
	 */
	public BinaryNode(K key, T element, BinaryNode parent){
	    this.key = key;
	    this.element = element;
	    this.parent = parent;
	}
    }

    /** Atributos del BinarySearchTree */
    // Raiz del arbol.
    public BinaryNode root;
    
    /**
     * Recupera el objeto con clave k.
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public T retrieve(K k){
	BinaryNode buscado = retrieveAux(root, k);
	if(buscado == null)
	    return null;
	else
	    return buscado.element;
    }

    /**
     * Metodo que busca y recupera un nodo comparando con la clave k.
     * @param actual - Nodo desde donde empezara a buscar.
     * @param k - clave con la cual va a comparar.
     * @return BinaryNode - Nodo con la clave k buscada o null si no existe.
     */
    private BinaryNode retrieveAux(BinaryNode actual, K k){
	//Cuando la clave no se encuentra.
	if(actual == null){
	    return null;
	}
	//caso cuando las claves son iguales.
	if (actual.key.compareTo(k) == 0)
	    return actual;
	else if(k.compareTo(actual.key) < 0){ //Fue menor y buscamos en el subarbol izquierdo.
	    return retrieveAux(actual.left, k);
	}else{  //Fue mayor y buscamos en el subarbol derecho.
	    return retrieveAux(actual.right, k);
	}
    }

    /**
     * Inserta un nuevo elemento al árbol.
     * @param e el elemento a ingresar.
     * @param k la clave del elemento a ingresar.
     */
    @Override
    public void insert(T e, K k){
	//Si el arbol es vacio.
	if(root == null){
	    root = new BinaryNode(k, e, null);
	    return;
	}

	insertAux(root, k, e);
    }

    /**
     * Metodo que inserta un nodo partiendo desde un nodo actual.
     * @param actual - Nodo desde donde se comenzara a buscar lugar para el nodo.
     * @param key - clave del nodo a insertar.
     * @param e - elemento del nodo a insertar.
     */
    private void insertAux(BinaryNode actual, K key, T e){
	// Si la clave es menor
	if(key.compareTo(actual.key) == 0){
	    return;
	}else if(key.compareTo(actual.key) < 0){
	    if(actual.left == null){
		BinaryNode agregado = new BinaryNode(key, e, actual);
		actual.left = agregado;
		return;
	    }else{
		insertAux(actual.left, key, e);
	    }
	}else{//La clave es mayor
	    if(actual.right == null){
		BinaryNode agregado = new BinaryNode(key, e, actual);
		actual.right = agregado;
		return;
	    }else{
		insertAux(actual.right, key, e);
	    }
	}
    }

    /**
     * Elimina el nodo con clave k del árbol.
     * @param k la clave perteneciente al nodo a eliminar.
     * @return T - el elemento almacenado en el nodo a eliminar
     *  o null si el nodo con clave k no existe.
     */
    @Override
    public T delete(K k){
	//Nodo a eliminar
	BinaryNode eliminado = retrieveAux(root, k);
	//Referencia al padre del eliminado.
	BinaryNode padre = eliminado.parent;
		
	//Caso 1: Ver si esta en el arbol.
	if(eliminado == null)
	    return null;
	
	T elementoRegresado = eliminado.element;
	
	//Caso 2: Si es una hoja
	if(eliminado.left == null && eliminado .right == null){
	    //Verificar si el eliminado es hijo izquierdo o derecho.
	    if(padre.left == eliminado)
		padre.left = null;
	    else
		padre.right = null;
	    return elementoRegresado;
	} else if(eliminado.left != null && eliminado .right != null){ //Caso 3: Si tiene dos hijos
	    BinaryNode max = findMax(eliminado.left);
	    delete(max.key);
	    eliminado.key = max.key;
	    eliminado.element = max.element;
	    return elementoRegresado;
	}

	boolean izquierdo = padre.left == eliminado;
	
	if(eliminado.right != null){ //Caso 4: Si tiene un solo hijo
	    eliminado = eliminado.right;
	}else{
	    eliminado = eliminado.left;
	}
	
	eliminado.parent = padre;
	
	if(izquierdo)
	    padre.left = eliminado;
	else
	    padre.right = eliminado;

	return elementoRegresado;
    }

    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin(){
	BinaryNode min = findMax(root);
	if(min == null)
	    return null;
	return min.element;
    }

    /**
     * Encuentra el nodo con la clave minima a partir de un nodo actual.
     * @param actual - el nodo actual.
     * @return BinaryNode - el nodo con clave minima a partir del actual.
     */
    private BinaryNode findMin(BinaryNode actual){
	if(actual == null)
	    return null;

	BinaryNode min = actual;
	//Buscamos el nodo que este mas a la izquierda.
	while(min != null)
	    min = min.left;
	return min;
    }

    /**
     * Encuentra la clave k con valor o peso máximo del árbol.
     * @return el elemento con llave de peso máximo.
     */
    @Override
    public T findMax(){
	BinaryNode max = findMax(root);
	if(max == null)
	    return null;
	return max.element;
    }

    /**
     * Encuentra el nodo con la clave maxima a partir de un nodo actual.
     * @param actual - el nodo actual.
     * @return BinaryNode - el nodo con clave mayor a partir del actual.
     */
    private BinaryNode findMax(BinaryNode actual){
	if(actual == null)
	    return null;
	
	BinaryNode max = actual;
	//Buscamos el nodo que este mas a la derecha.
	while(max.right != null)
	    max = max.right;
	return max;
    }

    /**
     * Implementacion de un recorrido preorden sobre un BST y lo imprime.
     */
    @Override
    public void preorden(){
	preordenAux(root);
    }

    /**
     * Implementacion de un recorrido preorden sobre un BST y lo imprime.
     * @param actual - Nodo desde donde se comenzara a recorrer.
     */
    private void preordenAux(BinaryNode actual){
	if(actual == null)
	    return;
	System.out.println(actual.element);
	preordenAux(actual.left);
	preordenAux(actual.right);
    }

    /**
     * Implementacion de un recorrido inorden sobre un BST y lo imprime.
     */
    @Override
    public void inorden(){
	inordenAux(root);
    }

    /**
     * Implementacion de un recorrido inorden sobre un BST y lo imprime.
     * @param actual - Nodo desde donde se comenzara a recorrer.
     */
    private void inordenAux(BinaryNode actual){
	if(actual == null)
	    return;
	inordenAux(actual.left);
	System.out.println(actual.element);
	inordenAux(actual.right);
    }

    /**
     * Implementacion de un recorrido postorden sobre un BST y lo imprime.
     */
    @Override
    public void postorden(){
	postordenAux(root);
    }

    /**
     * Implementacion de un recorrido postorden sobre un BST y lo imprime.
     * @param actual - Nodo desde donde se comenzara a recorrer.
     */
    private void postordenAux(BinaryNode actual){
	if(actual == null)
	    return;
	postordenAux(actual.left);
	postordenAux(actual.right);
	System.out.println(actual.element);
    }

    /**
     * Rotar un arbol hacia la derecha.
     * @param actual - nodo desde el cual se hará la rotacion.
     */
    public void rotarDerecha(BinaryNode actual){
	//Caso 1: en que actual no tiene hijo izquierdo ni derecho.
	if(actual.left == null && actual.right == null)
	    return;
	//Caso 2: en que actual no tiene hijo izquierdo con el cual rotar.
	if(actual.left == null)
	    return;
	//Caso 3: en que actual tiene al menos el hijo izquierdo.
	if(actual.left != null){
	    BinaryNode hi = actual.left;
	    BinaryNode nietod = hi.right;
	    BinaryNode padre = actual.parent;

	    //Verificar si actual es la raiz
	    if(padre == null)
		root = hi;

	    //Si el actual no es es la raiz entonces tiene padre.
	    if(padre != null && actual == padre.left)
		padre.left = hi;
	    else if(padre != null && actual == padre.right)
		padre.right = hi;

	    //Asignamos
	    hi.parent = padre;
	    if(nietod != null){
		nietod.parent = actual;
		actual.left = nietod;
	    }else{
		actual.left = null;
	    }
	    actual.parent = hi;
	    hi.left = actual;
	}
    }

    /**
     * Rotar un arbol hacia la izquierda.
     * @param actual - nodo desde el cual se hará la rotacion.
     */
    public void rotarIzquierda(BinaryNode actual){
	//Caso 1: en que actual no tiene hijo izquierdo ni derecho.
	if(actual.left == null && actual.right == null)
	    return;
	//Caso 2: en que actual no tiene hijo derecho con el cual rotar.
	if(actual.right == null)
	    return;
	//Caso 3: en que actual tiene al menos el hijo derecho.
	if(actual.right != null){
	    BinaryNode hd = actual.right;
	    BinaryNode nietoi = hd.left;
	    BinaryNode padre = actual.parent;

	    //Verificar si el actual es la raíz
	    if(padre == null)
		root = hd;

	    //Si el actual no es es la raiz entonces tiene padre.
	    if(padre != null && actual == padre.left)
		padre.left = hd;
	    else if(padre != null && actual == padre.right)
		padre.right = hd;
	    
	    //Asignamos
	    hd.parent = padre;
	    if(nietoi != null){
		nietoi.parent = actual;
		actual.right = nietoi;
	    }else{
		actual.right = null;
	    }
	    actual.parent = hd;
	    hd.left = actual;
	}
    }
}
