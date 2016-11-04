package GameOfThrones;

/**
 * Implementación de la llave
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class Llave implements Comparable<Llave> {

    /**
     * Identificador de la llave
     */
    private Integer ID;

    /**
     * Constructor parametrizado de Llave
     *
     * @param ID Identificador de la llave
     */
    public Llave(Integer ID) {
        this.ID = ID;
    }

    /**
     * Método que identifica una llave
     *
     * @return Identificador de la llave
     */
    public Integer identificar() {
        return ID;
    }

    /**
     * Método compareTo de la llave
     *
     * @param t Llave con la que comparar
     * @return -1 si la llave es menor, 1 si es mayor, 0 si son iguales.
     */
    @Override
    public int compareTo(Llave t) {
        return (ID.compareTo(t.ID));
    }

    /**
     * Método equals
     *
     * @param t Llave con la que comparar
     * @return True si son iguales, false si no.
     */
    @Override
    public boolean equals(Object t) {
        if (!(t instanceof Llave)) {
            return false;
        } else {
            return (this.compareTo((Llave) t) == 0);
        }
    }

    /**
     * Método toString de la Llave
     *
     * @return Llave casteada a String
     */
    @Override
    public String toString() {
        return (String.valueOf(ID));
    }

}
