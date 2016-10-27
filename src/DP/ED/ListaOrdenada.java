/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.ED;

/**
 *
 * @author Solaire
 * @param <tipoDato> Tipo de los datos de la lista
 */
public class ListaOrdenada<tipoDato extends Comparable<tipoDato>> extends List <tipoDato> {

    public ListaOrdenada() {
        super();
    }

    public ListaOrdenada(tipoDato data) {
        super(data);
    }

    @Override
    public void addLast(tipoDato data) throws OrderViolationException {
        throw new OrderViolationException();
    }

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
            last.setNext(nuevo);
            last = nuevo;
        }
    }
    
    public boolean searchAndDelete(tipoDato d){
        Node iterador;
        boolean encontrado=false;
        int i=0;
        for(iterador=first;(iterador!=null)&&(!encontrado);iterador=iterador.next()){
            if(iterador.get().equals(d)){
                encontrado=true;
                super.delete(i);
            }
            else
                i++;
        }
        return encontrado;
    }
}
