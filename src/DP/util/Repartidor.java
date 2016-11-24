/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DP.util;

import DP.ED.Grafo;
import java.util.Set;
import java.util.Iterator;
import DP.ED.List;
/**
 *
 * @author Solaire
 */
public class Repartidor {
    public static List<Integer> sortByFrequence(int kingsLanding, Grafo laberinto, int size){
        Set<Set<Integer>> caminos=laberinto.caminos(0, kingsLanding);
        int[] freq=new int[size];
        List<Integer> sorted=new List<>();
        Iterator<Set<Integer>> it= caminos.iterator();
        while(it.hasNext()){
            Iterator<Integer> iter=it.next().iterator();
            while(iter.hasNext()){
                freq[iter.next()]++;
            }
        }
        boolean fin=false;
        for(int i=0;i<freq.length && !fin ;i++){
            int inMay=0;
            int may=freq[0];
            for(int j=0;j<freq.length;j++){
                if(freq[j]>may){
                    inMay=j;
                    may=freq[j];
                }
            }
            if(may!=-1){
                sorted.addLast(inMay);
                freq[inMay]=-1;
            }
            else
                fin=true;
        }
        return sorted;
    }
    
}
