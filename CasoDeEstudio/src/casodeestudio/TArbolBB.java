import java.util.LinkedList;

public class TArbolBB<T> implements IArbolBB<T> {

  protected TElementoAB<T> raiz;
  int contador;

  public TArbolBB() {
    raiz = null;
  }

  public TArbolBB(TElementoAB<T> raizIngresada) {
    raiz = raizIngresada;
  }

  public boolean insertar(TElementoAB<T> unElemento) {
    if (this.raiz == null) {
      this.raiz = unElemento;
      return true;
    } else {
      return this.raiz.insertar(unElemento);
    }
  }

  public int insertarConConteo(TElementoAB<T> unElemento) {
    if (this.raiz == null) {
      this.raiz = unElemento;
      return 0;
    } else {
      int alturaRecibida = this.raiz.insertarConConteo(unElemento);
      return alturaRecibida + 1;
    }
  }

  /**
   * Busca un elemento dentro del árbol.
   *
   *
   * @param unaEtiqueta Etiqueta identificadora del elemento a buscar. .
   * @return Elemento encontrado. En caso de no encontrarlo, retorna nulo.
   */
  public TElementoAB<T> buscar(Comparable unaEtiqueta) {
    this.contador = 0;
    if (raiz != null) {
      TElementoAB<T> elementoBuscado = raiz.buscar(unaEtiqueta);
      if (elementoBuscado != null) {
        this.contador = raiz.buscar(unaEtiqueta, this.contador).conteo;
      } else {
        this.contador = -1;
      }
      return elementoBuscado;
    } else {
      return null;
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
   * Imprime en InOrden los elementos del árbol, separados por guiones.
   *
   * @return String conteniendo el preorden separado por guiones.
   */
  public String inOrden() {
    if (this.raiz != null) {
      return raiz.inOrden();
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
   * Elimina un elemento dada una etiqueta.
   *
   * @param unaEtiqueta
   */
  public void eliminar(Comparable unaEtiqueta) {
  }

  public int cuentaNodos() {
    int cantidad = (this.raiz).cantidadDeNodos();
    return cantidad;
  }

  public int obtenerAltura() {
    if (this.raiz == null) {
      return 0;
    } else {
      return this.raiz.obtenerAltura();
    }
  }

  public int obtenerCantidadHojas() {
    if (this.raiz == null) {
      return 0;
    } else {
      return this.raiz.obtenerCantidadHojas();
    }
  }

  public int obtenerNivelDeClave(Comparable unaEtiqueta) {
    if (this.raiz == null) {
      return -1;
    }
    if (this.raiz.etiqueta.compareTo(unaEtiqueta) == 0) {
      return 0;
    }
     return this.raiz.obtenerNivelDeClave(unaEtiqueta);
  }

  public Comparable obtenerMenorClave() {
    if (this.raiz == null) {
      return null;
    }
    if (this.raiz.getHijoIzq()==null){
      return this.raiz.getEtiqueta();
    } else {
      return this.raiz.obtenerMenorClave();
    }
  }

  public Comparable obtenerMayorClave() {
    if (this.raiz == null) {
      return null;
    }
    if (this.raiz.getHijoDer()==null){
      return this.raiz.getEtiqueta();
    } else {
      return this.raiz.obtenerMayorClave();
    }
  }

  public Comparable obtenerClaveAnterior(Comparable clave) {
    if (this.raiz == null) {
      System.out.println("Arbol está vacío");
      return null;
    }
    if (this.raiz.getHijoDer()==null & this.raiz.getHijoIzq()==null){
      System.out.println("Arbol tiene 1 solo nodo");
      return null;
    } else {
      return this.raiz.obtenerClaveAnterior(clave);
    }
  }

  public void cargarNivelesNodos() {
    this.raiz.cargarNivel(0);
  }

  public LinkedList<TElementoAB<T>> obtenerNodosNivel(int nivel) {
    this.raiz.cargarNivel(0);
    LinkedList<TElementoAB<T>> listaNodosNivel = new LinkedList<TElementoAB<T>>();
    this.raiz.obtenerNodosNivel(nivel, listaNodosNivel);
    return listaNodosNivel;
  }

  public LinkedList<TElementoAB<T>> obtenerHojas() {
    this.raiz.cargarNivel(0);
    LinkedList<TElementoAB<T>> listaHojas = new LinkedList<TElementoAB<T>>();
    this.raiz.obtenerHojas(listaHojas);
    return listaHojas;
  }

  public String hojasConSuNivel() {
    LinkedList<TElementoAB<T>> lista = this.obtenerHojas();
    String cadena = "";
    for (int i=0;i<lista.size();i++) {
      cadena = cadena + lista.get(i).getEtiqueta() + "," + lista.get(i).getNivel() + "\n";
    }
    return cadena;
  }
}