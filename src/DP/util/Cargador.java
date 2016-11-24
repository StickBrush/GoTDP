package DP.util;

import DP.Exceptions.MapSizeException;
import java.util.List;
import DP.GameOfThrones.Mapa;
import DP.Personajes.*;

/**
 * Clase creada para ser usada en la utilidad cargador contiene el main del
 * cargador. Se crea una instancia de la clase Estacion, una instancia de la
 * clase Cargador y se procesa el fichero de inicio, es decir, se leen todas las
 * líneas y se van creando todas las instancias de la simulación
 *
 * @version 5.0 - 27/10/2016
 * @author Profesores DP
 */
public class Cargador {

    /**
     * número de elementos distintos que tendrá la simulación - Mapa, Stark,
     * Lannister, Baratheon, Targaryen
     */
    static final int NUMELTOSCONF = 5;
    /**
     * atributo para almacenar el mapeo de los distintos elementos
     */
    static private DatoMapeo[] mapeo;

    /**
     * constructor parametrizado
     *
     * @param e referencia a la instancia del patrón Singleton
     */
    public Cargador() {
        mapeo = new DatoMapeo[NUMELTOSCONF];
        mapeo[0] = new DatoMapeo("MAPA", 5);
        mapeo[1] = new DatoMapeo("STARK", 4);
        mapeo[2] = new DatoMapeo("TARGARYEN", 4);
        mapeo[3] = new DatoMapeo("LANNISTER", 4);
        mapeo[4] = new DatoMapeo("CAMINANTE", 4);

    }

    /**
     * busca en mapeo el elemento leído del fichero inicio.txt y devuelve la
     * posición en la que está
     *
     * @param elto elemento a buscar en el array
     * @return res posición en mapeo de dicho elemento
     */
    private int queElemento(String elto) {
        int res = -1;
        boolean enc = false;

        for (int i = 0; (i < NUMELTOSCONF && !enc); i++) {
            if (mapeo[i].getNombre().equals(elto)) {
                res = i;
                enc = true;
            }
        }
        return res;
    }

    /**
     * método que crea las distintas instancias de la simulación
     *
     * @param elto nombre de la instancia que se pretende crear
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo de la
     * instancia
     * @return
     */
    public Object crear(String elto, int numCampos, List<String> vCampos) {
        //Si existe elemento y el n�mero de campos es correcto, procesarlo... si no, error
        int numElto = queElemento(elto);
        Object ret = null;

        //Comprobaci�n de datos básicos correctos
        if ((numElto != -1) && (mapeo[numElto].getCampos() == numCampos)) {
            //procesar
            switch (numElto) {
                case 0:
                    ret = crearMapa(numCampos, vCampos);
                    break;
                case 1:
                    ret = crearStark(numCampos, vCampos);
                    break;
                case 2:
                    ret = crearTargaryen(numCampos, vCampos);
                    break;
                case 3:
                    ret = crearLannister(numCampos, vCampos);
                    break;
                case 4:
                    ret = crearCaminante(numCampos, vCampos);
                    break;
            }
        } else {
            System.out.println("ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos + "\n");
        }
        return ret;
    }

    /**
     * método que crea una instancia de la clase Planta
     *
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo
     */
    private Mapa crearMapa(int numCampos, List<String> vCampos) {
        Mapa m = null;
        try {
            m = new Mapa(Integer.parseInt(vCampos.get(1)), Integer.parseInt(vCampos.get(2)), Integer.parseInt(vCampos.get(2)), Integer.parseInt(vCampos.get(4)));
        } catch (MapSizeException ex) {
            System.err.println("Tamaño del mapa inválido");
            System.err.println("Creando mapa por defecto...");
            try {
                m = new Mapa(35, 5, 5, 4);
            } catch (MapSizeException w) {
                System.err.println("Esto no pasará");
            }
        }
        return m;
    }

    /**
     * método que crea una instancia de la clase Stark
     *
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo
     */
    private Stark crearStark(int numCampos, List<String> vCampos) {
        return new Stark(vCampos.get(1), (char) vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)));
    }

    /**
     * método que crea una instancia de la clase Targaryen
     *
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo
     */
    private Targaryen crearTargaryen(int numCampos, List<String> vCampos) {
        return new Targaryen(vCampos.get(1), (char) vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)));
    }

    /**
     * método que crea una instancia de la clase Lannister
     *
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo
     */
    private Lannister crearLannister(int numCampos, List<String> vCampos) {
        return new Lannister(vCampos.get(1), (char) vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)));
    }

    /**
     * método que crea una instancia de la clase White Walker
     *
     * @param numCampos número de atributos que tendrá la instancia
     * @param vCampos array que contiene los valores de cada atributo
     */
    private CaminanteBlanco crearCaminante(int numCampos, List<String> vCampos) {
        return new CaminanteBlanco(vCampos.get(1), (char) vCampos.get(2).charAt(0), Integer.parseInt(vCampos.get(3)));
    }

}
