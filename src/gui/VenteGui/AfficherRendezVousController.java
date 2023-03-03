/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.VenteGui;

import entities.Vente;
import java.io.FileNotFoundException;
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
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AfficherRendezVousController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    Vente v = new Vente();
    VenteService vs = new VenteService();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
         int rowIndex = 1;
          int columnIndex = 0;
        try {
            List<Vente> Ventes = vs.recuperer();
            System.out.println(Ventes);
            for (int i = 0; i < Ventes.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RdvCard.fxml"));

                HBox AnchorPane = loader.load();
                RdvCardController controllerch = loader.getController();

                controllerch.setRdv(Ventes.get(i));
                System.out.println("Liste"+Ventes.get(i));
                System.out.println(Ventes.get(i));
                grid.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 1) {
                    columnIndex = 0;
                    rowIndex = rowIndex + 2;
                }
        // TODO
    }    
    
}       catch (SQLException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
