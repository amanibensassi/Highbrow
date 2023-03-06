/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Interface_commentairesController;
import entities.Commentaire;
import entities.Publication;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.CommentaireService;
import services.OtherServices;
import services.PublicationService;
import services.ReponseService;
import services.UserConn;
import services.UserService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class Interface_reponseController implements Initializable {

    @FXML
    private AnchorPane anchor_id;
    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label commentaireid;
    @FXML
    private ScrollPane scrollbarid;
    @FXML
    private VBox vboxcommentid;
    @FXML
    private TextField txtf_partagerpub1;
    @FXML
    private Button ajoutercommentair;
    @FXML
    private Label nbr_reponse;
      Reponse rep = new Reponse();
      Commentaire com = new Commentaire();
    ReponseService rs = new ReponseService();
    CommentaireService cs = new CommentaireService();
    Date d = new Date();
    @FXML
    private Label nbrcomment_id;
    @FXML
    private Button backButton_id;
    OtherServices os = new OtherServices();
     UserService us = new UserService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
    public void dynamicinitialize(Commentaire p) {
       
      
        try{
             com=p;
            this.txtf_partagerpub1.setCursor(Cursor.TEXT);
            // add the user accordinally to the publisher!!
            this.username_id.setText(us.recupererById(com.getId_utilisateur()).getPrenom() + " " + us.recupererById(com.getId_utilisateur()).getNom());
            this.dateid.setText( os.DateFilter(p.getDate_commentaire()));
            this.commentaireid.setText(p.getCommentaire());
            this.nbr_reponse.setText(String.valueOf(rs.countCommentaire(com.getIdcommentaire())));
//            com =cs.recupererParUtilisateurDate(p);
            System.out.println(com);
         List<Reponse> reponse = rs.recupererParCommentaire(com.getIdcommentaire());
            if (reponse.isEmpty())
                {System.out.println("no responses found");}
            else {
                for (Reponse com : reponse) {
                    ReponsecardController  cc = new ReponsecardController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("reponsecard.fxml"));
                    Node card = loader.load();
                    cc = loader.getController();
                    cc.setdesign(com);
                    vboxcommentid.getChildren().add(card); 
                    scrollbarid.setContent(vboxcommentid);
                }

            }
        }catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
         } catch (IOException ex) {
            Logger.getLogger("error"+ex.getMessage());
        }
    }  
    

    @FXML
    private void ajouterReponse(ActionEvent event) {
          if (txtf_partagerpub1.getText().isEmpty()){
                txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: #E31937  ; ");
            }else{ 
        
        try {
            //adding to the data base
            
            rep.setReponse(txtf_partagerpub1.getText());
            rep.setId_commentaire(com.getIdcommentaire());
            rep.setDate_reponse(d);
            rep.setId_utilisateur(UserConn.idutilisateur);
            rs.ajouter(rep);
            List<Reponse> reponse = rs.recuperer();
            rep= reponse.get(reponse.size()-1);
            txtf_partagerpub1.setText(null);
             txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: transparent ;  -fx-background-radius: 15 ; ");
            this.nbr_reponse.setText(String.valueOf(rs.countCommentaire(com.getIdcommentaire())));
            //adding the card to the vbox
            
                    ReponsecardController  cc = new ReponsecardController();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("reponsecard.fxml"));
                    Node card = loader.load();
                    cc = loader.getController();
                    cc.setdesign(rep);
                    vboxcommentid.getChildren().add(0, card);
                    scrollbarid.setContent(vboxcommentid);
                 
        }catch (IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    }
    @FXML
    private void backButton(ActionEvent event) {
        try{
            
             PublicationService ps = new PublicationService();
           Publication p =   ps.recupererParId(com.getId_publication());
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
            Parent root2 = loader1.load();
              Interface_commentairesController controller = loader1.getController();
          controller.dynamicinitialize(p.getIdpublication());
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            commentaireid.getScene().setRoot(borderPane); 
               
            
            
             
//        Interface_commentairesController cc = new Interface_commentairesController();
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_commentaires.fxml"));
//                    Parent root = loader.load();
//                    cc = loader.getController();
//                    cc.dynamicinitialize(p);
//                    //you need to reload publication
//                    commentaireid.getScene().setRoot(root);
        }catch (IOException | SQLException ex) {
            Logger.getLogger("error"+ex.getMessage());
        }     
    }
    }

    
    
    

