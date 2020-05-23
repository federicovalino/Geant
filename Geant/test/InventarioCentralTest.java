/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author FIT
 */
public class InventarioCentralTest {
    
    public InventarioCentralTest() {
    }
    
    InventarioGeant inventario;
    IAlmacen sucursal;
    Producto producto;
    
    @Before
    public void setUp() {
        sucursal = new Sucursal("Local Prueba","123456789"
                ,"Ucudal","La Blanqueada","Montevideo","Montevideo");
        inventario = new InventarioCentral();
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        producto = new Producto(12.3,"1","Jabon",15);
    }
    
    /**
     * Test of incorporarSucursal method, of class InventarioCentral.
     */
    @Test
    public void testIncorporarSucursal() {
        Sucursal sucNueva = new Sucursal("Local Prueba Salto","123456789"
                ,"Ucudal Salto","Dayman","Salto","Salto");
        boolean resultado = inventario.incorporarSucursal(sucNueva, sucNueva.getId());
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarSucursalExistente() {
        boolean resultado = inventario.incorporarSucursal(sucursal, sucursal.getId());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProducto() {
        boolean resultado = inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProductoYaExistente() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean resultado = inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProductoSucursal() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        inventario.incorporarProducto(producto2, producto2.getEtiqueta());
        boolean resultado = inventario.incorporarProductoSucursal
                (producto2.getEtiqueta(), sucursal.getId(), 10);
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }

    @Test
    public void testIncorporarProductoSucursalNoExistente() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        boolean resultado = inventario.incorporarProductoSucursal
                (producto2.getEtiqueta(), sucursal.getId(), 10);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProductoYaExistenteEnSucursal() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        inventario.incorporarProductoSucursal
                (producto2.getEtiqueta(), sucursal.getId(), 10);
        boolean resultado = inventario.incorporarProductoSucursal
                (producto2.getEtiqueta(), sucursal.getId(), 10);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProductoEnSucursalNoExistente() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        inventario.incorporarProductoSucursal 
            (producto2.getEtiqueta(), sucursal.getId(), 10);
        boolean resultado = inventario.incorporarProductoSucursal
                (producto2.getEtiqueta(), "Local aleatorio", 10);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testIncorporarProductoNoExistenteEnSucursalNoExistente() {
        boolean resultado = inventario.incorporarProductoSucursal
                ("2", "Local aleatorio", 10);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testAgregarStock() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(),sucursal.getId(), 10);
        boolean result = inventario.agregarStockSucursal(producto.getEtiqueta(),sucursal.getId(),12);
        boolean esperado = true;
        assertEquals(esperado, result);
    }

    @Test
    public void testAgregarStockEnSucursalNoExistente() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean result = inventario.agregarStockSucursal(producto.getEtiqueta(),"Local aleatorio",12);
        boolean esperado = false;
        assertEquals(esperado, result);
    }
    
    @Test
    public void testAgregarStockProductoNoExistenteEnSucursalExistente() {
        boolean result = inventario.agregarStockSucursal("2",sucursal.getId(),12);
        boolean esperado = false;
        assertEquals(esperado, result);
    }
    
    @Test
    public void testAgregarStockProductoNoExistenteEnSucursalNoExistente() {
        boolean result = inventario.agregarStockSucursal("2","Local aleatorio",12);
        boolean esperado = false;
        assertEquals(esperado, result);
    }
    
