public class TElementoAB<T> implements IElementoAB<T> {

    private Comparable etiqueta;
    private TElementoAB<T> hijoIzq;
    private TElementoAB<T> hijoDer;
    private T datos;
    private int frecExito;
    private int frecNoExitoIzq;
    private int frecNoExitoDer;

    /**
     * @param unaEtiqueta
     * @param unosDatos
     */
    @SuppressWarnings("unchecked")
    public TElementoAB(Comparable unaEtiqueta, T unosDatos) {
        etiqueta = unaEtiqueta;
        datos = unosDatos;
        this.frecExito = 0;
        this.frecNoExitoIzq = 0;
        this.frecNoExitoDer = 0;
    }

    @Override
    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public T getDatos() {
        return datos;
    }

    public TElementoAB<T> getHijoIzq() {
        return hijoIzq;
    }

    public TElementoAB<T> getHijoDer() {
        return hijoDer;
    }

    @Override
    public void setHijoIzq(TElementoAB<T> elemento) {
        this.hijoIzq = elemento;

    }

    @Override
    public void setHijoDer(TElementoAB<T> elemento) {
        this.hijoDer = elemento;
    }

    public int getfrecExito() {
        return this.frecExito;
    }

    public int getfrecNoExitoDer() {
        return this.frecNoExitoDer;
    }

    public int getfrecNoExitoIzq() {
        return this.frecNoExitoIzq;
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

    public TElementoAB<T> quitaElNodo(){
        if(this.hijoIzq == null){
            return this.hijoDer;
        }
        if(this.hijoDer == null){
            return this.hijoIzq;
        }

        TElementoAB<T> elHijo = this.hijoIzq;
        TElementoAB<T> elPadre = this;
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

    public void inorden(Lista<T> unaLista) {
        Nodo<T> nodoLista = null;
        if (hijoIzq != null) {
            this.hijoIzq.inorden(unaLista);
        }
        nodoLista = new Nodo<T>(this.getEtiqueta(),this.getDatos());
        unaLista.insertarPrimero(nodoLista);
        if (hijoDer != null) {
            this.hijoDer.inorden(unaLista);
        }
    }

    public void preorden(Lista<T> unaLista) {
        unaLista.insertar(new Nodo<T>(this.etiqueta,this.getDatos()));
        if (hijoIzq != null) {
            hijoIzq.preorden(unaLista);
        }
        if (hijoDer != null) {
            hijoDer.preorden(unaLista);
        }
    }

    public void postorden(Lista<T> unaLista) {
        if (hijoIzq != null) {
            hijoIzq.postorden(unaLista);
        }

        if (hijoDer != null) {
            hijoDer.postorden(unaLista);
        }
        unaLista.insertar(new Nodo<T>(this.etiqueta,this.getDatos()));
    }

    /**
     * @return
     */
    public String imprimir() {
        return (etiqueta.toString());
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

    public void cuentaFrec(Comparable unaClave) {
        if (this.etiqueta.compareTo(unaClave)==0){
            this.frecExito++;
            return;
        }
        if (unaClave.compareTo(this.etiqueta)<0){
            if (this.hijoIzq != null){
                this.hijoIzq.cuentaFrec(unaClave);
            } else {
                this.frecNoExitoIzq++;
                return;
            }
        } else {
            if (this.hijoDer != null){
                this.hijoDer.cuentaFrec(unaClave);
            } else {
                this.frecNoExitoDer++;
                return;
            }
        }
    }

    public int calcularCosto(int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE, int nivel) {

        int suma = 0;

        if (hijoIzq != null) {
            suma += hijoIzq.calcularCosto(FrecExito, FrecNoExito, indiceFE, indiceFNE, nivel + 1);
        } else {
            suma += FrecNoExito[indiceFNE[0]] * (nivel + 1);
            indiceFNE[0]++;
        }

        suma += FrecExito[indiceFE[0]] * nivel;
        indiceFE[0]++;

        if (hijoDer != null) {
             suma += hijoDer.calcularCosto(FrecExito, FrecNoExito, indiceFE, indiceFNE, nivel + 1);
        } else {
            suma += FrecNoExito[indiceFNE[0]] * (nivel + 1);
            indiceFNE[0]++;
        }
        return suma;
    }

    public void completaVectores(Comparable[] claves, int[] FrecExito, int[] FrecNoExito, int[] indiceFE, int[] indiceFNE) {
        if (this.hijoIzq != null){
            this.hijoIzq.completaVectores(claves, FrecExito, FrecNoExito, indiceFE, indiceFNE);
        }
        FrecExito[indiceFE[0]]= this.getfrecExito();
        claves[indiceFE[0]]= this.getEtiqueta();
        indiceFE[0]++;

        if(this.hijoIzq == null){
            FrecNoExito[indiceFNE[0]]= this.getfrecNoExitoIzq();
            indiceFNE[0]++;
        }
        if(this.hijoDer == null){
            FrecNoExito[indiceFNE[0]]= this.getfrecNoExitoDer();
            indiceFNE[0]++;
        }
        if (this.hijoDer != null){
            this.hijoDer.completaVectores(claves, FrecExito, FrecNoExito, indiceFE, indiceFNE);
        }
    }

    public int cumpleAVL(boolean[] cumple) {
        if (cumple[0]==false) {
            return -1;
        }
        int altIzq = 0;
        int altDer = 0;
        if (getHijoIzq() != null) {
            altIzq = this.getHijoIzq().cumpleAVL(cumple);
        }
        if (getHijoDer() != null) {
            altDer = this.getHijoDer().cumpleAVL(cumple);
        }
        int diferencia = Math.abs(altIzq - altDer);
        if (diferencia > 1) {
            cumple[0] = false;
        }
        return Math.max(altIzq, altDer) + 1;
    }

}
