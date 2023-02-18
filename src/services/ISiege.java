/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.SQLException;
import java.util.List;
import typeenumeration.Region;

/**
 *
 * @author Trabelsi Mohamed
 */
public interface ISiege<T> {
    public List<T> recupererSiegeByRegion(Region r) throws SQLException;
    public List<T> recupererSiegeByUtilisateur(int x) throws SQLException;
    
}
