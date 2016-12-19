package DP.Personajes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Targaryen
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class TargaryenTest {

    public TargaryenTest() {
    }

    /**
     * Test del método autoRuta , de la clase Targaryen.
     */
    @Test
    public void testAutoRuta() {
        Targaryen instance = new Targaryen("", 'T', 0);
        instance.autoRuta();
        String expResult = "(ruta:T:E E S O O E S O E N E S E S O O O S E O N E E S N E S S O O O E E E E O N E E S )\n";
        String result = instance.ruta();
        assertEquals(expResult, result);
    }

}
