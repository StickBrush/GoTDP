package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.ED.Cola;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de la clase Personaje
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
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
     * Ruta a seguir por el personaje
     */
    private Cola<Dir> ruta;
    /**
     * Número de llaves del personaje
     */
    protected int numLlaves;
    /**
     * Turno en el que el personaje empieza a moverse
     */
    protected int turno;

    /**
     * Constructor parametrizado de Personaje
     *
     * @param nombre Nombre del personaje
     * @param tipo Tipo de personaje
     * @param ID Marca identificativa
     * @param turno Turno en el que empieza a moverse
     */
    public Personaje(String nombre, String tipo, char ID, int turno) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ID = ID;
        ruta = new Cola<Dir>();
        this.turno=turno;
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
    public void setRuta(Dir[] vRuta) {
        for (int i = 0; i < vRuta.length; i++) {
            ruta.encolar(vRuta[i]);
        }
    }


    public void mover(Mapa m, int i, int j, int turno) throws MovementException{
        if(!ruta.vacia() && this.turno<=turno){
            Dir o= ruta.primero();
            ruta.desencolar();
            switch(o){
                case N:
                    if(i-1>0)
                        m.getSala(i-1, j).nuevoPersonaje(this, false);
                    else
                        throw new MovementException();
                    break;
                case S:
                    if(i+1<m.getTamY())
                        m.getSala(i+1, j).nuevoPersonaje(this, false);
                    else
                        throw new MovementException();
                    break;
                case O:
                    if(j-1>0)
                        m.getSala(i, j-1).nuevoPersonaje(this, false);
                    else
                        throw new MovementException();
                    break;
                case E:
                    if(j+1<m.getTamX())
                        m.getSala(i, j+1).nuevoPersonaje(this, false);
                    else
                        throw new MovementException();
                    break;
            }
        }
        else
            throw new MovementException();
    }
    
    public abstract void interactuarSala(Sala s);

    public abstract void interactuarPuerta(Mapa m, int i, int j) throws MovementException;
    
    public abstract Integer init(Mapa m);
    
    public String toString(){
        String aux = tipo + ":" + ID;
        return aux;
    }
}
