package GameOfThrones;

public class Atacante extends Personaje {

	public Atacante(String nombre, String tipo, char ID) {
		super(nombre, tipo, ID);
	}

	@Override
	public void interactuarPuerta() {
		System.out.println("Personaje "+nombre+" de tipo "+tipo+" cerrando puerta");

	}

	public void cogerLlave(Llave l) {
            this.llaves.addData(l);
	}

}