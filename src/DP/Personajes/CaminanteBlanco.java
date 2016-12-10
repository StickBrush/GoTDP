package DP.Personajes;

import DP.ED.Cola;
import DP.ED.Stack;
import DP.Exceptions.MovementException;
import DP.GameOfThrones.Dir;
import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.Sala;
import DP.util.UtilityKnife;

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

        @Override
    public void autoRuta(Mapa m) {
        Cola<Dir>[] subrutas= new Cola[4];
        Integer SE=m.getTamX()*m.getTamY()-1;
        Integer NE=m.getTamX()-1;
        Integer NO=0;
        Integer SO=(m.getTamY()-1)*m.getTamX();
        subrutas[0]=UtilityKnife.integerToDir(m.getLaberinto().caminoMinimo(SO, NO));
        subrutas[1]=UtilityKnife.integerToDir(m.getLaberinto().caminoMinimo(NO, NE));
        subrutas[2]=UtilityKnife.integerToDir(m.getLaberinto().caminoMinimo(NE, SE));
        subrutas[3]=UtilityKnife.integerToDir(m.getLaberinto().caminoMinimo(SE, SO));
        for(Cola<Dir> aux : subrutas){
            while(!aux.vacia()){
                ruta.encolar(aux.primero());
                aux.desencolar();
            }
        }
    }
    
    @Override
    public boolean mover(Mapa m, int i, int j, int turno) throws MovementException {
        Dir x=ruta.primero();
        boolean b=super.mover(m, i, j, turno);
        ruta.encolar(x);
        return b;
    }
}
