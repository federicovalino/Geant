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
public class SucursalTest {
        
    public SucursalTest() {   
    }
    
    Sucursal sucursal;
    IProducto producto;
    
    @Before
    public void setUp() {
        sucursal = new Sucursal("Local Prueba","123456789"
                ,"Ucudal","La blanqueada","Montevideo","Montevideo");
        producto = new Producto(12.3,"1","Jabon",15);
        sucursal.insertarProducto(producto);
    }
    
    @Test
    public void testBuscarProducto() {
        boolean resultado = sucursal.buscarPorCodigo("1")==producto;
        boolean esperado = true;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testBuscarProductoQueNoEsta() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        boolean resultado = sucursal.buscarPorCodigo("2")==producto2;
        boolean esperado = false;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testBuscarProductoQueNoEstaDevuelveNull() {
        IProducto resultado = sucursal.buscarPorCodigo("2");
        IProducto esperado = null;
        assertEquals(esperado,resultado);
    }

    /**
     * Test of getNombre method, of class Sucursal.
     */
    @Test
    public void testInsertarProductoQueNoEsta() {
        IProducto producto2 = new Producto(13.3,"2","agua",15);
        sucursal.insertarProducto(producto2);
        boolean esperado = true;
        boolean resultado = sucursal.buscarPorCodigo("2")==producto2;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testInsertarProductoQueYaEsta() {
        sucursal.insertarProducto(producto);
        int esperado = 30;
        int resultado = sucursal.buscarPorCodigo("1").getStock();
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testInsertarProductosVarios() {
        IProducto producto2 = new Producto(22.3,"2","Shampoo",15);
        IProducto producto3 = new Producto(62.3,"3","Agua",15);
        IProducto producto4 = new Producto(42.3,"4","Disco vinilo",11);
        sucursal.insertarProducto(producto2);
        sucursal.insertarProducto(producto3);
        sucursal.insertarProducto(producto4);
        String esperado = "Jabon,Shampoo,Agua,Disco vinilo,";
        Lista<IProducto> productos = sucursal.getArbolProductos().inorden();
        Nodo<IProducto> nodoActual = productos.getPrimero();
        String resultado = "";
        while (nodoActual != null) {
            resultado = resultado + nodoActual.getDato().getNombre() + ",";
            nodoActual = nodoActual.getSiguiente();
        }
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testAgregarStockProductoQueExiste() {
        sucursal.agregarStock("1", 12);
        int esperado = 27;
        int resultado = sucursal.buscarPorCodigo("1").getStock();
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testAgregarStockProductoQueNoExiste() {        
        sucursal.agregarStock("2", 12);
        boolean esperado = false;
        boolean resultado = sucursal.buscarPorCodigo("2")!= null;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testVentaMayorAStock() {
        boolean resultado = sucursal.venta("1", 16);
        boolean esperado = false;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testCantidadProductoVentaMayorAStock() {
        sucursal.venta("1", 16);
        int resultado = sucursal.buscarPorCodigo("1").getStock();
        int esperado = 15;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testVentaIgualAStock() {
        sucursal.venta("1", 15);
        int resultado = sucursal.buscarPorCodigo("1").getStock();
        int esperado = 0;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testExisteEnAlmacen() {
        boolean resultado = sucursal.existeEnAlmacen("1");
        boolean esperado = true;
        assertEquals(esperado,resultado);
    }
    
    @Test
    public void testNoExisteEnAlmacen() {
        boolean resultado = sucursal.existeEnAlmacen("2");
        boolean esperado = false;
        assertEquals(esperado,resultado);
    }
}
