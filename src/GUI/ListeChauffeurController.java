/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
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
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.ChauffeurService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ListeChauffeurController implements Initializable {

    private GridPane userGrid;
    ChauffeurService chaufService = new ChauffeurService();
    @FXML
    private GridPane us;
    @FXML
    private ImageView loadToAjoutCh;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {

        try {

            us.setHgap(10);
            us.setVgap(10);
            int rowIndex = 1;
            int columnIndex = 0;

            List<Chauffeur> Chauffeurs = chaufService.recuperer();
            for (int i = 0; i < Chauffeurs.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Chauffeur.fxml"));

                AnchorPane AnchorPane = loader.load();
                ChauffeurController controllerch = loader.getController();

                controllerch.setChauffeur(Chauffeurs.get(i));
                System.out.println(Chauffeurs.get(i));
                us.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 3) {
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
    private void loadToAjoutCh(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddChauffeur.fxml"));
        Parent root = loader.load();

        loadToAjoutCh.getScene().setRoot(root);
    }
}
