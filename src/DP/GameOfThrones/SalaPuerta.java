package DP.GameOfThrones;

import DP.Personajes.Personaje;
import DP.Exceptions.MovementException;
import DP.ED.Arbol;
import DP.ED.Cola;

/**
 * Implementación de la sala de la puerta
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
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
     */
    public void insertarPuerta(Puerta p) {
        this.p = p;
    }

    /**
     * Devuelve la puerta de la sala
     *
     * @return Puerta de la sala
     */
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

    /**
     * Simula un turno
     *
     * @param movidos Identificadores de personajes que ya se movieron
     */
    @Override
    public void simular(Arbol<Character> movidos) {
        Mapa m = Mapa.getInstance();
        int i = m.getKingsLanding() / m.getTamX();
        int j = m.getKingsLanding() % m.getTamX();
        Cola<Personaje> cAux = new Cola<>();
        boolean moved = false;
        for (Personaje p; this.tienePersonaje(); personajes.desencolar()) {
            p = personajes.primero();
            if (!movidos.pertenece(p.getID())) {
                try {
                    moved = p.interactuarPuerta();
                    if (!moved) {
                        cAux.encolar(p);
                    }
                } catch (MovementException ex) {
                    cAux.encolar(p);
                }
            } else {
                cAux.encolar(p);
            }
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
