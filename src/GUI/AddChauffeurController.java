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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
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
public class AddChauffeurController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cin;
    @FXML
    private TextField numtel;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private Button ajouterch;
    File file;
    File file1;
    File file2;
    ChauffeurService chaufService = new ChauffeurService();

    String reg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().add("bizerte");
        combobox.getItems().add("tunis");
        combobox.getItems().add("djerba");
        combobox.getItems().add("nabeul");
        combobox.getItems().add("sousse");
    }

    @FXML
    private File AjouterPermisAvant(MouseEvent event) {
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
    private File AjouterPermisArriere(MouseEvent event) {
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
    private File AjouterImage(MouseEvent event) {
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

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ajouter Chauffeur");
        alert.setHeaderText(null);
        alert.setContentText("Ajout fait avec succés");
        alert.showAndWait();

    }

    @FXML
    private void AjouterCh(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        int contact = Integer.parseInt(numtel.getText());
        String email = emailField.getText();
        int Cin = Integer.parseInt(cin.getText());
        float Prix = Float.parseFloat(prix.getText());

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

        Chauffeur chauf = new Chauffeur(Region.valueOf(combobox.getValue()), contact, Cin, email, permis, iamge, Prix, Nom, Prenom, permisBack, 2);

        chaufService.ajouter(chauf);
        showAlert();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
        Parent root = loader.load();

        ajouterch.getScene().setRoot(root);
    }
}
