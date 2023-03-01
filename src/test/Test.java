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
         //Siege s = new Siege(33,"siege_47",Region.tunis,"adresse_2","bbb@gmail.com",40120120,1);
         //SiegeService ps = new SiegeService();
         //ps.ajouter(s);
        // ps.modifier(s);
         //ps.supprimer(s);
         //System.out.println(ps.recupererSiegeByUtilisateur(1));
         //System.out.println(ps.recupererSiegeByRegion(Region.ariana));
         //String a = NbrPlace.a2.toString();
         //int b = Integer.parseInt(a.substring(1));
         Date d = new Date("2018/04/01");
         //Vehicule veh = new Vehicule(25);
         Vehicule s = new Vehicule(25,"fiat",45,"204TUN2402",Carburant.diesel,NbrPlace.cinq,40,Etat.a_louer,8000,46,d,"image3");
         VehiculeService ps = new VehiculeService();
         ps.ajouter(s);
         //veh.setPrix_par_jour(800);
         //ps.modifier(s);
         //ps.supprimer(s);
         //Vehicule veh = new Vehicule();
         //System.out.println(ps.recuperer());
         //System.out.println(ps.recupererVehiculeByCarburant(Carburant.diesel));
         //System.out.println(ps.recupererVehiculeBySiege(27));
         //System.out.println(ps.recupererVehiculeByEtat(Etat.a_louer));
         //System.out.println(ps.recupererVehiculeByPlace(NbrPlace.sept));
         //System.out.println(ps.recupererVehiculeBySiege(1));
         //ps.supprimer(s);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
