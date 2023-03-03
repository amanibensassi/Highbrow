/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Avis;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import services.AvisService;
import typeenumeration.Note;

/**
 * FXML Controller class
 *
 * @author benha
 */
public class AvisController implements Initializable {

    @FXML
    private Label AVIS;

    Avis ae = new Avis();
    AvisService as = new AvisService();
    @FXML
    private HBox  grid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
     }
public void setAvis(Avis a) throws ParseException {
        //type_rec.setText(r.getType_reclamation().toString());
//        corp.setText(r.getCorps());
//        etat.setSelected(r.isEtat());
//        date.setText(r.getDate_reclamation().toString());
             AVIS.setText(a.getNote().toString());
             ae.setNote(a.getNote());
//        re.setCorps(r.getCorps());
//        re.setType_reclamation(r.getType_reclamation());
//        //Date datee = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getDate_reclamation().toString());
//        //re.setDate_reclamation(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(r.getDate_reclamation()));
//        re.setEtat(r.isEtat());
//        re.setIdreclamation(r.getIdreclamation());
         System.out.println("rrrr"+ae);
    }    
    
}
