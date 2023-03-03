/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guitrabelsi;

import entities.Siege;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import services.SiegeService;
import typeenumeration.Region;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class ModifierSiegeController implements Initializable {

    @FXML
    private TextField nomsiegetf;
    @FXML
    private TextField emailtf;
    @FXML
    private TextField adressetf;
    @FXML
    private TextField numerodeteltf;
    @FXML
    private ComboBox<String> regiontf;
      
     SiegeService ps = new SiegeService();
    int idsiege =0;
    int idutilisateur=0;
    @FXML
    private Button validerbtn;
    //private Button modifier;
    @FXML
    private Button annulerbtn;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> listRegion = FXCollections.observableArrayList("ariana","beja","ben_Arous","bizerte","gabes","gafsa","jendouba","kairouan","kasserine","kebili","kef","mahdia","manouba","medenine","monastir","nabeul","sfax","sidi_Bouzid","siliana","sousse","tataouine","tozeur","tunis","zaghouan");
        regiontf.setItems(listRegion);
    }
    
     public void setData(Siege c) {

        nomsiegetf.setText(c.getNom_siege());
        
        emailtf.setText(c.getMail());
        adressetf.setText(String.valueOf(c.getAdresse()));
        regiontf.setValue(c.getRegion().toString());
        numerodeteltf.setText(String.valueOf(c.getNum_tel_siege()));
        idutilisateur=c.getId_utilisateur();
        idsiege=c.getIdsiege();
    
     }
    
    @FXML
    private void valider(ActionEvent event){
        try{
        Siege s = new Siege();
        s.setId_utilisateur(idutilisateur);
        s.setIdsiege(idsiege);
        String nomSiege = nomsiegetf.getText();

            s.setNom_siege(nomSiege);
     
        s.setMail(emailtf.getText());
        s.setAdresse(adressetf.getText());
        s.setNum_tel_siege(Integer.valueOf(numerodeteltf.getText()));
        s.setRegion(Region.valueOf(regiontf.getValue()));
        System.out.println(s);
        ps.modifier(s);
        System.out.println(s);
        System.out.println("siege modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root = loader.load();
            AfficherSiegeController controller = loader.getController();        
            nomsiegetf.getScene().setRoot(root);           
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void annuler(ActionEvent event) {
         try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root = loader.load();
            AfficherSiegeController controller = loader.getController();        
            nomsiegetf.getScene().setRoot(root);           
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }




    


 

    }
    
   

