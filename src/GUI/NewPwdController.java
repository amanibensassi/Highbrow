/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.UserService;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class NewPwdController implements Initializable {

    @FXML
    private TextField txtemail1;
    @FXML
    private TextField txtnew;
    @FXML
    private TextField txtconfirm;
    @FXML
    private Button newpasswordbutton;
     Connection cnx = MyDB.getInstance().getCnx();
     UserService us = new UserService();
     Stage Stage1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void ModifierMdp(String email, String pwd) {
     try{
        String req = "UPDATE utilisateur SET mot_de_passe=? WHERE mail=?";
        PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, pwd);
            pst.setString(2, email);
            pst.executeUpdate();
            System.out.println("The password was updated successfully!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @FXML
    private void NewPwd(ActionEvent event) throws SQLException {
         String new_pwd, confirm_pwd, email;
        new_pwd=txtnew.getText();
        confirm_pwd=txtconfirm.getText();
        email=txtemail1.getText();
        
        
        if (new_pwd==null || confirm_pwd ==null)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }
        else if (new_pwd.equals(confirm_pwd)==false)      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter the same password to confirm");
            alert.show();
        }else {
           us.modifierPassword(email,new_pwd);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("DONE");
            alert.setHeaderText("PASSWORD UPDATED");
            alert.setContentText("Reconnect with your new password");
            alert.show();
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
        }
        
        
    }
    
}
