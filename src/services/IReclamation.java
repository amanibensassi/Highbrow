/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author benha
 */
public interface IReclamation<T> {

    public void ajouterReclamationSiege(T t) throws SQLException;

    public void ajouterReclamationAdmin(T t) throws SQLException;

    public List<Reclamation> recupererReclamationUtilisateur(int t) throws SQLException;

    public List<Reclamation> recupererReclamationdAdmin() throws SQLException;

    public List<Reclamation> recupererReclamation_Siege(int t) throws SQLException;

}
