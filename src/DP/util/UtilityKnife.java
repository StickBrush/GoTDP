package DP.util;

import DP.ED.Cola;
import DP.ED.Grafo;
import java.util.Set;
import java.util.Iterator;
import DP.ED.List;
import DP.GameOfThrones.Dir;
import java.util.LinkedHashSet;
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
     * Devuelve, ordenadas por frecuencia, las salas de mayor tránsito
     *
     * @param kingsLanding Identificador de King's Landing
     * @param laberinto Laberinto de salas
     * @param size Tamaño total del mapa
     * @return Lista de salas ordenadas por frecuencia de tránsito
     */
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

    /**
     * Devuelve una copia del Set dado
     *
     * @param original Set original
     * @return Copia de original
     */
    public static Set<Integer> setCopy(Set<Integer> original) {
        Set<Integer> nuevo = new LinkedHashSet<>();
        nuevo.addAll(original);
        return nuevo;
    }

    /**
     * Devuelve si hay pared o no
     *
     * @param g Grafo sobre el que comprobar
     * @param paredes Identificadores de las salas a comprobar
     * @return True si hay paredes internas entre las salas, false si no
     */
    public static boolean hayPared(Grafo g, int[] paredes) {
        boolean pared;
        Arrays.sort(paredes);
        pared = !g.adyacente(paredes[0], paredes[1]);
        pared = pared || !g.adyacente(paredes[0], paredes[2]);
        pared = pared || !g.adyacente(paredes[1], paredes[3]);
        pared = pared || !g.adyacente(paredes[2], paredes[3]);
        return pared;
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
