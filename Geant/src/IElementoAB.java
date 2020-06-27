
import java.util.LinkedList;

public interface IElementoAB<T> {

    /**
     * Obtiene el valor de la etiqueta del nodo.
     *
     * @return Etiqueta del nodo.
     */
    public Comparable getEtiqueta();

    /**
     * Obtiene el hijo izquierdo del nodo.
     *
     * @return Hijo Izquierdo del nodo.
     */
    public TElementoAB<T> getHijoIzq();

    /**
     * Obtiene el hijo derecho del nodo.
     *
     * @return Hijo derecho del nodo.
     */
    public TElementoAB<T> getHijoDer();

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @return Elemento a ser asignado como hijo izquierdo.
     */
    public void setHijoIzq(TElementoAB<T> elemento);

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @return Elemento a ser asignado como hijo derecho.
     */
    public void setHijoDer(TElementoAB<T> elemento);

    /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     *
     * @param unaEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public TElementoAB<T> buscar(Comparable unaEtiqueta);



    /**
     * Inserta un elemento dentro del arbol.
     *
     * @param elemento Elemento a insertar.
     * @return Exito de la operaci�n.
     */
    public boolean insertar(TElementoAB<T> elemento);


    /**
     * Imprime en inorden el arbol separado por guiones.
     *
     * @return String conteniendo el InOrden
     */
    public String inOrden();

    /**
     * Imprime en preorden el arbol separado por guiones.
     *
     * @return String conteniendo el preOrden
     */
    public String preOrden();

    /**
     * Imprime en postorden el arbol separado por guiones.
     *
     * @return String conteniendo el postOrden
     */
    public String postOrden();

     /**
     * pone las etiquetas del recorrido en inorden en una linkedlist.
     *
     * @param unaLista
     */
    public void inorden(Lista<T> unaLista);

    /**
     * pone las etiquetas del recorrido en preorden en una linkedlist.
     *
     * @param unaLista
     */
    public void preorden(Lista<T> unaLista);

     /**
     * pone las etiquetas del recorrido en postorden en una linkedlist.
     *
     * @param unaLista
     */
    public void postorden(Lista<T> unaLista);

    /**
     * Retorna los datos contenidos en el elemento.
     *
     * @return
     */
    public T getDatos();

/**
	 * Retorna la altura del arbol cuya raiz es la del nodo actual.
	 * @return Altura del subarbol.
	 */
	public int obtenerAltura();

	/**
	 * Retorna el tamaño del arbol cuya raiz es la del nodo actual.
	 * @return tamaño del subarbol.
	 */
	public int obtenerTamanio();

	/**
	 * Retorna el nivel del elemento cuya etiqueta es la pasada por par�metro.
	 * @param unaEtiqueta
	 * @return Nivel
	 */
	public int obtenerNivel(Comparable unaEtiqueta);

	/**
	 * Retorna la cantidad de hojas del arbol cuya raiz es la del nodo actual.
	 * @return Cantidad de hojas del subarbol.
	 */
	public int obtenerCantidadHojas();

}
