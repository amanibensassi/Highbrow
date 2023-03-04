package GUI;

import entities.Siege;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import services.SiegeService;
import typeenumeration.Region;

public class AfficherSiegeController implements Initializable {

    @FXML
    private GridPane grid;

    SiegeService ps = new SiegeService();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label labelTitreSieges;
    @FXML
    private ComboBox<String> champRegion;
    @FXML
    private Button filtrebtn;
    @FXML
    private Button retourbtn;
    @FXML
    private TextField barreRecherche;
    @FXML
    private Button ajouterbtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> listRegion = FXCollections.observableArrayList("ariana", "beja", "ben_Arous", "bizerte", "gabes", "gafsa", "jendouba", "kairouan", "kasserine", "kebili", "kef", "mahdia", "manouba", "medenine", "monastir", "nabeul", "sfax", "sidi_Bouzid", "siliana", "sousse", "tataouine", "tozeur", "tunis", "zaghouan");
        champRegion.setItems(listRegion);

        try {
            List<Siege> sieges = ps.recuperer();
            int row = 1;
            int column = 0;
            for (int i = 0; i < sieges.size(); i++) {
                // Chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Siege.fxml"));
                AnchorPane pane = loader.load();

                // Passage de paramètres
                SiegeController controller = loader.getController();
                controller.setSiege(sieges.get(i));
                if (column > 0) {
                    column = 0;
                    row++;
                }
                grid.add(pane, column++, row);

                GridPane.setMargin(pane, new Insets(10));

            }
        } catch (SQLException ex) {
            System.out.println("Erreur de récupération des personnes : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface utilisateur : " + ex.getMessage());
        }

    }

    @FXML
    private void filtrerParRegion(ActionEvent event) {
        try {
            String region = champRegion.getValue(); // Récupérer la région sélectionnée
            List<Siege> sieges = ps.recupererSiegeByRegion(Region.valueOf(region)); // Récupérer les sièges par région

            grid.getChildren().clear(); // Effacer tous les enfants du GridPane
            champRegion.getValue(); // Récupérer la région sélectionnée

            grid.getChildren().clear(); // Effacer tous les enfants du GridPane

            if (sieges.isEmpty()) {
                // Afficher une alerte si la liste de sièges est vide
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText("Aucun siège n'a été trouvé pour cette région");
                alert.showAndWait();
            } else {
                int row = 1;
                int column = 0;
                for (int i = 0; i < sieges.size(); i++) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("Siege.fxml"));
                    AnchorPane pane = loader.load();

                    SiegeController controller = loader.getController();
                    controller.setSiege(sieges.get(i));
                    if (column > 0) {
                        column = 0;
                        row++;
                    }
                    grid.add(pane, column++, row);

                    GridPane.setMargin(pane, new Insets(10));
                }
            }
        } catch (SQLException ex) {
            System.out.println("Erreur de récupération des personnes : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface utilisateur : " + ex.getMessage());
        }
    }

    @FXML
    private void retour(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
            Parent root2 = loader1.load();
            AjouterSiegeController controller = loader.getController();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            retourbtn.getScene().setRoot(borderPane);
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("afficherSiege.fxml"));
//            Parent root = loader.load();
//            AfficherSiegeController controller = loader.getController();
//            //controller.dynamicinitialize(idsiege);
//            retourbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    private void chercherSieges(ActionEvent event) {

//        String recherche = barreRecherche.getText();
//        List<Siege> sieges = null;
//        try {
//            sieges = ps.recuperer();
//        } catch (SQLException ex) {
//            Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//// filtrer les sièges qui correspondent aux critères de recherche
//        List<Siege> resultatsRecherche = sieges.stream()
//                .filter(s -> s.getNom_siege().toLowerCase().contains(recherche.toLowerCase()))
//                .collect(Collectors.toList());
//        System.out.println(resultatsRecherche.size());
//        grid.getChildren().clear(); // Effacer tous les enfants du GridPane
//        int row = 1;
//        int column = 0;
//        for (int i = 0; i < resultatsRecherche.size(); i++) {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("Siege.fxml"));
//            AnchorPane pane = null;
//            try {
//                pane = loader.load();
//            } catch (IOException ex) {
//                Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            SiegeController controller = loader.getController();
//            controller.setSiege(resultatsRecherche.get(i));
//            if (column > 0) {
//                column = 0;
//                row++;
//            }
//            grid.add(pane, column++, row);
//
//            GridPane.setMargin(pane, new Insets(10));
//        }
    }

    @FXML
    private void ajouterSiege(ActionEvent event) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("SideBarUser.fxml"));
            Parent root1 = loader.load();
            BorderPane borderPane = new BorderPane();
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ajouterSiege.fxml"));
            Parent root2 = loader1.load();
           // AjouterSiegeController controller = loader.getController();
            HBox hbox = new HBox(root1, new Pane(), root2);
            hbox.setSpacing(20);

            borderPane.setRight(hbox);

            borderPane.setLeft(root1);

            borderPane.setPadding(new Insets(10, 10, 30, 10));
            ajouterbtn.getScene().setRoot(borderPane);

//            FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouterSiege.fxml"));
//            Parent root = loader.load();
//            AjouterSiegeController controller = loader.getController();
//
//            ajouterbtn.getScene().setRoot(root);
        } catch (IOException ex) {
            System.out.println("error" + ex.getMessage());
        }

    }

    @FXML
    private void BrreRechercheSiege(KeyEvent event) {
        String recherche = barreRecherche.getText();
        List<Siege> sieges = null;
        try {
            sieges = ps.recuperer();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
        }
// filtrer les sièges qui correspondent aux critères de recherche
        List<Siege> resultatsRecherche = sieges.stream()
                .filter(s -> s.getNom_siege().toLowerCase().startsWith(recherche.toLowerCase()))
                .collect(Collectors.toList());
        System.out.println(resultatsRecherche.size());
        grid.getChildren().clear(); // Effacer tous les enfants du GridPane
        int row = 1;
        int column = 0;
        for (int i = 0; i < resultatsRecherche.size(); i++) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Siege.fxml"));
            AnchorPane pane = null;
            try {
                pane = loader.load();
            } catch (IOException ex) {
                Logger.getLogger(AfficherSiegeController.class.getName()).log(Level.SEVERE, null, ex);
            }

            SiegeController controller = loader.getController();
            controller.setSiege(resultatsRecherche.get(i));
            if (column > 0) {
                column = 0;
                row++;
            }
            grid.add(pane, column++, row);

            GridPane.setMargin(pane, new Insets(10));
        }
        barreRecherche.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                BrreRechercheSiege(event); //Logger.getLogger(AfficherChauffeurController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });
    }

}
