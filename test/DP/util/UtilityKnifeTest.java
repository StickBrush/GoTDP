package DP.util;

import DP.ED.Cola;
import DP.GameOfThrones.Dir;
import static DP.GameOfThrones.Dir.*;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas del UtilityKnife
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class UtilityKnifeTest {

    public UtilityKnifeTest() {
    }

    /**
     * Test del método nuevaCombinacion , de la clase UtilityKnife.
     */
    @Test
    public void testNuevaCombinacion() {
        int j = 1;
        Integer[] listaLlaves = new Integer[15];
        for (int i = 0; i < listaLlaves.length; i++) {
            listaLlaves[i] = j;
            j += 2;
        }
        Integer[] expResult = {15, 7, 3, 1, 5, 11, 9, 13, 23, 19, 17, 21, 27, 25, 29};
        Integer[] result = UtilityKnife.nuevaCombinacion(listaLlaves);
        assertArrayEquals(expResult, result);
    }

    /**
     * Test del método integerToDir , de la clase UtilityKnife.
     */
    @Test
    public void testIntegerToDir() {
        System.out.println("integerToDir");
        Set<Integer> s = new LinkedHashSet<>();
        Integer[] ruta = {0, 1, 7, 8, 9, 15, 21, 20, 14};
        s.addAll(Arrays.asList(ruta));
        Cola<Dir> expResult = new Cola<>();
        expResult.encolar(E);
        expResult.encolar(S);
        expResult.encolar(E);
        expResult.encolar(E);
        expResult.encolar(S);
        expResult.encolar(S);
        expResult.encolar(O);
        expResult.encolar(N);
        Cola<Dir> result = UtilityKnife.integerToDir(s);
        assertEquals(expResult.numEl(), result.numEl());
        while (!expResult.vacia()) {
            assertEquals(expResult.primero(), result.primero());
            expResult.desencolar();
            result.desencolar();
        }
    }

}
