/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import entities.Reponse;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
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
public class ReponsecardController implements Initializable {

    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label reponseid;
    Reponse rep = new Reponse();
    ReponseService rs = new ReponseService();
    CommentaireService cs = new CommentaireService();
    OtherServices os = new OtherServices();
    @FXML
    private ImageView id_trash;
    UserService us = new UserService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setdesign(Reponse c) throws SQLException {
        this.rep = c;
        username_id.setText(us.recupererById(rep.getId_utilisateur()).getPrenom() + " " + us.recupererById(rep.getId_utilisateur()).getNom());
        dateid.setText(os.DateFilter(c.getDate_reponse()));
        reponseid.setText(c.getReponse());
        if (rep.getId_utilisateur() == UserConn.idutilisateur) {
            id_trash.setVisible(true);
        }
    }

    @FXML
    private void deletemethod(MouseEvent event) {
        try {
            System.out.println("reppp" + rep);
            rs.supprimer(rep);
            Commentaire c = cs.recupererParIdCommentaire(rep.getId_commentaire());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("interface_reponse.fxml"));
            Parent root2 = loader1.load();
            Interface_reponseController controller = loader1.getController();

            controller.dynamicinitialize(c);

            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            id_trash.getScene().setRoot(borderPane);

        } catch (IOException | SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

}
