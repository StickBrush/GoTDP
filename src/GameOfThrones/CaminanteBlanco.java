package GameOfThrones;

public class CaminanteBlanco extends Defensor {

	public CaminanteBlanco(String nombre, char ID) {
		super(nombre, "Caminante Blanco", ID);
	}
        
        public void kill(Personaje p){
            System.out.println("El personaje "+ p.getNombre() + " ha sido eliminado");
        }

}
