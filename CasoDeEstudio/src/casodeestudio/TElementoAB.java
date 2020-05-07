import java.util.LinkedList;

public class TElementoAB<T> implements ITElementoAB<T> {

    private int nivel;
    Comparable etiqueta;
    T dato;
    TElementoAB<T> hijoIzquierdo;
    TElementoAB<T> hijoDerecho;

    public TElementoAB(T datoIngresado, Comparable clave) {
        this.etiqueta = clave;
        this.dato = datoIngresado;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public TElementoAB(T datoIngresado, Comparable clave, TElementoAB<T> hijoI, TElementoAB<T> hijoD) {
        this.etiqueta = clave;
        this.dato = datoIngresado;
        this.hijoIzquierdo = hijoI;
        this.hijoDerecho = hijoD;

    }

    public Comparable getEtiqueta() {
        return this.etiqueta;
    }

    /**
     * Obtiene el hijo izquierdo del nodo.
     *
     * @return Hijo Izquierdo del nodo.
     */
    public TElementoAB<T> getHijoIzq() {
        return this.hijoIzquierdo;
    }

    /**
     * Obtiene el hijo derecho del nodo.
     *
     * @return Hijo derecho del nodo.
     */
    public TElementoAB<T> getHijoDer() {
        return this.hijoDerecho;
    }

    /**
     * Asigna el hijo izquierdo del nodo.
     *
     * @return Elemento a ser asignado como hijo izquierdo.
     */
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzquierdo = elemento;
    }

    /**
     * Asigna el hijo derecho del nodo.
     *
     * @return Elemento a ser asignado como hijo derecho.
     */
    public void setHijoDer(TElementoAB<T> elemento) {

        this.hijoDerecho = elemento;

    }

