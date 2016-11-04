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
        if (!l.isEmpty()) {
            tipoDato it = l.getFirst();
            int i = 1;
            boolean encontrado = false;
            while (i < l.size() && !encontrado) {
                if (l.get(i).compareTo(it) > 0) {
                    encontrado = true;
                } else {
                    i++;
                }
                if (!encontrado) {
                    l.addLast(data);
                } else {
                    l.add(i, data);
                }
            }
        } else {
            l.add(data);
        }
    }

    /**
     * Busca y elimina un dato en la lista
     *
     * @param d Dato a borrar
     */
    public void searchAndDelete(tipoDato d) {
        l.removeFirstOccurrence(d);
    }
}
