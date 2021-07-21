//Package

/**
 * Implementación de un árbol rojinegro.
 * @author Emmanuel Cruz Hernández y Axel Daniel Malváez Flores.
 * @version 1.0 Julio 2021.
 * @since Estructuras de Datos 2021-2.
 */
public class RedBlackTree<K extends Comparable, T> implements TDARedBlackTree<K,T>{

    /**
     * Nodo para árboles rojinegros.
     */
    public class RedBlackNode{
	
	/** Color del nodo. */
	public boolean red;

	/** Clave. */
	public K key;

	/** Elemento. */
	public T element;
	
	/** Padre del nodo. */
	public RedBlackNode parent;

	/** Hijo Izquierdo. */
	public RedBlackNode left;

	/** Hijo Derecho. */
	public RedBlackNode right;

	/**
	 * Crea un nuevo nodo para árboles rojinegros.
	 * @param key la llave del nodo.
	 * @param e el elemento a agregar.
	 * @param parent el padre del nodo.
	 */
	public RedBlackNode(K key, T element, RedBlackNode parent){
	    this.key = key;
	    this.element = element;
	    this.parent = parent;
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
	public boolean isRed(RedBlackNode v){
	    if(v == null)
		return false;
	    return v.red;
	}

	/**
	 * Verifica si el color del nodo es negro.
	 * @return true si el nodo es negro, false en otro caso.
	 */
	public boolean isBlack(RedBlackNode v){
	    if(this.isRed(v))
		return false;
	    else
		return true;
	}

	/**
	 * Colorea el nodo de negro.
	 */
	public void paintBlack(RedBlackNode v){
	    if(v.isRed(v))
		this.setColor();
	}

	/**
	 * Colorea el nodo de rojo.
	 */
	public void paintRed(RedBlackNode v){
	    if(v.isBlack(v))
		this.setColor();
	}

    }

    /** Atributos del RedBlackTree */
    // Raiz del arbol.
    public RedBlackNode root;
    
    /**
     * Recupera el objeto con clave k.
     * @param k la clave a buscar.
     * @return el elemento con clave k o null si no existe.
     */
    @Override
    public T retrieve(K k){
	RedBlackNode buscado = retrieveAux(root, k);
	if(buscado == null)
	    return null;
	else
	    return buscado.element;
    }

