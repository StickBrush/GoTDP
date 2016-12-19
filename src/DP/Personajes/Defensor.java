package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;

/**
 * Implementación de los Defensores
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public abstract class Defensor extends Personaje {

    /**
     * Constructor parametrizado del Defensor
     *
     * @param nombre Nombre del Defensor
     * @param tipo Tipo del Defensor
     * @param ID Marca identificativa del Defensor
     * @param turno Turno en el que empieza a moverse
     */
    public Defensor(String nombre, String tipo, char ID, int turno) {
        super(nombre, tipo, ID, turno);
    }

    /**
     * Interacción defensor-puerta
     *
     * @return False, los defensores no se mueven en estos casos
     * @throws MovementException El defensor no pudo moverse.
     */
    @Override
    public boolean interactuarPuerta() throws MovementException {
        Mapa m = Mapa.getInstance();
        int i = m.getKingsLanding() / m.getTamX();
        int j = m.getKingsLanding() % m.getTamX();
        m.getPuerta().cerrar();
        return this.reinsertar(i, j);
    }

    /**
     * Devuelve la sala de inicio del defensor
     *
     * @return Sala de inicio del defensor
     */
    @Override
    public abstract Integer init();
    
    /**
     * Mueve al personaje como si lo reinsertase
     *
     * @param i Coordenada i de la sala en la que está el personaje
     * @param j Coordenada j de la sala en la que está el personaje
     * @return True si el personaje se movió, false si no
     * @throws MovementException El personaje no se pudo mover.
     */
    protected boolean reinsertar(int i, int j) throws MovementException {
        Mapa m = Mapa.getInstance();
        if (!ruta.vacia() && this.turno <= m.getTurno()) {
            Dir o = ruta.primero();
            ruta.desencolar();
            switch (o) {
                case N:
                    if (i - 1 >= 0 && m.esAccesible(i * m.getTamX() + j, (i - 1) * m.getTamX() + j)) {
                        m.getSala(i - 1, j).nuevoPersonaje(this, true);
                        return true;
                    } else if (i - 1 < 0) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case S:
                    if (i + 1 < m.getTamY() && m.esAccesible(i * m.getTamX() + j, (i + 1) * m.getTamX() + j)) {
                        m.getSala(i + 1, j).nuevoPersonaje(this, true);
                        return true;
                    } else if (i + 1 >= m.getTamY()) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case O:
                    if (j - 1 >= 0 && m.esAccesible(i * m.getTamX() + j, i * m.getTamX() + j - 1)) {
                        m.getSala(i, j - 1).nuevoPersonaje(this, true);
                        return true;
                    } else if (j - 1 <= 0) {
                        throw new MovementException();
                    } else {
                        return false;
                    }
                case E:
                    if (j + 1 < m.getTamX() && m.esAccesible(i * m.getTamX() + j, i * m.getTamX() + j + 1)) {
                        m.getSala(i, j + 1).nuevoPersonaje(this, true);
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
}
