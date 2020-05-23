import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author FIT
 */
public class CasoDeEstudio {

    /**
     * @param args the command line arguments
     */
    public static void intercambiarLugar(String[] array,int i, int j) {
        String aux1 = array[i];
        String aux2 = array[j];
        array[i] = aux2;
        array[j] = aux1;
    }

    public static void desordenarLista(String[] array) {
        int largo = array.length;
        int j;
        Random random = new Random();
        for (int i=0;i<largo;i++){
            if (i!=0) {
                j = random.nextInt(i);
                intercambiarLugar(array,i,j);
            }
        }
    }

    public static void incorporarProductosPorArchivo(String nombreArchivo, InventarioGeant inventario) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String codigo;
        String descripcion;
        Double precio;
        String[] itemsLinea;
        boolean bandera = true;
        int contador = 0;
        desordenarLista(contenidoArchivo);
        for (int i = 0; i < contenidoArchivo.length; i++) {
            itemsLinea = contenidoArchivo[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            try {
                contador++;
                //System.out.println(contador);
                if (itemsLinea.length > 3) {
                    throw new Exception("La línea contiene más de 4 campos.");
                }
                codigo = itemsLinea[0].replaceAll("\"","");
                descripcion = itemsLinea[1].replaceAll("\"","");
                precio = Double.parseDouble(itemsLinea[2]);
                IProducto productoNuevo = new Producto(precio, codigo, descripcion, 0);
                inventario.incorporarProducto(productoNuevo, codigo);
            } catch (Exception e) {
               // System.out.println("Error al procesar linea: " + contador);
               // System.out.println(e.getMessage());
                bandera = false;
            }
        }
        if (bandera) {
            System.out.println("Carga productos satisfactoria");
        } else {
            System.out.println("Algunos productos no han sido cargados" +
            " revise que esté respetando las normas de inserción de archivos");
        }

        System.out.println(inventario.obtenerCantidadCatalogo());
    }

    public static void incorporarSucursalesPorArchivo(String nombreArchivo, InventarioGeant inventario) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String id;
        String telefono;
        String direccion;
        String codigoPostal;
        String ciudad;
        String departamento;
        boolean bandera = true;
        String[] itemsLinea;
        int contador = 0;
        desordenarLista(contenidoArchivo);
        for (int i = 0; i < contenidoArchivo.length; i++) {
            itemsLinea = contenidoArchivo[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            try {
                contador++;
                //System.out.println(contador);
                if (itemsLinea.length > 6) {
                    throw new Exception("La línea contiene más de 6 campos.");
                }
                id = itemsLinea[0].replaceAll("\"","");
                telefono = itemsLinea[1].replaceAll("\"","");
                direccion =  itemsLinea[2].replaceAll("\"","");
                codigoPostal = itemsLinea[3].replaceAll("\"","");
                ciudad =  itemsLinea[4].replaceAll("\"","");
                departamento =  itemsLinea[5].replaceAll("\"","");
                IAlmacen sucursal = new Sucursal(id,telefono,direccion,codigoPostal,ciudad,departamento);
                inventario.incorporarSucursal(sucursal,id);
            } catch (Exception e) {
                //System.out.println("Error al procesar linea: " + contador);
                bandera = false;
            }
        }
        if (bandera) {
            System.out.println("Carga de sucursales satisfactoria");
        } else {
            System.out.println("Algunas sucursales no han sido cargadas" +
            " revise que esté respetando las normas de inserción por archivo");
        }
        System.out.println(inventario.obtenerCantidadSucursales());
    }

    public static void incorporarProductosSucursalPorArchivo(String nombreArchivo, InventarioGeant inventario) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String clavSuc;
        String clavProd;
        int cantidad;
        boolean bandera = true;
        String[] itemsLinea;
        int contador = 0;
        desordenarLista(contenidoArchivo);
        for (int i = 0; i < contenidoArchivo.length; i++) {
            itemsLinea = contenidoArchivo[i].split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
            try {
                contador++;
                if (itemsLinea.length > 3) {
                    throw new Exception("La línea contiene más de 3 campos.");
                }
                clavSuc = itemsLinea[0].replaceAll("\"","");
                clavProd = itemsLinea[1].replaceAll("\"","");
                cantidad = Integer.parseInt(itemsLinea[2]);
                inventario.incorporarProductoSucursal(clavProd, clavSuc, cantidad);
            } catch (Exception e) {
                System.out.println("Error al procesar linea: " + contador);
                //System.out.println(e.getMessage());
                bandera = false;
            }
        }
        if (bandera) {
            System.out.println("Carga productos satisfactoria");
        } else {
            System.out.println("Algunos items no han sido cargados" +
            " revise que esté respetando las normas de inserción por archivo");
        }
    }


    public static void eliminarProductosPorArchivo(String nombreArchivo, InventarioCentral inventario) {
        String[] contenidoArchivo = ManejadorArchivosGenerico.leerArchivo(nombreArchivo);
        String codigo;
        int valorAgregado = 0;
        IProducto productoEncontrado;
        for (int i = 0; i < contenidoArchivo.length; i++) {
            try {
                codigo = contenidoArchivo[i];
                productoEncontrado = inventario.buscarProducto(codigo);
                if (productoEncontrado == null) {
                    System.out.println("No existe el producto");
                } else {
                    valorAgregado += productoEncontrado.getStock() * productoEncontrado.getPrecio();
                    //productoEncontrado.restarStock(productoEncontrado.getStock());
                    inventario.eliminarProducto(codigo);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar linea: " + contenidoArchivo[i]);
                System.out.println(e.getMessage());
            }
        }
        System.out.println("El valor del inventario se redujo: " + valorAgregado);
    }

    public static void main(String[] args) {
        InventarioGeant inventario = new InventarioCentral();
        incorporarProductosPorArchivo("src/archivos_caso_prueba/productos.txt",inventario);
        incorporarSucursalesPorArchivo("src/archivos_caso_prueba/sucursales.txt",inventario);
        incorporarProductosSucursalPorArchivo("src/archivos_caso_prueba/stock.txt", inventario);
        System.out.println("Listado Productos: ");
        String[] array1 = inventario.listarProductosPorCiudadBarrioNombre();
        String[] array2 = inventario.listarProductosOrdenadosPorNombre();
        ManejadorArchivosGenerico.escribirArchivo("ProductosOrdenadosPorCiudadBarrioNombreStock.txt", array1);
        ManejadorArchivosGenerico.escribirArchivo("ProductosOrdenadosNombreStock.txt", array2);
    }
}
