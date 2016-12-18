package DP.GameOfThrones;

import DP.Personajes.Personaje;
import DP.Personajes.Stark;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas de la SalaPuerta
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
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
     * Test del método insertarPuerta , de la clase SalaPuerta.
     */
    @Test
    public void testInsertarPuerta() {
        Puerta p = PuertaForTesting.getInstance();
        instance.insertarPuerta(p);
        assertSame(p, instance.getPuerta());
    }

    /**
     * Test del método getPuerta , de la clase SalaPuerta.
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
     * Test del método nuevoPersonaje , de la clase SalaPuerta.
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
