/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Location;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author eya
 */
public interface ILocation <Location>{
    public Location recupererById(int t) throws SQLException;
    public List<Location> recupererAllByIdVehicule(int t) throws SQLException;
    public List<Location> recupererAllByIdUser(int t) throws SQLException;
    public List<Location> recupererAllByIdChauffeur(int t) throws SQLException;
    public void AffecterUnchauffeur(int t,int idChauffeur) throws SQLException;
    public void supprimerLocation(int t) throws SQLException;
     public void AnnulerLocation(int i) throws SQLException;
}
