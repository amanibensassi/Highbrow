/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.AfficherEntretienController;
import entities.Chauffeur;
import entities.Mecanicien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.MecanicienService;
import services.UserConn;
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
    Specialite tabspes []={electricien,mecanicien,tolier};
    Region tabreg []={Region.ariana,Region.beja,Region.ben_Arous,Region.bizerte,Region.gabes,Region.gafsa,Region.jendouba,Region.kairouan,Region.kasserine,Region.kebili,Region.kef,Region.mahdia,Region.manouba,Region.medenine,Region.monastir,Region.monastir,Region.nabeul,Region.sfax,Region.sidi_Bouzid,Region.siliana,Region.sousse,Region.tataouine,Region.tozeur,Region.tunis,Region.zaghouan};
    private int id;
    @FXML
    private ImageView cal;
    @FXML
    private ImageView plus;
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
//        try {
//            personnes = ms.recuperer();
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficherMecaniciensProfController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        this.getData();
//        if (role=="User"){
//            ajouterMec.setVisible(false);
//        }
//        this.trie();
        
    }
    
        
        
    public void setIdVehicule(int id_v){
        this.id=id_v;
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
    
    
    public void getData(){
        if (UserConn.role.toString().equals("administrateur")){
            btnlister.setVisible(false);
            cal.setVisible(false);
        }
        
         if (UserConn.role.toString().equals("proprietaire_agence")){
            btnlister.setVisible(false);
            ajouterMec.setVisible(false);
            cal.setVisible(false);
            plus.setVisible(false);
        }
        try {
            System.out.println("DKHALLLL");
            List<Mecanicien> personnes = ms.recuperer();
            System.out.println("DDDDDD"+personnes);
            int row = 1;
            int column = 0;
            for (int i = 0; i < personnes.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                MecanicienController controller = loader.getController();
                controller.setPersonne(personnes.get(i));
                controller.setIdVehicule(id);
                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
            }
            System.out.println("ID"+id);
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterMecanicien(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AjouterMecanicien.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        grid.getScene().setRoot(borderPane);
        
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
 
           FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
        Parent root1 = loader.load();
        BorderPane borderPane = new BorderPane();
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource("AfficherEntretien.fxml"));
        Parent root2 = loader1.load();
        HBox hbox = new HBox(root1, new Pane(), root2);
        hbox.setSpacing(20);

        borderPane.setRight(hbox);

        borderPane.setLeft(root1);

        borderPane.setPadding(new Insets(10, 10, 30, 10));
        grid.getScene().setRoot(borderPane);
        
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
    private void rechercheMecanicien(KeyEvent event) throws IOException {
        
        
        grid.getChildren().clear();
         int row = 1;
           int column = 0;
            String recherche = txtrecherche.getText();
        List<Mecanicien> ch = null;
        try {
            ch = ms.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Mecanicien> resultatsRecherche = ch.stream()
                .filter(s -> s.getNom_mecanicien().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());

        grid.getChildren().clear();
        int rowIndex = 1;
        int columnIndex = 0;

        for (int i = 0; i < resultatsRecherche.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));

            AnchorPane AnchorPane = loader.load();
              MecanicienController controller = loader.getController();
                controller.setPersonne(resultatsRecherche.get(i));
            System.out.println(resultatsRecherche.get(i));
             grid.add(AnchorPane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }

        }
        txtrecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                try {
                    rechercheMecanicien(event);
                } catch (IOException ex) {
                    //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
//        grid.getChildren().clear();
//        try {
//            List<Mecanicien> personnes = ms.rechercherMecanicien(txtrecherche.getText());
//            int row = 1;
//            int column = 0;
//            for (int i = 0; i < personnes.size(); i++) {
//                //chargement dynamique d'une interface
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("Mecanicien.fxml"));
//                AnchorPane pane = loader.load();
//                //passage de parametres
//                MecanicienController controller = loader.getController();
//                controller.setPersonne(personnes.get(i));
//
//                grid.add(pane, column, row);
//                column++;
//                if (column > 0) {
//                    column = 0;
//                    row++;
//                }
//            }
//        } catch (SQLException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
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
