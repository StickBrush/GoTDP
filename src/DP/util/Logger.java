package DP.util;

import DP.GameOfThrones.Mapa;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import DP.ED.List;

/**
 * Clase para crear el registro (log)
 *
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public class Logger {

    /**
     * Búfer del archivo del que hacer log
     */
    private BufferedWriter logger;
    /**
     * Estado del logger
     */
    private boolean funcional;

    /**
     * Constructor por defecto del Logger
     */
    public Logger() {
        funcional = true;
        try {
            logger = new BufferedWriter(new FileWriter("registro.log"));
        } catch (IOException ex) {
            System.err.println("IOException en Logger. Se continuará sin log.");
            funcional = false;
        }
    }

    /**
     * Registra el mapa
     *
     * @param m Mapa a registrar
     */
    public void logMapa(Mapa m) {
        if (funcional) {
            try {
                List<String> structure = m.structureString();
                for (int i = 0; i < structure.size(); i++) {
                    logger.write(structure.get(i) + "\n");
                }
            } catch (IOException ex) {
                System.err.println("IOException en Logger. Se continuará sin log.");
                funcional = false;
            }
        }
    }

    /**
     * Registra la información del mapa
     *
     * @param m Mapa a registrar
     */
    public void logInfoMapa(Mapa m) {
        if (funcional) {
            try {
                logger.write(m.infoMapa());
            } catch (IOException ex) {
                System.err.println("IOException en Logger. Se continuará sin log.");
                funcional = false;
            }
        }
    }

    /**
     * Registra las rutas de los personajes de un mapa
     *
     * @param m Mapa a registrar
     */
    public void logRutas(Mapa m) {
        if (funcional) {
            try {
                logger.write(m.rutas());
            } catch (IOException ex) {
                System.err.println("IOException en Logger. Se continuará sin log.");
                funcional = false;
            }
        }
    }

    /**
     * Termina el log
     */
    public void endLogger() {
        try {
            if (funcional) {
                logger.close();
            }
        } catch (IOException ex) {
            System.err.println("IOException en Logger. Se continuará sin log.");
            funcional = false;
        }
    }
}
