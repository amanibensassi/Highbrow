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
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AdminProfileController implements Initializable {

    @FXML
    private Button lusers;
   UserService us = new UserService();
    Utilisateur u = new Utilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
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
    

