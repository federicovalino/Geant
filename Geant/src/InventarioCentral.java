
import java.security.SecureRandom;
import java.math.BigInteger;

public class InventarioCentral implements InventarioGeant {

    private TArbolBB<IAlmacen> almacenes;
    private TArbolBB<IProducto> catalogo;

    /**
     * Método Constructor que recibe árbol
     */
    public InventarioCentral(TArbolBB<IAlmacen> arbolAlmacenes) {
        this.almacenes = arbolAlmacenes;
    }

    /**
     * Método Constructor
     */
    public InventarioCentral() {
        this.almacenes = new TArbolBB<IAlmacen>();
        this.catalogo = new TArbolBB<IProducto>();
    }

    /**
     * Método que incorpora sucursal a inventario
     *
     * @param sucursal
     * @param clave
     * @return boolean
     */
    public boolean incorporarSucursal(IAlmacen sucursal, Comparable clave) {
        TElementoAB<IAlmacen> sucursalNodo = new TElementoAB<IAlmacen>(clave, sucursal);
        return this.almacenes.insertar(sucursalNodo);
    }

    /**
     * Método que inserta producto en inventario
     *
     * @param producto
     * @param clave
     * @return boolean
     */
    public boolean incorporarProducto(IProducto producto, Comparable clave) {
        TElementoAB<IProducto> productoNodo = new TElementoAB<IProducto>(clave, producto);
        return this.catalogo.insertar(productoNodo);
    }

