
public class Producto implements IProducto {

    private Double precio;
    private Comparable codigo;
    private String nombre;
    private int stock;

    /**
     * Constructor clase Producto
     * @param precio
     * @param codigo
     * @param nombre
     * @param stock
    */
    public Producto(Double precio, Comparable codigo, String nombre, int stock){
        this.precio = precio;
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
    }
     /**
     * Retorna el codigo del Producto.
     *
     * @return codigo del Producto.
     */

    public Comparable getEtiqueta(){
        return codigo;
    }

    /**
     * Retorna el precio unitario del Producto.
     *
     * @return precio del Producto.
     */
    public Double getPrecio(){
        return precio;
    }

    /**
     * Método que permite modificar el precio del producto
     *
    */
    public void setPrecio(Double precio){
        this.precio = precio;
    }

    /**
     * Retorna el stock del Producto.
     *
     * @return stock del Producto.
     */
    public Integer getStock(){
        return stock;
    }

    /**
     * Permite modificar el stock del Producto
     */
    public void setStock(Integer stock){
        this.stock = stock;
    }

    /**
     * Retorna la descripcion/nombre del Producto.
     *
     * @return descripci�n del Producto.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Método que permite cambiar el valor del nombre
     *
    */
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    /**
     * Método que escribe un string con el codigo, el nombre y el precio del producto
     * pensado para hacer impresiones por consola
     *
     * @return string con caracteristicas del producto
    */
    public String getTextoAImprimir() {
        String texto = this.codigo + ", " + this.nombre + ", " + this.precio + ", " + this.stock;
        return texto;
    }

}