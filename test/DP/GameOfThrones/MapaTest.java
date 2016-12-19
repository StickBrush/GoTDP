package DP.GameOfThrones;

import DP.ED.List;
import DP.Exceptions.MapSizeException;
import DP.Personajes.Personaje;
import DP.Personajes.Stark;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 * Pruebas del Mapa
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class MapaTest extends Mapa {

    private MapaTest(int a, int b, int c, int d) throws MapSizeException {
        super(a, b, c, d);
    }

    public MapaTest() {
    }

    @Before
    public void setUp() {
        instance = null;
    }

    /**
     * Test del método getInstance , de la clase Mapa.
     */
    @Test
    public void testGetInstance_4args() {
        int salaPuerta = 0;
        int X = 0;
        int Y = 0;
        int profComb = 0;
        boolean expResult = false;
        try {
            Mapa result = Mapa.getInstance(salaPuerta, X, Y, profComb);
        } catch (MapSizeException ex) {
            expResult = true;
        }
        assertTrue(expResult);
    }

    /**
     * Test del método getInstance , de la clase Mapa.
     */
    @Test
    public void testGetInstance_0args() {
        Mapa expResult = null;
        try {
            expResult = Mapa.getInstance(35, 6, 6, 4);
        } catch (MapSizeException ex) {
            fail("Excepción en argumentos controlados");
        }
        Mapa result = Mapa.getInstance();
        assertEquals(expResult, result);
    }

    /**
     * Test del método nuevoPersonaje , de la clase Mapa.
     */
    @Test
    public void testNuevoPersonaje() {
        Personaje p = new Stark("", 'T', 0);
        MapaTest.getInstance();
        instance.nuevoPersonaje(p);
        assertSame(p, instance.personajes.get(0));
    }

    /**
     * Test del método esAccesible , de la clase Mapa.
     */
    @Test
    public void testEsAccesible() {
        int IDS1 = 0;
        int IDS2 = 1;
        MapaTest.getInstance();
        instance.getLaberintoActualizado().nuevoArco(0, 1);
        boolean expResult = true;
        boolean result = instance.esAccesible(IDS1, IDS2);
        assertEquals(expResult, result);
        expResult = false;
        instance.getLaberintoActualizado().borraArco(0, 1);
        result = instance.esAccesible(IDS1, IDS2);
        assertEquals(expResult, result);
    }

    /**
     * Test del método distribuirLlaves , de la clase Mapa.
     */
    @Test
    public void testDistribuirLlaves() {
        MapaTest.getInstance();
        instance.distribuirLlaves();
        List<Integer> salas = new List<>();
        salas.addLast(0);
        salas.addLast(1);
        salas.addLast(2);
        salas.addLast(8);
        salas.addLast(14);
        salas.addLast(15);
        salas.addLast(21);
        salas.addLast(27);
        salas.addLast(28);
        int x = 0;
        for (int i = 0; i < instance.getTamY(); i++) {
            for (int j = 0; j < instance.getTamX(); j++) {
                if (salas.contains(x)) {
                    assertTrue(instance.getSala(i, j).tieneLlave());
                } else {
                    assertFalse(instance.getSala(i, j).tieneLlave());
                }
                x++;
            }
        }
    }

    /**
     * Test del método dumpPersonajes , de la clase Mapa.
     */
    @Test
    public void testDumpPersonajes() {
        MapaTest.getInstance();
        Personaje p = new Stark("", 'T', 0);
        instance.nuevoPersonaje(p);
        instance.dumpPersonajes();
        assertSame(p, instance.getSala(0, 0).primero());
    }

}
