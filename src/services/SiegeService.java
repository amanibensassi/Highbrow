/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Siege;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.MyDB;
import typeenumeration.Region;

/**
 *
 * @author Trabelsi Mohamed
 */
public class SiegeService implements IService<Siege>,ISiege<Siege> {
    
    Connection cnx;  
    
    public SiegeService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    @Override
    public void ajouter(Siege t) throws SQLException {       
        String req = "INSERT INTO siege(nom_siege,region,adresse,mail,num_tel_siege,id_utilisateur) VALUES("
                + "'" + t.getNom_siege() + "','" + t.getRegion() + "','" + t.getAdresse()+ "','"
                + t.getMail() + "','" + t.getNum_tel_siege()+ "','" + t.getId_utilisateur() +"'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);        
    }

    @Override
    public void modifier(Siege t) throws SQLException {
        String req = "UPDATE siege SET nom_siege = ?,region = ?,adresse = ?,mail = ?,num_tel_siege = ?,id_utilisateur = ? where idsiege = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getNom_siege());
        ps.setString(2, t.getRegion().toString());
        ps.setString(3, t.getAdresse());
        ps.setString(4, t.getMail());
        ps.setInt(5, t.getNum_tel_siege());
        ps.setInt(6, t.getId_utilisateur());
        ps.setInt(7, t.getIdsiege());
        ps.executeUpdate();      
    }
    

    @Override
    public void supprimer(Siege t) throws SQLException {
        String req = "DELETE FROM siege where idsiege = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,t.getIdsiege());
        ps.executeUpdate();
    }

    @Override
    public List<Siege> recuperer() throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege";
        Statement st = cnx.createStatement();
        ResultSet rs =  st.executeQuery(s);
        while(rs.next()){
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));
                       
            sieges.add(p);           
        }
        return sieges;
    }

    @Override
    public List<Siege> recupererSiegeByRegion(Region r) throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege WHERE region = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setString(1,r.toString());
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));
                       
            sieges.add(p);           
        }
        return sieges;
    }

    @Override
    public List<Siege> recupererSiegeByUtilisateur(int x) throws SQLException {
        List<Siege> sieges = new ArrayList<>();
        String s = "select * from siege WHERE id_utilisateur = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setInt(1,x);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Siege p = new Siege();
            p.setNom_siege(rs.getString("nom_siege"));
            p.setRegion(Region.valueOf(rs.getString("region")));
            p.setAdresse(rs.getString("adresse"));
            p.setMail(rs.getString("mail"));
            p.setNum_tel_siege(rs.getInt("num_tel_siege"));
            p.setId_utilisateur(rs.getInt("id_utilisateur"));
            p.setIdsiege(rs.getInt("idsiege"));
                       
            sieges.add(p);           
        }
        return sieges;
    }    

}
