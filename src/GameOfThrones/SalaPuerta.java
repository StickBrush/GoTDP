package GameOfThrones;


public class SalaPuerta extends Sala {
	private Puerta p;
	public SalaPuerta(){
		super();
	}
        @Override
	public void insertarPuerta(Puerta p) throws NotKingsLandingException{
		this.p=p;
	}
        
        @Override
	public Puerta getPuerta(){
		return p;
	}
}
