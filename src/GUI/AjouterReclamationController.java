/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import services.ReclamationService;
import typeenumeration.TypeReclamation;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AjouterReclamationController implements Initializable {

    /*
    
    private int idreclamation,id_utilisateur,id_siege;
    private TypeReclamation type_reclamation;
    private Date date_reclamation;
    private boolean etat;
    private String corps;
    
    */
    
    @FXML
    private ChoiceBox<TypeReclamation> cbrecl;
    @FXML
    private TextArea rec;
    ReclamationService rs = new ReclamationService();
    Reclamation r = new Reclamation();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TypeReclamation tab_type[]={TypeReclamation.siege,TypeReclamation.administrateur};
        cbrecl.getItems().addAll(tab_type);
    }    

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, ParseException {
        if (cbrecl.getValue().toString().equals("administrateur")){
            r.setId_utilisateur(1);
            r.setType_reclamation(TypeReclamation.administrateur);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dtf.format(now));
            r.setDate_reclamation(date);
            r.setEtat(false);
            r.setCorps(rec.getText());
            rs.ajouterReclamationAdmin(r);
        }
        else if (cbrecl.getValue().toString().equals("siege")){
            r.setId_utilisateur(1);
            r.setId_siege(2);
            r.setType_reclamation(TypeReclamation.siege);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  
            LocalDateTime now = LocalDateTime.now(); 
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dtf.format(now));
            r.setDate_reclamation(date);
            r.setEtat(false);
            r.setCorps(rec.getText());
            rs.ajouterReclamationSiege(r);
        }
    }
    
}
