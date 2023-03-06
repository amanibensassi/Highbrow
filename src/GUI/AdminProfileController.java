/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AdminProfileController implements Initializable {

    private Button lusers;
   UserService us = new UserService();
    Utilisateur u = new Utilisateur();
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    @FXML
    private Label mail;
    int idclient;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setData(Utilisateur ut){
        
        nom.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        num.setText(String.valueOf(ut.getNum_tel()));
        mail.setText(ut.getMail());
       // img.setImage(ut.getImage());
       // u.setPrenom(ut.getPrenom());
        //u.setNum_tel(ut.getNum_tel());
        idclient=ut.getIdutilisateur();
        //nom.setText(u.getNom());
        System.out.println("id"+idclient);
        System.out.println("ut.getnom"+ut.getNom());
        System.out.println("cientprofile"+ut);
    }

    private void afficher(ActionEvent event) {
         try {
                                        
                                        System.out.println(u);
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
                                        Parent root = loader.load();
                                        
                                        AfficherUtilisateurController controller = loader.getController();
                                       
                                       // us.modifier(u);
                                       //controller.setData(u);
                                        
                                        lusers.getScene().setRoot(root);     
                                    } catch (IOException ex) {
                                        System.out.println("error1" + ex.getMessage());
                                    }
        
        
    }
    }
    

