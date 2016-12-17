/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.Personajes;

import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class CaminanteBlancoTest {
    
    public CaminanteBlancoTest() {
    }

    /**
     * Test of kill method, of class CaminanteBlanco.
     */
    @Test
    public void testKill() {
        Personaje p = new CaminanteBlanco("", ' ', 0);
        Personaje s = new Stark("", 'T', 0);
        CaminanteBlanco instance = new CaminanteBlanco("", 't', 0);
        instance.kill(p);
        instance.kill(s);
        String res = instance.toString();
        String expResult="Caminante Blanco:t:T   ";
        assertEquals(res, expResult);
    }

    /**
     * Test of interactuarSala method, of class CaminanteBlanco.
     */
    @Test
    public void testInteractuarSala() {
        Sala s = new Sala(-10);
        CaminanteBlanco instance = new CaminanteBlanco("", 'T', 0);
        Personaje dead = new Stark("", 'D', 0);
        s.nuevoPersonaje(dead, false);
        instance.interactuarSala(s);
        String res = instance.toString();
        String expResult="Caminante Blanco:T:D ";
        assertEquals(res, expResult);
        s.nuevoPersonaje(instance, false);
        instance.interactuarSala(s);
        res=instance.toString();
        assertEquals(res, expResult);
    }

    /**
     * Test of init method, of class CaminanteBlanco.
     */
    @Test
    public void testInit() {
        CaminanteBlanco instance = new CaminanteBlanco("", 'T', 0);
        Integer expResult = 30;
        Integer result = instance.init();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class CaminanteBlanco.
     */
    @Test
    public void testToString() {
        CaminanteBlanco instance = new CaminanteBlanco("", 'T', 0);
        String expResult = "Caminante Blanco:T:";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of autoRuta method, of class CaminanteBlanco.
     */
    @Test
    public void testAutoRuta() {
        CaminanteBlanco instance = new CaminanteBlanco("", 'T', 0);
        instance.autoRuta();
        String res=instance.ruta();
        String expResult="(ruta:T:E E E N N N O N N O O E E S S E S E N N E N S O S S O S E E S N O O S O O O )\n";
        assertEquals(res, expResult);
    }
    
}
