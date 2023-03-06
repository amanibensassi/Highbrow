/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.ChauffeurService;
import entities.Chauffeur;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class DetailChProfilController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private ImageView profileImage;
    @FXML
    private Label prenom;
    @FXML
    private Label numtel;
    ChauffeurService ch = new ChauffeurService();
String nomImage;
    /**
     * Initializes the controller class.
     */
    public void setChauffeurDetail(int id) {
        System.out.println("cfghgfdsdfgthyj");
        Chauffeur c;
        try {
            c = ch.recupererById(id);
            nom.setText(c.getNom());
            prenom.setText(c.getPrenom());
            email.setText(c.getAdresse());
            numtel.setText(String.valueOf(c.getContact()));
             nomImage = "C://Users//anasm//OneDrive//Documents//ImagesProjet//" + c.getImage();
              File file = new File(nomImage);

        Image imagee = new Image(file.toURI().toString());

        profileImage.setImage(imagee);
        } catch (SQLException ex) {
            Logger.getLogger(DetailChProfilController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
