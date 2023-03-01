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
import java.util.Date;
import java.util.List;
/**
 *
 * @author ameni
 */
public class PublicationService implements IService<Publication> {
  Connection  cnx; 
  Date d = new Date();
  public PublicationService () {
        cnx = MyDB.getInstance().getCnx();
    }
  
  

    @Override
    public void ajouter(Publication t) throws SQLException {
        
        String req = "INSERT INTO publication(date_publication,publication,id_utilisateur) VALUES (?,?,?)";
       
        PreparedStatement st = cnx.prepareStatement(req);
        st.setTimestamp(1,new Timestamp(t.getDate_publication().getTime()));
        st.setString(2,t.getPublication());
        st.setInt(3,t.getId_utilisateur());
        st.executeUpdate();
         
    }

    
    @Override
    public void modifier(Publication t) throws SQLException {
        String req = "UPDATE publication SET date_publication=?,publication=? where idpublication = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new Timestamp(d.getTime()));
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
        //ajout    
        publication.add(p);
        
      }
      
     return publication;
    }
    
    
     public Publication recupererParUtilisateurDate(Publication t) throws SQLException {
     List<Publication> publication = new ArrayList<>();
       Timestamp time;
          time = new Timestamp(t.getDate_publication().getTime());
          time.setNanos(0);
/******************************working one *********************************/
// String req = "SELECT * FROM publication WHERE id_utilisateur = " + t.getId_utilisateur() + " AND date_publication = '" + new Timestamp(t.getDate_publication().getTime()) + "';";
   
 String req = "SELECT * FROM publication WHERE id_utilisateur = " + t.getId_utilisateur() + " AND date_publication = '" + time + "';";

      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
         System.out.println("query is executed"); 
         Publication p = new Publication();
        //remplissage
         System.out.println(rs.first());
            p.setDate_publication(rs.getTimestamp("date_publication"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdpublication(rs.getInt("idpublication"));
            p.setPublication(rs.getString("publication"));
        //ajout   
        publication.add(p);
     return p;
    }
     
     public Publication recupererParId(int t) throws SQLException {
     List<Publication> publication = new ArrayList<>();
     String req = "SELECT * FROM publication WHERE idpublication  = "+ t;
      Statement st = cnx.createStatement();
      ResultSet rs = st.executeQuery(req);
         
         Publication p = new Publication();
        //remplissage
         System.out.println(rs.first());
         
            p.setDate_publication(rs.getTimestamp("date_publication"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdpublication(rs.getInt("idpublication"));
            p.setPublication(rs.getString("publication"));
        //ajout    
        publication.add(p);
        
     return p;
    }
}
