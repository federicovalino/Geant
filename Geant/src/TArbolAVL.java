public class TArbolAVL<T> implements IArbolAVL<T> {

    TElementoAVL<T> raizAvl;

    public TArbolAVL() {
        this.raizAvl = null;
    }

    @Override
    public TElementoAVL<T> insertar(TElementoAVL<T> unElemento) {
        if (esVacio()) {
            return this.raizAvl = unElemento;
        }
        return this.raizAvl = raizAvl.insertar(unElemento);
    }

    @Override
    public TElementoAVL<T> buscar(Comparable unaEtiqueta) {
        if (esVacio()) {
            return null;
        } else {
            return raizAvl.buscar(unaEtiqueta);
        }
    }

    public void eliminar(Comparable unaEtiqueta) {
        if (!esVacio()) {
            this.raizAvl = this.raizAvl.eliminar(unaEtiqueta);
        }
    }

    public boolean esVacio(){
        if (this.raizAvl == null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Lista<T> inOrden() {
        Lista<T> listaInorden = new Lista<>();
        if (!esVacio()) {
            raizAvl.inOrden(listaInorden);
        }
        return listaInorden;
    }

    @Override
    public Lista<T> preOrden() {
        Lista<T> listaPreOrden = new Lista<T>();
        if (!esVacio()) {
            raizAvl.preOrden(listaPreOrden);
        }
        return listaPreOrden;
    }

    @Override
    public Lista<T> postOrden() {
        Lista<T> listaPostOrden = new Lista<>();
        if (!esVacio()) {
            raizAvl.postOrden(listaPostOrden);
        }
        return listaPostOrden;
    }

    public TElementoAVL<T> encontrarMinimo() {
        if (esVacio()) {
            return null;
        }
        return this.raizAvl.encontrarMinimo();
    }

    public TElementoAVL<T> encontrarMaximo() {
        if (esVacio()) {
             return null;
         }
         return this.raizAvl.encontrarMaximo();
    }

    public boolean cumpleAVL() {
        boolean[] cumple = {true};
        if (this.raizAvl != null) {
            raizAvl.cumpleAVL(cumple);
        }
        return cumple[0];
    }

}