/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.ChauffeurService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ChauffeurController implements Initializable {

    ChauffeurService c = new ChauffeurService();
    @FXML
    private AnchorPane cardPane;
    @FXML
    private ImageView userImage;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    private int id;
    private Chauffeur ch;
    private String nomImage;
    @FXML
    private Label mail;
    @FXML
    private Label numtel;
    @FXML
    private ImageView icon;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image image = new Image(getClass().getResourceAsStream("../images/programme.png"));
//ImageView icon = new ImageView(image);
        icon.setImage(image);
    }

    public void setChauffeur(Chauffeur c) {
        ch = c;
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        mail.setText(c.getAdresse());
//        cin.setText(String.valueOf(c.getCin()));
//        region.setText(String.valueOf(c.getRegion()));
//        prix.setText(String.valueOf(c.getPrix_par_jour()));
        numtel.setText(String.valueOf(c.getContact()));
        nomImage = "C://xampp//htdocs//img//" + c.getImage();
        id = c.getIdchauffeur();
        File file = new File(nomImage);

        Image imagee = new Image(file.toURI().toString());

        userImage.setImage(imagee);
    }

    @FXML
    private void supprimerChauffeur(ActionEvent event) {
        try {

            c.supprimer(ch);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
            Parent root = loader.load();

            supprimer.getScene().setRoot(root);
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void modifierChauffeur(ActionEvent event) {
         try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("detailChauffeur.fxml"));
            Parent root = loader.load();

            DetailChauffeurController chauffeurr = loader.getController();
            chauffeurr.setChauffeur(ch);

            modifier.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void onClickCalendrier(MouseEvent event) throws IOException {
       FXMLLoader loader = new FXMLLoader(getClass().getResource("calendrier.fxml"));
        Parent root = loader.load();

        icon.getScene().setRoot(root);
    }
}
