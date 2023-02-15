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
public class Siege {
    
    private int idsiege,num_tel_siege,id_utilisateur;
    private String nom_siege,adresse,mail;
    private Region region;

    public Siege() {
    }

    public Siege(int idsiege,String nom_siege,Region region,String adresse,String mail,int num_tel_siege, int id_utilisateur) {
        this.idsiege = idsiege;
        this.num_tel_siege = num_tel_siege;
        this.id_utilisateur = id_utilisateur;
        this.nom_siege = nom_siege;
        this.adresse = adresse;
        this.mail = mail;
        this.region = region;
    }

    public int getIdsiege() {
        return idsiege;
    }

    public void setIdsiege(int idsiege) {
        this.idsiege = idsiege;
    }

    public int getNum_tel_siege() {
        return num_tel_siege;
    }

    public void setNum_tel_siege(int num_tel_siege) {
        this.num_tel_siege = num_tel_siege;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public String getNom_siege() {
        return nom_siege;
    }

    public void setNom_siege(String nom_siege) {
        this.nom_siege = nom_siege;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "Siege{" + "idsiege=" + idsiege + ", num_tel_siege=" + num_tel_siege + ", id_utilisateur=" + id_utilisateur + ", nom_siege=" + nom_siege + ", adresse=" + adresse + ", mail=" + mail + ", region=" + region + '}';
    }

    public int getIdutilisateur() {
        //return id_utilisateur;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setId_uilisateur(int aInt) {
        //this.id_utilisateur = id_utilisateur;
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
