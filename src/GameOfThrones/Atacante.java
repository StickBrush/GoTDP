package GameOfThrones;

/**
 * Implementación de los atacantes
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class Atacante extends Personaje {

    /**
     * Constructor parametrizado de atacante
     *
     * @param nombre Nombre del personaje
     * @param tipo Tipo de personaje
     * @param ID Marca identificativa
     * @param turno Turno en el que empieza a moverse
     */
    public Atacante(String nombre, String tipo, char ID, int turno) {
        super(nombre, tipo, ID, turno);
    }

    /**
     * Interacción con la puerta de los atacantes
     *
     * @param p Puerta con la que interactuar
     */
    @Override
    public void interactuarPuerta(Puerta p) {
        if (!this.llaves.isEmpty()) {
            p.abrir(this.llaves.getTop());
            this.llaves.removeData();
        }
    }

    /**
     * Coger una llave del suelo
     *
     * @param l Llave a coger
     */
    public void cogerLlave(Llave l) {
        this.llaves.addData(l);
        numLlaves++;
    }

}
