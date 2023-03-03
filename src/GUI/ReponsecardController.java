/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reponse;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class ReponsecardController implements Initializable {

    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label reponseid;
    Reponse rep = new Reponse();
    ReponseService rs = new ReponseService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setdesign(Reponse c){
     this.rep=c;
     username_id.setText("test");
     dateid.setText(c.getDate_reponse().toString());

  reponseid.setText(c.getReponse());
    
    
    }
    
}
