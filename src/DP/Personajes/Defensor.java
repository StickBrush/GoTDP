package DP.Personajes;

import DP.Exceptions.MovementException;
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
        return this.mover(i, j);
    }

    /**
     * Devuelve la sala de inicio del defensor
     *
     * @return Sala de inicio del defensor
     */
    @Override
    public abstract Integer init();
}
