package DP.GameOfThrones;

import DP.ED.ListaOrdenada;
import DP.ED.Cola;
import DP.ED.Arbol;

/**
 * Implementación de la NotKingsLandingException
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
class NotKingsLandingException extends Exception {
}

/**
 * Implementación de la Sala
 *
 * @version 2.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC2
 */
public class Sala {

    /**
     * Llaves de la sala
     */
    private ListaOrdenada<Llave> llaves;
    /**
     * Personajes de la sala
     */
    protected Cola<Personaje> personajes;
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

    public Llave getLlave() {
        Llave aux = null;
        if (!llaves.estaVacia()) {
            aux = llaves.getFirst();
            llaves.deleteFirst();
        }
        return aux;
    }

    /**
     * Añade un nuevo personaje
     *
     * @param p Personaje a añadir
     * @param reinsert Indica si el personaje se vuelve a insertar o se mueve
     * @return False si el personaje no sale del mapa
     */
    public boolean nuevoPersonaje(Personaje p, boolean reinsert) {
        if (!reinsert) {
            p.interactuarSala(this);
        }
        personajes.encolar(p);

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
     * Devuelve al personaje que más tiempo lleve
     *
     * @return Personaje que más tiempo lleva
     */
    public Personaje primero() {
        return personajes.primero();
    }

    /**
     * Elimina llave de la sala
     */
    public void eliminarLlave() {
        llaves.deleteFirst();
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

    /**
     * Devuelve concatenadas todos los ID de las llaves
     *
     * @return ID de las llaves concatenados
     */
    public String getLlaves() {
        Cola<Llave> caux = new Cola<Llave>();
        String saux = "";
        for (Llave aux = null; !llaves.estaVacia(); llaves.deleteFirst()) {
            aux = llaves.getFirst();
            caux.encolar(aux);
            saux = saux + aux.toString() + " ";
        }
        for (Llave aux = null; !caux.vacia(); caux.desencolar()) {
            aux = caux.primero();
            llaves.add(aux);
        }
        return saux;
    }

    public void simular(int i, int j, Mapa m, Arbol<Character> movidos) {
        Cola<Personaje> cAux = new Cola<>();
        for (Personaje p; this.tienePersonaje(); personajes.desencolar()) {
            p = personajes.primero();
            try {
                if (!movidos.pertenece(p.getID())) {
                    movidos.insertar(p.getID());
                    p.mover(m, i, j, m.getTurno());
                } else {
                    cAux.encolar(p);
                }
            } catch (MovementException ex) {
                cAux.encolar(p);
            }
        }
        for (Personaje p; !cAux.vacia(); cAux.desencolar()) {
            p = cAux.primero();
            this.nuevoPersonaje(p, true);
        }
    }

    /**
     * Muestra a todos los personajes de la sala
     *
     * @param turno Turno actual
     */
    public void showPersonajes(int turno) {
        if (this.tienePersonaje()) {
            Cola<Personaje> caux = new Cola<Personaje>();
            while (this.tienePersonaje()) {
                System.out.println(personajes.primero().toString() + ":" + ID + ":" + turno);
                caux.encolar(personajes.primero());
                personajes.desencolar();
            }
            while (!caux.vacia()) {
                personajes.encolar(caux.primero());
                caux.desencolar();
            }
        }
    }
}
