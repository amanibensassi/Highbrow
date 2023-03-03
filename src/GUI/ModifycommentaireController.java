/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import entities.Commentaire;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import services.CommentaireService;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class ModifycommentaireController implements Initializable {

    @FXML
    private Button modifierpub;
    @FXML
    private Button supprimerpub;
    @FXML
    private Button annulerselection;
    @FXML
    private TextField idmodifycom;
    private Dialog<Void> dialog;
    private Node source;
    Commentaire com = new Commentaire();
    CommentaireService cs = new CommentaireService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void initialize(Commentaire com,Dialog<Void> dialog, Node source ) {
        this.com = com;
        //
        this.dialog = dialog;
        this.source = source;
        idmodifycom.setText(com.getCommentaire());
    }

    @FXML
    private void modifiercommentaire(ActionEvent event) {
          try {
            com.setCommentaire(idmodifycom.getText());
            cs.modifier(com);
            dialog.setResult(null);
            dialog.close();
        } catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void supprimercommentaire(ActionEvent event) {
         try {
            cs.supprimer(com);
            dialog.setResult(null);
            dialog.close();
        } catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void annulercommentaire(ActionEvent event) {
        dialog.setResult(null);
        dialog.close();
    }
    
    
}
