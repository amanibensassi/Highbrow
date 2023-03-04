/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ModifierVehiculeController;
import GUI.AfficherVehiculeBySiegeController;
import entities.Vehicule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class VehiculeController implements Initializable {
    
    Vehicule pe = new Vehicule();
    VehiculeService ps = new VehiculeService();
    @FXML
    private Label marqueLabel;
    @FXML
    private Label prixjourLabel;
    @FXML
    private ImageView imageLabel;
    @FXML
    private AnchorPane vehiculesLignes;
    @FXML
    private Label etatLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Label carburantLabel;
    @FXML
    private Label prixdeventeLabel;
    @FXML
    private Label nombredeplaceLabel;
    @FXML
    private Label immatriculationLabel;
    @FXML
    private Label kilometrageLabel;
    @FXML
    private Button reserverbtn;
    @FXML
    private AnchorPane id_star;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    private int idVehicule;
    
    public void setVehicule(Vehicule c) {
        idVehicule = c.getIdvehicule();
        marqueLabel.setText(c.getMarque());
        dateLabel.setText(String.valueOf(c.getDate_circulation()));
        kilometrageLabel.setText(String.valueOf(c.getKilometrage()));
        immatriculationLabel.setText(c.getImmatriculation());
        nombredeplaceLabel.setText(c.getNbr_place().toString());
        prixdeventeLabel.setText(String.valueOf(c.getPrix_vente()));
        etatLabel.setText(c.getEtat().toString());
        prixjourLabel.setText(String.valueOf(c.getPrix_par_jour()));
        carburantLabel.setText(c.getCarburant().toString());
        
        String nomImage = "C://xampp//htdocs//img//" + c.getImage_vehicule();
        File file = new File(nomImage);
        Image img = new Image(file.toURI().toString());
        imageLabel.setImage(img);
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
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterAvisVehicule.fxml"));
//        AjouterAvisVehiculeController cc= loader1.getController();
//        id_star.getChildren().add(id_star);
//        cc.setavis(c.getIdvehicule(),1);
    }
    
    @FXML
    private void supprimerVehicule(ActionEvent event) throws IOException {
        
        try {
            int id = pe.getId_siege();
            ps.supprimer(pe);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root2 = loader1.load();
            AfficherVehiculeBySiegeController controller = loader1.getController();
            controller.dynamicinitialize(id);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            
            borderPane.setRight(hbox);
            
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            vehiculesLignes.getScene().setRoot(borderPane);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeBySiegeController controller = loader.getController();
//            //controller.dynamicinitialize(id);
//            vehiculesLignes.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void modifierVehicule(ActionEvent event) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierVehicule.fxml"));
            Parent root2 = loader1.load();
            ModifierVehiculeController controller = loader1.getController();
            
            controller.setData(pe);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            
            borderPane.setRight(hbox);
            
            borderPane.setLeft(root1);
            
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            vehiculesLignes.getScene().setRoot(borderPane);

//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierVehicule.fxml"));
//            Parent root = loader.load();
//            ModifierVehiculeController controller = loader.getController();
//
//            controller.setData(pe);
//
//            vehiculesLignes.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    private void reserverVehicule(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("formLouerVehicule.fxml"));
        Parent root2 = loader1.load();
        FormLouerVehiculeController fc = loader1.getController();
        fc.setIdVehicule(idVehicule);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);
        
        borderPane.setRight(hbox);
        
        borderPane.setLeft(root1);
        
        borderPane.setPadding(new Insets(10, 10, 30, 10));
        vehiculesLignes.getScene().setRoot(borderPane);

//            
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("formLouerVehicule.fxml"));
//            Parent root2 = loader1.load();
//            //controller.dynamicinitialize(id);
//            vehiculesLignes.getScene().setRoot(root2);
    }
    
}
