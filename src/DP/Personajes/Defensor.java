package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.Exceptions.NotKingsLandingException;
import DP.GameOfThrones.Mapa;

/**
 * Implementación de los Defensores
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
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

    @Override
    public void interactuarPuerta(Mapa m, int i, int j) throws MovementException {
        try {
            m.getPuerta().cerrar();
        } catch (NotKingsLandingException ex) {
            System.err.println("Mapa no configurado");
        }
        this.mover(m, i, j, m.getTurno());
    }

    @Override
    public Integer init(Mapa m) {
        return null;
    }


}
