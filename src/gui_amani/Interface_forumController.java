/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_amani;

import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class Interface_forumController implements Initializable {

    @FXML
    private TextField txtf_partagerpub1;
    @FXML
    private Button ajouterpublication;
            Date date = new Date();
            PublicationService ps = new PublicationService();
            Publication p = new Publication();
    @FXML
    private AnchorPane anchor_id;
    @FXML
    private VBox vbox_id;
    @FXML
    private Pane scrollbar_id;
    @FXML
    private ScrollPane scrollPaneId;
    @FXML
    private Button id_button;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.txtf_partagerpub1.setCursor(Cursor.TEXT);
        //show the data base
        try{
         List<Publication> pub = ps.recuperer();
            if (pub.isEmpty())
                {System.out.println("no publication found");}
            else {
                for (Publication pub1 : pub) {
                    PublicationdesignController pd = new PublicationdesignController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("publicationdesign.fxml"));
                    Node card = loader.load();
                    pd = loader.getController();
                    pd.setdesign(pub1);
                    vbox_id.getChildren().add(card);
                    scrollPaneId.setContent(vbox_id);
                    
                }}

        }catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
         } catch (IOException ex) {
            Logger.getLogger(Interface_forumController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }    
    
    // création d'une autre initialise method does not load the data from the data base!!
    

    @FXML
    private void ajouterpublication(ActionEvent event) {
      
            if (txtf_partagerpub1.getText().isEmpty()){
                txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: #E31937  ; ");
            }else{
             try {
            //adding to the data base
            
            p.setPublication(txtf_partagerpub1.getText());
            p.setDate_publication(date);
            p.setId_utilisateur(1);
            ps.ajouter(p);
            
            txtf_partagerpub1.setText(null);
             txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: transparent ;  -fx-background-radius: 15 ; ");
            //adding the card to the vbox
            
            PublicationdesignController pd = new PublicationdesignController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("publicationdesign.fxml"));
            Node card = loader.load();
            pd = loader.getController();
            pd.setdesign(p);
            vbox_id.getChildren().add(0, card);
            vbox_id.blendModeProperty();
            scrollPaneId.setContent(vbox_id);
            
                     
        }catch (IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }} 
}

    @FXML
    private void generatePDF(ActionEvent event) {
        System.out.println("from here i will load the pdf file");
    }
}