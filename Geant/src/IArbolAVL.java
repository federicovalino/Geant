public interface IArbolAVL<T> {

    /**
     * Inserta un elemento en el arbol. En caso de ya existir un elemento con la
     * clave indicada en "unElemento", retorna falso.
     *
     * @param unElemento Elemento a insertar
     * @return Exito de la operaci�n
     */
    public IElementoAVL<T> insertar(TElementoAVL<T> unElemento);

    /**
     * Busca un elemento dentro del �rbol.
     *
     *
     * @param unaEtiqueta Etiqueta identificadora del elemento a buscar. .
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public TElementoAVL<T> buscar(Comparable unaEtiqueta);

    public void eliminar(Comparable unaEtiqueta);

    public Lista<T> preOrden();

    public Lista<T> inOrden();

    public Lista<T> postOrden();

    public boolean cumpleAVL();
}