package GameOfThrones;

/**
 * Implementación de la clase Lannister
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class Lannister extends Defensor {

    /**
     * Constructor parametrizado de Lannister
     *
     * @param nombre Nombre del Lannister
     * @param ID Marca identificativa
     */
    public Lannister(String nombre, char ID) {
        super(nombre, "Lannister", ID);
        int numLlavesGenerar = 45;
        numLlaves = numLlavesGenerar;
        Llave[] llavesGen = new Llave[numLlavesGenerar];
        int idLlave = 0;
        for (int i = 0; i < numLlavesGenerar; i++) {
            llavesGen[i] = new Llave(idLlave);
            if (idLlave % 2 == 1) {
                i++;
                llavesGen[i] = new Llave(idLlave);
            }
            idLlave++;
        }
        for (int i = numLlavesGenerar - 1; i >= 0; i--) {
            this.llaves.addData(llavesGen[i]);
        }
    }

}
