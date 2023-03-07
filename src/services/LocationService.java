/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Location;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import typeenumeration.EtatLocation;
import static typeenumeration.EtatLocation.annuler;
import utils.MyDB;

/**
 *
 * @author eya
 */
public class LocationService implements IService<Location>, ILocation<Location> {
    
    Connection cnx;
    
    public LocationService() {
        cnx = MyDB.getInstance().getCnx();
    }
    
    public List<Location> recupereleslocationdechaquepropagenceConfirmer(int idu) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "SELECT l.* FROM siege s JOIN vehicule v ON s.idsiege = v.id_siege "
                + "JOIN location l ON v.idvehicule = l.id_vehicule "
                + "WHERE s.id_utilisateur = ? AND l.etat = 'confirmer' "
                + "AND (l.opt_chauffeur = false OR l.id_chauffeur IS NOT NULL);";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idu);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    public List<Location> recupereleslocationdechaquepropagenceAnnulee(int idu) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "SELECT l.* FROM siege s JOIN vehicule v ON s.idsiege = v.id_siege "
                + "JOIN location l ON v.idvehicule = l.id_vehicule"
                + " WHERE s.id_utilisateur =? and l.etat ='annuler';;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idu);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    public List<Location> recupereleslocationdechaquepropagenceDemande(int idu) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "SELECT l.* FROM siege s JOIN vehicule v ON s.idsiege = v.id_siege JOIN location l ON v.idvehicule = l.id_vehicule "
                + "WHERE s.id_utilisateur = ? AND l.id_chauffeur IS NULL AND l.etat = 'confirmer' AND l.opt_chauffeur = true;;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idu);
//        ps.setBoolean(2, true);
//        ps.setString(3, EtatLocation.confirmer.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        System.out.println("srvieeeeeeeeeeeeee "+Locations);
        return Locations;
    }
    
    @Override
    public void ajouter(Location l) throws SQLException {
        String req = "INSERT INTO location(date_debut,date_fin,opt_chauffeur,id_vehicule,id_utilisateur,etat)VALUES(?,?,?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1, new Timestamp(l.getDate_debut().getTime()));
        ps.setTimestamp(2, new Timestamp(l.getDate_fin().getTime()));
        ps.setBoolean(3, l.isOpt_chauffeur());
        ps.setInt(4, l.getId_vehicule());
        ps.setInt(5, l.getId_utilisateur());
        ps.setString(6, "confirmer");
        ps.executeUpdate();
    }
    
    public TreeMap<Integer, Integer> nombreLocationParVehicule() throws SQLException {
        
        TreeMap<Integer, Integer> Locations = new TreeMap<Integer, Integer>();
        String req = "SELECT vehicule.idvehicule, COUNT(location.idlocation) as nombre_de_locations\n"
                + "FROM vehicule\n"
                + "LEFT JOIN location ON vehicule.idvehicule = location.id_vehicule\n"
                + "GROUP BY vehicule.idvehicule;";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Locations.put(rs.getInt("idvehicule"), rs.getInt("nombre_de_locations"));
            
        }
        
        return Locations;
    }
    
    @Override
    public List<Location> recupererAllByIdVehicule(int t) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where id_vehicule = ? and etat ='confirmer'";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    @Override
    public void AffecterUnchauffeur(int t, int idChauffeur) throws SQLException {
        String req = "UPDATE location SET id_chauffeur=? where idlocation= ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, idChauffeur);
        ps.setInt(2, t);
        
        ps.executeUpdate();
        
    }
    
    @Override
    public void supprimerLocation(int l) throws SQLException {
        String req1 = "select id_chauffeur from location where idlocation = ?";
        String req = "DELETE FROM location WHERE idlocation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        PreparedStatement ps1 = cnx.prepareStatement(req1);
        ps1.setInt(1, l);
        ps.setInt(1, l);
        ResultSet rs = ps1.executeQuery();
        if (rs.next()) {
            int idchauffeure = rs.getInt("Id_chauffeur");
            if (rs.getInt("Id_chauffeur") != 0) {
                String req2 = "UPDATE chauffeur SET disponibilite = 1 where idchauffeur =idchauffeure";
            } else {
            }
        }
        
        ps.executeUpdate();
    }
    
    @Override
    public Location modifierch(Location l) throws SQLException {
        String req = "UPDATE location SET date_debut=?,date_fin=?,opt_chauffeur=? where idlocation = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setTimestamp(1, new Timestamp(l.getDate_debut().getTime()));
        ps.setTimestamp(2, new Timestamp(l.getDate_fin().getTime()));
        ps.setBoolean(3, l.isOpt_chauffeur());
        ps.setInt(4, l.getIdlocation());
        System.out.println(ps);
        ps.executeUpdate();
        return l;
    }
    
    @Override
    public List<Location> recupererAllByIdUser(int t) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where id_utilisateur= ? and etat='confirmer'";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    public List<Location> getLocationConfirmer() throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where etat='confirmer'&& opt_chauffeur=false || etat='confirmer' && id_chauffeur is not null ";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    @Override
    public List<Location> recupererAllByIdChauffeur(int t) throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where id_chauffeur = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    public List<Location> recupererListeDemandech() throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where id_chauffeur is null and etat =? and opt_chauffeur=?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setBoolean(2, true);
        ps.setString(1, EtatLocation.confirmer.toString());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setEtat(EtatLocation.valueOf(rs.getString("etat")));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    public List<Location> getLocationAnnuler() throws SQLException {
        List<Location> Locations = new ArrayList<>();
        String req = "select * from location where etat ='annuler' ";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Location l = new Location();
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            Locations.add(l);
        }
        
        return Locations;
    }
    
    @Override
    public void AnnulerLocation(int i) throws SQLException {
        String req = "UPDATE location SET etat = 'annuler', id_chauffeur = null , opt_chauffeur=0 WHERE idlocation = ?;";
        PreparedStatement ps = cnx.prepareStatement(req);
        
        ps.setInt(1, i);
        
        System.out.println("anullation" + ps);
        ps.executeUpdate();
    }

    //Not To USE   
    @Override
    public Location recupererById(int t) throws SQLException {
        Location l = new Location();
        String req = "select * from location where idlocation = ? ";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, t);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            
            l.setIdlocation(rs.getInt("Idlocation"));
            l.setId_vehicule(rs.getInt("Id_vehicule"));
            l.setId_chauffeur(rs.getInt("Id_chauffeur"));
            l.setId_utilisateur(rs.getInt("Id_utilisateur"));
            l.setDate_debut(rs.getDate("date_debut"));
            l.setDate_fin(rs.getDate("date_fin"));
            l.setOpt_chauffeur(rs.getBoolean("opt_chauffeur"));
        }
        
        return l;
    }
    
    @Override
    public List<Location> recuperer() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void supprimer(Location t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void modifier(Location t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
