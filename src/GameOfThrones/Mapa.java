package GameOfThrones;

import java.util.Random;
import DP.ED.*;

/**
 * Implementación del mapa
 *
 * @version 1.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC1
 */
public class Mapa {

    /**
     * Número de columnas
     */
    private int tamX;
    /**
     * Número de filas
     */
    private int tamY;
    /**
     * Sala de la puerta
     */
    private int salaPuerta;
    /**
     * Profundidad de la combinación
     */
    private int profComb;

    private List<Llave> llaves;

    private Sala[][] salas;

    private Sala trono;

    private Integer iPuerta;

    private Integer jPuerta;

    private int turno;

    /**
     * Constructor parametrizado de Mapa
     *
     * @param salaPuerta Sala de la puerta
     * @param X Número de columnas
     * @param Y Número de filas
     * @param profComb Profundidad de la combinación
     */
    public Mapa(int salaPuerta, int X, int Y, int profComb) {
        tamX = X;
        tamY = Y;
        turno = 0;
        this.salaPuerta = salaPuerta;
        this.profComb = profComb;
        llaves = new List<Llave>();
        salas = new Sala[tamX][tamY];
        int contador = 0;
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                if (i != tamX - 1 || j != tamY - 1) {
                    salas[i][j] = new Sala(contador);
                }
                contador++;
            }
        }
        salas[tamX - 1][tamY - 1] = new SalaPuerta((tamX * tamY) - 1);
        iPuerta = tamX - 1;
        jPuerta = tamY - 1;
        trono = new Sala(1111);
    }

    /**
     * Inserta una puerta en el mapa
     *
     * @param p Puerta a insertar
     */
    public void insertarPuerta(Puerta p) {
        p.setAltura(profComb);
        try {
            salas[iPuerta][jPuerta].insertarPuerta(p);
        } catch (NotKingsLandingException ex) {
            System.err.println("La sala de la puerta está mal configurada");
        }
    }

    public void distribuirLlaves(Integer[] salasLlaves){
         int numLlavesGenerar = 45;
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
         Integer k = 0;
        boolean found = false;
        for (Integer i : salasLlaves) {
            found = false;
            for (int im = 0; im < tamY && !found; im++) {
                for (int jm = 0; jm < tamX && !found; jm++) {
                    if (salas[im][jm].getID() == i) {
                        found = true;
                        for (int contadorl = 0; contadorl < numLlavesGenerar/salasLlaves.length; contadorl++) {
                            salas[im][jm].nuevaLlave(llavesGen[k]);
                            k++;
                        }
                    }
                }
            }
        }
    }
    /**
     * Método auxiliar para reordenar un vector
     *
     * @param lista Vector a reordenar
     * @param i Posición para introducir el dato en mitad del vector
     * @param aux Vector reordenado
     * @return Siguiente posición libre de aux
     */
    private int comb(Integer[] lista, int i, Integer[] aux) {
        if (lista.length == 1) {
            aux[i] = lista[0];
            i++;
        } else {
            Integer[] divIz = new Integer[(lista.length) / 2];
            Integer[] divDer = new Integer[(lista.length) / 2];
            int mitad = lista.length / 2;
            for (int x = 0; x < mitad; x++) {
                divIz[x] = lista[x];
                divDer[x] = lista[x + mitad + 1];
            }
            aux[i] = lista[mitad];
            i++;
            i = comb(divIz, i, aux);
            i = comb(divDer, i, aux);
        }
        return i;
    }

    /**
     * Método para reordenar la combinación de llaves
     *
     * @param lista Combinación de llaves a reordenar
     * @return Combinación reordenada
     */
    public Integer[] nuevaCombinacion(Integer[] lista) {
        Integer[] aux = new Integer[lista.length];
        comb(lista, 0, aux);
        return aux;
    }

    /**
     * Muestra el mapa por pantalla
     */
    public void mostrarMapa() {
        System.out.println("turno:" + turno);
        System.out.println("mapa:" + salaPuerta);
        try {
            if (puertaAbierta()) {
                System.out.println("puerta:abierta:" + profComb + ":" + ((salas[iPuerta][jPuerta]).getPuerta().llavesCerr()) + ":" + ((salas[iPuerta][jPuerta]).getPuerta().llavesProb()));
            } else {
                System.out.println("puerta:cerrada:" + profComb + ":" + ((salas[iPuerta][jPuerta]).getPuerta().llavesCerr()) + ":" + ((salas[iPuerta][jPuerta]).getPuerta().llavesProb()));
            }
        } catch (NotKingsLandingException ex) {
            System.err.println("¿Mapa no configurado?");
        }
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                if (salas[i][j].tieneLlave()) {
                    System.out.println("sala:" + salas[i][j].getID() + ":" + salas[i][j].getLlaves()); //¿Cómo se hace print sin salto de línea?
                }
            }
        }
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                salas[i][j].showPersonajes(turno);
            }
        }
        System.out.println("(miembrostrono)");
        System.out.println("nuevorey:");
        trono.showPersonajes(turno);
    }

    public void simularTurno() {
        Cola<Personaje> colaSolicitudes = new Cola<Personaje>();
        Sala salaAux = null;
        Arbol<Character> personajesMovidos = new Arbol<Character>(); //Evita mover varias veces el mismo personaje.
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                salaAux = salas[i][j];
                while (salaAux.tienePersonaje()) {
                    colaSolicitudes.encolar(salaAux.primero());
                    salaAux.desencolar();
                }
                if (salaAux instanceof SalaPuerta) {
                    Cola<Personaje> colaAux = new Cola<Personaje>();
                    while (!colaSolicitudes.vacia()) {
                        try {
                            if (!personajesMovidos.pertenece(colaSolicitudes.primero().getID())) {
                                colaSolicitudes.primero().interactuarPuerta(salaAux.getPuerta());
                            }
                        } catch (NotKingsLandingException ex) {
                            System.err.println("Interactuada puerta que no existe");
                        } finally {
                            colaAux.encolar(colaSolicitudes.primero());
                            colaSolicitudes.desencolar();
                        }
                    }
                    while (!colaAux.vacia()) {
                        if(colaAux.primero() instanceof Lannister){
                            try{
                                switch (colaAux.primero().nextMove()){
                                case N:
                                    if (!personajesMovidos.pertenece(colaAux.primero().getID())) {
                                        personajesMovidos.insertar(colaAux.primero().getID());
                                        if (j - 1 >= 0) {
                                            salas[i][j - 1].nuevoPersonaje(colaAux.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaAux.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaAux.primero(), true);
                                    }
                                    colaAux.desencolar();
                                    break;
                                case S:
                                    if (!personajesMovidos.pertenece(colaAux.primero().getID())) {
                                        personajesMovidos.insertar(colaAux.primero().getID());
                                        if (j + 1 < tamX) {
                                            salas[i][j + 1].nuevoPersonaje(colaAux.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaAux.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaAux.primero(), true);
                                    }
                                    colaAux.desencolar();
                                    break;
                                case E:
                                    if (!personajesMovidos.pertenece(colaAux.primero().getID())) {
                                        personajesMovidos.insertar(colaAux.primero().getID());
                                        if (i + 1 < tamY) {
                                            salas[i + 1][j].nuevoPersonaje(colaAux.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaAux.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaAux.primero(), true);
                                    }
                                    colaAux.desencolar();
                                    break;
                                case O:
                                    if (!personajesMovidos.pertenece(colaAux.primero().getID())) {
                                        personajesMovidos.insertar(colaAux.primero().getID());
                                        if (i - 1 >= 0) {
                                            salas[i - 1][j].nuevoPersonaje(colaAux.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaAux.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaAux.primero(), true);
                                    }
                                    colaAux.desencolar();
                                    break;
                            }
                        } catch (NoMovesLeftException ex) {
                            salaAux.nuevoPersonaje(colaAux.primero(), true);
                            colaSolicitudes.desencolar();
                            }
                        }
                        else{
                        salaAux.nuevoPersonaje(colaAux.primero(), true);
                        colaAux.desencolar();}
                    }
                } else {
                    while (!colaSolicitudes.vacia()) {
                        boolean trono = false;
                        try {
                            switch (colaSolicitudes.primero().nextMove()) {
                                case N:
                                    if (!personajesMovidos.pertenece(colaSolicitudes.primero().getID())) {
                                        personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                        if (j - 1 >= 0) {
                                            trono = salas[i][j - 1].nuevoPersonaje(colaSolicitudes.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                    }
                                    colaSolicitudes.desencolar();
                                    break;
                                case S:
                                    if (!personajesMovidos.pertenece(colaSolicitudes.primero().getID())) {
                                        personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                        if (j + 1 < tamX) {
                                            trono = salas[i][j + 1].nuevoPersonaje(colaSolicitudes.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                    }
                                    colaSolicitudes.desencolar();
                                    break;
                                case E:
                                    if (!personajesMovidos.pertenece(colaSolicitudes.primero().getID())) {
                                        personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                        if (i + 1 < tamY) {
                                            trono = salas[i + 1][j].nuevoPersonaje(colaSolicitudes.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                    }
                                    colaSolicitudes.desencolar();
                                    break;
                                case O:
                                    if (!personajesMovidos.pertenece(colaSolicitudes.primero().getID())) {
                                        personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                        if (i - 1 >= 0) {
                                            trono = salas[i - 1][j].nuevoPersonaje(colaSolicitudes.primero(), false);
                                        } else {
                                            salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                        }
                                    } else {
                                        salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                                    }
                                    colaSolicitudes.desencolar();
                                    break;
                            }
                        } catch (NoMovesLeftException ex) {
                            salaAux.nuevoPersonaje(colaSolicitudes.primero(), true);
                            colaSolicitudes.desencolar();
                        } finally {
                            if (trono) {
                                while (salas[iPuerta][jPuerta].tienePersonaje()) {
                                    this.trono.nuevoPersonaje(salas[iPuerta][jPuerta].primero(), false);
                                    salas[iPuerta][jPuerta].desencolar();
                                }
                            }
                        }
                    }
                }
            }
        }
        turno++;
    }

    public boolean puertaAbierta() throws NotKingsLandingException {
        return salas[iPuerta][jPuerta].getPuerta().estaAbierta();
    }

    public void insertarPersonaje(Personaje p) {
        if (p instanceof Stark || p instanceof Targaryen) {
            salas[0][0].nuevoPersonaje(p, true);
        } else if (p instanceof CaminanteBlanco) {
            salas[tamY - 1][0].nuevoPersonaje(p, true);
        } else {
            salas[iPuerta][jPuerta].nuevoPersonaje(p, true);
        }
    }

    /**
     * Programa principal - EC1
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        int numLlaves = 15;
        int X = 6;
        int Y = 6;
        int salaPuerta = (X * Y) - 1;
        int profComb = 4;
        //Creación del mapa
        Mapa m = new Mapa(salaPuerta, X, Y, profComb);
        Integer[] SalasLlaves = {3, 4, 6, 8, 9, 10, 11, 12, 13};
        m.distribuirLlaves(SalasLlaves);
        int j = 1;
        //Creación de la lista de identificadores
        Integer[] listaLlaves = new Integer[numLlaves];
        for (int i = 0; i < listaLlaves.length; i++) {
            listaLlaves[i] = j;
            j += 2;
        }
        //Reordenación de la lista de identificadores
        listaLlaves = m.nuevaCombinacion(listaLlaves);
        //Paso de identificadores a llaves
        Llave[] combLlaves = new Llave[numLlaves];
        for (int i = 0; i < listaLlaves.length; i++) {
            combLlaves[i] = new Llave(listaLlaves[i]);
        }
        //Creación, configuración e inserción de la puerta
        Puerta p = new Puerta();
        p.configurar(combLlaves);
        m.insertarPuerta(p);

        //Creación de personajes
        Personaje[] personajes = new Personaje[4];
        personajes[0] = new Stark("Jonathan", 'J');
        personajes[1] = new Targaryen("Speedwagon", 'S');
        personajes[2] = new Lannister("Kars", 'K');
        personajes[3] = new CaminanteBlanco("DIO", 'D');

        /*
        //Sistema de generación de rutas
        Random RNG = new Random(); //Generador de números aleatorios (RNG)
        Orientacion[] ruta = new Orientacion[30];
        for (int pi = 0; pi < 4; pi++) {
            for (int i = 0; i < 30; i++) {
                switch (RNG.nextInt(4)) {
                    case 0:
                        ruta[i] = Orientacion.N;
                        break;
                    case 1:
                        ruta[i] = Orientacion.S;
                        break;
                    case 2:
                        ruta[i] = Orientacion.E;
                        break;
                    case 3:
                        ruta[i] = Orientacion.O;
                        break;
                }
            }
            personajes[pi].setRuta(ruta);
        }
         */
        Orientacion[] ruta = {Orientacion.S, Orientacion.S, Orientacion.E, Orientacion.E, Orientacion.N, Orientacion.E, Orientacion.N, Orientacion.E, Orientacion.S, Orientacion.E, Orientacion.S, Orientacion.S, Orientacion.O, Orientacion.S, Orientacion.E, Orientacion.S};
        personajes[0].setRuta(ruta);
        Orientacion[] ruta2 ={Orientacion.E, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.O, Orientacion.S, Orientacion.E, Orientacion.E, Orientacion.N, Orientacion.E, Orientacion.S, Orientacion.S, Orientacion.E, Orientacion.E};
        personajes[1].setRuta(ruta2);
        Orientacion[] ruta3 = {Orientacion.N, Orientacion.N, Orientacion.O, Orientacion.N, Orientacion.N, Orientacion.O, Orientacion.S, Orientacion.O, Orientacion.O, Orientacion.N, Orientacion.N, Orientacion.O, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.E, Orientacion.E, Orientacion.E, Orientacion.E, Orientacion.E};
        personajes[2].setRuta(ruta3);
        Orientacion[] ruta4 = {Orientacion.N, Orientacion.N, Orientacion.N, Orientacion.E, Orientacion.S, Orientacion.E, Orientacion.N, Orientacion.N, Orientacion.E, Orientacion.N, Orientacion.E, Orientacion.E, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.S, Orientacion.S};
        personajes[3].setRuta(ruta4);

        //Inserción de personajes
        for (int i = 0; i < 4; i++) {
            m.insertarPersonaje(personajes[i]);
        }
        
        m.mostrarMapa();

        //Simulación
        boolean abierta = false;
        for (int i = 0; i < 50 && !abierta; i++) {
            m.simularTurno();
            try {
                abierta = m.puertaAbierta();
            } catch (NotKingsLandingException ex) {
                System.err.println("¿El mapa no fue inicializado?");
            }
        }
        m.mostrarMapa();
    }

}
