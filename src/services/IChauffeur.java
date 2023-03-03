/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import entities.Chauffeur;
import entities.Location;

/**
 *
 * @author eya
 */
public interface IChauffeur <T>{
    public Chauffeur recupererById(int t) throws SQLException;
    public List<Chauffeur> recupererChauffeursDisponibles(Location l) throws SQLException;
    public List<Chauffeur> recupererChauffeurBYidSiege(int i) throws SQLException;

    
}
