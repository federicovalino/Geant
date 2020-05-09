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
public class ArbolBinarioSucursalTest {

    public ArbolBinarioSucursalTest() {
    }

    @Test
    public void testTamañoArbolEstaVacio() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        int esperado = -1;
        int resultado = arbol.cuentaNodos();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testTamañoArbolConUnNodo() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> nodo = new TElementoAB<Integer>(1, 1);
        arbol.insertar(nodo);
        int esperado = 1;
        int resultado = arbol.cuentaNodos();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testTamañoArbolCantidadCoincide() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, "5");
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(7, "7");
        TElementoAB<Integer> elemento3 = new TElementoAB<Integer>(4, "4");
        TElementoAB<Integer> elemento4 = new TElementoAB<Integer>(1, "1");
        TElementoAB<Integer> elemento5 = new TElementoAB<Integer>(9, "9");
        TElementoAB<Integer> elemento6 = new TElementoAB<Integer>(3, "3");

        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        arbol.insertar(elemento3);
        arbol.insertar(elemento4);
        arbol.insertar(elemento5);
        arbol.insertar(elemento6);

        int esperado = 6;
        int resultado = arbol.cuentaNodos();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testAlturaArbolVacio() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        int esperado = -1;
        int resultado = arbol.obtenerAltura();
        assertEquals(esperado, resultado);
    }

    @Test
    public void testAlturaArbolAlturaUno() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> nodo = new TElementoAB<Integer>(1, 1);
        arbol.insertar(nodo);
        int esperado = 0;
        int resultado = arbol.obtenerAltura();
        assertEquals(esperado, resultado);
    }

    @Test
    public void testAlturaArbolNoAlteraElOrden() {
        ArbolBinarioSucursal<Integer> arbolCargado = new ArbolBinarioSucursal<Integer>();
        Integer[] numerosAlAzar = {4, 8, 5, 11, 7, 1};
        int contador = 0;
        for (int i = 0; i < numerosAlAzar.length; i++) {
            TElementoAB<Integer> nodo = new TElementoAB<Integer>(i, numerosAlAzar[i]);
            arbolCargado.insertar(nodo);
            contador++;
        }
        arbolCargado.obtenerAltura();
        int esperado = 3;
        int resultado = arbolCargado.obtenerAltura();
        assertEquals(esperado, resultado);
    }

    @Test
    public void testAlturaArbolCoincide() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, "5");
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(7, "7");
        TElementoAB<Integer> elemento3 = new TElementoAB<Integer>(4, "4");
        TElementoAB<Integer> elemento4 = new TElementoAB<Integer>(1, "1");
        TElementoAB<Integer> elemento5 = new TElementoAB<Integer>(9, "9");
        TElementoAB<Integer> elemento6 = new TElementoAB<Integer>(3, "3");

        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        arbol.insertar(elemento3);
        arbol.insertar(elemento4);
        arbol.insertar(elemento5);
        arbol.insertar(elemento6);

        int esperado = 3;
        int resultado = arbol.obtenerAltura();

        assertEquals(esperado, resultado);
    }

    /**
     * Test of inorden method, of class TArbolBB.
     */
    @Test
    public void testGuardaEnInOrden() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, "5");
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(7, "7");
        TElementoAB<Integer> elemento3 = new TElementoAB<Integer>(4, "4");
        TElementoAB<Integer> elemento4 = new TElementoAB<Integer>(1, "1");
        TElementoAB<Integer> elemento5 = new TElementoAB<Integer>(9, "9");
        TElementoAB<Integer> elemento6 = new TElementoAB<Integer>(3, "3");

        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        arbol.insertar(elemento3);
        arbol.insertar(elemento4);
        arbol.insertar(elemento5);
        arbol.insertar(elemento6);

        Lista<Integer> expResult = new Lista();
        expResult.insertar(new Nodo<Integer>(elemento4.getEtiqueta(), elemento4.getDatos()));
        expResult.insertar(new Nodo<Integer>(elemento6.getEtiqueta(), elemento6.getDatos()));
        expResult.insertar(new Nodo<Integer>(elemento3.getEtiqueta(), elemento3.getDatos()));
        expResult.insertar(new Nodo<Integer>(elemento1.getEtiqueta(), elemento1.getDatos()));
        expResult.insertar(new Nodo<Integer>(elemento2.getEtiqueta(), elemento2.getDatos()));
        expResult.insertar(new Nodo<Integer>(elemento5.getEtiqueta(), elemento5.getDatos()));
        Lista<Integer> result = arbol.inorden();

        Nodo<Integer> nodoActualLista = expResult.getPrimero();
        Nodo<Integer> nodoActualArbol = result.getPrimero();
        boolean resultado = false;
        boolean esperado = true;
        while (nodoActualArbol != null) {
            if (nodoActualArbol.getDato().equals(nodoActualLista.getDato())) {
                resultado = true;
            } else {
                resultado = false;
                break;
            }
            nodoActualArbol = nodoActualArbol.getSiguiente();
            nodoActualLista = nodoActualLista.getSiguiente();
        }

        assertEquals(esperado, resultado);
    }

    @Test
    public void testInOrdenCantidad() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, "5");
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(7, "7");
        TElementoAB<Integer> elemento3 = new TElementoAB<Integer>(4, "4");
        TElementoAB<Integer> elemento4 = new TElementoAB<Integer>(1, "1");
        TElementoAB<Integer> elemento5 = new TElementoAB<Integer>(9, "9");
        TElementoAB<Integer> elemento6 = new TElementoAB<Integer>(3, "3");

        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        arbol.insertar(elemento3);
        arbol.insertar(elemento4);
        arbol.insertar(elemento5);
        arbol.insertar(elemento6);

        int esperado = 6;
        arbol.inorden();
        int resultado = arbol.cuentaNodos();

        assertEquals(esperado, resultado);
    }

    @Test
    public void testInOrdenVacia() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        Lista<Integer> listaArbol = arbol.inorden();
        boolean esperado = true;
        boolean resultado = listaArbol.esVacia();
        assertEquals(esperado, resultado);
    }

    @Test
    public void testInOrdenArbolUnElemento() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, "5");
        arbol.insertar(elemento1);
        Lista<Integer> listaArbol = arbol.inorden();
        int esperado = 1;
        int resultado = listaArbol.cantElementos();
        assertEquals(esperado, resultado);
    }

    @Test
    public void testNivelArbolVacio() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento = new TElementoAB<Integer>(1, 1);
        int resultado = arbol.obtenerNivelDeClave(elemento.getEtiqueta());
        int esperado = -1;
        assertEquals(esperado, resultado);
    }

    @Test
    public void testNivelArbolUnNodo() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento = new TElementoAB<Integer>(1, 1);
        arbol.insertar(elemento);
        int resultado = arbol.obtenerNivelDeClave(elemento.getEtiqueta());
        int esperado = 0;
        assertEquals(esperado, resultado);
    }

    @Test
    public void testNivelArbolDosNodos() {
        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(1, 1);
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(2, 2);
        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        int resultado = arbol.obtenerNivelDeClave(elemento2.getEtiqueta());
        int esperado = 1;
        assertEquals(esperado, resultado);
    }

    @Test
    public void TestCantidadDeHojasArbolUnicoNodo() {

        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(1, 1);
        arbol.insertar(elemento1);
        int resultado = arbol.obtenerCantidadHojas();
        int esperado = 1;
        assertEquals(esperado, resultado);

    }

    @Test
    public void TestCantidadDeHojasArbolVacio() {

        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();

        int resultado = arbol.obtenerCantidadHojas();
        int esperado = 0;
        assertEquals(esperado, resultado);

    }

    @Test
    public void TestCantidadDeHojas() {

        ArbolBinarioSucursal<Integer> arbol = new ArbolBinarioSucursal<Integer>();
        TElementoAB<Integer> elemento1 = new TElementoAB<Integer>(5, 5);
        TElementoAB<Integer> elemento2 = new TElementoAB<Integer>(3, 3);
        TElementoAB<Integer> elemento3 = new TElementoAB<Integer>(4, 4);
        TElementoAB<Integer> elemento4 = new TElementoAB<Integer>(2, 2);
        TElementoAB<Integer> elemento5 = new TElementoAB<Integer>(6, 6);
        TElementoAB<Integer> elemento6 = new TElementoAB<Integer>(9, 9);
        TElementoAB<Integer> elemento7 = new TElementoAB<Integer>(8, 8);

        arbol.insertar(elemento1);
        arbol.insertar(elemento2);
        arbol.insertar(elemento3);
        arbol.insertar(elemento4);
        arbol.insertar(elemento5);
        arbol.insertar(elemento6);
        arbol.insertar(elemento7);
        int resultado = arbol.obtenerCantidadHojas();
        int esperado = 3;
        assertEquals(esperado, resultado);
    }

}
