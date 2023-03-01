/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Siege;
import entities.Vehicule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.SiegeService;
import services.VehiculeService;

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
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    public void dynamicinitialize(int id){   
        try {
            this.id=id;
            //ee.getIdsiege();
            
            List<Vehicule> vehicules = ps.recupererVehiculeBySiege(id);
           
            int row = 0;
            int column = 0;
            for (int i = 0; i < vehicules.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
                AnchorPane pane = loader.load();
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

            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root = loader.load();
            AfficherSiegeController controller = loader.getController();
            //controller.dynamicinitialize(idsiege);
            retourbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }

    @FXML
    private void ajouterVehiculeBySiege(ActionEvent event) {
        
                try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterVehicule.fxml"));
            Parent root = loader.load();
            AjouterVehiculeController controller = loader.getController();
            controller.setVehicule1(id);
            //controller.dynamicinitialize(idsiege);
            ajouterbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    }    
    

