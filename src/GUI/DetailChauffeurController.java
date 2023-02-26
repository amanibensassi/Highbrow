/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Chauffeur;
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
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.ChauffeurService;
import typeenumeration.Region;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class DetailChauffeurController implements Initializable {

    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Label nomch;
    @FXML
    private Label prenomch;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField cin;
    @FXML
    private TextField numtel;
    @FXML
    private TextField prix;
    @FXML
    private ImageView imageview;
    String nomImage;
    String pa;
    String pb;
    int idch;
    @FXML
    private ImageView pavant;
    @FXML
    private ImageView pback;
    @FXML
    private Button ModifierCh;
    @FXML
    private ImageView imageupdate;
    @FXML
    private ImageView PAvantUpdate;
    @FXML
    private ImageView pArriereUpdate;

    /**
     * Initializes the controller class.
     */
  ChauffeurService chauffeur = new ChauffeurService();
    
    String nomPermis;
    String nomPermisBack;
    String img;
    String pAvant;
    String pBack;
    File file;
    File file1;
    File file2;
    public void setChauffeur(Chauffeur c) {
        nomch.setText(c.getNom());
        prenomch.setText(c.getPrenom());
        nom.setText(c.getNom());
        prenom.setText(c.getPrenom());
        emailField.setText(c.getAdresse());
        cin.setText(String.valueOf(c.getCin()));
        combobox.setValue(c.getRegion().toString());
        numtel.setText(String.valueOf(c.getContact()));
        prix.setText(String.valueOf(c.getPrix_par_jour()));
        nomImage = "C://xampp//htdocs//img//" + c.getImage();

        File filee = new File(nomImage);

        Image imagee = new Image(filee.toURI().toString());
       // imageview.setImage(new Image(getClass().getResourceAsStream("/path/to/image.png")));

        imageview.setImage(imagee);
        imageview.getStyleClass().add("detail.css");
        
            pa = "C://xampp//htdocs//img//" + c.getImage();

        File file2 = new File(pa);

        Image image2 = new Image(file2.toURI().toString());

        pavant.setImage(image2);
        
            pb = "C://xampp//htdocs//img//" + c.getImage();

        File file3 = new File(pb);

        Image image3 = new Image(file3.toURI().toString());

        pback.setImage(image3);
        idch = c.getIdchauffeur();
    }

    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().add("bizerte");
        combobox.getItems().add("tunis");
        combobox.getItems().add("djerba");
        combobox.getItems().add("nabeul");
        combobox.getItems().add("sousse");
    }
 @FXML
    private File ModifierImage(MouseEvent event) {
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

    @FXML
    private File ModifierPermisAvant(MouseEvent event) {
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
    private File ModifierPermisBack(MouseEvent event) {
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
        return file2;
    }

    @FXML
    private void aa(MouseEvent event) {
        System.out.println("zzzz");
    }

    @FXML
    private void ModifierCh(ActionEvent event)  throws  FileNotFoundException, IOException, SQLException {
         String Nom = nom.getText();
        String Prenom = prenom.getText();
        int Contact = Integer.parseInt(numtel.getText());
        String Email = emailField.getText();
        int Cin = Integer.parseInt(cin.getText());
        float Prix = Float.parseFloat(prix.getText());
        
         FileInputStream fl = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        String img = file.getName();
        fl.read(data);
        fl.close();

        FileInputStream f2 = new FileInputStream(file1);
        byte[] data1 = new byte[(int) file1.length()];
        String pAvant = file1.getName();
        f2.read(data1);
        f2.close();

        FileInputStream f3 = new FileInputStream(file2);
        byte[] data2 = new byte[(int) file2.length()];
        String pBack = file2.getName();
        f3.read(data2);
        f3.close();
        Chauffeur chauf = new Chauffeur(idch, Region.valueOf(combobox.getValue()), Contact, Cin, Email, pAvant, img, Prix, Nom, Prenom, pBack, 2);
        chauffeur.modifier(chauf);
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
            Parent root =loader.load(); 

               ModifierCh.getScene().setRoot(root);

    }
    }

   

