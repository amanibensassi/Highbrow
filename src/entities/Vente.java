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
public class Vente {

    private int idvente, id_vehicule, id_utilisateur;
    private Date date_rendez_vous;

    public Vente() {
    }

    public Vente(int id_vehicule, Date date_rendez_vous) {
        this.id_vehicule = id_vehicule;
        this.date_rendez_vous = date_rendez_vous;
    }

    public Vente(int idvente, int id_vehicule, Date date_rendez_vous) {
        this.idvente = idvente;
        this.id_vehicule = id_vehicule;
        this.date_rendez_vous = date_rendez_vous;
    }

    public Vente(int idvente, int id_vehicule, int id_utilisateur, Date date_rendez_vous) {
        this.idvente = idvente;
        this.id_vehicule = id_vehicule;
        this.id_utilisateur = id_utilisateur;
        this.date_rendez_vous = date_rendez_vous;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getIdvente() {
        return idvente;
    }

    public void setIdvente(int idvente) {
        this.idvente = idvente;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public Date getDate_rendez_vous() {
        return date_rendez_vous;
    }

    public void setDate_rendez_vous(Date date_rendez_vous) {
        this.date_rendez_vous = date_rendez_vous;
    }

    @Override
    public String toString() {
        return "Vente{" + "idvente=" + idvente + ", id_vehicule=" + id_vehicule + ", date_rendez_vous=" + date_rendez_vous + ", id_utilisatuer="+ id_utilisateur +'}' + "\n";
    }

}
