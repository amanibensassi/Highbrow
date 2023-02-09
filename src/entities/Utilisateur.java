/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import typeenumeration.Role;

/**
 *
 * @author anasm
 */
public class Utilisateur {
    
    private int idutilisateur,num_tel;
    private String nom,prenom,mail,mot_de_passe,photopermis_avant,photopermis_arriere,image;
    private Date date_naissance;
    private Role role;

    public Utilisateur() {
    }


    public int getIdutilisateur() {
        return idutilisateur;
    }

    public void setIdutilisateur(int idutilisateur) {
        this.idutilisateur = idutilisateur;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getPhotopermis_avant() {
        return photopermis_avant;
    }

    public void setPhotopermis_avant(String photopermis_avant) {
        this.photopermis_avant = photopermis_avant;
    }

    public String getPhotopermis_arriere() {
        return photopermis_arriere;
    }

    public void setPhotopermis_arriere(String photopermis_arriere) {
        this.photopermis_arriere = photopermis_arriere;
    }

   

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "idutilisateur=" + idutilisateur + ", num_tel=" + num_tel + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail + ", mot_de_passe=" + mot_de_passe + ", photopermis_avant=" + photopermis_avant + ", photopermis_arriere=" + photopermis_arriere + ", image=" + image + ", date_naissance=" + date_naissance + ", role=" + role + '}';
    }

 
    
    
}
