/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import typeenumeration.Region;

/**
 *
 * @author anasm
 */
public class Chauffeur {

    private int idchauffeur, contact, cin, id_siege;
    private Region region;
    private String adresse, permis, image, nom, prenom, permis_arriere;
    private float prix_par_jour;
    
//Constructeur pour modif

    public Chauffeur(int idchauffeur, Region region, int contact, int cin, String adresse, String permis, String image, float prix_par_jour, String nom, String prenom, String permis_arriere, int id_siege) {
        this.idchauffeur = idchauffeur;
        this.contact = contact;
        this.cin = cin;
        this.id_siege = id_siege;
        this.region = region;
        this.adresse = adresse;
        this.permis = permis;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.permis_arriere = permis_arriere;
        this.prix_par_jour = prix_par_jour;
    }

//Constructeur pour ajout
    public Chauffeur(Region region, int contact, int cin, String adresse, String permis, String image, float prix_par_jour, String nom, String prenom, String permis_arriere, int id_siege) {

        this.contact = contact;
        this.cin = cin;
        this.id_siege = id_siege;
        this.region = region;
        this.adresse = adresse;
        this.permis = permis;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.permis_arriere = permis_arriere;
        this.prix_par_jour = prix_par_jour;
    }

    public Chauffeur() {
    }

    public Chauffeur(int idchauffeur, Region region, int contact, int cin, String adresse, String permis, String image, float prix_par_jour, String nom, String prenom, String permis_arriere) {
   this.contact = contact;
        this.cin = cin;
        this.idchauffeur = idchauffeur;
        this.region = region;
        this.adresse = adresse;
        this.permis = permis;
        this.image = image;
        this.nom = nom;
        this.prenom = prenom;
        this.permis_arriere = permis_arriere;
        this.prix_par_jour = prix_par_jour;    }



    public String getPermis_arriere() {
        return permis_arriere;
    }

    public void setPermis_arriere(String permis_arriere) {
        this.permis_arriere = permis_arriere;
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

    public int getIdchauffeur() {
        return idchauffeur;
    }

    public void setIdchauffeur(int idchauffeur) {
        this.idchauffeur = idchauffeur;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrix_par_jour() {
        return prix_par_jour;
    }

    public void setPrix_par_jour(float prix_par_jour) {
        this.prix_par_jour = prix_par_jour;
    }

    public Chauffeur(int idchauffeur) {
        this.idchauffeur = idchauffeur;
    }

  

    @Override
    public String toString() {
        return "Chauffeur{" + "idchauffeur=" + idchauffeur + ", contact=" + contact + ", cin=" + cin + ", id_siege=" + id_siege + ", region=" + region + ", adresse=" + adresse + ", permis=" + permis + ", image=" + image + ", nom=" + nom + ", prenom=" + prenom + ", permis_arriere=" + permis_arriere + ", prix_par_jour=" + prix_par_jour + '}';
    }

    public int getId_siege() {
        return id_siege;
    }

    public void setId_siege(int id_siege) {
        this.id_siege = id_siege;
    }

}
