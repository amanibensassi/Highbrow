/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class RespoLocationCardController implements Initializable {

    @FXML
    private Label db;
    @FXML
    private Label df;
    @FXML
    private Button choisirch;
    int idL;
    Location loca = new Location();
 Vehicule v = new Vehicule();
    VehiculeService vs = new VehiculeService();
    @FXML
    private Label idvehi;
    /**
     * Initializes the controller class.
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setLocation(Location c) throws SQLException {
           v = vs.recupererVehiculeByid(c.getId_vehicule());
           idvehi.setText(v.getImmatriculation());
        loca = c;
        String dateDebut = c.getDate_debut() + "";
        db.setText(dateDebut);
        String dateFin = c.getDate_fin() + "";
        df.setText(dateFin);
        boolean opch = c.isOpt_chauffeur();
        idL = c.getIdlocation();
    }

    @FXML
    private void choisirchauffeur(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeChauffeurDisponibles.fxml"));
        Parent root = loader.load();
        ListeChauffeurDisponiblesController md = loader.getController();
        md.test(loca,idL);
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root));
        modalStage.showAndWait();
       
    }

}
