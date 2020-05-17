public class TArbolBB<T> implements IArbolBB<T> {

    private TElementoAB<T> raiz;

    /**
     * Separador utilizado entre elemento y elemento al imprimir la lista
     */
    public static final String SEPARADOR_ELEMENTOS_IMPRESOS = "-";

    public TArbolBB(TElementoAB<T> raiz) {
        raiz = null;
    }

    public TArbolBB() {
        raiz = null;
    }

    /**
     * @param unElemento
     * @return
     */
    public boolean insertar(TElementoAB<T> unElemento) {
        if (esVacio()) {
            raiz = unElemento;
            return true;
        } else {
            return raiz.insertar(unElemento);
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @SuppressWarnings("unchecked")
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raiz.buscar(unaEtiqueta);
        }
    }

      /**
   * Imprime en PreOrden los elementos del árbol, separados por guiones.
   *
   * @return String conteniendo el preorden separado por guiones.
   */
    public String preOrden() {
      if (this.raiz != null) {
        return raiz.preOrden();
      }
      return "";
    }

    /**
     * Imprime en PostOrden los elementos del árbol, separados por guiones.
     *
     * @return String conteniendo el preorden separado por guiones.
     */
    public String postOrden() {
      if (this.raiz != null) {
        return raiz.postOrden();
      }
      return "";
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    public String inOrden() {
        if (this.raiz != null) {
            return raiz.inOrden();
        }
        return "";
    }

    /**
     * @return recorrida en inorden del arbol, null en caso de ser vacío
     */
    public Lista<T> inorden() {
        Lista<T> listaInOrden = new Lista<T>();
        if (!esVacio()) {
            raiz.inOrden(listaInOrden);
        }
        return listaInOrden;
    }

    /**
     * @return recorrida en preOrden del arbol, null en caso de ser vacío
     */
    /**
     * @return
     */
    public boolean esVacio() {
        return (raiz == null);
    }

    /**
     * @return True si habían elementos en el árbol, false en caso contrario
     */
    public boolean vaciar() {
        if (!esVacio()) {
            raiz = null;
            return true;
        }
        return false;
    }

    public int obtenerAltura() {
        if (this.raiz == null) {
            return -1;
        } else {
            return this.raiz.obtenerAltura();
        }
    }

    @Override
    public int obtenerTamanio() {
        int cantidad = (this.raiz).obtenerTamanio();
        return cantidad;
    }

    public void cargarNivelesNodos() {
        this.raiz.cargarNivel(0);
    }

    @Override
    public int obtenerNivel(Comparable unaEtiqueta) {
        if (this.raiz == null) {
            return -1;
        }
        if (this.raiz.getEtiqueta().compareTo(unaEtiqueta) == 0) {
            return 0;
        }
        return this.raiz.obtenerNivel(unaEtiqueta);
    }

    @Override
    public int obtenerCantidadHojas() {
        if (this.raiz == null) {
            return 0;
        } else {
            return this.raiz.obtenerCantidadHojas();
        }
    }

    public void eliminar(Comparable unaEtiqueta){
        if(this.raiz != null){
            this.raiz = this.raiz.eliminar(unaEtiqueta);
        }
    }
}
