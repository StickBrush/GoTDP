package DP.ED;

class RotatingViolationException extends Exception{}

public class ColaRotatoria <tipoDato> extends Cola <tipoDato>{
    public ColaRotatoria(){
        super();
    }

    @Override
    public void desencolar() throws RotatingViolationException{
        throw new RotatingViolationException();
    }

    public void reencolar(){
        if(!this.vacia()){
            tipoDato first=this.primero();
            try{
                super.desencolar();
            }
            catch(RotatingViolationException ex){
                System.err.println("Esta parte del código no debería ser alcanzada jamás.");
            }
            this.encolar(first);
        }
    }
}