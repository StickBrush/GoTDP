package GameOfThrones;

import DP.ED.Arbol;

public abstract class Personaje {
	protected String nombre;
	protected String tipo;
	protected char ID;
	protected Arbol<Llave> arbol;
	public Personaje(String nombre, String tipo, char ID){
		this.nombre=nombre;
		this.tipo=tipo;
		this.ID=ID;
		arbol=new Arbol<Llave>();
	}
	public void movimiento(){
		System.out.println("Personaje " +nombre+" de tipo "+tipo+" moviéndose");
	}
	public abstract void interactuarPuerta();
	public abstract void interactuarLlave();
}
