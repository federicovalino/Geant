public interface InventarioGeant {

    /**
     * Método que incorpora sucursal a inventario
     *
     * @param sucursal
     * @param clave
     * @return boolean
     */
    public boolean incorporarSucursal(IAlmacen sucursal, Comparable clave);

    /**
     * Método que inserta producto en inventario
     *
     * @param producto
     * @param clave
     * @return boolean
     */
    public boolean incorporarProducto(IProducto producto, Comparable clave);

    /**
     * Método que inserta en sucursal un producto existente en inventario
     *
     * @param claveProd
     * @param claveSuc
     * @param cantida
     * @return valor booleano
     */
    public boolean incorporarProductoSucursal(Comparable claveProd, Comparable claveSuc,int cantidad);

    /**
     * Agrega Stock de un producto a una sucursal del inventario
     *
     * @param claveProd
     * @param claveSuc
     * @param cantidad
     */
    public boolean agregarStockSucursal(Comparable clave, Comparable sucursal, int cantidad);

    /**
     * Busca una sucursal en el inventario
     *
     * @param etiqueta
     * @returns almacen o null
     */
    public IAlmacen buscarSucursal(Comparable etiqueta) ;

    /**
     * Busca un producto en el catalogo del inventario
     *
     * @param etiqueta
     * @return producto o null
     */
    public IProducto buscarProducto(Comparable etiqueta);

    /**
     * Busca un producto en una sucursal especificada
     *
     * @param etiqueta
     * @return producto o null
     */
    public IProducto buscarProductoSucursal(Comparable producto, Comparable sucursal);

    /**
     * Articula la venta de un producto en una sucursal delegando acciones
     *
     * @param claveProd
     * @param claveSuc
     * @param cantidad
     * @return valor booleano
     */
    public boolean ventaSucursal(Comparable claveProd, Comparable claveSuc, int cantidad);

    /**
     * Elimina el producto de toda la cadena de supermercados
     *
     * @param claveProd
     * @return valor booleano
     */
    public boolean eliminarProducto(Comparable producto);

    /**
     * Elimina producto de una sucursal
     *
     * @param producto
     * @param claveSuc
     * @return valor booleano
     */
    public boolean eliminarProductoSucursal(Comparable producto,Comparable sucursal);

    /**
     * Devuelve array con los productos ordenados por ciudad barrrio nombre y stock
     *
     * @return array de strings
     */
    public String[] listarProductosPorCiudadBarrioNombre();

    /**
     * Obtiene todos los productos del catálogo ordenados por nombre y con stock
     *
     * @return array de strings
     */
    public String[] listarProductosOrdenadosPorNombre();

    /**
     * Obtiene cantidad de productos disponibles en el catalogo
     *
     * @return cantidad
     */
    public int obtenerCantidadCatalogo();
    /**
     * Obtiene cantidad de sucursales en el inventario
     *
     * @cantidad
     */
    public int obtenerCantidadSucursales();

    /**
     * Evalua existencia del producto en el catalogo del inventario
     *
     * @param producto
     * @return valor booleano
     */
    public boolean existeProducto(Comparable producto);

    /**
     * Evalua existencia de sucursal en el inventario
     *
     * @param sucursal
     * @return valor booleano
     */
    public boolean existeSucursal(Comparable sucursal);
}