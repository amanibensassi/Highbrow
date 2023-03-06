/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ReclamationController;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.ReclamationService;
import services.UserConn;

/**
 * FXML Controller class
 *
 * @author benha
 */
public class AfficherReclamationUtilisateurController implements Initializable {

    @FXML
    private GridPane grid;
    ReclamationService rs = new ReclamationService();
    @FXML
    private Button btnajouterreclamation;
    private int ids;
    String role="User";
    /**
     * Initializes the controller class.
     */
 
    public int getIds() {
        return ids;
    }

    public void setIds(int ids) {
        this.ids = ids;
    }
    
    public void getData() throws ParseException{
        try {
            
            if (UserConn.role.toString().equals("administrateur")){
            List<Reclamation> reclamations = rs.recuperer();
            //List<Reclamation> reclamations = rs.recupererReclamation_Siege(ids);
            System.out.println("aa"+reclamations);
            int row = 1;
            int column = 0;
            for (int i = 0; i < reclamations.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                HBox AnchorPane  = loader.load();
                //passage de parametres
                ReclamationController controller = loader.getController();
                controller.setReclamation(reclamations.get(i));
                grid.add(AnchorPane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
            }
            
            if (UserConn.role.toString().equals("client") || UserConn.role.toString().equals("proprietaire_agence") ){
                btnajouterreclamation.setVisible(false);
            List<Reclamation> reclamations = rs.recupererReclamationUtilisateur(UserConn.idutilisateur);
            //List<Reclamation> reclamations = rs.recupererReclamation_Siege(ids);
            System.out.println("aa"+reclamations);
            int row = 1;
            int column = 0;
            for (int i = 0; i < reclamations.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                HBox AnchorPane  = loader.load();
                //passage de parametres
                ReclamationController controller = loader.getController();
                controller.setReclamation(reclamations.get(i));
                grid.add(AnchorPane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
            }
           
            if (UserConn.role.toString().equals("proprietaire_agence")){
            //List<Reclamation> reclamations = rs.recupererReclamationUtilisateur(1);
                System.out.println("IDS"+ids);
            List<Reclamation> reclamations = rs.recupererReclamation_Siege(ids);
            System.out.println("ssssaa"+reclamations);
            int row = 1;
            int column = 0;
            for (int i = 0; i < reclamations.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ReclamationSiege.fxml"));
                AnchorPane AnchorPane  = loader.load();
                //passage de parametres
                ReclamationSiegeController controller = loader.getController();
                controller.setReclamation(reclamations.get(i));
                grid.add(AnchorPane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   @Override
    public void initialize(URL url, ResourceBundle rb) {
//        try {
//            // TODO
//            //this.getData();
//        } catch (ParseException ex) {
//            Logger.getLogger(AfficherReclamationController.class.getName()).log(Level.SEVERE, null, ex);
//        }
            if (UserConn.role.toString().equals("administrateur")){
            btnajouterreclamation.setVisible(false);
            }
    }  

    @FXML
    private void AjouterReclamation(ActionEvent event) throws IOException {
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
        Parent root2 = loader2.load();
        AjouterReclamationController md1 = loader2.getController();
        
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root2));
        modalStage.showAndWait();
        try {
            this.getData();
        } catch (ParseException ex) {
            Logger.getLogger(AfficherReclamationUtilisateurController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
