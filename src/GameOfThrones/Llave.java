package GameOfThrones;

/**
 * Implementación de la llave
 * @version 1.0
 * @author Juan Luis Herrera González
 */
public class Llave implements Comparable <Llave> {

    /**
     * Identificador de la llave
     */
    private Integer ID;

    /**
     * Constructor parametrizado de Llave
     *
     * @param ID Identificador de la llave
     */
    public Llave(Integer ID) {
        this.ID = ID;
    }

    /**
     * Método que identifica una llave
     *
     * @return Identificador de la llave
     */
    public Integer identificar() {
        return ID;
    }

    @Override
    public int compareTo(Llave t) {
         return (this.identificar().compareTo(t.identificar()));
    }
    
    public boolean equals(Llave t){
        return (this.identificar().equals(t.identificar()));
    }
    
    @Override
    public String toString(){
        return (String.valueOf(ID));
    }
    
}
