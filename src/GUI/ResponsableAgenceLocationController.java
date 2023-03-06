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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ResponsableAgenceLocationController implements Initializable {

    LocationService ls = new LocationService();
    @FXML
    private GridPane grid;
    @FXML
    private Button Demandech;
    @FXML
    private Button LA;
    @FXML
    private Button LC;

    /**
     * Initializes the controller class.
     */
    public void test(String k) throws IOException {
        try {

            int rowIndex = 1;
            int columnIndex = 0;
            List<Location> locations = new ArrayList<>();
            if (k == "annuler") {
                locations = ls.getLocationAnnuler();
                for (int i = 0; i < locations.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cardLocationRespoAgence.fxml"));

                    HBox AnchorPane = loader.load();
//                HBox AnchorPane = loader.load();
                    CardLocationRespoAgenceController controllerch = loader.getController();

                    controllerch.setLocation(locations.get(i));

                    grid.add(AnchorPane, columnIndex, rowIndex);
                    columnIndex++;
                    if (columnIndex == 2) {
                        columnIndex = 0;
                        rowIndex = rowIndex + 1;
                    }

                }
            }
            if (k == "demmande") {
                locations.clear();
                locations = ls.recupererListeDemandech();
                 
                 System.out.println("affecterrrrr"+locations);
                 grid.getChildren().clear();
                for (int i = 0; i < locations.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("RespoLocationCard.fxml"));
                   
                    AnchorPane AnchorPane = loader.load();
                    RespoLocationCardController controllerch = loader.getController();

                    controllerch.setLocation(locations.get(i));
                    grid.add(AnchorPane, columnIndex, rowIndex);
                    columnIndex++;
                    if (columnIndex == 2) {
                        columnIndex = 0;
                        rowIndex = rowIndex + 1;
                    }

                }
               
               
            }
            if (k == "confirme") {
                locations = ls.getLocationConfirmer();
                for (int i = 0; i < locations.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("cardLocationRespoAgence.fxml"));

                    HBox AnchorPane = loader.load();
                    CardLocationRespoAgenceController controllerch = loader.getController();

                    controllerch.setLocation(locations.get(i));

                    grid.add(AnchorPane, columnIndex, rowIndex);
                    columnIndex++;
                    if (columnIndex == 2) {
                        columnIndex = 0;
                        rowIndex = rowIndex + 1;
                    }

                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void intialautre() {
        try {
            test("confirme");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            test("confirme");
//        } catch (IOException ex) {
//            System.out.println(ex.getMessage());
//        }
    }

    @FXML
    private void Demmandechauffeur(ActionEvent event) throws IOException {

        grid.getChildren().clear();
        test("demmande");
    }

    @FXML
    private void locationConfirmer(ActionEvent event) throws IOException {

        grid.getChildren().clear();
        test("confirme");

    }

    @FXML
    private void LocationAnnuler(ActionEvent event) throws IOException {

        grid.getChildren().clear();
        test("annuler");

    }

    public void UpdateListe() throws IOException, SQLException {
        int rowIndex = 1;
        int columnIndex = 0;
        List<Location> locations = new ArrayList<>();
        locations = ls.recupererListeDemandech();
       for (Node node : grid.getChildren()) {
        if (node instanceof Label) {
            int index = rowIndex * grid.getColumnConstraints().size()+ columnIndex;
            if (index < locations.size()) {
                Location location = locations.get(index);
                ((Label) node).setText("Location #" + location.getIdlocation() + ": ");
            } else {
                ((Label) node).setText("");
            }
            columnIndex++;
            if (columnIndex >= grid.getColumnConstraints().size()) {
                columnIndex = 0;
                rowIndex++;
            }
        }
    }
       
//        for (int i = 0; i < locations.size(); i++) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("RespoLocationCard.fxml"));
//
//            AnchorPane AnchorPane = loader.load();
//            RespoLocationCardController controllerch = loader.getController();
//
//            controllerch.setLocation(locations.get(i));
//            grid.add(AnchorPane, columnIndex, rowIndex);
//            columnIndex++;
//            if (columnIndex == 2) {
//                columnIndex = 0;
//                rowIndex = rowIndex + 1;
//            }
//
//        }
    }

}
