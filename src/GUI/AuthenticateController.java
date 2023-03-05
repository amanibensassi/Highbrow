/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.PropProfileController;
import GUI.ClientProfileController;
import entities.Utilisateur;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import services.UserConn;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Hamma
 *
 */
public class AuthenticateController implements Initializable {

    Stage Stage1;
    Stage Stage;

    @FXML
    private TextField mail;
    @FXML
    private PasswordField mot_de_passe;

    /**
     * Initializes the controller class.
     */
    UserService us = new UserService();
    Utilisateur u = new Utilisateur();

    Connection cnx;
    @FXML
    private Button login;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void authenticate(ActionEvent event) throws SQLException, IOException {

        if (us.authenticate(mail.getText(), mot_de_passe.getText()).getIdutilisateur() != 0) {
            System.out.println("LOOOOOOOOOOOOOOOOOOOOGIIIIIIIIIIIIN" + u);
            if ((us.role_selection(mail.getText())).equals("client")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                Parent root1 = loader.load();
                SideBarUserController sb = loader.getController();

                sb.setRole(us.role_selection(mail.getText()));
                BorderPane borderPane = new BorderPane();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
                Parent root2 = loader1.load();
//      
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
//            Parent root;
                System.out.println("auth" + us.authenticate(mail.getText(), mot_de_passe.getText()));
                u = us.authenticate(mail.getText(), mot_de_passe.getText());
                //UserConn.role = u.getRole();
                System.out.println("isEtat" + u.isEtat());
                if (!u.isEtat()) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attention");
                    alert.setHeaderText("Votre compte est desactivé");
                    alert.setContentText("votre compte est desactivé du des actions inderdites ");
                    alert.show();
                    System.out.println("Alerte votre compte est désactivé");
                } else {
                    //UserConn.role=u.getRole();
                    System.out.println("authenticaaaate" + UserConn.role);
                    ClientProfileController controller = loader1.getController();
                    controller.setData(u);
                    HBox hbox = new HBox(root1, new Pane(), root2);
                    hbox.setSpacing(20);

                    borderPane.setRight(hbox);

                    borderPane.setLeft(root1);

                    borderPane.setPadding(new Insets(10, 10, 30, 10));

                    mail.getScene().setRoot(borderPane);
//                root = loader.load();
//                         
//                mail.getScene().setRoot(root);
                }
            } else if ((us.role_selection(mail.getText())).equals("proprietaire_agence")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                Parent root1 = loader.load();
                SideBarUserController sb = loader.getController();

                sb.setRole(us.role_selection(mail.getText()));
                BorderPane borderPane = new BorderPane();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PropProfile.fxml"));
                Parent root2 = loader1.load();
                //UserConn.role = u.getRole();
                System.out.println("auth" + us.authenticate(mail.getText(), mot_de_passe.getText()));
                u = us.authenticate(mail.getText(), mot_de_passe.getText());
                System.out.println(u);
                System.out.println("isEtat" + u.isEtat());
                if (!u.isEtat()) {

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Attention");
                    alert.setHeaderText("Votre compte est desactivé");
                    alert.setContentText("votre compte est desactivé du des actions inderdites ");
                    alert.show();
                    System.out.println("Alerte votre compte est désactivé");
                } else {
                   // UserConn.role = u.getRole();

                    PropProfileController controller = loader1.getController();
                    controller.setData(u);
                    HBox hbox = new HBox(root1, new Pane(), root2);
                    hbox.setSpacing(20);

                    borderPane.setRight(hbox);

                    borderPane.setLeft(root1);

                    borderPane.setPadding(new Insets(10, 10, 30, 10));

                    mail.getScene().setRoot(borderPane);

                }
            } else if ((us.role_selection(mail.getText())).equals("administrateur")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                Parent root1 = loader.load();
                SideBarUserController sb = loader.getController();

                sb.setRole(us.role_selection(mail.getText()));
                BorderPane borderPane = new BorderPane();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AdminProfile.fxml"));
                Parent root2 = loader1.load();

                System.out.println("auth" + us.authenticate(mail.getText(), mot_de_passe.getText()));
                u = us.authenticate(mail.getText(), mot_de_passe.getText());
                System.out.println(u);
                AdminProfileController controller = loader1.getController();
                controller.setData(u);
                HBox hbox = new HBox(root1, new Pane(), root2);
                hbox.setSpacing(20);
                borderPane.setRight(hbox);
                borderPane.setLeft(root1);
                borderPane.setPadding(new Insets(10, 10, 30, 10));
                mail.getScene().setRoot(borderPane);
            }

        }
    }
//    public String cryptage(String password) throws NoSuchAlgorithmException {
//        MessageDigest m = MessageDigest.getInstance("MD5");
//        m.reset();
//        m.update(password.getBytes());
//        byte[] digest = m.digest();
//        BigInteger bigint = new BigInteger(1, digest);
//        String hpassword = bigint.toString(16);
//        while (hpassword.length() < 32) {
//            hpassword = "0" + hpassword;
//        }
//        return hpassword;
//    }
//    

    @FXML
    private void mdp_oublie(MouseEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ForgotMdp.fxml"));
            Stage1 = (Stage) ((Node) event.getSource()).getScene().getWindow();
            root.setOnMousePressed(pressEvent -> {
                root.setOnMouseDragged(dragEvent -> {
                    Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                    Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                });
            });
            Scene scene = new Scene(root);
            Stage1.setScene(scene);
            Stage1.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
