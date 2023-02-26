/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.util.Callback;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class FormLouerVehiculeController implements Initializable {

    @FXML
    private DatePicker datefin;
    @FXML
    private Button reserverVehicule;
    @FXML
    private DatePicker datedebut = new DatePicker();
    @FXML
    private Label label;
    @FXML
    private ComboBox<String>optionCH ;
    @FXML
    private Label label1;
    LocationService ls = new LocationService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        datedebut.setDayCellFactory(dayCellFactory2);
        optionCH.getItems().add("nom");
        optionCH.getItems().add("oui");
        
    }

    public void setdatedebut(LocalDate d) {

        datedebut.setValue(d);
        datefin.setDayCellFactory(dayCellFactory);

    }

    @FXML
    private void reserverVehicule(ActionEvent event) {
        
        Date ddebut = Date.valueOf(datedebut.getValue());
        Date dfin = Date.valueOf(datefin.getValue());
        String txt = optionCH.getValue();
        Boolean opch ;
        if(txt.equals("oui"))
        {opch =true; }
        else {opch =false;}
       Location l = new Location(ddebut,dfin,opch, 2,1); 
        try {
            ls.ajouter(l);
        } catch (SQLException ex) {
            Logger.getLogger(FormLouerVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    @FXML
    private void setdatedebut(ActionEvent event) {
        datefin.setDayCellFactory(dayCellFactory);
        LocalDate myDate = datedebut.getValue();
        System.out.println(myDate);
    }

    private Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
        @Override
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);

                    if (item.isBefore(datedebut.getValue().plusDays(1))) {
                        setDisable(true);
                        setStyle("-fx-background-color: #EEEEEE;");
                    }
                }
            };
        }
    };

    private Callback<DatePicker, DateCell> dayCellFactory2 = new Callback<DatePicker, DateCell>() {
        @Override
        public DateCell call(final DatePicker datePicker) {
            return new DateCell() {
                @Override
                public void updateItem(LocalDate item, boolean empty) {
                    super.updateItem(item, empty);
                    System.out.println("today" + LocalDate.now());
                    if (item.isBefore(LocalDate.now())) {
                        setDisable(true);
                        setStyle("-fx-background-color: #EEEEEE;");
                    }

                }
            };
        }
    };
}
