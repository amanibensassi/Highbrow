/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAnas;

import entities.Mecanicien;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.MecanicienService;
import typeenumeration.Region;
import typeenumeration.Specialite;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherMecanicienController implements Initializable {

    @FXML
    private TableColumn<Mecanicien, Integer> id;
    @FXML
    private TableColumn<Mecanicien, String> nom;
    @FXML
    private TableColumn<Mecanicien, String> prenom;
    @FXML
    private TableColumn<Mecanicien, Integer> contact;
    @FXML
    private TableColumn<Mecanicien, Specialite> specialite;
    @FXML
    private TableColumn<Mecanicien, Region> region;
    @FXML
    private TableColumn<Mecanicien, String> adresse;
    @FXML
    private TableColumn<Mecanicien, String> image;

    MecanicienService ms=new MecanicienService();
    @FXML
    private TableView<Mecanicien> tabmecaniciens;
    @FXML
    private TableColumn<Mecanicien, Button> delete;
    @FXML
    private TableColumn<Mecanicien, Button> detail;
    @FXML
    private TableColumn<Mecanicien, Button> modifier;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try{
            List<Mecanicien> mecaniciens = ms.recuperer();
            ObservableList<Mecanicien> olp = FXCollections.observableArrayList(mecaniciens);
            tabmecaniciens.setItems(olp);
            Button btnafficher = new Button();
            btnafficher.setText("Supprimer");
            Button btnsupprimer = new Button();
            btnsupprimer.setText("Afficher");
            id.setCellValueFactory(new PropertyValueFactory("idmecanicien"));
            nom.setCellValueFactory(new PropertyValueFactory("nom_mecanicien"));
            prenom.setCellValueFactory(new PropertyValueFactory("prenom_mecanicien"));
            contact.setCellValueFactory(new PropertyValueFactory("contact"));
            specialite.setCellValueFactory(new PropertyValueFactory("specialite"));
            region.setCellValueFactory(new PropertyValueFactory("region"));
            image.setCellValueFactory(new PropertyValueFactory("image"));
            adresse.setCellValueFactory(new PropertyValueFactory("adresse"));
            this.delete();
            this.afficherDetail();
            this.modifierMecanicien();
            //btnAfficher();
            //btnSupprimer();
            
                //this.delete();
        } catch (SQLException ex) {
            System.out.println("error" + ex.getMessage());
        }
            
        // TODO
    }   
    
    
    public void delete() {
        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                Mecanicien m=tabmecaniciens.getItems().get(getIndex());
                                // Je ne peux pas comme ca car supprimer n'est pas boolean c'est void
                                /*if (ms.supprimer(tabmecaniciens.getItems().get(getIndex()))) {
                                    tabmecaniciens.getItems().remove(getIndex());
                                    tabmecaniciens.refresh();
                                }*/
                                ms.supprimer(m);
                                tabmecaniciens.getItems().remove(getIndex());
                                tabmecaniciens.refresh();
                            } catch (SQLException ex) {
                                System.out.println("erreor:" + ex.getMessage());

                            }

                        });
                        setGraphic(b);

                    }
                }
            };
            

        });

    }
    
       public void afficherDetail() {
        detail.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Détail");
                        b.setOnAction((event) -> {
                            //try {
                                Mecanicien m=tabmecaniciens.getItems().get(getIndex());
                                System.out.println(m);
                                try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDetailMecanicien.fxml"));
                                        Parent root = loader.load();
                                        AfficherDetailMecanicienController controller = loader.getController();
                                        controller.setData(m);
                                        tabmecaniciens.getScene().setRoot(root);
                                        
                                    } catch (IOException ex) {
                                        System.out.println("error" + ex.getMessage());
                                    }
                        });
                        setGraphic(b);

                    }
                }
            };
            

        });

    }
       
       
       public void modifierMecanicien() {
        modifier.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Modifier");
                        b.setOnAction((event) -> {
                            //try {
                                Mecanicien m=tabmecaniciens.getItems().get(getIndex());
                                System.out.println(m);
                                try {
                                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierMecanicien.fxml"));
                                        Parent root = loader.load();
                                        ModifierMecanicienController controller = loader.getController();
                                        controller.setData(m);
                                        tabmecaniciens.getScene().setRoot(root);
                                        
                                    } catch (IOException ex) {
                                        System.out.println("error" + ex.getMessage());
                                    }
                        });
                        setGraphic(b);

                    }
                }
            };
            

        });

    }
    
}
