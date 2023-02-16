/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Entretien;
import entities.Mecanicien;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import services.EntretienService;
import services.MecanicienService;
import typeenumeration.Region;
import typeenumeration.Specialite;

/**
 *
 * @author anasm
 */
public class Test {
    
    public static void main(String[] args) throws ParseException {
        try {
            Mecanicien m = new Mecanicien(12345678, Specialite.mecanicien, Region.tunis, "Araina", "C:","Anas","Maghrebi");
            Mecanicien m1 = new Mecanicien(98566, Specialite.tolier, Region.bizerte, "Araina", "image:","Eya","Somai");
            Mecanicien m2 = new Mecanicien(1,120, Specialite.electricien, Region.nabeul, "Araina", "image121525:","Mohamed","Trabelsi");
            MecanicienService ms = new MecanicienService();
            //ms.ajouter(m1);
            //ms.ajouter(m);
            //ms.modifier(m2);
            //ms.supprimer(m2);
            System.out.println("Récumpérer Tous les méniciens");
            
            System.out.println(ms.recuperer());
            
            System.out.println("Récumpérer les mécaniciens by région");
            
            System.out.println(ms.recupererMecanicienByRegion(m));
            
            System.out.println("Récumpérer les mécaniciens by spécialité");
            
            System.out.println(ms.recupererMecanicienBySpecialite(m1));
            
            
            System.out.println("Récupérer by id \n");
            
            System.out.println(ms.recupererById(m2));
            
            SimpleDateFormat simp = new SimpleDateFormat("dd/MM/yyy HH:mm");
            String date= ("15/02/2000 10:45");
            Date d=simp.parse(date);
            Entretien e=new Entretien(1,1, d,true);
            Entretien e1=new Entretien(1,1, d,true);
            Entretien e2=new Entretien(1, 1, d,false);
            Entretien e3=new Entretien(27, 1, d,false);
            EntretienService es = new EntretienService();
            //es.modifier(e1);
            //es.ajouter(e2);
            
            System.out.println("Tous les entretiens");
            System.out.println(es.recuperer());
            
            System.out.println("Récupérer entretiens By ID");
            System.out.println(es.recupererById(e3));
            System.out.println("Date mécanicien indispo");
            System.out.println(ms.mecaniciensIsDispo(m2));
            
            String dateent= ("27/02/2023 19:45");
            Date da=simp.parse(dateent);
            Mecanicien mm = new Mecanicien(4,12345678, Specialite.mecanicien, Region.tunis, "Araina", "C:","Anas","Maghrebi");

            
            System.out.println("Les entretiens d'un véhicule");
            System.out.println(es.recupererEntretienByVehicule(1));
            
            System.out.println("Les entretiens d'un mécanicien");
            System.out.println(es.recupererEntretienByMecanicien(1));
            
            System.out.println("Modifier Etat entretien");
            Entretien em= new Entretien(35, 5, 1, da, true);
            //es.modifierEtat(em);
            //es.modifier(em);
            
           

            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
