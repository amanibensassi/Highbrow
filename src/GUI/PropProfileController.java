/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.UserConn;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class PropProfileController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    Utilisateur u = new Utilisateur();
    int idclient = 0;
    UserService us = new UserService();
    @FXML
    private Label num1;
    @FXML
    private Label nom_id2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Utilisateur ut) {
        u = ut;
        nom.setText(ut.getNom());
        nom_id2.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        num.setText(String.valueOf(ut.getNum_tel()));
        num1.setText(ut.getMail());
        // img.setImage(ut.getImage());
        // u.setPrenom(ut.getPrenom());
        //u.setNum_tel(ut.getNum_tel());
        idclient = ut.getIdutilisateur();
        //nom.setText(u.getNom());
        System.out.println("id" + idclient);
        System.out.println("ut.getnom" + ut.getNom());
        System.out.println("cientprofile" + ut);
    }

    @FXML
    private void modifier_prop(ActionEvent event) throws IOException, SQLException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(UserConn.role.toString());
        BorderPane borderPane = new BorderPane();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierUser.fxml"));
        Parent root2 = loader1.load();
        u = us.recupererById(UserConn.idutilisateur);
        ModifierUserController controller = loader1.getController();
        controller.setData(u);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();

        nom.getScene().setRoot(borderPane);

    }

}
