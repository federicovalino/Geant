public class Sucursal implements IAlmacen {

    private String nombre;
    private String direccion;
    private String barrio;
    private String ciudad;
    private String departamento;
    private String telefono;
    private String id;
    private TArbolBB<IProducto> productos;

    public Sucursal(String nombre,String id) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = "";
        this.telefono = "";
        this.barrio = "";
        this.ciudad = "";
        this.departamento = "";
        this.productos = new TArbolBB<IProducto>();
    }

    public Sucursal(String id,String telefono,String direccion,String barrio,String ciudad,String departamento) {
        this.id = id;
        this.nombre = "";
        this.direccion = direccion;
        this.telefono = telefono;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.departamento = departamento;
        this.productos = new TArbolBB<IProducto>();
    }

    public String getNombre() {
        return this.nombre;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null && id != "") {
            this.id = id;
        }
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion != null && direccion != "") {
            this.direccion = direccion;
        }
    }

    public String getBarrio() {
        return this.barrio;
    }

    public void setBarrio(String barrio) {
        if (barrio != null && barrio != "") {
            this.barrio = barrio;
        }
    }

    public String getCiudad() {
        return this.ciudad;
    }

    public void setCiudad(String ciudad) {
        if (ciudad != null && ciudad != "") {
            this.ciudad = ciudad;
        }
    }

    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        if (departamento != null && departamento !="") {
            this.departamento = departamento;
        }
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono != null && telefono != "") {
            this.telefono = telefono;
        }
    }

    public void insertarProducto(IProducto unProducto) {
        TElementoAB<IProducto> nodoNuevo = new TElementoAB<IProducto>(unProducto.getEtiqueta(),unProducto);
        if (this.productos == null) {
            this.productos = new TArbolBB<IProducto>(nodoNuevo);
        } else {
            Boolean estaEnAlmacen = this.agregarStock
                (unProducto.getEtiqueta(), unProducto.getStock());
            if (estaEnAlmacen != true) {
                this.productos.insertar(nodoNuevo);
            }
        }
    }

    public String imprimirProductos() {
        return this.productos.inOrden();
    }

    public boolean agregarStock(Comparable clave, Integer cantidad) {
        TElementoAB<IProducto> producto = this.productos.buscar(clave);
        if (producto!=null) {
            int cantidadActual = producto.getDatos().getStock();
            producto.getDatos().setStock(cantidadActual + cantidad);
            System.out.println("El valor económico agregado es: "
                + producto.getDatos().getPrecio()*cantidad);
            return true;
        } else {
            return false;
        }
    }

    public boolean venta(Comparable clave, Integer cantidad) {
        int restarStock = restarStock(clave, cantidad);
        if (restarStock == -1) {
            return false;
        } else {
            return true;
        }
    }

    private Integer restarStock(Comparable clave, Integer cantidad) {
        TElementoAB<IProducto> producto = this.productos.buscar(clave);
        if (producto!=null) {
            int cantidadActual = producto.getDatos().getStock();
            if (cantidad > cantidadActual) {
                System.out.println("La cantidad solicitada excede el stock "
                        + "actual que es de " + cantidadActual + " unidades");
                return -1;
            } else {
                producto.getDatos().setStock(cantidadActual - cantidad);
                System.out.println("El valor económico reducido es de: "
                + producto.getDatos().getPrecio()*cantidad);
                return cantidad;
            }

        } else {
            System.out.println("No existe el producto");
            return -1;
        }
    }

    public IProducto buscarPorCodigo(Comparable clave) {
        TElementoAB<IProducto> producto = this.productos.buscar(clave);
        if (producto!=null) {
            return producto.getDatos();
        } else {
            return null;
        }
    }

    public boolean existeEnAlmacen (Comparable clave) {
        return this.productos.buscar(clave) != null;
    }

    @Override
    public boolean eliminarProducto(Comparable clave) {
        this.productos.eliminar(clave);
        return this.buscarPorCodigo(clave) == null;
    }

    public TArbolBB<IProducto> getArbolProductos() {
        return this.productos;
    }

    public int cantidadProductos() {
        return this.productos.obtenerTamanio();
    }

}

