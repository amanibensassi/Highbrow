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
    
    private int idvente,id_vehicule;
    private Date date_rendez_vous;

    public Vente() {
    }

    public Vente(int idvente, int id_vehicule, Date date_rendez_vous) {
        this.idvente = idvente;
        this.id_vehicule = id_vehicule;
        this.date_rendez_vous = date_rendez_vous;
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
        return "Vente{" + "idvente=" + idvente + ", id_vehicule=" + id_vehicule + ", date_rendez_vous=" + date_rendez_vous + '}';
    }
    
    
    
}
