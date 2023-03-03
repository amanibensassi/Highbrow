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
public class Publication {
    
    private int idpublication,id_utilisateur;
    private Date date_publication;
    private String publication;

    public Publication() {
    }

    public Publication( int id_utilisateur, Date date_publication, String publication) {
        this.idpublication = idpublication;
        this.id_utilisateur = id_utilisateur;
        this.date_publication = date_publication;
        this.publication = publication;
    }

    public Publication( int idpublication, int id_utilisateur, Date date_publication, String publication) {
        this.idpublication = idpublication;
        this.id_utilisateur = id_utilisateur;
        this.date_publication = date_publication;
        this.publication = publication;
    }
    
    public int getIdpublication() {
        return idpublication;
    }

    public void setIdpublication(int idpublication) {
        this.idpublication = idpublication;
    }
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(Date date_publication) {
        this.date_publication = date_publication;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {  
        this.publication = publication;
    }

    @Override
    public String toString() {
        return "Publication{" + "idpublication=" + idpublication +", id_utilisateur=" + id_utilisateur + ", date_publication=" + date_publication + ", publication=" + publication + '}'+'\n';
    }
    
    
    
}
