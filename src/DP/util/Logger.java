package DP.util;

import DP.GameOfThrones.Mapa;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import DP.ED.List;

/**
 *
 * @author StickBrush
 */
public class Logger {

    private BufferedWriter logger;
    private boolean funcional;

    public Logger() {
        funcional = true;
        try {
            logger = new BufferedWriter(new FileWriter("registro.log"));
            logger.close();
        } catch (IOException ex) {
            System.err.println("IOException en Logger. Se continuará sin log.");
            funcional = false;
        }
    }

    public void logMapa(Mapa m) {
        if (funcional) {
            try {
                logger = new BufferedWriter(new FileWriter("registro.log"));
                List<String> structure=m.structureString();
                for(int i=0;i<structure.size();i++){
                    logger.write(structure.get(i)+"\n");
                }
                logger.close();
            } catch (IOException ex) {
                System.err.println("IOException en Logger. Se continuará sin log.");
                funcional=false;
            }
        }
    }
}
