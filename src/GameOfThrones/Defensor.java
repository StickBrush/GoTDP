package GameOfThrones;

public class Defensor extends Personaje {

	public Defensor(String nombre, String tipo, char ID) {
		super(nombre, tipo, ID);
	}

	@Override
	public void interactuarPuerta() {
		System.out.println("Personaje "+nombre+" de tipo "+tipo+" cerrando puerta");

	}

	@Override
	public void interactuarLlave() {
		arbol.borrar(arbol.getRaiz());
	}

}
