package GameOfThrones;

/**
 * Implementación del mapa
 * @version 1.0
 * @author Juan Luis Herrera González
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

    public void mostrarMapa(){
        System.out.println("Salida: " + salaPuerta);
        if(p.estaAbierta())
            System.out.println("Puerta: Abierta. Altura: " + profComb);
        else
            System.out.println("Puera: Cerrada. Altura: " + profComb);
        System.out.println("Llaves en cerradura: " + p.llavesCerr() +". Llaves probadas: " + p.llavesProb());
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
        Llave[] combLlaves=new Llave[numLlaves];
        for (int i = 0; i < listaLlaves.length; i++) {
            combLlaves[i]=new Llave(listaLlaves[i]);
        }
        //Creación, configuración e inserción de la puerta
        Puerta p=new Puerta();
        p.configurar(combLlaves);
        m.insertarPuerta(p);
        Llave[] test={new Llave(3), new Llave(15), new Llave(2), new Llave(29), new Llave(1), new Llave(3), new Llave(7)};
        for(int i=0;i<test.length;i++){
            p.abrir(test[i]);
            p.mostrarCerradura();
        }
        m.mostrarMapa();
    }

}
