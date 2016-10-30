package GameOfThrones;


public class SalaPuerta extends Sala {
	private Puerta p;
	public SalaPuerta(Integer ID){
		super(ID);
	}
        @Override
	public void insertarPuerta(Puerta p) throws NotKingsLandingException{
		this.p=p;
	}
        
        @Override
	public Puerta getPuerta(){
		return p;
	}
        
        @Override
        public boolean nuevoPersonaje(Personaje pe){
            if(!p.estaAbierta())
                return super.nuevoPersonaje(pe);
            else
                return !super.nuevoPersonaje(pe);
        }
}
