/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherUtilisateurController;
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.UserConn;
import services.UserService;
import services.crypterPassword;
import typeenumeration.Role;
import static typeenumeration.Role.client;
import static typeenumeration.Role.proprietaire_agence;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class ModifierUserController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField mail;
    @FXML
    private TextField num_tel;
    @FXML
    private PasswordField password;
    @FXML
    private ChoiceBox<Role> role;
    @FXML
    private Button modifier;
    @FXML
    private DatePicker date_de_naissance;
    private TextField photo_permis_avant;
    private TextField photo_permis_arriere;
    @FXML
    private Button image;
    File file;
    File file1;
    File file2;

    String pAvant;
    String pBack;
    String img;
    Boolean test = false;
    Boolean test1 = false;
    Boolean test2 = false;

    UserService us = new UserService();
    crypterPassword pass = new crypterPassword();

    Role tabrole[] = {client, proprietaire_agence};
    int iduser = 0;
    @FXML
    private Button pa;
    @FXML
    private Button pb;
    Utilisateur usercnnecter = new Utilisateur();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll(tabrole);
        usercnnecter.setNom(UserConn.nom);
        usercnnecter.setPrenom(UserConn.prenom);
        usercnnecter.setMail(UserConn.mail);
        usercnnecter.setNum_tel(UserConn.num_tel);
    }

    @FXML
    private File AjouterImage(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file = chooser.getSelectedFile();
            String fileName = file.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        test = true;
        return file;

    }

    @FXML
    private void modifier(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        Utilisateur u = new Utilisateur();
        if (password.getText().equals("") || nom.getText() == null || prenom.getText() == null || mail.getText() == null || role.getValue() == null || num_tel.getText() == null || date_de_naissance.getValue() == null || password.getText() == null) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        } else if (mail.getText().matches("(.*)@(.*)") == false) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        } else if (num_tel.getText().length() != 8) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid numero");
            alert.show();

        } else {
            try {
                u.setIdutilisateur(iduser);
                u.setNom(nom.getText());
                u.setPrenom(prenom.getText());
                u.setMail(mail.getText());
                u.setNum_tel(Integer.valueOf(num_tel.getText()));
                u.setDate_naissance(Date.valueOf(date_de_naissance.getValue()));
                u.setMot_de_passe(pass.crypterPassword(password.getText()));
                u.setRole(role.getValue());
                if (test == true) {
                    FileInputStream fl = new FileInputStream(file);
                    byte[] data = new byte[(int) file.length()];
                    img = file.getName();
                    fl.read(data);
                    fl.close();
                    System.out.println("new" + img);
                }
                if (test1 == true) {
                    FileInputStream f2 = new FileInputStream(file1);
                    byte[] data1 = new byte[(int) file1.length()];
                    pAvant = file1.getName();
                    f2.read(data1);
                    f2.close();
                }

                if (test2 == true) {
                    FileInputStream f3 = new FileInputStream(file2);
                    byte[] data2 = new byte[(int) file2.length()];
                    pBack = file2.getName();
                    f3.read(data2);
                    f3.close();
                }

                System.out.println("personne Modifier avec succes");
                System.out.println(u);
                u.setImage(img);
                u.setPhotopermis_arriere(pBack);
                u.setPhotopermis_avant(pAvant);
                us.modifier(u);
            } catch (SQLException ex) {
                System.out.println("error" + ex.getMessage());
            }
          

            if (UserConn.role.toString().equals("client")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                Parent root1 = loader.load();
                SideBarUserController cc = loader.getController();
                cc.setRole(UserConn.role.toString());
                BorderPane borderPane = new BorderPane();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
                Parent root2 = loader1.load();
                ClientProfileController ac = loader1.getController();
                ac.setData(usercnnecter);
                HBox hbox = new HBox(root1, new Pane(), root2);
                hbox.setSpacing(20);
                borderPane.setRight(hbox);
                borderPane.setLeft(root1);
                borderPane.setPadding(new Insets(10, 10, 30, 10));
                nom.getScene().setRoot(borderPane);
            }

            if (UserConn.role.toString().equals("proprietaire_agence")) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
                Parent root1 = loader.load();
                SideBarUserController cc = loader.getController();
                cc.setRole(UserConn.role.toString());
                BorderPane borderPane = new BorderPane();
                FXMLLoader loader1 = new FXMLLoader(getClass().getResource("PropProfile.fxml"));
                Parent root2 = loader1.load();
                PropProfileController ac = loader1.getController();
                ac.setData(usercnnecter);
                HBox hbox = new HBox(root1, new Pane(), root2);
                hbox.setSpacing(20);
                borderPane.setRight(hbox);
                borderPane.setLeft(root1);
                borderPane.setPadding(new Insets(10, 10, 30, 10));
                nom.getScene().setRoot(borderPane);
            }
//            if ((us.role_selection(mail.getText())).equals("client")) {
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("ClientProfile.fxml"));
//                Parent root = loader.load();
//                ClientProfileController controller = loader.getController();
//                controller.setData(u);
//                //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
//                nom.getScene().setRoot(root);
//            }
//            if ((us.role_selection(mail.getText())).equals("proprietaire_agence")) {
//
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("PropProfile.fxml"));
//                Parent root = loader.load();
//                PropProfileController controller = loader.getController();
//                controller.setData(u);
//                nom.getScene().setRoot(root);
//            }
        }

    }

    public void setData(Utilisateur u) {
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        num_tel.setText(String.valueOf(u.getNum_tel()));
        img = u.getImage();
        pAvant = u.getPhotopermis_avant();
        pBack = u.getPhotopermis_arriere();

        date_de_naissance.setValue(LocalDate.parse(String.valueOf(u.getDate_naissance())));
        mail.setText(u.getMail());
        role.setValue(u.getRole());
        iduser = u.getIdutilisateur();
    }

    @FXML
    private File ajouterpa(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file1 = chooser.getSelectedFile();
            String fileName = file1.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file1);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        test1 = true;
        return file1;
    }

    @FXML
    private File ajouterpb(ActionEvent event) {
        Path to1 = null;
        String m = null;
        String path = "C:\\xampp\\htdocs\\img";
        JFileChooser chooser = new JFileChooser();

        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "JPG & PNG Images", "jpg", "jpeg", "PNG");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            m = chooser.getSelectedFile().getAbsolutePath();

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //           to2 = Paths.get("src\\"+path+"\\"+file.getName()+".png");

                    CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                    };
                    Files.copy(from, to1, options);
                    System.out.println("added");
                    System.out.println(file2);

                } catch (IOException ex) {
                    System.out.println();
                }
            }

        }
        test2 = true;
        return file2;
    }

}
