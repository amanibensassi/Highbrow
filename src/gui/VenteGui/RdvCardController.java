/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.VenteGui;

import entities.Vente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */

public class RdvCardController implements Initializable {

    @FXML
    private Label iduser;
    @FXML
    private Label idveh;
    @FXML
    private Label date_rendez_vous;
    @FXML
    private Button detail;
    @FXML
    private Button delete;
    
   Vente ve = new Vente();
   VenteService vs = new VenteService();
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setRdv(Vente v) throws FileNotFoundException, ParseException {
                 iduser.setText(String.valueOf(v.getId_utilisateur()));
                 idveh.setText(String.valueOf(v.getId_vehicule()));  
         ve=v ;
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
         String dateString=sdf.format(v.getDate_rendez_vous());
         date_rendez_vous.setText(dateString); 

    }

    @FXML
    private void Rdvdetail(ActionEvent event) {
        try {
                                        System.out.println(ve);
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRendezVous.fxml"));
                                        Parent root = loader.load();
                                        ModifierRendezVousController controller = loader.getController();
                                        controller.setData(ve);
                                        date_rendez_vous.getScene().setRoot(root);     
                                    } catch (IOException ex) {
                                        System.out.println("error1" + ex.getMessage());
                                    }
        
        
    }

    @FXML
    private void SupprimerRdv(ActionEvent event) throws IOException {
        try{
           vs.supprimer(ve);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
           Parent root = loader.load();
           delete.getScene().setRoot(root);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
        
    }
    

