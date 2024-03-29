package DP.ED;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Implementación de la cola
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 * @param <tipoDato> Tipo genérico de dato para la cola
 */
public class Cola<tipoDato> {

    /**
     * Cola usada
     */
    private Queue<tipoDato> q;

    /**
     * Constructor por defecto de cola
     */
    public Cola() {
        q = new LinkedList<tipoDato>();
    }

    /**
     * Encola un dato en la cola
     *
     * @param dato Dato a encolar
     */
    public void encolar(tipoDato dato) {
        q.add(dato);
    }

    /**
     * Devuelve el primer dato de la cola
     *
     * @return Primer dato de la cola
     */
    public tipoDato primero() {
        return q.element();
    }

    /**
     * Devuelve si la cola está vacía
     *
     * @return True si la cola está vacía, false si no
     */
    public boolean vacia() {
        return q.isEmpty();
    }

    /**
     * Desencola el primero de la cola
     */
    public void desencolar() {
        if (!q.isEmpty()) {
            q.remove();
        }
    }

    /**
     * Devuelve el número de elementos de la cola
     *
     * @return Elementos de la cola
     */
    public int numEl() {
        return q.size();
    }

}
