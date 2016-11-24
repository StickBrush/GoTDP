/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

/**
 *
 * @author Solaire
 */
public class PuertaForTesting extends Puerta {
    public PuertaForTesting(){
        super();
    }
    public int getAltura(){
        return this.altura;
    }
    
    public void forceOpen(){
        this.abierta=true;
    }
}
