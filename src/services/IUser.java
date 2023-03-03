/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Utilisateur;
import java.sql.SQLException;

/**
 *
 * @author Hamma
 */
public interface IUser<T> {

    public void modifierPassword(T t) throws SQLException;

    public Utilisateur recupererById(int t) throws SQLException;

    public void desactiver(T t) throws SQLException;

    public void approuver(T t) throws SQLException;

    public Utilisateur authenticate(String mail, String password) throws SQLException;
}
