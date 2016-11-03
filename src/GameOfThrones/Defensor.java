package GameOfThrones;

/**
 * Implementación de los Defensores
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
public class Defensor extends Personaje {

    /**
     * Constructor parametrizado del Defensor
     *
     * @param nombre Nombre del Defensor
     * @param tipo Tipo del Defensor
     * @param ID Marca identificativa del Defensor
     */
    public Defensor(String nombre, String tipo, char ID) {
        super(nombre, tipo, ID);
    }

    /**
     * Cerrado de puerta
     *
     * @param p Puerta a cerrar
     */
    @Override
    public void interactuarPuerta(Puerta p) {
        p.cerrar(); //Totalmente balanceado, nada que nerfear aquí
    }

    /**
     * Deja una llave en el suelo
     *
     * @return Llave a dejar
     */
    public Llave dejarLlave() {
        Llave aux = this.llaves.getTop();
        this.llaves.removeData();
        numLlaves--;
        return aux;
    }

}
