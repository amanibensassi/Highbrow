package guitrabelsi;

import entities.Vehicule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import services.VehiculeService;

public class AfficherVehiculeController implements Initializable {

    @FXML
    private GridPane grid;

    VehiculeService ps = new VehiculeService();
    @FXML
    private ScrollPane scrollPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //GridPane grid = new GridPane();
        AjouterVehiculeController avc = new AjouterVehiculeController();
        SiegeController sc = new SiegeController();
        
        /*if (avc.getBtn().equals("btn1") && sc.equals(null)){
            System.out.println("recuperer");
        }
        else if (sc.getBtn().equals("btn2") && avc.equals(null)){
            System.out.println("recuperer vehicules");
        }*/
        try {
            List<Vehicule> vehicules = ps.recuperer();
            int row = 0;
            int column = 0;
            for (int i = 0; i < vehicules.size(); i++) {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("Vehicule.fxml"));
                AnchorPane pane = loader.load();
                VehiculeController controller = loader.getController();
                controller.setVehicule(vehicules.get(i));
                grid.add(pane, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }

                grid.setAlignment(Pos.CENTER);
                grid.setHalignment(scrollPane, HPos.CENTER);
                grid.setValignment(scrollPane, VPos.CENTER);
                grid.getColumnConstraints().clear();
                grid.getRowConstraints().clear();
            }
        } catch (SQLException ex) {
            System.out.println("Erreur de récupération des vehicule : " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("Erreur de chargement de l'interface vehicule : " + ex.getMessage());
        }
    }
    Vehicule pe = new Vehicule();
    public void setVehicule(Vehicule c) {

        
        
        String nomImage = "C://xampp//htdocs//img//" + c.getImage_vehicule();       
        File file = new File(nomImage);
        Image img = new Image(file.toURI().toString());
        
        pe.setId_siege(c.getId_siege());
        pe.setIdvehicule(c.getIdvehicule());
        pe.setMarque(c.getMarque());
        pe.setDate_circulation(c.getDate_circulation());
        pe.setImage_vehicule(c.getImage_vehicule());
        pe.setEtat(c.getEtat());
        pe.setPrix_par_jour(c.getPrix_par_jour());
        pe.setCarburant(c.getCarburant());
        pe.setImmatriculation(c.getImmatriculation());
        pe.setPrix_vente(c.getPrix_vente());
        pe.setNbr_place(c.getNbr_place());
        pe.setKilometrage(c.getKilometrage());
    }


}
