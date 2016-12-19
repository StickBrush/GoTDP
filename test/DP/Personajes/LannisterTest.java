package DP.Personajes;

import DP.GameOfThrones.Dir;
import static DP.GameOfThrones.Dir.*;
import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class LannisterTest {

    public LannisterTest() {
    }

    /**
     * Test del método init , de la clase Lannister.
     */
    @Test
    public void testInit() {
        Lannister instance = new Lannister("", 'T', 0);
        Integer expResult = 35;
        Integer result = instance.init();
        assertEquals(expResult, result);
    }

    /**
     * Test del método interactuarSala , de la clase Lannister.
     */
    @Test
    public void testInteractuarSala() {
        Sala s = new Sala(-1);
        Lannister instance = new Lannister("", 'T', 0);
        assertFalse(s.tieneLlave());
        instance.interactuarSala(s);
        assertTrue(s.tieneLlave());
    }

    /**
     * Test del método toString , de la clase Lannister.
     */
    @Test
    public void testToString() {
        Lannister instance = new Lannister("", 'T', 0);
        String expResult = "Lannister:T:29 27 25 23 21 19 17 15 13 11 9 7 5 3 1 ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test del método autoRuta , de la clase Lannister.
     */
    @Test
    public void testAutoRuta() {
        Lannister instance = new Lannister("", 'T', 0);
        instance.autoRuta();
        String res = instance.ruta();
        String expResult = "(ruta:T:N O O N E N N E N S O S S O N O N N O O E E S S E S S S O O O E E E N E E S )\n";
        assertEquals(expResult, res);
    }

    /**
     * Test del método mover , de la clase Lannister.
     */
    @Test
    public void testMover() throws Exception {
        int i = 0;
        int j = 0;
        Lannister instance = new Lannister("", 'T', 0);
        boolean expResult = true;
        Dir[] ruta = {E, S, E};
        instance.setRuta(ruta);
        boolean result = instance.mover(i, j);
        assertEquals(expResult, result);
        String res = instance.ruta();
        String expRes = "(ruta:T:S E E )\n";
        assertEquals(expRes, res);
    }

}
