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
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.EntretienService;
import services.MecanicienService;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class EntretienController implements Initializable {

    @FXML
    private Label lbldate;
    @FXML
    private Label lblmec;
    @FXML
    private Label lblvehicule;
    Entretien en = new Entretien();
    Mecanicien m = new Mecanicien();
    MecanicienService ms = new MecanicienService();
    EntretienService es = new EntretienService();
    @FXML
    private AnchorPane detailEntre;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Button btnmodifier;
    @FXML
    private CheckBox cbetat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }    
    
    public void setEntretien(Entretien e) throws SQLException {
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
            String[] parts = String.valueOf(e.getDate_entretien()).split(" ");
        String[] parts1 = parts[1].split(":");
        int heure = Integer.valueOf(parts1[0]);
        lbldate.setText(String.valueOf(parts[0]+" à "+parts1[0]+"H"));
        //lblmec.setText(String.valueOf(e.getId_mecanicien())); 
        lblvehicule.setText(String.valueOf(e.getId_vehicule()));
        cbetat.setSelected(e.isEtat_entretien());
        en.setDate_entretien(e.getDate_entretien());
        en.setId_mecanicien(e.getId_mecanicien());
        en.setId_vehicule(e.getId_vehicule());
        en.setEtat_entretien(e.isEtat_entretien());
        en.setIdentretien(e.getIdentretien());
    }

    @FXML
    private void detail(MouseEvent event) throws SQLException {
        
        try {
                                        //System.out.println(me);
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetailEntretien.fxml"));
                                        Parent root = loader.load();
                                        AfficherDetailEntretienController controller = loader.getController();
                                        controller.setData(en);
                                        lblmec.getScene().setRoot(root);     
                                    } catch (IOException ex) {
                                        System.out.println("error1" + ex.getMessage());
                                    }
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
        
        try{
            System.out.println(en);
           es.supprimer(en);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEntretien.fxml"));
           Parent root = loader.load();
           btnsupprimer.getScene().setRoot(root);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }

    @FXML
    private void modifier(ActionEvent event) throws IOException, ParseException, SQLException {
        
       /* try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEntretien.fxml"));
                                        Parent root = loader.load();
                                        ModifierEntretienController controller = loader.getController();
                                        controller.setData(en);
                                        lblmec.getScene().setRoot(root);
                                        
                                    } catch (IOException ex) {
                                        System.out.println("error" + ex.getMessage());
                                    }*/
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierEntretien.fxml"));
        Parent root = loader.load();
        ModifierEntretienController controller = loader.getController();
        System.out.println("EN"+en);
        m.setIdmecanicien(en.getId_mecanicien());
        controller.setDate(en);
        controller.setData(m);
        lblmec.getScene().setRoot(root);
        
    }

    @FXML
    private void changeretat(ActionEvent event) throws SQLException {
        en.setEtat_entretien(cbetat.isSelected());
        es.modifierEtat(en);
        
    }
    
}
