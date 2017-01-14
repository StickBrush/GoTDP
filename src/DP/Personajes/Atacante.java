package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.ED.Stack;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de los atacantes
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public abstract class Atacante extends Personaje {

    /**
     * Llaves del personaje
     */
    protected Stack<Llave> llaves;

    /**
     * Constructor parametrizado de atacante PRE={ID no se puede repetir}
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
     * Interacción atacante-puerta PRE={Mapa.salas[iPuerta][jPuerta].p!=null}
     *
     * @throws MovementException El personaje no se pudo mover.
     * @return False, los atacantes jamás se mueven
     */
    @Override
    public boolean interactuarPuerta() throws MovementException {
        Mapa m = Mapa.getInstance();
        if (!this.llaves.isEmpty()) {
            m.getPuerta().abrir(this.llaves.getTop());
            this.llaves.removeData();
        }
        return false;
    }

    /**
     * Interacción atacante-sala PRE={s!=null}
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
     * @return ID de la sala de inicio del atacante
     */
    @Override
    public Integer init() {
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
