/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherDetailMecanicienController;
import entities.Mecanicien;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.MecanicienService;
import services.UserConn;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class MecanicienController implements Initializable{

    @FXML
    private Label nomPrenomLabel;
    @FXML
    private Label ageLabel;
    @FXML
    private AnchorPane mec;
    
    Mecanicien me = new Mecanicien();
    
    MecanicienService ms=new MecanicienService();
    @FXML
    private Button btnmodifier;
    @FXML
    private Button btnsupprimer;
    @FXML
    private ImageView img;
    //String role="user";
    String role="Administrateur";
    private int id;
    @FXML
    private Label lblspec;
    @FXML
    private ImageView update;
    @FXML
    private ImageView delete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }   
    
         
    public void setIdVehicule(int id_v){
        this.id=id_v;
       
        
    }
    
    public void setPersonne(Mecanicien m) {
         if (UserConn.role.toString().equals("proprietaire_agence")){
            btnmodifier.setVisible(false);
            btnsupprimer.setVisible(false);
            update.setVisible(false);
            delete.setVisible(false);
        }
        nomPrenomLabel.setText(m.getNom_mecanicien());
        //ageLabel.setText(String.valueOf(m.getContact()) );
        ageLabel.setText(m.getPrenom_mecanicien()); 
       // System.out.println(m.getImage());
       lblspec.setText(m.getSpecialite().toString());
        File file = new File(m.getImage());
        Image imagee = new Image(file.toURI().toString());
        img.setImage(imagee);
            me.setIdmecanicien(m.getIdmecanicien());
            me.setNom_mecanicien(m.getNom_mecanicien());
            me.setPrenom_mecanicien(m.getPrenom_mecanicien());
            me.setAdresse(m.getAdresse());
            me.setContact(m.getContact());
            me.setRegion(m.getRegion());
            me.setSpecialite(m.getSpecialite());
            me.setImage(m.getImage());
            //System.out.println("Envoyer image"+m.getImage());
    }

    @FXML
    private void detailMeca(MouseEvent event) {
                                try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                                        Parent root1 = loader.load();
                                        BorderPane borderPane = new BorderPane();
                                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherDetailMecanicien.fxml"));
                                        Parent root2 = loader1.load();
                                        AfficherDetailMecanicienController controller = loader1.getController();
                                        controller.setData(me);
                                        controller.setIdVehicule(id);
                                        HBox hbox = new HBox(root1, new Pane(), root2);
                                        hbox.setSpacing(20);

                                        borderPane.setRight(hbox);

                                        borderPane.setLeft(root1);

                                        borderPane.setPadding(new Insets(10, 10, 30, 10));
                                        mec.getScene().setRoot(borderPane);  
                                    } catch (IOException ex) {
                                        System.out.println("error1" + ex.getMessage());
                                    }
        
    }

    @FXML
    private void modifier(ActionEvent event) {
        System.out.println("click");
        try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                                        Parent root1 = loader.load();
                                        BorderPane borderPane = new BorderPane();
                                        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ModifierMecanicien.fxml"));
                                        Parent root2 = loader1.load();
                                        ModifierMecanicienController controller = loader1.getController();
                                        controller.setData(me);
                                        HBox hbox = new HBox(root1, new Pane(), root2);
                                        hbox.setSpacing(20);

                                        borderPane.setRight(hbox);

                                        borderPane.setLeft(root1);

                                        borderPane.setPadding(new Insets(10, 10, 30, 10));
                                        mec.getScene().setRoot(borderPane);  
                                        ///
                                        
                                    } catch (IOException ex) {
                                        System.out.println("error" + ex.getMessage());
                                    }
        
    }

    @FXML
    private void supprimer(ActionEvent event) throws IOException{
        try{
           ms.supprimer(me);
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root2 = loader1.load();
        AfficherMecaniciensProfController fc = loader1.getController();
        fc.setIdVehicule(id);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        mec.getScene().setRoot(borderPane);
        }
        catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
   
    
}
