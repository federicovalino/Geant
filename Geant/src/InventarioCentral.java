
public class InventarioCentral implements InventarioGeant {

    private TArbolBB<IAlmacen> almacenes;
    private TArbolBB<IProducto> catalogo;
    private TArbolBB<IProducto> registro;

    public InventarioCentral(TArbolBB<IAlmacen> arbolAlmacenes){
        this.almacenes = arbolAlmacenes;
    }

    public InventarioCentral(){
        this.almacenes = new TArbolBB<IAlmacen>();
        this.catalogo = new TArbolBB<IProducto>();
        this.registro = new TArbolBB<IProducto>();
    }

    public boolean incorporarSucursal(IAlmacen sucursal, Comparable clave) {
        if (!this.existeSucursal(clave)) {
            TElementoAB<IAlmacen> sucursalNodo = new TElementoAB<IAlmacen>(clave,sucursal);
            return this.almacenes.insertar(sucursalNodo);
        } else {
            return false;
        }
    }

    public boolean incorporarProducto(IProducto producto, Comparable clave) {
        TElementoAB<IProducto> productoNodo = new TElementoAB<IProducto>(clave,producto);
        return this.catalogo.insertar(productoNodo);
    }

    public void incorporarProductoSucursal(Comparable claveProd, Comparable claveSuc,int cantidad) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(claveProd);
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (producto != null && sucursal != null) {

            producto.getDatos().setStock(producto.getDatos().getStock()
                                                        + cantidad);

            IProducto productoClonado = new Producto(producto.getDatos().getPrecio(),
                producto.getDatos().getEtiqueta(),producto.getDatos().getNombre(),cantidad);

            sucursal.getDatos().insertarProducto(productoClonado);

            IAlmacen sucDato = sucursal.getDatos();

            Comparable etiqueta = sucDato.getCiudad() + "," +
                    sucDato.getBarrio() + "," + productoClonado.getNombre();

            TElementoAB<IProducto> productoRegistrado = new TElementoAB<IProducto>(etiqueta, productoClonado);
            this.registro.insertar(productoRegistrado);
        }
    }

    public void agregarStock(Comparable clave, int cantidad) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(clave);
        if (producto!=null) {
            int cantAnterior = producto.getDatos().getStock();
            producto.getDatos().setStock(cantAnterior + cantidad);
        }
    }

    public boolean agregarStockSucursal(Comparable claveProd, Comparable claveSuc, int cantidad) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(claveProd);
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (producto != null && sucursal != null) {
            return sucursal.getDatos().agregarStock(claveProd, cantidad);
        } else {
            return false;
        }
    }


    public IProducto buscarProducto(Comparable etiqueta) {
        TElementoAB<IProducto> producto = this.catalogo.buscar(etiqueta);
        if (producto != null) {
            return producto.getDatos();
        } else {
            return null;
        }
    }

    public IProducto buscarProductoSucursal(Comparable claveProd, Comparable claveSuc) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (sucursal != null) {
            return sucursal.getDatos().buscarPorCodigo(claveProd);
        } else {
            return null;
        }
    }

    public boolean ventaSucursal(Comparable claveProd, Comparable claveSuc, int cantidad) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (sucursal != null) {
            return sucursal.getDatos().venta(claveProd, cantidad);
        } else {
            return false;
        }
    }

    public void eliminarProducto(Comparable claveProd) {
        Lista<IAlmacen> lista = almacenes.inorden();
        Nodo<IAlmacen> nodoActual = lista.getPrimero();
        TElementoAB<IProducto> prod = this.catalogo.buscar(claveProd);
        while(nodoActual != null) {
            nodoActual.getDato().eliminarProducto(claveProd);
            IAlmacen sucActual = nodoActual.getDato();

            this.registro.eliminar(sucActual.getCiudad() +
                    sucActual.getBarrio() + prod.getDatos().getNombre());

            nodoActual = nodoActual.getSiguiente();
        }
        this.catalogo.eliminar(claveProd);
    }

    public boolean eliminarProductoSucursal(Comparable producto,Comparable claveSuc) {
        TElementoAB<IAlmacen> sucursal = this.almacenes.buscar(claveSuc);
        if (sucursal != null) {
            return sucursal.getDatos().eliminarProducto(producto);
        } else {
            return false;
        }
    }

    public boolean existeProducto(Comparable producto) {
        return this.catalogo.buscar(producto)!=null;
    }

    public boolean existeSucursal(Comparable sucursal) {
        return this.almacenes.buscar(sucursal)!=null;
    }

    public boolean locacionesProducto(Comparable producto) {
        return this.catalogo.buscar(producto)!=null;
    }

    public int obtenerCantidadCatalogo() {
        return this.catalogo.obtenerTamanio();
    }

    public int obtenerCantidadSucursales() {
        return this.almacenes.obtenerTamanio();
    }

    public String[] listarProductosPorCiudadBarrioNombre() {
        Lista<IProducto> prodRegistrados = this.registro.inorden();
        Nodo<IProducto> nodoActual = prodRegistrados.getPrimero();
        int cantidad = prodRegistrados.cantElementos();
        String[] listado = new String[cantidad];
        int i = 0;
        while(nodoActual != null) {
            listado[i] = nodoActual.getEtiqueta() + ","
                + String.valueOf(nodoActual.getDato().getStock());
            nodoActual = nodoActual.getSiguiente();
            i++;
        }
        return listado;
    }

    public String[] listarProductosOrdenadosPorNombre() {
        Nodo<IProducto> nodoActual = this.catalogo.inorden().getPrimero();
        TArbolBB<IProducto> arbol = new TArbolBB<IProducto>();
        int i=0;
        while(nodoActual != null) {
            TElementoAB<IProducto> nodoArbol = new TElementoAB<IProducto>
                (nodoActual.getDato().getNombre(),nodoActual.getDato());
            arbol.insertar(nodoArbol);
            nodoActual = nodoActual.getSiguiente();
            i++;
        }
        String[] listado = new String[i];
        nodoActual = arbol.inorden().getPrimero();
        int j = 0;
        while(nodoActual != null) {
            listado[j] = nodoActual.getDato().getNombre() + ","
                + String.valueOf(nodoActual.getDato().getStock());
            nodoActual = nodoActual.getSiguiente();
            j++;
        }
        return listado;
    }

}

