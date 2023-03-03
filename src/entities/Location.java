/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import typeenumeration.EtatLocation;
/**
 *
 * @author anasm
 */
public class Location {

    private int idlocation, id_vehicule, id_chauffeur, id_utilisateur;
    private Date date_debut, date_fin;
    private boolean opt_chauffeur;
    private EtatLocation etat ;

    public Location() {
    }
//Pour ajout

    public Location(Date date_debut, Date date_fin, boolean opt_chauffeur, int id_vehicule, int id_utilisateur) {
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.opt_chauffeur = opt_chauffeur;
        this.id_vehicule = id_vehicule;
        this.id_utilisateur = id_utilisateur;
        
    }

    public EtatLocation getEtat() {
        return etat;
    }

    public void setEtat(EtatLocation etat) {
        this.etat = etat;
    }

    public Location(int idlocation,Date date_debut, Date date_fin, boolean opt_chauffeur, int id_vehicule, int id_utilisateur,EtatLocation etat) {
        this.idlocation = idlocation;
        this.id_vehicule = id_vehicule;
        this.id_chauffeur = id_chauffeur;
        this.id_utilisateur = id_utilisateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.opt_chauffeur = opt_chauffeur;
        this.etat=etat;
    }

    public Location(int idlocation,Date date_debut, Date date_fin, boolean opt_chauffeur, int id_vehicule, int id_utilisateur) {
        this.idlocation = idlocation;
        this.id_vehicule = id_vehicule;
        this.id_chauffeur = id_chauffeur;
        this.id_utilisateur = id_utilisateur;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.opt_chauffeur = opt_chauffeur;
        this.etat=etat;
    }
    public Location(int idlocation) {
        this.idlocation = idlocation;

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

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Location{" + "idlocation=" + idlocation + ", id_vehicule=" + id_vehicule + ", id_chauffeur=" + id_chauffeur + ", id_utilisateur=" + id_utilisateur + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", opt_chauffeur=" + opt_chauffeur + ", etat=" + etat + '}';
    }

    
}
