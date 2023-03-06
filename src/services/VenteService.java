/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import entities.Vente;
import java.sql.Connection;
import java.sql.Date;
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
 * @author Hamma
 */
public class VenteService implements IService<Vente>,Ivente<Vente> {

    Connection cnx;

    public VenteService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vente v) throws SQLException {

        String req = "INSERT INTO vente (date_rendez_vous,id_vehicule,id_utilisateur) VALUES (?,?,?)";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setTimestamp(1, new Timestamp(v.getDate_rendez_vous().getTime()));

        ps.setInt(2, v.getId_vehicule());
        ps.setInt(3, v.getId_utilisateur());

        ps.executeUpdate();
    }
// recuper renddez vous de vente by id user 
  @Override
    public Vente recupererById(int id) throws SQLException {
        String req = "select * from vente where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Vente vu = new Vente();

        if (rs.next()) {
            vu.setIdvente(rs.getInt("idvente"));
            vu.setDate_rendez_vous(rs.getDate("date_rendez_vous"));
            vu.setId_vehicule(rs.getInt("id_vehicule"));
            vu.setId_utilisateur(rs.getInt("id_utilisateur"));

        }
        return vu;
    }

    // recuper by id vehicules
      @Override
    public Vente recupererByIdVehicule(int id) throws SQLException {
        String req = "select * from vente where id_vehicule = ?";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
       
  Vente veh = new Vente();
        if (rs.next()) {
           
            veh.setIdvente(rs.getInt("idvente"));
            veh.setDate_rendez_vous(rs.getDate("date_rendez_vous"));
            veh.setId_vehicule(rs.getInt("id_vehicule"));
            veh.setId_utilisateur(rs.getInt("id_utilisateur"));

        }
        return veh;
    }

    @Override
    public List<Vente> recuperer() throws SQLException {
        List<Vente> Ventes = new ArrayList<>();
        String s = "select * from vente";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        while (rs.next()) {
            Vente ve = new Vente();
            ve.setIdvente(rs.getInt(1));
            ve.setDate_rendez_vous(rs.getTimestamp("date_rendez_vous"));
            ve.setId_vehicule(rs.getInt("id_vehicule"));
            ve.setId_utilisateur(rs.getInt("id_utilisateur"));
           
            Ventes.add(ve);

        }

        return Ventes;

    }
    
    
    public List<Vente> recupererAllvehiculesByidVehicule(int id) throws SQLException {
        List<Vente> Ventes = new ArrayList<>();
        String s = "select * from vente where id_vehicule =?";
        PreparedStatement ps = cnx.prepareCall(s);
        ps.setInt(1, id);
        System.out.println("REQUETE"+ps);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vente ve = new Vente();
            ve.setIdvente(rs.getInt(1));
            ve.setDate_rendez_vous(rs.getTimestamp("date_rendez_vous"));
            ve.setId_vehicule(rs.getInt("id_vehicule"));
            ve.setId_utilisateur(rs.getInt("id_utilisateur"));
           
            Ventes.add(ve);

        }

        return Ventes;

    }
    
    public List<Vente> recupererAllvehiculesByidUtilisateur(int id) throws SQLException {
        List<Vente> Ventes = new ArrayList<>();
        String s = "select * from vente where id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareCall(s);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Vente ve = new Vente();
            ve.setIdvente(rs.getInt(1));
            ve.setDate_rendez_vous(rs.getTimestamp("date_rendez_vous"));
            ve.setId_vehicule(rs.getInt("id_vehicule"));
            ve.setId_utilisateur(rs.getInt("id_utilisateur"));
           
            Ventes.add(ve);

        }

        return Ventes;

    }
    

     @Override
    public void modifier(Vente v) throws SQLException {
        String req = "UPDATE vente SET date_rendez_vous = ? where idvente = ? ";
        PreparedStatement ps = cnx.prepareCall(req);
        ps.setTimestamp(1, new Timestamp(v.getDate_rendez_vous().getTime()));
        ps.setInt(2, v.getIdvente());
       

        ps.executeUpdate();

    }

    @Override
    public void supprimer(Vente v) throws SQLException {
        String req = "DELETE FROM vente WHERE idvente = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, v.getIdvente());
        ps.executeUpdate();
    }

   

}
