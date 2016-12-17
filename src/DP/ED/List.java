package DP.ED;

import java.util.LinkedList;

/**
 * Implementación de la Lista
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class List<tipoDato> {

    /**
     * Lista usada
     */
    private LinkedList<tipoDato> l;

    /**
     * Constructor por defecto de lista
     */
    public List() {
        l = new LinkedList<tipoDato>();
    }

    /**
     * Constructor parametrizado de lista
     *
     * @param data Dato con el que crear la lista
     */
    public List(tipoDato data) {
        l = new LinkedList<tipoDato>();
        addLast(data);
    }

    /**
     * Retorna el primer elemento de la lista
     *
     * @return Primer elemento de la lista
     */
    public tipoDato getFirst() {
        return l.getFirst();
    }

    /**
     * Devuelve el último deato de la lista
     *
     * @return Último dato de la lista
     */
    public tipoDato getLast() {
        return l.getLast();
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
     * Devuelve el tamaño de la lista
     *
     * @return Tamaño de la lista
     */
    public Integer size() {
        return l.size();
    }

    /**
     * Retorna el elemento de la posición pos
     *
     * @param pos Posición del elemento a retornar
     * @return Elemento de la posición pos
     */
    public tipoDato get(Integer pos) {
        return l.get(pos);
    }

    /**
     * Elimina el dato de la posición pos
     *
     * @param pos Dato a eliminar
     */
    public void delete(Integer pos) {
        l.remove((int) pos);
     }

    /**
     * Método que añade un dato al final
     *
     * @param Data Dato a añadir al final
     */
    public void addLast(tipoDato Data) {
        l.addLast(Data);
    }

    /**
     * Elimina el último elemento de la lista
     *
     */
    public void removeLast() {
        l.removeLast();
    }
    
    public boolean contains(tipoDato Data){
        return l.contains(Data);
    }
}
