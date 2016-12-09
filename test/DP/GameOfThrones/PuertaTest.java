/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Solaire
 */
public class PuertaTest {

    private PuertaForTesting instance;

    @Before
    public void setUp() {
        instance = PuertaForTesting.getInstance();
    }

    /**
     * Test of setAltura method, of class Puerta.
     */
    @Test
    public void testSetAltura() {
        int altura = 0;
        instance.setAltura(altura);
        assertEquals(altura, instance.getAltura());
    }

    /**
     * Test of cerrar method, of class Puerta.
     */
    @Test
    public void testCerrar() {
        Llave[] comb = {};
        instance.configurar(comb);
        instance.forceOpen();
        assertTrue(instance.estaAbierta());
        instance.cerrar();
        assertFalse(instance.estaAbierta());
    }

    /**
     * Test of abrir method, of class Puerta.
     */
    @Test
    public void testAbrir() {
        Llave[] comb = {new Llave(15), new Llave(7), new Llave(3), new Llave(1), new Llave(5), new Llave(11), new Llave(9), new Llave(13), new Llave(23), new Llave(19), new Llave(17), new Llave(21), new Llave(27), new Llave(25), new Llave(29)};
        instance.configurar(comb);
        instance.setAltura(4);
        assertFalse("La puerta se abre de inicio", instance.estaAbierta());
        for (Llave comb1 : comb) {
            instance.abrir(comb1);
        }
        assertTrue("La puerta no se abre", instance.estaAbierta());
    }

    /**
     * Test of llavesCerr method, of class Puerta.
     */
    @Test
    public void testLlavesCerr() {
        int expResult = 0;
        int result = instance.llavesCerr();
        assertEquals(expResult, result);
        Llave[] comb = {new Llave(0), new Llave(5), new Llave(25)};
        instance.configurar(comb);
        expResult = 3;
        result = instance.llavesCerr();
        assertEquals(expResult, result);
    }

    /**
     * Test of llavesProb method, of class Puerta.
     */
    @Test
    public void testLlavesProb() {
        int expResult = 0;
        int result = instance.llavesProb();
        assertEquals(expResult, result);
        Llave[] comb = {new Llave(0), new Llave(4)};
        instance.configurar(comb);
        instance.abrir(new Llave(0));
        instance.abrir(new Llave(4));
        expResult = 2;
        result = instance.llavesProb();
        assertEquals(expResult, result);
    }

}
