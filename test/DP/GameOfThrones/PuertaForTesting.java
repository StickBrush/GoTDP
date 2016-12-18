package DP.GameOfThrones;

/**
 * Puerta para pruebas
 *
 * @version 4.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC4
 */
public class PuertaForTesting extends Puerta {

    /**
     * Instancia (patrón Singleton)
     */
    private static PuertaForTesting instance = null;

    /**
     * Constructor por defecto de PuertaForTesting
     */
    private PuertaForTesting() {
        super();
    }

    /**
     * GetInstance de PuertaForTesting
     *
     * @return Instancia única
     */
    public static PuertaForTesting getInstance() {
        if (instance == null) {
            instance = new PuertaForTesting();
        }
        return instance;
    }

    /**
     * Fuerza la instancia a null
     */
    public static void forceNull() {
        instance = null;
    }

    /**
     * Devuelve la altura
     *
     * @return Altura
     */
    public int getAltura() {
        return this.altura;
    }

    /**
     * Abre la puerta
     */
    public void forceOpen() {
        this.abierta = true;
    }
}
