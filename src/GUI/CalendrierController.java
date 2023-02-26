/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
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
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;

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

    /**
     * Initializes the controller class.
     */
    private void updateDaysGrid() {

        try {

//            BackgroundFill backgroundFill = new BackgroundFill(Color.GREY, null, null);
//            Background background = new Background(backgroundFill);
            // Créer une liste de boutons pour chaque jour du mois en cours
            List<AnchorPane> buttons = new ArrayList<>();
            //afficher le mois
            YearMonth yearMonth = YearMonth.from(currentDate);

            //afficher combien de jour dans le mois
            int daysInMonth = yearMonth.getMonth().length(yearMonth.isLeapYear());

            for (int day = 1; day <= daysInMonth; day++) {
                String ddd = String.valueOf(day);
                DayOfWeek dayOfWeek = yearMonth.atDay(day).getDayOfWeek();

                Label label = new Label(String.valueOf(day));
                label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #333333; -fx-font-weight: bold;");
                /**
                 * *************************
                 */
                String moissss = yearMonth.toString();
                String[] parts5 = moissss.split("-");
                String month11 = parts5[1];
                String yearsss = parts5[0];
int month11fet =Integer.parseInt(month11);
int ammfet =Integer.parseInt(yearsss);
                AnchorPane button = new AnchorPane();
                //button.setDisable(true);
                LocalDate today = LocalDate.now();
               
               
                String curentdatee=today.toString();
                String[] splitCurrentDate = curentdatee.split("-");
                String chhar = splitCurrentDate[1];
                String nhar = splitCurrentDate[2];
                String aam = splitCurrentDate[0];
                int nhar2 = Integer.parseInt(nhar);
                int chhar2 = Integer.parseInt(chhar);
                int nharat = Integer.parseInt(label.getText());
               int ammtw = Integer.parseInt(aam);
                System.out.println("aam parcour"+month11fet);
              
                System.out.println("aam tw"+chhar2);
                if( chhar2>month11fet || ammfet<ammtw)
                    
                {  button.setDisable(true);}
                    if( month11.equals(chhar) && aam.equals(yearsss)&& nharat<nhar2){
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

                    System.out.println(datezzz1);
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("formLouerVehicule.fxml"));
                    Parent root;
                    try {
                        root = loader.load();
                        FormLouerVehiculeController chauffeurr = loader.getController();
                        chauffeurr.setdatedebut(datezzz1);

                        button.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(CalendrierController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    //modifier.getScene().setRoot(root);
                    //System.out.println(datePicker);
                    // Ajouter le code que vous souhaitez exécuter ici
                });

                //   button.setBackground(background);
                // button.setPrefSize(200, 200);
                // button.setId("list-anchor-pane");
                // button.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: black;");
                button.setStyle("-fx-background-color: #F5F5F5; -fx-border-color: black; -fx-padding: 10px;");
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
                List<Location> locationFait = ls.recupererAllByIdVehicule(2);

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
                            }

                        }
                        //******** hethi nabdew nihsmou ml 1 hat il date fin si char d debu diff date fin w chahr date fin equal il chhar courant
                        for (int x = 1; x <= dayy3; x++) {

                            String khra = x + "";

                            if (khra.equals(label.getText()) && moisL2.equals(moisString)) {
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

                        label.setStyle("-fx-font-size: 14pt; -fx-text-fill: #FF0000;; -fx-font-weight: bold;");
                    }
                    if (dayEnString3.equals(label.getText()) && moisString.equals(moisL2)) {

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

    @Override
    public void initialize(URL url, ResourceBundle rb
    ) {
        // Afficher la date d'aujourd'hui
        currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(" MMMM yyyy");
        localDate.setText(currentDate.format(formatter));

        // Afficher les jours du mois en cours
        updateDaysGrid();

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

}
