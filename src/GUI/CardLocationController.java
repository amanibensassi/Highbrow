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
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.LocationService;

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
    LocationService lS = new LocationService();
    @FXML
    private HBox hbox;
    private Pane modalPane;
    private VBox modalBox;
    int idch;
    @FXML
    private ImageView img;
    @FXML
    private Button Modifier;

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
        System.out.println("tabbb location " + loca);
        idch = c.getId_chauffeur();
        loca.setId_chauffeur(idch);
loca.setDate_fin(c.getDate_fin());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void annuler(ActionEvent event) throws SQLException, IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Êtes-vous sûr de vouloir anuuler cet reservation ?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            lS.AnnulerLocation(idL);

            Alert confirm = new Alert(Alert.AlertType.INFORMATION);
            confirm.setTitle("Suppression réussie");
            confirm.setHeaderText(null);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
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
        
      

            annuler.getScene().setRoot(borderPane);
            confirm.setContentText("votre reservation a été supprimé avec succès.");
            confirm.showAndWait();
        }

    }

    @FXML
    private void ModifierLocation(ActionEvent event) throws IOException {
//        BoxBlur blur = new BoxBlur(5, 5, 2);
//        hbox.setEffect(blur);

        
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("CalendrierModifierLocation.fxml"));
        Parent root2 = loader2.load();
        CalendrierModifierLocationController md1 = loader2.getController();
        
       
        md1.setLocation(loca);
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root2));
        modalStage.showAndWait();

    }

    @FXML
    private void afficherChDetail(MouseEvent event) throws IOException, SQLException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("detailLocation_pfChau.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();

        if (loca.getId_chauffeur() != 0) {
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("detailChProfil.fxml"));
            Parent root2 = loader1.load();
            DetailChProfilController dchauffeur = loader1.getController();
            dchauffeur.setChauffeurDetail(loca.getId_chauffeur());

            // Utiliser un HBox pour placer root1 et root2 côte à côte avec un espacement de 20 pixels
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
        } else {
            borderPane.setLeft(root1);
        }

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        Scene scene = new Scene(borderPane);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.NONE);
        stage.initOwner(img.getScene().getWindow());
        stage.show();
    }

}
