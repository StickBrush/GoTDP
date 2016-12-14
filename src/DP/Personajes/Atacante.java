package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.ED.Stack;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de los atacantes
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public abstract class Atacante extends Personaje {

    /**
     * Llaves del personaje
     */
    protected Stack<Llave> llaves;

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
        llaves = new Stack<>();
    }

    /**
     * Interacción atacante-puerta
     *
     * @param m Mapa que contiene al personaje
     * @param i Coordenada i de la sala de la puerta
     * @param j Coordenada j de la sala de la puerta
     * @throws MovementException El personaje no se pudo mover.
     * @return False, los atacantes jamás se mueven
     */
    @Override
    public boolean interactuarPuerta(Mapa m, int i, int j) throws MovementException {
        if (!this.llaves.isEmpty()) {
            m.getPuerta().abrir(this.llaves.getTop());
            this.llaves.removeData();
        }
        return false;
    }

    /**
     * Interacción atacante-sala
     *
     * @param s Sala con la que interactuar
     */
    @Override
    public void interactuarSala(Sala s) {
        Llave aux = s.getLlave();
        if (aux != null) {
            llaves.addData(aux);
        }
    }

    /**
     * ID de la sala de inicio
     *
     * @param m Mapa que contiene al personaje
     * @return ID de la sala de inicio del atacante
     */
    @Override
    public Integer init(Mapa m) {
        return 0;
    }

    /**
     * Devuelve todas las llaves concatenadas, recursivamente
     *
     * @param aux String a modificar recursivamente
     * @return Todas las llaves concatenadas
     */
    private String getAllLlaves(String aux) {
        if (!llaves.isEmpty()) {
            Llave laux = llaves.getTop();
            llaves.removeData();
            aux = aux + laux.toString() + " ";
            aux = getAllLlaves(aux);
            llaves.addData(laux);
        }
        return aux;
    }

    /**
     * Método toString del atacante
     *
     * @return Atacante casteado a String
     */
    @Override
    public String toString() {
        String aux = "";
        if (llaves != null) {
            aux = getAllLlaves("");
        }
        aux = tipo + ":" + ID + ":" + aux;
        return aux;
    }
}
