package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Puerta;
import DP.GameOfThrones.Sala;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del Atacante
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class AtacanteTest {

    public AtacanteTest() {
    }

    /**
     * Test del método interactuarPuerta , de la clase Atacante.
     */
    @Test
    public void testInteractuarPuerta() throws Exception {
        Mapa.getInstance().insertarPuerta(Puerta.getInstance());
        Puerta p = Puerta.getInstance();
        Llave l = new Llave(0);
        Llave[] combinacion = {l};
        p.configurar(combinacion);
        Atacante instance = new AtacanteImpl();
        Sala x = new Sala(-10);
        x.nuevaLlave(l);
        instance.interactuarSala(x);
        boolean expResult = false;
        boolean result = instance.interactuarPuerta();
        assertEquals(expResult, result);
        assertTrue(p.estaAbierta());
    }

    /**
     * Test del método interactuarSala , de la clase Atacante.
     */
    @Test
    public void testInteractuarSala() throws MovementException {
        Sala s = new Sala(-10);
        s.nuevaLlave(new Llave(0));
        Atacante instance = new AtacanteImpl();
        instance.interactuarSala(s);
        Mapa.getInstance().insertarPuerta(Puerta.getInstance());
        instance.interactuarPuerta();
        assertEquals(Puerta.getInstance().llavesProb(), 1);
    }

    /**
     * Test del método init , de la clase Atacante.
     */
    @Test
    public void testInit() {
        Atacante instance = new AtacanteImpl();
        Integer expResult = 0;
        Integer result = instance.init();
        assertEquals(expResult, result);
    }

    /**
     * Test del método toString , de la clase Atacante.
     */
    @Test
    public void testToString() {
        Atacante instance = new AtacanteImpl();
        Sala x = new Sala(-10);
        x.nuevaLlave(new Llave(0));
        instance.interactuarSala(x);
        String expResult = ": :0 ";
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    public class AtacanteImpl extends Atacante {

        public AtacanteImpl() {
            super("", "", ' ', 0);
        }

        @Override
        public void autoRuta() {
            throw new UnsupportedOperationException();
        }
    }

}
