/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Avis;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author benha
 */
public interface IAvis<T> {

    public void ajouterAvisVehicule(T t) throws SQLException;

    public void ajouterAvisSiege(T t) throws SQLException;

    public List<Avis> recupererAvisVehicule(int t) throws SQLException;

    public List<Avis> recupererAvisSiege(int t) throws SQLException;

}
