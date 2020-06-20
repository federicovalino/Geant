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
    public static void main(String[] args) {
        InventarioGeant inventario = new InventarioCentral();
        Cliente.incorporarProductosPorArchivo("src/archivos_caso_prueba/productos.txt",inventario);
        Cliente.incorporarSucursalesPorArchivo("src/archivos_caso_prueba/sucursales.txt",inventario);
        Cliente.incorporarProductosSucursalPorArchivo("src/archivos_caso_prueba/stock.txt", inventario);
        System.out.println("Listado Productos: ");
        String[] array1 = inventario.listarProductosPorCiudadBarrioNombre();
        String[] array2 = inventario.listarProductosOrdenadosPorNombre();
        ManejadorArchivosGenerico.escribirArchivo("src/archivos_resultados/ProductosOrdenadosPorCiudadBarrioNombreStock.txt", array1);
        ManejadorArchivosGenerico.escribirArchivo("src/archivos_resultados/ProductosOrdenadosNombreStock.txt", array2);
    }
}
