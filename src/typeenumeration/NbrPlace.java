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
    deux(2),
    cinq(5),
    sept(7),
    neuf(9);

   
   private int valeur;
   
   private NbrPlace(int valeur){
       this.valeur=valeur;
   }
   
   public int getValeur(){
       return valeur;
   }
    
}
