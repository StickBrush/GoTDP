package DP.GameOfThrones;

import DP.Exceptions.MovementException;
import DP.Exceptions.NotKingsLandingException;
import DP.ED.Arbol;
import DP.ED.Cola;

/**
 * Implementación de la sala de la puerta
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class SalaPuerta extends Sala {

    /**
     * Puerta de la sala
     */
    private Puerta p;

    /**
     * Constructor parametrizado de la sala de la puerta
     *
     * @param ID Identificador de la sala
     */
    public SalaPuerta(Integer ID) {
        super(ID);
    }

    /**
     * Inserta una puerta en la sala
     *
     * @param p Puerta a insertar
     * @throws NotKingsLandingException
     */
    @Override
    public void insertarPuerta(Puerta p) throws NotKingsLandingException {
        this.p = p;
    }

    /**
     * Devuelve la puerta de la sala
     *
     * @return Puerta de la sala
     */
    @Override
    public Puerta getPuerta() {
        return p;
    }

    /**
     * Añade un nuevo personaje
     *
     * @param pe Personaje a añadir
     * @param reinsert Indica si el personaje se vuelve a insertar o se mueve
     * @return True si la puerta está abierta, false si no.
     */
    @Override
    public boolean nuevoPersonaje(Personaje pe, boolean reinsert) {
        if (!p.estaAbierta()) {
            return super.nuevoPersonaje(pe, reinsert);
        } else {
            return !super.nuevoPersonaje(pe, reinsert);
        }
    }

    @Override
    public void simular(int i, int j, Mapa m, Arbol<Character> movidos) {
        Cola<Personaje> cAux = new Cola<>();
        for (Personaje p; this.tienePersonaje(); personajes.desencolar()) {
            p = personajes.primero();
            if (!movidos.pertenece(p.getID())) {
                try {
                    p.interactuarPuerta(m, i, j);
                } catch (MovementException ex) {
                    cAux.encolar(p);
                }
            }
            else
                cAux.encolar(p);
        }
        for (Personaje p; !cAux.vacia(); cAux.desencolar()) {
            p = cAux.primero();
            if (this.p.estaAbierta()) {
                m.getSalaTrono().nuevoPersonaje(p, true);
            } else {
                this.nuevoPersonaje(p, true);
            }
        }
    }
}
