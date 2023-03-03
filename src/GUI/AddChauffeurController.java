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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
        if (nom.getText().isEmpty()
                || prenom.getText().isEmpty() || prix.getText().isEmpty() || cin.getText().isEmpty()
                || emailField.getText().isEmpty() || numtel.getText().isEmpty()) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));
            if (nom.getText().isEmpty()) {
                nom.setEffect(in);
            }
            if (prenom.getText().isEmpty()) {
                prenom.setEffect(in);
            }
            if (prix.getText().isEmpty()) {
                prix.setEffect(in);
            }
            if (cin.getText().isEmpty()) {
                cin.setEffect(in);
            }
            if (emailField.getText().isEmpty()) {
                emailField.setEffect(in);
            }
            if (numtel.getText().isEmpty()) {
                numtel.setEffect(in);
            }
            //txtnom.setStyle("-fx-border-color: red " );
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Il faut remplir les champs obligatoires ");
            alert.showAndWait();

        } else if (TestPrix() & TestText(nom.getText()) & TestText(prenom.getText()) & TestEmail()
                & TestCin(cin.getText()) & TestCin(numtel.getText())) {

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
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
       FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ListeChauffeur.fxml"));
            Parent root2 = loader1.load();
      
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);
       
            borderPane.setLeft(root1);
        

        borderPane.setPadding(new Insets(10, 10, 30, 10));
//        Scene scene = new Scene(borderPane);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
        
        ajouterch.getScene().setRoot(borderPane);
        }
    }

    private boolean TestText(String cha) {
        boolean test = false;
        Pattern p = Pattern.compile("[a-zA-Z]*");
        Matcher m1 = p.matcher(cha);
        if (m1.find() && m1.group().equals(cha) || m1.find() && m1.group().equals(cha)) {
            test = true;
        }
        return test;
    }

    private boolean TestPrix() {
        boolean test = false;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(prix.getText());
        if (m.find() && m.group().equals(prix.getText())) {
            test = true;
        }
        return test;
    }

    private boolean TestCin(String cin) {
        boolean test = false;
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(cin);
        if (m.find() && m.group().equals(cin)) {
            test = true;
        }
        return test;
    }

    private boolean TestEmail() {
        boolean test = false;
        Pattern p = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher m = p.matcher(emailField.getText());
        if (m.find() && m.group().equals(emailField.getText())) {
            test = true;
        }
        return test;
    }

    @FXML
    private void testNom(KeyEvent event) {
        if (TestText(nom.getText()) & nom.getText().isEmpty() == false) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            nom.setEffect(in);
        } else {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            nom.setEffect(in);
        }
    }

    @FXML
    private void testprix(KeyEvent event) {
        if (prix.getText().isEmpty() == false) {
            if (TestPrix()) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                prix.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                prix.setEffect(in);
            }

        }
    }

    @FXML
    private void testPrenom(KeyEvent event) {
        if (TestText(prenom.getText()) & prenom.getText().isEmpty() == false) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            prenom.setEffect(in);
        } else {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            prenom.setEffect(in);
        }
    }

    @FXML
    private void testEmail(KeyEvent event) {

        if (TestEmail() & emailField.getText().isEmpty() == false) {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#52FF00"));

            emailField.setEffect(in);
        } else {

            InnerShadow in = new InnerShadow();
            in.setColor(Color.web("#f80000"));

            emailField.setEffect(in);
        }

    }

    @FXML
    private void testCin(KeyEvent event) {
        if (cin.getText().isEmpty() == false) {
            if (TestCin(cin.getText()) && cin.getText().length() == 7) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                cin.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                cin.setEffect(in);
            }

        }
    }

    @FXML
    private void testTel(KeyEvent event) {
        if (numtel.getText().isEmpty() == false) {
            if (TestCin(numtel.getText()) && numtel.getText().length() == 7) {

                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#52FF00"));

                numtel.setEffect(in);
            } else {
                InnerShadow in = new InnerShadow();
                in.setColor(Color.web("#f80000"));

                numtel.setEffect(in);
            }

        }

    }
}
