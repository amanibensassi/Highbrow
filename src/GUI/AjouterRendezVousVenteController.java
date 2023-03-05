/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.Vente;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AjouterRendezVousVenteController implements Initializable {

    @FXML
    private TextField iduser;
    @FXML
    private TextField idveh;
    @FXML
    private DatePicker date_rendezvous;
    String lsheures[]={"9","10","11","12","14","15","16","17","18"};
    /**
     * Initializes the controller class.
     */
    VenteService vs = new VenteService();
    @FXML
    private ChoiceBox<String> heure;
    private int idv;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        heure.getItems().addAll(lsheures);
        // TODO
    }

    public void setidVehicule(int id) {
        idv = id;
    }

    ;
    @FXML
    private void ajouterRendezVous(ActionEvent event) throws IOException, ParseException {
        String dd = date_rendezvous.getValue() + " " + heure.getValue()+ ":00:00.0";
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dd);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        format.parse(dd);
        Vente v = new Vente();
        v.setId_utilisateur(1);
        v.setId_vehicule(idv);
        v.setDate_rendez_vous(format.parse(dd));

        try {
            vs.ajouter(v);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
            Parent root2 = loader1.load();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));

            iduser.getScene().setRoot(borderPane);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
