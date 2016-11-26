package DP.Personajes;

import DP.ED.Stack;
import DP.GameOfThrones.Llave;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;

/**
 * Implementación de la clase Lannister
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
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
        llaves=new Stack<Llave>();
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
    /**
     * Devuelve la sala de inicio del Lannister
     * @param m Mapa que contiene al Lannister
     * @return Sala de inicio del Lannister
     */
    @Override
    public Integer init(Mapa m) {
        return (m.getTamX()*m.getTamY())-1;
    }
    
    /**
     * Interacción Lannister-sala
     * @param s Sala con la que interactuar
     */
    @Override
    public void interactuarSala(Sala s) {
        if(!llaves.isEmpty()){
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
     * @return Lannister casteado a String
     */
        @Override
        public String toString(){
        String aux="";
        if(llaves != null)
            aux=getAllLlaves("");
        aux = tipo + ":" + ID +":" + aux;
        return aux;
    }
}
