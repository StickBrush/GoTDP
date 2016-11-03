package DP.ED;

/**
 * Implementación de la cola
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 * @param <tipoDato> Tipo genérico de dato para la cola
 */
public class Cola<tipoDato> {

    /**
     * Implementación del nodo de la cola
     *
     * @version 2.0
     * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
     * @param <tipoD> Tipo genérico de dato del nodo
     */
    private class Nodo<tipoD> {

        /**
         * Nodo siguiente
         */
        private Nodo<tipoD> siguiente;
        /**
         * Dato del nodo actual
         */
        private tipoD dato;

        /**
         * Constructor parametrizado de nodo
         *
         * @param siguiente Nodo siguiente al nuevo
         * @param dato Dato del nuevo nodo
         */
        public Nodo(Nodo<tipoD> siguiente, tipoD dato) {
            this.siguiente = siguiente;
            this.dato = dato;
        }

        /**
         * Devuelve el nodo siguiente
         *
         * @return Nodo siguiente
         */
        public Nodo<tipoD> siguiente() {
            return siguiente;
        }

        /**
         * Devuelve el dato del nodo
         *
         * @return Dato del nodo
         */
        public tipoD dato() {
            return dato;
        }
    }
    /**
     * Primero de la cola
     */
    Nodo<tipoDato> primero;
    /**
     * Último de la cola
     */
    Nodo<tipoDato> ultimo;

    /**
     * Constructor por defecto de la cola
     */
    public Cola() {
        primero = null;
        ultimo = null;
    }

    /**
     * Encola un dato en la cola
     *
     * @param dato Dato a encolar
     */
    public void encolar(tipoDato dato) {
        Nodo<tipoDato> nuevo = new Nodo<tipoDato>(null, dato);
        if (!vacia()) {
            ultimo.siguiente = nuevo;
        } else {
            primero = nuevo;
        }
        ultimo = nuevo;
    }

    /**
     * Devuelve el primer dato de la cola
     *
     * @return Primer dato de la cola
     */
    public tipoDato primero() {
        if (!vacia()) {
            return primero.dato();
        } else {
            return null;
        }
    }

    /**
     * Devuelve si la cola está vacía
     *
     * @return True si la cola está vacía, false si no
     */
    public boolean vacia() {
        return (primero == null);
    }

    /**
     * Desencola el primero de la cola
     */
    public void desencolar() {
        if (!vacia()) {
            primero = primero.siguiente();
            if (primero == null) {
                ultimo = null;
            }
        }
    }

}
