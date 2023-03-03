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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
     int idvente =0;
    @FXML
    private Button mod;
    @FXML
    private TextField heure;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifier(ActionEvent event) throws ParseException {
        try {
                   Vente v = new Vente();

            String dd=date_rendezvous.getValue()+" "+heure.getText()+":00:00.0";
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
            Parent root = loader.load();
            AfficherRendezVousController controller = loader.getController();
            //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
            mod.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }
    public void setData(Vente v) {
       
        date_rendezvous.setValue(LocalDate.parse(String.valueOf(v.getDate_rendez_vous())));
        idvente=v.getIdvente();
        
        
        
    }
    }
    

