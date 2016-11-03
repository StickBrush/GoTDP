package GameOfThrones;

import DP.ED.ListaOrdenada;
import DP.ED.Cola;

/**
 * Implementación de la NotKingsLandingException
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
class NotKingsLandingException extends Exception {
}

/**
 * Implementación de la Sala
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
public class Sala {

    /**
     * Llaves de la sala
     */
    private ListaOrdenada<Llave> llaves;
    /**
     * Personajes de la sala
     */
    private Cola<Personaje> personajes;
    /**
     * Identificador de la sala
     */
    private Integer ID;

    /**
     * Constructor parametrizado de sala
     *
     * @param ID Identificador de la sala
     */
    public Sala(Integer ID) {
        llaves = new ListaOrdenada<Llave>();
        personajes = new Cola<Personaje>();
        this.ID = ID;
    }

    /**
     * Añade una nueva llave
     *
     * @param l Llave a añadir
     */
    public void nuevaLlave(Llave l) {
        llaves.add(l);
    }

    /**
     * Añade un nuevo personaje
     *
     * @param p Personaje a añadir
     * @return False si el personaje no sale del mapa
     */
    public boolean nuevoPersonaje(Personaje p) {
        if (p instanceof CaminanteBlanco && this.tienePersonaje()) {
            ((CaminanteBlanco) p).kill(primero());
            personajes.desencolar();
        }
        personajes.encolar(p);
        if (p instanceof Atacante) {
            ((Atacante) p).cogerLlave(getLlave(0));
            eliminarLlave(getLlave(0));
        } else if (p instanceof Defensor && !(p instanceof CaminanteBlanco)) {
            this.nuevaLlave(((Defensor) p).dejarLlave());
        }
        return false;
    }

    /**
     * Devuelve si la sala tiene personajes
     *
     * @return True si tiene personajes, false si no
     */
    public boolean tienePersonaje() {
        return (!personajes.vacia());
    }

    /**
     * Devuelve una llave
     *
     * @param pos Posición de la llave
     * @return Llave de la posición pos
     */
    public Llave getLlave(int pos) {
        return llaves.get(pos);
    }

    /**
     * Devuelve al personaje que más tiempo lleve
     *
     * @return Personaje que más tiempo lleva
     */
    public Personaje primero() {
        return personajes.primero();
    }

    /**
     * Elimina llave de la sala
     *
     * @param l Llave a eliminar
     */
    public void eliminarLlave(Llave l) {
        llaves.searchAndDelete(l);
    }

    /**
     * Devuelve si tiene o no llaves
     *
     * @return True si tiene llaves, false si no
     */
    public boolean tieneLlave() {
        return !llaves.estaVacia();
    }

    /**
     * Elimina al personaje que más tiempo lleve
     */
    public void desencolar() {
        personajes.desencolar();
    }

    /**
     * Lanza una NotKingsLandingException
     *
     * @param p Puerta ignorada
     * @throws NotKingsLandingException
     */
    public void insertarPuerta(Puerta p) throws NotKingsLandingException {
        throw new NotKingsLandingException();
    }

    /**
     * Lanza una NotKingsLandingException
     *
     * @return N/A
     * @throws NotKingsLandingException
     */
    public Puerta getPuerta() throws NotKingsLandingException {
        throw new NotKingsLandingException();
    }

    /**
     * Devuelve el ID de la Sala
     *
     * @return ID de la sala
     */
    public Integer getID() {
        return ID;
    }
}
