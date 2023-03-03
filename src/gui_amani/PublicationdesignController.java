/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui_amani;

import entities.Commentaire;
import entities.Publication;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import services.CommentaireService;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class PublicationdesignController implements Initializable {

    @FXML
    private Label dateid;
    @FXML
    private Label publicationid;
    Date d = new Date();
    PublicationService ps = new PublicationService();
    CommentaireService cs = new CommentaireService();
    Publication pub = new Publication();
    Commentaire com = new Commentaire();
    @FXML
    private Label id_likes;
    @FXML
    private Label id_dislikes;
    @FXML
    private Label username_id;
    @FXML
    private Button dislike_button;
    @FXML
    private Label nbr_commentaire;
    @FXML
    private Button like_button;
    @FXML
    private Button modifierpublication_id;
    @FXML
    private Button addcomment;
    @FXML
    private ImageView like_image;
    @FXML
    private ImageView dislike_image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setdesign(Publication p) {

//     username_id.setText(p.getId_utilisateur());
        dateid.setText(p.getDate_publication().toString());
        publicationid.setText(p.getPublication());
        try {
            this.pub = ps.recupererParUtilisateurDate(p);
            this.com.setId_publication(pub.getIdpublication());
            /**********************session id*************/
            this.com.setId_utilisateur(1);
            
            
            this.id_likes.setText(String.valueOf(cs.countLikes(p.getIdpublication())));
            this.id_dislikes.setText(String.valueOf(cs.countDislikes(p.getIdpublication())));
            nbr_commentaire.setText(String.valueOf(cs.countCommentaire(com.getId_publication())));
            
            
                        /*********************session id********************/
//             if ((com.getId_utilisateur()==1)&&(cs.recupererInteractionUser(com).getNbr_like()==true)){
//                 
//            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_up.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
//            this.like_image.setImage(img1);
//            this.like_button.setDisable(true);
//           
//           }else if((cs.recupererInteractionUser(com).getId_utilisateur()==1)&&(cs.recupererInteractionUser(com).getNbr_dislike()==true)){
//               
//            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_down.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
//            this.dislike_image.setImage(img1);
//            this.dislike_button.setDisable(true);
//            
//           }
            
        } catch (SQLException ex) {
            System.out.println("erreur d'initialisation de la publication");
            
        }
       

    }

    @FXML
    private void addlike(ActionEvent event) {
        try {
            
            
            //image button change & button disable
            
            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_up.png";
            File file = new File(img);
            Image img1 = new Image(file.toURI().toString());
            this.like_image.setImage(img1);
            this.like_button.setDisable(true);
            
            
            // database connexion
            cs.ajouterLike(com);
            Commentaire c = cs.recupererInteractionUser(com);
            id_likes.setText(String.valueOf(cs.countLikes(com.getId_publication())));
            
        } catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void adddislike(ActionEvent event) {
        try {
            
            
            // interface part
            
            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_down.png";
            File file = new File(img);
            Image img1 = new Image(file.toURI().toString());
            this.dislike_image.setImage(img1);
            this.dislike_button.setDisable(true);
            

            //data base part
            
            cs.ajouterDislike(com);
            Commentaire c = cs.recupererInteractionUser(com);
            id_dislikes.setText(String.valueOf(cs.countDislikes(com.getId_publication())));
          
        } catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void addcomment(ActionEvent event) {
        try {
            System.out.println("enty cliquit aal commentaire w aandek "+pub);
            Interface_commentairesController cc = new Interface_commentairesController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
            Parent root = loader.load();
            cc = loader.getController();
            cc.dynamicinitialize(pub);
            publicationid.getScene().setRoot(root);

        } catch (IOException ex) {
            System.out.println("no entry to the dialog pane");
        }
//            Parent root = loader.load();
//            Afficher_publicationController controller = loader.getController();
//            idmodifypub.getScene().setRoot(root);
    }

    @FXML
    private void modifierPublication(ActionEvent event) {
        try {
            Dialog<Void> dialog = new Dialog<>();
            dialog.setTitle("Modifier la publication");
            this.pub = ps.recupererParUtilisateurDate(pub);
            ModifypublicationController mod = new ModifypublicationController();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifypublication.fxml"));
            Node pane = loader.load();
            mod = loader.getController();
            mod.initialize(pub, dialog, pane);

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
            pub = ps.recupererParId(pub.getIdpublication());
            System.out.println("after modif + recupérer" + pub);
            dateid.setText(pub.getDate_publication().toString());
            publicationid.setText(pub.getPublication());

        } catch (IOException | SQLException ex) {
            System.out.println("Erreur modification de la publication");
        }
    }

}
