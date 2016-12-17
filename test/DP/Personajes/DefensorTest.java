/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.Personajes;

import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.PuertaForTesting;
import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class DefensorTest {
    
    public DefensorTest() {
    }

    /**
     * Test of interactuarPuerta method, of class Defensor.
     */
    @Test
    public void testInteractuarPuerta() throws Exception {
        Mapa.getInstance().insertarPuerta(PuertaForTesting.getInstance());
        PuertaForTesting.getInstance().forceOpen();
        Llave[] l={new Llave(0)};
        PuertaForTesting.getInstance().configurar(l);
        int i = 5;
        int j = 5;
        Defensor instance = new DefensorImpl();
        boolean expResult = false;
        boolean result = instance.interactuarPuerta(i, j);
        assertEquals(expResult, result);
        assertFalse(PuertaForTesting.getInstance().estaAbierta());
    }
    
    
    public class DefensorImpl extends Defensor {

        public DefensorImpl() {
            super("", "", ' ', 0);
        }

        @Override
        public Integer init() {
            return null;
        }

        @Override
        public void autoRuta() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void interactuarSala(Sala s) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
    
}
