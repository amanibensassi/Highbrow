/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherRendezVousController;
import entities.Vente;
import GUI.AfficherUtilisateurController;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.UserConn;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class ModifierRendezVousController implements Initializable {

    @FXML
    private DatePicker date_rendezvous;
    VenteService vs = new VenteService();
    int idvente = 0;
    @FXML
    private Button mod;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private Button annuler_id;
    String lsheures[] = {"9", "10", "11", "12", "14", "15", "16", "17", "18"};

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setData(Vente v) {
        heure.getItems().setAll(lsheures);
        System.out.println("entered");
        String[] parts = String.valueOf(v.getDate_rendez_vous()).split(" ");
        String[] parts1 = parts[1].split(":");
        int heure = Integer.valueOf(parts1[0]);
        date_rendezvous.setValue(LocalDate.parse(parts[0], ISO_LOCAL_DATE));
        idvente = v.getIdvente();
        this.heure.setValue(String.valueOf(heure));

    }

    @FXML
    private void modifier(ActionEvent event) throws ParseException {
        try {
            Vente v = new Vente();

            String dd = date_rendezvous.getValue() + " " + heure.getValue() + ":00:00.0";
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
            format.parse(dd);
            v.setDate_rendez_vous(format.parse(dd));

            v.setIdvente(idvente);
//             v.setDate_rendez_vous(Date.valueOf(date_rendezvous.getValue()));
//            

            System.out.println("date modifié avec succes");
            System.out.println(v);
            vs.modifier(v);
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
            Parent root2 = loader1.load();
            AfficherRendezVousController rv = loader1.getController();
            rv.setdata();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            mod.getScene().setRoot(borderPane);

        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void annulerModif(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
        Parent root = loader.load();
        AfficherRendezVousController controller = loader.getController();
        //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
        mod.getScene().setRoot(root);
    }
}
