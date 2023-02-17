/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author ameni
 */
public class CommentaireService implements IService<Commentaire> {
Connection  cnx; 
  
  public CommentaireService () {
        cnx = MyDB.getInstance().getCnx();
    }
  
@Override
    public void ajouter(Commentaire t) throws SQLException {
        String req = "INSERT INTO commentaire(date_commentaire,commentaire,id_publication,id_utilisateur) VALUES (?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(t.getDate_commentaire().getTime()));
        st.setString(2,t.getCommentaire());
        st.setInt(3,t.getId_publication());
        st.setInt(4,t.getId_utilisateur());
        st.executeUpdate();
       
    }

    @Override
    public void modifier(Commentaire t) throws SQLException {
       String req = "UPDATE commentaire SET date_commentaire=?,commentaire=? where idcommentaire = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new Timestamp(t.getDate_commentaire().getTime()));
        ps.setString(2, t.getCommentaire());
        ps.setInt(3, t.getIdcommentaire());
        ps.executeUpdate();
        
    }

    @Override
    public void supprimer(Commentaire t) throws SQLException {
       
        String req = "DELETE FROM commentaire WHERE idcommentaire = ? ;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdcommentaire());
        ps.executeUpdate();
      
    }

    @Override
    public List<Commentaire> recuperer() throws SQLException {
         List<Commentaire> commentaire = new ArrayList<>();
      String req = "SELECT * FROM commentaire";
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Commentaire p = new Commentaire();
        //remplissage
           p.setCommentaire(rs.getString("commentaire"));
           p.setDate_commentaire(rs.getTimestamp("date_commentaire"));
           p.setId_publication(rs.getInt("id_publication"));
           p.setId_utilisateur(rs.getInt("id_utilisateur"));
           p.setIdcommentaire(rs.getInt("idcommentaire"));
        //ajout    
        commentaire.add(p);
        
      }
     return commentaire;
     
    }

   public List<Commentaire> recupererParpublication(int t) throws SQLException {
         List<Commentaire> commentaire = new ArrayList<>();
      String req = "SELECT * FROM commentaire WHERE id_publication = " + t;
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Commentaire p = new Commentaire();
        //remplissage
           p.setCommentaire(rs.getString("commentaire"));
           p.setDate_commentaire(rs.getTimestamp("date_commentaire"));
           p.setId_publication(rs.getInt("id_publication"));
           p.setId_utilisateur(rs.getInt("id_utilisateur"));
           p.setIdcommentaire(rs.getInt("idcommentaire"));
        //ajout    
        commentaire.add(p);
        
      }
     return commentaire;
    }
    
}
