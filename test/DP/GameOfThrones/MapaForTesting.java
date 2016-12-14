/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.GameOfThrones;

import DP.Exceptions.MapSizeException;

/**
 *
 * @author StickBrush
 */
public class MapaForTesting extends Mapa {
    
    private MapaForTesting(int a, int b, int c, int d) throws MapSizeException{
        super(a,b,c,d);
    }

    public void forceNull(){
        instance=null;
    }
}
