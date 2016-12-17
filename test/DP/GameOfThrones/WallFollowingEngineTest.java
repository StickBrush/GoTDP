/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

import DP.ED.Cola;
import DP.Exceptions.MapSizeException;
import static DP.GameOfThrones.Dir.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class WallFollowingEngineTest {
    
    public WallFollowingEngineTest() {
    }

    /**
     * Test of wallFollower method, of class WallFollowingEngine.
     */
    @Test
    public void testWallFollower() throws MapSizeException {
        int kingsLanding = 8;
        Mapa.getInstance(8, 3, 3, 5);
        WallFollowingEngine instance = new WallFollowingEngine();
        Cola<Dir> expResult = new Cola<>();
        expResult.encolar(E);
        expResult.encolar(S);
        expResult.encolar(O);
        expResult.encolar(S);
        expResult.encolar(E);
        expResult.encolar(E);
        Cola<Dir> result = instance.wallFollower(kingsLanding);
        while(!result.vacia()){
            assertEquals(expResult.primero(), result.primero());
            expResult.desencolar();
            result.desencolar();
        }
    }
    
}
