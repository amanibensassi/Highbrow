/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import services.ReclamationService;

/**
 * FXML Controller class
 *
 * @author benha
 */
public class ReclamationController implements Initializable {

    @FXML
    private Label type_rec;
    @FXML
    private Label corp;
    @FXML
    private CheckBox etat;
    @FXML
    private Label date;
    Reclamation re = new Reclamation();
    ReclamationService rs = new ReclamationService();
    @FXML
    private HBox grid;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setReclamation(Reclamation r) throws ParseException {
        type_rec.setText(r.getType_reclamation().toString());
        corp.setText(r.getCorps());
        etat.setSelected(r.isEtat());
        date.setText(r.getDate_reclamation().toString());
        re.setCorps(r.getCorps());
        re.setType_reclamation(r.getType_reclamation());
        //Date datee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getDate_reclamation().toString());
        //re.setDate_reclamation(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getDate_reclamation()));
        re.setEtat(r.isEtat());
        re.setIdreclamation(r.getIdreclamation());
         System.out.println("rrrr"+re);
    }

    @FXML
    private void changeretat(ActionEvent event) throws SQLException {
        re.setEtat(etat.isSelected());
        System.out.println("re"+re);
        rs.modifier(re);
    }
    
    
}

