package gui;



import entities.Siege;
import entities.Vehicule;
import gui.SiegeController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.SiegeService;
import services.VehiculeService;


public class AfficherSiegeController implements Initializable {

    @FXML
    private GridPane grid;

    SiegeService ps = new SiegeService();
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label labelTitreSieges;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         
       

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

    
    

}
