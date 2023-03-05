/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import entities.Siege;
import entities.Utilisateur;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.ReclamationService;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class ReclamationSiegeController implements Initializable {

    @FXML
    private HBox grid;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label date;
    @FXML
    private CheckBox etat;
    @FXML
    private Label corp;
    UserService us = new UserService();
    Reclamation re = new Reclamation();
    ReclamationService rs = new ReclamationService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    
    public void setReclamation(Reclamation r) throws ParseException, SQLException {
        Utilisateur u =new Utilisateur();
        u=us.recupererById(r.getId_utilisateur());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        etat.setSelected(r.isEtat());
        date.setText(r.getDate_reclamation().toString());
        corp.setText(r.getCorps());
        re.setCorps(r.getCorps());
        re.setType_reclamation(r.getType_reclamation());
        if (re.isEtat()) {
            etat.setDisable(true);
        }
        re.setIdreclamation(r.getIdreclamation());
         System.out.println("rrrr"+re);
    }

    @FXML
    private void changeretat(ActionEvent event) throws SQLException {
        re.setEtat(etat.isSelected());
        System.out.println("re"+re);
        rs.modifier(re);
    }
    
    
    
}
