package GameOfThrones;

import DP.ED.Stack;
import DP.ED.Cola;
public class Sala {
	private Stack<Llave> pila;
	private Cola<Personaje> cola;
	public Sala(){
		pila=new Stack<Llave>();
		cola=new Cola<Personaje>();
	}
	public void stackear(Llave l){
		pila.addData(l);
	}
	public void encolar(Personaje p){
		cola.encolar(p);
	}
	public boolean tieneGente(){
		return (!cola.vacia());
	}
	public Llave cima(){
		return pila.getTop();
	}
	public Personaje primero(){
		return cola.primero();
	}
	public void destackear(){
		pila.removeData();
	}
	public void desencolar(){
		cola.desencolar();
	}
	public void insertarPuerta(Puerta p){
		//HAAAAAAAAAAAAAAAAAAAAAAAAAAAAX
	}
	public Puerta getPuerta(){
		return null;
	}
}
