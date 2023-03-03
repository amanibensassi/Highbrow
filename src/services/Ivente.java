/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Vente;
import java.sql.SQLException;

/**
 *
 * @author Hamma
 */
public interface Ivente<T> {

   // public void ajouterRendezVous(T t) throws SQLException;

    public Vente recupererById(int t) throws SQLException;

    public Vente recupererByIdVehicule(int t) throws SQLException;
}
