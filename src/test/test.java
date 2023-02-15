/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Utilisateur;
import entities.Vente;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import services.UserService;
import services.VenteService;
import typeenumeration.Role;
import utils.MyDB;

/**
 *
 * @author Hamma //
 */
public class test {

    public static void main(String[] args) throws ParseException {

        try {
            UserService us = new UserService();
            VenteService vs = new VenteService();
            Date d = new Date("10/09/2000");
            Date dateVente = new Date("03/01/1999 15:15");
            Vente v = new Vente(2, 1, 11, dateVente);
            Utilisateur u = new Utilisateur(3, 25125035, "Mrabet", "Hama", "mohamed.mrabet@esprit.tn", "123", "e", "2", "image", d, Role.client);
            Utilisateur u1 = new Utilisateur(11, 25125035, "Mrabet", "Hama", "mohamed.mrabet@gmail.com", "12345", "e1", "21", "image1", d, Role.client);

            Utilisateur auth = new Utilisateur("mohamed.mrabet@esprit.tn", "12345");

       ///////////// user crud ///////////////
//         us.ajouter(u1);
//         us.approuver(u1);
//         us.modifierPassword(u1);
//         us.supprimer(u1);
//        System.out.println(us.recuperer(u));
//System.out.println(us.recupererById(11));
//////////////////////// Vente crud /////////////////////
            ////  vs.ajouterRendezVous(v);
                System.out.println(vs.recupererByIdVehicule(1));  
//            vs.supprimer(v);
//            vs.modifier(v);
         // System.out.println(vs.recuperer(v));
         ////////////////// affichage user ////////////////////
//           
            System.out.println("cbn");
            ///////////////////////////////authentification///////////////////////////
            System.out.println(us.authenticate("mohamed.mrabet@gmail.com", "123488"));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
