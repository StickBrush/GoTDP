package GameOfThrones;

import java.util.Random;
import DP.ED.*;

/**
 * Implementación del mapa
 *
 * @version 1.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A)
 * EC1
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
    
    /**
     * Constructor parametrizado de Mapa
     *
     * @param salaPuerta Sala de la puerta
     * @param X Número de columnas
     * @param Y Número de filas
     * @param profComb Profundidad de la combinación
     */
    public Mapa(int salaPuerta, int X, int Y, int profComb) {
        int numLlavesGenerar=45;
        Llave[] llavesGen=new Llave[numLlavesGenerar];
        int idLlave=0;
        for(int i=0;i<numLlavesGenerar;i++){
            llavesGen[i]=new Llave(idLlave);
            if(idLlave%2==1){
                i++;
                llavesGen[i]=new Llave(idLlave);
            }
            idLlave++;
        }
        tamX = X;
        tamY = Y;
        this.salaPuerta = salaPuerta;
        this.profComb = profComb;
        llaves=new List<Llave>();
        salas=new Sala[tamX][tamY];
        int contador=0;
        for(int i=0;i<tamY;i++){
            for(int j=0;j<tamX;j++){
                if(i!=tamX-1 || j!=tamY-1)
                    salas[i][j]=new Sala(contador);
                contador++;
            }
        }
        salas[tamX-1][tamY-1]=new SalaPuerta((tamX*tamY)-1);
        iPuerta=tamX-1;
        jPuerta=tamY-1;
        trono=new Sala(1111);
        Integer[] SalasLlaves={3, 4, 6, 8, 9, 10, 11, 12, 13};
        Integer k=0;
        boolean found=false;
        for(Integer i : SalasLlaves){
            found=false;
            for(int im=0;im<tamY && !found;im++){
                for(int jm=0;jm<tamX && !found;jm++){
                    if(salas[im][jm].getID()==i){
                        found=true;
                        for(int contadorl=0;contadorl<5;contadorl++){
                            salas[im][jm].nuevaLlave(llavesGen[k]);
                            k++;
                        }
                    }
                }
            }
        }
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
        //TO BE CONTINUED
    }
    
    public void simularTurno(){
        Cola<Personaje> colaSolicitudes= new Cola<Personaje>();
        Sala salaAux=null;
        Arbol<Character> personajesMovidos=new Arbol<Character>(); //Evita mover varias veces el mismo personaje.
        for(int i=0;i<tamX;i++){
            for(int j=0;j<tamY;j++){
                salaAux=salas[i][j];
                while(salaAux.tienePersonaje()){
                    colaSolicitudes.encolar(salaAux.primero());
                    salaAux.desencolar();
                }
                if(salaAux instanceof SalaPuerta){
                    Cola<Personaje> colaAux=new Cola<Personaje>();
                    while(!colaSolicitudes.vacia()){
                        try{
                            colaSolicitudes.primero().interactuarPuerta(salaAux.getPuerta());
                        }
                        catch(NotKingsLandingException ex){
                            System.err.println("Interactuada puerta que no existe");
                        }
                        finally{
                            colaAux.encolar(colaSolicitudes.primero());
                            colaSolicitudes.desencolar();
                        }
                    }
                    while(!colaAux.vacia()){
                        colaSolicitudes.encolar(colaAux.primero());
                        colaAux.desencolar();
                    }
                }
                else{
                    while(!colaSolicitudes.vacia()){
                    try{
                        switch(colaSolicitudes.primero().nextMove()){
                            case N:
                                if(!personajesMovidos.pertenece(colaSolicitudes.primero().getID())){
                                salas[i][j-1].nuevoPersonaje(colaSolicitudes.primero());
                                personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                }
                                colaSolicitudes.desencolar();
                                break;
                            case S:
                                if(!personajesMovidos.pertenece(colaSolicitudes.primero().getID())){
                                salas[i][j+1].nuevoPersonaje(colaSolicitudes.primero());
                                personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                }
                                colaSolicitudes.desencolar();
                                break;
                            case E:
                                if(!personajesMovidos.pertenece(colaSolicitudes.primero().getID())){
                                salas[i+1][j].nuevoPersonaje(colaSolicitudes.primero());
                                personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                }
                                colaSolicitudes.desencolar();
                                break;
                            case O:
                                if(!personajesMovidos.pertenece(colaSolicitudes.primero().getID())){
                                salas[i-1][j].nuevoPersonaje(colaSolicitudes.primero());
                                personajesMovidos.insertar(colaSolicitudes.primero().getID());
                                }
                                colaSolicitudes.desencolar();
                                break;
                        }
                    }
                    catch(NoMovesLeftException ex){
                        System.err.println("A ese personaje no le quedan movimientos");
                        salaAux.nuevoPersonaje(colaSolicitudes.primero());
                        colaSolicitudes.desencolar();
                    }
                    catch(ArrayIndexOutOfBoundsException ex){
                        System.err.println("Te has salido de los límites");
                        salaAux.nuevoPersonaje(colaSolicitudes.primero());
                        colaSolicitudes.desencolar();
                    }
                }
                }
            }
        }
    }
    
    public void insertarPersonaje(Personaje p){
        if(p instanceof Stark || p instanceof Targaryen)
            salas[0][0].nuevoPersonaje(p);
        else{
            if(p instanceof CaminanteBlanco)
                salas[tamY-1][0].nuevoPersonaje(p);
            else
                salas[tamY-1][tamX-1].nuevoPersonaje(p);
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
        //Dado que no conozco personajes de Juego de Tronos, los nombres son de JoJo's Bizarre Adventure
        Personaje[] personajes=new Personaje[8];
        personajes[0]=new Stark("Jonathan", 'J');
        personajes[1]=new Stark("Joseph", 'O');
        personajes[2]=new Targaryen("Speedwagon", 'S');
        personajes[3]=new Targaryen("Zeppeli", 'Z');
        personajes[4]=new Lannister("Santana", 'A');
        personajes[5]=new Lannister("Kars", 'K');
        personajes[6]=new CaminanteBlanco("Dio Brando", 'B');
        personajes[7]=new CaminanteBlanco("DIO", 'D');
        
        //Sistema de generación de rutas
        Random RNG = new Random(); //Generador de números aleatorios (RNG)
        Orientacion[] ruta=new Orientacion[30];
        for(int pi=0;pi<8;pi++){
            for(int i=0;i<30;i++){
                switch(RNG.nextInt(4)){
                    case 0:
                        ruta[i]=Orientacion.N;
                        break;
                    case 1:
                        ruta[i]=Orientacion.S;
                        break;
                    case 2:
                        ruta[i]=Orientacion.E;
                        break;
                    case 3:
                        ruta[i]=Orientacion.O;
                        break;
                }
            }
            personajes[pi].setRuta(ruta);
        }
        //Inserción de personajes
        for(int i=0;i<8;i++){
            m.insertarPersonaje(personajes[i]);
        }
        
        //Simulación
        for(int i=0;i<50;i++){
            m.simularTurno();
        }
    }

}
