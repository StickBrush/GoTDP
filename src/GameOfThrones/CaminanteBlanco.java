package GameOfThrones;

public class CaminanteBlanco extends Defensor {
    
    private int capturados;

	public CaminanteBlanco(String nombre, char ID) {
		super(nombre, "Caminante Blanco", ID);
                capturados=0;
	}
        
        public void kill(Personaje p){
            System.out.println("El personaje "+ p.getNombre() + " ha sido eliminado");
            capturados++;
        }
        
        public int getCapturados(){
            return capturados;
        }

}
