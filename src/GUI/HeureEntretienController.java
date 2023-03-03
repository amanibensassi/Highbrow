/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Mecanicien;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import services.MecanicienService;
import typeenumeration.Region;
import typeenumeration.Specialite;

/**
 * FXML Controller class
 *
 * @author anasm
 */
public class HeureEntretienController implements Initializable {

    MecanicienService ms = new MecanicienService();
    Mecanicien m=new Mecanicien(5, 0, Specialite.mecanicien, Region.kebili, "adresse", "image", "nom_mecanicien", "prenom_mecanicien");
    @FXML
    private GridPane grid;
    @FXML
    private Label lbl;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    public void setPersonne(List<Date> m) throws IOException {
         
         int row = 0;
            int column = 0;
            if (m.size()==0){
                lbl.setVisible(false);
            }
            else {
                lbl.setVisible(true);
            for (int i = 0; i < m.size(); i++) {
                Label label = new Label();
                String h = m.get(i).toString();
                String[] parts1 = h.split(":");
                String minutes=parts1[1];
                String[] heurestab = parts1[0].split(" ");
                String heures=heurestab[1];
                label.setText(heures+":"+minutes);
                grid.add(label, column, row);
                column++;
                if (column > 0) {
                    column = 0;
                    row++;
                }
    }
}
    }
}
