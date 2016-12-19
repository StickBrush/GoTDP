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
        String expResult = "Lannister:T:0 1 1 2 3 3 4 5 5 6 7 7 8 9 9 10 11 11 12 13 13 14 15 15 16 17 17 18 19 19 20 21 21 22 23 23 24 25 25 26 27 27 28 29 29 ";
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
        String res=instance.ruta();
        String expResult="(ruta:T:N O O N E N N E N S O S S O N O N N O O E E S S E S S S O O O E E E N E E S )\n";
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
        String res=instance.ruta();
        String expRes="(ruta:T:S E E )\n";
        assertEquals(expRes, res);
    }
    
}
