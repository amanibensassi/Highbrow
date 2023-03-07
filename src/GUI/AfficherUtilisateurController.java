/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Mecanicien;
import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AfficherUtilisateurController implements Initializable {

    @FXML
    private GridPane grid;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();
    @FXML
    private TextField txtrecherche;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        int rowIndex = 1;
        int columnIndex = 0;
        try {
            List<Utilisateur> utilisateurs = us.recuperer();
            System.out.println(utilisateurs);
            for (int i = 0; i < utilisateurs.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCard.fxml"));

                HBox AnchorPane = loader.load();
                UserCardController controllerch = loader.getController();

                controllerch.setUser(utilisateurs.get(i));
                System.out.println("Liste" + utilisateurs.get(i));
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

    @FXML
    private void rechercheUser(KeyEvent event) throws IOException {

        String recherche = txtrecherche.getText();
        List<Utilisateur> ch = null;
        try {
            ch = us.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Utilisateur> resultatsRecherche = ch.stream()
                .filter(s -> s.getNom().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());

        grid.getChildren().clear();
        int rowIndex = 1;
        int columnIndex = 0;

        for (int i = 0; i < resultatsRecherche.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("UserCard.fxml"));
           // AnchorPane AnchorPane = loader.load();
            HBox AnchorPane = loader.load();
            UserCardController controllerch = loader.getController();

            controllerch.setUser(resultatsRecherche.get(i));

            grid.add(AnchorPane, columnIndex, rowIndex);
            columnIndex++;
            if (columnIndex == 1) {
                columnIndex = 0;
                rowIndex = rowIndex + 2;
            }

        }
        txtrecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    rechercheUser(event);
                } catch (IOException ex) {
                    //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });

    }
}
