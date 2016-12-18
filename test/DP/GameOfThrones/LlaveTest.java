package DP.GameOfThrones;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas de la Llave
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class LlaveTest {

    private Llave instance;

    @Before
    public void setUp() {
        instance = new Llave(115);
    }

    /**
     * Prueba del método identificar , de la clase Llave.
     */
    @Test
    public void testIdentificar() {
        Integer expResult = 115;
        Integer result = instance.identificar();
        assertEquals(expResult, result);
    }

    /**
     * Prueba del método compareTo , de la clase Llave.
     */
    @Test
    public void testCompareTo() {
        Llave t = new Llave(115);
        int expResult = 0;
        int result = instance.compareTo(t);
        assertEquals(expResult, result);
        t = new Llave(7);
        result = instance.compareTo(t);
        assertTrue(result > 0);
        t = new Llave(200);
        result = instance.compareTo(t);
        assertTrue(result < 0);
    }

    /**
     * Prueba del método equals , de la clase Llave.
     */
    @Test
    public void testEquals() {
        Object t = null;
        assertFalse(instance.equals(t));
        t = new Llave(0);
        assertFalse(instance.equals(t));
        t = new Llave(115);
        assertTrue(instance.equals(t));

    }

    /**
     * Prueba del método toString , de la clase Llave.
     */
    @Test
    public void testToString() {
        String expResult = "115";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

}
