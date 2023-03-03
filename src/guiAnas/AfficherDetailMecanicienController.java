/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAnas;

import entities.Mecanicien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherDetailMecanicienController implements Initializable {

    int idmec=0;
    String image;
    @FXML
    private Label lblnom;
    @FXML
    private Label lblprenom;
    @FXML
    private Label lblspecialite;
    @FXML
    private Label lblregion;
    @FXML
    private Label lblcontact;
    @FXML
    private Label lbladresse;
    @FXML
    private ImageView img;
    Mecanicien me = new Mecanicien();
    @FXML
    private Button calendrier;
    int id=0;
    String role="User";
    //String role="Administrateur";
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (role!="User"){
            calendrier.setVisible(false);
        }
        if (me.getImage()!=null){
        this.setData(me);
        }
//        else {
//            System.out.println("null");
//        }
        // TODO
    }    
    public void setData(Mecanicien m) {
          id=m.getIdmecanicien();
          lblnom.setText(m.getNom_mecanicien());
          lblprenom.setText(m.getPrenom_mecanicien());
          lblcontact.setText(String.valueOf(m.getContact()));
          lbladresse.setText(m.getAdresse());
          lblregion.setText(String.valueOf(m.getRegion()));
          lblspecialite.setText(String.valueOf(m.getSpecialite()));
          File file = new File(m.getImage());
          Image imagee = new Image(file.toURI().toString());
          img.setImage(imagee);         
          me.setIdmecanicien(id);
          me.setNom_mecanicien(m.getNom_mecanicien());
          
    }

    @FXML
    private void afficherListe(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root = loader.load();
        AfficherMecaniciensProfController controller = loader.getController();
        lbladresse.getScene().setRoot(root);
    }

    @FXML
    private void afficherCalendrier(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Calendrier.fxml"));
        Parent root = loader.load();
        CalendrierController controller = loader.getController();
        System.out.println("ME"+me);
        controller.setData(me);
        lbladresse.getScene().setRoot(root);
        
    }

    
    
}
