package GameOfThrones;

import DP.ED.Stack;

/**
 * Implementación del Caminante Blanco
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class CaminanteBlanco extends Defensor {

    /**
     * Identificadores de los personajes capturados;
     */
    private Stack<Character> capturados;

    /**
     * Constructor parametrizado de Caminante Blanco
     *
     * @param nombre Nombre del Caminante Blanco
     * @param ID Marca identificativa del Caminante Blanco
     */
    public CaminanteBlanco(String nombre, char ID) {
        super(nombre, "Caminante Blanco", ID);
        llaves = null;
        capturados = new Stack<Character>();
    }

    /**
     * Método que captura personajes
     *
     * @param p Personaje a capturar
     */
    public void kill(Personaje p) {
        capturados.addData(p.getID());
    }

    /**
     * Devuelve un string con los identificadores de los personajes capturados
     *
     * @return Peronajes capturados
     */
    public String getCapturados() {
        return capturados("");
    }

    /**
     * Método privado que devuelve todos los capturados concatenados
     *
     * @param aux Parámetro para recursividad
     * @return Capturados concatenados
     */
    private String capturados(String aux) {
        if (!capturados.isEmpty()) {
            aux = aux + capturados.getTop() + " ";
            Character reinsert = capturados.getTop();
            capturados.removeData();
            aux = capturados(aux);
            capturados.addData(reinsert);
        }
        return aux;
    }

}
