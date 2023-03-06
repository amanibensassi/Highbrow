/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.twilio.rest.preview.sync.service.Document;
import com.twilio.twiml.fax.Receive.PageSize;
import entities.Publication;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.OtherServices;
import services.PublicationService;
import services.UserConn;

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
            OtherServices os = new OtherServices();
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
    @FXML
    private RadioButton trietendance;
    @FXML
    private RadioButton triedate;
    @FXML
    private Button button_filtreiid;
    List<Publication> pub = new ArrayList();
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.txtf_partagerpub1.setCursor(Cursor.TEXT);
        //show the data base
        try{
         pub = ps.recuperer();
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
    private void ajouterpublication(ActionEvent event) throws AWTException {
      
            if (txtf_partagerpub1.getText().isEmpty()){
                txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: #E31937  ; ");
            }else{
             try {
            //adding to the data base
            
            p.setPublication(txtf_partagerpub1.getText());
            p.setDate_publication(date);
            p.setId_utilisateur(UserConn.idutilisateur);
            ps.ajouter(p);
            pub= ps.recuperer();
            p= pub.get(pub.size()-1);
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
            
            
              if (SystemTray.isSupported()) {

                    String imagePath = "/media/full_down.png";
                    URL imageURL = getClass().getResource(imagePath);
                    Image img1 = new Image(imageURL.toString());
                    BufferedImage awtImage = SwingFXUtils.fromFXImage(img1, null);

                    TrayIcon trayIcon = new TrayIcon(awtImage, "Publication ajoutée");
                    SystemTray tray = SystemTray.getSystemTray();
                    try {
                        tray.add(trayIcon);
                        trayIcon.displayMessage("Votre publication a été ajouté avec succés", "Votre publication à été ajouté le " + p.getDate_publication().getHours()+":"+p.getDate_publication().getMinutes(), TrayIcon.MessageType.INFO);
                    } catch (AWTException e) {
                        System.err.println("Could not add TrayIcon to SystemTray");
                    }

                } else {
                    System.err.println("SystemTray is not supported");
                }

                     
        }catch (IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }} 
}

    @FXML
    private void generatePDF(ActionEvent event) throws IOException, SQLException {
         pub = os.sortByPopularity(pub);
         p=pub.get(0);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("pdfGeneration.fxml"));
                    Node card = loader.load();
                   PdfGenerationController pd = loader.getController();
                    pd.telecharger(p);
       
    }

    @FXML
    private void filtrer(ActionEvent event) {
       try{ 
           if (trietendance.isSelected()) {
        triedate.setSelected(false);
        pub = os.sortByPopularity(pub);
    } else if (triedate.isSelected()) { // Tri par ordre croissant si la checkbox "trieCroissant" est cochée
        pub = ps.recuperer();
       Collections.reverse(pub);
//         vehiculesByEtat.stream().filter((v) -> (v.getNbr_place() == n)).forEachOrdered((v) -> {
//            vehiculesByEtatAndPlace.add(v);
        trietendance.setSelected(false);
    }}catch ( SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
       try{ 
           vbox_id.getChildren().clear();
         for (Publication pub1 : pub) {
                    PublicationdesignController pd = new PublicationdesignController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("publicationdesign.fxml"));
                    Node card = loader.load();
                    pd = loader.getController();
                    pd.setdesign(pub1);
                    vbox_id.getChildren().add(card);
                    scrollPaneId.setContent(vbox_id);
                    
                }}catch ( IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }

    @FXML
    private void trietendanceid(ActionEvent event) {
        triedate.setSelected(false);
    }

    @FXML
    private void triedateid(ActionEvent event) {
         trietendance.setSelected(false);
    }
}