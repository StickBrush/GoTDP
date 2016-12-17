/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.Personajes;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class StarkTest {
    
    public StarkTest() {
    }

    /**
     * Test of autoRuta method, of class Stark.
     */
    @Test
    public void testAutoRuta() {
        Stark instance = new Stark("", 'T', 0);
        instance.autoRuta();
        String expResult="(ruta:T:E E S S E S S E E S )\n";
        String result=instance.ruta();
        assertEquals(expResult, result);
    }
    
}
