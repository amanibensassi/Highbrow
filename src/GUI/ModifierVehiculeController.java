/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherVehiculeBySiegeController;
import entities.Vehicule;
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
//import java.sql.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import services.VehiculeService;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
import java.time.LocalDate;
import java.time.Instant;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class ModifierVehiculeController implements Initializable {

    @FXML
    private TextField marqueid;
    @FXML
    private TextField kilometrageid;
    @FXML
    private TextField prixjourid;
    @FXML
    private TextField immatriculationid;
    @FXML
    private ComboBox<NbrPlace> nombreplaceid;
    @FXML
    private TextField prixventeid;
    @FXML
    private ComboBox<Carburant> carburantid;
    @FXML
    private ComboBox<Etat> etatid;
    @FXML
    private DatePicker datecirculationid;
    @FXML
    private Button imageid;
    @FXML
    private Button validerbtn;

    VehiculeService ps = new VehiculeService();
    int idsiege = 0;
    int idutilisateur = 0;
    int idvehicule = 0;
    @FXML
    private Button annulerbtn;
private Boolean testImage = false ; 
    @FXML
    private TextField img;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        NbrPlace[] tabplace = {NbrPlace.cinq, NbrPlace.deux, NbrPlace.neuf, NbrPlace.sept};
        nombreplaceid.getItems().setAll(tabplace);

        Carburant[] tabcarburant = {Carburant.diesel, Carburant.essence};
        carburantid.getItems().setAll(tabcarburant);

        Etat[] tabetat = {Etat.a_louer, Etat.a_vendre, Etat.louer};
        etatid.getItems().setAll(tabetat);
    }
  File file1;

    @FXML
    private File ajouterImage(ActionEvent event) {

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
        //imageid.setText(String.valueOf(file1));
        img.setText(file1.getName().toString());
        testImage =true;
        return file1;
    }
    public void setData(Vehicule c) {

        marqueid.setText(c.getMarque());

        Date date = new Date();
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();   
        LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
        datecirculationid.setValue(localDate);

        
        img.setText(c.getImage_vehicule());
        etatid.setValue(c.getEtat());
        prixjourid.setText(String.valueOf(c.getPrix_par_jour()));
        prixventeid.setText(String.valueOf(c.getPrix_vente()));
        kilometrageid.setText(String.valueOf(c.getKilometrage()));
        carburantid.setValue(c.getCarburant());
        immatriculationid.setText(c.getImmatriculation());
        nombreplaceid.setValue(c.getNbr_place());
        idvehicule = c.getIdvehicule();
        idsiege = c.getId_siege();
    }

    @FXML
    private void validerModification(ActionEvent event) throws FileNotFoundException, IOException {

        try {
            Vehicule s = new Vehicule();
            s.setIdvehicule(idvehicule);
            s.setId_siege(idsiege);
            s.setMarque(marqueid.getText());
            s.setKilometrage(Integer.parseInt(kilometrageid.getText()));
            s.setImmatriculation(immatriculationid.getText());

            s.setPrix_par_jour(Float.parseFloat(prixjourid.getText()));
            s.setPrix_vente(Float.parseFloat(prixventeid.getText()));

            LocalDate localDate = datecirculationid.getValue();
            Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
            Date date = Date.from(instant);
            s.setDate_circulation(date);

            s.setNbr_place(nombreplaceid.getValue());
            s.setCarburant(carburantid.getValue());
            s.setEtat(etatid.getValue());
           
            if (testImage==true) {
            FileInputStream f2 = new FileInputStream(file1);
            byte[] data1 = new byte[(int) file1.length()];
            //img = file1.getName();
            img.setText(file1.getName().toString());
                System.out.println("image baed update "+file1.getName().toString());
            f2.read(data1);
            f2.close();
        }
             s.setImage_vehicule(img.getText());
            ps.modifier(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("modification réussie");
            alert.setHeaderText("modification de vehicule réussi");
            alert.setContentText("Le vehicule a été modifié avec succès !");
            alert.showAndWait();

            System.out.println("vehicule modifié avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
        try {

              
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root2 = loader1.load();
             AfficherVehiculeBySiegeController controller = loader1.getController();
            controller.dynamicinitialize(idsiege);
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            validerbtn.getScene().setRoot(borderPane);
            
            
            
            
            
            
//            
//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeBySiegeController controller = loader.getController();
//            controller.dynamicinitialize(idsiege);
//            validerbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }
  

    @FXML
    private void annuler(ActionEvent event) {

        try {
 FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root2 = loader1.load();
            AfficherVehiculeBySiegeController controller = loader1.getController();
            controller.dynamicinitialize(idsiege);
          
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            validerbtn.getScene().setRoot(borderPane);
            
            
            
            
            
            
//            
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeBySiegeController controller = loader.getController();
//            controller.dynamicinitialize(idsiege);
//            validerbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

    @FXML
    private void img(ActionEvent event) {
    }

}
