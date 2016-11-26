package DP.Personajes;

import DP.ED.Stack;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación del Caminante Blanco
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
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
     * @param turno Turno en el que empieza a moverse
     */
    public CaminanteBlanco(String nombre, char ID, int turno) {
        super(nombre, "Caminante Blanco", ID, turno);
        capturados = new Stack<Character>();
    }

    /**
     * Método que captura personajes
     *
     * @param p Personaje a capturar
     */
    public void kill(Personaje p) {
        capturados.addData(p.getID());
        System.out.println(p);
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

    /**
     * Interacción caminante-sala
     *
     * @param s Sala con la que interactuar
     */
    @Override
    public void interactuarSala(Sala s) {
        if (s.tienePersonaje()) {
            capturados.addData(s.primero().getID());
            s.desencolar();
        }
    }

    /**
     * Devuelve la sala de inicio del caminante
     *
     * @param m Mapa que contiene al caminante
     * @return Sala de inicio del caminante
     */
    @Override
    public Integer init(Mapa m) {
        return (m.getTamY() - 1) * m.getTamX();
    }

    /**
     * Método toString del caminante
     *
     * @return CaminanteBlanco casteado a String
     */
    @Override
    public String toString() {
        String aux = capturados("");
        aux = tipo + ":" + ID + ":" + aux;
        return aux;
    }
}
