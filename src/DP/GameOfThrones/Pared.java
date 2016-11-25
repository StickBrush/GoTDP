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
public class Pared {

    private Sala s1;
    private Sala s2;

    public Pared(Sala s1, Sala s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    public Sala getSala1() {
        return s1;
    }

    public Sala getSala2() {
        return s2;
    }

    public boolean tirable() {
        return (s1.getKruskal() != s2.getKruskal());
    }

    public boolean horizontal() {
        int res = s2.getID() - s1.getID();
        return !(res == 1 || res == -1);
    }
}
