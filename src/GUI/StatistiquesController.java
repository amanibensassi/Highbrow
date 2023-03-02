/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.awt.Color;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;
import services.ChauffeurService;
import services.LocationService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class StatistiquesController implements Initializable {

    @FXML
    private AnchorPane chartPane;
    private JPanel chartPanel;

    private JFreeChart chart;
    private JPanel chartPane2;

    private JFreeChart chart3;
    @FXML
    private AnchorPane chartpanepie;
    LocationService ls = new LocationService();
    ChauffeurService ch = new ChauffeurService();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Créer le jeu de données
            TreeMap<Integer,Integer> nbrLocatioByvehicule = ls.nombreLocationParVehicule();
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
             for (Map.Entry<Integer, Integer> entry : nbrLocatioByvehicule.entrySet()) {
            Integer key = entry.getKey();
            int value = entry.getValue();
           dataset.addValue(value,"vehicule",key );
        }
          
            
//            dataset.addValue(5.0, "Series 1", "Category 2");
//            dataset.addValue(3.0, "Series 1", "Category 3");
//            dataset.addValue(2.0, "Series 2", "Category 1");
//            dataset.addValue(3.0, "Series 2", "Category 2");
//            dataset.addValue(2.0, "Series 2", "Category 3");
 TreeMap<Integer,Integer> nbrchauffeurParSiege = ch.nombrechauffeurBysiege();
            DefaultPieDataset dataset2 = new DefaultPieDataset();
               for (Map.Entry<Integer, Integer> entry : nbrchauffeurParSiege.entrySet()) {
            Integer key = entry.getKey();
            int value = entry.getValue();
            
            String v = key+"";
                   System.out.println(key);
            dataset2.setValue(v, value);
        }
           

            JFreeChart chart2 = ChartFactory.createPieChart(
                    "Nombre des chauffeurs par siege", // 
                    dataset2, // Dataset
                    true, // 
                    true, // 
                    false // 
            );

            // Créer le graphique
            chart = ChartFactory.createBarChart(
                    "Nombre des locations par vehicule", // Titre du graphique
                    "Category", // 
                    "Value", // 
                    dataset, // 
                    PlotOrientation.VERTICAL, // Orientation
                    true, //
                    true, // 
                    false // 
            );
           
            chartPane2 = new ChartPanel(chart2);
            chartPane2.setPreferredSize(new java.awt.Dimension(450, 400));
            SwingNode swingNode1 = new SwingNode();

           
            swingNode1.setContent(chartPane2);

           
            chartpanepie.getChildren().add(swingNode1);

          
            chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new java.awt.Dimension(450, 400));
            SwingNode swingNode = new SwingNode();

          
            swingNode.setContent(chartPanel);

            
            chartPane.getChildren().add(swingNode);
        } catch (SQLException ex) {
            Logger.getLogger(StatistiquesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
