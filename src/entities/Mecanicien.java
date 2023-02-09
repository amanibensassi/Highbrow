/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import typeenumeration.Region;
import typeenumeration.Specialite;

/**
 *
 * @author anasm
 */
public class Mecanicien {
    
    private int idmecanicien,contact;
    private Specialite specialite;
    private Region region;
    private String adresse,image;

    public Mecanicien() {
    }


    public int getIdmecanicien() {
        return idmecanicien;
    }

    public void setIdmecanicien(int idmecanicien) {
        this.idmecanicien = idmecanicien;
    }

    public int getContact() {
        return contact;
    }

    public void setContact(int contact) {
        this.contact = contact;
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        this.specialite = specialite;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Mecanicien{" + "idmecanicien=" + idmecanicien + ", contact=" + contact + ", specialite=" + specialite + ", region=" + region + ", adresse=" + adresse + ", image=" + image + '}';
    }
    
    
    
}
