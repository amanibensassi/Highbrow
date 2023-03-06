/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Statement;
import javax.xml.bind.DatatypeConverter;
import utils.MyDB;

/**
 *
 * @author Hamma
 */
public class crypterPassword {
     public Connection cnx;
    private Statement ste;
    public crypterPassword() {
//	cnx = MyDB.getInstance().getCnx();
//        MyDB db=new MyDB();
//        cnx = db.getCnx();
          cnx = MyDB.getInstance().getCnx();
        
   
    }
    
    public String crypterPassword(String password) {
	String hashValue = "";
	try {

	    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	    messageDigest.update(password.getBytes());
	    byte[] digestedBytes = messageDigest.digest();
	    hashValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();

	} catch (NoSuchAlgorithmException e) {
	}

	return hashValue;
    }
    
    
}
