/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class CardLocationRespoAgenceController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private Label nomVeh;
    @FXML
    private Label db;
    @FXML
    private Label df;

    /**
     * Initializes the controller class.
     */
     public void setLocation(Location c) {
        
        String dateDebut = c.getDate_debut() + "";
        db.setText(dateDebut);
        String dateFin = c.getDate_fin() + "";
        df.setText(dateFin);
        boolean opch = c.isOpt_chauffeur();
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
