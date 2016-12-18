package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.ED.Cola;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de la clase Personaje
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
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
    protected Cola<Dir> ruta;
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
        this.turno = turno;
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
        for (Dir vRuta1 : vRuta) {
            ruta.encolar(vRuta1);
        }
    }

    /**
     * Cálculo automático de la ruta
     */
    public abstract void autoRuta();

    /**
     * Mueve al personaje
     *
     * @param i Coordenada i de la sala en la que está el personaje
     * @param j Coordenada j de la sala en la que está el personaje
     * @return True si el personaje se movió, false si no
     * @throws MovementException El personaje no se pudo mover.
     */
    public boolean mover(int i, int j) throws MovementException {
        Mapa m = Mapa.getInstance();
        if (!ruta.vacia() && this.turno <= m.getTurno()) {
            Dir o = ruta.primero();
            ruta.desencolar();
            switch (o) {
                case N:
                    if (i - 1 >= 0 && m.esAccesible(i * m.getTamX() + j, (i - 1) * m.getTamX() + j)) {
                        m.getSala(i - 1, j).nuevoPersonaje(this, false);
                        return true;
                    } else if (i - 1 < 0) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case S:
                    if (i + 1 < m.getTamY() && m.esAccesible(i * m.getTamX() + j, (i + 1) * m.getTamX() + j)) {
                        m.getSala(i + 1, j).nuevoPersonaje(this, false);
                        return true;
                    } else if (i + 1 >= m.getTamY()) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case O:
                    if (j - 1 >= 0 && m.esAccesible(i * m.getTamX() + j, i * m.getTamX() + j - 1)) {
                        m.getSala(i, j - 1).nuevoPersonaje(this, false);
                        return true;
                    } else if (j - 1 <= 0) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case E:
                    if (j + 1 < m.getTamX() && m.esAccesible(i * m.getTamX() + j, i * m.getTamX() + j + 1)) {
                        m.getSala(i, j + 1).nuevoPersonaje(this, false);
                        return true;
                    } else if (j + 1 > m.getTamX()) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * Interacción personaje-sala
     *
     * @param s Sala con la que interactuar
     */
    public abstract void interactuarSala(Sala s);

    /**
     * Interacción personaje-puerta
     *
     * @throws MovementException El personaje no se pudo mover
     * @return True si el personaje se movió, false si no.
     */
    public abstract boolean interactuarPuerta() throws MovementException;

    /**
     * Devuelve la sala de inicio del personaje
     *
     * @return Sala de inicio del personaje
     */
    public abstract Integer init();

    /**
     * Método toString del personaje
     *
     * @return Personaje casteado a String
     */
    @Override
    public String toString() {
        String aux = tipo + ":" + ID;
        return aux;
    }

    /**
     * Devuelve la ruta del personaje como string
     *
     * @return Ruta casteada a String
     */
    public String ruta() {
        Cola<Dir> aux = new Cola<>();
        String sol = "(ruta:" + this.ID + ":";
        Dir dAux;
        while (!ruta.vacia()) {
            dAux = ruta.primero();
            ruta.desencolar();
            aux.encolar(dAux);
            switch (dAux) {
                case N:
                    sol = sol + "N ";
                    break;
                case S:
                    sol = sol + "S ";
                    break;
                case E:
                    sol = sol + "E ";
                    break;
                case O:
                    sol = sol + "O ";
                    break;
            }
        }
        while (!aux.vacia()) {
            dAux = aux.primero();
            ruta.encolar(dAux);
            aux.desencolar();
        }
        sol = sol + ")\n";
        return sol;
    }
}