/*
    public void incorporarProducto (Producto producto, Comparable sucursal) {
        try {
            TElementoAB<IAlmacen> almacen = this.almacenes.buscar(sucursal);

            TElementoAB<Producto> productoAlmacen =
                this.productos.buscar(producto.getEtiqueta());

            Producto productoNuevo = new Producto(producto.getPrecio(),
                producto.getCodigo(),producto.getNombre(),producto.getStock());

            TElementoAB<Producto> nuevoElementoProducto =
            new TElementoAB<Producto>(productoNuevo, productoNuevo.getEtiqueta());

            if (productoAlmacen == null && almacen != null) {
                almacen.getDatos().insertarProducto(producto);
                productos.insertar(nuevoElementoProducto);

            } else if (almacen == null) {

                System.out.print("No existe el almacén " + sucursal);

            } else if (productoAlmacen != null && almacen != null) {
                Producto productoSucursal = almacen.getDatos().
                    getArbolProductos().buscar(producto.getEtiqueta()).getDatos();

                productoAlmacen.getDatos().setStock
                    (producto.getStock() + productoAlmacen.getDatos().getStock());

                if (productoSucursal == null) {
                    almacen.getDatos().insertarProducto(producto);
                } else {
                    almacen.getDatos().agregarStock(producto.getEtiqueta(), producto.getStock());
                }
            }
        } catch (Exception e) {
            System.out.println("Error en incorporación: " + e.getMessage());
        }
    }

    public void agregarStockSucursal(Comparable claveProducto, Comparable sucursal, int cantidad) {
        Producto producto = this.productos.buscar(claveProducto).getDatos();
        producto.setStock(producto.getStock() + cantidad);
        Producto productoSucursal =
            (Producto) almacenes.buscar(sucursal).getDatos().buscarPorCodigo(claveProducto);
        productoSucursal.setStock(productoSucursal.getStock() + cantidad);
    }

    public void agregarStock(Comparable clave, int cantidad) {
        Producto producto = this.productos.buscar(clave).getDatos();
        producto.setStock(producto.getStock() + cantidad);
    }


    public void venta(Comparable claveProducto) {
        Producto producto = this.productos.buscar(claveProducto).getDatos();
        producto.setStock(producto.getStock() - 1);
    }

    public void ventaSucursal(Comparable claveProducto, Comparable sucursal) {
        Producto producto = this.productos.buscar(claveProducto).getDatos();
        producto.setStock(producto.getStock() - 1);
    }

    public boolean existeEnAlmacen(Comparable etiqueta) {
        return this.productos.buscar(etiqueta)!=null;
    }

    public String productosOrdenadosPorNombre() {
        TArbolBB<Producto> arbol = obtenerArbolOrdenadoPorNombre();
        Lista<Producto> listaNodosDelArbol = arbol.inorden();
        Nodo<Producto> nodoActual = listaNodosDelArbol.getPrimero();
        String stringADevolver = "";
        while (nodoActual != null) {
            Producto producto = nodoActual.getDato();
            stringADevolver = stringADevolver + producto.getNombre()
                    + "," + producto.getStock() + "\n";
            nodoActual = nodoActual.getSiguiente();
        }
        return stringADevolver;
    }

    public TArbolBB<Producto> obtenerArbolOrdenadoPorNombre() {
        Lista<Producto> listaNodosDelArbol = this.productos.inorden();
        Nodo<Producto> nodoActual = listaNodosDelArbol.getPrimero();
        TArbolBB<Producto> arbol = new TArbolBB<Producto>();
        while (nodoActual != null) {
            Producto productoNuevo = nodoActual.getDato();
            TElementoAB<Producto> producto = new TElementoAB<Producto>(productoNuevo.getNombre(),productoNuevo);
            arbol.raiz.insertar(producto);
            nodoActual = nodoActual.getSiguiente();
        }
        return arbol;
    }*/