public interface IAlmacen {

    public String getDireccion();

    public void setDireccion(String direccion);

    public String getTelefono();

    public void setTelefono(String telefono);

    public String getNombre();

    public ArbolBinarioSucursal<Producto> getArbolProductos();
    /**
     * Incorporar un nuevo producto al supermercado.
     *
     * @param unProducto
     */
    public boolean insertarProducto(Producto unProducto);

    /**
     * Eliminar productos que ya no se venden (por no ser comercializados m�s).
     *
     * @param clave
     * @return
     */
    public boolean eliminar(Comparable clave);

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductosInOrden();

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductosPostOrden();

    /**
     * Imprime la lista de productos
     *
     * @return
     */
    public String imprimirProductosPreOrden();

    /**
     * Agregar stock a un producto existente.
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public boolean agregarStock(Comparable clave, Integer cantidad);

    /**
     * Simular la venta de un producto (reducir el stock de un producto
     * existente
     *
     * @param clave
     * @param cantidad
     * @return
     */
    public boolean restarStock(Comparable clave, Integer cantidad);

    /**
     * Dado un código de producto, indicar las existencias del mismo en el
     * almac�n.
     *
     * @param clave
     * @return
     */
    public IProducto buscarPorCodigo(Comparable clave);

    /**
     * Listar todos los productos registrados, ordenados por nombre, presentando
     * adem�s su stock. Imprime por consola la lista de todos los productos
     * registrados y su stock actual.
     */
    public void listarOrdenadoPorNombre();

    /**
     * Busca un producto por su descripci�n.
     *
     * @param descripcion
     * @return
     */
    public Producto buscarPorDescripcion(String descripcion);

    /**
     * Retorna el tama�o del almacen: cantidad de productos. No es lo mismo que
     * el total de stock, sino que ser�a en definitiva el tama�o de la lista.
     *
     * @return
     */
    public int cantidadProductos();

}
