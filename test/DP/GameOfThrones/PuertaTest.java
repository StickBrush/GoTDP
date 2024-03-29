package DP.GameOfThrones;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas de la Puerta
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class PuertaTest {

    private PuertaForTesting instance;

    @Before
    public void setUp() {
        PuertaForTesting.forceNull();
        instance = PuertaForTesting.getInstance();
    }

    /**
     * Test del método setAltura , de la clase Puerta.
     */
    @Test
    public void testSetAltura() {
        int altura = 0;
        instance.setAltura(altura);
        assertEquals(altura, instance.getAltura());
    }

    /**
     * Test del método cerrar , de la clase Puerta.
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
     * Test del método abrir , de la clase Puerta.
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
     * Test del método llavesCerr , de la clase Puerta.
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
     * Test del método llavesProb , de la clase Puerta.
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
