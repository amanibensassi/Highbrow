/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reponse;
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
public class ReponseService implements IService<Reponse>{
    Connection  cnx; 
  Date d = new Date();
  public ReponseService () {
        cnx = MyDB.getInstance().getCnx();
    }
  
@Override
    public void ajouter(Reponse t) throws SQLException {
        String req = "INSERT INTO reponse(date_reponse,reponse,id_utilisateur,id_commentaire) VALUES (?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(d.getTime()));
        st.setString(2,t.getReponse());
        st.setInt(3,t.getId_utilisateur());
        st.setInt(4,t.getId_commentaire());
        st.executeUpdate();
       
    }

    @Override
    public void modifier(Reponse t) throws SQLException {
       String req = "UPDATE reponse SET date_reponse=?,reponse=? where	id_reponse = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new Timestamp(d.getTime()));
        ps.setString(2, t.getReponse());
        ps.setInt(3, t.getIdreponse());
        ps.executeUpdate();
        
    }

    @Override
    public void supprimer(Reponse t) throws SQLException {
       
        String req = "DELETE FROM reponse WHERE id_reponse= ? ;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdreponse());
        ps.executeUpdate();
      
    }

    @Override
    public List<Reponse> recuperer() throws SQLException {
         List<Reponse> reponse = new ArrayList<>();
      String req = "SELECT * FROM reponse";
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Reponse p = new Reponse();
        //remplissage
           p.setReponse(rs.getString("reponse"));
           p.setDate_reponse(rs.getTimestamp("date_reponse"));
           p.setId_commentaire(rs.getInt("id_commentaire"));
           p.setId_utilisateur(rs.getInt("id_utilisateur"));
           p.setIdreponse(rs.getInt("id_reponse"));
        //ajout    
        reponse.add(p);
        
      }
     return reponse;
     
    }

     public List<Reponse> recupererParCommentaire(int t) throws SQLException {
         List<Reponse> Reponse = new ArrayList<>();
      String req = "SELECT * FROM reponse WHERE id_commentaire ="+t;
     PreparedStatement st = cnx.prepareStatement(req);
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Reponse p = new Reponse();
        //remplissage
           p.setReponse(rs.getString("reponse"));
           p.setDate_reponse(rs.getTimestamp("date_reponse"));
           p.setId_commentaire(rs.getInt("id_commentaire"));
           p.setId_utilisateur(rs.getInt("id_utilisateur"));
           p.setIdreponse(rs.getInt("id_reponse"));
        //ajout    
        Reponse.add(p); 
      }
     return Reponse;
    }
     
     public List<Reponse> recupererParUtilisateur(int t) throws SQLException {
         List<Reponse> Reponse = new ArrayList<>();
      String req = "SELECT * FROM reponse WHERE id_utilisateur ="+t;
     PreparedStatement st = cnx.prepareStatement(req);
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Reponse p = new Reponse();
        //remplissage
           p.setReponse(rs.getString("reponse"));
           p.setDate_reponse(rs.getTimestamp("date_reponse"));
           p.setId_commentaire(rs.getInt("id_commentaire"));
           p.setId_utilisateur(rs.getInt("id_utilisateur"));
           p.setIdreponse(rs.getInt("id_reponse"));
        //ajout    
        Reponse.add(p); 
      }
     return Reponse;
    }
     
        public int countCommentaire (int id) throws SQLException {

            int count =0;
            String req = "SELECT COUNT(*) FROM reponse WHERE id_commentaire = ?";
            PreparedStatement pstmt = cnx.prepareStatement(req);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                  count = rs.getInt(1);
            }
            return count;
                }
        
        
        
}
