/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAnas;

import entities.Mecanicien;
import entities.Vehicule;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.input.MouseEvent;
import services.MecanicienService;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AjouterEntretienController implements Initializable {

    @FXML
    //private ChoiceBox<List<Mecanicien>> id_mecanicien;
    private ChoiceBox<String> id_mecanicien;
    @FXML
    private ChoiceBox<List<Vehicule>> id_vehicule;
    @FXML
    private DatePicker date_entretien;
    MecanicienService ms = new MecanicienService();
    int id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.listeMecaniciens();
        } catch (SQLException ex) {
            Logger.getLogger(AjouterEntretienController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void ajouterEntretien(ActionEvent event) {
        System.out.println("ID:"+id);
    }
    
    private void listeMecaniciens() throws SQLException{
            List<Mecanicien> mecaniciens = ms.recuperer();
            //ObservableList<Mecanicien> olp = FXCollections.observableArrayList(mecaniciens);
            //mecaniciens.stream().forEach(m -> id_mecanicien.getItems().add(m.getNom_mecanicien()));
            for (Mecanicien mecanicien : mecaniciens) {
            System.out.println(mecanicien.getNom_mecanicien());
            id_mecanicien.getItems().add(mecanicien.getNom_mecanicien());
            }
            id_mecanicien.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            int selectedIndex = id_mecanicien.getSelectionModel().getSelectedIndex();
            System.out.println("Index sélectionné : " + selectedIndex);
            System.out.println(mecaniciens.get(selectedIndex));
            id=mecaniciens.get(selectedIndex).getIdmecanicien();
        });
    }
    
    
}
