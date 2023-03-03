/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.PropProfileController;
import GUI.ClientProfileController;
import entities.Utilisateur;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Hamma
 *
 */
public class AuthenticateController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField mot_de_passe;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();
    Utilisateur u = new Utilisateur();

    Connection cnx;
    @FXML
    private Button login;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }
    
    
    

    @FXML
    private void authenticate(ActionEvent event) throws SQLException {
        if (us.authenticate(mail.getText(), mot_de_passe.getText()).getIdutilisateur() != 0) {
            if((us.role_selection(mail.getText())).equals("client")){
                
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
            Parent root;
            try {

                System.out.println("auth" + us.authenticate(mail.getText(), mot_de_passe.getText()));
                u = us.authenticate(mail.getText(), mot_de_passe.getText());
                System.out.println(u);
                root = loader.load();
                ClientProfileController controller = loader.getController();
                controller.setData(u);
                //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
                mail.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
          else if((us.role_selection(mail.getText())).equals("proprietaire_agence")){
                
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PropProfile.fxml"));
            Parent root;
            try {

                System.out.println("auth" + us.authenticate(mail.getText(), mot_de_passe.getText()));
                u = us.authenticate(mail.getText(), mot_de_passe.getText());
                System.out.println(u);
                root = loader.load();
                PropProfileController controller = loader.getController();
                controller.setData(u);
                //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
                mail.getScene().setRoot(root);
            } catch (IOException ex) {
                Logger.getLogger(AuthenticateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
            
    }
}
//    public String cryptage(String password) throws NoSuchAlgorithmException {
//        MessageDigest m = MessageDigest.getInstance("MD5");
//        m.reset();
//        m.update(password.getBytes());
//        byte[] digest = m.digest();
//        BigInteger bigint = new BigInteger(1, digest);
//        String hpassword = bigint.toString(16);
//        while (hpassword.length() < 32) {
//            hpassword = "0" + hpassword;
//        }
//        return hpassword;
//    }
//    

    @FXML
    private void mdp_oublie(MouseEvent event) {
    }
}
