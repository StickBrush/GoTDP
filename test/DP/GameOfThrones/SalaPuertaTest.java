/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

import DP.ED.Arbol;
import DP.Personajes.Personaje;
import DP.Personajes.Stark;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Solaire
 */
public class SalaPuertaTest {

    private SalaPuerta instance;

    public SalaPuertaTest() {
    }

    @Before
    public void setUp() {
        instance = new SalaPuerta(0);
    }

    /**
     * Test of insertarPuerta method, of class SalaPuerta.
     */
    @Test
    public void testInsertarPuerta() {
        Puerta p = PuertaForTesting.getInstance();
        instance.insertarPuerta(p);
        assertSame(p, instance.getPuerta());
    }

    /**
     * Test of getPuerta method, of class SalaPuerta.
     */
    @Test
    public void testGetPuerta() {
        Puerta expResult = null;
        Puerta result = instance.getPuerta();
        assertEquals(expResult, result);
        result = PuertaForTesting.getInstance();
        instance.insertarPuerta(result);
        assertSame(result, instance.getPuerta());
    }

    /**
     * Test of nuevoPersonaje method, of class SalaPuerta.
     */
    @Test
    public void testNuevoPersonaje() {
        Personaje pe = new Stark("", 'T', 0);
        boolean reinsert = false;
        Puerta p = PuertaForTesting.getInstance();
        instance.insertarPuerta(p);
        boolean expResult = false;
        boolean result = instance.nuevoPersonaje(pe, reinsert);
        assertEquals(expResult, result);
        PuertaForTesting.getInstance().forceOpen();
        assertTrue(instance.nuevoPersonaje(pe, reinsert));
    }

}
