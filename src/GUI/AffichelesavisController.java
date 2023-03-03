/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AvisController;
import GUI.AfficherReclamationController;
import entities.Avis;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.AvisService;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author benha
 */
public class AffichelesavisController implements Initializable {

    @FXML
    private GridPane grid;
    AvisService as = new AvisService();
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
      try {
            // TODO
            this.getData();
        } catch (ParseException ex) {
            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AffichelesavisController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }    
    
    private void getData() throws ParseException, SQLException{
        try {
            List<Avis> avis = as.recupererAvisSiege(3);
            System.out.println("aa"+avis);
            int row = 1;
            int column = 0;
            for (int i = 0; i < avis.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Avis.fxml"));
                HBox AnchorPane  = loader.load();
                //passage de parametres
                AvisController controller = loader.getController();
                controller.setAvis(avis.get(i));
                grid.add(AnchorPane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
