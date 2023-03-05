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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.UserConn;
import typeenumeration.Role;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class SideBarUserController implements Initializable {

    @FXML
    private Button profil;
    @FXML
    private Button HLocation;
    @FXML
    private Button reclamer;
    @FXML
    private Button deconx;
    private Button reclamer1;
    private Button statistique;
    @FXML
    private Label prenom;
    @FXML
    private Label nom;
    @FXML
    private Label numtel;
    @FXML
    private Label role;
    @FXML
    private Button siegesDisponible;
    @FXML
    private Button forum;
    private String roleUser;
    @FXML
    private AnchorPane consulter;
    @FXML
    private Button id_entretient;

    /**
     * Initializes the controller class.
     */
    public void setRole(String role) throws IOException {
//        System.out.println("khraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaak  "+UserConn.role);
//        roleUser = String.valueOf(UserConn.role);
//        System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee"+roleUser);
//        if(roleUser.equals("proprietaire_agence"))
//        {
//            System.out.println("west if side bar"+role);
//        siegesDisponible.setText("mes sieges");
//        HLocation.setText("Gestion location");
//        consulter.setVisible(true);
//        
//        }
//        if(roleUser.equals("administrateur")){
//            siegesDisponible.setText("Liste des utilisateurs");
//            HLocation.setText("Liste des mecaniciens");
//        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("role intiliazeeeee +" + UserConn.role);
        String rr = UserConn.role.toString();
        if (rr.equals("proprietaire_agence")) {
            System.out.println("west if side bar" + rr);
            siegesDisponible.setText("mes sieges");
            HLocation.setText("Gestion location");
            consulter.setVisible(true);

        }
        if (rr.equals("administrateur")) {
            siegesDisponible.setText("Liste des utilisateurs");
            HLocation.setText("Liste des mecaniciens");
        }
    }

    @FXML
    private void GoToProfil(ActionEvent event) throws IOException {

        if (roleUser.equals("administrateur")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(roleUser);
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AdminProfile.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            profil.getScene().setRoot(borderPane);
        }

        if (roleUser.equals("client")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(roleUser);
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            profil.getScene().setRoot(borderPane);
        }

        if (roleUser.equals("proprietaire_agence")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(roleUser);
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PropProfile.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            profil.getScene().setRoot(borderPane);
        }

    }

    @FXML
    private void GoToReclamer(ActionEvent event) throws IOException, ParseException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(roleUser);
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherReclamationUtilisateur.fxml"));
        Parent root2 = loader1.load();
        AfficherReclamationUtilisateurController md = loader1.getController();
        md.getData();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));

        reclamer.getScene().setRoot(borderPane);
    }

    @FXML
    private void GoToHistoriqueLocation(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(roleUser);
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeMesLocations.fxml"));
        Parent root2 = loader1.load();

        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();

        HLocation.getScene().setRoot(borderPane);
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("authenticate.fxml"));
        Parent root2 = loader1.load();

        deconx.getScene().setRoot(root2);
    }

    @FXML
    private void GoToSiegesDisponibles(ActionEvent event) throws IOException {
        if (roleUser.equals("proprietaire_agence")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(roleUser);
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root2 = loader1.load();

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            siegesDisponible.getScene().setRoot(borderPane);
        }
    }

    @FXML
    private void GoToForum(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(roleUser);
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_forum.fxml"));
        Parent root2 = loader1.load();

        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        deconx.getScene().setRoot(borderPane);
    }

    @FXML
    private void entretient(ActionEvent event) {
    }

}
