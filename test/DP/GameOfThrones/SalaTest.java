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
public class SalaTest {
    
    private Sala instance;
    public SalaTest() {
    }
    
    @Before
    public void setUp(){
        instance=new Sala(0);
    }

    /**
     * Test of nuevaLlave method, of class Sala.
     */
    @Test
    public void testNuevaLlaveGetLlave() {
        Llave l = new Llave(5);
        instance.nuevaLlave(l);
        assertSame(l, instance.getLlave());
        assertEquals(null, instance.getLlave());
    }

    /**
     * Test of nuevoPersonaje method, of class Sala.
     */
    @Test
    public void testNuevoPersonaje() {
        Personaje p = new Stark("", 'T', 0);
        instance.nuevaLlave(new Llave(0));
        boolean reinsert = false;
        boolean expResult = false;
        boolean result = instance.nuevoPersonaje(p, reinsert);
        assertEquals(expResult, result);
        assertEquals(null, instance.getLlave());
        Llave l = new Llave(0);
        instance.nuevaLlave(l);
        result=instance.nuevoPersonaje(p, true);
        assertEquals(expResult, result);
        assertSame(l, instance.getLlave());
    }

    /**
     * Test of tienePersonaje method, of class Sala.
     */
    @Test
    public void testTienePersonaje() {
        System.out.println("tienePersonaje");
        boolean expResult = false;
        boolean result = instance.tienePersonaje();
        assertEquals(expResult, result);
        Personaje p = new Stark("", 'T', 0);
        instance.nuevoPersonaje(p, false);
        assertTrue(instance.tienePersonaje());
    }

    /**
     * Test of primero method, of class Sala.
     */
    @Test
    public void testPrimero() {
        System.out.println("primero");
        Personaje expResult;
        Personaje result;
        expResult=new Stark("", 'T', 0);
        instance.nuevoPersonaje(expResult, false);
        assertSame(expResult, instance.primero());
        result=new Stark("", 't', 0);
        instance.nuevoPersonaje(result, true);
        assertSame(expResult, instance.primero());
        assertNotSame(result, instance.primero());
    }

    /**
     * Test of eliminarLlave method, of class Sala.
     */
    @Test
    public void testEliminarLlave() {
        System.out.println("eliminarLlave");
        instance.nuevaLlave(new Llave(0));
        instance.eliminarLlave();
        assertEquals(null, instance.getLlave());
    }

    /**
     * Test of tieneLlave method, of class Sala.
     */
    @Test
    public void testTieneLlave() {
        System.out.println("tieneLlave");
        boolean expResult = false;
        boolean result = instance.tieneLlave();
        assertEquals(expResult, result);
        instance.nuevaLlave(new Llave(0));
        assertTrue(instance.tieneLlave());
    }

    /**
     * Test of desencolar method, of class Sala.
     */
    @Test
    public void testDesencolar() {
        instance.nuevoPersonaje(new Stark("", 'T', 0), false);
        Personaje p = new Stark("", 't', 0);
        instance.nuevoPersonaje(p, false);
        instance.desencolar();
        assertEquals(p, instance.primero());
    }

    /**
     * Test of getID method, of class Sala.
     */
    @Test
    public void testGetID() {
        System.out.println("getID");
        Integer expResult = 0;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test of simular method, of class Sala.
     */
    @Test
    public void testSimular() {
        fail("TODO");
    }

    /**
     * Test of setKruskal method, of class Sala.
     */
    @Test
    public void testSetKruskal() {
        Integer nuevo = 5;
        instance.setKruskal(5);
        assertEquals(nuevo, instance.getKruskal());
    }

    /**
     * Test of getKruskal method, of class Sala.
     */
    @Test
    public void testGetKruskal() {
        Integer expResult = 0;
        Integer result = instance.getKruskal();
        assertEquals(expResult, result);
        instance.setKruskal(5);
        assertEquals(Integer.valueOf(5), instance.getKruskal());
    }
    
}
