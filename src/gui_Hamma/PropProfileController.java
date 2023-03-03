/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Hamma;

import entities.Utilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class PropProfileController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;
    Utilisateur u = new Utilisateur();
    int idclient=0;

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
