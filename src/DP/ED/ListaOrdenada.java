/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.ED;

/**
 * Implementación de la Lista Ordenada
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 * @param <tipoDato> Tipo de dato de la lista ordenada. Debe ser comparable.
 */
public class ListaOrdenada<tipoDato extends Comparable<tipoDato>> extends List<tipoDato> {

    /**
     * Constructor por defecto de la lista ordenada
     */
    public ListaOrdenada() {
        super();
    }

    /**
     * Constructor parametrizado de la Lista Ordenada
     *
     * @param data Dato con el que crear la lista
     */
    public ListaOrdenada(tipoDato data) {
        super(data);
    }

    /**
     * Lanza una OrderViolationException
     *
     * @param data Será ignorado
     * @throws OrderViolationException
     */
    @Override
    public void addLast(tipoDato data) throws OrderViolationException {
        throw new OrderViolationException();
    }

    /**
     * Añade un dato en orden
     *
     * @param data Dato a añadir
     */
    public void add(tipoDato data) {
        boolean encontrado = false;
        Node iterador;
        for (iterador = first; (iterador != null) && (!encontrado); iterador = iterador.next()) {
            if ((iterador.get()).compareTo(data) > 0) {
                encontrado = true;
            }
        }
        if (iterador != null) {
            Node nuevo = new Node(iterador.prev(), data, iterador);
            (iterador.prev()).setNext(nuevo);
            iterador.setPrev(nuevo);
        } else {
            Node nuevo = new Node(last, data, null);
            if (last != null) {
                last.setNext(nuevo);
            }
            last = nuevo;
        }
    }

    /**
     * Busca y elimina un dato en la lista
     *
     * @param d Dato a borrar
     * @return True si lo borró, false si no.
     */
    public boolean searchAndDelete(tipoDato d) {
        Node iterador;
        boolean encontrado = false;
        int i = 0;
        for (iterador = first; (iterador != null) && (!encontrado); iterador = iterador.next()) {
            if (iterador.get().equals(d)) {
                encontrado = true;
                super.delete(i);
            } else {
                i++;
            }
        }
        return encontrado;
    }
}
