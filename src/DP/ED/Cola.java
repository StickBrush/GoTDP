package DP.ED;

public class Cola <tipoDato> {
	private class Nodo <tipoD>{
		private Nodo <tipoD> siguiente;
		private tipoD dato;
		
		public Nodo(Nodo<tipoD> siguiente, tipoD dato){
			this.siguiente=siguiente;
			this.dato=dato;
		}
		
		public Nodo<tipoD> siguiente(){
			return siguiente;
		}
		public tipoD dato(){
			return dato;
		}
	}
	
	Nodo<tipoDato> primero;
	Nodo<tipoDato> ultimo;
	
	public Cola(){
		primero=null;
		ultimo=null;
	}
	
	public void encolar(tipoDato dato){
		Nodo<tipoDato> nuevo = new Nodo<tipoDato>(null, dato);
		if(!vacia())
			ultimo.siguiente=nuevo;
		else
			primero=nuevo;
		ultimo=nuevo;
	}
	
	public tipoDato primero(){
		if(!vacia())
			return primero.dato();
		else
			return null;
	}
	
	public boolean vacia(){
		return (primero == null);
	}
	
	public void desencolar() throws RotatingViolationException{
		if(!vacia()){
			primero=primero.siguiente();
			if(primero==null)
				ultimo=null;
		}
	}
	
}
