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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class ChauffeurDisponibleController implements Initializable {

    LocationService ls = new LocationService();
    @FXML
    private HBox hbox;
    @FXML
    private ImageView userImage;
    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label numtel;
    @FXML
    private Label mail;
    @FXML
    private Label cin;
   
    private Chauffeur ch;
    private String nomImage;
    @FXML
    private Button affecter;
private int idL;
 private int idchauff;
    /**
     * Initializes the controller class.
     */
    public void setChauffeur(Chauffeur c,int id) {
        idL =id;
        ch = c;
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        mail.setText(c.getAdresse());
//        cin.setText(String.valueOf(c.getCin()));
//        region.setText(String.valueOf(c.getRegion()));
//        prix.setText(String.valueOf(c.getPrix_par_jour()));
        numtel.setText(String.valueOf(c.getContact()));
        nomImage = "C://xampp//htdocs//img//" + c.getImage();
        idchauff = c.getIdchauffeur();
        File file = new File(nomImage);
        
        Image imagee = new Image(file.toURI().toString());
        
        userImage.setImage(imagee);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void afficherChDetail(MouseEvent event) {
    }
    
    @FXML
    private void affecterLech(ActionEvent event) {
        try {
            
            ls.AffecterUnchauffeur(idL, idchauff);
             Stage stage = (Stage) affecter.getScene().getWindow(); // obtenir la référence de la fenêtre actuelle
                stage.close();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ResponsableAgenceLocation.fxml"));
            Parent root2 = loader1.load();
      
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
       
            borderPane.setLeft(root1);
        

        borderPane.setPadding(new Insets(10, 10, 30, 10));
//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        
        affecter.getScene().setRoot(borderPane);           
    
        } catch (SQLException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
