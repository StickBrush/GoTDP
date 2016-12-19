package DP.GameOfThrones;

import DP.ED.Cola;
import DP.Exceptions.MapSizeException;
import static DP.GameOfThrones.Dir.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del WallFollowingEngine
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class WallFollowingEngineTest {

    public WallFollowingEngineTest() {
    }

    /**
     * Test del método wallFollower , de la clase WallFollowingEngine.
     */
    @Test
    public void testWallFollower() throws MapSizeException {
        Mapa.getInstance(8, 3, 3, 5);
        WallFollowingEngine instance = new WallFollowingEngine();
        Cola<Dir> expResult = new Cola<>();
        expResult.encolar(E);
        expResult.encolar(S);
        expResult.encolar(O);
        expResult.encolar(S);
        expResult.encolar(E);
        expResult.encolar(E);
        Cola<Dir> result = instance.wallFollower();
        while (!result.vacia()) {
            assertEquals(expResult.primero(), result.primero());
            expResult.desencolar();
            result.desencolar();
        }
    }

}