    /**
     * Busca un elemento dentro del arbol con la etiqueta indicada.
     *
     * @param unaEtiqueta del nodo a buscar
     * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
     */
    public TElementoAB<T> buscar(Comparable unaEtiqueta) {
        if (unaEtiqueta.compareTo(this.etiqueta) == 0) {
            return this;
        } else {
            if (unaEtiqueta.compareTo(this.etiqueta) < 0) {
                if (this.hijoIzquierdo != null) {
                    return this.hijoIzquierdo.buscar(unaEtiqueta);
                } else {
                    return null;
                }
            } else {
                if (unaEtiqueta.compareTo(this.etiqueta) > 0) {
                    if (this.hijoDerecho != null) {
                        return hijoDerecho.buscar(unaEtiqueta);
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Inserta un elemento dentro del arbol.
     *
     * @param elemento Elemento a insertar.
     * @return Exito de la operaci�n.
     */
    public boolean insertar(TElementoAB<T> elemento) {
        if (elemento.etiqueta.equals(this.etiqueta)) {
            return false;
        } else if (elemento.etiqueta.compareTo(this.etiqueta) < 0) {
            if (this.hijoIzquierdo == null) {
                this.hijoIzquierdo = elemento;
                return true;
            } else {
                return this.hijoIzquierdo.insertar(elemento);
            }
        } else {
            if (this.hijoDerecho == null) {
                this.hijoDerecho = elemento;
                return true;
            } else {
                return this.hijoDerecho.insertar(elemento);
            }
        }
    }

    /**
     * Imprime en preorden el arbol separado por guiones.
     *
     * @return String conteniendo el PreOrden
     */
    public String preOrden() {
        String temp = "";
        temp = temp + "," + this.etiqueta;
        if (hijoIzquierdo != null) {
            temp = hijoIzquierdo.preOrden();
        }
        if (hijoDerecho != null) {
            temp = temp + hijoDerecho.preOrden();
        }
        return temp;
    }

    /**
     * Imprime en inorden el arbol separado por guiones.
     *
     * @return String conteniendo el InOrden
     */
    public String inOrden() {
        String temp = "";
        if (hijoIzquierdo != null) {
            temp = hijoIzquierdo.inOrden() + ",";
        }
        temp += this.etiqueta;
        if (hijoDerecho != null) {
            temp = temp + "," + hijoDerecho.inOrden();
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
        if (hijoIzquierdo != null) {
            temp = hijoIzquierdo.postOrden();
        }
        if (hijoDerecho != null) {
            temp = temp + hijoDerecho.postOrden();
        }
        temp = temp + "," + etiqueta;
        return temp;
    }

    /**
     * Retorna los datos contenidos en el elemento.
     *
     * @return
     */
    public T getDatos() {
        return this.dato;

    }

    /**
     * Elimina un elemento dada una etiqueta.
     *
     * @param unaEtiqueta
     * @return
     */
    public TElementoAB eliminar(Comparable unaEtiqueta) {
        return null;
    }

    // Cantidad de Nodos
    public int cantidadDeNodos() {
        int contador1 = 0;
        int contador2 = 0;
        int contadorFinal = 0;
        if (this == null) {
            return 0;
        }
        if (this.hijoIzquierdo != null) {
            contador1 = this.hijoIzquierdo.cantidadDeNodos();
        }
        if (this.getHijoDer() != null) {
            contador2 = this.hijoDerecho.cantidadDeNodos();
        }
        return contadorFinal = contador1 + contador2 + 1;
    }

    public int obtenerAltura() {
        int alturaIzquierdo = 0;
        int alturaDerecho = 0;
        if (this.hijoIzquierdo == null && this.hijoDerecho == null) {
            return 0;
        }
        if (this.hijoIzquierdo != null) {
            alturaIzquierdo = this.hijoIzquierdo.obtenerAltura();
        }
        if (this.hijoDerecho != null) {
            alturaDerecho = this.hijoDerecho.obtenerAltura();
        }
        return 1 + (alturaIzquierdo > alturaDerecho ? alturaIzquierdo : alturaDerecho);
    }

    public int obtenerCantidadHojas() {
        int hojasIzquierdo = 0;
        int hojasDerecho = 0;
        if (this.hijoIzquierdo == null && this.hijoDerecho == null) {
            return 1;
        }
        if (this.hijoIzquierdo != null) {
            hojasIzquierdo = this.hijoIzquierdo.obtenerCantidadHojas();
        }
        if (this.hijoDerecho != null) {
            hojasDerecho = this.hijoDerecho.obtenerCantidadHojas();
        }
        return hojasIzquierdo + hojasDerecho;
    }

    public int obtenerNivelDeClave(Comparable unaEtiqueta) {
        if (this.etiqueta.compareTo(unaEtiqueta) == 0) {
            return 0;
        }
        if (this.hijoIzquierdo!=null) {
            if (this.etiqueta.compareTo(unaEtiqueta) > 0) {
                return 1 + this.hijoIzquierdo.obtenerNivelDeClave(unaEtiqueta);
            }
        }
        if (this.hijoDerecho!=null) {
            if (this.etiqueta.compareTo(unaEtiqueta) < 0) {
                return 1 + this.hijoDerecho.obtenerNivelDeClave(unaEtiqueta);
            }
        }
        return -1;
    }

    public Comparable obtenerMenorClave() {
        if (this.hijoIzquierdo!=null) {
            return this.hijoIzquierdo.obtenerMenorClave();
        } else {
            return this.getEtiqueta();
        }
    }

    public Comparable obtenerMayorClave() {
        if (this.hijoDerecho!=null) {
            return this.hijoDerecho.obtenerMayorClave();
        } else {
            return this.getEtiqueta();
        }
    }

    public Comparable obtenerClaveAnterior(Comparable clave) {
        if (this.hijoIzquierdo!=null) {
            if (this.etiqueta.compareTo(clave) > 0) {
                if (this.hijoIzquierdo.getEtiqueta().equals(clave)) {
                    return this.getEtiqueta();
                } else {
                    return this.hijoIzquierdo.obtenerClaveAnterior(clave);
                }
            }
        }
        if (this.hijoDerecho!=null) {
            if (this.etiqueta.compareTo(clave) < 0) {
                if (this.hijoDerecho.getEtiqueta().equals(clave)) {
                    return this.getEtiqueta();
                } else {
                    return this.hijoDerecho.obtenerClaveAnterior(clave);
                }
            }
        }
        System.out.println("El árbol no tiene el nodo");
        return null;
    }

    public void cargarNivel(int nivel) {
        this.nivel = nivel;
        if (this.hijoDerecho != null && this.hijoIzquierdo != null) {
            this.hijoIzquierdo.cargarNivel(nivel+1);
            this.hijoDerecho.cargarNivel(nivel+1);
        } else if (this.hijoIzquierdo != null && this.hijoDerecho == null) {
            this.hijoIzquierdo.cargarNivel(nivel+1);
        } else if (this.hijoIzquierdo == null && this.hijoDerecho != null){
            this.hijoDerecho.cargarNivel(nivel+1);
        }
    }

    public void obtenerNodosNivel(int nivel, LinkedList<TElementoAB<T>> lista) {
        if (this.nivel==nivel) {
            lista.addFirst(this);
        }
        if (hijoIzquierdo != null) {
            this.hijoIzquierdo.obtenerNodosNivel(nivel,lista);
        }
        if (hijoDerecho != null) {
            this.hijoDerecho.obtenerNodosNivel(nivel,lista);
        }
    }

    public void obtenerHojas(LinkedList<TElementoAB<T>> lista) {
        if (this.hijoIzquierdo == null && hijoDerecho == null) {
            lista.addFirst(this);
        }
        if (hijoIzquierdo != null) {
            this.hijoIzquierdo.obtenerHojas(lista);
        }
        if (hijoDerecho != null) {
            this.hijoDerecho.obtenerHojas(lista);
        }
    }

    public int getNivel() {
        return this.nivel;
    }
}
