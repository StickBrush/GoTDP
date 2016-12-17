/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Solaire
 */
public class PersonajeTest {
    
    public PersonajeTest() {
    }

    /**
     * Test of mover method, of class Personaje.
     */
    @Test
    public void testMover() throws Exception {
        int i = 0;
        int j = 0;
        int turno = 6;
        Personaje instance = new PersonajeImpl();
        Mapa.getInstance().nuevoPersonaje(instance);
        Mapa.getInstance().dumpPersonajes();
        boolean expResult = true;
        boolean result = instance.mover(i, j, turno);
        assertEquals(expResult, result);
        assertTrue(Mapa.getInstance().getSala(i, j+1).tienePersonaje());
    }
    
    /**
     * Test of toString method, of class Personaje.
     */
    @Test
    public void testToString() {
        Personaje instance = new PersonajeImpl();
        String expResult = ": ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test of ruta method, of class Personaje.
     */
    @Test
    public void testRuta() {
        Personaje instance = new PersonajeImpl();
        Dir[] ruta = {Dir.E, Dir.S};
        instance.setRuta(ruta);
        String expResult = "(ruta: :E S )\n";
        String result = instance.ruta();
        assertEquals(expResult, result);
    }

    public class PersonajeImpl extends Personaje {

        public PersonajeImpl() {
            super("", "", ' ', 0);
        }

        public void autoRuta() {
            Dir[] ruta={Dir.E};
            setRuta(ruta);
        }

        public void interactuarSala(Sala s) {
        }

        public boolean interactuarPuerta(int i, int j) throws MovementException {
            return false;
        }

        public Integer init() {
            return 0;
        }
    }
    
}