    /**
     * Método que inserta en sucursal un producto existente en inventario
     *
     * @param claveProd
     * @param claveSuc
     * @param cantida
     * @return valor booleano
     */
    public boolean incorporarProductoSucursal(Comparable claveProd, Comparable claveSuc, int cantidad) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(claveProd);
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (producto != null && sucursal != null) {

            producto.getDatos().setStock(producto.getDatos().getStock() + cantidad);

            IProducto productoClonado = new Producto(producto.getDatos().getPrecio(),
                    producto.getDatos().getNombre() + "," + producto.getDatos().getEtiqueta(),
                    producto.getDatos().getNombre(), cantidad);

            return sucursal.getDatos().insertarProducto(productoClonado);
        } else {
            return false;
        }
    }

    /**
     * Agrega Stock de un producto a una sucursal del inventario
     *
     * @param claveProd
     * @param claveSuc
     * @param cantidad
     */
    public boolean agregarStockSucursal(Comparable claveProd, Comparable claveSuc, int cantidad) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(claveProd);
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (producto != null && sucursal != null) {
            return sucursal.getDatos().agregarStock(producto.getDatos().getNombre()
                    + "," + claveProd, cantidad);
        } else {
            return false;
        }
    }

    /**
     * Busca una sucursal en el inventario
     *
     * @param etiqueta
     * @returns almacen o null
     */
    public IAlmacen buscarSucursal(Comparable etiqueta) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(etiqueta);
        if (sucursal != null) {
            return sucursal.getDatos();
        } else {
            return null;
        }
    }

    /**
     * Busca un producto en el catalogo del inventario
     *
     * @param etiqueta
     * @return producto o null
     */
    public IProducto buscarProducto(Comparable etiqueta) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(etiqueta);
        if (producto != null) {
            return producto.getDatos();
        } else {
            return null;
        }
    }

    /**
     * Busca un producto en una sucursal especificada
     *
     * @param etiqueta
     * @return producto o null
     */
    public IProducto buscarProductoSucursal(Comparable claveProd, Comparable claveSuc) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        TElementoAB<IProducto> producto = this.catalogo.buscar(claveProd);
        if (sucursal != null && producto != null) {
            IProducto prodBuscado = sucursal.getDatos().buscarPorCodigo(producto.getDatos().getNombre() + "," + claveProd);
            if (prodBuscado != null) {
                return prodBuscado;
            } else {
                enQueSucursales(claveProd);
                return null;
            }
        } else if (producto != null) {
            System.out.println("El producto que buscas no existe");
            return null;
        } else {
            System.out.println("La sucursal en la que buscas no existe");
            return null;
        }
    }

    private void enQueSucursales(Comparable clavProd) {
        IProducto producto = this.buscarProducto(clavProd);
        System.out.println("El producto no tiene stock en la sucursal que buscas");
        if (producto.getStock().equals(0)) {
            System.out.println("El producto buscado no tiene stock en otras sucursales");
        } else {
            Lista<IAlmacen> listaAlmacen = this.almacenes.inorden();
            Nodo<IAlmacen> nodoActual = listaAlmacen.getPrimero();
            while (nodoActual != null) {
                if (nodoActual.getDato().buscarPorCodigo(producto.getNombre() + "," + clavProd) != null) {
                    System.out.println("El producto tiene stock en "
                            + nodoActual.getDato().getId()
                            + " " + nodoActual.getDato().getCiudad());
                }
                nodoActual = nodoActual.getSiguiente();
            }
        }
    }

    /**
     * Articula la venta de un producto en una sucursal delegando acciones
     *
     * @param claveProd
     * @param claveSuc
     * @param cantidad
     * @return valor booleano
     */
    public boolean ventaSucursal(Comparable claveProd, Comparable claveSuc, int cantidad) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        TElementoAB<IProducto> prodInv = this.catalogo.buscar(claveProd);
        if (sucursal != null & prodInv != null) {
            if (sucursal.getDatos().venta(prodInv.getDatos().getNombre() + "," + claveProd, cantidad)) {
                int cant = prodInv.getDatos().getStock();
                prodInv.getDatos().setStock(cant - cantidad);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Elimina el producto de toda la cadena de supermercados
     *
     * @param claveProd
     * @return valor booleano
     */
    public boolean eliminarProducto(Comparable claveProd) {
        Lista<IAlmacen> lista = almacenes.inorden();
        Nodo<IAlmacen> nodoActual = lista.getPrimero();
        TElementoAB<IProducto> prod = this.catalogo.buscar(claveProd);
        if (prod == null) {
            return false;
        } else {
            while (nodoActual != null) {
                nodoActual.getDato().eliminarProducto(prod.getDatos().getEtiqueta()
                        + "," + claveProd);
                nodoActual = nodoActual.getSiguiente();
            }
            this.catalogo.eliminar(claveProd);
            return true;
        }
    }

    /**
     * Elimina producto de una sucursal
     *
     * @param producto
     * @param claveSuc
     * @return valor booleano
     */
    public boolean eliminarProductoSucursal(Comparable producto, Comparable claveSuc) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        TElementoAB<IProducto> prod = this.catalogo.buscar(producto);
        if (sucursal != null && prod != null) {
            return sucursal.getDatos().eliminarProducto(prod.getDatos().getNombre() + "," + producto);
        } else {
            return false;
        }
    }

    /**
     * Evalua existencia del producto en el catalogo del inventario
     *
     * @param producto
     * @return valor booleano
     */
    public boolean existeProducto(Comparable producto) {
        return this.catalogo.buscar(producto) != null;
    }

    /**
     * Evalua existencia de sucursal en el inventario
     *
     * @param sucursal
     * @return valor booleano
     */
    public boolean existeSucursal(Comparable sucursal) {
        return this.almacenes.buscar(sucursal) != null;
    }

    /**
     * Obtiene cantidad de productos disponibles en el catalogo
     *
     * @return cantidad
     */
    public int obtenerCantidadCatalogo() {
        return this.catalogo.obtenerTamanio();
    }

    /**
     * Obtiene cantidad de sucursales en el inventario
     *
     * @cantidad
     */
    public int obtenerCantidadSucursales() {
        return this.almacenes.obtenerTamanio();
    }

    private TArbolBB<IAlmacen> obtenerAlmacenesOrdenadosPorNombre() {
        Lista<IAlmacen> listaAlmacenes = this.almacenes.inorden();
        if (listaAlmacenes == null) {
            return null;
        }
        Nodo<IAlmacen> nodoActual = listaAlmacenes.getPrimero();
        TArbolBB<IAlmacen> arbol = new TArbolBB<IAlmacen>();
        while (nodoActual != null) {
            TElementoAB<IAlmacen> nodo = new TElementoAB<IAlmacen>(nodoActual.getDato().getCiudad() + ","
                    + nodoActual.getDato().getBarrio(), nodoActual.getDato());

            arbol.insertar(nodo);
            nodoActual = nodoActual.getSiguiente();
        }
        return arbol;
    }

    private Lista<Lista<IProducto>> ordenarProductosPorCiudadBarrioNombre() {
        TArbolBB<IAlmacen> arbol = obtenerAlmacenesOrdenadosPorNombre();
        if (arbol == null) {
            return null;
        }
        Lista<IAlmacen> lista = arbol.inorden();
        Nodo<IAlmacen> nodoActual = lista.getPrimero();
        Lista<Lista<IProducto>> listaProd = new Lista<Lista<IProducto>>();
        while (nodoActual != null) {
            Lista<IProducto> producto = nodoActual.getDato().getArbolProductos().inorden();
            listaProd.insertar(new Nodo<Lista<IProducto>>(nodoActual.getEtiqueta(), producto));
            nodoActual = nodoActual.getSiguiente();
        }
        return listaProd;
    }

    /**
     * Devuelve array con los productos ordenados por ciudad barrrio nombre y stock
     *
     * @return array de strings
     */
    public String[] listarProductosPorCiudadBarrioNombre() {
        Lista<Lista<IProducto>> listaProd = ordenarProductosPorCiudadBarrioNombre();
        if (listaProd == null) {
            return null;
        }
        Nodo<Lista<IProducto>> nodoActual = listaProd.getPrimero();
        int cantidad = 0;
        while (nodoActual != null) {
            cantidad = cantidad + nodoActual.getDato().cantElementos();
            nodoActual = nodoActual.getSiguiente();
        }
        String[] listado = new String[cantidad];
        nodoActual = listaProd.getPrimero();
        if (nodoActual == null) {
            return null;
        }
        int i = 0;
        while (nodoActual != null) {
            Nodo<IProducto> nodoAuxiliar = nodoActual.getDato().getPrimero();
            while (nodoAuxiliar != null) {
                listado[i] = nodoActual.getEtiqueta() + ","
                        + nodoAuxiliar.getDato().getNombre() + ","
                        + nodoAuxiliar.getDato().getStock();
                i++;
                nodoAuxiliar = nodoAuxiliar.getSiguiente();
            }
            nodoActual = nodoActual.getSiguiente();
        }
        return listado;
    }

    /**
     * Obtiene todos los productos del catálogo ordenados por nombre y con stock
     *
     * @return array de strings
     */
    public String[] listarProductosOrdenadosPorNombre() {
        Nodo<IProducto> nodoActual = this.catalogo.inorden().getPrimero();
        TArbolBB<IProducto> arbol = new TArbolBB<IProducto>();
        while (nodoActual != null) {
            TElementoAB<IProducto> nodoArbol = new TElementoAB<IProducto>(nodoActual.getDato().getNombre(), nodoActual.getDato());
            arbol.insertar(nodoArbol);
            nodoActual = nodoActual.getSiguiente();
        }
        Lista<IProducto> lista = arbol.inorden();
        int cantidad = lista.cantElementos();
        String[] listado = new String[cantidad];
        nodoActual = lista.getPrimero();
        int j = 0;
        if (nodoActual == null) {
            return null;
        }
        while (nodoActual != null) {
            listado[j] = nodoActual.getDato().getNombre() + ","
                    + String.valueOf(nodoActual.getDato().getStock());
            nodoActual = nodoActual.getSiguiente();
            j++;
        }
        return listado;
    }
}
