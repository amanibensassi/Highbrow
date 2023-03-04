/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Interface_forumController;
import entities.Commentaire;
import entities.Publication;
import java.io.File;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import services.CommentaireService;
import services.PublicationService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
    public class Interface_commentairesController implements Initializable {

    @FXML
    private AnchorPane anchor_id;
    @FXML
    private TextField txtf_partagerpub1;
    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label publicationid;
    @FXML
    private Button like_button;
    @FXML
    private Button dislike_button;
    @FXML
    private VBox vboxcommentid;
    @FXML
    private Button ajoutercommentaire;
    @FXML
    private ScrollPane scrollbarid;
    CommentaireService cs = new CommentaireService();
    Commentaire c = new Commentaire();
    Publication pp = new Publication();
    PublicationService ps = new PublicationService();
    Date d = new Date();
    @FXML
    private Label id_like;
    @FXML
    private Label id_dislike;
    @FXML
    private Label nbr_commentaire;
    @FXML
    private Button backbutton_id;
    @FXML
    private ImageView like_img;
    @FXML
    private ImageView dislike_img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    }  
    
    public void dynamicinitialize(Publication p) {
          try{
            this.txtf_partagerpub1.setCursor(Cursor.TEXT);
            this.dateid.setText(p.getDate_publication().toString());
            this.publicationid.setText(p.getPublication());
            id_like.setText(String.valueOf(cs.countLikes(p.getIdpublication())));
            id_dislike.setText(String.valueOf(cs.countDislikes(p.getIdpublication())));
            nbr_commentaire.setText(String.valueOf(cs.countCommentaire(p.getIdpublication())));

            pp =p;
          
         List<Commentaire> pub = cs.recupererParpublication(pp.getIdpublication());
            if (pub.isEmpty())
                {System.out.println("no publication found");}
            else {
                for (Commentaire com : pub) {
                    if (com.getCommentaire().equals("")){}
                    else{
                    CommentairecardController  cc = new CommentairecardController ();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("commentairecard.fxml"));
                    Node card = loader.load();
                    cc = loader.getController();
                    cc.setdesign(com);
                    vboxcommentid.getChildren().add(card); 
                     scrollbarid.setContent(vboxcommentid);     
                      }
                    
                    
                        /*********************session id********************/
//     if ((com.getId_utilisateur()==1)&&(cs.recupererInteractionUser(com).getNbr_like()==true)){
//                 
//            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_up.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
//            this.like_img.setImage(img1);
//            this.like_button.setDisable(true);
//           
//           }else if((cs.recupererInteractionUser(com).getId_utilisateur()==1)&&(cs.recupererInteractionUser(com).getNbr_dislike()==true)){
//               
//            String img = "C:\\xampp\\htdocs\\pidev\\Highbrow\\src\\media\\full_down.png";
//            File file = new File(img);
//            Image img1 = new Image(file.toURI().toString());
//            this.dislike_img.setImage(img1);
//            this.dislike_button.setDisable(true);
//            
//           }
                   
                }

            }
        }catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
         } catch (IOException ex) {
            Logger.getLogger("error"+ex.getMessage());
        }
    }  
    
    


    @FXML
    private void addLike(ActionEvent event) {
         try{
        String img= "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\media\\full_up.png";
        File file = new File(img);
        Image img1= new Image(file.toURI().toString());
        this.like_img.setImage(img1);
        this.like_button.setDisable(true);
        
        
        Commentaire com = new Commentaire();
        c.setId_utilisateur(1);
        c.setId_publication(pp.getIdpublication());
        cs.ajouterLike(c);
        id_like.setText(String.valueOf(cs.countLikes(c.getId_publication())));
             System.out.println("avant récupération"+com);
              c = cs.recupererInteractionUser(com);
             System.out.println(c);
       
        
        }catch (SQLException ex)
        {System.out.println("erreur modification de la publication");}
    }

    @FXML
    private void addDislike(ActionEvent event) {
            try{
        String img= "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\media\\full_down.png";	
        File file = new File(img);
        Image img1= new Image(file.toURI().toString());
         this.dislike_img.setImage(img1);
        this.dislike_button.setDisable(true);
        
        
        System.out.println("user session");
        c.setId_utilisateur(1);
        c.setId_publication(pp.getIdpublication());
        cs.ajouterDislike(c);
        id_dislike.setText(String.valueOf(cs.countDislikes(c.getId_publication())));
       
        }catch (SQLException ex)
        {System.out.println("erreur modification de la publication");}
    }
    
    
    
    
    
    
    

    @FXML
    private void ajoutercommentaire(ActionEvent event) {
        System.out.println("clickkk ");
            if (txtf_partagerpub1.getText().isEmpty()){
                txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: #E31937  ; ");
            }else{
          try {
            //adding to the data base
            c.setCommentaire(txtf_partagerpub1.getText());
            c.setDate_commentaire(d);
            c.setId_publication(pp.getIdpublication());
            c.setId_utilisateur(1);
              System.out.println("the user");
            cs.ajouter(c);
              System.out.println("done");
            txtf_partagerpub1.setText(null);
            txtf_partagerpub1.setStyle("-fx-border-radius: 15 ; -fx-border-color: transparent ;  -fx-background-radius: 15 ; ");
           //  pp =ps.recupererParUtilisateurDate(pp);
            //adding the card to the vbox
            
                    CommentairecardController  cc = new CommentairecardController ();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("commentairecard.fxml"));
                    Node card = loader.load();
                    cc = loader.getController();
                    cc.setdesign(c);
                    vboxcommentid.getChildren().add(0, card);
                    scrollbarid.setContent(vboxcommentid);
            nbr_commentaire.setText(String.valueOf(cs.countCommentaire(pp.getIdpublication())));
//            PublicationdesignController pd = new PublicationdesignController();
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("publicationdesign.fxml"));
//            Node card = loader.load();
//            pd = loader.getController();
//            pd.setdesign(p);
//            vboxcommentid.getChildren().add(card);
                 
        }catch (IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }}
    }

    @FXML
    private void backbutton(ActionEvent event) {
         try{
             
              FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_forum.fxml"));
            Parent root2 = loader1.load();
//              Interface_forumController controller = loader1.getController();
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            nbr_commentaire.getScene().setRoot(borderPane); 
             
             
//        Interface_forumController cc = new Interface_forumController();
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("interface_forum.fxml"));
//                    Parent root = loader.load();
//                    cc = loader.getController();
//                    this.nbr_commentaire.getScene().setRoot(root);
        }catch (IOException ex) {
            Logger.getLogger("error"+ex.getMessage());
        }  
    }
    
}
