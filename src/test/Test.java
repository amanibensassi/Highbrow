/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Vehicule;
import java.util.Date;
import java.sql.SQLException;
import services.VehiculeService;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;


public class Test {
    
    public static void main(String[] args) {
        try{
          
           // Siege s = new Siege(5,"siege_7",Region.djerba,"adresse_7","mail_6",50450450,2);
            //SiegeService ps = new SiegeService();
           // ps.ajouter(s);
            //ps.modifier(s);
            //ps.supprimer(s);
            //System.out.println(ps.recupererVehiculeByUtilisateur(2));
            //String a = NbrPlace.a2.toString();
            //int b = Integer.parseInt(a.substring(1));
            Date d = new Date("2018/02/01");
          Vehicule s = new Vehicule(20,"fiat",7777,"108-202",Carburant.diesel,NbrPlace.neuf,90,Etat.a_vendre,90000,7,d,"image");
          VehiculeService ps = new VehiculeService();
          //ps.ajouter(s);
         //ps.modifier(s);
         //ps.supprimer(s);
         // System.out.println(ps.recuperer());
         System.out.println(ps.recupererVehiculeByCarburant(Carburant.diesel));
          //System.out.println(ps.recupererVehiculeBySiege(7));
          //System.out.println(ps.recupererVehiculeByEtat(Etat.a_louer));
          //System.out.println(ps.recupererVehiculeByPlace(NbrPlace.sept));
          //ps.supprimer(s);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
