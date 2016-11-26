package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.GameOfThrones.Mapa;

/**
 * Implementación de los Defensores
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
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
     * @param m Mapa que contiene al defensor
     * @param i Coordenada i de la sala de la puerta
     * @param j Coordenada j de la sala de la puerta
     * @throws MovementException El defensor no pudo moverse.
     */
    @Override
    public void interactuarPuerta(Mapa m, int i, int j) throws MovementException {
        m.getPuerta().cerrar();
        this.mover(m, i, j, m.getTurno());
    }

    /**
     * Devuelve la sala de inicio del defensor
     *
     * @param m Mapa que contiene al defensor
     * @return Sala de inicio del defensor
     */
    @Override
    public abstract Integer init(Mapa m);
}
