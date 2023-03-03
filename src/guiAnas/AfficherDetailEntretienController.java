/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAnas;

import entities.Entretien;
import entities.Mecanicien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import services.MecanicienService;
/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherDetailEntretienController implements Initializable {

    @FXML
    private Label lbldate;
    @FXML
    private Label lblmec;
    @FXML
    private Label lblvehicule;
    @FXML
    private Label lblEtat;
    int id=0;
    Entretien en = new Entretien();
    MecanicienService ms = new MecanicienService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setData(Entretien e) throws SQLException {
        
            List<Mecanicien> mecaniciens = ms.recuperer();
            //ObservableList<Mecanicien> olp = FXCollections.observableArrayList(mecaniciens);
            //mecaniciens.stream().forEach(m -> id_mecanicien.getItems().add(m.getNom_mecanicien()));
            for (Mecanicien mecanicien : mecaniciens) {
                if (e.getId_mecanicien()==mecanicien.getIdmecanicien()){
                    lblmec.setText(mecanicien.getNom_mecanicien()+" "+mecanicien.getPrenom_mecanicien());
                }
            }
            
//              Pour afficher la marque et l'immatriculation au lieu de l'id du véhicule
//            List<Vehicule> vehicules = vs.recuperer();
//            for (Vehicule vehicule : vehicules) {
//                if (e.getId_vehicule()==vehicule.getIdvehicule()){
//                    lblvehicule.setText(vehicule.getMarque()+" "+vehicule.getImmatriculation());
//                }
//            }
        
          id=e.getIdentretien();
          lbldate.setText(String.valueOf(e.getDate_entretien()));
          lblvehicule.setText(String.valueOf(e.getId_vehicule()));
          if (e.isEtat_entretien()){
              lblEtat.setText("L'entretient a déja été éfféctué");
          }
          else {
              lblEtat.setText("L'entretient n'a pas encoré été éfféctué");
          }
          
          
    }

    @FXML
    private void lister(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEntretien.fxml"));
        Parent root = loader.load();
        AfficherEntretienController controller = loader.getController();
        lblEtat.getScene().setRoot(root);
    }
    
}
