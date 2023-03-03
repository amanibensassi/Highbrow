/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import entities.Location;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.ChauffeurService;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ListeChauffeurDisponiblesController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    Date date_debut = new Date("2023/11/11");
    Date date_fin = new Date("2023/11/22");
    ChauffeurService ls = new ChauffeurService();
    Location l2 = new Location(43, date_debut, date_fin, true, 2, 1);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void test(Location location,int idlocation) {
        try {

            int rowIndex = 1;
            int columnIndex = 0;

            List<Chauffeur> chauf = ls.recupererChauffeursDisponibles(location);

            for (int i = 0; i < chauf.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ChauffeurDisponible.fxml"));

                HBox AnchorPane = loader.load();
                ChauffeurDisponibleController controllerch = loader.getController();

                controllerch.setChauffeur(chauf.get(i),idlocation);

                grid.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 1) {
                    columnIndex = 0;
                    rowIndex = rowIndex + 2;
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ListeChauffeurController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ListeChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
