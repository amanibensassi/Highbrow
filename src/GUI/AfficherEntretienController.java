/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Entretien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.EntretienService;
import services.UserConn;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherEntretienController implements Initializable {

    @FXML
    private GridPane grid;
    EntretienService es = new EntretienService();
    @FXML
    private ImageView imgtrie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getData();
        // TODO
    }

    private void getData() {
        try {
            List<Entretien> entretiens = es.recuperelesEntretientByUserConnecte(UserConn.idutilisateur);
            int row = 1;
            int column = 0;
            for (int i = 0; i < entretiens.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Entretien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                EntretienController controller = loader.getController();
                controller.setEntretien(entretiens.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }



    private void listerMecaniciens(ActionEvent event) throws IOException {

       FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root2 = loader1.load();
        AfficherMecaniciensProfController fc = loader1.getController();
        fc.setIdVehicule(5);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        grid.getScene().setRoot(borderPane);
    }

    @FXML
    private void trieCroissant(MouseEvent event) throws IOException, SQLException {
        grid.getChildren().clear();
        try {
            List<Entretien> entretiens = es.trieCroissant();
            int row = 1;
            int column = 0;
            for (int i = 0; i < entretiens.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Entretien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                EntretienController controller = loader.getController();
                controller.setEntretien(entretiens.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }

        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
