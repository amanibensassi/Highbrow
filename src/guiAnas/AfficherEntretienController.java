/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package guiAnas;

import entities.Entretien;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.EntretienService;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class AfficherEntretienController implements Initializable {

    @FXML
    private GridPane grid;
    @FXML
    private Button ajouterEntr;
    @FXML
    private TextField txtrecherche;
    EntretienService es = new EntretienService();
    @FXML
    private Button btnlister;
    @FXML
    private ImageView imgtrie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.getData();
        // TODO
    }  
    
    private void getData(){
        try {
            List<Entretien> entretiens = es.recuperer();
            int row = 1;
            int column = 0;
            for (int i = 0; i < entretiens.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Entretien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                EntretienController controller = loader.getController();
                controller.setEntretien(entretiens.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }
        } catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void ajouterMecanicien(ActionEvent event) {
    }

    @FXML
    private void rechercheMecanicien(KeyEvent event) {
    }

    @FXML
    private void listerMecaniciens(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherMecaniciensProf.fxml"));
            Parent root = loader.load();
            AfficherMecaniciensProfController controller = loader.getController();
            grid.getScene().setRoot(root);
        
    }

    @FXML
    private void trieCroissant(MouseEvent event) throws IOException, SQLException {
        grid.getChildren().clear();
        try {
            List<Entretien> entretiens = es.trieCroissant();
            int row = 1;
            int column = 0;
            for (int i = 0; i < entretiens.size(); i++) {
                //chargement dynamique d'une interface
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Entretien.fxml"));
                AnchorPane pane = loader.load();
                //passage de parametres
                EntretienController controller = loader.getController();
                controller.setEntretien(entretiens.get(i));

                grid.add(pane, column, row);
                column++;
                if (column > 2) {
                    column = 0;
                    row++;
                }
            }
            File file = new File("D:\\Anas INFO\\Esprit\\3A7\\PIDev\\travail momentané projet\\projet\\src\\images\\trier-az.png");
            Image imagee = new Image(file.toURI().toString());
            imgtrie.setImage(imagee);
        
    }
        catch (SQLException | IOException ex) {
            System.out.println(ex.getMessage());
        }
        
}
}
