package DP.GameOfThrones;

import DP.Personajes.Personaje;
import DP.Exceptions.MovementException;
import DP.ED.ListaOrdenada;
import DP.ED.Cola;
import DP.ED.Arbol;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Implementación de la Sala
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
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
     * Marca para Kruskal
     */
    private Integer Kruskal;

    /**
     * Constructor parametrizado de sala PRE={ID no se puede repetir}
     *
     * @param ID Identificador de la sala
     */
    public Sala(Integer ID) {
        llaves = new ListaOrdenada<Llave>();
        personajes = new Cola<Personaje>();
        this.ID = ID;
        Kruskal = ID;
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
     * Devuelve la primera llave de la estructura, si existe
     *
     * @return Primera llave, o null si no la hubiese
     */
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

    /**
     * Simula un turno en esta sala PRE={movidos!=null}
     *
     * @param movidos Personajes que ya se han movido este turno
     */
    public void simular(Arbol<Character> movidos) {
        Mapa m = Mapa.getInstance();
        int i = ID / m.getTamX();
        int j = ID % m.getTamX();
        Cola<Personaje> cAux = new Cola<>();
        boolean moved = false;
        for (Personaje p; this.tienePersonaje(); personajes.desencolar()) {
            p = personajes.primero();
            try {
                if (!movidos.pertenece(p.getID())) {
                    movidos.insertar(p.getID());
                    moved = p.mover(i, j);
                    if (!moved) {
                        cAux.encolar(p);
                    }
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
     * Devuelve concatenados los personajes de la sala
     *
     * @param turno Turno actual
     * @return Personajes concatenados
     */
    public String showPersonajes(int turno) {
        String sol = "";
        if (this.tienePersonaje()) {
            Cola<Personaje> caux = new Cola<Personaje>();
            while (this.tienePersonaje()) {
                sol = sol + "(" + ID + ":" + turno + ":" + personajes.primero().toString() + ")\n";
                caux.encolar(personajes.primero());
                personajes.desencolar();
            }
            while (!caux.vacia()) {
                personajes.encolar(caux.primero());
                caux.desencolar();
            }
        }
        return sol;
    }

    /**
     * Setter de la marca para Kruskal
     *
     * @param nuevo Nueva marca
     */
    public void setKruskal(Integer nuevo) {
        Kruskal = nuevo;
    }

    /**
     * Getter de la marca para Kruskal
     *
     * @return Marca para Kruskal
     */
    public Integer getKruskal() {
        return Kruskal;
    }

    /**
     * Devuelve un string con la estructura de la sala
     *
     * @return Estructura de la sala
     */
    public String showSala() {
        Mapa m = Mapa.getInstance();
        String aux = "";
        switch (personajes.numEl()) {
            case 0: //Si no hay personajes
                if (ID / m.getTamX() == m.getTamY() - 1 || !m.esAccesible(ID, ID + m.getTamX())) //Si hay pared sur (o límite de mapa)
                {
                    aux = aux + "_";
                } else {
                    aux = aux + " ";
                }
                break;
            case 1: //Si hay un personaje
                aux = aux + personajes.primero().getID();
                break;
            default: //Si hay varios personajes
                aux = aux + personajes.numEl();
                break;
        }
        if (ID % (m.getTamX()) != m.getTamX() - 1) { //Si no estoy en el límite derecho
            if (!m.esAccesible(ID, ID + 1)) //Si hay pared este (NO límite mapa)
            {
                aux = aux + "|";
            } else {
                aux = aux + " ";
            }
        }
        return aux;
    }

    /**
     * Devuelve una pared con el primer vecino no accesible
     *
     * @return Pared con vecino no accesible
     */
    public Set<Pared> vecinoNoAccesible() {
        Mapa m = Mapa.getInstance();
        Set<Pared> paredes = new LinkedHashSet<>();
        if (ID / m.getTamX() != 0 && !m.esAccesible(ID, ID - m.getTamX())) {
            paredes.add(new Pared(this, m.getSala((ID / m.getTamX()) - 1, ID % m.getTamX())));
        }
        if (ID / m.getTamX() != m.getTamY() - 1 && !m.esAccesible(ID, ID + m.getTamX())) {
            paredes.add(new Pared(this, m.getSala((ID / m.getTamX()) + 1, ID % m.getTamX())));
        }
        if (ID % m.getTamX() != 0 && !m.esAccesible(ID, ID - 1)) {
            paredes.add(new Pared(this, m.getSala((ID / m.getTamX()), ID % m.getTamX() - 1)));
        }
        if (ID % m.getTamX() != m.getTamX() - 1 && !m.esAccesible(ID, ID + 1)) {
            paredes.add(new Pared(this, m.getSala((ID / m.getTamX()), ID % m.getTamX() + 1)));
        }
        return paredes;
    }
}
