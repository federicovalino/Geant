public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;
    private int nivel;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
        nivel = 0;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    /**
     * @param unElemento
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean insertar(TElementoAB<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().insertar(unElemento);
            } else {
                hijoIzq = unElemento;
                return true;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiqueta) > 0) {
            if (hijoDer != null) {
                return getHijoDer().insertar(unElemento);
            } else {
                hijoDer = unElemento;
                return true;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.-
            return false;
        }
    }

    /**
     * @param unaEtiqueta
     * @return
     */
    @Override
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {

        if (unaEtiqueta.equals(etiqueta)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiqueta) < 0) {
            if (hijoIzq != null) {
                return getHijoIzq().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDer != null) {
            return getHijoDer().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

        /**
     * Imprime en preorden el arbol separado por guiones.
     *
     * @return String conteniendo el PreOrden
     */
    public String preOrden() {
        String temp = "";
        temp = temp + " " + this.etiqueta;
        if (hijoIzq != null) {
            temp = temp + hijoIzq.preOrden();
        }
        if (hijoDer != null) {
            temp = temp + hijoDer.preOrden();
        }
        return temp;
    }

    /**
     * Imprime en postorden el arbol separado por guiones.
     *
     * @return String conteniendo el PostOrden
     */
    public String postOrden() {
        String temp = "";
        if (hijoIzq != null) {
            temp = hijoIzq.postOrden();
        }
        if (hijoDer != null) {
            temp = temp + hijoDer.postOrden();
        }
        temp = temp + " " + etiqueta;
        return temp;
    }

    /**
     * @return recorrida en inorden del subArbol que cuelga del elemento actual
     */
    @Override
    public String inOrden() {
        String temp = "";
        if (hijoIzq != null) {
            temp = hijoIzq.inOrden() + " ";
        }
        temp += this.etiqueta;
        if (hijoDer != null) {
            temp = temp + " " + hijoDer.inOrden();
        }
        return temp;
    }

    public void inOrden(Lista<T> unaLista) {
        Nodo<T> nodoLista = null;
        if (hijoIzq != null) {
            this.hijoIzq.inOrden(unaLista);
        }
        nodoLista = new Nodo<T>(this.getEtiqueta(),this.getDatos());
        unaLista.insertar(nodoLista);
        if (hijoDer != null) {
            this.hijoDer.inOrden(unaLista);
        }
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
    }

    @Override
    public T getDatos() {
        return datos;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }



    @Override
    public int obtenerAltura() {
        int alturaIzquierdo = 0;
        int alturaDerecho = 0;
        if (this.hijoIzq == null && this.hijoDer == null) {
            return 0;
        }
        if (this.hijoIzq != null) {
            alturaIzquierdo = this.hijoIzq.obtenerAltura();
        }
        if (this.hijoDer != null) {
            alturaDerecho = this.hijoDer.obtenerAltura();
        }
        return 1 + (alturaIzquierdo > alturaDerecho ? alturaIzquierdo : alturaDerecho);
    }


    public int obtenerTamanio() {
        int contador1 = 0;
        int contador2 = 0;
        int contadorFinal = 0;
        if (this == null) {
            return 0;
        }
        if (this.hijoIzq != null) {
            contador1 = this.hijoIzq.obtenerTamanio();
        }
        if (this.getHijoDer() != null) {
            contador2 = this.hijoDer.obtenerTamanio();
        }
        return contadorFinal = contador1 + contador2 + 1;
    }

    public int obtenerNivel(Comparable unaEtiqueta) {
        if (this.etiqueta.compareTo(unaEtiqueta) == 0) {
            return 0;
        }
        if (this.hijoIzq!=null) {
            if (this.etiqueta.compareTo(unaEtiqueta) > 0) {
                return 1 + this.hijoIzq.obtenerNivel(unaEtiqueta);
            }
        }
        if (this.hijoDer!=null) {
            if (this.etiqueta.compareTo(unaEtiqueta) < 0) {
                return 1 + this.hijoDer.obtenerNivel(unaEtiqueta);
            }
        }
        return -1;
    }

    public void cargarNivel(int nivel) {
        this.nivel = nivel;
        if (this.hijoDer != null && this.hijoIzq != null) {
            this.hijoIzq.cargarNivel(nivel+1);
            this.hijoDer.cargarNivel(nivel+1);
        } else if (this.hijoIzq != null && this.hijoDer == null) {
            this.hijoIzq.cargarNivel(nivel+1);
        } else if (this.hijoIzq == null && this.hijoDer != null){
            this.hijoDer.cargarNivel(nivel+1);
        }
    }

    public int obtenerCantidadHojas() {
        int hojasIzquierdo = 0;
        int hojasDerecho = 0;
        if (this.hijoIzq == null && this.hijoDer == null) {
            return 1;
        }
        if (this.hijoIzq != null) {
            hojasIzquierdo = this.hijoIzq.obtenerCantidadHojas();
        }
        if (this.hijoDer != null) {
            hojasDerecho = this.hijoDer.obtenerCantidadHojas();
        }
        return hojasIzquierdo + hojasDerecho;
    }

    public TElementoAB eliminar(Comparable unaEtiqueta){

        if(unaEtiqueta.compareTo(this.etiqueta) < 0){
            if(this.hijoIzq != null){
                this.hijoIzq = this.hijoIzq.eliminar(unaEtiqueta);
            }
            return this;
        }

        if(unaEtiqueta.compareTo(this.etiqueta) > 0){
            if(this.hijoDer != null){
                this.hijoDer = this.hijoDer.eliminar(unaEtiqueta);
            }
            return this;
        }
        return this.quitaElNodo();
    }

    public TElementoAB quitaElNodo(){
        if(this.hijoIzq == null){
            return this.hijoDer;
        }
        if(this.hijoDer == null){
            return this.hijoIzq;
        }

        TElementoAB elHijo = this.hijoIzq;
        TElementoAB elPadre = this;
        while(elHijo.hijoDer != null){
            elPadre = elHijo;
            elHijo = elHijo.hijoDer;
        }

        if(elPadre != this){
            elPadre.hijoDer = elHijo.hijoIzq;
            elHijo.hijoIzq = this.hijoIzq;
        }

        elHijo.hijoDer = this.hijoDer;
        return elHijo;
    }
}
