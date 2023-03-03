/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_Hamma;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
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
//import services.EnvoyerEmail;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class ForgotMDPController implements Initializable {

    @FXML
    private TextField pwdemail;
    @FXML
    private TextField pwdcode;
    @FXML
    private Button codebutton;
    @FXML
    private Button resetbutton;
    int test;
    Parent root;
    private Stage stage;
    private Stage scene;
    /**
     * Initializes the controller class.
     * 
     */

Connection cnx;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public Integer rechercheEmail(String email){
                    Integer exist = 0;
                    Connection cnx = MyDB.getInstance().getCnx();
                    PreparedStatement pst;
                    ResultSet rs = null;
                    try {
                        pst = cnx.prepareStatement("SELECT COUNT(*) AS count FROM utilisateur WHERE mail=? ");
                        pst.setString(1, email);
                      
                        rs = pst.executeQuery();
                        if (rs.next()) {
                             exist=rs.getInt("count"); 
                        }
                    } catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                    }
                    return exist;
    }

    @FXML
    public int setCode (){
            Random random = new Random();
            int code;
            code = random.nextInt(100000);
            test=code;
            return code;
    } 

    private int sendcode(ActionEvent event) throws MessagingException {
        if (rechercheEmail(pwdemail.getText())==1){
            pwdcode.setDisable(false);
            resetbutton.setDisable(false);
            
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Next Step");
            alert.setHeaderText("A code was sent via email");
            alert.setContentText("Please check your e-mail box to get your reset code ");
            alert.show();
            
            test=setCode();
           // EnvoyerEmail.envoyer(pwdemail.getText(),test);
            
        
        }
        return test;
        
        
    }

    @FXML
    private void reset_onclick(ActionEvent event) {
         try {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Last Step");
            alert.setHeaderText("NEW PASSWORD");
            alert.setContentText("Please enter a new password");
            alert.show();
            
            root = FXMLLoader.load(getClass().getResource("NewPwd.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
