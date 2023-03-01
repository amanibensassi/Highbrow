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
public class Commentaire {
    
    private int idcommentaire,id_publication,id_utilisateur;
    private Date date_commentaire;
    private String commentaire ;
    private Boolean nbr_dislike;
    private Boolean nbr_like;
    
 

    public Commentaire() {
    }

    public Commentaire( int id_publication, int id_utilisateur, Date date_commentaire, String commentaire) {
        this.id_publication = id_publication;
        this.id_utilisateur = id_utilisateur;
        this.date_commentaire = date_commentaire;
        this.commentaire = commentaire;
    }
    
    public Commentaire(int idcommentaire, int id_publication, int id_utilisateur, Date date_commentaire, String commentaire) {
        this.idcommentaire = idcommentaire;
        this.id_publication = id_publication;
        this.id_utilisateur = id_utilisateur;
        this.date_commentaire = date_commentaire;
        this.commentaire = commentaire;
    }
    public Commentaire( int id_publication, int id_utilisateur, Boolean nbr_like, Boolean nbr_dislike) {
        this.id_publication = id_publication;
        this.id_utilisateur = id_utilisateur;
        this.nbr_like= nbr_like;
        this.nbr_dislike=nbr_dislike;
    }

    
       public Boolean getNbr_like() {
        return nbr_like;
    }

    public void setNbr_like(Boolean nbr_like) {
        this.nbr_like = nbr_like;
    }

    public Boolean getNbr_dislike() {
        return nbr_dislike;
    }

    public void setNbr_dislike(Boolean nbr_dislike) {
        this.nbr_dislike = nbr_dislike;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

   

    public int getIdcommentaire() {
        return idcommentaire;
    }

    public void setIdcommentaire(int idcommentaire) {
        this.idcommentaire = idcommentaire;
    }

    public int getId_publication() {
        return id_publication;
    }

    public void setId_publication(int id_publication) {
        this.id_publication = id_publication;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public Date getDate_commentaire() {
        return date_commentaire;
    }

    public void setDate_commentaire(Date date_commentaire) {
        this.date_commentaire = date_commentaire;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idcommentaire=" + idcommentaire + ", id_publication=" + id_publication + ", id_utilisateur=" + id_utilisateur + ", date_commentaire=" + date_commentaire + ", commentaire=" + commentaire + ", nbr_dislike=" + nbr_dislike + ", nbr_like=" + nbr_like + '}';
    }

    
    public String toStringCommentaire() {
        return "Commentaire{" + "idcommentaire : " + idcommentaire + ", id_publication : " + id_publication + ", id_utilisateur : " + id_utilisateur + ", date_commentaire : " + date_commentaire + ", commentaire : " + commentaire + '}'+'\n';
    }
    
    public String toStringLikes() {
        return "Commentaire{" + "idcommentaire : " + idcommentaire + ", id_publication : " + id_publication + ", id_utilisateur : " + id_utilisateur + ", nbr_likes : " + this.nbr_like + ", nbr_dislikes : " + this.nbr_dislike + '}'+'\n';
    }
    
    
}
