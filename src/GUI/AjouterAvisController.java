/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Avis;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.AvisService;
import typeenumeration.Note;
import static typeenumeration.Note.n1;
import static typeenumeration.Note.n2;
import static typeenumeration.Note.n3;
import static typeenumeration.Note.n4;
import static typeenumeration.Note.n5;

/**
 * FXML Controller class
 *
 * @author benha
 */
public class AjouterAvisController implements Initializable {

    AvisService as = new AvisService();
    @FXML
    private ImageView star1;
    @FXML
    private ImageView star2;
    @FXML
    private ImageView star3;
    @FXML
    private ImageView star4;
    @FXML
    private ImageView star5;
    String nom_img;
    String nom_img1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            List<Avis> avis = new ArrayList<>();

            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {

                for (int k = 0; k < 5; k++) {
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file = new File(nom_img);
                    Image image1 = new Image(file.toURI().toString());
                    star1.setImage(image1);
                    star2.setImage(image1);
                    star3.setImage(image1);
                    star4.setImage(image1);
                    star5.setImage(image1);
                }
            } else {
                for (int s = 0; s < avis.size(); s++) {
                    Note n = avis.get(s).getNote();
                    if (n.equals(n1)) {
                        nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                        File file = new File(nom_img1);
                        Image image1 = new Image(file.toURI().toString());
                        nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                        File file1 = new File(nom_img);
                        Image image = new Image(file1.toURI().toString());
                        star1.setImage(image);
                        star2.setImage(image1);
                        star3.setImage(image1);
                        star4.setImage(image1);
                        star5.setImage(image1);

                    }
                    if (n.equals(n2)) {
                        nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                        File file = new File(nom_img1);
                        Image image1 = new Image(file.toURI().toString());
                        nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                        File file1 = new File(nom_img);
                        Image image = new Image(file1.toURI().toString());
                        star1.setImage(image);
                        star2.setImage(image);
                        star3.setImage(image1);
                        star4.setImage(image1);
                        star5.setImage(image1);

                    }
                    if (n.equals(n3)) {
                        nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                        File file = new File(nom_img1);
                        Image image1 = new Image(file.toURI().toString());
                        nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                        File file1 = new File(nom_img);
                        Image image = new Image(file1.toURI().toString());
                        star1.setImage(image);
                        star2.setImage(image);
                        star3.setImage(image);
                        star4.setImage(image1);
                        star5.setImage(image1);

                    }
                    if (n.equals(n4)) {
                        nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                        File file = new File(nom_img1);
                        Image image1 = new Image(file.toURI().toString());
                        nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                        File file1 = new File(nom_img);
                        Image image = new Image(file1.toURI().toString());
                        star1.setImage(image);
                        star2.setImage(image);
                        star3.setImage(image);
                        star4.setImage(image);
                        star5.setImage(image1);

                    }
                    if (n.equals(n5)) {
                        nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                        File file = new File(nom_img1);
                        Image image1 = new Image(file.toURI().toString());
                        nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                        File file1 = new File(nom_img);
                        Image image = new Image(file1.toURI().toString());
                        star1.setImage(image);
                        star2.setImage(image);
                        star3.setImage(image);
                        star4.setImage(image);
                        star5.setImage(image);

                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterAvisController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    @FXML
    private void clickstart1(MouseEvent event) throws IOException {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {
                for (int k = 0; k < 5; k++) {
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file = new File(nom_img);
                    Image image1 = new Image(file.toURI().toString());
                    star1.setImage(image1);
                    star2.setImage(image1);
                    star3.setImage(image1);
                    star4.setImage(image1);
                    star5.setImage(image1);
                }
                Avis a = new Avis();
                a.setNote(n1);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);
                as.ajouterAvisSiege(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                }
                a.setNote(n1);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);

                as.modifier(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart2(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n2);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);
                as.ajouterAvisSiege(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                }
                a.setNote(n2);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);

                as.modifier(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart3(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n3);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);
                as.ajouterAvisSiege(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);;
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                }
                a.setNote(n3);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);

                as.modifier(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart4(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n4);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);
                as.ajouterAvisSiege(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);
                star4.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                }
                a.setNote(n4);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);

                as.modifier(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);
                star4.setImage(image);
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart5(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisSiege(2, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n5);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);
                as.ajouterAvisSiege(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);
                star4.setImage(image);
                star5.setImage(image);
                star5.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                }
                a.setNote(n5);
                a.setId_Utilisateur(1);
                a.setIdsiege(2);

                as.modifier(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
                star3.setImage(image);
                star4.setImage(image);
                star5.setImage(image);
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

}
