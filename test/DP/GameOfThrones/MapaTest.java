/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

import DP.ED.Grafo;
import DP.ED.List;
import DP.Exceptions.MapSizeException;
import DP.Personajes.Personaje;
import DP.Personajes.Stark;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author StickBrush
 */
public class MapaTest {
    private MapaForTesting instance;
    
    public MapaTest() {
    }

    @Before
    public void setUp(){
        instance.forceNull();
    }
    /**
     * Test of getInstance method, of class Mapa.
     */
    @Test
    public void testGetInstance() throws MapSizeException {
        int salaPuerta = 0;
        int X = 0;
        int Y = 0;
        int profComb = 0;
        Mapa expResult = null;
        Mapa result = Mapa.getInstance(salaPuerta, X, Y, profComb);
        assertNotEquals(expResult, result);
        expResult=Mapa.getInstance(salaPuerta, X, Y, profComb);
        assertSame(expResult, result);
    }

    /**
     * Test of esAccesible method, of class Mapa.
     */
    @Test
    public void testEsAccesible() throws MapSizeException {
        int IDS1 = 0;
        int IDS2 = 1;
        Mapa instance=Mapa.getInstance(5, 5, 5, 5);
        boolean expResult = false;
        boolean result = instance.esAccesible(IDS1, IDS2);
        assertEquals(expResult, result);
    }

    /**
     * Test of getLaberinto method, of class Mapa.
     */
    @Test
    public void testGetLaberintoActualizado() throws MapSizeException {
        System.out.println("getLaberinto");
        Mapa instance = Mapa.getInstance(5, 5, 5, 5);
        Grafo expResult = null;
        Grafo result = instance.getLaberintoActualizado();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of distribuirLlaves method, of class Mapa.
     */
    @Test
    public void testDistribuirLlaves() throws MapSizeException {
        Mapa instance = Mapa.getInstance(5, 5, 5, 5);
        instance.distribuirLlaves();
        assertFalse(instance.getSala(0, 0).tieneLlave());
    }
    /**
     * Test of simularTurno method, of class Mapa.
     */
    @Test
    public void testSimularTurno() {
        System.out.println("simularTurno");
        Mapa instance = null;
        instance.simularTurno();
        fail("The test case is a prototype.");
    }

    /**
     * Test of puertaAbierta method, of class Mapa.
     */
    @Test
    public void testPuertaAbierta() {
        System.out.println("puertaAbierta");
        Mapa instance = null;
        boolean expResult = false;
        boolean result = instance.puertaAbierta();
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    /**
     * Test of insertarPersonaje method, of class Mapa.
     */
    @Test
    public void testInsertarPersonaje() throws MapSizeException {
        System.out.println("insertarPersonaje");
        Personaje p = new Stark("", 'T', 0);
        Mapa instance = Mapa.getInstance(5, 5, 5, 5);        
        instance.insertarPersonaje(p);
        assertTrue(instance.getSala(0, 0).tienePersonaje());
    }
    /**
     * Test of dumpPersonajes method, of class Mapa.
     */
    @Test
    public void testDumpPersonajes() throws MapSizeException {
        Mapa instance = Mapa.getInstance(5, 5, 5, 5);
        instance.nuevoPersonaje(new Stark("", 'T', 0));
        instance.dumpPersonajes();
        assertTrue(instance.getSala(0, 0).tienePersonaje());
    }
    
}
