/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import typeenumeration.Note;
import utils.MyDB;

/**
 *
 * @author benha
 */
public class AvisService implements IService<Avis>, IAvis<Avis> {

    Connection cnx;

    public AvisService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouterAvisSiege(Avis t) throws SQLException {
        String req = "INSERT INTO Avis (note,idsiege, id_Utilisateur) VALUES(?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNote().toString());
        ps.setInt(2, t.getIdsiege());
        ps.setInt(3, t.getId_Utilisateur());
        ps.executeUpdate();
    }

    @Override
    public void ajouterAvisVehicule(Avis t) throws SQLException {
        String req = "INSERT INTO Avis (note,id_vehicule, id_Utilisateur) VALUES(?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNote().toString());
        ps.setInt(2, t.getId_vehicule());
        ps.setInt(3, t.getId_Utilisateur());
        ps.executeUpdate();
    }

    @Override
    public void modifier(Avis t) throws SQLException {
        String req = "UPDATE Avis SET note = ? where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNote().toString());
        ps.setInt(2, t.getId());
        ps.executeUpdate();
    }

    //@Override
    public void supprimer(Avis t) throws SQLException {
        String req = "DELETE FROM Avis where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getId());
        ps.executeUpdate();

    }

    public List<Avis> recupererAvisVehicule(int t) throws SQLException {
        List<Avis> avis = new ArrayList<>();
        String s = "select * from avis where id_vehicule = ?";
        PreparedStatement st = cnx.prepareStatement(s);
        st.setInt(1, t);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setId_vehicule(rs.getInt("id_vehicule"));
            a.setIdsiege(rs.getInt("idsiege"));
            a.setId_Utilisateur(rs.getInt("id_utilisateur"));
            a.setNote(Note.valueOf(rs.getString("note")));
            avis.add(a);
        }
        return avis;
    }

    public List<Avis> recupererAvisSiege(int t) throws SQLException {
        List<Avis> avis = new ArrayList<>();
        String s = "select * from avis where idsiege = ?";
        PreparedStatement st = cnx.prepareStatement(s);
        st.setInt(1, t);
        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setId_vehicule(rs.getInt("id_vehicule"));
            a.setIdsiege(rs.getInt("idsiege"));
            a.setId_Utilisateur(rs.getInt("id_utilisateur"));
            a.setNote(Note.valueOf(rs.getString("note")));
            avis.add(a);
        }
        return avis;
    }

    @Override
    public void ajouter(Avis t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Avis> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
