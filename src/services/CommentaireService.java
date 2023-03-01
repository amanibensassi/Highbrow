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
import java.util.Date;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author ameni
 */
public class CommentaireService implements IService<Commentaire> {
Connection  cnx; 
 Date d = new Date(); 
  public CommentaireService () {
        cnx = MyDB.getInstance().getCnx();
    }
  
@Override
    public void ajouter(Commentaire t) throws SQLException {
        String req = "INSERT INTO commentaire(date_commentaire,commentaire,id_publication,id_utilisateur,nbr_like,nbr_dislike) VALUES (?,?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(t.getDate_commentaire().getTime()));
        st.setString(2,t.getCommentaire());
        st.setInt(3,t.getId_publication());
        st.setInt(4,t.getId_utilisateur());
        st.setBoolean(5,false);
        st.setBoolean(6,false);
        st.executeUpdate();
       
    }
    public void ajouterLike(Commentaire t) throws SQLException {
     String req = "INSERT INTO commentaire(date_commentaire,commentaire,id_publication,id_utilisateur,nbr_like,nbr_dislike) VALUES (?,?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(d.getTime()));
        st.setString(2,"");
        st.setInt(3,t.getId_publication());
        st.setInt(4,t.getId_utilisateur());
        st.setBoolean(5,true);
        st.setBoolean(6,false);
        st.executeUpdate();
    }
    
     public void ajouterDislike(Commentaire t) throws SQLException {
     String req = "INSERT INTO commentaire(date_commentaire,commentaire,id_publication,id_utilisateur,nbr_like,nbr_dislike) VALUES (?,?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(d.getTime()));
        st.setString(2,"");
        st.setInt(3,t.getId_publication());
        st.setInt(4,t.getId_utilisateur());
        st.setBoolean(5,false);
        st.setBoolean(6,true);
        st.executeUpdate();
    }

    @Override
    public void modifier(Commentaire t) throws SQLException {
       String req = "UPDATE commentaire SET date_commentaire=?,commentaire=? where idcommentaire = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new Timestamp(d.getTime()));
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
   
    public Commentaire recupererParUtilisateurDate(Commentaire t) throws SQLException {
     List<Commentaire> commentaire = new ArrayList<>();
     Timestamp time;
          time = new Timestamp(t.getDate_commentaire().getTime());
          time.setNanos(0);
          System.out.println(t);
      String req = "SELECT * FROM commentaire WHERE id_utilisateur = " + t.getId_utilisateur() + " AND date_commentaire = '" + time + "';";
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
         
         Commentaire com = new Commentaire();
        //remplissage
         System.out.println(rs.first());
           com.setCommentaire(rs.getString("commentaire"));
           com.setDate_commentaire(rs.getTimestamp("date_commentaire"));
           com.setId_publication(rs.getInt("id_publication"));
           com.setId_utilisateur(rs.getInt("id_utilisateur"));
           com.setIdcommentaire(rs.getInt("idcommentaire")); 
        commentaire.add(com);
        
     return com;
    }
    
    public int countLikes (int id) throws SQLException {

        int count =0;
        String req = "SELECT COUNT(*) FROM commentaire WHERE id_publication = ? AND nbr_like = ?";
        PreparedStatement pstmt = cnx.prepareStatement(req);
        pstmt.setInt(1, id);
        pstmt.setBoolean(2, true);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
              count = rs.getInt(1);
        }
        return count;
    }
    
     public int countDislikes (int id) throws SQLException {

            int count =0;
            String req = "SELECT COUNT(*) FROM commentaire WHERE id_publication = ? AND nbr_dislike = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, true);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                  count = rs.getInt(1);
            }
            return count;
    }
 
     
       public int countCommentaire (int id) throws SQLException {

            int count =0;
            String req = "SELECT COUNT(*) FROM commentaire WHERE id_publication = ? AND nbr_dislike = ? AND nbr_like = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            pstmt.setBoolean(2, false);
            pstmt.setBoolean(3, false);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                  count = rs.getInt(1);
            }
            return count;
                }
       
       
}
