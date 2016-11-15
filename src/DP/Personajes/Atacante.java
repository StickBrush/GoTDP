package DP.Personajes;

import DP.Exceptions.MovementException;
import DP.Exceptions.NotKingsLandingException;
import DP.ED.Stack;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de los atacantes
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
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

    @Override
    public void interactuarPuerta(Mapa m, int i, int j) throws MovementException {
        if (!this.llaves.isEmpty()) {
            try {
                m.getPuerta().abrir(this.llaves.getTop());
            } catch (NotKingsLandingException ex) {
                System.err.println("Mapa no configurado");
            }
            this.llaves.removeData();
        }
        throw new MovementException();
    }

    @Override
    public void interactuarSala(Sala s) {
        Llave aux = s.getLlave();
        if (aux != null) {
            llaves.addData(aux);
        }
    }

    @Override
    public Integer init(Mapa m) {
        return 0;
    }

    /**
     * Devuelve concatenadas todas las llaves
     *
     * @return Todas las llaves concatenadas
     */
    public String getLlaves() {
        if (llaves != null) {
            return getAllLlaves("");
        } else {
            return "";
        }
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
