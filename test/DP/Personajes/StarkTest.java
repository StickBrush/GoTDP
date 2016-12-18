package DP.Personajes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Stark
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class StarkTest {

    public StarkTest() {
    }

    /**
     * Test del método autoRuta , de la clase Stark.
     */
    @Test
    public void testAutoRuta() {
        Stark instance = new Stark("", 'T', 0);
        instance.autoRuta();
        String expResult = "(ruta:T:E E S S E S S E E S )\n";
        String result = instance.ruta();
        assertEquals(expResult, result);
    }

}
