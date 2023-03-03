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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

    File file;
    File file1;
    File file2;

    String pAvant;
    String pBack;
    String img;
    Boolean test =false ;
    Boolean test1 =false ;
    Boolean test2 =false ;
    public void setChauffeur(Chauffeur c) throws IOException {
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
        img = c.getImage();
        pAvant = c.getPermis();
        pBack = c.getPermis_arriere();
        File filee = new File(nomImage);

        Image imagee = new Image(filee.toURI().toString());
        // imageview.setImage(new Image(getClass().getResourceAsStream("/path/to/image.png")));

        imageview.setImage(imagee);
        imageview.getStyleClass().add("detail.css");
        pa = "C://xampp//htdocs//img//" + c.getPermis();
        File file2 = new File(pa);

        Image image2 = new Image(file2.toURI().toString());

        pavant.setImage(image2);

        pb = "C://xampp//htdocs//img//" + c.getPermis_arriere();

        File file3 = new File(pb);

        Image image3 = new Image(file3.toURI().toString());

        pback.setImage(image3);
        idch = c.getIdchauffeur();
        System.out.println("adresse image" + img);

    }

    public void initialize(URL url, ResourceBundle rb) {
        combobox.getItems().add("bizerte");
        combobox.getItems().add("tunis");
        combobox.getItems().add("djerba");
        combobox.getItems().add("nabeul");
        combobox.getItems().add("sousse");

    }

    @FXML
    private File ModifierImage() {
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
        test = true ;
        return file;
    }

    @FXML
    private File ModifierPermisAvant() {
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
          test1 =true ;
        return file1;
    }

    @FXML
    private File ModifierPermisBack() {
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
         test2 = true ;
        return file2;
    }

    @FXML
    private void aa(MouseEvent event) {
        System.out.println("zzzz");
    }

    @FXML
    private void ModifierCh(ActionEvent event) throws FileNotFoundException, IOException, SQLException {
        System.out.println("ancien" + img);
        String Nom = nom.getText();
        String Prenom = prenom.getText();
        int Contact = Integer.parseInt(numtel.getText());
        String Email = emailField.getText();
        int Cin = Integer.parseInt(cin.getText());
        float Prix = Float.parseFloat(prix.getText());
       
        if (test==true) {
            FileInputStream fl = new FileInputStream(file);
            byte[] data = new byte[(int) file.length()];
            img = file.getName();
            fl.read(data);
            fl.close();
            System.out.println("new" + img);
        }
        if (test1==true) {
            FileInputStream f2 = new FileInputStream(file1);
            byte[] data1 = new byte[(int) file1.length()];
            pAvant = file1.getName();
            f2.read(data1);
            f2.close();
        }

        if (test2==true) {
            FileInputStream f3 = new FileInputStream(file2);
            byte[] data2 = new byte[(int) file2.length()];
            pBack = file2.getName();
            f3.read(data2);
            f3.close();
        }

        Chauffeur chauf = new Chauffeur(idch, Region.valueOf(combobox.getValue()), Contact, Cin, Email, pAvant, img, Prix, Nom, Prenom, pBack, 2);
        chauffeur.modifier(chauf);

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

        ModifierCh.getScene().setRoot(borderPane);

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
