package DP.GameOfThrones;

import DP.ED.Cola;
import static DP.GameOfThrones.Dir.*;

/**
 * Implementación del algoritmo del Wall Follower
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class WallFollowingEngine {

    /**
     * Orientación hacia la que mira el WallFollowingEngine
     */
    private Dir orientacion;
    /**
     * Sala actual
     */
    private int salaAct;

    /**
     * Constructor por defecto del WallFollowingEngine
     */
    public WallFollowingEngine() {
        salaAct = 0;
        orientacion = S;
    }

    /**
     * Convierte de Dir a int
     *
     * @param d Dir a convertir
     * @return int con el incremento correspondiente
     */
    private int castDirToInt(Dir d) {
        switch (d) {
            case N:
                return -Mapa.getInstance().getTamX();
            case S:
                return Mapa.getInstance().getTamX();
            case E:
                return 1;
            case O:
                return -1;
            default:
                return 0;
        }
    }

    /**
     * Algoritmo Wall Follower
     *
     * @return Ruta con el Wall Follower
     */
    public Cola<Dir> wallFollower() {
        Cola<Dir> ruta = new Cola<>();
        Mapa aux = Mapa.getInstance();
        int kingsLanding = aux.getKingsLanding();
        while (salaAct != kingsLanding) {
            switch (orientacion) {
                case N:
                    if (salaAct % aux.getTamX() < aux.getTamX() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(E))) {
                        ruta.encolar(E);
                        orientacion = E;
                        salaAct += castDirToInt(E);
                    } else if (salaAct / aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(N))) {
                        ruta.encolar(N);
                        orientacion = N;
                        salaAct += castDirToInt(N);
                    } else if (salaAct % aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(O))) {
                        ruta.encolar(O);
                        orientacion = O;
                        salaAct += castDirToInt(O);
                    } else {
                        ruta.encolar(S);
                        orientacion = S;
                        salaAct += castDirToInt(S);
                    }
                    break;
                case S:
                    if (salaAct % aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(O))) {
                        ruta.encolar(O);
                        orientacion = O;
                        salaAct += castDirToInt(O);
                    } else if (salaAct / aux.getTamX() < aux.getTamY() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(S))) {
                        ruta.encolar(S);
                        orientacion = S;
                        salaAct += castDirToInt(S);
                    } else if (salaAct % aux.getTamX() < aux.getTamX() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(E))) {
                        ruta.encolar(E);
                        orientacion = E;
                        salaAct += castDirToInt(E);
                    } else {
                        ruta.encolar(N);
                        orientacion = N;
                        salaAct += castDirToInt(N);
                    }
                    break;
                case O:
                    if (salaAct / aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(N))) {
                        ruta.encolar(N);
                        orientacion = N;
                        salaAct += castDirToInt(N);
                    } else if (salaAct % aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(O))) {
                        ruta.encolar(O);
                        orientacion = O;
                        salaAct += castDirToInt(O);
                    } else if (salaAct / aux.getTamX() < aux.getTamY() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(S))) {
                        ruta.encolar(S);
                        orientacion = S;
                        salaAct += castDirToInt(S);
                    } else {
                        ruta.encolar(E);
                        orientacion = E;
                        salaAct += castDirToInt(E);
                    }
                    break;
                case E:
                    if (salaAct / aux.getTamX() < aux.getTamY() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(S))) {
                        ruta.encolar(S);
                        orientacion = S;
                        salaAct += castDirToInt(S);
                    } else if (salaAct % aux.getTamX() < aux.getTamX() - 1 && aux.esAccesible(salaAct, salaAct + castDirToInt(E))) {
                        ruta.encolar(E);
                        orientacion = E;
                        salaAct += castDirToInt(E);
                    } else if (salaAct / aux.getTamX() > 0 && aux.esAccesible(salaAct, salaAct + castDirToInt(N))) {
                        ruta.encolar(N);
                        orientacion = N;
                        salaAct += castDirToInt(N);
                    } else {
                        ruta.encolar(O);
                        orientacion = O;
                        salaAct += castDirToInt(O);
                    }
                    break;
            }
        }
        return ruta;
    }
}
