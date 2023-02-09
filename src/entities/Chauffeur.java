/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import typeenumeration.Region;

/**
 *
 * @author anasm
 */
public class Chauffeur {
    
    private int idchauffeur,contact,cin,id_location;
    private Region region;
    private String adresse,permis,image;
    private float prix_par_jour;

    public Chauffeur() {
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

    public int getId_location() {
        return id_location;
    }

    public void setId_location(int id_location) {
        this.id_location = id_location;
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

    @Override
    public String toString() {
        return "Chauffeur{" + "idchauffeur=" + idchauffeur + ", contact=" + contact + ", cin=" + cin + ", id_location=" + id_location + ", region=" + region + ", adresse=" + adresse + ", permis=" + permis + ", image=" + image + ", prix_par_jour=" + prix_par_jour + '}';
    }
    
    
    
}
