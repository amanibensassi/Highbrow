/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import entities.Location;
import entities.Utilisateur;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.RowConstraints;
import services.LocationService;
import java.util.Date;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import services.UserConn;
import services.UserService;
import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class CalendrierController implements Initializable {

    private LocalDate currentDate;
    @FXML
    private Label localDate;
    @FXML
    private Button next;
    @FXML
    private Button prev;
    @FXML
    private GridPane daysGrid;

    LocationService ls = new LocationService();
    @FXML
    private Label label;
    @FXML
    private DatePicker datedebut;
    @FXML
    private DatePicker datefin;
    @FXML
    private Label label1;
    @FXML
    private ComboBox<String> optionCH;
    @FXML
    private Button reserverVehicule;
    private int idv;
    Utilisateur us = new Utilisateur();
    UserService userservice = new UserService();

    Vehicule veh = new Vehicule();
    VehiculeService vehservice = new VehiculeService();

    /**
     * Initializes the controller class.
     */
    private boolean isDateReserved(LocalDate date) throws SQLException {
        List<Location> lccc = ls.recupererAllByIdVehicule(1);
        for (int z = 0; z < lccc.size(); z++) {
            Date debut1 = lccc.get(z).getDate_debut();
            Date fin1 = lccc.get(z).getDate_fin();
            LocalDate debut = LocalDate.parse(debut1 + "", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            LocalDate fin = LocalDate.parse(fin1 + "", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if (debut.isBefore(date) && fin.isAfter(date)) {
                return true; // La date est réservée
            }

        }
        return false; // La date n'est pas réservée
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

    public void setIdVehicule(int id) {
        System.out.println(id);
        idv = id;
        datedebut.setDayCellFactory(dayCellFactory2);

        System.out.println(idv);

    }

    private void updateDaysGrid() {

        try {
            List<AnchorPane> buttons = new ArrayList<>();
            //afficher le mois
            YearMonth yearMonth = YearMonth.from(currentDate);

            //afficher combien de jour dans le mois
            int daysInMonth = yearMonth.getMonth().length(yearMonth.isLeapYear());

            for (int day = 1; day <= daysInMonth; day++) {
                String ddd = String.valueOf(day);
                DayOfWeek dayOfWeek = yearMonth.atDay(day).getDayOfWeek();

                Label label = new Label(String.valueOf(day));
                label.setStyle("-fx-font-size: 12pt; -fx-text-fill: #333333; -fx-font-weight: bold;");
                /**
                 * *************************
                 */
                String moissss = yearMonth.toString();
                String[] parts5 = moissss.split("-");
                String month11 = parts5[1];
                String yearsss = parts5[0];
                int month11fet = Integer.parseInt(month11);
                int ammfet = Integer.parseInt(yearsss);
                AnchorPane button = new AnchorPane();
                //button.setDisable(true);
                LocalDate today = LocalDate.now();

                String curentdatee = today.toString();
                String[] splitCurrentDate = curentdatee.split("-");
                String chhar = splitCurrentDate[1];
                String nhar = splitCurrentDate[2];
                String aam = splitCurrentDate[0];
                int nhar2 = Integer.parseInt(nhar);
                int chhar2 = Integer.parseInt(chhar);
                int nharat = Integer.parseInt(label.getText());
                int ammtw = Integer.parseInt(aam);

                if (chhar2 > month11fet || ammfet < ammtw) {
                    button.setDisable(true);
                }
                if (month11.equals(chhar) && aam.equals(yearsss) && nharat < nhar2) {
                    System.out.println("sdfvfd");
                    button.setDisable(true);
                }
//            
                button.setOnMouseClicked(e -> {

                    LocalDate datezzz1;
                    if (label.getText().length() < 2) {
                        String jj = "0" + label.getText();

                        String date1 = month11 + "/" + jj + "/" + yearsss;
                        // String date1 = yearsss + "/" + month11 + "/" + jj;
                        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        datezzz1 = LocalDate.parse(date1, formatter1);
                        //datePicker.setValue(datezzz1);

                    } else {

                        String date = month11 + "/" + ddd + "/" + yearsss;
                        //String date = yearsss + "/" + month11 + "/" + ddd;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        datezzz1 = LocalDate.parse(date, formatter);
                        //  datePicker.setValue(datezzz);
                    }

                    datedebut.setValue(datezzz1);
                    datefin.setDayCellFactory(dayCellFactory);

                });

                button.setStyle("-fx-background-color: #F5F5F5; -fx-padding: 10px;");
                button.getChildren().add(label);
                buttons.add(button);
                // button.setPrefSize(200, 200);

                /**
                 * ****************************************************************************************
                 */
                String mois = yearMonth.toString();
                String[] parts1 = mois.split("-");
                int month1 = Integer.parseInt(parts1[1]);

                String moisString = month1 + "";
                List<Location> locationFait = ls.recupererAllByIdVehicule(idv);
                System.out.println("dcfvgbvfd");
                System.out.println("essai nouveau calendrierrrr" + locationFait);
                for (int i = 0; i < locationFait.size(); i++) {

                    Date dateDebut = locationFait.get(i).getDate_debut();
                    Date dateFin = locationFait.get(i).getDate_fin();
                    String s = dateDebut.toString();
                    String s2 = dateFin.toString();

                    String[] parts = s.split("-");
                    String[] parts3 = s2.split("-");
                    int year = Integer.parseInt(parts[0]);
                    int month = Integer.parseInt(parts[1]);
                    int dayy = Integer.parseInt(parts[2]);
                    String dayEnString = dayy + "";
                    String moisL = month + "";

                    int year3 = Integer.parseInt(parts3[0]);
                    int month3 = Integer.parseInt(parts3[1]);
                    int dayy3 = Integer.parseInt(parts3[2]);
                    String dayEnString3 = dayy3 + "";
                    String moisL2 = month3 + "";

                    int rs = dayy3 - dayy;
//                     List lbs = new ArrayList<>();
//                    for (int kk = dayy; kk <= dayy3-1; kk++) {
//                        Label label2 = new Label(String.valueOf(kk));
//                        AnchorPane an = new AnchorPane();
//                        an.getChildren().add(label2);
//                    
//                lbs.add(label2.getText());
//                        
//               }
//******** date de but w fin andhoum nafs chhhar
                    rs = dayy3 - dayy;
                    if (moisString.equals(moisL) && moisString.equals(moisL2)) {
                        for (int hama = dayy; hama <= dayy3; hama++) {
                            String khra = hama + "";

                            if (khra.equals(label.getText()) && moisString.equals(moisL)) {
                                label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                                button.setDisable(true);
                                //   label.setTextFill(Color.RED);
                            }

                        }
                    }
                    //****** if datefin mahich fard chhar ma date debut + chahaar date debut nafs ili nparcouri fih              
                    if (!moisL.equals(moisL2)) {
                        for (int a = dayy; a <= daysInMonth; a++) {

                            String khra = a + "";

                            if (khra.equals(label.getText()) && moisL.equals(moisString)) {
                                label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                                button.setDisable(true);
                            }

                        }
                        //******** hethi nabdew nihsmou ml 1 hat il date fin si char d debu diff date fin w chahr date fin equal il chhar courant
                        for (int x = 1; x <= dayy3; x++) {

                            String khra = x + "";

                            if (khra.equals(label.getText()) && moisL2.equals(moisString)) {
                                button.setDisable(true);
                                label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                            }

                        }
                    }
                    //System.out.println("eeee"+daysInMonth);
                    int var = month3 - month;

                    if (var >= 2) {

                        for (int w = month + 1; w < month3; w++) {
                            //System.out.println("ff");
                            for (int c = 1; c <= daysInMonth; c++) {
                                String ff = c + "";
                                if (ff.equals(label.getText()) && month1 > month && month1 < month3) {
                                    button.setDisable(true);
                                    label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                                }

                            }
                        }
                    }

//
//                    for (int hama = dayy; hama <= dayy3; hama++) {
//                        String khra = hama + "";
//                        System.out.println(hama);
//                        if (khra.equals(label.getText()) && moisString.equals(moisL)) {
//                            label.setTextFill(Color.RED);
//                        }
//                      
//
//                    }
                    if (dayEnString.equals(label.getText()) && moisString.equals(moisL)) {
                        System.out.println("sdfghjikohgfdsfghjuiopikjhgfdsfghyujopkjhgfdsftghyujio");
                        button.setDisable(true);
                        label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                    }
                    if (dayEnString3.equals(label.getText()) && moisString.equals(moisL2)) {
                        button.setDisable(true);
                        label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                    }

// for (int k = dayy; k < dayy3; k++) {
//                            System.out.println("dd");
//
//                            label.setStyle("-fx-text-fill: green;");
//
//                        }
                }

            }

            //Ajouter les boutons à la grille des jours
            daysGrid.getChildren().clear();
            int row = 0;
            int column = 0;
            for (AnchorPane button : buttons) {
                daysGrid.add(button, column, row);
                column++;
                if (column > 6) {
                    column = 0;
                    row++;
                }
            }

            // zina afffichage
            daysGrid.getColumnConstraints().clear();
            daysGrid.getRowConstraints().clear();
            for (int i = 0; i < 7; i++) {
                ColumnConstraints columnConstraints = new ColumnConstraints();
                columnConstraints.setPrefWidth(85.0);
                daysGrid.getColumnConstraints().add(columnConstraints);
            }
            for (int i = 0; i <= row; i++) {
                RowConstraints rowConstraints = new RowConstraints();
                rowConstraints.setPrefHeight(80.0);
                daysGrid.getRowConstraints().add(rowConstraints);
            }

            daysGrid.setPrefSize(85.0 * 7, 80.0 * (row + 1));

        } catch (SQLException ex) {
            Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void testCalendrier(int id) {
        idv = id;
        currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM yyyy");
        localDate.setText(currentDate.format(formatter));
        optionCH.getItems().add("non");
        optionCH.getItems().add("oui");
        // Afficher les jours du mois en cours
        updateDaysGrid();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // Afficher la date d'aujourd'hui

    }

    @FXML
    private void next(ActionEvent event
    ) {
        currentDate = currentDate.plusMonths(1);

        // Afficher la nouvelle date et les jours du mois en cours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM yyyy");
        localDate.setText(currentDate.format(formatter));
        updateDaysGrid();

    }

    @FXML
    private void prev(ActionEvent event
    ) {
        // Revenir au mois précédent
        currentDate = currentDate.minusMonths(1);

        // Afficher la nouvelle date et les jours du mois en cours
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        localDate.setText(currentDate.format(formatter));
        updateDaysGrid();
    }

    @FXML
    private void setdatedebut(ActionEvent event) {
        datefin.setDayCellFactory(dayCellFactory);
        LocalDate myDate = datedebut.getValue();
    }

    @FXML
    private void reserverVehicule(ActionEvent event) throws IOException {
        java.sql.Date ddebut = java.sql.Date.valueOf(datedebut.getValue());
        System.out.println(ddebut);
        java.sql.Date dfin = java.sql.Date.valueOf(datefin.getValue());
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
            Twilio.init("AC84ff7691013163d92fc31146ac61b9dc", "edd707b9aca44d01c33f87876d7b0db8");
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("+21653723896"),
                    new com.twilio.type.PhoneNumber("+12762888645"),
                    "Bonjour " + UserConn.nom + " " + UserConn.prenom + " Votre reservation au vehicule " + veh.getMarque() + ", immatriculation: " + veh.getImmatriculation() + " du " + datedebut.getValue() + " au " + datefin.getValue() + " a éte effectuer avec succes.").create();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeMesLocations.fxml"));
            Parent root2 = loader1.load();
            ListeMesLocationsController cc = loader1.getController();
            cc.listemeslocation();

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));

            veh = vehservice.recupererVehiculeByid(idv);

            reserverVehicule.getScene().setRoot(borderPane);

        } catch (SQLException ex) {
            // Logger.getLogger(FormLouerVehiculeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
