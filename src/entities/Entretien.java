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
public class Entretien {
    
    private int identretien,id_mecanicien,id_vehicule;
    private Date date_entretien;
    private boolean etat_entretien;

    public Entretien() {
    }

    public Entretien(int id_mecanicien, int id_vehicule, Date date_entretien, boolean etat_entretien) {
        this.id_mecanicien = id_mecanicien;
        this.id_vehicule = id_vehicule;
        this.date_entretien = date_entretien;
        this.etat_entretien = etat_entretien;
    }

    public Entretien(int identretien, int id_mecanicien, int id_vehicule, Date date_entretien, boolean etat_entretien) {
        this.identretien = identretien;
        this.id_mecanicien = id_mecanicien;
        this.id_vehicule = id_vehicule;
        this.date_entretien = date_entretien;
        this.etat_entretien = etat_entretien;
    }

    public int getIdentretien() {
        return identretien;
    }

    public void setIdentretien(int identretien) {
        this.identretien = identretien;
    }

    public int getId_mecanicien() {
        return id_mecanicien;
    }

    public void setId_mecanicien(int id_mecanicien) {
        this.id_mecanicien = id_mecanicien;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public Date getDate_entretien() {
        return date_entretien;
    }

    public void setDate_entretien(Date date_entretien) {
        this.date_entretien = date_entretien;
    }

    public boolean isEtat_entretien() {
        return etat_entretien;
    }

    public void setEtat_entretien(boolean etat_entretien) {
        this.etat_entretien = etat_entretien;
    }

    @Override
    public String toString() {
        //date_entretien.getHours();
        return "Entretien{" + "identretien=" + identretien + ", id_mecanicien=" + id_mecanicien + ", date_entretien=" + date_entretien+ '}'+"\n";
    }
    
    
    
}
