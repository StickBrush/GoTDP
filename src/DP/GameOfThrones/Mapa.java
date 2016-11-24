package DP.GameOfThrones;

import DP.Personajes.*;
import DP.Exceptions.MapSizeException;
import DP.Exceptions.NotKingsLandingException;
import java.util.Random;
import DP.ED.*;
import java.util.Objects;
import DP.util.FicheroCarga;
import DP.util.Cargador;
import java.io.IOException;
import DP.util.Repartidor;

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
     * Profundidad de la combinación
     */
    private int profComb;

    private Sala[][] salas;

    private Sala trono;

    private Integer iPuerta;

    private Integer jPuerta;

    private int turno;
    
    private Grafo laberinto;
    
    private List<Personaje> personajes;

    /**
     * Constructor parametrizado de Mapa
     *
     * @param salaPuerta Sala de la puerta
     * @param X Número de columnas
     * @param Y Número de filas
     * @param profComb Profundidad de la combinación
     * @throws DP.Exceptions.MapSizeException Se intentó crear el mapa con dimensiones no válidas
     */
    public Mapa(int salaPuerta, int X, int Y, int profComb) throws MapSizeException {
        personajes=new List<>();
        laberinto=new Grafo();
        tamX = X;
        tamY = Y;
        if (X <= 0 || Y <= 0) {
            throw new MapSizeException();
        }
        turno = 1;
        this.profComb = profComb;
        salas = new Sala[tamX][tamY];
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
        List<Pared> paredes= new List<>();
        generarParedes(paredes);
        Kruskal(paredes);
    }
    
    private void Kruskal(List<Pared> paredes){
        Random RNG=new Random();
        Pared aux;
        int pos;
        while(!paredes.estaVacia()){
            pos=RNG.nextInt(paredes.size());
            aux=paredes.get(pos);
            paredes.delete(pos);
            if(aux.tirable()){
                for(int i=0;i<tamY;i++){
                    for(int j=0;j<tamX;j++){
                        if(Objects.equals(salas[i][j].getKruskal(), aux.getSala2().getKruskal()))
                            salas[i][j].setKruskal(aux.getSala1().getKruskal());
                    }
                }
                laberinto.nuevoArco(aux.getSala1().getID(), aux.getSala2().getID(), 1);
            }
        }
    }

    private void generarParedes(List<Pared> paredes){
        for(int i=0;i<tamY;i++){
            for(int j=0;j<tamX;j++){
                if(i!=0){
                    paredes.addLast(new Pared(salas[i][j], salas[i-1][j]));
                }
                if(j!=tamX-1){
                    paredes.addLast(new Pared(salas[i][j], salas[i][j+1]));
                }
                if(i!=tamY-1){
                    paredes.addLast(new Pared(salas[i][j], salas[i+1][j]));
                }
                if(j!=0){
                    paredes.addLast(new Pared(salas[i][j], salas[i][j-1]));
                }
            }
        }
    }
    
    public void nuevoPersonaje(Personaje p){
        personajes.addLast(p);
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

    public boolean esAccesible(int IDS1, int IDS2){
        return laberinto.adyacente(IDS1, IDS2);
    }
    public void distribuirLlaves() {
        int numLlavesGenerar = 45;
        List<Integer> salasLlaves=Repartidor.sortByFrequence(iPuerta*tamX+jPuerta, laberinto, tamX*tamY);
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
        for (int i=0;i<salasLlaves.size();i++) {
            int x = salasLlaves.get(i) % tamX;
            int y = salasLlaves.get(i) / tamX;
            for (int contadorl = 0; contadorl < 5 && k<llavesGen.length-1; contadorl++) {
                salas[y][x].nuevaLlave(llavesGen[k]);
                k++;
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
        int SalaPuerta = iPuerta * tamY + tamX;
        System.out.println("turno:" + turno);
        System.out.println("mapa:" + SalaPuerta);
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
                    System.out.println("sala:" + salas[i][j].getID() + ":" + salas[i][j].getLlaves());
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

    public Sala getSala(int i, int j) {
        return salas[i][j];
    }

    public Integer getTamX() {
        return tamX;
    }

    public Integer getTamY() {
        return tamY;
    }

    public Sala getSalaTrono() {
        return trono;
    }

    public int getTurno() {
        return turno;
    }

    public Puerta getPuerta() throws NotKingsLandingException {
        return salas[iPuerta][jPuerta].getPuerta();
    }

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

    public boolean puertaAbierta() throws NotKingsLandingException {
        return salas[iPuerta][jPuerta].getPuerta().estaAbierta();
    }

    public void insertarPersonaje(Personaje p) {
        int i = p.init(this) / tamX;
        int j = p.init(this) % tamX;
        salas[i][j].nuevoPersonaje(p, true);
    }

    public void showMapa(){
        for(int i=0;i<tamX;i++){
            System.out.println("_");
        }
        for(int i=0;i<tamY;i++){
            System.out.println("|");
            for(int j=0;j<tamX;j++){
                System.out.println(salas[i][j].showSala(this));
            }
            System.out.println("|");
        }
    }
    /**
     * Programa principal - EC2
     *
     * @param args Argumentos de línea de comandos
     * @throws DP.Exceptions.MapSizeException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws MapSizeException, IOException {
        int numLlaves = 15;
        //int X = 6;
        //int Y = 6;
        //int salaPuerta = (X * Y) - 1;
        //int profComb = 4;
        //Creación del mapa
        //Mapa m = new Mapa(salaPuerta, X, Y, profComb);
        Cargador cargador=new Cargador();
        Mapa m=FicheroCarga.procesarFichero("inicio.txt", cargador);
        m.distribuirLlaves();
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
//        Personaje[] personajes = new Personaje[4];
//        personajes[0] = new Stark("Jonathan", 'J', 1);
//        personajes[1] = new Targaryen("Speedwagon", 'S', 1);
//        personajes[2] = new Lannister("Kars", 'K', 1);
//        personajes[3] = new CaminanteBlanco("DIO", 'D', 1);
//        
//        Dir[] ruta = {Dir.S, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.N, Dir.E, Dir.S, Dir.E, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.S};
//        personajes[0].setRuta(ruta);
//        Dir[] ruta2 = {Dir.E, Dir.S, Dir.S, Dir.S, Dir.O, Dir.S, Dir.E, Dir.E, Dir.N, Dir.E, Dir.S, Dir.S, Dir.E, Dir.E};
//        personajes[1].setRuta(ruta2);
//        Dir[] ruta3 = {Dir.N, Dir.N, Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.O, Dir.O, Dir.N, Dir.N, Dir.O, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S, Dir.E, Dir.E, Dir.E, Dir.E, Dir.E};
//        personajes[2].setRuta(ruta3);
//        Dir[] ruta4 = {Dir.N, Dir.N, Dir.N, Dir.E, Dir.S, Dir.E, Dir.N, Dir.N, Dir.E, Dir.N, Dir.E, Dir.E, Dir.S, Dir.S, Dir.S, Dir.S, Dir.S};
//        personajes[3].setRuta(ruta4);
//
//        //Inserción de personajes
//        for (int i = 0; i < 4; i++) {
//            m.insertarPersonaje(personajes[i]);
//        }
//
//        m.mostrarMapa();
//
//        //Simulación
//        boolean abierta = false;
//        for (int i = 0; i < 50 && !abierta; i++) {
//            m.simularTurno();
//            try {
//                abierta = m.puertaAbierta();
//            } catch (NotKingsLandingException ex) {
//                System.err.println("¿El mapa no fue inicializado?");
//            }
//        }
//        m.mostrarMapa();
    }

}
