package DP.GameOfThrones;

/**
 * Puerta para pruebas
 * @version 3.0
 * @author Juan Luis Herrera González Curso: 2º (Grupo Grande A) EC3
 */
public class PuertaForTesting extends Puerta {
    
    private static PuertaForTesting instance=null;
    
    private PuertaForTesting(){
        super();
    }
    
    public static PuertaForTesting getInstance(){
        if(instance==null)
            instance=new PuertaForTesting();
        return instance;
    }
    
    public static void forceNull(){
        instance=null;
    }
    
    public int getAltura(){
        return this.altura;
    }
    
    public void forceOpen(){
        this.abierta=true;
    }
}
