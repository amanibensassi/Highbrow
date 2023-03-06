/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.LocationService;
import services.UserConn;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ListeMesLocationsController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    LocationService ls = new LocationService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void listemeslocation() {
        try {

            int rowIndex = 1;
            int columnIndex = 0;

            List<Location> locations = ls.recupererAllByIdUser(UserConn.idutilisateur);
            
            for (int i = 0; i < locations.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CardLocation.fxml"));

                HBox AnchorPane = loader.load();
                CardLocationController controllerch = loader.getController();

                controllerch.setLocation(locations.get(i));
                
               
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

    @FXML
    private void modal(MouseEvent event) {
    }

    void setLocation(Location l) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
