package GameOfThrones;

public class Defensor extends Personaje {

	public Defensor(String nombre, String tipo, char ID) {
		super(nombre, tipo, ID);
	}

	@Override
	public void interactuarPuerta() {
		System.out.println("Personaje "+nombre+" de tipo "+tipo+" cerrando puerta");

	}

	public Llave dejarLlave() {
		Llave aux=this.llaves.getTop();
                this.llaves.removeData();
                return aux;
	}

}
