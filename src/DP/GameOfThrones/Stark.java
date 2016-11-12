package DP.GameOfThrones;

/**
 * Implementación de la clase Stark
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
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

}