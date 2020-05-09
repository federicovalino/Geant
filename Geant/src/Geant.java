
public class Geant {

    ArbolBinarioSucursal<IAlmacen> almacenes;
    ArbolBinarioSucursal<Producto> productos;

    public Geant(ArbolBinarioSucursal<IAlmacen> arbolAlmacenes){
        this.almacenes = arbolAlmacenes;
    }

    public Geant(){
        this.almacenes = new ArbolBinarioSucursal<IAlmacen>();
        this.productos = new ArbolBinarioSucursal<Producto>();
    }

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
}