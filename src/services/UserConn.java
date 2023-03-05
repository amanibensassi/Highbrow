/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.Date;
import typeenumeration.Role;

/**
 *
 * @author anasm
 */
public class UserConn {
    
    public static int idutilisateur;
    public static int num_tel;
    public static String nom, prenom, mail, mot_de_passe, photopermis_avant, photopermis_arriere, image;
    public static Date date_naissance;
    public static Role role;
    public static boolean etat;

    public UserConn(int idutilisateur, int num_tel, String nom, String prenom, String mail, String mot_de_passe, String photopermis_avant, String photopermis_arriere, String image, Date date_naissance, Role role, boolean etat) {
        UserConn.idutilisateur = idutilisateur;
        UserConn.num_tel = num_tel;
        UserConn.nom = nom;
        UserConn.prenom = prenom;
        UserConn.mail = mail;
        UserConn.mot_de_passe = mot_de_passe;
        UserConn.photopermis_avant = photopermis_avant;
        UserConn.photopermis_arriere = photopermis_arriere;
        UserConn.image = image;
        UserConn.date_naissance = date_naissance;
        UserConn.role = role;
        UserConn.etat = etat;
    }
    
    
    
}
