/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import entities.Location;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    private ComboBox<String> optionCH;
    @FXML
    private Label label1;
    LocationService ls = new LocationService();
private int idv;
    /**
     * Initializes the controller class.
     */
     private boolean isDateReserved(LocalDate date) throws SQLException {
   List<Location> lccc = ls.recupererAllByIdVehicule(1);
   for (int z=0 ; z<lccc.size();z++ )
   {
    java.util.Date debut1 =lccc.get(z).getDate_debut();
        java.util.Date fin1 =lccc.get(z).getDate_fin();
    LocalDate debut = LocalDate.parse(debut1+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate fin = LocalDate.parse(fin1+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (debut.isBefore(date) && fin.isAfter(date)) {
            return true; // La date est réservée
        }
   
   }
//    for (Location reservation : lccc) {
//        Date debut1 =reservation.getDate_debut();
//        Date fin1 =reservation.getDate_fin();
//        LocalDate debut = LocalDate.parse(debut1+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        LocalDate fin = LocalDate.parse(fin1+"", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//        if (debut.isBefore(date) && fin.isAfter(date)) {
//            return true; // La date est réservée
//        }
//    }
    return false; // La date n'est pas réservée
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       

    }

    public void setIdVehicule(int id )
    {System.out.println(id);
        idv=id ;
     datedebut.setDayCellFactory(dayCellFactory2);
        optionCH.getItems().add("non");
        optionCH.getItems().add("oui");
    System.out.println(idv);
    
    }
    
    
    
    
    public void setdatedebut(LocalDate d) {

        datedebut.setValue(d);
        datefin.setDayCellFactory(dayCellFactory);
        

    }

    @FXML
    private void reserverVehicule(ActionEvent event) throws IOException {

        Date ddebut = Date.valueOf(datedebut.getValue());
        System.out.println(ddebut);
        Date dfin = Date.valueOf(datefin.getValue());
        System.out.println(dfin);
        String txt = optionCH.getValue();
        System.out.println(txt);
        Boolean opch;
        if (txt.equals("oui")) {
            opch = true;
        } else {
            opch = false;
        }
        Location l = new Location(ddebut, dfin, opch, idv, 1);
        System.out.println(l);
        try {
            ls.ajouter(l);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeMesLocations.fxml"));
            Parent root2 = loader1.load();

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));

//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
            reserverVehicule.getScene().setRoot(borderPane);
            Twilio.init("AC84ff7691013163d92fc31146ac61b9dc", "edd707b9aca44d01c33f87876d7b0db8");
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+21653723896"),
                    new com.twilio.type.PhoneNumber("+12762888645"),
                    "Votre reservation au vehicule kethe du a kathe dans le siege kethe a ete effectuer avec succes").create();
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
                      try {
                        if (isDateReserved(item)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Couleur rose pour indiquer que la date est réservée
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CalendrierModifierLocationController.class.getName()).log(Level.SEVERE, null, ex);
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
                      try {
                        if (isDateReserved(item)) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;"); // Couleur rose pour indiquer que la date est réservée
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(CalendrierModifierLocationController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
        }
    };
}
