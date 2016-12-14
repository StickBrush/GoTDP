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
    
    private static Logger instance=null;

    /**
     * Constructor por defecto del Logger
     */
    private Logger() {
        funcional = true;
        try {
            logger = new BufferedWriter(new FileWriter("registro.log"));
        } catch (IOException ex) {
            System.err.println("IOException en Logger. Se continuará sin log.");
            funcional = false;
        }
    }
    
    public static Logger getInstance(){
        if(instance==null)
            instance=new Logger();
        return instance;
    }

    /**
     * Registra el mapa
     *
     * @param m Mapa a registrar
     */
    public void logMapa() {
        if (funcional) {
            Mapa m = Mapa.getInstance();
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
    public void logInfoMapa() {
        if (funcional) {
            Mapa m = Mapa.getInstance();
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
    public void logRutas() {
        if (funcional) {
            Mapa m = Mapa.getInstance();
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
