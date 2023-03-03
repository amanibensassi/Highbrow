/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Hamma;

import entities.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class ClientProfileController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label prenom;
    @FXML
    private Label num;

    UserService us = new UserService();
    Utilisateur u = new Utilisateur();
    int idclient = 0 ;
    @FXML
    private ImageView img;
    @FXML
    private Button logout;
    /**
     * Initializes the controller class.
     */
        Stage Stage1;

    Stage stage;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label listeSiege;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
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
        
    }/*
    public void afficher() throws SQLException{
        this.setData(u);
         nom.setText(u.getNom());
        System.out.println("uu"+u.getNom());
        u=us.recupererById(idclient);
        System.out.println("rr"+u);
    }*/

    @FXML
    private void logout(ActionEvent event) {
         try {
                     Parent root = FXMLLoader.load(getClass().getResource("authenticate.fxml"));
                        Stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
                                      root.setOnMousePressed(pressEvent -> {
                        root.setOnMouseDragged(dragEvent -> {
                            Stage1.setX(dragEvent.getScreenX() - pressEvent.getSceneX());
                            Stage1.setY(dragEvent.getScreenY() - pressEvent.getSceneY());
                        });
                    });
                        Scene  scene = new Scene(root);
                        Stage1.setScene(scene);
                        Stage1.show();

                } catch (IOException ex) {
                     System.out.println(ex.getMessage());
                }
        
        
//        Alert alert = new Alert(AlertType.CONFIRMATION);
//        alert.setTitle("Logout");
//        alert.setHeaderText("Your about to logout");
//        if(alert.showAndWait().get() == ButtonType.OK){
//        stage =(Stage) scenePane.getScene().getWindow();
//        System.out.println("logged out !!");
//        stage.close();
//        }
            
    }

//    private void modifier(ActionEvent event) throws SQLException {
//        
//         try {
//                                        
//                                        System.out.println(u);
//                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierUser.fxml"));
//                                        Parent root = loader.load();
//                                        
//                                        ModifierUserController controller = loader.getController();
//                                       
//                                       // us.modifier(u);
//                                       controller.setData(u);
//                                        
//                                        nom.getScene().setRoot(root);     
//                                    } catch (IOException ex) {
//                                        System.out.println("error1" + ex.getMessage());
//                                    }
//        
//        
//    }

}
   
        
    
    

