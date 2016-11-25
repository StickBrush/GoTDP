package DP.util;

import DP.ED.Grafo;
import java.util.Set;
import java.util.Iterator;
import DP.ED.List;
import java.util.LinkedHashSet;

/**
 *
 * @author Solaire
 */
public class UtilityKnife {

    public static List<Integer> sortByFrequence(int kingsLanding, Grafo laberinto, int size) {
        Set<Set<Integer>> caminos = laberinto.caminos(0, kingsLanding);
        int[] freq = new int[size];
        List<Integer> sorted = new List<>();
        Iterator<Set<Integer>> it = caminos.iterator();
        while (it.hasNext()) {
            Iterator<Integer> iter = it.next().iterator();
            while (iter.hasNext()) {
                freq[iter.next()]++;
            }
        }
        boolean fin = false;
        for (int i = 0; i < freq.length && !fin; i++) {
            int inMay = 0;
            int may = freq[0];
            for (int j = 0; j < freq.length; j++) {
                if (freq[j] > may) {
                    inMay = j;
                    may = freq[j];
                }
            }
            if (may != -1) {
                if(inMay!=kingsLanding && inMay!=0)
                    sorted.addLast(inMay);
                freq[inMay] = -1;
            } else {
                fin = true;
            }
        }
        return sorted;
    }

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
    
        public static Set<Integer> setCopy(Set<Integer> original) {
        Set<Integer> nuevo = new LinkedHashSet<>();
        nuevo.addAll(original);
        return nuevo;
    }
}
