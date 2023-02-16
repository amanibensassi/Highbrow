/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Mecanicien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import typeenumeration.Specialite;
import typeenumeration.Region;
import utils.MyDB;

/**
 *
 * @author anasm
 */
public class MecanicienService implements IService<Mecanicien>,IMecanicienService<Mecanicien>{

   Connection cnx;

    public MecanicienService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Mecanicien t) throws SQLException {
        String req = "INSERT INTO mecanicien (nom_mecanicien,prenom_mecanicien,contact,specialite,region,adresse,image) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_mecanicien());
        ps.setString(2, t.getPrenom_mecanicien());
        ps.setInt(3, t.getContact());
        ps.setString(4, t.getSpecialite().toString());
        ps.setString(5, t.getRegion().toString());
        ps.setString(6, t.getAdresse());
        ps.setString(7, t.getImage());
        ps.executeUpdate();
    }

    @Override
    public void modifier(Mecanicien t) throws SQLException {
        String req = "UPDATE mecanicien SET nom_mecanicien = ?,prenom_mecanicien = ?,contact = ?,specialite = ?,region = ?,adresse = ?,image = ? where idmecanicien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_mecanicien());
        ps.setString(2,t.getPrenom_mecanicien());
        ps.setInt(3, t.getContact());
        ps.setString(4, t.getSpecialite().toString());
        ps.setString(5, t.getRegion().toString());
        ps.setString(6, t.getAdresse());
        ps.setString(7, t.getImage());
        ps.setInt(8,t.getIdmecanicien());
        ps.executeUpdate();
        
    }

    @Override
    public void supprimer(Mecanicien t) throws SQLException {
     
        String req = "DELETE FROM mecanicien where idmecanicien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdmecanicien());
        ps.executeUpdate();
        
    }
    
    @Override
    public List<Mecanicien> recuperer() throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String s = "select * from mecanicien";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Mecanicien m = new Mecanicien();
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setNom_mecanicien(rs.getString("nom_mecanicien"));
            m.setPrenom_mecanicien(rs.getString("prenom_mecanicien"));
            m.setContact(rs.getInt("contact"));
            m.setSpecialite(Specialite.valueOf(rs.getString("specialite")));
            m.setRegion(Region.valueOf(rs.getString("region")));
            m.setAdresse(rs.getString("adresse"));
            m.setImage(rs.getString("image"));
            mecaniciens.add(m);
            
        }
        return mecaniciens;
    }
    
    @Override
    public Mecanicien recupererById(Mecanicien t) throws SQLException {
        String req = "select * from mecanicien where idmecanicien = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdmecanicien());
        ResultSet rs =  ps.executeQuery();
        Mecanicien m = new Mecanicien();
        if (rs.next()){
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setNom_mecanicien(rs.getString("nom_mecanicien"));
            m.setContact(rs.getInt("contact"));
            m.setSpecialite(Specialite.valueOf(rs.getString("specialite")));
            m.setRegion(Region.valueOf(rs.getString("region")));
            m.setAdresse(rs.getString("adresse"));
            m.setImage(rs.getString("image"));
        }
            return m;
    }
    
    @Override
    public List<Mecanicien> recupererMecanicienByRegion(Mecanicien t) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where region = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getRegion().toString());
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Mecanicien m = new Mecanicien();
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setNom_mecanicien(rs.getString("nom_mecanicien"));
            m.setContact(rs.getInt("contact"));
            m.setSpecialite(Specialite.valueOf(rs.getString("specialite")));
            m.setRegion(Region.valueOf(rs.getString("region")));
            m.setAdresse(rs.getString("adresse"));
            m.setImage(rs.getString("image"));
            mecaniciens.add(m);
            
        }
        return mecaniciens;
    }
    
    @Override
    public List<Mecanicien> recupererMecanicienBySpecialite(Mecanicien t) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where specialite = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getSpecialite().toString());
        ResultSet rs =  ps.executeQuery();
        while(rs.next()){
            Mecanicien m = new Mecanicien();
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setIdmecanicien(rs.getInt("idmecanicien"));
            m.setNom_mecanicien(rs.getString("nom_mecanicien"));
            m.setContact(rs.getInt("contact"));
            m.setSpecialite(Specialite.valueOf(rs.getString("specialite")));
            m.setRegion(Region.valueOf(rs.getString("region")));
            m.setAdresse(rs.getString("adresse"));
            m.setImage(rs.getString("image"));
            mecaniciens.add(m);
            
        }
        return mecaniciens;
    }
    
//    public boolean estDispo(Mecanicien t,Date d) throws SQLException {
//        boolean res=false;
//        List <Date> dateMec = new ArrayList<>();
//        String req = "select * from entretien where id_mecanicien = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setInt(1, t.getIdmecanicien());
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()){
//        Date dd=rs.getTimestamp("date_entretien");
//        dateMec.add(dd);
//        }
//        for (Date i : dateMec) {
//          if (d.after(i)){
//            res=true;
//        }
//        }
//        return res;
//    }
    
    
   @Override
    public List<Date> mecaniciensIsDispo(Mecanicien t) throws SQLException {
        List <Date> dateMec = new ArrayList<>();
        String req= "select date_entretien from entretien where id_mecanicien = ? AND etat_entretien = false";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdmecanicien());
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
        Date dd=rs.getTimestamp("date_entretien");
        dateMec.add(dd);
        }
        return dateMec;
    }
    
}
