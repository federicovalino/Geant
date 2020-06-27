
public interface IElementoAVL<T> {

   /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     *
     * @param unaEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */

    public IElementoAVL<T> buscar(Comparable unaEtiqueta);

    /**
     * Inserta un elemento dentro del arbol.
     *
     * @param elemento Elemento a insertar.
     * @return Exito de la operaci�n.
     */
    public IElementoAVL<T> insertar(TElementoAVL<T> elemento);

    public void preOrden(Lista<T> unaLista);

    public void inOrden(Lista<T> unaLista);

    public void postOrden(Lista<T> unaLista);

    /**
     * Elimina un elemento dada una etiqueta.
     *
     * @param unaEtiqueta
     * @return
     */
    public IElementoAVL<T> eliminar(Comparable unaEtiqueta);

    public int cumpleAVL(boolean[] cumple);

}