    /**
     * Metodo que busca y recupera un nodo comparando con la clave k.
     * @param actual - Nodo desde donde empezara a buscar.
     * @param k - clave con la cual va a comparar.
     * @return RedBlackNode - Nodo con la clave k buscada o null si no existe.
     */
    private RedBlackNode retrieveAux(RedBlackNode actual, K k){
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
     * Agrega un nuevo elemento al árbol.
     * @param e el elemento a agregar.
     * @param k la clave del elemento.
     */
    @Override
    public void insert(T e, K k){
	if(root == null){
	    RedBlackNode v = new RedBlackNode(k, e, null);
	    root = v;
	    this.rebalancear(v);
	}else{
	    RedBlackNode v = insertAux(root, k, e);
	    this.rebalancear(v);
	}
    }

    /**
     * Metodo que inserta un nodo partiendo desde un nodo actual.
     * @param actual - Nodo desde donde se comenzara a buscar lugar para el nodo.
     * @param key - clave del nodo a insertar.
     * @param e - elemento del nodo a insertar.
     * @return RedBlackNode - regresa el elemento agregado.
     */
    private RedBlackNode insertAux(RedBlackNode actual, K key, T e){
	// Si la clave es igual
	if(key.compareTo(actual.key) == 0){
	    return null;
	}else if(key.compareTo(actual.key) < 0){ //Si la clave es menor.
	    if(actual.left == null){
		RedBlackNode agregado = new RedBlackNode(key, e, actual);
		actual.left = agregado;
		return agregado;
	    }else{
		return insertAux(actual.left, key, e);
	    }
	}else{//La clave es mayor
	    if(actual.right == null){
		RedBlackNode agregado = new RedBlackNode(key, e, actual);
		actual.right = agregado;
		return agregado;
	    }else{
		return insertAux(actual.right, key, e);
	    }
	}
    }

    /**
     * Metodo para rebalancear sobre un nodo un arbol rojinegro.
     * @param actual - RedBlackNode desde el cual se va a rebalancear.
     */
    private void rebalancear(RedBlackNode actual){
	
	//Caso 1: Si la raiz es roja, cambia color a negro.
	if(actual.parent == null){
	    System.out.println("Entra al caso 1");
	    actual.paintBlack(actual);
	    return;
	}
	
	//Caso 2: Si el padre es negro termina.
	RedBlackNode p = actual.parent;  //Padre del actual
	if(actual.isBlack(p)){
	    System.out.println("Entra al caso 2");
	    return;
	}
	
	RedBlackNode a = p.parent;    //Padre de p, i.e. abuelo del actual.
	RedBlackNode t = (a.left == p) ? a.right : a.left;
	
	//Caso 3: Si el padre es rojo y el tio es rojo
	if(actual.isRed(p) && actual.isRed(t)){
	    System.out.println("Entra al caso 3");
	    p.setColor();
	    t.setColor();
	    a.setColor();
	    this.rebalancear(a);
	    return;
	}

	//Caso 4. Si el padre y el actual estan cruzados
	if((p == a.right && actual == p.left) || (p == a.left && actual == p.right)){
	    System.out.println("Entra al caso 4");
	    if(p == a.right){
		this.rotarDerecha(p);
	    }else{
		this.rotarIzquierda(p);
	    }
	    
	    this.rebalancear(p);
	} else { //Caso 5. Si el padre y el actual no estan cruzados.
	    System.out.println("Entra al caso 5");
	    p.setColor();
	    a.setColor();
	    
	    if(actual == p.right){
		this.rotarIzquierda(a);
	    }else{
		this.rotarDerecha(a);
	    }
	    return;
	}

    }

    /**
     * Rotar un arbol hacia la derecha.
     * @param actual - nodo desde el cual se hará la rotacion.
     */
    public void rotarDerecha(RedBlackNode actual){
	//Caso 1: en que actual no tiene hijo izquierdo ni derecho.
	if(actual.left == null && actual.right == null)
	    return;
	//Caso 2: en que actual no tiene hijo izquierdo con el cual rotar.
	if(actual.left == null)
	    return;
	//Caso 3: en que actual tiene al menos el hijo izquierdo.
	if(actual.left != null){
	    RedBlackNode hi = actual.left;
	    RedBlackNode nietod = hi.right;
	    RedBlackNode padre = actual.parent;

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
	    hi.right = actual;
	    return;
	}
    }

    /**
     * Rotar un arbol hacia la izquierda.
     * @param actual - nodo desde el cual se hará la rotacion.
     */
    public void rotarIzquierda(RedBlackNode actual){
	//Caso 1: en que actual no tiene hijo izquierdo ni derecho.
	if(actual.left == null && actual.right == null)
	    return;
	//Caso 2: en que actual no tiene hijo derecho con el cual rotar.
	if(actual.right == null)
	    return;
	//Caso 3: en que actual tiene al menos el hijo derecho.
	if(actual.right != null){
	    RedBlackNode hd = actual.right;
	    RedBlackNode nietoi = hd.left;
	    RedBlackNode padre = actual.parent;

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
	    return;
	}
    }

    /**
     * Elimina un elemento del árbol.
     * @param k la clave del elemento a eliminar.
     * @return el elemento eliminado o null si no existe.
     */
    @Override
    public T delete(K k){
	return null;
    }


    /**
     * Encuentra la clave k con valor o peso mínimo del árbol.
     * @return el elemento con llave de peso mínimo.
     */
    @Override
    public T findMin(){
	RedBlackNode min = findMax(root);
	if(min == null)
	    return null;
	return min.element;
    }

    /**
     * Encuentra el nodo con la clave minima a partir de un nodo actual.
     * @param actual - el nodo actual.
     * @return RedBlackNode - el nodo con clave minima a partir del actual.
     */
    private RedBlackNode findMin(RedBlackNode actual){
	if(actual == null)
	    return null;

	RedBlackNode min = actual;
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
	RedBlackNode max = findMax(root);
	if(max == null)
	    return null;
	return max.element;
    }

    /**
     * Encuentra el nodo con la clave maxima a partir de un nodo actual.
     * @param actual - el nodo actual.
     * @return RedBlackNode - el nodo con clave mayor a partir del actual.
     */
    private RedBlackNode findMax(RedBlackNode actual){
	if(actual == null)
	    return null;
	
	RedBlackNode max = actual;
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
    private void preordenAux(RedBlackNode actual){
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
    private void inordenAux(RedBlackNode actual){
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
    private void postordenAux(RedBlackNode actual){
	if(actual == null)
	    return;
	postordenAux(actual.left);
	postordenAux(actual.right);
	System.out.println(actual.element);
    }
    
    public static void main(String[] args) {
	RedBlackTree<Double, String> arbol1 = new RedBlackTree<>();
	arbol1.insert("do", 2.0);
	/*System.out.println(arbol1.root.element);
	System.out.println(arbol1.root.isRed(arbol1.root));
	System.out.println();*/
	arbol1.insert("you", 5.0);
	/*System.out.println(arbol1.root.element);
	System.out.println(arbol1.root.right.element);
	System.out.println(arbol1.root.isRed(arbol1.root));
	System.out.println(arbol1.root.right.isRed(arbol1.root.right));
	System.out.println();*/
	arbol1.insert("quarrel", 3.0);
	/*System.out.println(arbol1.root.element);
	System.out.println(arbol1.root.left.element);
	System.out.println(arbol1.root.right.element);
	System.out.println(arbol1.root.isRed(arbol1.root));
	System.out.println(arbol1.root.left.isRed(arbol1.root.left));
	System.out.println(arbol1.root.right.isRed(arbol1.root.right));
	System.out.println();
	*/
	arbol1.insert("sir", 4.0);
	
	arbol1.insert("am", 1.0);

	System.out.println("Arbol1 preorden:");
	arbol1.preorden();

	System.out.println();

	System.out.println("Arbol1 inorden:");
	arbol1.inorden();

	System.out.println();

	System.out.println("Arbol1 postorden:");
	arbol1.postorden();
	
    }
}
