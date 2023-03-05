/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AfficherEtatUserController implements Initializable {
          @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
            UserService us = new UserService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        int rowIndex = 1;
          int columnIndex = 0;
        try {
            List<Utilisateur> utilisateurs = us.recupererByEtat();
            System.out.println(utilisateurs);
            System.out.println(utilisateurs);
            for (int i = 0; i < utilisateurs.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("EtatCard.fxml"));

                HBox AnchorPane = loader.load();
                EtatCardController controllerch = loader.getController();

                controllerch.setUser(utilisateurs.get(i));
                System.out.println("Liste"+utilisateurs.get(i));
                System.out.println(utilisateurs.get(i));
                grid.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 1) {
                    columnIndex = 0;
                    rowIndex = rowIndex + 2;
                }

            }
            
            // TODO
        } catch (SQLException ex) {
            Logger.getLogger(AfficherUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
