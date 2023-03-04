/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherSiegeController;
import entities.Siege;
import entities.Vehicule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.SiegeService;
import services.VehiculeService;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class AfficherVehiculeBySiegeController implements Initializable {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private GridPane grid;
    VehiculeService ps = new VehiculeService();
    SiegeService ps1 = new SiegeService();
    Siege ee = new Siege();
    @FXML
    private Button retourbtn;
    @FXML
    private Button ajouterbtn;
    int id;
    @FXML
    private ComboBox<Carburant> carburant;
    @FXML
    private ComboBox<NbrPlace> place;
    @FXML
    private ComboBox<Etat> etat;
    @FXML
    private Button filtrerbtn;
    
    @FXML
    private ToggleButton trieDecroissant;
    @FXML
    private RadioButton trieCroissant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NbrPlace[] tabplace = {NbrPlace.cinq, NbrPlace.deux, NbrPlace.neuf, NbrPlace.sept};
        place.getItems().setAll(tabplace);

        Carburant[] tabcarburant = {Carburant.diesel, Carburant.essence};
        carburant.getItems().setAll(tabcarburant);

        Etat[] tabetat = {Etat.a_louer, Etat.a_vendre, Etat.louer};
        etat.getItems().setAll(tabetat);
    }

    public void dynamicinitialize(int id) {
        try {
            this.id = id;
            //ee.getIdsiege();

            List<Vehicule> vehicules = ps.recupererVehiculeBySiege(id);

            int row = 0;
            int column = 0;
            for (int i = 0; i < vehicules.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
                AnchorPane pane = loader.load();
                VehiculeController controller = loader.getController();
                controller.setVehicule(vehicules.get(i));
                
//                FXMLLoader load= new FXMLLoader(getClass().getResource("AjouterAvisVehicule.fxml"));
//                AjouterAvisVehiculeController c = load.getController();
//                c.setavis(vehicules.get(i).getIdvehicule(), 1);
                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }

                grid.setAlignment(Pos.CENTER);
                grid.setHalignment(scrollPane, HPos.CENTER);
                grid.setValignment(scrollPane, VPos.CENTER);
                grid.getColumnConstraints().clear();
                grid.getRowConstraints().clear();
            }
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface vehicule : " + ex.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVehiculeBySiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    Vehicule pe = new Vehicule();

    public void setVehicule(Vehicule c) {

        String nomImage = "C://xampp//htdocs//img//" + c.getImage_vehicule();
        File file = new File(nomImage);
        Image img = new Image(file.toURI().toString());

        pe.setId_siege(c.getId_siege());
        pe.setIdvehicule(c.getIdvehicule());
        pe.setMarque(c.getMarque());
        pe.setDate_circulation(c.getDate_circulation());
        pe.setImage_vehicule(c.getImage_vehicule());
        pe.setEtat(c.getEtat());
        pe.setPrix_par_jour(c.getPrix_par_jour());
        pe.setCarburant(c.getCarburant());
        pe.setImmatriculation(c.getImmatriculation());
        pe.setPrix_vente(c.getPrix_vente());
        pe.setNbr_place(c.getNbr_place());
        pe.setKilometrage(c.getKilometrage());
    }

    @FXML
    private void retour(ActionEvent event) {
        try {

               FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root2 = loader1.load();
           // AjouterSiegeController controller = loader.getController();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            retourbtn.getScene().setRoot(borderPane);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//            AfficherSiegeController controller = loader.getController();
//            //controller.dynamicinitialize(idsiege);
//            retourbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void ajouterVehiculeBySiege(ActionEvent event) {

        try {
            
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ajouterVehicule.fxml"));
            Parent root2 = loader1.load();
            AjouterVehiculeController controller = loader1.getController();
            controller.setVehicule1(id);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            ajouterbtn.getScene().setRoot(borderPane);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterVehicule.fxml"));
//            Parent root = loader.load();
//            AjouterVehiculeController controller = loader.getController();
//            controller.setVehicule1(id);
//            //controller.dynamicinitialize(idsiege);
//            ajouterbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void filtrage(ActionEvent event) {

        Carburant c = carburant.getValue();
        NbrPlace p = place.getValue();
        Etat e = etat.getValue();
        List<Vehicule> vehicules = null;
        try {
            if (c != null && p != null && e != null) {
                vehicules = ps.recupererVehiculesByCarburantPlaceEtat(c, p, e);
            } else if (c != null && p != null && e == null) {
                vehicules = ps.recupererVehiculeByPlaceAndCarburant(p, c);
            } else if (c != null && p == null && e != null) {
                vehicules = ps.recupererVehiculeByEtatAndCarburant(e, c);
            } else if (c == null && p != null && e != null) {
                vehicules = ps.recupererVehiculeByEtatAndPlace(e, p);
            } else if (c != null && p == null && e == null) {
                vehicules = ps.recupererVehiculeByCarburant(c);
            } else if (c == null && p != null && e == null) {
                vehicules = ps.recupererVehiculeByPlace(p);
            } else if (c == null && p == null && e != null) {
                vehicules = ps.recupererVehiculeByEtat(e);
            } else {
                vehicules = ps.recupererVehiculeBySiege(id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherVehiculeBySiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
          if (trieDecroissant.isSelected()) {
        vehicules = ps.trierVehiculesParPrixDeLocationDecroissant(vehicules);
    } else if (trieCroissant.isSelected()) { // Tri par ordre croissant si la checkbox "trieCroissant" est cochée
        vehicules = ps.trierVehiculesParPrixDeLocationCroissant(vehicules);
    }
         

        grid.getChildren().clear(); // Effacer tous les enfants du GridPane
        int row = 0;
        int column = 0;
        for (int i = 0; i < vehicules.size(); i++) {
           
              

                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
                    AnchorPane pane = null;
                    try {
                        pane = loader.load();
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherVehiculeBySiegeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    VehiculeController controller = loader.getController();
                    controller.setVehicule(vehicules.get(i));
                    grid.add(pane, column, row);
                    column++;
                    if (column > 0) {
                        column = 0;
                        row++;
                    }

                    grid.setAlignment(Pos.CENTER);
                    grid.setHalignment(scrollPane, HPos.CENTER);
                    grid.setValignment(scrollPane, VPos.CENTER);
                    grid.getColumnConstraints().clear();
                    grid.getRowConstraints().clear();
                }
            
        }


    }

