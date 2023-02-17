/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import entities.Publication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import utils.MyDB;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author ameni
 */
public class PublicationService implements IService<Publication> {
  Connection  cnx; 
  
  public PublicationService () {
        cnx = MyDB.getInstance().getCnx();
    }
  
  

    @Override
    public void ajouter(Publication t) throws SQLException {
        
        String req = "INSERT INTO publication(date_publication,publication,nbr_like,nbr_dislike,id_utilisateur) VALUES (?,?,?,?,?)";
       
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(t.getDate_publication().getTime()));
        st.setString(2,t.getPublication());
        st.setInt(3,t.getNbr_like());
        st.setInt(4,t.getNbr_dislike());
        st.setInt(5,t.getId_utilisateur());
        st.executeUpdate();
         
    }

    
    public void AddLike (Publication t) throws SQLException{
    String req = "UPDATE `publication` SET `nbr_like` = `nbr_like` + '1' WHERE `publication`.`idpublication` = ?;";
      PreparedStatement ps = cnx.prepareStatement(req);
      ps.setInt(1, t.getIdpublication());
      ps.executeUpdate();
    }
    
     public void AddDislike (Publication t) throws SQLException{
    String req = "UPDATE `publication` SET `nbr_dislike` = `nbr_dislike` + '1' WHERE `publication`.`idpublication` = ?;";
      PreparedStatement ps = cnx.prepareStatement(req);
      ps.setInt(1, t.getIdpublication());
      ps.executeUpdate();
    }
    
    @Override
    public void modifier(Publication t) throws SQLException {
        String req = "UPDATE publication SET date_publication=?,publication=? where idpublication = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new Timestamp(t.getDate_publication().getTime()));
        ps.setString(2, t.getPublication());
        ps.setInt(3, t.getIdpublication());
        ps.executeUpdate();
        
        
    }

    @Override
    public void supprimer(Publication t) throws SQLException {
        String req = "DELETE FROM publication WHERE idpublication = ? ;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdpublication());
        ps.executeUpdate();
       
    }

    @Override
    public List<Publication> recuperer() throws SQLException {
     List<Publication> publication = new ArrayList<>();
      String req = "SELECT * FROM publication";
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Publication p = new Publication();
        //remplissage
            p.setDate_publication(rs.getTimestamp("date_publication"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdpublication(rs.getInt("idpublication"));
            p.setPublication(rs.getString("publication"));
            p.setNbr_like(rs.getInt("nbr_like"));
            p.setNbr_dislike(rs.getInt("nbr_dislike"));
        //ajout    
        publication.add(p);
        
      }
      
     return publication;
     
    }
    
    public List<Publication> recupererParUtilisateur(int t) throws SQLException {
     List<Publication> publication = new ArrayList<>();
      String req = "SELECT * FROM publication WHERE id_utilisateur = " + t;
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
      while (rs.next()){
        Publication p = new Publication();
        //remplissage
            p.setDate_publication(rs.getTimestamp("date_publication"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdpublication(rs.getInt("idpublication"));
            p.setPublication(rs.getString("publication"));
            p.setNbr_like(rs.getInt("nbr_like"));
            p.setNbr_dislike(rs.getInt("nbr_dislike"));
        //ajout    
        publication.add(p);
        
      }
      
     return publication;
    }
  
}
