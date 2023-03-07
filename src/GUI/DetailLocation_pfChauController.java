/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Chauffeur;
import entities.Location;
import entities.Vehicule;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.ChauffeurService;
import services.LocationService;
import services.VehiculeService;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class DetailLocation_pfChauController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label marque;
    @FXML
    private Label nbrjour;
    @FXML
    private Label calcule1;
    @FXML
    private Label email114;
    @FXML
    private Label oui_non;
    @FXML
    private Label calcule2;
    @FXML
    private Label email1141;
    @FXML
    private Label numtel1;
    @FXML
    private Label totaleSomme;
    private int idvehicule;
    VehiculeService vs = new VehiculeService();
    LocationService ls = new LocationService();
    ChauffeurService cs = new ChauffeurService();
    @FXML
    private ImageView facture;
String n;
    /**
     * Initializes the controller class.
     */
    public void facture(int idv, int idl) throws SQLException {
        Vehicule v = new Vehicule();
        Location l = new Location();
        Chauffeur c = new Chauffeur();
        float prixch = 0;
        int nbrjourr;
        v = vs.recupererVehiculeByid(idv);
        l = ls.recupererById(idl);
        if(l.getId_chauffeur()!=0)
        {c=cs.recupererById(l.getId_chauffeur());};
        marque.setText(v.getMarque());
        String db = l.getDate_debut().toString();
        String df = l.getDate_fin().toString();

        String[] splitdb = db.split("-");
        String[] splitdf = df.split("-");

        String moisdb = splitdb[1];
        String jourdb = splitdb[2];

        String moisdf = splitdf[1];
        String jourdf = splitdf[2];

        int monthdb = Integer.parseInt(moisdb);
        int daydb = Integer.parseInt(jourdb);

        int monthdf = Integer.parseInt(moisdf);
        int daydf = Integer.parseInt(jourdf);

        nbrjourr = daydf - daydb +1;
         n = nbrjourr + "";
        nbrjour.setText(n);

        float prix = nbrjourr * v.getPrix_par_jour();
        String p = prix + "";

        calcule1.setText(p);
        Boolean opch = l.isOpt_chauffeur();
        System.out.println(opch);
        if (opch == true) {
            oui_non.setText("oui");
            prixch = nbrjourr * c.getPrix_par_jour();
        }
        if (opch == false) {
            oui_non.setText("non");
        }

        String pch = prixch + "";
        calcule2.setText(pch);

        float sm = prixch + prix;
        String smfinal = sm + "";
        totaleSomme.setText(smfinal);

//        System.out.println("char debut"+monthdb);
//        System.out.println("nhar debut"+daydb);
//        
//        System.out.println("char fin"+monthdf);
//        System.out.println("nhar fin"+daydf);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void facturetelecharger(MouseEvent event) {
          Document document = new Document(PageSize.A4);

        try {
            String filePath = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\facture.pdf";
            //System.out.println("Working Directory = " + System.getProperty("C:\\xampp\\htdocs\\Highbrow"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();
          // Créer un Chunk avec le texte "Nom : " suivi du texte du Label nom
        Chunk nomChunk = new Chunk("Nom : ");
        nomChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nomParagraph = new Paragraph(nomChunk);
        nomParagraph.add(new Chunk(nom.getText()));

        // Créer un Chunk avec le texte "Email : " suivi du texte du Label email
        Chunk emailChunk = new Chunk("Email : ");
        emailChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph emailParagraph = new Paragraph(emailChunk);
        emailParagraph.add(new Chunk(email.getText()));

        // Créer un Chunk avec le texte "Marque : " suivi du texte du Label marque
        Chunk marqueChunk = new Chunk("Marque : ");
        marqueChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph marqueParagraph = new Paragraph(marqueChunk);
        marqueParagraph.add(new Chunk(marque.getText()));

        // Créer un Chunk avec le texte "Nombre de jours : " suivi du texte du Label nbrjour
        Chunk nbrjourChunk = new Chunk("Nombre de jours : ");
        nbrjourChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nbrjourParagraph = new Paragraph(nbrjourChunk);
        nbrjourParagraph.add(new Chunk(nbrjour.getText()));

        Chunk optionch = new Chunk("Option Chauffeur : ");
        optionch.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph optionch1 = new Paragraph(optionch);
        optionch1.add(new Chunk(oui_non.getText()));
        
        
        // Créer une Table avec deux colonnes pour afficher les totaux
        
        
        
//          Chunk sommech = new Chunk("Prix chauffeur   : ");
//        nbrjourChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
//        Paragraph optionch2 = new Paragraph(sommech);
//        optionch2.add(new Chunk(calcule2.getText()));
        
        
        // Créer une Table avec deux colonnes pour afficher les totaux
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

        PdfPCell cell1 = new PdfPCell(new Phrase("Prix chauffeur"));
        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell1);

        PdfPCell cell2 = new PdfPCell(new Phrase(calcule2.getText()));
        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell2);

        PdfPCell cell3 = new PdfPCell(new Phrase("prix location"));
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell3);

        PdfPCell cell4 = new PdfPCell(new Phrase(calcule1.getText()));
        cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell4);
        
           PdfPCell cell5 = new PdfPCell(new Phrase("Somme Total"));
        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell5);

        PdfPCell cell6 = new PdfPCell(new Phrase(totaleSomme.getText()));
        cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell6);

        // Ajouter les Paragraphs et la Table au Document
        document.add(nomParagraph);
        document.add(emailParagraph);
        document.add(marqueParagraph);
        document.add(nbrjourParagraph);
        document.add(table);

        document.close();
        writer.close();
            System.out.println("PDF has been created successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

}
