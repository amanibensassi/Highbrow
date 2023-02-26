/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import entities.Location;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class CardLocationController implements Initializable {

    @FXML
    private Label nomVeh;
    @FXML
    private Label db;
    @FXML
    private Label df;
    @FXML
    private Button annuler;
    private int idL;
    /**
     * Initializes the controller class.
     */
    Location loca = new Location();
    @FXML
    private Button Modifier;
    @FXML
    private HBox hbox;

    public void setLocation(Location c) {
        loca = c;
        String dateDebut = c.getDate_debut() + "";
        db.setText(dateDebut);
        String dateFin = c.getDate_fin() + "";
        df.setText(dateFin);
        boolean opch = c.isOpt_chauffeur();
        idL = c.getIdlocation();
        loca.setOpt_chauffeur(opch);
        loca.setIdlocation(idL);
        System.out.println("tabbb location "+loca);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void annuler(ActionEvent event) {
    }

    @FXML
    private void ModifierLocation(ActionEvent event) throws IOException {
        BoxBlur blur = new BoxBlur(5, 5, 2);
        hbox.setEffect(blur);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierLocation.fxml"));

        Parent root = loader.load();
        ModifierLocationController md = loader.getController();
        md.setLocation(loca);
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root));
        modalStage.showAndWait();

    }

}
