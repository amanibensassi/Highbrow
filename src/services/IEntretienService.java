/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Entretien;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author anasm
 */
public interface IEntretienService <T>{
    
    public T recupererById(T t) throws SQLException;;
    public List<T> recupererEntretienByVehicule(int id) throws SQLException;;
    public List<T> recupererEntretienByMecanicien(int id) throws SQLException;;
    public void modifierEtat(Entretien t) throws SQLException;;
    
    
}
