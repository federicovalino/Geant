
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
public class Cliente {
    
    public Cliente(){
    }

    private static void intercambiarLugar(String[] array, int i, int j) {
        String aux1 = array[i];
        String aux2 = array[j];
        array[i] = aux2;
        array[j] = aux1;
    }

    private static void desordenarLista(String[] array) {
        int largo = array.length;
        int j;
        Random random = new Random();
        for (int i = 0; i < largo; i++) {
            if (i != 0) {
                j = random.nextInt(i);
                intercambiarLugar(array, i, j);
            }
        }
    }

    public static void incorporarSucursal(IAlmacen sucursal, InventarioGeant inventario) {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
    }
    
    public static void incorporarProductos(IProducto producto, InventarioGeant inventario) {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
    }
    
    public static void incorporarProductosEnSucursal(Comparable sucursal,Comparable producto ,int cantidad, InventarioGeant inventario) {
        inventario.incorporarProductoSucursal(producto, sucursal, cantidad);
    }
    
    public static void agregarStock(Comparable sucursal,Comparable producto ,int cantidad, InventarioGeant inventario) {
        inventario.agregarStockSucursal(producto, sucursal, cantidad);
    }
    
    public static void eliminarProducto(Comparable producto,InventarioGeant inventario) {
        inventario.eliminarProducto(producto);
    }
    
    public static void eliminarProductoSucursal(Comparable sucursal,Comparable producto,InventarioGeant inventario) {
        inventario.eliminarProductoSucursal(producto, sucursal);
    }
    
    public static void listarProductosOrdenadoPorNombre(InventarioGeant inventario) {
        inventario.listarProductosOrdenadosPorNombre();
    }
    
    public static void listarOrdenadoPorCiudadBarrioNombre(InventarioGeant inventario) {
        inventario.listarProductosPorCiudadBarrioNombre();
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
                codigo = itemsLinea[0].replaceAll("\"", "");
                descripcion = itemsLinea[1].replaceAll("\"", "");
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
            System.out.println("Algunos productos no han sido cargados"
                    + " revise que esté respetando las normas de inserción de archivos");
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
                id = itemsLinea[0].replaceAll("\"", "");
                telefono = itemsLinea[1].replaceAll("\"", "");
                direccion = itemsLinea[2].replaceAll("\"", "");
                codigoPostal = itemsLinea[3].replaceAll("\"", "");
                ciudad = itemsLinea[4].replaceAll("\"", "");
                departamento = itemsLinea[5].replaceAll("\"", "");
                IAlmacen sucursal = new Sucursal(id, telefono, direccion, codigoPostal, ciudad, departamento);
                inventario.incorporarSucursal(sucursal, id);
            } catch (Exception e) {
                //System.out.println("Error al procesar linea: " + contador);
                bandera = false;
            }
        }
        if (bandera) {
            System.out.println("Carga de sucursales satisfactoria");
        } else {
            System.out.println("Algunas sucursales no han sido cargadas"
                    + " revise que esté respetando las normas de inserción por archivo");
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
                clavSuc = itemsLinea[0].replaceAll("\"", "");
                clavProd = itemsLinea[1].replaceAll("\"", "");
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
            System.out.println("Algunos items no han sido cargados"
                    + " revise que esté respetando las normas de inserción por archivo");
        }
    }
}
