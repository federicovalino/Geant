public interface InventarioGeant {

    public boolean incorporarSucursal(IAlmacen sucursal, Comparable clave);

    public boolean incorporarProducto(IProducto producto, Comparable clave);

    public boolean incorporarProductoSucursal(Comparable claveProd, Comparable claveSuc,int cantidad);

    public boolean agregarStockSucursal(Comparable clave, Comparable sucursal, int cantidad);

    public IProducto buscarProducto(Comparable etiqueta);

    public IProducto buscarProductoSucursal(Comparable producto, Comparable sucursal);

    public boolean ventaSucursal(Comparable claveProd, Comparable claveSuc, int cantidad);

    public boolean eliminarProducto(Comparable producto);

    public boolean eliminarProductoSucursal(Comparable producto,Comparable sucursal);

    public String[] listarProductosPorCiudadBarrioNombre();

    public String[] listarProductosOrdenadosPorNombre();

    public boolean locacionesProducto(Comparable producto);

    public int obtenerCantidadCatalogo();

    public int obtenerCantidadSucursales();

    public boolean existeProducto(Comparable producto);

    public boolean existeSucursal(Comparable sucursal);
}