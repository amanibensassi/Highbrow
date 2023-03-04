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
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    @FXML
    private TextField rechercher;
    @FXML
    private AnchorPane btnrecherche;
    int siegeid;

    /**
     * Initializes the controller class.
     */
    @Override

    public void initialize(URL url, ResourceBundle rb) {
    }

    public void affichage(int id) {
        this.siegeid = id;
        try {

            us.setHgap(10);
            us.setVgap(10);
            int rowIndex = 1;
            int columnIndex = 0;

            List<Chauffeur> Chauffeurs = chaufService.recupererChauffeurBYidSiege(id);
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AddChauffeur.fxml"));
        Parent root2 = loader1.load();
        AddChauffeurController controllerch = loader1.getController();
        System.out.println(siegeid);
        controllerch.getidsiege(siegeid);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();

        loadToAjoutCh.getScene().setRoot(borderPane);
    }

    @FXML
    private void rechercheChauffeur(KeyEvent event) throws IOException, SQLException {

        String recherche = rechercher.getText();
        List<Chauffeur> ch = null;
        try {
            ch = chaufService.recupererChauffeurBYidSiege(siegeid);
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Chauffeur> resultatsRecherche = ch.stream()
                .filter(s -> s.getNom().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());

        us.getChildren().clear();
        int rowIndex = 1;
        int columnIndex = 0;

        for (int i = 0; i < resultatsRecherche.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Chauffeur.fxml"));

            AnchorPane AnchorPane = loader.load();
            ChauffeurController controllerch = loader.getController();

            controllerch.setChauffeur(resultatsRecherche.get(i));
            System.out.println(resultatsRecherche.get(i));
            us.add(AnchorPane, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 3) {
                columnIndex = 0;
                rowIndex = rowIndex + 2;
            }

        }
        rechercher.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    rechercheChauffeur(event);
                } catch (IOException | SQLException ex) {
                    //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    @FXML
    private void rechercherch(KeyEvent event) {
    }

}
