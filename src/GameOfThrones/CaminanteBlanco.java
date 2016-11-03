package GameOfThrones;

/**
 * Implementación del Caminante Blanco
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
public class CaminanteBlanco extends Defensor {

    /**
     * Número de personajes capturados
     */
    private int capturados;

    /**
     * Constructor parametrizado de Caminante Blanco
     *
     * @param nombre Nombre del Caminante Blanco
     * @param ID Marca identificativa del Caminante Blanco
     */
    public CaminanteBlanco(String nombre, char ID) {
        super(nombre, "Caminante Blanco", ID);
        llaves = null; //Y que el Garbage Collector elimine la pila
        capturados = 0;
    }

    /**
     * Método que captura personajes
     *
     * @param p Personaje a capturar
     */
    public void kill(Personaje p) {
        System.out.println("El personaje " + p.getNombre() + " ha sido eliminado");
        capturados++;
    }

    /**
     * Devuelve el número de personajes capturados
     *
     * @return Peronajes capturados
     */
    public int getCapturados() {
        return capturados;
    }

}
