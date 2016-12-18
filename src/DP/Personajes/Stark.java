package DP.Personajes;

import DP.GameOfThrones.Mapa;
import DP.util.UtilityKnife;

/**
 * Implementación de la clase Stark
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class Stark extends Atacante {

    /**
     * Constructor parametrizado de Stark
     *
     * @param nombre Nombre del Stark
     * @param ID Marca identificativa
     * @param turno Turno en el que empieza a moverse
     */
    public Stark(String nombre, char ID, int turno) {
        super(nombre, "Stark", ID, turno);
    }

    /**
     * Cálculo automático de la ruta
     */
    @Override
    public void autoRuta() {
        ruta = UtilityKnife.integerToDir(Mapa.getInstance().getLaberintoActualizado().profundidad(Mapa.getInstance().getKingsLanding()));
    }

}
