/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ModifierSiegeController;
import GUI.AfficherVehiculeBySiegeController;
import entities.Siege;
import entities.Vehicule;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Modality;
import javafx.stage.Stage;
import services.SiegeService;
import services.UserConn;
import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class SiegeController implements Initializable {

    Siege pe = new Siege();

    SiegeService ps = new SiegeService();
    VehiculeService aa = new VehiculeService();
    @FXML
    private AnchorPane siegesLignes;
     private GridPane grid;
    @FXML
    private Label regionLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label numLabel;
    @FXML
    private Button modifierButton;
    @FXML
    private Button btnsupprimer;
    @FXML
    private Label adresseLabel;
    @FXML
    private Label nomsiegeLabel;
    @FXML
    private Button affichervehiculesbtn;
    @FXML
    private Button idVoirChauffeur;
    @FXML
    private ImageView btnreclamation;
    
    int ids ;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (UserConn.role.toString().equals("client")){
            modifierButton.setVisible(false);
            btnsupprimer.setVisible(false);
            idVoirChauffeur.setVisible(false);
        }

    }
    
    public void setSiege(Siege c) {
        ids=c.getIdsiege();
        nomsiegeLabel.setText(c.getNom_siege());
        mailLabel.setText(c.getMail());
        adresseLabel.setText(c.getAdresse());
        regionLabel.setText(c.getRegion().toString());
        numLabel.setText(String.valueOf(c.getNum_tel_siege()));

        pe.setId_utilisateur(c.getId_utilisateur());
        pe.setIdsiege(c.getIdsiege());
        pe.setAdresse(c.getAdresse());
        pe.setMail(c.getMail());
        pe.setNom_siege(c.getNom_siege());
        pe.setNum_tel_siege(c.getNum_tel_siege());
        pe.setRegion(c.getRegion());
    }

    @FXML
    private void modifierSiege(ActionEvent event) {

        try {
            System.out.println("NIK ZIBIIIIIIIII"+UserConn.role);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierSiege.fxml"));
            Parent root2 = loader1.load();
              ModifierSiegeController controller = loader1.getController();

            controller.setData(pe);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            siegesLignes.getScene().setRoot(borderPane);
            
            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierSiege.fxml"));
//            Parent root = loader.load();
//            ModifierSiegeController controller = loader.getController();
//
//            controller.setData(pe);
//
//            siegesLignes.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void supprimerSiege(ActionEvent event) throws IOException {

//        try {
//            ps.supprimer(pe);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//
//            siegesLignes.getScene().setRoot(root);
//
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Confirmation");
    alert.setHeaderText(null);
    alert.setContentText("Voulez-vous vraiment supprimer ce siège ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.get() == ButtonType.OK){
        try {
            ps.supprimer(pe);
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root2 = loader1.load();
             // AfficherSiegeController controller = loader1.getController();

            //controller.setData(pe);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            siegesLignes.getScene().setRoot(borderPane);
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    }


    @FXML
    private void afficherVehiculeSiege(ActionEvent event) throws SQLException {
        // Récupération de l'ID du siège à partir de l'objet "pe" (supposons que "pe" soit un objet Siege)
        
        int siegeId = pe.getIdsiege();
        //System.err.println("1");
        System.out.println(siegeId);

        List<Vehicule> vehicules = aa.recupererVehiculeBySiege(siegeId);
        System.out.println(vehicules);

        if (vehicules.isEmpty() && (UserConn.role.toString().equals("client") || UserConn.role.toString().equals("administrateur"))) {
            // Aucun véhicule n'a été trouvé pour ce siège
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Aucun véhicule trouvé");
            alert.setHeaderText(null);
            alert.setContentText("Il n'y a aucun véhicule associé à ce siège.");
            alert.showAndWait();
//                           try {
//                               
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
//            Parent root1 = loader.load();
//            BorderPane borderPane = new BorderPane();
//            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root2 = loader1.load();
//              AfficherVehiculeBySiegeController controller = loader1.getController();
//            controller.dynamicinitialize(pe.getIdsiege());
//          
//            HBox hbox = new HBox(root1, new Pane(), root2);
//            hbox.setSpacing(20);
//
//            borderPane.setRight(hbox);
//
//            borderPane.setLeft(root1);
//
//            borderPane.setPadding(new Insets(10, 10, 30, 10));
//            affichervehiculesbtn.getScene().setRoot(borderPane);       
//                               
//                               
//                               
//                               
//                               
//                               
//                               
////            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
////            Parent root = loader.load();
////            AfficherVehiculeBySiegeController controller = loader.getController();
////            controller.dynamicinitialize(pe.getIdsiege());
////            affichervehiculesbtn.getScene().setRoot(root);
//            
//        } catch (IOException ex) {
//            System.out.println("error" + ex.getMessage());
//        }
        } else {
               try {
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root2 = loader1.load();
              AfficherVehiculeBySiegeController controller = loader1.getController();
            controller.dynamicinitialize(pe.getIdsiege());
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            affichervehiculesbtn.getScene().setRoot(borderPane); 
                   
//                   
//                   
//                   
//                   
//                   
//                   
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeBySiegeController controller = loader.getController();
//            controller.dynamicinitialize(pe.getIdsiege());
//            affichervehiculesbtn.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
//                        // Affichage des véhicules dans une liste
//    ListView<Vehicule> vehiculesListView = new ListView<>();
//    vehiculesListView.getItems().addAll(vehicules);
//
//    // Configuration de la mise en page de la liste
//    vehiculesListView.setPrefWidth(400);
//    vehiculesListView.setPrefHeight(200);
//    vehiculesListView.setLayoutX(50);
//    vehiculesListView.setLayoutY(50);
//
//    
//    
//    
//
//    // Création d'un conteneur pour les éléments
//    Pane pane = new Pane();
//    pane.getChildren().addAll(vehiculesListView);
//
//    // Création de la fenêtre
//    Stage stage = new Stage();
//    stage.setTitle("Véhicules associés au siège " + siegeId);
//    stage.setScene(new Scene(pane, 870, 650));
//    stage.show();
//}


        }
    }

    @FXML
    private void VoirChauffeur(ActionEvent event) throws IOException {
        int siegeId = pe.getIdsiege();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
            Parent root2 = loader1.load();
            ListeChauffeurController c= loader1.getController();
            c.affichage(siegeId);
//              AfficherVehiculeBySiegeController controller = loader1.getController();
//            controller.dynamicinitialize(pe.getIdsiege());
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            idVoirChauffeur.getScene().setRoot(borderPane);   
        
        
    }

    @FXML
    private void AjouterReclamation(MouseEvent event) throws IOException, ParseException, SQLException {
        
        if (UserConn.role.toString().equals("client")){
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
        Parent root2 = loader2.load();
        AjouterReclamationController md1 = loader2.getController();
        md1.setIdSiege(ids);
           
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root2));
        modalStage.showAndWait();
        }
        if (UserConn.role.toString().equals("proprietaire_agence")){
            
        FXMLLoader loader2 = new FXMLLoader(getClass().getResource("AfficherReclamationUtilisateur.fxml"));
        Parent root2 = loader2.load();
        System.out.println("IIIIDIDIDIDIDI"+ids);
        AfficherReclamationUtilisateurController md1 = loader2.getController();
        md1.getReclamationSiege(ids);
        Stage modalStage = new Stage();
        modalStage.initModality(Modality.APPLICATION_MODAL.APPLICATION_MODAL);
        modalStage.setScene(new Scene(root2));
        modalStage.showAndWait();
        }
        
    }


}

        
    
    


    

