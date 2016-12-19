package DP.GameOfThrones;

import DP.ED.Arbol;

/**
 * Implementación de la puerta
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class Puerta {

    /**
     * Combinación de llaves
     */
    private Arbol<Llave> comb;
    /**
     * Llaves probadas
     */
    private Arbol<Llave> probadas;
    /**
     * Indica si la puerta está abierta o cerrada
     */
    protected boolean abierta;
    /**
     * Combinación para la que está configurada la puerta
     */
    private Llave[] combinacion;
    /**
     * Altura de la combinación
     */
    protected int altura;

    /**
     * Instancia (patrón Singleton)
     */
    private static Puerta instance = null;

    /**
     * Constructor parametrizado de Puerta
     */
    protected Puerta() {
        comb = new Arbol<Llave>();
        probadas = new Arbol<Llave>();
        abierta = false;
    }

    /**
     * Método getInstancia del patrón Singleton
     *
     * @return Instancia única de puerta
     */
    public static Puerta getInstance() {
        if (instance == null) {
            instance = new Puerta();
        }
        return instance;
    }

    /**
     * Modifica la altura de la combinación
     *
     * @param altura Nueva altura
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Configura la puerta con la combinación introducida
     *
     * @param combinacion Combinación para configurar la puerta
     */
    public void configurar(Llave[] combinacion) {
        this.combinacion = combinacion;
        for (Llave combinacion1 : combinacion) {
            comb.insertar(combinacion1);
        }
    }

    /**
     * Cierra la puerta
     */
    public void cerrar() {
        abierta = false;
        comb = new Arbol<Llave>();
        probadas = new Arbol<Llave>();
        this.configurar(combinacion);
    }

    /**
     * Intenta abrir la puerta
     *
     * @param llave Llave insertada en la puerta
     */
    public void abrir(Llave llave) {
        if (!probadas.pertenece(llave) && comb.pertenece(llave)) {
            comb.borrar(llave);
            probadas.insertar(llave);
            Integer[] nodos = {0, 0};
            comb.tiposNodos(nodos);
            if (nodos[0] <= nodos[1] && comb.profundidad() < altura) {
                abierta = true;
            }
        }
    }

    /**
     * Devuelve si la puerta está abierta o no
     *
     * @return True si está abierta, false en caso contrario
     */
    public boolean estaAbierta() {
        return abierta;
    }

    /**
     * Muestra el estado de la cerradura
     */
    public void mostrarCerradura() {
        if (abierta) {
            System.out.println("La puerta está abierta");
        } else {
            System.out.println("La puerta está cerrada");
        }
    }

    /**
     * Devuelve el número de llaves en la cerradura
     *
     * @return Llaves en la cerradura
     */
    public int llavesCerr() {
        return comb.nodos();
    }

    /**
     * Devuelve el número de llaves probadas
     *
     * @return Llaves probadas
     */
    public int llavesProb() {
        return probadas.nodos();
    }

    /**
     * Devuelve todas las llaves de la cerradura
     *
     * @return Llaves de la cerradura
     */
    public String cerradura() {
        String c;
        c = mostrarLlaves(comb);
        return c;
    }

    /**
     * Devuelve las llaves probads
     *
     * @return Llaves probadas
     */
    public String probadas() {
        String p;
        p = mostrarLlaves(probadas);
        return p;
    }

    /**
     * Muestra las llaves de un árbol
     *
     * @param a Árbol a mostrar
     * @return Árbol concatenado (inOrden)
     */
    private String mostrarLlaves(Arbol<Llave> a) {
        String s = new String();
        if (!a.vacio()) {
            if (a.getHijoIzq() != null) {
                s = s + mostrarLlaves(a.getHijoIzq());
            }
            s = s + a.getRaiz().toString() + " ";
            if (a.getHijoDer() != null) {
                s = s + mostrarLlaves(a.getHijoDer());
            }
        }
        return s;
    }

}
