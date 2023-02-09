/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author anasm
 */
public class Location {
    
    private int idlocation,id_vehicule;
    private Date date_debut,date_fin;
    private boolean opt_chauffeur;

    public Location() {
    }


    public int getIdlocation() {
        return idlocation;
    }

    public void setIdlocation(int idlocation) {
        this.idlocation = idlocation;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public boolean isOpt_chauffeur() {
        return opt_chauffeur;
    }

    public void setOpt_chauffeur(boolean opt_chauffeur) {
        this.opt_chauffeur = opt_chauffeur;
    }

    @Override
    public String toString() {
        return "Location{" + "idlocation=" + idlocation + ", id_vehicule=" + id_vehicule + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", opt_chauffeur=" + opt_chauffeur + '}';
    }
    
    
    
}
