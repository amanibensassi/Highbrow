/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherDetailMecanicienController;
import entities.Mecanicien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.MecanicienService;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class MecanicienController implements Initializable{

    @FXML
    private Label nomPrenomLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private AnchorPane mec;
    
    Mecanicien me = new Mecanicien();
    
    MecanicienService ms=new MecanicienService();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private ImageView img;
    //String role="user";
    String role="Administrateur";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (role=="user"){
            btnmodifier.setVisible(false);
            btnsupprimer.setVisible(false);
        }
        // TODO
    }    
    
    public void setPersonne(Mecanicien m) {
        nomPrenomLabel.setText(m.getNom_mecanicien());
        //ageLabel.setText(String.valueOf(m.getContact()) );
        ageLabel.setText(m.getPrenom_mecanicien()); 
       // System.out.println(m.getImage());
        File file = new File(m.getImage());
        Image imagee = new Image(file.toURI().toString());
        img.setImage(imagee);
            me.setIdmecanicien(m.getIdmecanicien());
            me.setNom_mecanicien(m.getNom_mecanicien());
            me.setPrenom_mecanicien(m.getPrenom_mecanicien());
            me.setAdresse(m.getAdresse());
            me.setContact(m.getContact());
            me.setRegion(m.getRegion());
            me.setSpecialite(m.getSpecialite());
            me.setImage(m.getImage());
            //System.out.println("Envoyer image"+m.getImage());
    }

    @FXML
    private void detailMeca(MouseEvent event) {
                                try {
                                        System.out.println(me);
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetailMecanicien.fxml"));
                                        Parent root = loader.load();
                                        AfficherDetailMecanicienController controller = loader.getController();
                                        controller.setData(me);
                                        mec.getScene().setRoot(root);     
                                    } catch (IOException ex) {
                                        System.out.println("error1" + ex.getMessage());
                                    }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println("click");
        try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMecanicien.fxml"));
                                        Parent root = loader.load();
                                        ModifierMecanicienController controller = loader.getController();
                                        controller.setData(me);
                                        mec.getScene().setRoot(root);
                                        
                                    } catch (IOException ex) {
                                        System.out.println("error" + ex.getMessage());
                                    }
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException{
        try{
           ms.supprimer(me);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
           Parent root = loader.load();
           btnsupprimer.getScene().setRoot(root);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
}
