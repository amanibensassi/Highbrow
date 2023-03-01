/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Location;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class SideBarUserController implements Initializable {

    @FXML
    private Button profil;
    @FXML
    private Button sieges;
    @FXML
    private Button HLocation;
    @FXML
    private Button reclamer;
    @FXML
    private Button deconx;

    /**
     * Initializes the controller class.
     */
     public void test() throws IOException {
      
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
          try {
            test();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
       
        
    }    

    @FXML
    private void GoToProfil(ActionEvent event) {
    }

    @FXML
    private void  GoToReclamer(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("formLouerVehicule.fxml"));
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
        
        reclamer.getScene().setRoot(borderPane);
    }

    @FXML
    private void GoToHistoriqueLocation(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeMesLocations.fxml"));
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
        
        HLocation.getScene().setRoot(borderPane);
    }
    

    @FXML
    private void GoToSieges (ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
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
        
        HLocation.getScene().setRoot(borderPane);
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
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
        
        deconx.getScene().setRoot(borderPane);
    }
    
}
