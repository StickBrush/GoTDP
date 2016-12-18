package DP.GameOfThrones;

import DP.Personajes.Personaje;
import DP.Personajes.Stark;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas de la Sala
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class SalaTest {

    private Sala instance;

    public SalaTest() {
    }

    @Before
    public void setUp() {
        instance = new Sala(0);
    }

    /**
     * Test del método nuevaLlave , de la clase Sala.
     */
    @Test
    public void testNuevaLlaveGetLlave() {
        Llave l = new Llave(5);
        instance.nuevaLlave(l);
        assertSame(l, instance.getLlave());
        assertEquals(null, instance.getLlave());
    }

    /**
     * Test del método nuevoPersonaje , de la clase Sala.
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
        result = instance.nuevoPersonaje(p, true);
        assertEquals(expResult, result);
        assertSame(l, instance.getLlave());
    }

    /**
     * Test del método tienePersonaje , de la clase Sala.
     */
    @Test
    public void testTienePersonaje() {
        boolean expResult = false;
        boolean result = instance.tienePersonaje();
        assertEquals(expResult, result);
        Personaje p = new Stark("", 'T', 0);
        instance.nuevoPersonaje(p, false);
        assertTrue(instance.tienePersonaje());
    }

    /**
     * Test del método primero , de la clase Sala.
     */
    @Test
    public void testPrimero() {
        Personaje expResult;
        Personaje result;
        expResult = new Stark("", 'T', 0);
        instance.nuevoPersonaje(expResult, false);
        assertSame(expResult, instance.primero());
        result = new Stark("", 't', 0);
        instance.nuevoPersonaje(result, true);
        assertSame(expResult, instance.primero());
        assertNotSame(result, instance.primero());
    }

    /**
     * Test del método eliminarLlave , de la clase Sala.
     */
    @Test
    public void testEliminarLlave() {
        instance.nuevaLlave(new Llave(0));
        instance.eliminarLlave();
        assertEquals(null, instance.getLlave());
    }

    /**
     * Test del método tieneLlave , de la clase Sala.
     */
    @Test
    public void testTieneLlave() {
        boolean expResult = false;
        boolean result = instance.tieneLlave();
        assertEquals(expResult, result);
        instance.nuevaLlave(new Llave(0));
        assertTrue(instance.tieneLlave());
    }

    /**
     * Test del método desencolar , de la clase Sala.
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
     * Test del método getID , de la clase Sala.
     */
    @Test
    public void testGetID() {
        Integer expResult = 0;
        Integer result = instance.getID();
        assertEquals(expResult, result);
    }

    /**
     * Test del método setKruskal , de la clase Sala.
     */
    @Test
    public void testSetKruskal() {
        Integer nuevo = 5;
        instance.setKruskal(5);
        assertEquals(nuevo, instance.getKruskal());
    }

    /**
     * Test del método getKruskal , de la clase Sala.
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