    @Test
    public void testBuscarProducto() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean resultado = inventario.buscarProducto(producto.getEtiqueta()) == producto;
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testBuscarProductoNoExistente() {
        boolean resultado = inventario.buscarProducto(producto.getEtiqueta()) == producto;
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testBuscarProductoSucursal() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        String resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()).getNombre();
        String esperado = "Jabon";
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testBuscarProductoNoExistenteEnUnaSucursal() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        IProducto resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId());
        IProducto esperado = null;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testBuscarEnSucursalProductoNoExistenteInventario() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        boolean resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId())== null;
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testVentaSucursalNoExistente() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        boolean resultado = inventario.ventaSucursal(producto.getEtiqueta(), "Local aleatorio",11);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testVentaProductoNoExistente() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        boolean resultado = inventario.ventaSucursal(producto.getEtiqueta(),sucursal.getId(),11);
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testVentaCantidadGeneral() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        inventario.ventaSucursal(producto.getEtiqueta(), sucursal.getId(),11);
        int resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()).getStock();
        int esperado = 1;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testVentaMismaCantidad() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        inventario.ventaSucursal(producto.getEtiqueta(), sucursal.getId(),12);
        int resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()).getStock();
        int esperado = 0;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testVentaMasCantidad() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        inventario.ventaSucursal(producto.getEtiqueta(), sucursal.getId(),13);
        int resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()).getStock();
        int esperado = 12;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionSucursalNoExistente() {
        boolean resultado = inventario.eliminarProductoSucursal(producto.getEtiqueta(), "");
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionSucursalProductoNoExistente() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        boolean resultado =inventario.eliminarProductoSucursal(producto.getEtiqueta(), sucursal.getId());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoNoExistenteEnSucursalNoExistente() {
        boolean resultado =inventario.eliminarProductoSucursal(producto.getEtiqueta(), sucursal.getId());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionSucursalValor() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        inventario.eliminarProductoSucursal(producto.getEtiqueta(), sucursal.getId());
        boolean resultado = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()) == null;
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionSucursal() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        boolean resultado =inventario.eliminarProductoSucursal(producto.getEtiqueta(), sucursal.getId());
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoNoExistenteSucursal() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        boolean resultado = inventario.eliminarProducto(producto.getEtiqueta());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoNoExistente() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        boolean resultado = inventario.eliminarProducto(producto.getEtiqueta());
        boolean esperado = false;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoSoloInventario() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        boolean resultado = inventario.eliminarProducto(producto.getEtiqueta());
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoSoloInventario2() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.eliminarProducto(producto.getEtiqueta());
        boolean resultado = inventario.buscarProducto(producto.getEtiqueta())==null;
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testEliminacionProductoInventario() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        inventario.incorporarProductoSucursal(producto.getEtiqueta(), sucursal.getId(), 12);
        inventario.eliminarProducto(producto.getEtiqueta());
        boolean resultado1 = inventario.buscarProducto(producto.getEtiqueta())== null;
        boolean resultado2 = inventario.buscarProductoSucursal(producto.getEtiqueta(), sucursal.getId()) == null;
        boolean esperado = true;
        assertEquals(esperado, resultado1 && resultado2);
    }
    
    @Test
    public void testObtenerCantidadCatalogo() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        int resultado = inventario.obtenerCantidadCatalogo();
        int esperado = 1;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testObtenerCantidadSucursales() {
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        int resultado = inventario.obtenerCantidadSucursales();
        int esperado = 1;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testListarOrdenadoPorNombre0Producto() {
        String[] resultado = inventario.listarProductosOrdenadosPorNombre();
        String esperado = null;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testListarOrdenadoPorNombre1Producto() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarSucursal(sucursal, sucursal.getId());
        String[] resultado = inventario.listarProductosOrdenadosPorNombre();
        String esperado = "Jabon,15";
        assertEquals(esperado, resultado[0]);
    }
    
    @Test
    public void testListarOrdenadoPorNombre4Productos() {
        inventario.incorporarProducto(producto, producto.getEtiqueta());
        inventario.incorporarProducto(new Producto(22.3,"2","Shampoo",15),"2");
        inventario.incorporarProducto(new Producto(22.3,"3","Agua",15),"3");
        inventario.incorporarProducto(new Producto(22.3,"4","Guante",15),"4");
        String[] resultado = inventario.listarProductosOrdenadosPorNombre();
        String esperado = "Agua,15-Guante,15-Jabon,15-Shampoo,15";
        assertEquals(esperado, resultado[0]+"-"+resultado[1]+"-"+resultado[2]+"-"+resultado[3]);
    }
    
    @Test
    public void testListarOrdenadoCiudadBarrioNombreSucursalesVacia() {
        InventarioGeant inv = new InventarioCentral();
        inv.incorporarProducto(new Producto(22.3,"2","Shampoo",15),"2");
        String[] resultado = inv.listarProductosPorCiudadBarrioNombre();
        String esperado = null;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testListarOrdenadoCiudadBarrioNombreSinProductos() {
        InventarioGeant inv = new InventarioCentral();
        inv.incorporarProducto(new Producto(22.3,"2","Shampoo",15),"2");
        String[] resultado = inv.listarProductosPorCiudadBarrioNombre();
        String esperado = null;
        assertEquals(esperado, resultado);
    }
    
    @Test
    public void testListarOrdenadoCiudadBarrioNombre3Productos2Sucursales() {
        Sucursal sucNueva = new Sucursal("Local Prueba Salto","123456789","Ucudal Salto","Dayman","Salto","Salto");
        inventario.incorporarProducto(new Producto(22.3,"2","Shampoo",15),"2");
        inventario.incorporarProducto(new Producto(22.3,"3","Agua",15),"3");
        inventario.incorporarProducto(new Producto(22.3,"4","Guante",15),"4");
        inventario.incorporarProductoSucursal("4", sucursal.getId(), 1);
        inventario.incorporarProductoSucursal("2", sucursal.getId(), 1);
        inventario.incorporarProductoSucursal("3", sucursal.getId(), 1);
        inventario.incorporarProductoSucursal("4", sucNueva.getId(), 1);
        inventario.incorporarProductoSucursal("2", sucNueva.getId(), 1);
        inventario.incorporarProductoSucursal("3", sucNueva.getId(), 1);
        String[] listado = inventario.listarProductosPorCiudadBarrioNombre();
        String[] listadoEsperado = {"Montevideo,La Blanqueada,Agua,1",
                "Montevideo,La Blanqueada,Guante,1", "Montevideo,La Blanqueada,Shampoo,1",
                "Salto,Dayman,Agua,1","Salto,Guante,Agua,1","Salto,Guante,Shampoo,1"};
        boolean resultado = true;
        for (int i=0; i<listado.length;i++) {
            if(listado[i].equals(listadoEsperado[i])){
                resultado = true;
            } else {
                resultado = false;
                break;
            }
        }
        boolean esperado = true;
        assertEquals(esperado, resultado);
    }
}