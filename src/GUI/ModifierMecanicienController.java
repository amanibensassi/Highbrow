/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherMecaniciensProfController;
import entities.Mecanicien;
import java.io.File;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.MecanicienService;
import typeenumeration.Region;
import typeenumeration.Specialite;
import static typeenumeration.Specialite.electricien;
import static typeenumeration.Specialite.mecanicien;
import static typeenumeration.Specialite.tolier;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class ModifierMecanicienController implements Initializable {
    
    @FXML
    private ChoiceBox<Region> cbregion;
    @FXML
    private ChoiceBox<Specialite> cbspecialite;

    Specialite tabspes []={electricien,mecanicien,tolier};
    Region tabreg []={Region.ariana,Region.beja,Region.ben_Arous,Region.bizerte,Region.gabes,Region.gafsa,Region.jendouba,Region.kairouan,Region.kasserine,Region.kebili,Region.kef,Region.mahdia,Region.manouba,Region.medenine,Region.monastir,Region.monastir,Region.nabeul,Region.sfax,Region.sidi_Bouzid,Region.siliana,Region.sousse,Region.tataouine,Region.tozeur,Region.tunis,Region.zaghouan};
    MecanicienService ms=new MecanicienService();
    int idmec=0;
    File file = null;
    File file2 = null;
    @FXML
    private TextField txtNom;
    @FXML
    private TextField txtPrenom;
    @FXML
    private TextField txtContact;
    @FXML
    private TextField txtAdresse;
    @FXML
    private TextField txtImage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbspecialite.getItems().addAll(tabspes);
        cbregion.getItems().addAll(tabreg);
        txtImage.setEditable(false);
        // TODO
    }  
    
    public void setData(Mecanicien m) {
        txtNom.setText(m.getNom_mecanicien());
        txtPrenom.setText(m.getPrenom_mecanicien());
        txtContact.setText(String.valueOf(m.getContact()));
        txtImage.setText(m.getImage());
        txtAdresse.setText(m.getAdresse());
        cbregion.setValue(m.getRegion());
        cbspecialite.setValue(m.getSpecialite());
        idmec=m.getIdmecanicien();
    }

    @FXML
    private void modifier(ActionEvent event) {
        
        
        if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty() || txtContact.getText().isEmpty() || txtAdresse.getText().isEmpty() || txtImage.getText().isEmpty() || cbregion.getValue() == null || cbspecialite.getValue() == null) {
            //JOptionPane.showMessageDialog(null, "Remplir les champs vides");
            Alert al = new Alert(Alert.AlertType.ERROR);
            al.setHeaderText(null);
            al.setContentText("Veuillez remplir les champs vides ! ");
            al.showAndWait();
        }
        else if (!txtContact.getText().matches("\\d{8}")){
            Alert a3 = new Alert(Alert.AlertType.ERROR);
            a3.setHeaderText(null);
            a3.setContentText("Veuillez saisir un numéro de téléphone valide ! ");
            a3.showAndWait();
        }
        else if (!txtNom.getText().matches("[a-zA-Z]+") || !txtPrenom.getText().matches("[a-zA-Z]+") ) {
            Alert a2 = new Alert(Alert.AlertType.ERROR);
            a2.setHeaderText(null);
            a2.setContentText("Veuillez saisir uniquement des lettres ! ");
            a2.showAndWait();
        }
        
        else{
        
        try {
            Mecanicien m = new Mecanicien();
            m.setIdmecanicien(idmec);
            m.setNom_mecanicien(txtNom.getText());
            m.setPrenom_mecanicien(txtPrenom.getText());
            m.setAdresse(txtAdresse.getText());
            m.setContact(Integer.valueOf(txtContact.getText()));
            m.setRegion(cbregion.getValue());
            m.setSpecialite(cbspecialite.getValue());
            m.setImage(txtImage.getText());
            System.out.println("personne Modifier avec succes");
            System.out.println(m);
            ms.modifier(m);
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {
            
         
            /////
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
        Parent root2 = loader1.load();
        AfficherMecaniciensProfController fc = loader1.getController();
        fc.setIdVehicule(idmec);
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        txtNom.getScene().setRoot(borderPane);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
        }
        
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
        txtImage.setText(String.valueOf(file2));
        return file2;
        
    }

    @FXML
    private void afficherMecaniciens(ActionEvent event) {
         try {
            //FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMecanicien.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
            Parent root = loader.load();
            //AfficherMecanicienController controller = loader.getController();
            AfficherMecaniciensProfController controller = loader.getController();
            //controller.setData(txtNom.getText() + " " + txtPrenom.getText());
            txtNom.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
    
}
