package DP.ED;

import java.util.Queue;
import java.util.PriorityQueue;

/**
 * Implementación de la Lista Ordenada
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 * @param <tipoDato> Tipo de dato de la lista ordenada. Debe ser comparable.
 */
public class ListaOrdenada<tipoDato> {

    /**
     * Cola de prioridad usada como lista ordenada
     */
    private Queue<tipoDato> l;

    /**
     * Constructor por defecto de la lista ordenada
     */
    public ListaOrdenada() {
        l = new PriorityQueue<tipoDato>();
    }

    /**
     * Devuelve si la lista está vacía
     *
     * @return True si está vacía, false si no
     */
    public boolean estaVacia() {
        return l.isEmpty();
    }

    /**
     * Devuelve el primer elemento
     *
     * @return Primer elemento de la lista
     */
    public tipoDato getFirst() {
        return l.element();
    }

    /**
     * Constructor parametrizado de la Lista Ordenada
     *
     * @param data Dato con el que crear la lista
     */
    public ListaOrdenada(tipoDato data) {
        l = new PriorityQueue<tipoDato>();
        l.add(data);
    }

    /**
     * Añade un dato en orden
     *
     * @param data Dato a añadir
     */
    public void add(tipoDato data) {
        l.add(data);
    }

    /**
     * Busca y elimina un dato en la lista
     *
     * @param d Dato a borrar
     */
    public void searchAndDelete(tipoDato d) {
        l.remove(d);
    }

    /**
     * Elimina el primer dato de la lista
     */
    public void deleteFirst() {
        l.remove();
    }
}
