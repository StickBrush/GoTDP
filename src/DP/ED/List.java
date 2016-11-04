package DP.ED;

import java.util.LinkedList;

/**
 * Implementation of the method for the List class.
 *
 * @version 2.0
 * @author
 * <b> Profesores DP </b><br>
 * Program Development<br/>
 * 16/17 Course
 */
public class List<tipoDato> {

    private LinkedList<tipoDato> l;

    /**
     * Default Constructor for the List class
     */
    public List() {
        l = new LinkedList<tipoDato>();
    }

    /**
     * Parametrized method for the List class
     *
     * @param data the data that the List will store
     */
    public List(tipoDato data) {
        addLast(data);
    }

    /**
     * Method that returns the element that is stored at the beginning of the
     * list
     *
     * @return the first element
     */
    public tipoDato getFirst() {
        return l.getFirst();
    }

    /**
     * Method that returns the data that is stored at the end of the list
     *
     * @return the last data
     */
    public tipoDato getLast() {
        return l.getLast();
    }

    public boolean estaVacia() {
        return l.isEmpty();
    }

    /**
     * Method that returns the size of the list
     *
     * @return the size of the list
     */
    public Integer size() {
        return l.size();
    }

    /**
     * Method that returns the data contained in the position passed as
     * parameter
     *
     * @param pos the position of the element to be returned
     * @return the data contained in the position passed as parameter
     */
    public tipoDato get(Integer pos) {
        return l.get(pos);
    }

    public void delete(Integer pos) {
        l.remove(pos);
    }

    /**
     * Method to add a data by the end of the list
     *
     * @param Data value that is going to be added to the list
     * @throws DP.ED.OrderViolationException
     */
    public void addLast(tipoDato Data){
        l.addLast(Data);
    }

    /**
     * It removes the last data in the list
     *
     */
    public void removeLast() {
        l.removeLast();
    }
}
