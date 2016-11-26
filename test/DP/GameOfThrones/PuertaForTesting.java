package DP.GameOfThrones;

/**
 * Puerta para pruebas
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public class PuertaForTesting extends Puerta {
    public PuertaForTesting(){
        super();
    }
    public int getAltura(){
        return this.altura;
    }
    
    public void forceOpen(){
        this.abierta=true;
    }
}
