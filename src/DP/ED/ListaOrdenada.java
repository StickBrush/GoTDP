/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.ED;

import java.util.Queue;
import java.util.PriorityQueue;
/**
 * Implementación de la Lista Ordenada
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 * @param <tipoDato> Tipo de dato de la lista ordenada. Debe ser comparable.
 */
public class ListaOrdenada<tipoDato>{
    
    private Queue<tipoDato> l;
    /**
     * Constructor por defecto de la lista ordenada
     */
    public ListaOrdenada() {
        l=new PriorityQueue<tipoDato>();
    }
    
    public boolean estaVacia(){
        return l.isEmpty();
    }
    
    public tipoDato getFirst(){
        return l.element();
    }
    /**
     * Constructor parametrizado de la Lista Ordenada
     *
     * @param data Dato con el que crear la lista
     */
    public ListaOrdenada(tipoDato data) {
        l=new PriorityQueue<tipoDato>();
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
    
    public void deleteFirst(){
        l.remove();
    }
}
