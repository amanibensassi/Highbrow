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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.UserService;
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
    @FXML
    private TextField photo_permis_avant;
    @FXML
    private TextField photo_permis_arriere;
    @FXML
    private TextField image;
           
    File file = null;
    File file2 = null;
    
        UserService us = new UserService();
        Role tabrole []= {client,proprietaire_agence};
        int iduser =0 ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         role.getItems().addAll(tabrole);
        // TODO
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

            file2 = chooser.getSelectedFile();
            String fileName = file2.getName();

            if (chooser.getSelectedFile() != null) {

                try {
                    Path from = Paths.get(chooser.getSelectedFile().toURI());
                    to1 = Paths.get(path + "\\" + fileName);
                    //to2 =Paths.get("src\\"+path+"\\"+file.getName()+".png");

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
        image.setText(String.valueOf(file2));
        return file2;
        
    }

        
    

    @FXML
    private void modifier(ActionEvent event) throws FileNotFoundException, IOException {
          try {
            Utilisateur u = new Utilisateur();
            u.setIdutilisateur(iduser);
            u.setNom(nom.getText());
            u.setPrenom(prenom.getText());
            u.setMail(mail.getText());
            u.setNum_tel(Integer.valueOf(num_tel.getText()));
            u.setDate_naissance(Date.valueOf(date_de_naissance.getValue()));
            u.setMot_de_passe(password.getText());
            u.setRole(role.getValue());
            u.setImage(u.getImage());
            //u.setImage(image.getText());
            FileInputStream f3 = new FileInputStream(file2);
        byte[] data2 = new byte[(int) file2.length()];
        String img = file2.getName();
        f3.read(data2);
        f3.close();

 u.setImage(img);
            u.setPhotopermis_arriere(photo_permis_arriere.getText());
            u.setPhotopermis_avant(photo_permis_avant.getText());
            
            System.out.println("personne Modifier avec succes");
            System.out.println(u);
            us.modifier(u);
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherUtilisateur.fxml"));
            Parent root = loader.load();
            AfficherUtilisateurController controller = loader.getController();
            //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
            nom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        
    }
        
         public void setData(Utilisateur u) {
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        num_tel.setText(String.valueOf(u.getNum_tel()));
        image.setText(u.getImage());
        photo_permis_avant.setText(u.getPhotopermis_avant());
        photo_permis_arriere.setText(u.getPhotopermis_arriere());

       date_de_naissance.setValue(LocalDate.parse(String.valueOf(u.getDate_naissance())));
        mail.setText(u.getMail());
        role.setValue(u.getRole());
        iduser=u.getIdutilisateur();
    }

    }
    
    

