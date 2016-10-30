package GameOfThrones;

import DP.ED.Stack;
import DP.ED.Cola;

public abstract class Personaje {
	protected String nombre;
	protected String tipo;
	protected char ID;
	protected Stack<Llave> llaves;
        private Cola<Orientacion> ruta;
        protected int numLlaves;
        
	public Personaje(String nombre, String tipo, char ID){
		this.nombre=nombre;
		this.tipo=tipo;
		this.ID=ID;
		llaves=new Stack<Llave>();
                ruta=new Cola<Orientacion>();
                numLlaves=0;
	}
        
        public String getNombre(){
            return nombre;
        }
        
        public String getTipo(){
            return tipo;
        }
        
        public char getID(){
            return ID;
        }
        
        public void setRuta(Orientacion[] vRuta){
            for(int i=0;i<vRuta.length;i++){
                ruta.encolar(vRuta[i]);
            }
        }
        
        public Orientacion nextMove() throws NoMovesLeftException{
            if(!ruta.vacia()){
                Orientacion o=ruta.primero();
                ruta.desencolar();
                return o;
            }
            else
                throw new NoMovesLeftException();
        }
        
	public abstract void interactuarPuerta(Puerta p);
}
