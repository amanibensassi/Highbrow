/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Utilisateur;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.UserConn;
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
    String pa,pb;

    UserService us = new UserService();
    Utilisateur u = new Utilisateur();
    int idclient = 0 ;
    /**
     * Initializes the controller class.
     */
        Stage Stage1;

    Stage stage;
    @FXML
    private AnchorPane scenePane;
    @FXML
    private Label nom1;
    @FXML
    private Label num1;
    @FXML
    private ImageView permis_avant;
    @FXML
    private ImageView permis_arriere;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        
    }
    public void setData(Utilisateur ut){
        
         pa = "C://Users//anasm//OneDrive//Documents//ImagesProjet//" + ut.getPhotopermis_avant();
        File file2 = new File(pa);
        
        Image image2 = new Image(file2.toURI().toString());
        
        permis_avant.setImage(image2);
        
        pb = "C://Users//anasm//OneDrive//Documents//ImagesProjet//" + ut.getPhotopermis_arriere();
        
        File file3 = new File(pb);
        
        Image image3 = new Image(file3.toURI().toString());
        permis_arriere.setImage(image3);
        
        nom.setText(ut.getNom());
        prenom.setText(ut.getPrenom());
        nom1.setText(ut.getNom());
        num.setText(String.valueOf(ut.getNum_tel()));
        num1.setText(ut.getMail());
       // img.setImage(ut.getImage());
       // u.setPrenom(ut.getPrenom());
        //u.setNum_tel(ut.getNum_tel());
        idclient=ut.getIdutilisateur();
        //nom.setText(u.getNom());
        System.out.println("id"+idclient);
        System.out.println("ut.getnom"+ut.getNom());
        System.out.println("cientprofile"+ut);
        
    }
   
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
//   stage =(Stage) scenePane.getScene().getWindow();
//        System.out.println("logged out !!");
//        stage.close();
//        }
            
    }



    @FXML
    private void modifier_user(ActionEvent event) throws IOException, SQLException {


                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        SideBarUserController cc = loader.getController();
        cc.setRole(UserConn.role.toString());
        BorderPane borderPane = new BorderPane();

        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierUser.fxml"));
        Parent root2 = loader1.load();
        u = us.recupererById(UserConn.idutilisateur);
        System.out.println("RRRRRRR"+u);
        ModifierUserController controller = loader1.getController();
        controller.setData(u);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        nom.getScene().setRoot(borderPane);
        
    }

}
   
        
    
    

