package DP.Personajes;

import DP.ED.Cola;
import DP.ED.Stack;
import DP.Exceptions.MovementException;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;
import DP.util.UtilityKnife;

/**
 * Implementación de la clase Lannister
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class Lannister extends Defensor {

    /**
     * Llaves del personaje
     */
    protected Stack<Llave> llaves;

    /**
     * Constructor parametrizado de Lannister
     *
     * @param nombre Nombre del Lannister
     * @param ID Marca identificativa
     * @param turno Turno en el que empieza a moverse
     */
    public Lannister(String nombre, char ID, int turno) {
        super(nombre, "Lannister", ID, turno);
        llaves = new Stack<Llave>();
        int numLlavesGenerar = 45;
        numLlaves = numLlavesGenerar;
        for (int i = 1; i < 30; i += 2) {
            this.llaves.addData(new Llave(i));
        }
    }

    /**
     * Devuelve la sala de inicio del Lannister
     *
     * @return Sala de inicio del Lannister
     */
    @Override
    public Integer init() {
        Mapa m = Mapa.getInstance();
        return (m.getTamX() * m.getTamY()) - 1;
    }

    /**
     * Interacción Lannister-sala
     *
     * @param s Sala con la que interactuar
     */
    @Override
    public void interactuarSala(Sala s) {
        if (!llaves.isEmpty() && s.getID()%2 == 0) {
            s.nuevaLlave(llaves.getTop());
            llaves.removeData();
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

    /**
     * Método toString del Lannister
     *
     * @return Lannister casteado a String
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

    /**
     * Cálculo automático de la ruta
     */
    @Override
    public void autoRuta() {
        Cola<Dir>[] subrutas = new Cola[4];
        Mapa m = Mapa.getInstance();
        Integer SE = m.getTamX() * m.getTamY() - 1;
        Integer NE = m.getTamX() - 1;
        Integer NO = 0;
        Integer SO = (m.getTamY() - 1) * m.getTamX();
        subrutas[0] = UtilityKnife.integerToDir(m.getLaberintoActualizado().caminoMinimo(SE, NE));
        subrutas[1] = UtilityKnife.integerToDir(m.getLaberintoActualizado().caminoMinimo(NE, NO));
        subrutas[2] = UtilityKnife.integerToDir(m.getLaberintoActualizado().caminoMinimo(NO, SO));
        subrutas[3] = UtilityKnife.integerToDir(m.getLaberintoActualizado().caminoMinimo(SO, SE));
        for (Cola<Dir> aux : subrutas) {
            while (!aux.vacia()) {
                ruta.encolar(aux.primero());
                aux.desencolar();
            }
        }
    }

    /**
     * Método mover del Lannister
     *
     * @param i Coordenada i de la sala
     * @param j Coordenada j de la sala
     * @return True si se pudo mover, false si no
     * @throws MovementException Error al mover
     */
    @Override
    public boolean mover(int i, int j) throws MovementException {
        Dir x = null;
        if (turno <= Mapa.getInstance().getTurno()) {
            x = ruta.primero();
        }
        boolean b = super.mover(i, j);
        if (x != null) {
            ruta.encolar(x);
        }
        return b;
    }

    /**
     * Mueve al personaje como si lo reinsertase
     *
     * @param i Coordenada i de la sala en la que está el personaje
     * @param j Coordenada j de la sala en la que está el personaje
     * @return True si el personaje se movió, false si no
     * @throws MovementException El personaje no se pudo mover.
     */
    @Override
    protected boolean reinsertar(int i, int j) throws MovementException {
        Dir x = null;
        if (turno <= Mapa.getInstance().getTurno()) {
            x = ruta.primero();
        }
        boolean b = super.reinsertar(i, j);
        if (x != null) {
            ruta.encolar(x);
        }
        return b;
    }
}
