package DP.util;

/**
 * Clase creada para ser usada en la utilidad cargador estudiada previamente en
 * sesi�n práctica "Excepciones"
 *
 * @version 1.0 - 02/11/2011
 * @author Profesores DP
 */
import DP.Exceptions.MapSizeException;
import DP.GameOfThrones.Mapa;
import DP.Personajes.Personaje;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FicheroCarga {

    /**
     * atributo de la clase que indica el numero máximo de campos que se pueden
     * leer
     */
    public static final int MAXCAMPOS = 10;

    /**
     * buffer para almacenar el flujo de entrada
     */
    private static BufferedReader bufferIn;

    /**
     * Metodo para procesar el fichero
     *
     * @param nombreFichero Nombre del fichero a procesar
     * @param cargador Cargador usado para procesar
     * @return Mapa creado con los personajes en la lista
     * @throws java.io.FileNotFoundException No se encontró archivo
     * @throws java.io.IOException Error de E/S
     */
    public static Mapa procesarFichero(String nombreFichero, Cargador cargador) throws FileNotFoundException, IOException {
        //**String vCampos[]=new String[MAXCAMPOS];
        List<String> vCampos = new ArrayList<String>();
        List<Personaje> personajes = new ArrayList<>();
        Mapa m = null;
        Object o;
        String S = new String();
        int numCampos = 0;
        bufferIn = new BufferedReader(new FileReader(nombreFichero));//creaci�n del filtro asociado al flujo de datos

        while ((S = bufferIn.readLine()) != null) {
            System.out.println("S: " + S);
            if (!S.startsWith("--")) {
                vCampos.clear();
                numCampos = trocearLinea(S, vCampos);
                o = cargador.crear(vCampos.get(0), numCampos, vCampos);
                if (o instanceof Mapa) {
                    m = (Mapa) o;
                } else if (o instanceof Personaje) {
                    personajes.add((Personaje) o);
                }
            }
        }
        bufferIn.close();	     //cerramos el filtro
        if (m == null) {
            System.err.println("ERROR:No se dieron parámetros para el mapa");
            System.err.println("Creando mapa por defecto...");
            try {
                m = Mapa.getInstance(35, 6, 6, 4);
            } catch (MapSizeException w) {
                System.err.println("Esto no pasará");
            }
        }
        for (Personaje p : personajes) {
            m.nuevoPersonaje(p);
        }
        return m;
    }

    /**
     * Metodo para trocear cada l�nea y separarla por campos
     *
     * @param S cadena con la l�nea completa le�da
     * @param vCampos. Array de String que contiene en cada posici�n un campo
     * distinto
     * @return numCampos. N�mero campos encontrados
     */
    private static int trocearLinea(String S, List<String> vCampos) {
        boolean eol = false;
        int pos = 0, posini = 0, numCampos = 0;

        while (!eol) {
            pos = S.indexOf("#");
            if (pos != -1) {
                vCampos.add(new String(S.substring(posini, pos)));
                S = S.substring(pos + 1, S.length());
                numCampos++;
            } else {
                eol = true;
            }
        }
        return numCampos;
    }

}
