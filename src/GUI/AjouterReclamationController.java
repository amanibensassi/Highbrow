/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Reclamation;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.ReclamationService;
import services.UserConn;
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
    private ChoiceBox<TypeReclamation> cbrecl;
    @FXML
    private TextArea rec;
    ReclamationService rs = new ReclamationService();
    Reclamation r = new Reclamation();
    @FXML
    private Button btnajouter;
    private int ids;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void setIdSiege(int id) {
        ids = id;
    }

    public void refreshPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherReclamationUtilisateur.fxml"));
        Parent root2 = loader1.load();
        AfficherReclamationUtilisateurController controller = loader1.getController();
        controller.initialize(null, null);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));

        rec.getScene().setRoot(borderPane);
    }

    @FXML
    private void ajouter(ActionEvent event) throws SQLException, ParseException, IOException, AWTException {
        r.setId_utilisateur(UserConn.idutilisateur);
        r.setType_reclamation(TypeReclamation.administrateur);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(dtf.format(now));
        r.setDate_reclamation(date);
        r.setEtat(false);
        r.setId_siege(ids);
        r.setCorps(rec.getText());
        if (ids == 0) {
            rs.ajouterReclamationAdmin(r);
        } else {
            rs.ajouterReclamationSiege(r);
        }
          Stage stage = (Stage) btnajouter.getScene().getWindow();
        stage.close();

        // Mettre à jour la liste des réclamations dans la fenêtre principale
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReclamationUtilisateur.fxml"));
        Parent root = loader.load();
        AfficherReclamationUtilisateurController controller = loader.getController();
        controller.initialize(null, null);
        if (SystemTray.isSupported()) {
//         String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_down.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
             String imagePath = "/media/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString()); 
            BufferedImage awtImage = SwingFXUtils.fromFXImage(img1, null);
        TrayIcon trayIcon = new TrayIcon(awtImage, "Notification Title");
        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
            trayIcon.displayMessage("Notification Message", "Notification Content", MessageType.INFO);
        } catch (AWTException e) {
            System.err.println("Could not add TrayIcon to SystemTray");}
        
       } else {
        System.err.println("SystemTray is not supported");
      

    }

}}
