/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Connection;
import java.sql.SQLException;
import entities.Reclamation;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import typeenumeration.TypeReclamation;
import utils.MyDB;
/**
 *
 * @author benha
 */
public class ReclamationService implements IService<Reclamation> ,IReclamation<Reclamation>{
    Connection cnx;
    public ReclamationService(){
         cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouterReclamationSiege(Reclamation t) throws SQLException {
        String req = "INSERT INTO Reclamation (id_utilisateur, id_siege, type_reclamation, date_reclamation, etat, corps) VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_utilisateur() );
        ps.setInt(2, t.getId_siege());
        ps.setString(3, t.getType_reclamation() .toString());
        ps.setTimestamp(4, new Timestamp(t.getDate_reclamation().getTime()));
        ps.setBoolean(5,false);
        ps.setString(6, t.getCorps());
        ps.executeUpdate();  
    }
    @Override
 public void ajouterReclamationAdmin(Reclamation t) throws SQLException {
        String req = "INSERT INTO Reclamation (id_utilisateur, type_reclamation, date_reclamation, etat, corps) VALUES(?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId_utilisateur() );
        ps.setString(2, t.getType_reclamation() .toString());
        ps.setTimestamp(3, new Timestamp(t.getDate_reclamation().getTime()));
        ps.setBoolean(4, false);
        ps.setString(5, t.getCorps());
        ps.executeUpdate();  
    }
    @Override
    public List<Reclamation> recupererReclamationUtilisateur (int t) throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        String s = "select * from reclamation where id_utilisateur = ?";
        PreparedStatement st = cnx.prepareStatement(s);
        st.setInt(1, t);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setIdreclamation(rs.getInt("idreclamation"));
            r.setId_utilisateur(rs.getInt("id_utilisateur"));
            r.setId_siege(rs.getInt("id_siege"));
            r.setType_reclamation(TypeReclamation.valueOf(rs.getString("type_reclamation")));
            r.setDate_reclamation(rs.getDate("date_reclamation"));
            r.setEtat(rs.getBoolean("etat"));
            r.setCorps(rs.getString("corps"));
            reclamation.add(r);
        }
        return reclamation;
    }
    @Override
    public List<Reclamation> recupererReclamationdAdmin () throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        String s = "select * from reclamation where id_siege is null";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setIdreclamation(rs.getInt("idreclamation"));
            r.setId_utilisateur(rs.getInt("id_utilisateur"));
            r.setId_siege(rs.getInt("id_siege"));
            r.setType_reclamation(TypeReclamation.valueOf(rs.getString("type_reclamation")));
            r.setDate_reclamation(rs.getDate("date_reclamation"));
            r.setEtat(rs.getBoolean("etat"));
            r.setCorps(rs.getString("corps"));
            reclamation.add(r);
        }
        return reclamation;
    }
    @Override
      public List<Reclamation> recupererReclamation_Siege (int idSiege) throws SQLException {
        List<Reclamation> reclamation = new ArrayList<>();
        String s = "select * from reclamation where id_siege =? ";
        PreparedStatement st = cnx.prepareStatement(s);
         st.setInt(1, idSiege);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setIdreclamation(rs.getInt("idreclamation"));
            r.setId_utilisateur(rs.getInt("id_utilisateur"));
            r.setId_siege(rs.getInt("id_siege"));
            r.setType_reclamation(TypeReclamation.valueOf(rs.getString("type_reclamation")));
            r.setDate_reclamation(rs.getDate("date_reclamation"));
            r.setEtat(rs.getBoolean("etat"));
            r.setCorps(rs.getString("corps"));
            reclamation.add(r);
        }
        return reclamation;
    }
    @Override
    public void modifier(Reclamation t) throws SQLException {
        String req = "UPDATE Reclamation SET etat = ? where idreclamation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setBoolean(1, t.isEtat());
        ps.setInt(2, t.getIdreclamation());
        ps.executeUpdate();
    }

    @Override
    public void ajouter(Reclamation t) throws SQLException {
    }

    @Override
    public void supprimer(Reclamation t) throws SQLException {
    }

    @Override
    public List<Reclamation> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

 

  
    

   

