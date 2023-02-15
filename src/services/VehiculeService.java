/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vehicule;
import static java.lang.String.valueOf;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
import utils.MyDB;


public class VehiculeService implements IService<Vehicule>,IVehicule<Vehicule> {
    
    Connection cnx;
    
    public VehiculeService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Vehicule t) throws SQLException {
        String req = "INSERT INTO vehicule(marque,kilometrage,immatriculation,carburant,nbr_place,prix_par_jour,etat,prix_vente,id_siege,annee_circulation,image_vehicule) VALUES("
                + "'" + t.getMarque() + "','" + t.getKilometrage() + "','" + t.getImmatriculation()+ "','"
                + t.getCarburant() + "','" + t.getNbr_place().getValeur() + "','" + t.getPrix_par_jour() +"','" + t.getEtat() + "','"+ t.getPrix_vente() + "','"+ t.getId_siege() + "','" + new Timestamp(t.getAnnee_circulation().getTime())+ "','"+ t.getImage_vehicule()+ "'" + ")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }

    @Override
    public void modifier(Vehicule t) throws SQLException {
        String req = "UPDATE vehicule SET marque = ?,kilometrage = ?,immatriculation = ?,carburant = ?,nbr_place = ?,prix_par_jour = ?,etat = ?,prix_vente = ?,id_siege = ?,annee_circulation = ?,image_vehicule = ? where idvehicule = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getMarque());
        ps.setInt(2, t.getKilometrage());
        ps.setString(3, t.getImmatriculation());
        ps.setString(4, t.getCarburant().toString());
        ps.setString(5, valueOf(t.getNbr_place().getValeur()));
        ps.setFloat(6, t.getPrix_par_jour());
        ps.setString(7,t.getEtat().toString());
        ps.setFloat(8, t.getPrix_vente());
        ps.setInt(9, t.getId_siege());
        ps.setTimestamp(10,new Timestamp(t.getAnnee_circulation().getTime()));
        ps.setString(11, t.getImage_vehicule());
        ps.setInt(12, t.getIdvehicule());
        ps.executeUpdate();
    }

    @Override
    public void supprimer(Vehicule t) throws SQLException {
                String req = "DELETE FROM vehicule where idvehicule = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1,t.getIdvehicule());

        ps.executeUpdate();
    }

    @Override
    public List<Vehicule> recuperer() throws SQLException {
                List<Vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule";
        PreparedStatement st = cnx.prepareStatement(s);
        ResultSet rs =  st.executeQuery();
        while(rs.next()){
            Vehicule p = new Vehicule();
            p.setMarque(rs.getString("marque"));
            p.setKilometrage(rs.getInt("kilometrage"));
            p.setImmatriculation(rs.getString("immatriculation"));
            p.setCarburant(Carburant.valueOf(rs.getString("carburant")));
            switch (rs.getString("nbr_place"))
            {
                case "2": p.setNbr_place(NbrPlace.deux) ;break;
                case "5": p.setNbr_place(NbrPlace.cinq) ;break;
                case "7": p.setNbr_place(NbrPlace.sept) ;break;
                case "9": p.setNbr_place(NbrPlace.neuf) ;break;                                
            }          
            p.setPrix_par_jour(rs.getFloat("prix_par_jour"));
            p.setEtat(Etat.valueOf(rs.getString("etat")));
            p.setPrix_vente(rs.getFloat("prix_vente"));
            p.setId_siege(rs.getInt("id_siege"));
            p.setAnnee_circulation(rs.getDate("annee_circulation"));
            p.setImage_vehicule(rs.getString("image_vehicule"));
            p.setIdvehicule(rs.getInt("idvehicule"));
            
            vehicules.add(p);           
        }
        return vehicules;
    }

    @Override
    public List<Vehicule> recupererVehiculeByCarburant(Carburant c) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule WHERE Carburant = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setString(1,c.toString());
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Vehicule p = new Vehicule();
            p.setMarque(rs.getString("marque"));
            p.setKilometrage(rs.getInt("kilometrage"));
            p.setImmatriculation(rs.getString("immatriculation"));
            p.setCarburant(Carburant.valueOf(rs.getString("carburant")));
            switch (rs.getString("nbr_place"))
            {
                case "2": p.setNbr_place(NbrPlace.deux) ;break;
                case "5": p.setNbr_place(NbrPlace.cinq) ;break;
                case "7": p.setNbr_place(NbrPlace.sept) ;break;
                case "9": p.setNbr_place(NbrPlace.neuf) ;break;                                
            } 
            p.setPrix_par_jour(rs.getFloat("prix_par_jour"));
            p.setEtat(Etat.valueOf(rs.getString("etat")));
            p.setPrix_vente(rs.getFloat("prix_vente"));
            p.setId_siege(rs.getInt("id_siege"));
            p.setAnnee_circulation(rs.getDate("annee_circulation"));
            p.setImage_vehicule(rs.getString("image_vehicule"));
            p.setIdvehicule(rs.getInt("idvehicule"));

            vehicules.add(p);           
        }
        return vehicules;
    }

    @Override
    public List<Vehicule> recupererVehiculeBySiege(int x) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule WHERE id_siege = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setInt(1,x);
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Vehicule p = new Vehicule();
            p.setMarque(rs.getString("marque"));
            p.setKilometrage(rs.getInt("kilometrage"));
            p.setImmatriculation(rs.getString("immatriculation"));
            p.setCarburant(Carburant.valueOf(rs.getString("carburant")));
            switch (rs.getString("nbr_place"))
            {
                case "2": p.setNbr_place(NbrPlace.deux) ;break;
                case "5": p.setNbr_place(NbrPlace.cinq) ;break;
                case "7": p.setNbr_place(NbrPlace.sept) ;break;
                case "9": p.setNbr_place(NbrPlace.neuf) ;break;                                
            } 
            p.setPrix_par_jour(rs.getFloat("prix_par_jour"));
            p.setEtat(Etat.valueOf(rs.getString("etat")));
            p.setPrix_vente(rs.getFloat("prix_vente"));
            p.setId_siege(rs.getInt("id_siege"));
            p.setAnnee_circulation(rs.getDate("annee_circulation"));
            p.setImage_vehicule(rs.getString("image_vehicule"));
            p.setIdvehicule(rs.getInt("idvehicule"));

            vehicules.add(p);           
        }
        return vehicules;

    }

    @Override
    public List<Vehicule> recupererVehiculeByEtat(Etat e) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule WHERE etat = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        ps.setString(1,e.toString());
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Vehicule p = new Vehicule();
            p.setMarque(rs.getString("marque"));
            p.setKilometrage(rs.getInt("kilometrage"));
            p.setImmatriculation(rs.getString("immatriculation"));
            p.setCarburant(Carburant.valueOf(rs.getString("carburant")));
            switch (rs.getString("nbr_place"))
            {
                case "2": p.setNbr_place(NbrPlace.deux) ;break;
                case "5": p.setNbr_place(NbrPlace.cinq) ;break;
                case "7": p.setNbr_place(NbrPlace.sept) ;break;
                case "9": p.setNbr_place(NbrPlace.neuf) ;break;                                
            } 
            p.setPrix_par_jour(rs.getFloat("prix_par_jour"));
            p.setEtat(Etat.valueOf(rs.getString("etat")));
            p.setPrix_vente(rs.getFloat("prix_vente"));
            p.setId_siege(rs.getInt("id_siege"));
            p.setAnnee_circulation(rs.getDate("annee_circulation"));
            p.setImage_vehicule(rs.getString("image_vehicule"));
            p.setIdvehicule(rs.getInt("idvehicule"));

            vehicules.add(p);           
        }
        return vehicules;
    }

    @Override
    public List<Vehicule> recupererVehiculeByPlace(NbrPlace n) throws SQLException {
        List<Vehicule> vehicules = new ArrayList<>();
        String s = "select * from vehicule WHERE nbr_place = ?";
        PreparedStatement ps = cnx.prepareStatement(s);
        String nbr=n.toString();
        switch (nbr)
            {
              case "deux": ps.setString(1,"2") ;break;
              case "cinq": ps.setString(1,"5") ;break;
              case "sept": ps.setString(1,"7") ;break;
              case "neuf": ps.setString(1,"2") ;break;                                   
            } 
        //ps.setString(1,n.toString());
        ResultSet rs= ps.executeQuery();
        while(rs.next()){
            Vehicule p = new Vehicule();
            p.setMarque(rs.getString("marque"));
            p.setKilometrage(rs.getInt("kilometrage"));
            p.setImmatriculation(rs.getString("immatriculation"));
            p.setCarburant(Carburant.valueOf(rs.getString("carburant")));
            switch (rs.getString("nbr_place"))
            {
                case "2": p.setNbr_place(NbrPlace.deux) ;break;
                case "5": p.setNbr_place(NbrPlace.cinq) ;break;
                case "7": p.setNbr_place(NbrPlace.sept) ;break;
                case "9": p.setNbr_place(NbrPlace.neuf) ;break;                                
            } 
            p.setPrix_par_jour(rs.getFloat("prix_par_jour"));
            p.setEtat(Etat.valueOf(rs.getString("etat")));
            p.setPrix_vente(rs.getFloat("prix_vente"));
            p.setId_siege(rs.getInt("id_siege"));
            p.setAnnee_circulation(rs.getDate("annee_circulation"));
            p.setImage_vehicule(rs.getString("image_vehicule"));
            p.setIdvehicule(rs.getInt("idvehicule"));

            vehicules.add(p);           
        }
        return vehicules;
    }
    
    
    
}
