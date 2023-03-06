/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Vente;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import services.UserConn;
import services.VenteService;

/**
 * FXML Controller class
 *
 * @author Hamma
 */
public class AfficherRendezVousController implements Initializable {

    @FXML
    private GridPane grid;
    private int id_v;

    /**
     * Initializes the controller class.
     */
    Vente v = new Vente();
    VenteService vs = new VenteService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void setidVehicule(int id){
        id_v=id;
    }
    
    public void setdata( ) throws IOException, FileNotFoundException, ParseException {
        int rowIndex = 1;
        int columnIndex = 0;
        List<Vente> Ventes;
        if (UserConn.role.toString().equals("client")){
            
            try {
            Ventes = vs.recupererAllvehiculesByidUtilisateur(UserConn.idutilisateur);
            System.out.println(Ventes);
            for (int i = 0; i < Ventes.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RdvCard.fxml"));

                HBox AnchorPane = loader.load();
                RdvCardController controllerch = loader.getController();

                controllerch.setRdv(Ventes.get(i));
                System.out.println("Liste" + Ventes.get(i));
                System.out.println(Ventes.get(i));
                grid.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 1) {
                    columnIndex = 0;
                    rowIndex = rowIndex + 2;
                }
                // TODO
            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        }
        if (UserConn.role.toString().equals("proprietaire_agence")){
            try {
                System.out.println("IDVEEEEE"+id_v);
            Ventes = vs.recupererAllvehiculesByidVehicule(id_v);
            System.out.println(Ventes);
            for (int i = 0; i < Ventes.size(); i++) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("RdvCard.fxml"));

                HBox AnchorPane = loader.load();
                RdvCardController controllerch = loader.getController();

                controllerch.setRdv(Ventes.get(i));
                System.out.println("Liste" + Ventes.get(i));
                System.out.println(Ventes.get(i));
                grid.add(AnchorPane, columnIndex, rowIndex);
                columnIndex++;
                if (columnIndex == 1) {
                    columnIndex = 0;
                    rowIndex = rowIndex + 2;
                }
                // TODO
            }

        } catch (SQLException ex) {
            Logger.getLogger(AfficherRendezVousController.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }

}
