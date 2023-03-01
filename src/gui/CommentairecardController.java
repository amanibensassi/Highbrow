/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import services.ReponseService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class CommentairecardController implements Initializable {

    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label publicationid;
    @FXML
    private Label nbr_reponse;
    Commentaire comment= new Commentaire();
    @FXML
    private Button commentbutton;
    @FXML
    private Button modifiercommentaire_id;
    ReponseService rs = new ReponseService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setdesign(Commentaire c){
     this.comment=c;
//     username_id.setId(c.getIdcommentaire();
     dateid.setText(c.getDate_commentaire().toString());
     publicationid.setText(c.getCommentaire());
     try{
    nbr_reponse.setText(String.valueOf(rs.countCommentaire(c.getIdcommentaire())));
     }catch (SQLException ex){System.out.println("setting error in comment design card");}
    }

    @FXML
    private void addcomment(ActionEvent event) {
        System.out.println("not supported");
           try{
                    Interface_reponseController cc = new Interface_reponseController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_reponse.fxml"));
                    Parent root = loader.load();
                    cc = loader.getController();
                    cc.dynamicinitialize(comment);
                    publicationid.getScene().setRoot(root);
                    
        }catch (IOException ex)
        {System.out.println("erreur modification de la publication");}
        
        
    }

    @FXML
    private void modifierCommentaire(ActionEvent event) {
         try {
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Modifier la publication");

        ModifycommentaireController mod = new ModifycommentaireController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifycommentaire.fxml"));
        Node pane = loader.load();
        mod = loader.getController();
        mod.initialize(comment, dialog, pane);

        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.setContent(pane);
                

       
                    // les bouttons: necessaires walla il crash
                    ButtonType okButtonType = new ButtonType("", ButtonBar.ButtonData.OK_DONE);
                    ButtonType cancelButtonType = new ButtonType("", ButtonBar.ButtonData.CANCEL_CLOSE);
                    dialogPane.getButtonTypes().addAll(okButtonType, cancelButtonType);
                    // hiding the buttons
                    Button okButton = (Button) dialogPane.lookupButton(okButtonType);
                    okButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");
                    Button cancelButton = (Button) dialogPane.lookupButton(cancelButtonType);
                    cancelButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent;");

        // lezma for l'affichage mtee dialog pane
       dialog.showAndWait();
    } catch (IOException ex) {
        System.out.println("Erreur modification de la publication");
    }
        
        
    }
    
}
