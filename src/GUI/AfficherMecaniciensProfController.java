/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherEntretienController;
import entities.Mecanicien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
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
public class AfficherMecaniciensProfController implements Initializable {

    @FXML
    private GridPane grid;
    MecanicienService ms=new MecanicienService();
    @FXML
    private TextField txtrecherche;
    @FXML
    private Button ajouterMec;
    String role="Administrateur";
    //String role="User";
    @FXML
    private Button btnlister;

    List<Mecanicien> personnes;
    @FXML
    private ChoiceBox<String> cbregion;
    @FXML
    private ChoiceBox<String> cbspecialite;
    Specialite tabspes []={electricien,mecanicien,tolier};
    Region tabreg []={Region.ariana,Region.beja,Region.ben_Arous,Region.bizerte,Region.gabes,Region.gafsa,Region.jendouba,Region.kairouan,Region.kasserine,Region.kebili,Region.kef,Region.mahdia,Region.manouba,Region.medenine,Region.monastir,Region.monastir,Region.nabeul,Region.sfax,Region.sidi_Bouzid,Region.siliana,Region.sousse,Region.tataouine,Region.tozeur,Region.tunis,Region.zaghouan};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //cbregion.getItems().addAll(tabreg);
        //cbspecialite.getItems().addAll(tabspes);
        //cbregion.setValue(Region.tunis);
        //cbspecialite.setValue(mecanicien);
//        ObservableList<String> listRegion = FXCollections.observableArrayList("ariana","beja","ben_Arous","bizerte","gabes","gafsa","jendouba","kairouan","kasserine","kebili","kef","mahdia","manouba","medenine","monastir","nabeul","sfax","sidi_Bouzid","siliana","sousse","tataouine","tozeur","tunis","zaghouan");
//        cbregion.setItems(listRegion);
//        ObservableList<String> listSpecialite = FXCollections.observableArrayList("mecanicien","tolier","electricien");
//        cbspecialite.setItems(listSpecialite);
        try {
            personnes = ms.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherMecaniciensProfController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.getData();
        if (role=="User"){
            ajouterMec.setVisible(false);
        }
        this.trie();
        
    }
    
    private void getData(){
        try {
            List<Mecanicien> personnes = ms.recuperer();
            int row = 1;
            int column = 0;
            for (int i = 0; i < personnes.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                MecanicienController controller = loader.getController();
                controller.setPersonne(personnes.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterMecanicien(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterMecanicien.fxml"));
            Parent root = loader.load();
            AjouterMecanicienController controller = loader.getController();
            grid.getScene().setRoot(root);
        
    }
/*
    private void ajouterEntretien(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEntretien.fxml"));
            Parent root = loader.load();
            AjouterEntretienController controller = loader.getController();
            grid.getScene().setRoot(root);
        
    }
*/
   

    @FXML
    private void listerEntretiens(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEntretien.fxml"));
            Parent root = loader.load();
            AfficherEntretienController controller = loader.getController();
            grid.getScene().setRoot(root);
        
    }

    /*@FXML
    private void rechercheMecanicien(KeyEvent event) throws IOException {
        grid.getChildren().clear();
            System.out.println("PERSONNES"+personnes);
        //List<Mecanicien> personnes;
            //personnes = ms.recuperer();
            List<Mecanicien> personnes_recherche = personnes.stream().filter(e->e.getNom_mecanicien().equals(txtrecherche.getText())).collect(Collectors.toList());
            System.out.println("nom:"+txtrecherche.getText());
            System.out.println("emps"+personnes_recherche);
            
            int row = 1;
            int column = 0;
            for (int i = 0; i < personnes_recherche.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                MecanicienController controller = loader.getController();
                controller.setPersonne(personnes_recherche.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
                }
         
    }*/

    @FXML
    private void rechercheMecanicien(KeyEvent event) {
        grid.getChildren().clear();
        try {
            List<Mecanicien> personnes = ms.rechercherMecanicien(txtrecherche.getText());
            int row = 1;
            int column = 0;
            for (int i = 0; i < personnes.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                MecanicienController controller = loader.getController();
                controller.setPersonne(personnes.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void trie(){
        cbregion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                grid.getChildren().clear();
                try {
                    List<Mecanicien> personnes=ms.recupererMecanicienByRegion(String.valueOf(String.valueOf(cbregion.getValue().toString())));
                    int row = 1;
                    int column = 0;
                    for (int i = 0; i < personnes.size(); i++) {
                        //chargement dynamique d'une interface
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
                        AnchorPane pane = loader.load();
                        //passage de parametres
                        MecanicienController controller = loader.getController();
                        controller.setPersonne(personnes.get(i));

                        grid.add(pane, column, row);
                        column++;
                        if (column > 0) {
                            column = 0;
                            row++;
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(AfficherMecaniciensProfController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(AfficherMecaniciensProfController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}
