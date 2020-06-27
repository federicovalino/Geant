public class TElementoAVL<T> implements IElementoAVL<T> {

    private final Comparable etiquetaAvl;
    private TElementoAVL<T> hijoIzqAvl;
    private TElementoAVL<T> hijoDerAvl;
    private final T datosAvl;
    private int height;

    public TElementoAVL(Comparable unaEtiqueta, T unosDatos) {
        this.etiquetaAvl = unaEtiqueta;
        this.datosAvl = unosDatos;
        this.hijoDerAvl = null;
        this.hijoIzqAvl = null;
        this.height = 0;
    }

    public Comparable getEtiqueta() {
        return this.etiquetaAvl;
    }

    public T getDatos() {
        return this.datosAvl;
    }

    public TElementoAVL<T> getHijoDerAVL() {
        return this.hijoDerAvl;
    }

    public TElementoAVL<T> getHijoIzqAVL() {
        return this.hijoIzqAvl;
    }

    public void setHijoDerAVL(TElementoAVL<T> elemento) {
        this.hijoDerAvl = elemento;
    }

    public void setHijoIzqAVL(TElementoAVL<T> elemento) {
        this.hijoIzqAvl = elemento;
    }

    public TElementoAVL<T> insertar(TElementoAVL<T> unElemento) {
        if (unElemento.getEtiqueta().compareTo(etiquetaAvl) < 0) {
            if (hijoIzqAvl != null) {
                hijoIzqAvl = hijoIzqAvl.insertar(unElemento);
                actualizarAltura();
                return balancear();
            } else {
                hijoIzqAvl = unElemento;
                actualizarAltura();
                return this;
            }
        } else if (unElemento.getEtiqueta().compareTo(etiquetaAvl) > 0) {
            if (hijoDerAvl != null) {
                hijoDerAvl = hijoDerAvl.insertar(unElemento);
                actualizarAltura();
                return balancear();
            } else {
                hijoDerAvl = unElemento;
                actualizarAltura();
                return this;
            }
        } else {
            // ya existe un elemento con la misma etiqueta.
            return this;
        }
    }

    public TElementoAVL<T> buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta.equals(etiquetaAvl)) {
            return this;
        } else if (unaEtiqueta.compareTo(etiquetaAvl) < 0) {
            if (hijoIzqAvl != null) {
                return getHijoIzqAVL().buscar(unaEtiqueta);
            } else {
                return null;
            }
        } else if (hijoDerAvl != null) {
            return getHijoDerAVL().buscar(unaEtiqueta);
        } else {
            return null;
        }
    }

    public TElementoAVL<T> eliminar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiquetaAvl) < 0){
            if(hijoIzqAvl != null){
                hijoIzqAvl = hijoIzqAvl.eliminar(unaEtiqueta);
                actualizarAltura();
                return balancear();
            }
            return this;
        }
        if(unaEtiqueta.compareTo(etiquetaAvl) > 0){
            if(hijoDerAvl != null){
                hijoDerAvl = hijoDerAvl.eliminar(unaEtiqueta);
                actualizarAltura();
                return balancear();
            }
            return this;
        }
        TElementoAVL<T> nuevo = quitaElNodo();
        return nuevo;
    }

    private TElementoAVL<T> quitaElNodo(){

        if(hijoIzqAvl == null){
            return hijoDerAvl;
        }
        if(hijoDerAvl == null){
            return hijoIzqAvl;
        }

        TElementoAVL<T> elHijo = hijoIzqAvl;
        TElementoAVL<T> elPadre = this;
        while(elHijo.getHijoDerAVL() != null){
            elPadre = elHijo;
            elHijo = elHijo.getHijoDerAVL();
        }

        if(elPadre != this){
            elPadre.setHijoDerAVL(elHijo.hijoIzqAvl);
            elHijo.setHijoIzqAVL(hijoIzqAvl);
        }

        elHijo.setHijoDerAVL(hijoDerAvl);
        return elHijo;
    }

    public int getHeight() {
        return this.height;
    }

    private boolean isBalanceado() {
        int altHI = -1;
        if (hijoIzqAvl != null) {
            altHI = hijoIzqAvl.getHeight();
        }
        int altHD = -1;
        if (hijoDerAvl != null) {
            altHD = hijoDerAvl.getHeight();
        }
        return Math.abs(altHD - altHI) <= 1;
    }

    private TElementoAVL<T> balancear() {
        if (!isBalanceado()) {
            int altLL = -2;
            int altLR = -2;
            int altRL = -2;
            int altRR = -2;

            if (hijoIzqAvl != null) {
                if (hijoIzqAvl.getHijoIzqAVL() != null) {
                    altLL = hijoIzqAvl.getHijoIzqAVL().getHeight();
                } else {
                    altLL = -1;
                }
                if (hijoIzqAvl.getHijoDerAVL() != null) {
                    altLR = hijoIzqAvl.getHijoDerAVL().getHeight();
                } else {
                    altLR = -1;
                }
            }

            if (hijoDerAvl != null) {
                if (hijoDerAvl.getHijoIzqAVL() != null) {
                    altRL = hijoDerAvl.getHijoIzqAVL().getHeight();
                } else {
                    altRL = -1;
                }
                if (hijoDerAvl.getHijoDerAVL() != null) {
                    altRR = hijoDerAvl.getHijoDerAVL().getHeight();
                } else {
                    altRR = -1;
                }
            }

            if ((altLL > altLR) && (altLL > altRL) && (altLL > altRR)) {
                return rotacionLL(this);
            } else if ((altLR > altRL) && (altLR > altRR)) {
                return rotacionLR(this);
            } else if (altRL > altRR) {
                return rotacionRL(this);
            } else {
                return rotacionRR(this);
            }
        }
        return this;
    }

    /**
     * Acutaliza la altura del nodo
     */
    public void actualizarAltura() {
        int altL = -1;
        int altR = -1;
        if (hijoIzqAvl != null) {
            altL = hijoIzqAvl.getHeight();
        }
        if (hijoDerAvl != null) {
            altR = hijoDerAvl.getHeight();
        }
        height = 1 + Math.max(altL, altR);
    }


    /**
     * Realiza la rotacion necesaria cuando la causa del desbalance viene del
     * hijo izquierdo del hijo izquierdo.
     *
     * @param k2 El nodo en el que se da el desbalance
     * @return El nodo que toma el lugar de k2.
     */
    private TElementoAVL<T> rotacionLL(TElementoAVL<T> k2) {
        TElementoAVL<T> k1 = k2.getHijoIzqAVL();
        k2.setHijoIzqAVL(k1.getHijoDerAVL());
        k1.setHijoDerAVL(k2);
        k2.actualizarAltura();
        k1.actualizarAltura();
        return k1;
    }

    /**
     * Realiza la rotacion necesaria cuando la causa del desbalance viene del
     * hijo derecho del hijo derecho.
     *
     * @param k1 El nodo en el que se da el desbalance
     * @return El nodo que toma el lugar de k1.
     */
    private TElementoAVL<T> rotacionRR(TElementoAVL<T> k1) {
        TElementoAVL<T> k2 = k1.getHijoDerAVL();
        k1.setHijoDerAVL(k2.getHijoIzqAVL());
        k2.setHijoIzqAVL(k1);
        k1.actualizarAltura();
        k2.actualizarAltura();
        return k2;
    }

    /**
     * Realiza la rotacion necesaria cuando la causa del desbalance viene del
     * hijo derecho del hijo izquierdo.
     *
     * @param k3 El nodo en el que se da el desbalance
     * @return El nodo que toma el lugar de k3.
     */
    private TElementoAVL<T> rotacionLR(TElementoAVL<T> k3) {
        k3.setHijoIzqAVL(rotacionRR(k3.getHijoIzqAVL()));
        return rotacionLL(k3);
    }

    /**
     * Realiza la rotacion necesaria cuando la causa del desbalance viene del
     * hijo izquierdo del hijo derecho.
     *
     * @param k1 El nodo en el que se da el desbalance
     * @return El nodo que toma el lugar de k1.
     */
    private TElementoAVL<T> rotacionRL(TElementoAVL<T> k1) {
        k1.setHijoDerAVL(rotacionLL(k1.getHijoDerAVL()));
        return rotacionRR(k1);
    }

    public int obtenerTamanio() {
        int contador1 = 0;
        int contador2 = 0;
        int contadorFinal = 0;
        if (this == null) {
            return 0;
        }
        if (this.getHijoIzqAVL() != null) {
            contador1 = this.hijoIzqAvl.obtenerTamanio();
        }
        if (this.getHijoDerAVL() != null) {
            contador2 = this.hijoDerAvl.obtenerTamanio();
        }
        return contadorFinal = contador1 + contador2 + 1;
    }


    public int alturaNodo(TElementoAVL<T> nodo){
        if (nodo == null)
            return 0;
        int izq = -1;
        int der = -1;
        if(nodo.getHijoIzqAVL() != null){
            izq = nodo.getHijoIzqAVL().height;
        }
        if(nodo.getHijoDerAVL() != null){
            der=nodo.getHijoDerAVL().height;
        }
        return Integer.max(izq,der)+1;
    }

    public TElementoAVL<T> encontrarMinimo() {
        if(this.getHijoIzqAVL() != null){
            return this.getHijoIzqAVL().encontrarMinimo();
        }
        return this;
    }

    /**
     *
     * @return
     */
    public TElementoAVL<T> encontrarMaximo() {
        if(this.getHijoDerAVL() != null){
            return this.getHijoDerAVL().encontrarMaximo();
        }
        return this;
    }

    @Override
    public void inOrden(Lista<T> unaLista) {
        if (hijoIzqAvl != null) {
            hijoIzqAvl.inOrden(unaLista);
        }
        unaLista.insertar(new Nodo<T>(this.etiquetaAvl,this.getDatos()));
        if (hijoDerAvl != null) {
            hijoDerAvl.inOrden(unaLista);
        }
    }

    @Override
    public void preOrden(Lista<T> unaLista) {
        unaLista.insertar(new Nodo<T>(this.etiquetaAvl,this.getDatos()));
        if (hijoIzqAvl != null) {
            hijoIzqAvl.preOrden(unaLista);
        }
        if (hijoDerAvl != null) {
            hijoDerAvl.preOrden(unaLista);
        }
    }

    @Override
    public void postOrden(Lista<T> unaLista) {
        if (hijoIzqAvl != null) {
            hijoIzqAvl.postOrden(unaLista);
        }

        if (hijoDerAvl != null) {
            hijoDerAvl.postOrden(unaLista);
        }
        unaLista.insertar(new Nodo<T>(this.etiquetaAvl,this.getDatos()));
    }

    public int obtenerNivel(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(etiquetaAvl) == 0) {
            return 0;
        } else {
            if (unaEtiqueta.compareTo(etiquetaAvl) < 0) {
                if (hijoIzqAvl != null) {
                    return 1 + hijoIzqAvl.obtenerNivel(unaEtiqueta);
                } else {
                    return 0;
                }
            } else {
                if (unaEtiqueta.compareTo(etiquetaAvl) > 0) {
                    if (hijoDerAvl != null) {
                        return 1 + hijoDerAvl.obtenerNivel(unaEtiqueta);
                    } else {
                        return 0;
                    }
                } else {
                    return 0;
                }
            }
        }
    }

    public int cumpleAVL(boolean[] cumple) {
        if (cumple[0]==false) {
            return -1;
        }
        int altIzq = 0;
        int altDer = 0;
        if (getHijoIzqAVL() != null) {
            altIzq = this.getHijoIzqAVL().cumpleAVL(cumple);
        }
        if (getHijoDerAVL() != null) {
            altDer = this.getHijoDerAVL().cumpleAVL(cumple);
        }
        int diferencia = Math.abs(altIzq - altDer);
        if (diferencia > 1) {
            cumple[0] = false;
        }
        return Math.max(altIzq, altDer) + 1;
    }

    public String imprimir() {
        return (etiquetaAvl.toString());
    }
}