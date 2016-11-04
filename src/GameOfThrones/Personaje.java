package GameOfThrones;

import DP.ED.Stack;
import DP.ED.Cola;

/**
 * Implementación de la clase Personaje
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
public abstract class Personaje {

    /**
     * Nombre del personaje
     */
    protected String nombre;
    /**
     * Tipo de personaje
     */
    protected String tipo;
    /**
     * Marca identificativa
     */
    protected char ID;
    /**
     * Llaves del personaje
     */
    protected Stack<Llave> llaves;
    /**
     * Ruta a seguir por el personaje
     */
    private Cola<Orientacion> ruta;
    /**
     * Número de llaves del personaje
     */
    protected int numLlaves;

    /**
     * Constructor parametrizado de Personaje
     *
     * @param nombre Nombre del personaje
     * @param tipo Tipo de personaje
     * @param ID Marca identificativa
     */
    public Personaje(String nombre, String tipo, char ID) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ID = ID;
        llaves = new Stack<Llave>();
        ruta = new Cola<Orientacion>();
        numLlaves = 0;
    }

    /**
     * Devuelve el nombre del personaje
     *
     * @return Nombre del personaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el tipo del personaje
     *
     * @return Tipo de personaje
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Devuelve la marca identificativa del personaje
     *
     * @return Marca identificativa
     */
    public char getID() {
        return ID;
    }

    /**
     * Setea la ruta del personaje
     *
     * @param vRuta Ruta a seguir por el personaje
     */
    public void setRuta(Orientacion[] vRuta) {
        for (int i = 0; i < vRuta.length; i++) {
            ruta.encolar(vRuta[i]);
        }
    }

    /**
     * Devuelve el siguiente movimiento del personaje
     *
     * @return Siguiente movimiento del personaje
     * @throws NoMovesLeftException
     */
    public Orientacion nextMove() throws NoMovesLeftException {
        if (!ruta.vacia()) {
            Orientacion o = ruta.primero();
            ruta.desencolar();
            return o;
        } else {
            throw new NoMovesLeftException();
        }
    }

    /**
     * Interactúa con la puerta
     *
     * @param p Puerta con la que interactuar
     */
    public abstract void interactuarPuerta(Puerta p);
    
    /**
     * Devuelve concatenadas todas las llaves
     * @return Todas las llaves concatenadas
     */
    public String getLlaves(){
        return getAllLlaves("");
    }
    
    /**
     * Devuelve todas las llaves concatenadas, recursivamente
     * @param aux String a modificar recursivamente
     * @return Todas las llaves concatenadas
     */
    private String getAllLlaves(String aux){
        if(!llaves.isEmpty()){
            Llave laux=llaves.getTop();
            llaves.removeData();
            aux=aux+laux.toString()+" ";
            aux=getAllLlaves(aux);
            llaves.addData(laux);
        }
        return aux;
    }
}
