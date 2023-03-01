/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Siege;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import services.SiegeService;
import typeenumeration.Region;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class AjouterSiegeController implements Initializable {

    @FXML
    private TextField nomsiegetf;
    @FXML
    private TextField numerodeteltf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField adressetf;
    @FXML
    private ComboBox<String> regiontf;
    @FXML
    private Button ajouterbtn;
    @FXML
    private Button afficherbtn;

    SiegeService ps = new SiegeService();
    @FXML
    private Label labelTitreSieges;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listRegion = FXCollections.observableArrayList("ariana","beja","ben_Arous","bizerte","gabes","gafsa","jendouba","kairouan","kasserine","kebili","kef","mahdia","manouba","medenine","monastir","nabeul","sfax","sidi_Bouzid","siliana","sousse","tataouine","tozeur","tunis","zaghouan");
        regiontf.setItems(listRegion);
    }    
  

    @FXML
    private void ajouter(ActionEvent event) {
        try {
            Siege s = new Siege();
            // controle de saisie - nom du siege ! 
            String nomSiege = nomsiegetf.getText();
            if(nomSiege == null || nomSiege.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("la case 'nom du siege' est vide ! ");
            alert.setContentText("Veuillez entrer un nom de siege valide. ! ");
            alert.showAndWait();
            return;
        }
            s.setNom_siege(nomSiege);
            // controle de saisie - adresse ! 
            String adresse = adressetf.getText();
            if(adresse == null || adresse.trim().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("le case 'adresse' est vide ! ");
            alert.setContentText("Veuillez entrer une adresse valide. ! ");
            alert.showAndWait();
            return;
            }
            s.setAdresse(adresse);
            // controle de saisie - numero de telephone ! 
            int numTelSiege = (Integer.parseInt(numerodeteltf.getText()));
            Pattern p = Pattern.compile("\\d{8}");
            Matcher m= p.matcher(Integer.toString(numTelSiege));
            if (!m.matches()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("le case 'numero du siege' est vide ! ");
            alert.setContentText("Veuillez entrer un numéro de téléphone valide ( composé avec 8 chiffres).");
            alert.showAndWait();
            return;
        }
            s.setNum_tel_siege(numTelSiege);
            // controle de saisie - email ! 
            String email = emailtf.getText();
            if (email == null || !email.matches("\\b[\\w.%-]+@gmail\\.com\\b")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur de saisie");
            alert.setHeaderText("le case 'email' est vide ! ");
            alert.setContentText("Veuillez entrer un email valide (@gmail.com).");
            alert.showAndWait();
            return;
        }            
            s.setMail(emailtf.getText());
            String r = regiontf.getSelectionModel().getSelectedItem();
            s.setRegion(Region.valueOf(r));
            
            
            s.setId_utilisateur(1);
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de siège réussi");
            alert.setContentText("Le siège a été ajouté avec succès !");
            alert.showAndWait();
            System.out.println("Siege ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
        
    

    @FXML
    private void recuperer(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root = loader.load();
            AfficherSiegeController controller = loader.getController();
            
            adressetf.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
