/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;
import typeenumeration.TypeReclamation;

/**
 *
 * @author anasm
 */
public class Reclamation {
    
    private int idreclamation,id_utilisateur,id_siege;
    private TypeReclamation type_reclamation;
    private Date date_reclamation;
    private boolean etat;
    private String corps;

    public Reclamation() {
    }

    public int getIdreclamation() {
        return idreclamation;
    }

    public void setIdreclamation(int idreclamation) {
        this.idreclamation = idreclamation;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_siege() {
        return id_siege;
    }

    public void setId_siege(int id_siege) {
        this.id_siege = id_siege;
    }

    public TypeReclamation getType_reclamation() {
        return type_reclamation;
    }

    public void setType_reclamation(TypeReclamation type_reclamation) {
        this.type_reclamation = type_reclamation;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }

    public String getCorps() {
        return corps;
    }

    public void setCorps(String corps) {
        this.corps = corps;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "idreclamation=" + idreclamation + ", id_utilisateur=" + id_utilisateur + ", id_siege=" + id_siege + ", type_reclamation=" + type_reclamation + ", date_reclamation=" + date_reclamation + ", etat=" + etat + ", corps=" + corps + '}';
    }
    
    
    
}
