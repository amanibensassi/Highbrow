/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package typeenumeration;

/**
 *
 * @author anasm
 */
public enum NbrPlace {
   UN(1),
    DEUX(2),
    TROIS(3);

    private int valeur;

    NbrPlace(int valeur) {
        this.valeur = valeur;
    }

    public int getValeur() {
        return valeur;
    }
    
}
