package GameOfThrones;

public class Defensor extends Personaje {

	public Defensor(String nombre, String tipo, char ID) {
		super(nombre, tipo, ID);
	}

	@Override
	public void interactuarPuerta(Puerta p) {
		p.cerrar(); //Totalmente balanceado, nada que nerfear aquí
	}

	public Llave dejarLlave() {
		Llave aux=this.llaves.getTop();
                this.llaves.removeData();
                numLlaves--;
                return aux;
	}

}
