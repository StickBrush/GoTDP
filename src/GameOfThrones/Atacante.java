package GameOfThrones;

public class Atacante extends Personaje {

	public Atacante(String nombre, String tipo, char ID) {
		super(nombre, tipo, ID);
	}

	@Override
	public void interactuarPuerta(Puerta p) {
            if(!this.llaves.isEmpty()){
		p.abrir(this.llaves.getTop());
                this.llaves.removeData();
            }
	}

	public void cogerLlave(Llave l) {
            this.llaves.addData(l);
	}

}