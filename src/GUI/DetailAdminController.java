/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class DetailAdminController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label mail;
    @FXML
    private Label num;
    @FXML
    private Label date_de_naissance;
    @FXML
    private Label role;
    @FXML
    private ImageView image;
    int idclient ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
     public void setData(Utilisateur ut){
        
        nom.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        num.setText(String.valueOf(ut.getNum_tel()));
        mail.setText(ut.getMail());
        role.setText(String.valueOf(ut.getRole()));
        date_de_naissance.setText(ut.getDate_naissance().toString());      

// img.setImage(ut.getImage());
       // u.setPrenom(ut.getPrenom());
        //u.setNum_tel(ut.getNum_tel());
        idclient=ut.getIdutilisateur();
        //nom.setText(u.getNom());
        System.out.println("id"+idclient);
        System.out.println("ut.getnom"+ut.getNom());
        System.out.println("cientprofile"+ut);
        
    }
    
}
