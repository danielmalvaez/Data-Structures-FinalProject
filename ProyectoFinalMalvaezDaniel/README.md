# Proyecto Final Data Structures 💻
## Autor: Axel Daniel Malváez Flores 🦈

#### Clases del proyecto:

* Pair.java:
	* Implementación de parejas (Pair), clase genérica. Permite juntar dos tipos de datos con un respectivo key y un value.
	* Métodos: getKey(), getValue(), setValue(), setKey(), toString() y el constructor.
* Reader.java:
	* Implementación de los métodos que nos permiten leer, escribir y transformar documentos con la extensión .txt
	* Métodos: readDocument() (con sobreescritura), writeString(), readString(), cleanDocument().
* TFIDFcalculator.java:
	* Implementación de los métodos que realizan los cálculos del TF, IDF, TF-IDF y la similitud entre una consulta y los documentos.
	* Métodos: listToFalse(), listOcurrencias(), calcularIDF() (con sobreescritura), calcularTF() (con sobreescritura), calcularTFIDF() y 				similitud(). 
* Auxiliar.java:
	* Implementación de los métodos auxiliares que nos ayudarán a reducir código y hacer los calculos para los documentos y la búsqueda.
	* Métodos: getListsString(), getListsIDF(), getListsTF(), getListsTFIDF(), swap(), insertionParejas().
	* Explicación: Los dos últimos métodos son para poder ordenar un arreglo de tipo pareja únicamente tomando como referencia el Key de las 					  mismas.
* PrincipalInterface.java:
	* Implementación de la GUI para nuestro proyecto, esta es la GUI principal donde se obtendrá la ruta en donde buscaremos los archivos que 	  nos interesan, este es el comienzo de nuestro Main Method.
	* Métodos: Constructor, getIconImage(), initComponents(), botonSalirActionPerformed(), verificarRutaActionPerformed(). 
* Menu.java:
	* Implementación de la GUI para nuestro proyecto, esta es una GUI secundaria donde se mostrará el menú al usuario y sus posibles 	  alternativas para el programa. Continuación del Main Method.
	* Métodos: Constructor, getIconImage(), initComponents(), setters() (setters de cada atributo), BuscarActionPerformed(), SalirActionPerformed(), cambiarRutaActionPerformed(), HistorialButtonActionPerformed().
* Buscar.java:
	* Implementación de la GUI para nuestro proyecto, esta es una GUI secundaria donde se mostrará el panel para hacer una búsqueda y compararla con cada *tracked file* que tengamos en nuestra lista de documentos.
	* Métodos: Constructor, getIconImage(), initComponents(), setters() (setters de cada atributo), BuscarActionPerformed(), salirProgramaActionPerformed(), backButtonActionPerformed(), buscarButtonActionPerformed(). 
* Historial.java:
	* Implementaciónde la GUI para nuestro proyecto, esta es una GUI secundaria donde se mostrará el historial que se encuentra guardado en un 	  archivo .txt y donde podremos acceder y conocer todas las búsquedas que hemos realizado y de querer borrarlas.
	* Métodos: Constructor, getIconImage(), initComponents(), setters() (setters de cada atributo), backButtonActionPerformed(), 				 cleanHistorialActionPerformed(), salirProgramaActionPerformed(), addHistorial().
* Resultados.java:
	* Implementaciónde la GUI para nuestro proyecto, esta es una GUI secundaria donde se mostrarán los mejores 10 (a lo más) resultados que 	  nuestro programa haya arrojado y coincidido con nuestra búsqueda. 
	* Métodos: Constructor, getIconImage(), initComponents(), setters() (setters de cada atributo), backButtonActionPerformed(), 				 cleanHistorialActionPerformed(), salirButtonActionPerformed().

##### Explicación GUI:
Cada GUI tiene atributos establecidos para que al momento de mandar llamar a una o regresar a la GUI anterior estos valores se transfieran entre sí para no perderlos, la única interfaz que no tendrá estos valores es la PrincipalInterface pues al ser la que los recolecta desde una ruta dada como input, no se necesitan.


##### Estructuras Utilizadas: 
* ArrayLists\<K>
* Pair\<V, K>
* Hashtables\<K, V>

Su acceso a sus elementos se hace en O(1) en la mayoría de los casos.

#### Execution (Usuario):
##### Proceso de entradas
* El programa pide una ruta de entrada en donde buscará los archivos con extensión .txt y los procesará. Si la ruta no es válida se lanza un warning message o de ser válida y no contener archivos .txt igual se mostrará un mensaje, de lo contrario lo mandará al GUI Menu.
* El programa mostrará un menú:
	* Búsqueda:
		* El programa abrirá la GUI Buscar y pedirá un input tal que esta entrada sea una búsqueda de a lo más 200 caractéres, el programa no 		  acepta una búsqueda únicamente realizada con caractéres especiales o mayor a 200 caractéres y de ser ingresada se lanzará un mensaje 		  de aviso.
		* Resultados:
			* El programa mostrará los resultados más relevantes con su búsqueda.
	* Historial de Búsqueda:
		* El programa mostrará un TextArea donde se mostrará su historial, si aún no cuenta con Historial, el programa le mostrará un mensaje 		  avisándole sobre esto, de lo contrario, se muestran las búsquedas realizadas.
	* Ingresar otra Ruta:
		* El programa regresa a la GUI PrincipalInterface donde se le pedirá la ruta de nuevo.

En cualquier ventana usted podrá regresar a la ventana anterior y salir del programa. 

##### Proceso de Ejecución en la Command Line (Terminal)
* Para abrir la interfaz del programa, vaya a interfaz/ y ejecute el MiniGoogle.jar que hay dentro (La incluí ya compilada pues se necesitaban varias librerías de NetBeans y dado que no sé si tienen el IDE instalado, decidí mandarlo ya compilado para que no hubiera errores al compilarlo en una máquina externa). Así mismo si desean ver el código de mi proyecto, lo incluí en *interfaz/MotorBusquedaInterfazProject/src/fciencias/edatos/motorBusqueda*  y aquí encontrarán las clases adicionales que no están en el *src/* principal.

* Para ejecutar el programa sin la interfaz gráfica simplemente en la terminal ejecute los siguientes comandos y el programa se ejecutará y realizará directamente en la terminal.

**Comandos a Utilizar:**

* ant build
* ant jar
* ant run
* ant clean

#### Nota GUI:
**LAS GUI ESTÁN IMPLEMENTADAS DE MANERA QUE PUEDAN MINIMIZARSE, SIN EMBARGO, _LA INTERFAZ ES DE UN SOLO TAMAÑO_, EN CASO DE EXPANDIRSE, LA GUI IMPLEMENTADA NO LO HARÁ, ES POR ESO QUE SE RECOMIENDA SIEMPRE MANTENERSE LA VENTANA AL CENTRO Y _NO_ EXPANDIR.**

#### DIFICULTADES CON EL PROYECTO: 

La primera versión del proyecto fue con RedBlackTrees y con LinkedLists, sin embargo debido a que el algoritmo tenía una complejidad considerable y a que tardaba mucho en calcular para archivos largos es por eso que decidí cambiar de estructuras y en lugar de LinkedLists decidí utilizar os ArrayLists y en lugar de RedBlackTrees los Hashtables que ya se implementaron en JAVA.

Al final dado que me quedaron dos días de sobra, decidí implementar una GUI para el proyecto, longrando conectarla con el mismo. Lo difícil fue el tener que *documentar* pues para hacer la *GUI* utilicé el NetBeans IDE y fue más sencillo (es por eso que unas partes del proyecto están documentadas en inglés).






