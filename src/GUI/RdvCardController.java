/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import entities.Vehicule;
import entities.Vente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.UserConn;
import services.UserService;
import services.VehiculeService;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class RdvCardController implements Initializable {

    private Label iduser;
    private Label idveh;
    @FXML
    private Label date_rendez_vous;
    @FXML
    private Button detail;
    @FXML
    private Button delete;

    Vente ve = new Vente();
    VenteService vs = new VenteService();
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label nomv;
    Utilisateur u = new Utilisateur();
    Vehicule vh = new Vehicule();
    VehiculeService vhs = new VehiculeService();
    UserService us = new UserService();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(UserConn.role.toString().equals("proprietaire_agence"))
        {detail.setVisible(false);
        delete.setVisible(false);
      
        }
    }

    public void setRdv(Vente v) throws FileNotFoundException, ParseException, SQLException {
        u= us.recupererById(v.getId_utilisateur());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        vh = vhs.recupererVehiculeByid(v.getId_vehicule());
        nomv.setText(vh.getImmatriculation());
       // iduser.setText(String.valueOf(v.getId_utilisateur()));
       // idveh.setText(String.valueOf(v.getId_vehicule()));
        ve = v;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        String dateString = sdf.format(v.getDate_rendez_vous());
        System.out.println(dateString);

        date_rendez_vous.setText(v.getDate_rendez_vous().toString());

    }

    @FXML
    private void Rdvdetail(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierRendezVous.fxml"));
            Parent root2 = loader1.load();
            ModifierRendezVousController ac = loader1.getController();
            ac.setData(ve);;
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);
            borderPane.setRight(hbox);
            borderPane.setLeft(root1);
            borderPane.setPadding(new Insets(10, 10, 30, 10));
            detail.getScene().setRoot(borderPane);
        } catch (IOException ex) {
            System.out.println("error1" + ex.getMessage());
        }

    }

    @FXML
    private void SupprimerRdv(ActionEvent event) throws IOException, FileNotFoundException, ParseException, SQLException {
        vs.supprimer(ve);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(UserConn.role.toString());
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
        Parent root2 = loader1.load();
        AfficherRendezVousController rv = loader1.getController();
        rv.setdata();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);
        borderPane.setRight(hbox);
        borderPane.setLeft(root1);
        borderPane.setPadding(new Insets(10, 10, 30, 10));
        delete.getScene().setRoot(borderPane);

    }

}
