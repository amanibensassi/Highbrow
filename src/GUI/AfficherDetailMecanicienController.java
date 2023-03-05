/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Mecanicien;
import GUI.CalendrierEController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherDetailMecanicienController implements Initializable {

    int idmec = 0;
    String image;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblspecialite;
    @FXML
    private Label lblregion;
    @FXML
    private Label lblcontact;
    @FXML
    private Label lbladresse;
    @FXML
    private ImageView img;
    Mecanicien me = new Mecanicien();
    @FXML
    private Button calendrier;
    int id = 0;
    String role = "User";
    private int id_vehicule;

    //String role="Administrateur";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // TODO
    }

    public void setIdVehicule(int id_v) {
        this.id_vehicule = id_v;
        if (role != "User") {
            calendrier.setVisible(false);
        }
        if (me.getImage() != null) {
            this.setData(me);
        }
        //        else {
//            System.out.println("null");
//        }
    }

    public void setData(Mecanicien m) {
        id = m.getIdmecanicien();
        lblnom.setText(m.getNom_mecanicien());
        lblprenom.setText(m.getPrenom_mecanicien());
        lblcontact.setText(String.valueOf(m.getContact()));
        lbladresse.setText(m.getAdresse());
        lblregion.setText(String.valueOf(m.getRegion()));
        lblspecialite.setText(String.valueOf(m.getSpecialite()));
        File file = new File(m.getImage());
        Image imagee = new Image(file.toURI().toString());
        img.setImage(imagee);
        me.setIdmecanicien(id);
        me.setNom_mecanicien(m.getNom_mecanicien());

    }

    @FXML
    private void afficherListe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root2 = loader1.load();
        AfficherMecaniciensProfController fc = loader1.getController();
        fc.setIdVehicule(id_vehicule);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        lbladresse.getScene().setRoot(borderPane);
    }

    @FXML
    private void afficherCalendrier(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("CalendrierE.fxml"));
        Parent root2 = loader1.load();
        CalendrierEController controller= loader1.getController();
        controller.setData(me, id_vehicule);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        lbladresse.getScene().setRoot(borderPane);

    }

}
