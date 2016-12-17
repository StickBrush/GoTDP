package DP.util;

import DP.ED.Cola;
import DP.ED.Grafo;
import java.util.Set;
import java.util.Iterator;
import DP.ED.List;
import DP.GameOfThrones.Dir;
import java.util.Arrays;

/**
 * Clase Utility, tiene toda clase de utilidades para otras clases. No es
 * instanciable
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public abstract class UtilityKnife {

    /**
     * Método para reordenar la combinación de llaves
     *
     * @param lista Combinación de llaves a reordenar
     * @return Combinación reordenada
     */
    public static Integer[] nuevaCombinacion(Integer[] lista) {
        Integer[] aux = new Integer[lista.length];
        comb(lista, 0, aux);
        return aux;
    }

    /**
     * Método auxiliar para reordenar un vector
     *
     * @param lista Vector a reordenar
     * @param i Posición para introducir el dato en mitad del vector
     * @param aux Vector reordenado
     * @return Siguiente posición libre de aux
     */
    private static int comb(Integer[] lista, int i, Integer[] aux) {
        if (lista.length == 1) {
            aux[i] = lista[0];
            i++;
        } else {
            Integer[] divIz = new Integer[(lista.length) / 2];
            Integer[] divDer = new Integer[(lista.length) / 2];
            int mitad = lista.length / 2;
            for (int x = 0; x < mitad; x++) {
                divIz[x] = lista[x];
                divDer[x] = lista[x + mitad + 1];
            }
            aux[i] = lista[mitad];
            i++;
            i = comb(divIz, i, aux);
            i = comb(divDer, i, aux);
        }
        return i;
    }

    public static Cola<Dir> integerToDir(Set<Integer> s) {
        Cola<Dir> result = new Cola<>();
        Iterator<Integer> i = s.iterator();
        Integer last = i.next();
        while (i.hasNext()) {
            Integer act = i.next();
            if (last - act == -1) {
                result.encolar(Dir.E);
            } else if (last - act == 1) {
                result.encolar(Dir.O);
            } else if (last - act < -1) {
                result.encolar(Dir.S);
            } else {
                result.encolar(Dir.N);
            }
            last=act;
        }
        return result;
    }
}
