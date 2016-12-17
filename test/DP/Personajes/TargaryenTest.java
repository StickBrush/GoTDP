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
public class TargaryenTest {
    
    public TargaryenTest() {
    }

    /**
     * Test of autoRuta method, of class Targaryen.
     */
    @Test
    public void testAutoRuta() {
        Targaryen instance = new Targaryen("", 'T', 0);
        instance.autoRuta();
        String expResult="(ruta:T:E E S O O E S O E N E S E S O O O S E O N E E S N E S S O O O E E E E O N E E S )\n";
        String result=instance.ruta();
        assertEquals(expResult, result);
    }
    
}
