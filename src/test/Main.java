/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.time.YearMonth;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import utils.MyDB;

/**
 *
 * @author eya
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            //MyDB.getInstance();
            // Parent root =FXMLLoader.load(getClass().getResource("../GUI/AjouterChauffeur.fxml"));
            //  Parent root =FXMLLoader.load(getClass().getResource("../GUI/RecupererAllChauffeurs.fxml"));
            // Parent root =FXMLLoader.load(getClass().getResource("../GUI/calendrier.fxml"));
//     Parent root =FXMLLoader.load(getClass().getResource("../GUI/ListeChauffeur.fxml"));
//      //Parent root =FXMLLoader.load(getClass().getResource("../GUI/detailChauffeur.fxml"));
//
//           Scene scene = new Scene(root,900,500);
//            
//           primaryStage.initStyle(StageStyle.UNDECORATED);
//           primaryStage.setTitle("Chauffeurs");
//            primaryStage.setScene(scene);
//            
//            primaryStage.show();

            //   FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeChauffeur.fxml"));
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/formLouerVehicule.fxml"));
          //  FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ListeMesLocations.fxml"));
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/SideBarUser.fxml"));
            // FXMLLoader loader = new FXMLLoader(getClass().getResource("../GUI/ResponsableAgenceLocation.fxml"));
            Parent root = loader.load();
            Scene sc = new Scene(root, 900, 500);
            primaryStage.setTitle("GestionChauffeurs + Location");
            primaryStage.setScene(sc);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
