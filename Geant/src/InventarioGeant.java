public interface InventarioGeant {

    public boolean incorporarSucursal(IAlmacen sucursal, Comparable clave);

    public boolean incorporarProductoNuevo(IProducto producto, Comparable clave);

    public void incorporarProductoSucursal(Comparable claveProd, Comparable claveSuc,int cantidad);

    public boolean agregarStock(Comparable clave, Comparable sucursal, int cantidad);

    public IProducto buscarProducto(Comparable etiqueta);

    public IProducto buscarProductoSucursal(Comparable producto, Comparable sucursal);

    public boolean ventaSucursal(Comparable claveProd, Comparable claveSuc, int cantidad);

    public void eliminarProducto(Comparable producto);

    public boolean eliminarProductoSucursal(Comparable producto,Comparable sucursal);

    public String productosOrdenadosPorNombreConStock();

    public String listarProductosPorCiudadBarrioNombre();

    public String listarProductosOrdenadosPorNombre();

    public int obtenerCantidadCatalogo();

    public int obtenerCantidadSucursales();
}