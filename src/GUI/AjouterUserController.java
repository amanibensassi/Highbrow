/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import static com.sun.webkit.perf.WCFontPerfLogger.reset;
import entities.Utilisateur;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
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
public class AjouterUserController implements Initializable {

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
    private Button ajouter;
    @FXML
    private DatePicker date_de_naissance;
    private TextField photo_permis_avant;
    private TextField photo_permis_arriere;
    private TextField image;
        File file;
    File file1;
    File file2;

    /**
     * Initializes the controller class.
     *  
     */       crypterPassword pss=new crypterPassword();

            UserService us = new UserService() ;
            
            Role tabrole []= {client,proprietaire_agence} ;
            
           
    @FXML
    private Button img;
    @FXML
    private Button imagearrire;
    @FXML
    private Button imageavant;
            
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
                              //to2 =  Paths.get("src\\"+path+"\\"+file.getName()+".png");

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
         return file2;
     }

      @FXML
    private File ajoutarrire(ActionEvent event) {
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
        return file1;
    }

    @FXML
    private File ajouterimageavant(ActionEvent event) {
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
        return file;
    }
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().addAll(tabrole);
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws FileNotFoundException, IOException, NoSuchAlgorithmException {
        Utilisateur u = new Utilisateur();
//        u.setNum_tel(Integer.parseInt(num_tel.getText()));
//        u.setNom(nom.getText());
//        u.setPrenom(prenom.getText());
//        u.setMail(mail.getText());
//        u.setDate_naissance(Date.valueOf(date_de_naissance.getValue()));
//        u.setMot_de_passe(password.getText());
//        u.setRole(role.getValue());
//        u.setPhotopermis_avant(photo_permis_avant.getText());
//        u.setPhotopermis_arriere(photo_permis_arriere.getText());
//       
//
//        FileInputStream f3 = new FileInputStream(file2);
//        byte[] data2 = new byte[(int) file2.length()];
//        String img = file2.getName();
//        f3.read(data2);
//        f3.close();
//
//        u.setImage(img);
        if (nom.getText()==null || prenom.getText() ==null || mail.getText()==null || role.getValue()==null
                || num_tel.getText()==null || date_de_naissance.getValue()==null  || password.getText()==null 
               )      
        {   Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Missing Informations");
            alert.show();
        }else if (mail.getText().matches("(.*)@(.*)")==false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid email address");
            alert.show();
        }else if (num_tel.getText().length()!=8 ){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Failed");
            alert.setHeaderText("Attention !!");
            alert.setContentText("Enter a valid numero");
            alert.show();

       
        }else{
            //Methode Ajouter    
            
        try {
              u.setNum_tel(Integer.parseInt(num_tel.getText()));
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setMail(mail.getText());
        u.setDate_naissance(Date.valueOf(date_de_naissance.getValue()));
        u.setMot_de_passe(pss.crypterPassword(password.getText()));
        u.setRole(role.getValue());
//        u.setPhotopermis_avant(photo_permis_avant.getText());
//        u.setPhotopermis_arriere(photo_permis_arriere.getText());
//       

       FileInputStream fl = new FileInputStream(file2);
            byte[] data = new byte[(int) file2.length()];
            String iamge = file2.getName();
            fl.read(data);
            fl.close();

            FileInputStream f2 = new FileInputStream(file);
            byte[] data1 = new byte[(int) file.length()];
            String permis = file.getName();
            f2.read(data1);
            f2.close();

            FileInputStream f3 = new FileInputStream(file1);
            byte[] data2 = new byte[(int) file1.length()];
            String permisBack = file1.getName();
            f3.read(data2);
            f3.close();

        u.setImage(iamge);
        u.setPhotopermis_arriere(permisBack);
        u.setPhotopermis_avant(permis);
            us.ajouter(u);
            System.out.println(u);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("authenticate.fxml"));
            Parent root = loader.load();
            mail.getScene().setRoot(root);

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

  
    }

  
}

   
