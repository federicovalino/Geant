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

    public boolean insertarProducto(IProducto unProducto) {
        TElementoAB<IProducto> nodoNuevo = new TElementoAB<IProducto>
                (unProducto.getEtiqueta(),unProducto);
        if (this.productos == null) {
            this.productos = new TArbolBB<IProducto>(nodoNuevo);
            return true;
        } else {
            Boolean estaEnAlmacen = this.agregarStock
                (unProducto.getEtiqueta(), unProducto.getStock());
            if (estaEnAlmacen != true) {
                return this.productos.insertar(nodoNuevo);
            }
            return estaEnAlmacen;
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