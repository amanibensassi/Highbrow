/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author anasm
 */
public interface IMecanicienService <T>{
    
    public T recupererById(T t) throws SQLException;
    public List<T> recupererMecanicienByRegion(T t) throws SQLException;
    public List<T> recupererMecanicienBySpecialite(T t) throws SQLException;
    public List<Date> mecaniciensIsDispo(T t) throws SQLException;
    
}
