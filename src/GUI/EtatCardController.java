/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class EtatCardController implements Initializable {

    @FXML
    private ImageView image;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mail;
    @FXML
    private Label num;
    @FXML
    private Label date_naissance;
    @FXML
    private Label role;
    @FXML
    private ImageView photo_permis_avant;
    @FXML
    private ImageView photo_permis_arriere;
    @FXML
    private Label etat;
    @FXML
    private Button approuved;
    UserService us = new UserService();
    Utilisateur u = new Utilisateur();
    int iduser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setUser(Utilisateur ut) {
        File file = new File(ut.getImage());
        Image imagee = new Image(file.toURI().toString());
        
        
        
        
        nom.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        num.setText(String.valueOf(ut.getNum_tel()));
        //image.set(ut.getImage());
        //photo_permis_avant.setText(ut.getPhotopermis_avant());
        //photo_permis_arriere.setText(ut.getPhotopermis_arriere());
        date_naissance.setText(ut.getDate_naissance().toString());      
        mail.setText(ut.getMail());
        role.setText(String.valueOf(ut.getRole()));
        u.setImage(ut.getImage());
        image.setImage(imagee);
       // etat.setText(String.valueOf(ut.getetat()));
        System.out.println("Imageee"+imagee.toString());
        iduser=ut.getIdutilisateur();
        u=ut;

    }

    @FXML
    private void approuvez(ActionEvent event) throws IOException {
         try{
           us.approuver(u);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEtatUser.fxml"));
           Parent root = loader.load();
           approuved.getScene().setRoot(root);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
}