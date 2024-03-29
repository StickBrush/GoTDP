package DP.ED;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Queue;
import java.util.LinkedList;

/**
 * @file grafo.h Declaracion de la clase grafo
 * @author Profesores DP Asignatura Desarrollo de Programa Curso 11/12
 */
public class Grafo {

    public static final int MAXVERT = 115;
    public static final int INFINITO = 9999;
    public static final int NOVALOR = -1;

    /**
     * Numero de nodos del grafo
     */
    private int numNodos;

    /**
     * Vector que almacena los nodos del grafo
     */
    private int[] nodos = new int[MAXVERT];

    /**
     * Matriz de adyacencia, para almacenar los arcos del grafo
     */
    private int[][] arcos = new int[MAXVERT][MAXVERT];

    /**
     * Matriz de Camino (Warshall - Path)
     */
    private boolean[][] warshallC = new boolean[MAXVERT][MAXVERT];

    /**
     * Matriz de Costes (Floyd - Cost)
     */
    private int[][] floydC = new int[MAXVERT][MAXVERT];

    /**
     * Matriz de Camino (Floyd - Path)
     */
    private int[][] floydP = new int[MAXVERT][MAXVERT];

    /**
     * Metodo constructor por defecto de la clase grafo
     */
    public Grafo() {
        int x, y;
        numNodos = 0;

        for (x = 0; x < MAXVERT; x++) {
            nodos[x] = NOVALOR;
        }

        for (x = 0; x < MAXVERT; x++) {
            for (y = 0; y < MAXVERT; y++) {
                arcos[x][y] = INFINITO;
                warshallC[x][y] = false;
                floydC[x][y] = INFINITO;
                floydP[x][y] = NOVALOR;
            }
        }

        // Diagonales
        for (x = 0; x < MAXVERT; x++) {
            arcos[x][x] = 0;
            warshallC[x][x] = false;
            floydC[x][x] = 0;
            //floydP[x][x]=NO_VALOR;
        }
    }

    /**
     * Metodo que comprueba si el grafo esta vacio
     *
     * @return Retorna un valor booleano que indica si el grafo esta o no vacio
     */
    public boolean esVacio() {
        return (numNodos == 0);
    }

    /**
     * Metodo que inserta un nuevo arco en el grafo
     *
     * @param origen es el nodo de origen del arco nuevo
     * @param destino es el nodo de destino del arco nuevo
     * @return true si se pudo insertar
     */
    public boolean nuevoArco(int origen, int destino) {
        boolean resultado = false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
            arcos[origen][destino] = 1;
            arcos[destino][origen] = 1;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Metodo que borra un arco del grafo
     *
     * @param origen es el nodo de origen del arco nuevo
     * @param destino es el nodo de destino del arco nuevo
     * @return true si se pudo borrar
     */
    public boolean borraArco(int origen, int destino) {
        boolean resultado = false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
            arcos[origen][destino] = INFINITO;
            arcos[destino][origen] = INFINITO;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Metodo que comprueba si dos nodos son adyacentes
     *
     * @param origen es el primer nodo
     * @param destino es el segundo nodo
     * @return Retorna un valor booleano que indica si los dos nodos son
     * adyacentes
     */
    public boolean adyacente(int origen, int destino) {
        boolean resultado = false;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
            resultado = (arcos[origen][destino] != INFINITO);
        }
        return resultado;
    }

    /**
     * Metodo que retorna el peso de un arco
     *
     * @param origen es el primer nodo del arco
     * @param destino es el segundo nodo del arco
     * @return Retorna un valor entero que contiene el peso del arco
     */
    public int getArco(int origen, int destino) {
        int arco = NOVALOR;
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
            arco = arcos[origen][destino];
        }
        return arco;
    }

