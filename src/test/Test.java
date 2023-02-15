/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Avis;
import entities.Reclamation;
import java.sql.SQLException;
import java.util.Date;
import services.AvisService;
import services.ReclamationService;
import typeenumeration.Note;
import typeenumeration.TypeReclamation;

/**
 *
 * @author benha
 */
public class Test {

    public static void main(String[] args) {

        try {
            AvisService ps = new AvisService();
            ReclamationService rs = new ReclamationService();
            System.out.println("**************************************Avis***************************************");

            //******************************************Pour ajouter note a un siege (lezm constructeur fih id siege w bkia
            Avis a = new Avis(1, 1, Note.n1);
            // ps.ajouterAvisSiege(a);

            //*****************************************Pour Ajouter note a une vehicule (lezm constructeur ili ifh id vehicule
            Avis a1 = new Avis(1, Note.n4, 1);
            // ps.ajouterAvisVehicule(a1);

            //*****************************************Pour modifier note (lezm constrcuteur fih id ta avis)
            Avis a2 = new Avis(9, 1, 1, 1, Note.n4);
            //ps.modifier(a2);

            //****************************************Pour afficher les avis par vehicules
            //System.out.println(ps.recupererAvisVehicule(1));
            //****************************************Pour afficher les avis d'un siege
            // System.out.println(ps.recupererAvisSiege(1));
            //**********************************************Supprimer un avis
            // ps.supprimer(a2);
            System.out.println("**************************************RECLAMATION***************************************");

            Date d = new Date("02/03/2024 15:22");
            //2024-03-02 15:22:00
            //******************************************Pour ajouter Reclamation a un siege (lezm constructeur fih id siege w bkia

            Reclamation r = new Reclamation(1, 1, TypeReclamation.administrateur, d, "corp");
            // rs.ajouter(r);

            //**********************************************************Ajouter reclamation aus admin
            Reclamation r1 = new Reclamation(1, TypeReclamation.administrateur, d, "corp25525552");
            // rs.ajouterReclamationAdmin(r1);

            //********************************************Modifier etat reclamation
            Reclamation r2 = new Reclamation(17, 1, 1, TypeReclamation.administrateur, d, false, "corp");
            //rs.modifier(r2);

            //************************************************Recuper les reclamations d'un utilisateur
            //System.out.println(rs.recupererReclamationUtilisateur(1));
            //*****************************************************Recupere les reclamation d'un admin
            //System.out.println(rs.recupererReclamationdAdmin());
            //***********************************************************Recupere les reclamation d'un siege specifique
            System.out.println(rs.recupererReclamation_Siege(1));

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
