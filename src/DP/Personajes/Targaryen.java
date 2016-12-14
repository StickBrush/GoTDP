package DP.Personajes;

import DP.GameOfThrones.Mapa;
import DP.GameOfThrones.FengShuiEngine;

/**
 * Implementación de la clase Targaryen
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public class Targaryen extends Atacante {

    /**
     * Construtor parametrizado de Targaryen
     *
     * @param nombre Nombre del Targaryen
     * @param ID Marca identificativa
     * @param turno Turno en el que empieza a moverse
     */
    public Targaryen(String nombre, char ID,int turno) {
        super(nombre, "Targaryen", ID, turno);
    }

    @Override
    public void autoRuta() {
        FengShuiEngine E=new FengShuiEngine();
        ruta=E.wallFollower(Mapa.getInstance().getKingsLanding());
    }

}