/*
    public void actualizarProductosPorArchivo(String nombreArchivo) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String codigo;
        String descripcion;
        int precio;
        int cantidad;
        int valorAgregado = 0;
        String[] itemsLinea;
        IProducto productoEncontrado;
        for (int i = 0; i < contenidoArchivo.length; i++) {
            itemsLinea = contenidoArchivo[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            try {
                if (itemsLinea.length > 4) {
                    throw new Exception("La línea contiene más de 4 campos.");
                }
                codigo = itemsLinea[0];
                descripcion = itemsLinea[1];
                precio = Integer.parseInt(itemsLinea[2]);
                cantidad = Integer.parseInt(itemsLinea[3]);

                valorAgregado += precio * cantidad;
                productoEncontrado = this.buscarPorCodigo(codigo);
                if (productoEncontrado == null) {
                    IProducto productoNuevo = new Producto(codigo, descripcion, precio, cantidad);
                    this.insertarProducto(productoNuevo);
                } else {
                    this.agregarStock(codigo, cantidad);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar linea:\n" + contenidoArchivo[i]);
                System.out.println(e.getMessage());
            }
        }
        System.out.println("El valor del inventario aumentó: " + valorAgregado);
    }


    public void eliminarProductosPorArchivo(String nombreArchivo) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String codigo;
        int valorAgregado = 0;
        Producto productoEncontrado;
        for (int i = 0; i < contenidoArchivo.length; i++) {
            try {
                codigo = contenidoArchivo[i];
                productoEncontrado = this.buscarPorCodigo(codigo);
                if (productoEncontrado == null) {
                    System.out.println("No existe el producto");
                } else {
                    valorAgregado += productoEncontrado.getStock() * productoEncontrado.getPrecio();
                    //productoEncontrado.restarStock(productoEncontrado.getStock());
                    this.eliminarProducto(codigo);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar linea:\n" + contenidoArchivo[i]);
                System.out.println(e.getMessage());
            }
        }
        System.out.println("El valor del inventario se redujo: " + valorAgregado);
    }*/




/*
    public boolean insertarProducto(Producto unProducto) {
        TElementoAB<Producto> producto = new TElementoAB<Producto>(unProducto.getEtiqueta(),unProducto);
        return this.arbolProductos.insertar(producto);
    }


    public boolean eliminar(Comparable clave) {
        return false;
    }


    public String imprimirProductosInOrden() {
        return this.arbolProductos.inOrden();
    }

    public String imprimirProductosPostOrden() {
        return this.arbolProductos.postOrden();
    }

    public String imprimirProductosPreOrden() {
        return this.arbolProductos.preOrden();
    }

    public boolean agregarStock(Comparable clave, Integer cantidad) {
        Producto elemento = buscarPorCodigo(clave);
        if (elemento==null) {
            System.out.println("No existe producto a agregar Stock");
            return false;
        } else {
            elemento.setStock(elemento.getStock() + cantidad);
            return true;
        }
    }

    @Override
    public boolean restarStock(Comparable clave, Integer cantidad) {
        Producto elemento = buscarPorCodigo(clave);
        if (elemento==null) {
            System.out.println("No existe producto a agregar Stock");
            return false;
        } else {
            int cantidadStock = elemento.getStock();
            if (cantidadStock>=cantidad) {
                elemento.setStock(elemento.getStock() - cantidad);
                return true;
            } else {
                System.out.println("La cantidad solicitada sobrepasa "
                + "el stock remanente quedan: "+ cantidadStock+" elementos");
                return false;
            }
        }
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        return getArbolProductos().buscar(clave).getDatos();
    }

    public void listarOrdenadoPorNombre() {

    }

    public Producto buscarPorDescripcion(String descripcion) {
        return null;
    }

    public int cantidadProductos() {
        return 0;
    }

    public String listarProductosOrdenadosPorNombre() {
        Lista<Producto> lista = this.arbolProductos.inorden();
        TArbolBB<Producto> productos = new TArbolBB<Producto>();
        Nodo<Producto> nodoActual = lista.getPrimero();
        while (nodoActual != null) {
            Producto dato = nodoActual.getDato();
            TElementoAB<Producto> producto = new TElementoAB<Producto>(dato.getNombre(),dato);
            productos.insertar(producto);
            nodoActual = nodoActual.getSiguiente();
        }
        lista = productos.inorden();
        nodoActual = lista.getPrimero();
        String acumulador = "";
        while (nodoActual != null) {
            Producto dato = nodoActual.getDato();
            acumulador+= dato.getNombre() +","+dato.getPrecio()+"\n";
        }
        return acumulador;
    }
*/