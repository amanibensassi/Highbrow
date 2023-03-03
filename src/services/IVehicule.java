/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
import java.sql.SQLException;
import java.util.List;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
/**
 *
 * @author Trabelsi Mohamed
 */
public interface IVehicule<T> {
    public List<T> recupererVehiculeByCarburant(Carburant c) throws SQLException;
    public List<T> recupererVehiculeBySiege(int x) throws SQLException;
    public List<T> recupererVehiculeByEtat(Etat e) throws SQLException;
    public List<T> recupererVehiculeByPlace(NbrPlace n) throws SQLException;
}
