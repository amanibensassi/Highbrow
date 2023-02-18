/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Siege;
import entities.Vehicule;
import java.util.Date;
import java.sql.SQLException;
import services.SiegeService;
import services.VehiculeService;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
import typeenumeration.Region;


public class Test {
    
    public static void main(String[] args) {
        try{
            //Siege siege = new Siege();
            //siege.setMail("ameni");
           Siege s = new Siege(5,"siege_9",Region.ariana,"adresse_7","aaa@gmail.com",11111111,1);
            SiegeService ps = new SiegeService();
           //ps.ajouter(s);
            //ps.modifier(s);
            //ps.supprimer(s);
            //System.out.println(ps.recupererVehiculeByUtilisateur(2));
            //String a = NbrPlace.a2.toString();
            //int b = Integer.parseInt(a.substring(1));
           // Date d = new Date("2018/04/01");
            //Vehicule s = new Vehicule(3,"kia",7777,"170TUN2002",Carburant.diesel,NbrPlace.neuf,90,Etat.a_vendre,90000,1,d,"image1");
           // VehiculeService ps = new VehiculeService();
            ps.ajouter(s);
            //ps.modifier(s);
         //ps.supprimer(s);
         //System.out.println(ps.recuperer());
         //System.out.println(ps.recupererVehiculeByCarburant(Carburant.diesel));
          //System.out.println(ps.recupererVehiculeBySiege(7));
          //System.out.println(ps.recupererVehiculeByEtat(Etat.a_louer));
          //System.out.println(ps.recupererVehiculeByPlace(NbrPlace.sept));
          //System.out.println(ps.recupererVehiculeBySiege(1));
          //ps.supprimer(s);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
