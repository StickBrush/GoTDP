package DP.ED;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Implementación de la cola
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 * @param <tipoDato> Tipo genérico de dato para la cola
 */
public class Cola<tipoDato> {

    private Queue<tipoDato> q;

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

}