    /**
     * Metodo que inserta un nuevo nodo en el grafo
     *
     * @param n es el nodo que se desea insertar
     * @return true si se pudo insertar
     */
    public boolean nuevoNodo(int n) {
        boolean resultado = false;

        if (numNodos < MAXVERT) {
            nodos[numNodos] = n;
            numNodos++;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Metodo que elimina un nodo del grafo
     *
     * @param nodo nodo que se desea eliminar
     * @return true si se pudo borrar
     */
    public boolean borraNodo(int nodo) {
        boolean resultado = false;
        int pos = nodo;

        if ((numNodos > 0) && (pos <= numNodos)) {
            int x, y;
            // Borrar el nodo
            for (x = pos; x < numNodos - 1; x++) {
                nodos[x] = nodos[x + 1];
                System.out.println(nodos[x + 1]);
            }
            // Borrar en la Matriz de Adyacencia
            // Borra la fila
            for (x = pos; x < numNodos - 1; x++) {
                for (y = 0; y < numNodos; y++) {
                    arcos[x][y] = arcos[x + 1][y];
                }
            }
            // Borra la columna
            for (x = 0; x < numNodos; x++) {
                for (y = pos; y < numNodos - 1; y++) {
                    arcos[x][y] = arcos[x][y + 1];
                }
            }
            // Decrementa el numero de nodos
            numNodos--;
            resultado = true;
        }
        return resultado;
    }

    /**
     * Metodo que muestra el vector de nodos del grafo
     */
    public void mostrarNodos() {
        System.out.println("NODOS:");
        for (int x = 0; x < numNodos; x++) {
            System.out.print(nodos[x] + " ");
        }
        System.out.println();
    }

    /**
     * Metodo que muestra los arcos del grafo (la matriz de adyacencia)
     */
    public void mostrarArcos() {
        int x, y;

        System.out.println("ARCOS:");
        for (x = 0; x < numNodos; x++) {
            for (y = 0; y < numNodos; y++) {
                //cout.width(3);
                if (arcos[x][y] != INFINITO) {
                    System.out.format("%4d", arcos[x][y]);
                } else {
                    System.out.format("%4s", "#");
                }
            }
            System.out.println();
        }
    }

    /**
     * Metodo que devuelve el conjunto de nodos adyacentes al nodo actual
     *
     * @param origen es el nodo actual
     * @param ady En este conjunto se almacenarán los nodos adyacentes al nodo
     * origen
     */
    public void adyacentes(int origen, Set<Integer> ady) {
        if ((origen >= 0) && (origen < numNodos)) {
            for (int i = 0; i < numNodos; i++) {
                if ((arcos[origen][i] != INFINITO) && (arcos[origen][i] != 0)) {
                    ady.add(i);
                }
            }
        }
    }

    /**
     * Metodo que muestra la matriz de Warshall
     */
    public void mostrarPW() {
        int x, y;

        System.out.println("warshallC:");
        for (x = 0; x < numNodos; x++) {
            for (y = 0; y < numNodos; y++) {
                System.out.format("%6b", warshallC[x][y]);
            }
            System.out.println();
        }
    }

    /**
     * Metodo que muestra las matrices de coste y camino de Floyd
     */
    public void mostrarFloydC() {
        int x, y;
        System.out.println("floydC:");
        for (y = 0; y < numNodos; y++) {
            for (x = 0; x < numNodos; x++) {
                //cout.width(3);
                System.out.format("%4d", floydC[x][y]);
            }
            System.out.println();
        }

        System.out.println("floydP:");
        for (x = 0; x < numNodos; x++) {
            for (y = 0; y < numNodos; y++) {
                //cout.width(2);
                System.out.format("%4d", floydP[x][y]);
            }
            System.out.println();
        }
    }

    /**
     * Metodo que realiza el algoritmo de Warshall sobre el grafo
     */
    public void warshall() {
        int i, j, k;

        // Obtener la matriz de adyacencia en P
        for (i = 0; i < numNodos; i++) {
            for (j = 0; j < numNodos; j++) {
                warshallC[i][j] = (arcos[i][j] != INFINITO);
            }
        }

        // Iterar
        for (k = 0; k < numNodos; k++) {
            for (i = 0; i < numNodos; i++) {
                for (j = 0; j < numNodos; j++) {
                    warshallC[i][j] = (warshallC[i][j] || (warshallC[i][k] && warshallC[k][j]));
                }
            }
        }
    }

    /**
     * Metodo que realiza el algoritmo de Floyd sobre el grafo
     */
    public void floyd() {
        int i, j, k;

        // Obtener la matriz de adyacencia en P
        for (i = 0; i < numNodos; i++) {
            for (j = 0; j < numNodos; j++) {
                floydC[i][j] = arcos[i][j];
                floydP[i][j] = NOVALOR;
            }
        }

        // Iterar
        for (k = 0; k < numNodos; k++) {
            for (i = 0; i < numNodos; i++) {
                for (j = 0; j < numNodos; j++) {
                    if (i != j) {
                        if ((floydC[i][k] + floydC[k][j] < floydC[i][j])) {
                            floydC[i][j] = floydC[i][k] + floydC[k][j];
                            floydP[i][j] = k;
                        }
                    }
                }
            }
        }
    }

    /**
     * Metodo que devuelve el siguiente nodo en la ruta entre un origen y un
     * destino
     *
     * @param origen es el primer nodo
     * @param destino es el segundo nodo
     * @return Siguiente nodo
     */
    public int siguiente(int origen, int destino) {
        int sig = -1; // Si no hay camino posible
        if ((origen >= 0) && (origen < numNodos) && (destino >= 0) && (destino < numNodos)) {
            if (warshallC[origen][destino]) { // Para comprobar que haya camino
                if (floydP[origen][destino] != NOVALOR) {
                    sig = siguiente(origen, floydP[origen][destino]);
                } else {
                    sig = destino;
                }
            }
        }
        return sig;
    }

    /**
     * Genera la ruta más corta entre origen y destino
     *
     * @param origen Nodo del que partir
     * @param destino Nodo al que llegar
     * @return Ruta más corta
     */
    public Set<Integer> ruta(int origen, int destino) {
        Set<Integer> camino = new LinkedHashSet<Integer>();
        genRuta(origen, destino, camino);
        return camino;
    }

    /**
     * Generador de la ruta
     *
     * @param origen Origen de la ruta
     * @param destino Destino de la ruta
     * @param ruta Ruta que se irá actualizando
     */
    private void genRuta(int origen, int destino, Set<Integer> ruta) {
        int x = siguiente(origen, destino);
        ruta.add(x);
        if (x != destino) {
            genRuta(x, destino, ruta);
        }
    }

    /**
     * Recorrido en profundidad del grafo
     *
     * @param destino Nodo hasta el que recorrer
     * @return Recorrido en profundidad
     */
    public Set<Integer> profundidad(int destino) {
        return profundidad(0, new LinkedHashSet<Integer>(), destino);
    }

    /**
     * Recorre en profundidad el grafo
     *
     * @param origen Nodo origen
     * @param visitados Nodos visitados
     * @param destino Nodo destino
     * @return Recorrido en profundidad
     */
    private Set<Integer> profundidad(int origen, Set<Integer> visitados, int destino) {
        Set<Integer> Ady = new LinkedHashSet<>();
        int x;
        visitados.add(origen);
        if (origen == destino) {
            return visitados;
        } else {
            this.adyacentes(origen, Ady);
            while (!Ady.isEmpty()) {
                x = Ady.iterator().next();
                Ady.remove(x);
                if (!visitados.contains(x)) {
                    Set<Integer> sol = profundidad(x, visitados, destino);
                    if (sol != null) {
                        return sol;
                    }
                }
            }
        }
        visitados.remove(origen);
        return null;
    }

    /**
     * Hace el camino mínimo del grafo
     *
     * @param origen Nodo origen
     * @param destino Nodo destino
     * @return Camino mínimo de origen a destino
     */
    public Set<Integer> caminoMinimo(int origen, int destino) {
        Set<Integer> res = new LinkedHashSet<>();
        while (origen != destino) {
            res.add(origen);
            origen = this.siguiente(origen, destino);
        }
        res.add(origen);
        return res;
    }

    /**
     * Devuelve todos los posibles caminos de origen a destino
     *
     * @param origen Nodo del que partir
     * @param destino Nodo al que llegar
     * @return Todos los posibles caminos origen->destino
     */
    public Set<Set<Integer>> caminos(int origen, int destino) {
        Set<Set<Integer>> caminos = new LinkedHashSet<>();
        Set<Integer> visitados = new LinkedHashSet<>();
        this.warshall();
        this.floyd();
        genCamino(origen, destino, visitados, caminos);
        return caminos;

    }

    /**
     * Copia un set
     *
     * @param original Set original
     * @return Set copia
     */
    private static Set<Integer> setCopy(Set<Integer> original) {
        Set<Integer> nuevo = new LinkedHashSet<>();
        nuevo.addAll(original);
        return nuevo;
    }

    /**
     * Generador de todos los caminos
     *
     * @param origen Nodo actual
     * @param destino Destino final
     * @param visitados Nodos visitados
     * @param caminos Caminos posibles
     */
    private void genCamino(int origen, int destino, Set<Integer> visitados, Set<Set<Integer>> caminos) {
        Set<Integer> Ady = new LinkedHashSet<>();
        int x;
        visitados.add(origen);
        if (origen == destino) {
            caminos.add(setCopy(visitados));
        } else {
            this.adyacentes(origen, Ady);
            while (!Ady.isEmpty()) {
                x = Ady.iterator().next();
                Ady.remove(x);
                if (!visitados.contains(x)) {
                    genCamino(x, destino, visitados, caminos);
                }
            }
        }
        visitados.remove(origen);
    }

    /**
     * Devuelve el nodo más conectado
     *
     * @return Nodo con mayor número de arcos de salida
     */
    public int nodoConectado() {
        int nodoMax = 0;
        int contMax = 0;
        if (!esVacio()) {
            nodoMax = nodos[0];
            for (int i = 0; i < numNodos; i++) {
                int cont = 0;
                for (int j = 0; j < numNodos; j++) {
                    if (arcos[i][j] != INFINITO) {
                        cont++;
                    }
                }
                if (cont > contMax) {
                    nodoMax = nodos[i];
                    contMax = cont;
                }
            }
        }
        return nodoMax;
    }

    /**
     * Recorrido en anchura del grafo
     */
    public void anchura() {
        if (!esVacio()) {
            Set<Integer> visitados = new LinkedHashSet<>();
            recAnchura(nodos[0], visitados);
        }
    }

    /**
     * Recorrido en anchura
     *
     * @param origen Nodo actual
     * @param visitados Nodos visitados
     */
    public void recAnchura(int origen, Set<Integer> visitados) {
        int x;
        Queue<Integer> q = new LinkedList<>();
        visitados.add(origen);
        q.add(origen);
        while (!q.isEmpty()) {
            origen = q.peek();
            q.remove();
            System.out.println(origen);
            Set<Integer> Ady = new LinkedHashSet<>();
            adyacentes(origen, Ady);
            while (!Ady.isEmpty()) {
                x = Ady.iterator().next();
                Ady.remove(x);
                if (!visitados.contains(x)) {
                    visitados.add(x);
                    q.add(x);
                }
            }
        }
    }
}
