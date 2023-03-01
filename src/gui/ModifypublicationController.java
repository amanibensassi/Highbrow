///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package gui;
//
//import entities.Publication;
//import java.io.IOException;
//import java.net.URL;
//import java.sql.SQLException;
//import java.util.Date;
//import java.util.ResourceBundle;
//import javafx.event.ActionEvent;
//import javafx.fxml.FXML;
//import javafx.fxml.FXMLLoader;
//import javafx.fxml.Initializable;
//import javafx.scene.Parent;
//import javafx.scene.control.Button;
//import javafx.scene.control.Dialog;
//import javafx.scene.control.TextField;
//import services.PublicationService;
//
///**
// * FXML Controller class
// *
// * @author ameni
// */
//public class ModifypublicationController implements Initializable {
//
//    @FXML
//    private TextField idmodifypub;
//    @FXML
//    private Button modifierpub;
//    @FXML
//    private Button supprimerpub;
//    private Publication pub = new Publication();
//    Date date = new Date();
//    PublicationService ps = new PublicationService();
//    @FXML
//    private Button annulerselection;
//   
//    
//
//    /**
//     * Initializes the controller class.
//     */
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//  
//    }   
//     
//    public void initialize(Publication p) {
//        
//        pub=p;
//        System.out.println("enty tawa fel initialize!!! "+pub);
//        System.out.println("publication iss  "+this.pub);
//        idmodifypub.setText(this.pub.getPublication());
//    
//    }    
//
//    @FXML 
//    private void modifierpublication(ActionEvent event) {
//        try{
//            pub.setPublication(idmodifypub.getText());
//            ps.modifier(pub);
//
//            
//            //chemin vers l'affichage des publications : 
//        }catch (SQLException ex)
//        {System.out.println("erreur modification de la publication");}
//    }
//    
//    @FXML
//    private void supprimerpublication(ActionEvent event) {
//        try{
//        ps.supprimer(pub);
//        
//         //chemin vers l'affichage des publications : 
//        }catch  (SQLException ex)
//        {System.out.println("erreur modification de la publication");}
//    }
//
//    @FXML
//    private void annulerselection(ActionEvent event) {
//
//    }
//    
//}

package gui;

import entities.Publication;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;
import services.PublicationService;

public class ModifypublicationController implements Initializable {

    @FXML
    private TextField idmodifypub;
    @FXML
    private Button modifierpub;
    @FXML
    private Button supprimerpub;
    private Publication pub = new Publication();
    PublicationService ps = new PublicationService();
    @FXML
    private Button annulerselection;

    private Dialog<Void> dialog;
    private Node source;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void initialize(Publication p,Dialog<Void> dialog, Node source ) {
        try{
        this.pub = ps.recupererParUtilisateurDate(p);
            System.out.println(pub);
        //
        this.dialog = dialog;
        this.source = source;
        idmodifypub.setText(this.pub.getPublication());
        }catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void modifierpublication(ActionEvent event) {
        try {
            pub.setPublication(idmodifypub.getText());
            ps.modifier(pub);
            dialog.setResult(null);
            dialog.close();
        } catch (SQLException ex) {
            System.out.println("erreur modification de la publication");
        }
    }

    @FXML
    private void supprimerpublication(ActionEvent event) {
        try {
            ps.supprimer(pub);
            dialog.setResult(null);
            dialog.close();
        } catch (SQLException ex) {
            System.out.println("erreur suppression de la publication");
        }
    }

    @FXML
    private void annulerselection(ActionEvent event) {
        dialog.setResult(null);
        dialog.close();
    }
}
