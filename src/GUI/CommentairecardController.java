/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.CommentaireService;
import services.OtherServices;
import services.ReponseService;
import services.UserConn;
import services.UserService;

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
    CommentaireService cs = new CommentaireService();
        UserService us = new UserService();

     OtherServices os = new OtherServices();
    @FXML
    private ImageView id_trash;
    @FXML
    private ImageView imageviewtobeoff;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     public void setdesign(Commentaire c) {
     this.comment=c;
      try{
     username_id.setText(us.recupererById(c.getId_utilisateur()).getNom()+" "+us.recupererById(c.getId_utilisateur()).getPrenom());
     dateid.setText(os.DateFilter(c.getDate_commentaire()));
     publicationid.setText(c.getCommentaire());
    nbr_reponse.setText(String.valueOf(rs.countCommentaire(c.getIdcommentaire())));
    
    if(c.getId_utilisateur()==UserConn.idutilisateur){
            imageviewtobeoff.setVisible(true);
            modifiercommentaire_id.setVisible(true);
            id_trash.setVisible(true);
            }
     }catch (SQLException ex){System.out.println("setting error in comment design card");}
    }

    @FXML
    private void addcomment(ActionEvent event) {
           try{
               
               FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_reponse.fxml"));
            Parent root2 = loader1.load();
              Interface_reponseController controller = loader1.getController();
            controller.dynamicinitialize(comment);
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            publicationid.getScene().setRoot(borderPane); 
               
               
               
//                    Interface_reponseController cc = new Interface_reponseController();
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_reponse.fxml"));
//                    Parent root = loader.load();
//                    cc = loader.getController();
//                    cc.dynamicinitialize(comment);
//                    publicationid.getScene().setRoot(root);
                    
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
      
       comment= cs.recupererParIdCommentaire(comment.getIdcommentaire());
//     username_id.setId(c.getIdcommentaire();
     dateid.setText(comment.getDate_commentaire().toString());
     publicationid.setText(comment.getCommentaire());
    } catch (IOException | SQLException ex) {
        System.out.println("Erreur modification de la publication");
    }
        
        
    }

    @FXML
    private void deletemethod(MouseEvent event) {
        try{
            cs.supprimer(comment);
              FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
            Parent root2 = loader1.load();
            Interface_commentairesController controller = loader1.getController();
            controller.dynamicinitialize(comment.getId_publication());
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            publicationid.getScene().setRoot(borderPane); 
        } catch (SQLException | IOException ex) {
        System.out.println("Erreur modification de la publication");
    }
    }
    
}
