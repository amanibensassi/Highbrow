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
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

    /**
     * Initializes the controller class.
     */
            VenteService vs = new VenteService() ;
    @FXML
    private TextField heure;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterRendezVous(ActionEvent event) throws IOException, ParseException {
        String dd=date_rendezvous.getValue()+" "+heure.getText()+":00:00.0";
        //Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dd);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");
        format.parse(dd);
        Vente v = new Vente ();
        v.setId_utilisateur(Integer.parseInt(iduser.getText()));
        v.setId_vehicule(Integer.parseInt(idveh.getText()));
        v.setDate_rendez_vous(format.parse(dd));
      
        try {
            vs.ajouter(v);
            reset();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
            Parent root = loader.load();
            iduser.getScene().setRoot(root);
            System.out.println("rendez ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
