/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.UserConn;
import services.UserService;
import typeenumeration.Role;
import static typeenumeration.Role.client;
import static typeenumeration.Role.proprietaire_agence;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class UserCardController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mail;
    @FXML
    private Label num;
    @FXML
    private Label date_naissance;
    @FXML
    private Button delete;
    Utilisateur u = new Utilisateur();
    UserService us = new UserService();
    Role tabrole[] = {client, proprietaire_agence};
    int iduser;
    @FXML
    private ImageView image;
    @FXML
    private Label role;
    private Button approuved;
    @FXML
    private Button desactiver;
    @FXML
    private Label etat;
    @FXML
    private Button detail;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setUser(Utilisateur ut) {
        File file = new File(ut.getImage());
        Image imagee = new Image(file.toURI().toString());

        nom.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        num.setText(String.valueOf(ut.getNum_tel()));
        //image.set(ut.getImage());
        //photo_permis_avant.setText(ut.getPhotopermis_avant());
        //photo_permis_arriere.setText(ut.getPhotopermis_arriere());
        date_naissance.setText(ut.getDate_naissance().toString());
        mail.setText(ut.getMail());
        role.setText(String.valueOf(ut.getRole()));
        u.setImage(ut.getImage());
        image.setImage(imagee);

        if (ut.getetat() == true) {
            etat.setText("approuvé");
        } else {
            etat.setText("désactivé");
        }
        System.out.println("Imageee" + imagee.toString());
        iduser = ut.getIdutilisateur();
        u = ut;

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException, IOException {
        try {
            us.supprimer(u);
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            delete.getScene().setRoot(borderPane);
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
//            Parent root = loader.load();
//            delete.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @FXML
    private void Userdetails(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("DetailAdmin.fxml"));
            Parent root2 = loader1.load();
             DetailAdminController controller = loader1.getController();
            controller.setData(u);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nom.getScene().setRoot(borderPane);
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("DetailAdmin.fxml"));
//            Parent root = loader.load();
//            DetailAdminController controller = loader.getController();
//            controller.setData(u);
//            nom.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error1" + ex.getMessage());
        }

    }

    private void approuvez(ActionEvent event) throws IOException {
        try {
            us.approuver(u);
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            approuved.getScene().setRoot(borderPane);
            
            
            
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
//            Parent root = loader.load();
//            approuved.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void desactiver(ActionEvent event) throws IOException {
        try {
            us.desactiver(u);
               FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            desactiver.getScene().setRoot(borderPane);
            
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
//            Parent root = loader.load();
//            desactiver.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
