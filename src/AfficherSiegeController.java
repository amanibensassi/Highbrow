/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import entities.Siege;
import gui.SiegeController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.SiegeService;

/**
 * FXML Controller class
 *
 * @author Skander
 */
public class AfficherSiegeController implements Initializable {

    @FXML
    private GridPane grid;

    SiegeService ps = new SiegeService();
    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // GridPane grid = new GridPane();

        try {
            List<Siege> sieges = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 1; i < sieges.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Siege.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                SiegeController controller = loader.getController();
                controller.setSiege(sieges.get(i));

                grid.add(pane, column, row);
                grid.setAlignment(Pos.CENTER);
                grid.setHalignment(scrollPane, HPos.CENTER);
                grid.setValignment(scrollPane, VPos.CENTER);
                //grid.getParent().setStyle("-fx-background-color: #CCCCCC;");
                
                        column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur de récupération des personnes : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }
    
    

}