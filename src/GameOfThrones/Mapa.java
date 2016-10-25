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

    /**
     * Puerta del trono
     */
    private Puerta p;
    
    private List<Llave> llaves;
    
    private Sala[][] salas;
    
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
        this.salaPuerta = salaPuerta;
        this.profComb = profComb;
        llaves=new List<Llave>();
        salas=new Sala[tamX][tamY];
    }

    /**
     * Inserta una puerta en el mapa
     *
     * @param p Puerta a insertar
     */
    public void insertarPuerta(Puerta p) {
        this.p = p;
        p.setAltura(profComb);
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
        System.out.println("Mapa de tamaño " + tamX + "x" + tamY);
        System.out.println("Salida: " + salaPuerta);
        if (p.estaAbierta()) {
            System.out.println("Puerta: Abierta. Altura: " + profComb);
        } else {
            System.out.println("Puera: Cerrada. Altura: " + profComb);
        }
        System.out.println("Llaves en cerradura: " + p.llavesCerr() + ". Llaves probadas: " + p.llavesProb());
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
        //Se prueba la secuencia dada como ejemplo en la documentación
        Llave[] test = {new Llave(1), new Llave(5), new Llave(4), new Llave(9), new Llave(6), new Llave(17), new Llave(13), new Llave(20), new Llave(21), new Llave(2), new Llave(8), new Llave(27), new Llave(25), new Llave(29)};
        for (Llave testkey : test) {
            p.abrir(testkey);
            p.mostrarCerradura();
        }
        m.mostrarMapa();
        p.cerrar();
        //Probando llaves que no coinciden en la puerta. Números pares, altos, cero y negativos
        Llave[] testFail = {new Llave(2), new Llave(315), new Llave(0), new Llave(-46)};
        for (Llave testkey : testFail) {
            p.abrir(testkey);
        }
        p.cerrar();
        //Probando llaves repetidas
        Llave[] testRepeat = {new Llave(3), new Llave(3)};
        for (Llave testkey : testRepeat) {
            p.abrir(testkey);
        }
        p.cerrar();
        //Probando con valores aleatorios
        int pruebaAleatoria = 500; //Veces que se hará la prueba con llaves al azar
        Random RNG = new Random(); //Generador de números aleatorios (RNG)
        Llave testkey; //Llave para asignar valores aleatorios
        for (int i = 0; i < pruebaAleatoria; i++) {
            testkey = new Llave(RNG.nextInt());
            p.abrir(testkey);
        }
        p.cerrar();
    }

}
