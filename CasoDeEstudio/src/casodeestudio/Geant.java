public class Geant {

    TArbolBB<IAlmacen> almacenes;
    TArbolBB<Producto> productos;

    public Geant(TArbolBB<IAlmacen> arbolAlmacenes){
        this.almacenes = arbolAlmacenes;
    }

    public Geant(){
        this.almacenes = new TArbolBB<IAlmacen>();
        this.productos = new TArbolBB<Producto>();
    }

    public void incorporarProducto (Producto productoNuevo, Comparable sucursal) {
        if (this.productos.buscar(productoNuevo.getEtiqueta()) == null) {
            TElementoAB<Producto> nuevoProducto = new TElementoAB<Producto>(productoNuevo, productoNuevo.getEtiqueta());
            this.productos.insertar(nuevoProducto);
        }
    }
}