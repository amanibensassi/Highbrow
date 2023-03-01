/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ameni
 */
public class Reponse {
  private int idreponse, id_commentaire, id_utilisateur;
  private Date date_reponse;
  private String reponse;

    public Reponse() {
    }

    public Reponse(int idreponse, int id_commentaire, int id_utilisateur, Date date_reponse, String reponse) {
        this.idreponse = idreponse;
        this.id_commentaire = id_commentaire;
        this.id_utilisateur = id_utilisateur;
        this.date_reponse = date_reponse;
        this.reponse = reponse;
    }

    

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int idcommentaire) {
        this.id_commentaire = idcommentaire;
    }

    public int getIdreponse() {
        return idreponse;
    }

    public void setIdreponse(int idreponse) {
        this.idreponse = idreponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idreponse=" + idreponse + ", id_commentaire=" + id_commentaire + ", id_utilisateur=" + id_utilisateur + ", date_reponse=" + date_reponse + ", reponse=" + reponse + '}'+'\n';
    }
  
    
  
  
}
