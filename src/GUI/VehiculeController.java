/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.ModifierVehiculeController;
import GUI.AfficherVehiculeBySiegeController;
import entities.Avis;
import entities.Vehicule;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.AvisService;
import services.UserConn;

import services.VehiculeService;
import typeenumeration.Note;
import static typeenumeration.Note.n1;
import static typeenumeration.Note.n2;
import static typeenumeration.Note.n3;
import static typeenumeration.Note.n4;
import static typeenumeration.Note.n5;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class VehiculeController implements Initializable {

    Vehicule pe = new Vehicule();
    VehiculeService ps = new VehiculeService();
    @FXML
    private Label marqueLabel;
    @FXML
    private Label prixjourLabel;
    @FXML
    private ImageView imageLabel;
    @FXML
    private AnchorPane vehiculesLignes;
    @FXML
    private Label etatLabel;
    @FXML
    private Label dateLabel;
    @FXML
    private Button supprimerbtn;
    @FXML
    private Button modifierbtn;
    @FXML
    private Label carburantLabel;
    @FXML
    private Label prixdeventeLabel;
    @FXML
    private Label nombredeplaceLabel;
    @FXML
    private Label immatriculationLabel;
    @FXML
    private Label kilometrageLabel;
    @FXML
    private Button reserverbtn;
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
    String nom_imgvide;
    String nom_img1;
    AvisService as = new AvisService();
    @FXML
    private ImageView id_entretient;
    @FXML
    private Label lblnbretoiles;
    @FXML
    private ImageView rendezvousVente;
    @FXML
    private ImageView voirRendezVous;
    @FXML
    private HBox hboxavis;
    @FXML
    private Label lblavis;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    private int idVehicule;

    public int getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(int idVehicule) {
        this.idVehicule = idVehicule;
    }

    public void setVehicule(Vehicule c) throws SQLException {
        idVehicule = c.getIdvehicule();
        marqueLabel.setText(c.getMarque());
        dateLabel.setText(String.valueOf(c.getDate_circulation()));
        kilometrageLabel.setText(String.valueOf(c.getKilometrage()));
        immatriculationLabel.setText(c.getImmatriculation());
        nombredeplaceLabel.setText(c.getNbr_place().toString());
        prixdeventeLabel.setText(String.valueOf(c.getPrix_vente()));
        etatLabel.setText(c.getEtat().toString());
        if (c.getEtat().toString().equals("a_louer")) {
            rendezvousVente.setVisible(false);
        }
        prixjourLabel.setText(String.valueOf(c.getPrix_par_jour()));
        carburantLabel.setText(c.getCarburant().toString());
        TreeMap<Integer, Integer> calcul = as.nombreAvisVehicule(idVehicule);
        for (Map.Entry<Integer, Integer> entry : calcul.entrySet()) {
            float key = Integer.parseInt(String.valueOf(entry.getKey()));
            float value = Integer.parseInt(String.valueOf(entry.getValue()));
            System.out.println("AAAA" + key + value);
            float res = (key / value) * 100;
            lblnbretoiles.setText(String.valueOf(res) + "%");
        }
        String nomImage = "C://Users//anasm//OneDrive//Documents//ImagesProjet//" + c.getImage_vehicule();
        File file = new File(nomImage);
        Image img = new Image(file.toURI().toString());
        imageLabel.setImage(img);
        pe.setId_siege(c.getId_siege());
        pe.setIdvehicule(c.getIdvehicule());
        pe.setMarque(c.getMarque());
        pe.setDate_circulation(c.getDate_circulation());
        pe.setImage_vehicule(c.getImage_vehicule());
        pe.setEtat(c.getEtat());
        pe.setPrix_par_jour(c.getPrix_par_jour());
        pe.setCarburant(c.getCarburant());
        pe.setImmatriculation(c.getImmatriculation());
        pe.setPrix_vente(c.getPrix_vente());
        pe.setNbr_place(c.getNbr_place());
        pe.setKilometrage(c.getKilometrage());
        if (UserConn.role.toString().equals("client")) {
            supprimerbtn.setVisible(false);
            modifierbtn.setVisible(false);
            id_entretient.setVisible(false);
            lblnbretoiles.setVisible(false);
        }
        if (UserConn.role.toString().equals("proprietaire_agence")) {
            reserverbtn.setVisible(false);
            hboxavis.setVisible(false);
            lblavis.setVisible(false);
        }
        List<Avis> avis = new ArrayList<>();
        avis = as.testAvisVehicule(c.getIdvehicule(), UserConn.idutilisateur);
        if (avis.size() == 0) {

            for (int k = 0; k < 5; k++) {
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                File file1 = new File(nom_img);
                Image image1 = new Image(file1.toURI().toString());
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
                    File file1 = new File(nom_img1);
                    Image image1 = new Image(file1.toURI().toString());
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file2 = new File(nom_img);
                    Image image = new Image(file2.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image1);
                    star3.setImage(image1);
                    star4.setImage(image1);
                    star5.setImage(image1);

                }
                if (n.equals(n2)) {
                    nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file1 = new File(nom_img1);
                    Image image1 = new Image(file1.toURI().toString());
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file2 = new File(nom_img);
                    Image image = new Image(file2.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image1);
                    star4.setImage(image1);
                    star5.setImage(image1);

                }
                if (n.equals(n3)) {
                    nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file1 = new File(nom_img1);
                    Image image1 = new Image(file1.toURI().toString());
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file2 = new File(nom_img);
                    Image image = new Image(file2.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(image1);
                    star5.setImage(image1);

                }
                if (n.equals(n4)) {
                    nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file1 = new File(nom_img1);
                    Image image1 = new Image(file1.toURI().toString());
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file2 = new File(nom_img);
                    Image image = new Image(file2.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(image);
                    star5.setImage(image1);

                }
                if (n.equals(n5)) {
                    nom_img1 = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File file1 = new File(nom_img1);
                    Image image1 = new Image(file1.toURI().toString());
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file2 = new File(nom_img);
                    Image image = new Image(file2.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(image);
                    star5.setImage(image);

                }
            }
        }
    }

    @FXML
    private void supprimerVehicule(ActionEvent event) throws IOException {

        try {
            int id = pe.getId_siege();
            ps.supprimer(pe);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root2 = loader1.load();
            AfficherVehiculeBySiegeController controller = loader1.getController();
            controller.dynamicinitialize(id);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            vehiculesLignes.getScene().setRoot(borderPane);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeBySiegeController controller = loader.getController();
//            //controller.dynamicinitialize(id);
//            vehiculesLignes.getScene().setRoot(root);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void modifierVehicule(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("modifierVehicule.fxml"));
            Parent root2 = loader1.load();
            ModifierVehiculeController controller = loader1.getController();

            controller.setData(pe);
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            vehiculesLignes.getScene().setRoot(borderPane);

//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierVehicule.fxml"));
//            Parent root = loader.load();
//            ModifierVehiculeController controller = loader.getController();
//
//            controller.setData(pe);
//
//            vehiculesLignes.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void reserverVehicule(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("Calendrier.fxml"));
        Parent root2 = loader1.load();
        CalendrierController fc = loader1.getController();
        fc.testCalendrier(idVehicule);
        fc.setIdVehicule(idVehicule);

        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        vehiculesLignes.getScene().setRoot(borderPane);

//            
//        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("formLouerVehicule.fxml"));
//            Parent root2 = loader1.load();
//            //controller.dynamicinitialize(id);
//            vehiculesLignes.getScene().setRoot(root2);
    }

    @FXML
    private void clickstart1(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisVehicule(idVehicule, 1);
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
                a.setId_vehicule(idVehicule);
                as.ajouterAvisVehicule(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());

                    a.setNote(n1);
                    a.setId_Utilisateur(1);
                    a.setId_vehicule(idVehicule);
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file = new File(nom_img);
                    Image image = new Image(file.toURI().toString());
                    nom_imgvide = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File filevide = new File(nom_imgvide);
                    Image imagevide = new Image(filevide.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(imagevide);
                    star3.setImage(imagevide);
                    star4.setImage(imagevide);
                    star5.setImage(imagevide);
                    as.modifier(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart2(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisVehicule(idVehicule, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n2);
                a.setId_Utilisateur(1);
                a.setId_vehicule(idVehicule);
                as.ajouterAvisVehicule(a);
                nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                File file = new File(nom_img);
                Image image = new Image(file.toURI().toString());
                star1.setImage(image);
                star2.setImage(image);
            } else {
                Avis a = new Avis();
                for (int i = 0; i < avis.size(); i++) {

                    a.setId(avis.get(i).getId());
                    a.setNote(n2);
                    a.setId_Utilisateur(1);
                    a.setId_vehicule(idVehicule);
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file = new File(nom_img);
                    Image image = new Image(file.toURI().toString());
                    nom_imgvide = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File filevide = new File(nom_imgvide);
                    Image imagevide = new Image(filevide.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(imagevide);
                    star4.setImage(imagevide);
                    star5.setImage(imagevide);
                    as.modifier(a);
                }

            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart3(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisVehicule(idVehicule, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n3);
                a.setId_Utilisateur(1);
                a.setId_vehicule(idVehicule);
                as.ajouterAvisVehicule(a);
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

                    a.setNote(n3);
                    a.setId_Utilisateur(1);
                    a.setId_vehicule(idVehicule);
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file = new File(nom_img);
                    Image image = new Image(file.toURI().toString());
                    nom_imgvide = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File filevide = new File(nom_imgvide);
                    Image imagevide = new Image(filevide.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(imagevide);
                    star5.setImage(imagevide);
                    as.modifier(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart4(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisVehicule(idVehicule, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n4);
                a.setId_Utilisateur(1);
                a.setId_vehicule(idVehicule);
                as.ajouterAvisVehicule(a);
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

                    a.setNote(n4);
                    a.setId_Utilisateur(1);
                    a.setId_vehicule(idVehicule);

                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file = new File(nom_img);
                    Image image = new Image(file.toURI().toString());
                    nom_imgvide = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File filevide = new File(nom_imgvide);
                    Image imagevide = new Image(filevide.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(image);
                    star5.setImage(imagevide);
                    as.modifier(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void clickstart5(MouseEvent event) {
        try {
            List<Avis> avis = new ArrayList<>();
            avis = as.testAvisVehicule(idVehicule, 1);
            if (avis.size() == 0) {
                Avis a = new Avis();
                a.setNote(n5);
                a.setId_Utilisateur(1);
                a.setId_vehicule(idVehicule);
                as.ajouterAvisVehicule(a);
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

                    a.setNote(n5);
                    a.setId_Utilisateur(1);
                    a.setId_vehicule(idVehicule);
                    nom_img = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\starp.png";
                    File file = new File(nom_img);
                    Image image = new Image(file.toURI().toString());
                    nom_imgvide = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\src\\images\\star.png";
                    File filevide = new File(nom_imgvide);
                    Image imagevide = new Image(filevide.toURI().toString());
                    star1.setImage(image);
                    star2.setImage(image);
                    star3.setImage(image);
                    star4.setImage(image);
                    star5.setImage(image);
                    as.modifier(a);
                }
            }
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void entretient(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root2 = loader1.load();
        AfficherMecaniciensProfController fc = loader1.getController();
        fc.setIdVehicule(idVehicule);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        vehiculesLignes.getScene().setRoot(borderPane);

    }

    @FXML
    private void AjouterRendezVousVente(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterRendezVousVente.fxml"));
        Parent root2 = loader1.load();
        AjouterRendezVousVenteController controller = loader1.getController();
        controller.setidVehicule(idVehicule);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));

        rendezvousVente.getScene().setRoot(borderPane);
    }

    @FXML
    private void ConsulterLesRendezVous(MouseEvent event) throws IOException, FileNotFoundException, ParseException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
         SideBarUserController cc = loader.getController();
        cc.setRole(UserConn.role.toString());
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherRendezVous.fxml"));
        Parent root2 = loader1.load();
        AfficherRendezVousController rv = loader1.getController();
        rv.setidVehicule(idVehicule);
        rv.setdata();
        
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));

        rendezvousVente.getScene().setRoot(borderPane);
    }

}
