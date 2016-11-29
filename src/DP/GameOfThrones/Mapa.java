package DP.GameOfThrones;

import DP.Personajes.*;
import DP.Exceptions.MapSizeException;
import DP.ED.*;
import DP.util.FicheroCarga;
import DP.util.Cargador;
import java.io.IOException;
import DP.util.UtilityKnife;
import DP.util.GenAleatorios;
import DP.util.Logger;
import java.util.Set;
import java.util.LinkedHashSet;

/**
 * Implementación del mapa
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
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
     * Profundidad de la combinación
     */
    private int profComb;
    /**
     * Salas del mapa
     */
    private Sala[][] salas;
    /**
     * Sala del trono
     */
    private Sala trono;
    /**
     * Coordenada Y de la puerta
     */
    private Integer iPuerta;
    /**
     * Coordenada X de la puerta
     */
    private Integer jPuerta;
    /**
     * Turno actual
     */
    private int turno;
    /**
     * Laberinto superpuesto al mapa
     */
    private Grafo laberinto;
    /**
     * Personajes del mapa
     */
    private List<Personaje> personajes;

    /**
     * Constructor parametrizado de Mapa
     *
     * @param salaPuerta Sala de la puerta
     * @param X Número de columnas
     * @param Y Número de filas
     * @param profComb Profundidad de la combinación
     * @throws DP.Exceptions.MapSizeException Se intentó crear el mapa con
     * dimensiones no válidas
     */
    public Mapa(int salaPuerta, int X, int Y, int profComb) throws MapSizeException {
        personajes = new List<>();
        laberinto = new Grafo();
        tamX = X;
        tamY = Y;
        if (X <= 0 || Y <= 0) {
            throw new MapSizeException();
        }
        turno = 1;
        this.profComb = profComb;
        salas = new Sala[tamY][tamX];
        int contador = 0;
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                if (contador != salaPuerta) {
                    salas[i][j] = new Sala(contador);
                    laberinto.nuevoNodo(contador);
                } else {
                    salas[i][j] = new SalaPuerta(contador);
                    laberinto.nuevoNodo(contador);
                    iPuerta = i;
                    jPuerta = j;
                }
                contador++;
            }
        }
        trono = new Sala(1111);
        List<Pared> paredes = new List<>();
        generarParedes(paredes);
        Kruskal(paredes);
        crearAtajos();
    }

    /**
     * Algoritmo de Kruskal
     *
     * @param paredes Paredes del mapa
     */
    private void Kruskal(List<Pared> paredes) {
        Pared aux;
        int pos;
        int marca;
        while (!paredes.estaVacia()) {
            pos = GenAleatorios.generarNumero(paredes.size());
            aux = paredes.get(pos);
            paredes.delete(pos);
            if (aux.tirable()) {
                marca = aux.getSala2().getKruskal();
                for (int i = 0; i < tamY; i++) {
                    for (int j = 0; j < tamX; j++) {
                        if (salas[i][j].getKruskal() == marca) {
                            salas[i][j].setKruskal(aux.getSala1().getKruskal());
                        }
                    }
                }
                laberinto.nuevoArco(aux.getSala1().getID(), aux.getSala2().getID());
            }
        }
    }

    /**
     * Genera todas las paredes del mapa
     *
     * @param paredes Paredes del mapa
     */
    private void generarParedes(List<Pared> paredes) {
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                int ID = i * tamX + j;
                if (i != 0) {
                    paredes.addLast(new Pared(salas[i][j], salas[i - 1][j]));
                }
                if (j != tamX - 1) {
                    paredes.addLast(new Pared(salas[i][j], salas[i][j + 1]));
                }
                if (i != tamY - 1) {
                    paredes.addLast(new Pared(salas[i][j], salas[i + 1][j]));
                }
                if (j != 0) {
                    paredes.addLast(new Pared(salas[i][j], salas[i][j - 1]));
                }
            }
        }
    }

    /**
     * Añade un personaje al mapa sin insertarlo en una sala
     *
     * @param p Personaje a añadir
     */
    public void nuevoPersonaje(Personaje p) {
        personajes.addLast(p);
    }

    /**
     * Inserta una puerta en el mapa
     *
     * @param p Puerta a insertar
     */
    public void insertarPuerta(Puerta p) {
        p.setAltura(profComb);
        ((SalaPuerta) salas[iPuerta][jPuerta]).insertarPuerta(p);
    }

    /**
     * Devuelve si se puede acceder
     *
     * @param IDS1 ID de la primera sala
     * @param IDS2 ID de la segunda sala
     * @return True si no están separadas por pared, false si hay pared.
     */
    public boolean esAccesible(int IDS1, int IDS2) {
        return laberinto.adyacente(IDS1, IDS2);
    }

    public void distribuirLlaves() {
        int numLlavesGenerar = 45;
        List<Integer> salasLlaves = UtilityKnife.sortByFrequence(iPuerta * tamX + jPuerta, laberinto, tamX * tamY);
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
        for (int i = 0; i < salasLlaves.size(); i++) {
            int x = salasLlaves.get(i) % tamX;
            int y = salasLlaves.get(i) / tamX;
            for (int contadorl = 0; contadorl < 5 && k < llavesGen.length; contadorl++) {
                salas[y][x].nuevaLlave(llavesGen[k]);
                k++;
            }
        }
    }

    /**
     * Devuelve la información del Mapa
     *
     * @return Información del Mapa
     */
    public String infoMapa() {
        int SalaPuerta = iPuerta * tamY + tamX;
        String sol;
        sol = "(turno:" + turno + ")" + "\n";
        sol = sol + "(mapa:" + SalaPuerta + ")" + "\n";
        if (puertaAbierta()) {
            sol = sol + ("(puerta:abierta:" + profComb + ":" + (((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta().llavesCerr()) + ":" + (((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta().llavesProb()) + ")") + "\n";
        } else {
            sol = sol + ("(puerta:cerrada:" + profComb + ":" + (((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta().llavesCerr()) + ":" + (((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta().llavesProb()) + ")") + "\n";
        }
        List<String> structure = structureString();
        for (int i = 0; i < structure.size(); i++) {
            sol = sol + structure.get(i) + "\n";
        }
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                if (salas[i][j].tieneLlave()) {
                    sol = sol + "(sala:" + salas[i][j].getID() + ":" + salas[i][j].getLlaves() + ")" + "\n";
                }
            }
        }
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                sol = sol + salas[i][j].showPersonajes(turno);
            }
        }
        sol = sol + "(miembrostrono)\n";
        sol = sol + "(nuevorey:" + trono.showPersonajes(turno) + ")+\n";
        return sol;
    }

    /**
     * Retorna la sala especificada del mapa
     *
     * @param i Coordenada Y de la sala
     * @param j Coordenada X de la sala
     * @return Sala con identificador (i*tamX)+j
     */
    public Sala getSala(int i, int j) {
        return salas[i][j];
    }

    /**
     * Retorna el ancho del mapa
     *
     * @return Máxima coordenada X del mapa
     */
    public Integer getTamX() {
        return tamX;
    }

    /**
     * Retorna el alto del mapa
     *
     * @return Máxima coordenada Y del mapa
     */
    public Integer getTamY() {
        return tamY;
    }

    /**
     * Retorna la sala del trono
     *
     * @return Trono
     */
    public Sala getSalaTrono() {
        return trono;
    }

    /**
     * Retorna el turno actual
     *
     * @return Turno actual
     */
    public int getTurno() {
        return turno;
    }

    /**
     * Retorna la puerta del mapa
     *
     * @return Puerta del trono
     */
    public Puerta getPuerta() {
        return ((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta();
    }

    /**
     * Simula un turno completo. Acarrea incremento de turno
     */
    public void simularTurno() {
        Sala salaAux;
        Arbol<Character> personajesMovidos = new Arbol<Character>(); //Evita mover varias veces el mismo personaje.
        for (int i = 0; i < tamY; i++) {
            for (int j = 0; j < tamX; j++) {
                salaAux = salas[i][j];
                salaAux.simular(i, j, this, personajesMovidos);
            }
        }
        turno++;
    }

    /**
     * Retorna el estado de la puerta
     *
     * @return True si la puerta está abierta, false si no
     */
    public boolean puertaAbierta() {
        return ((SalaPuerta) salas[iPuerta][jPuerta]).getPuerta().estaAbierta();
    }

    /**
     * Inserta un personaje en el mapa, en su sala de inicio correspondiente
     *
     * @param p Personaje al que insertar
     */
    public void insertarPersonaje(Personaje p) {
        int i = p.init(this) / tamX;
        int j = p.init(this) % tamX;
        salas[i][j].nuevoPersonaje(p, true);
    }

    /**
     * Devuelve un string con la estructura del mapa. Usado en Logger.
     *
     * @return String con dibujo del mapa
     */
    public List<String> structureString() {
        List<String> structure = new List<>();
        String total = "";
        total = total + " ";
        for (int i = 0; i < tamX; i++) {
            total = total + "_ ";
        }
        structure.addLast(total);
        total = "";
        for (int i = 0; i < tamY; i++) {
            total = total + "|";
            for (int j = 0; j < tamX; j++) {
                total = total + salas[i][j].showSala(this);
            }
            total = total + "|";
            structure.addLast(total);
            total = "";
        }
        return structure;
    }

    /**
     * Inserta al mapa todos los personajes de la lista
     */
    public void dumpPersonajes() {
        for (int i = 0; i < personajes.size(); i++) {
            Personaje p = personajes.get(i);
            this.insertarPersonaje(p);
        }
    }

    /**
     * Crea atajos en el mapa
     */
    private void crearAtajos() {
        Double a = (tamX * tamY) * 0.05; //Calculamos el 5% de las salas, es decir, las paredes a tirar
        Integer i = a.intValue();
        int checked = 0;
        if (i < 1) {
            i = 1; //Garantizamos que tiramos al menos una pared (para garantizar multiplicidad de caminos)
        }
        Set<Integer> comprobadas = new LinkedHashSet<>(); //Salas ya comprobadas
        boolean tirable;
        for (; i > 0 && checked < tamX * tamY; i--) { //Pararemos, bien si ya creamos suficientes atajos, bien si no se puede
            tirable = true; //Asumimos que podemos tirar la pared
            int pos = GenAleatorios.generarNumero(tamX * tamY); //Tomamos sala al azar
            if (!comprobadas.contains(pos)) { //Si no la hemos comprobado ya...
                checked++;
                comprobadas.add(pos);
                Sala comprobar = salas[pos / tamX][pos % tamX];
                Pared aux = comprobar.vecinoNoAccesible(this); //Tomamos el vecino no accesible
                if (aux != null) { //Si existe...
                    laberinto.nuevoArco(aux.getSala1().getID(), aux.getSala2().getID()); //Tiramos la pared
                    if (aux.horizontal()) { //Comprobamos que se podía tirar
                        if (aux.getSala1().getID() % tamX < tamX - 1) {
                            int[] ids = {aux.getSala1().getID(), aux.getSala1().getID() + 1, aux.getSala2().getID(), aux.getSala2().getID() + 1};
                            tirable = tirable && UtilityKnife.hayPared(laberinto, ids);
                        }
                        if (aux.getSala1().getID() % tamX > 0) {
                            int[] ids = {aux.getSala1().getID(), aux.getSala1().getID() - 1, aux.getSala2().getID(), aux.getSala2().getID() - 1};
                            tirable = tirable && UtilityKnife.hayPared(laberinto, ids);
                        }
                    } else {
                        if (aux.getSala1().getID() / tamX < tamY - 1) {
                            int[] ids = {aux.getSala1().getID(), aux.getSala1().getID() + tamX, aux.getSala2().getID(), aux.getSala2().getID() + tamX};
                            tirable = tirable && UtilityKnife.hayPared(laberinto, ids);
                        }
                        if (aux.getSala1().getID() / tamX > 0) {
                            int[] ids = {aux.getSala1().getID(), aux.getSala1().getID() - tamX, aux.getSala2().getID(), aux.getSala2().getID() - tamX};
                            tirable = tirable && UtilityKnife.hayPared(laberinto, ids);
                        }
                    }
                    if (!tirable) { //Si no se podía, la restauramos
                        laberinto.borraArco(aux.getSala1().getID(), aux.getSala2().getID());
                        i++; //No hemos tirado nada
                    }
                } else {
                    i++; //Si no había vecino, no hemos tirado nada
                }
            } else {
                i++; //Si ya la comprobamos, no hemos tirado nada
            }
        }
    }

    /**
     * Devuelve, concatenadas, las rutas de los personajes
     *
     * @return Rutas concatenadas
     */
    public String rutas() {
        String sol = "";
        for (int i = 0; i < personajes.size(); i++) {
            sol = sol + personajes.get(i).ruta();
        }
        return sol;
    }

    /**
     * Programa principal - EC3
     *
     * @param args Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        int numLlaves = 15;
        Cargador cargador = new Cargador();
        Mapa m = null;
        String ficheroInicio;
        if(args.length>0)
            ficheroInicio=args[0];
        else
            ficheroInicio="inicio.txt";
        try {
            m = FicheroCarga.procesarFichero(ficheroInicio, cargador); //Cargamos el mapa de fichero
        } catch (IOException ex) {
            System.err.println("Error al cargar inicio.txt. Creando mapa por defecto...");
            try {
                m = new Mapa(35, 6, 6, 4); //Mapa por defecto (el de EC2)
            } catch (MapSizeException exc) {
                System.err.println("Esto no pasará"); //La excepción se controla manualmente, aquí no se llegará
            }
        }
        //Log del mapa
        Logger logger = new Logger();
        logger.logMapa(m);
        logger.logRutas(m);
        m.distribuirLlaves();

        //Creación de la lista de identificadores
        int j = 1;
        Integer[] listaLlaves = new Integer[numLlaves];
        for (int i = 0; i < listaLlaves.length; i++) {
            listaLlaves[i] = j;
            j += 2;
        }
        //Reordenación de la lista de identificadores
        listaLlaves = UtilityKnife.nuevaCombinacion(listaLlaves);
        //Paso de identificadores a llaves
        Llave[] combLlaves = new Llave[numLlaves];
        for (int i = 0; i < listaLlaves.length; i++) {
            combLlaves[i] = new Llave(listaLlaves[i]);
        }

        //Creación, configuración e inserción de la puerta
        Puerta p = new Puerta();
        p.configurar(combLlaves);
        m.insertarPuerta(p);
        m.dumpPersonajes();
        //Simulación
        boolean abierta = false;
        for (int i = 0; i < 50 && !abierta; i++) {
            m.simularTurno();
            logger.logInfoMapa(m);
            abierta = m.puertaAbierta();
        }
        logger.endLogger();
    }

}
