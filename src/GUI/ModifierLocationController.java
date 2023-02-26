/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ModifierLocationController implements Initializable {

    @FXML
    private DatePicker dateDebut;
    @FXML
    private DatePicker dateDin;
    @FXML
    private Button valider;
    @FXML
    private ComboBox<String> opchaffeur;
    LocationService ls = new LocationService();
    int idl ;
    /**
     * Initializes the controller class.
     */
    public void setLocation(Location l )
    {
        Date db = l.getDate_debut();
        Date df = l.getDate_fin();
        String dbe = db.toString();
        String dbf = df.toString();
        LocalDate ddebut = LocalDate.parse(dbe);
        dateDebut.setValue(ddebut);
        dateDin.setValue(LocalDate.parse(dbf));
        Boolean opch =  l.isOpt_chauffeur();
        System.out.println(opch);
        if ( opch ==true)
        {opchaffeur.setValue("oui");}
        else {opchaffeur.setValue("non");}
        idl=l.getIdlocation();
        
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         opchaffeur.getItems().add("nom");
        opchaffeur.getItems().add("oui");
       
    }    

    @FXML
    private void modifierLoactionValider(ActionEvent event) {
        java.sql.Date ddebut = java.sql.Date.valueOf(dateDebut.getValue());
        java.sql.Date dfin = java.sql.Date.valueOf(dateDin.getValue());
        String txt = opchaffeur.getValue();
        Boolean opch ;
        if(txt.equals("oui"))
        {opch =true; }
        else {opch =false;}
       Location l = new Location(idl,ddebut,dfin,opch, 2,1); 
        try {
            ls.modifier(l);
        } catch (SQLException ex) {
            Logger.getLogger(FormLouerVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
