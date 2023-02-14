/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Chauffeur;
import entities.Location;
import java.sql.SQLException;
import services.ChauffeurService;
import typeenumeration.Region;
import java.util.Date;
import services.LocationService;

/**
 *
 * @author eya
 */
public class Test {
    
    public static void main(String[] args) {
        try {
            ChauffeurService cs = new ChauffeurService();
            LocationService ls = new LocationService();
            
            Date date_debut = new Date("2023/11/08");
            Date date_fin = new Date("2023/11/10");

//Pour Ajout
            Chauffeur c = new Chauffeur(Region.nabeul, 1, 88888, "rue-kethee", "ffff", "Picturesffff", (float) 455, "nom", "prenom", "eee", 1);
//Pour Modif
            Chauffeur c4 = new Chauffeur(74, Region.nabeul, 1, 88888, "rue-kethee", "ffff", "Picturesffff", (float) 455, "nom", "prenom", "eee", 1);
//PourLocation ajout 
            Location l1 = new Location(date_debut, date_fin, true, 2, 1);
// pour modifer Location;
            //Location l2 = new Location(38, date_debut, date_fin, true, 2, 1);
//ls.ajouter(l1);
            /**
             * *********************** TESTER CHAUFFEUR
             * ************************
             */

            //cs.ajouter(c);
            //cs.modifier(c4);
            //cs.supprimer(c4);
            //System.out.println(cs.recuperer());
            //System.out.println( cs.recupererById(62));
            //System.out.println(cs.recupererChauffeursDisponibles(l2));
            //System.out.println(cs.recupererChauffeurBYidSiege(1));
            /**
             * *********************** TESTER LOCATION ************************
             */
            // ls.ajouter(l1);
            //ls.AffecterUnchauffeur(35,72);
            //ls.supprimerLocation(20);
            //ls.modifier(l2);
            // System.out.println(ls.recupererAllByIdUser(1));
            //System.out.println(ls.recupererAllByIdChauffeur(71));
             System.out.println(ls.recupererAllByIdVehicule(2));
            //ls.AnnulerLocation(42);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
