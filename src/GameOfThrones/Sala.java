package GameOfThrones;

import DP.ED.ListaOrdenada;
import DP.ED.Cola;

class NotKingsLandingException extends Exception{}

public class Sala {
	private ListaOrdenada<Llave> llaves;
	private Cola<Personaje> personajes;
	private Integer ID;
	public Sala(Integer ID){
		llaves=new ListaOrdenada<Llave>();
		personajes=new Cola<Personaje>();
		this.ID=ID;
	}
	public void nuevaLlave(Llave l){
		llaves.add(l);
	}
	public void nuevoPersonaje(Personaje p){
		personajes.encolar(p);
	}
	public boolean tienePersonaje(){
		return (!personajes.vacia());
	}
	public Llave getLlave(int pos){
		return llaves.get(pos);
	}
	public Personaje primero(){
		return personajes.primero();
	}
	public void eliminarLlave(Llave l){
		llaves.searchAndDelete(l);
	}
	public void desencolar(){
            personajes.desencolar();
	}
	public void insertarPuerta(Puerta p) throws NotKingsLandingException{
		throw new NotKingsLandingException();
	}
	public Puerta getPuerta() throws NotKingsLandingException{
		throw new NotKingsLandingException();
	}
	public Integer getID(){
		return ID;
	}
}
