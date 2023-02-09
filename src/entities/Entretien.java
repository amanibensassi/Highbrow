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
    
    private int identretien,id_mecanicien;
    private Date date_entretien;

    public Entretien() {
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

    public Date getDate_entretien() {
        return date_entretien;
    }

    public void setDate_entretien(Date date_entretien) {
        this.date_entretien = date_entretien;
    }

    @Override
    public String toString() {
        return "Entretien{" + "identretien=" + identretien + ", id_mecanicien=" + id_mecanicien + ", date_entretien=" + date_entretien + '}';
    }
    
    
    
}
