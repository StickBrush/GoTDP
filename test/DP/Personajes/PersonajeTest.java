package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Personaje
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class PersonajeTest {

    public PersonajeTest() {
    }

    /**
     * Test del método mover , de la clase Personaje.
     */
    @Test
    public void testMover() throws Exception {
        int i = 0;
        int j = 0;
        Personaje instance = new PersonajeImpl();
        Mapa.getInstance().nuevoPersonaje(instance);
        Mapa.getInstance().dumpPersonajes();
        boolean expResult = true;
        boolean result = instance.mover(i, j);
        assertEquals(expResult, result);
        assertTrue(Mapa.getInstance().getSala(i, j + 1).tienePersonaje());
    }

    /**
     * Test del método toString , de la clase Personaje.
     */
    @Test
    public void testToString() {
        Personaje instance = new PersonajeImpl();
        String expResult = ": ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    /**
     * Test del método ruta , de la clase Personaje.
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
            Dir[] ruta = {Dir.E};
            setRuta(ruta);
        }

        public void interactuarSala(Sala s) {
        }

        public Integer init() {
            return 0;
        }

        @Override
        public boolean interactuarPuerta() throws MovementException {
            return false;
        }
    }

}
