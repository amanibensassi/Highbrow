/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Entretien;
import entities.Location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;

/**
 *
 * @author anasm
 */
public class EntretienService implements IService<Entretien>,IEntretienService<Entretien>{

    Connection cnx;
    
    public EntretienService() {
        
         cnx = MyDB.getInstance().getCnx();
    }
    
    public List<Entretien> recuperelesEntretientByUserConnecte(int idu) throws SQLException {
        List<Entretien> Entretients = new ArrayList<>();
        String req = "SELECT e.* FROM siege s JOIN vehicule v ON s.idsiege = v.id_siege "
                + "JOIN entretien e ON v.idvehicule = e.id_vehicule "
                + "WHERE s.id_utilisateur = ?";
    PreparedStatement ps = cnx.prepareStatement(req);
    ps.setInt(1, idu);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            Entretients.add(e);
            
        }
        return Entretients;
    }

    @Override
    public void ajouter(Entretien t) throws SQLException {
       /* Pattern patternDate = Pattern.compile("^\\\\d{2}/\\\\d{2}/\\\\d{4}$");
        Matcher matcherDate = patternDate.matcher(String.valueOf(t.getDate_entretien()));
        
        if (!matcherDate.matches() ) {
            System.out.println("Veuillez verifier la date");
            throw new IllegalArgumentException("Veuillez verifier les données");
        }
        else {*/
        String req = "INSERT INTO entretien (date_entretien,id_mecanicien,id_vehicule,etat_entretien) VALUES (?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new java.sql.Timestamp(t.getDate_entretien().getTime()));
        ps.setInt(2, t.getId_mecanicien());
        ps.setInt(3, t.getId_vehicule());
        ps.setBoolean(4,false);
        ps.executeUpdate();
        //}
    }

//    @Override
//    public void modifier(Entretien t) throws SQLException {
//        String req = "UPDATE entretien SET date_entretien = ?,id_mecanicien = ?,id_vehicule = ?,etat_entretien = ? where identretien = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setTimestamp(1,new java.sql.Timestamp(t.getDate_entretien().getTime()));
//        ps.setInt(2, t.getId_mecanicien());
//        ps.setInt(3, t.getId_vehicule());
//        ps.setBoolean(4,t.isEtat_entretien());
//        ps.setInt(5, t.getIdentretien());
//        ps.executeUpdate();
//    }
    
    @Override
    public void modifier(Entretien t) throws SQLException {
        String req = "UPDATE entretien SET date_entretien = ? where identretien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1,new java.sql.Timestamp(t.getDate_entretien().getTime()));
        ps.setInt(2, t.getIdentretien());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Entretien t) throws SQLException { 
        String req = "DELETE FROM entretien where identretien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdentretien());
        ps.executeUpdate();
    }
        
    @Override
    public List<Entretien> recuperer() throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String req = "select * from entretien ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            entretiens.add(e);
            
        }
        return entretiens;
    }
    
    @Override
    public Entretien recupererById(Entretien t) throws SQLException {
        String req = "select * from entretien where identretien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdentretien());
        ResultSet rs =  ps.executeQuery();
        Entretien e = new Entretien();
        if (rs.next()){
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
        }
            return e;
    }
    
//    public List<Entretien> recupererEntretienByVehicule(Vehicule t) throws SQLException {
//        List<Entretien> entretiens = new ArrayList<>();
//        String req = "select * from entretien where id_vehicule = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setInt(1, t.getIdvehicule());
//        ResultSet rs =  ps.executeQuery();
//        while(rs.next()){
//            Entretien e = new Entretien();
//            e.setIdentretien(rs.getInt("identretien"));
//            e.setDate_entretien(rs.getTimestamp("date_entretien"));
//            e.setId_mecanicien(rs.getInt("id_mecanicien"));
//            e.setId_vehicule(rs.getInt("id_vehicule"));
//            entretiens.add(e);
//            
//        }
//        return entretiens;
//    }
    @Override
     public List<Entretien> recupererEntretienByVehicule(int id) throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String req = "select * from entretien where id_vehicule = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            entretiens.add(e);
            
        }
        return entretiens;
    }
     
     @Override
     public List<Entretien> recupererEntretienByMecanicien(int id) throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String req = "select * from entretien where id_mecanicien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, id);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            entretiens.add(e);
            
        }
        return entretiens;
    }
    
     @Override
     public void modifierEtat(Entretien t) throws SQLException {
        String req = "UPDATE entretien SET etat_entretien = ? where identretien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setBoolean(1, t.isEtat_entretien());
        ps.setInt(2, t.getIdentretien());
        ps.executeUpdate();
    }
    
    public List<Entretien> trieCroissant() throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String req = "select * from entretien order by date_entretien";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            entretiens.add(e);
            
        }
        return entretiens;
    }
    
    public List<Entretien> trieDecroissant() throws SQLException {
        List<Entretien> entretiens = new ArrayList<>();
        String req = "select * from entretien order by date_entretien DESC";
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Entretien e = new Entretien();
            e.setIdentretien(rs.getInt("identretien"));
            e.setDate_entretien(rs.getTimestamp("date_entretien"));
            e.setId_mecanicien(rs.getInt("id_mecanicien"));
            e.setId_vehicule(rs.getInt("id_vehicule"));
            e.setEtat_entretien(rs.getBoolean("etat_entretien"));
            entretiens.add(e);
            
        }
        return entretiens;
    }
    
}
