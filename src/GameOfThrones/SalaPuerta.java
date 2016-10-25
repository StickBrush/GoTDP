package GameOfThrones;


public class SalaPuerta extends Sala {
	private Puerta p;
	public SalaPuerta(){
		super();
	}
	public void insertarPuerta(Puerta p){
		this.p=p;
	}
	public Puerta getPuerta(){
		return p;
	}
}
