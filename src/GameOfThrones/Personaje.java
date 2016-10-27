package GameOfThrones;

import DP.ED.Arbol;
import DP.ED.Cola;

public abstract class Personaje {
	protected String nombre;
	protected String tipo;
	protected char ID;
	protected Arbol<Llave> arbol;
        private Cola<Orientacion> ruta;
	public Personaje(String nombre, String tipo, char ID){
		this.nombre=nombre;
		this.tipo=tipo;
		this.ID=ID;
		arbol=new Arbol<Llave>();
                ruta=new Cola<Orientacion>();
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
        
	public abstract void interactuarPuerta();
	public abstract void interactuarLlave();
}
