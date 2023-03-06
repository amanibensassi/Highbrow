/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
import javafx.geometry.Insets;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.CommentaireService;
import services.OtherServices;
import services.PublicationService;
import services.UserConn;
import services.UserService;

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
    OtherServices os = new OtherServices();
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
    @FXML
    private ImageView id_trash;
    UserService us = new UserService();
    @FXML
    private ImageView imageviewtobe_off;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setdesign(Publication p) throws SQLException {

        username_id.setText(us.recupererById(com.getId_utilisateur()).getPrenom()+" "+us.recupererById(com.getId_utilisateur()).getNom());
        dateid.setText(os.DateFilter(p.getDate_publication()));
        publicationid.setText(p.getPublication());
        try {
            this.pub = p;
            this.com.setId_publication(pub.getIdpublication());
            com.setId_utilisateur(UserConn.idutilisateur);
            /**
             * ********************session id setting card infooo***********************
             */
            this.com.setId_utilisateur(UserConn.idutilisateur);
            username_id.setText(us.recupererById(pub.getId_utilisateur()).getNom()+" "+us.recupererById(pub.getId_utilisateur()).getPrenom());
            this.id_likes.setText(String.valueOf(cs.countLikes(p.getIdpublication())));
            this.id_dislikes.setText(String.valueOf(cs.countDislikes(p.getIdpublication())));
            nbr_commentaire.setText(String.valueOf(cs.countCommentaire(com.getId_publication())));

            /**
             * *******************session id*******************
             */
            if(pub.getId_utilisateur()==UserConn.idutilisateur){
                imageviewtobe_off.setVisible(true);
            modifierpublication_id.setVisible(true);
            id_trash.setVisible(true);
            }
            if (cs.recupererInteractionUser(com).getNbr_like()== true){
            String imagePath = "/media/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString());    
          
            this.like_image.setImage(img1);
            this.like_button.setDisable(true);
            this.dislike_button.setDisable(true);
           
           }
            if(cs.recupererInteractionUser(com).getNbr_dislike()==true){
            String imagePath = "/media/full_down.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString());    
            this.dislike_image.setImage(img1);
            this.dislike_button.setDisable(true);
            this.like_button.setDisable(true); 
           }
        } catch (SQLException ex) {
            System.out.println("erreur d'initialisation de la publication");

        }

    }

    @FXML
    private void addlike(ActionEvent event) {
        try {

            //image button change & button disable
            String imagePath = "/media/full_up.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString());
            this.like_image.setImage(img1);
            this.like_button.setDisable(true);
            com.setId_utilisateur(UserConn.idutilisateur);
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
            String imagePath = "/media/full_down.png";
            URL imageURL = getClass().getResource(imagePath);
            Image img1 = new Image(imageURL.toString());

            // interface part
            this.dislike_image.setImage(img1);
            this.dislike_button.setDisable(true);
            com.setId_utilisateur(UserConn.idutilisateur);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
            Parent root2 = loader1.load();
            Interface_commentairesController controller = loader1.getController();
            controller.dynamicinitialize(pub.getIdpublication());

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            publicationid.getScene().setRoot(borderPane);

//            Interface_commentairesController cc = new Interface_commentairesController();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
//            Parent root = loader.load();
//            cc = loader.getController();
//            cc.dynamicinitialize(pub);
//            publicationid.getScene().setRoot(root);
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

    @FXML
    private void deletemethod(MouseEvent event) {
        try {
            ps.supprimer(pub);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            SideBarUserController cc = loader.getController();
            cc.setRole(UserConn.role.toString());
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_forum.fxml"));
            Parent root2 = loader1.load();

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            id_trash.getScene().setRoot(borderPane);
        } catch (SQLException ex) {
            System.out.println("Erreur de suppression de la publication");
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface");
        }
    }

}
