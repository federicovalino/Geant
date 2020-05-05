public class Lista<T> implements ILista<T> {

    protected Nodo<T> primero;

    public Lista(Nodo<T> nodo) {
        primero = nodo;
    }

    public Lista() {
        primero = null;
    }

    @Override
    public void insertar(Nodo<T> unNodo) {
        if (esVacia()) {
            primero = unNodo;
        } else {
            Nodo<T> aux = primero;
            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }
            aux.setSiguiente(unNodo);
        }
    }

    public void insertarPrimero(Nodo<T> nodoNuevo){
        if(this.esVacia()){
            this.primero = nodoNuevo;
        } else {
            nodoNuevo.setSiguiente(this.primero);
            this.primero = nodoNuevo;
        }
    }

    public Lista<T> insertarPorArchivo(String path) {
        Comparable[] etiquetas = ManejadorArchivosGenerico.leerArchivo(path);
        for (int i = 0; i < etiquetas.length; i++) {
            Nodo<T> nodo = new Nodo<T>(etiquetas[i],null);
            this.insertar(nodo);
        }
        return this;
    }

    @Override
    public Nodo<T> buscar(Comparable clave) {
        if (esVacia()) {
            return null;
        } else {
            Nodo<T> aux = primero;
            while (aux != null) {
                if (aux.getEtiqueta().equals(clave)) {
                    return aux;
                }
                aux = aux.getSiguiente();
            }
        }
        return null;
    }

    @Override
    public boolean eliminar(Comparable clave) {
        if (esVacia()) {
            return false;
        }
        if (primero.getSiguiente() == null) {
            if (primero.getEtiqueta().equals(clave)) {
                primero = null;
                return true;
            }
        }
        Nodo<T> aux = primero;
        if (aux.getEtiqueta().compareTo(clave) == 0) {
            //Eliminamos el primer elemento
            Nodo<T> temp1 = aux;
            Nodo<T> temp = aux.getSiguiente();
            primero = temp;
            return true;
        }
        while (aux.getSiguiente() != null) {
            if (aux.getSiguiente().getEtiqueta().equals(clave)) {
                Nodo<T> temp = aux.getSiguiente();
                aux.setSiguiente(temp.getSiguiente());
                return true;

            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    @Override
    public String imprimir() {
        String aux = "";
        if (!esVacia()) {
            Nodo<T> temp = primero;
            while (temp != null) {
                temp.imprimirEtiqueta();
                temp = temp.getSiguiente();
            }
        }
        return aux;
    }

    public String imprimir(String separador) {
        String aux = "";
        if (esVacia()) {
            return "";
        } else {
            Nodo<T> temp = primero;
            aux = "" + temp.getEtiqueta();
            while (temp.getSiguiente() != null) {
                aux = aux + separador + temp.getSiguiente().getEtiqueta();
                temp = temp.getSiguiente();
            }

        }
        return aux;

    }

    @Override
    public int cantElementos() {
        int contador = 0;
        if (esVacia()) {
            System.out.println("Cantidad de elementos 0.");
            return 0;
        } else {
            INodo<T> aux = primero;
            while (aux != null) {
                contador++;
                aux = aux.getSiguiente();
            }
        }
        return contador;
    }

    public Nodo<T> obtenerMayor() {
        Nodo<T> masGrande = this.primero;
        Nodo<T> actual = this.primero;
        while(actual!=null){
            if(actual.compareTo(masGrande.getEtiqueta())>0) {
                masGrande = actual;
            }
            actual = actual.getSiguiente();
        }
        return masGrande;
    }

    @Override
    public boolean esVacia() {
        return primero == null;
    }

    public boolean estaOrdenada(){
        Nodo<T> nodoActual = this.primero;
        while (nodoActual.compareTo(nodoActual.getSiguiente().getEtiqueta())<=0) {
            nodoActual = nodoActual.getSiguiente();
            if (nodoActual.getSiguiente() == null) {
                return true;
            }
        }
        return false;
    }

    public Nodo<T> getPrimero() {
        return primero;
    }

    @Override
    public void setPrimero(Nodo<T> unNodo) {
        this.primero = unNodo;
    }
}


