package GameOfThrones;

import DP.ED.Arbol;

/**
 * Implementación de la puerta
 *
 * @version 1.0
 * @author Juan Luis Herrera González
 * Curso: 2º (Grupo Grande A)
 */
public class Puerta {

    /**
     * Combinación de llaves
     */
    private Arbol <Llave> comb;
    /**
     * Llaves probadas
     */
    private Arbol <Llave> probadas;
    /**
     * Indica si la puerta está abierta o cerrada
     */
    private boolean abierta;
    /**
     * Combinación para la que está configurada la puerta
     */
    private Llave[] combinacion;
    /**
     * Altura de la combinación
     */
    private int altura;

    /**
     * Constructor parametrizado de Puerta
     */
    public Puerta() {
        comb = new Arbol();
        probadas = new Arbol();
        abierta = false;
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
        for (int i = 0; i < combinacion.length; i++) {
            comb.insertar(combinacion[i]);
        }
    }

    /**
     * Cierra la puerta
     */
    public void cerrar() {
        abierta = false;
        comb = new Arbol();
        probadas = new Arbol();
        this.configurar(combinacion);
    }

    /**
     * Intenta abrir la puerta
     *
     * @param llave Llave insertada en la puerta
     */
    public void abrir(Llave llave) {
        if (!probadas.pertenece(llave)) {
            if (comb.pertenece(llave)) {
                comb.borrar(llave);
                System.out.println("Llave " + llave.identificar() + " insertada");
            } else {
                System.out.println("La llave " + llave.identificar() + " no coincide");
            }
            probadas.insertar(llave);
            Integer[] nodos = {0, 0};
            comb.tiposNodos(nodos);
            if (nodos[0] <= nodos[1] && comb.profundidad() < altura) {
                abierta = true;
            }
        } else {
            System.out.println("Esta llave ya se ha probado");
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
     * @return Llaves en la cerradura
     */
    public int llavesCerr() {
        return comb.nodos();
    }

    /**
     * Devuelve el número de llaves probadas
     * @return Llaves probadas
     */
    public int llavesProb() {
        return probadas.nodos();
    }
}
