package DP.GameOfThrones;

/**
 * Implementación de los Defensores
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class Defensor extends Personaje {

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
     * Cerrado de puerta
     *
     * @param p Puerta a cerrar
     */
    @Override
    public void interactuarPuerta(Puerta p) {
        p.cerrar();
    }

    /**
     * Deja una llave en el suelo
     *
     * @return Llave a dejar
     */
    public Llave dejarLlave() {
        Llave aux = null;
        if(!llaves.isEmpty()){
        aux= this.llaves.getTop();
        this.llaves.removeData();
        numLlaves--;
        }
        return aux;
    }

}
