package DP.GameOfThrones;

import java.util.Objects;

/**
 * Clase pared, de uso exclusivo para la creación del laberinto
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class Pared {

    /**
     * Primera sala separada por la pared
     */
    private Sala s1;
    /**
     * Segunda sala separada por la pared
     */
    private Sala s2;

    /**
     * Constructor parametrizado de Pared
     *
     * @param s1 Sala 1 a separar
     * @param s2 Sala 2 a separar
     */
    public Pared(Sala s1, Sala s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    /**
     * Devuelve s1
     *
     * @return Sala 1 separada
     */
    public Sala getSala1() {
        return s1;
    }

    /**
     * Devuelve s2
     *
     * @return Sala 2 separada
     */
    public Sala getSala2() {
        return s2;
    }

    /**
     * Devuelve si la pared separa salas de igual marca
     *
     * @return True si se puede tirar la pared, false si no
     */
    public boolean tirable() {
        return (!Objects.equals(s1.getKruskal(), s2.getKruskal()));
    }

    /**
     * Devuelve si la pared es horizontal
     *
     * @return True si es horizontal
     */
    public boolean horizontal() {
        int res = s2.getID() - s1.getID();
        return !(res == 1 || res == -1);
    }
}
