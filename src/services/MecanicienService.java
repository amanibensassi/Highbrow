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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        Pattern patternNomPrenom = Pattern.compile("[a-zA-Z]+");
        Pattern patternContact = Pattern.compile("[0-9]+");
        Matcher matcherNom = patternNomPrenom.matcher(t.getNom_mecanicien());
        Matcher matcherPrenom = patternNomPrenom.matcher(t.getPrenom_mecanicien());
        Matcher matcherContact = patternContact.matcher(String.valueOf(t.getContact()));
        
        if (!matcherNom.matches() | !matcherPrenom.matches() | !matcherContact.matches()) {
            System.out.println("Veuillez verifier les données");
            throw new IllegalArgumentException("Veuillez verifier les données");
        }
        else {
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
    }

    @Override
    public void modifier(Mecanicien t) throws SQLException {
//        Pattern patternNomPrenom = Pattern.compile("[a-zA-Z]+");
//        //Pattern patternContact = Pattern.compile("[0-9]+");
//        Pattern patternContact = Pattern.compile("^\\d{8}$");
//        Matcher matcherNom = patternNomPrenom.matcher(t.getNom_mecanicien());
//        Matcher matcherPrenom = patternNomPrenom.matcher(t.getPrenom_mecanicien());
//        Matcher matcherContact = patternContact.matcher(String.valueOf(t.getContact()));
//        
//        if (!matcherNom.matches() | !matcherPrenom.matches() | !matcherContact.matches()) {
//            System.out.println("Veuillez verifier les données");
//            throw new IllegalArgumentException("Veuillez verifier les données");
//        }
//        else {
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
        //}
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
    
//    @Override
    public List<Mecanicien> recupererMecanicienByRegion(String nom) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where region = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nom);
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
    
    //@Override
    public List<Mecanicien> recupererMecanicienBySpecialite(String nom) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where specialite = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, nom);
        ResultSet rs =  ps.executeQuery();
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
    public List<Date> mecaniciensIsDispo(Mecanicien t,Date d) throws SQLException {
        List <Date> dateMec = new ArrayList<>();
        String req= "select date_entretien from entretien where id_mecanicien = ? AND etat_entretien = false AND YEAR(date_entretien) = ? AND MONTH(date_entretien) = ? AND DAY(date_entretien) = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date=sdf.format(d);
        String[] parts = date.split("-");
        ps.setInt(1, t.getIdmecanicien());
        ps.setInt(2, Integer.valueOf(parts[0]));
        ps.setInt(3, Integer.valueOf(parts[1]));
        ps.setInt(4, Integer.valueOf(parts[2]));
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
        Date dd=rs.getTimestamp("date_entretien");
        dateMec.add(dd);
        }
        return dateMec;
    }
    
    public List<Date> mecaniciensInDispo(Mecanicien t) throws SQLException {
        List <Date> dateMec = new ArrayList<>();
        String req= "select date_entretien from entretien where id_mecanicien = ? AND etat_entretien = false ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t.getIdmecanicien());
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
        Date dd=rs.getTimestamp("date_entretien");
        dateMec.add(dd);
        }
        return dateMec;
    }
    
   public List<Mecanicien> rechercherMecanicien(String nom) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where nom_mecanicien like ? or prenom_mecanicien like ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, "%"+nom+"%");
        ps.setString(2, "%"+nom+"%");    
        ResultSet rs =  ps.executeQuery();
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
   
    public List<Mecanicien> recupererMecanicienBySpecialiteEtRegion(String specialite,String region) throws SQLException {
        List<Mecanicien> mecaniciens = new ArrayList<>();
        String req = "select * from mecanicien where specialite = ? AND where region = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, specialite);
        ps.setString(2, region);
        ResultSet rs =  ps.executeQuery();
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
    
}
