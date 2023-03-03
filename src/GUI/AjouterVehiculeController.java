/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherVehiculeBySiegeController;
import entities.Siege;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import services.SiegeService;
import services.VehiculeService;
import typeenumeration.Carburant;
import typeenumeration.Etat;
import typeenumeration.NbrPlace;
import typeenumeration.Region;

/**
 * FXML Controller class
 *
 * @author Trabelsi Mohamed
 */
public class AjouterVehiculeController implements Initializable {

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
    private Button ajouterbtn;
    @FXML
    private Button afficherbtn;
    @FXML
    private TextField prixventeid;
    @FXML
    private ComboBox<Carburant> carburantid;
    @FXML
    private ComboBox<Etat> etatid;
    @FXML
    private DatePicker datecirculationid;
    private TextField imageid;
    
    VehiculeService ps = new VehiculeService();
    @FXML
    private Button image;
    int id;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void setVehicule1(int s){
        this.id=s;
    NbrPlace[] tabplace={NbrPlace.cinq,NbrPlace.deux,NbrPlace.neuf,NbrPlace.sept};
        nombreplaceid.getItems().setAll(tabplace);
        
        Carburant[] tabcarburant={Carburant.diesel,Carburant.essence};
        carburantid.getItems().setAll(tabcarburant);
        
             Etat[] tabetat={Etat.a_louer,Etat.a_vendre,Etat.louer};
        etatid.getItems().setAll(tabetat);
    
    
    }


    
    @FXML
    private void ajouter(ActionEvent event) throws FileNotFoundException, IOException {
        try {
            Vehicule s = new Vehicule();
              
            s.setMarque(marqueid.getText());
            s.setKilometrage(Integer.parseInt(kilometrageid.getText()));
            s.setImmatriculation(immatriculationid.getText());
            
            s.setPrix_par_jour(Integer.parseInt(prixjourid.getText()));
            s.setPrix_vente(Integer.parseInt(prixventeid.getText()));
            s.setDate_circulation(Date.valueOf(datecirculationid.getValue()));
                    
            //NbrPlace r = nombreplaceid.getSelectionModel().getSelectedItem();
            //s.setNbr_place(r.getValeur());
            s.setNbr_place(nombreplaceid.getValue());
            s.setCarburant(carburantid.getValue());
            s.setEtat(etatid.getValue());
            FileInputStream f2 = new FileInputStream(file1);
        byte[] data1 = new byte[(int) file1.length()];
        String img = file1.getName();
        f2.read(data1);
        f2.close();
            s.setImage_vehicule(img);
            //s.setIdvehicule(0);
            s.setId_siege(id);
            ps.ajouter(s);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ajout réussi");
            alert.setHeaderText("Ajout de vehicule réussi");
            alert.setContentText("Le vehicule a été ajouté avec succès !");
            alert.showAndWait();
            System.out.println("vehicule ajouter avec succes");
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

//    @FXML
//    private void recuperer(ActionEvent event) {
//        if (afficherbtn.isPressed()){
//            System.out.println("eee");
//        }
//                /*try {
//                    
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehicule.fxml"));
//            Parent root = loader.load();
//            AfficherVehiculeController controller = loader.getController();
//            
//            afficherbtn.getScene().setRoot(root);
//            
//        } catch (IOException ex) {
//            System.out.println("error" + ex.getMessage());
//        }*/
//    }

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
        return file1;
    }

    @FXML
    private void Affciher(ActionEvent event) {
      try {
                    
                    
            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherVehiculeBySiege.fxml"));
            Parent root = loader.load();
            AfficherVehiculeBySiegeController controller = loader.getController();
            controller.dynamicinitialize(id);
            afficherbtn.getScene().setRoot(root);
            
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }
    }

   
    
    
    
}
