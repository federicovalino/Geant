

public class Sucursal implements IAlmacen{

    private String direccion;
    private String telefono;
    private String nombre;
    private String barrio;
    private String ciudad;
    ArbolBinarioSucursal<Producto> arbolProductos;

    public Sucursal(String nombre,String barrio,String ciudad) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.ciudad = ciudad;
    }

    public Sucursal(String nombre,String barrio,String ciudad,ArbolBinarioSucursal<Producto> productos) {
        this.nombre = nombre;
        this.barrio = barrio;
        this.ciudad = ciudad;
        this.arbolProductos = productos;
    }

    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return this.telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return this.nombre;
    }


    public ArbolBinarioSucursal<Producto> getArbolProductos() {
        return this.arbolProductos;
    }

    /**
     * Incorporar un nuevo producto al supermercado.
     *
     * @param unProducto
     */
    public boolean insertarProducto(Producto unProducto) {
        TElementoAB<Producto> producto = new TElementoAB<Producto>(unProducto,unProducto.getEtiqueta());
        return this.arbolProductos.insertar(producto);
    }

    /**
     * Eliminar productos que ya no se venden (por no ser comercializados m�s).
     *
     * @param clave
     * @return
     */
    public boolean eliminar(Comparable clave) {
        return false;
    }

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductosInOrden() {
        return this.arbolProductos.inOrden();
    }

    /**
     * Dado un separador ej.:",", ";", imprime los productos separados por tal
     * caracter
     *
     * @param separador
     * @return
     */
    public String imprimirProductosPostOrden() {
        return this.arbolProductos.postOrden();
    }

    public String imprimirProductosPreOrden() {
        return this.arbolProductos.preOrden();
    }

    /**
     * Agregar stock a un producto existente.
     *
     * @param clave
     * @param cantidad
     * @return
     */
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

    /**
     * Simular la venta de un producto (reducir el stock de un producto
     * existente
     *
     * @param clave
     * @param cantidad
     * @return
     */
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

    /**
     * Dado un código de producto, indicar las existencias del mismo en el
     * almac�n.
     *
     * @param clave
     * @return
     */
    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        return getArbolProductos().buscar(clave).getDatos();
    }

    /**
     * Listar todos los productos registrados, ordenados por nombre, presentando
     * adem�s su stock. Imprime por consola la lista de todos los productos
     * registrados y su stock actual.
     */
    public void listarOrdenadoPorNombre() {

    }

    /**
     * Busca un producto por su descripci�n.
     *
     * @param descripcion
     * @return
     */
    public Producto buscarPorDescripcion(String descripcion) {
        return null;
    }

    /**
     * Retorna el tama�o del almacen: cantidad de productos. No es lo mismo que
     * el total de stock, sino que ser�a en definitiva el tama�o de la lista.
     *
     * @return
     */
    public int cantidadProductos() {
        return 0;
    }
}