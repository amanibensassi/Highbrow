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
import entities.Publication;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import services.CommentaireService;
import services.OtherServices;

/**
 * FXML Controller class
 *
 * @author ameni
 */
public class PdfGenerationController implements Initializable {

    @FXML
    private ImageView like_image;
    @FXML
    private ImageView dislike_image;
    @FXML
    private Label username_id;
    @FXML
    private Label dateid;
    @FXML
    private Label publicationid;
    @FXML
    private Label id_likes;
    @FXML
    private Label nbr_commentaire;
    @FXML
    private Label id_dislikes;
OtherServices os = new OtherServices();
CommentaireService cs = new CommentaireService();



    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public void telecharger(Publication pub) throws SQLException {
        username_id.setText(String.valueOf(pub.getId_utilisateur()));
        dateid.setText(os.DateFilter(pub.getDate_publication()));
        publicationid.setText(pub.getPublication());
        id_likes.setText(String.valueOf(cs.countLikes(pub.getIdpublication())));
        id_dislikes.setText(String.valueOf(cs.countDislikes(pub.getIdpublication())));
        nbr_commentaire.setText(String.valueOf(cs.countCommentaire(pub.getIdpublication())));
        Document document = new Document(PageSize.A4);

        try {
            String filePath = "D:\\Anas INFO\\XAMPP\\htdocs\\Highbrow\\publication.pdf";
            //System.out.println("Working Directory = " + System.getProperty("C:\\xampp\\htdocs\\Highbrow"));
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(new File(filePath)));
            document.open();
          // Créer un Chunk avec le texte "Nom : " suivi du texte du Label nom
        Chunk nomChunk = new Chunk("Nom et prénom : ");
        nomChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nomParagraph = new Paragraph(nomChunk);
        nomParagraph.add(new Chunk(username_id.getText()));

        // Créer un Chunk avec le texte "Email : " suivi du texte du Label email
        Chunk emailChunk = new Chunk("Date : ");
        emailChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph emailParagraph = new Paragraph(emailChunk);
        emailParagraph.add(new Chunk(dateid.getText()));

        // Créer un Chunk avec le texte "Marque : " suivi du texte du Label marque
        Chunk marqueChunk = new Chunk("Corps de la publication : ");
        marqueChunk.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph marqueParagraph = new Paragraph(marqueChunk);
        marqueParagraph.add(new Chunk(publicationid.getText()));

        // Créer un Chunk avec le texte "Nombre de jours : " suivi du texte du Label nbrjour
        Chunk nbrlikes = new Chunk("Nombre de likes : ");
        nbrlikes.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nbrjourParagraph = new Paragraph(nbrlikes);
        nbrjourParagraph.add(new Chunk(id_likes.getText()));

        Chunk nbrdislikes = new Chunk("Nombre de dislikes : ");
        nbrdislikes.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nbrdislikespara = new Paragraph(nbrdislikes);
        nbrdislikespara.add(new Chunk(id_likes.getText()));
        
        Chunk nbrjcommentaire = new Chunk("Nombre de comentaire : ");
        nbrjcommentaire.setFont(FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12));
        Paragraph nbrjcommentairepara = new Paragraph(nbrjcommentaire);
        nbrjcommentairepara.add(new Chunk(nbr_commentaire.getText()));
        
        // Créer une Table avec deux colonnes pour afficher les totaux
        PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100);

//        PdfPCell cell1 = new PdfPCell(new Phrase("Nombre de dislikes"));
//        cell1.setHorizontalAlignment(Element.ALIGN_LEFT);
//        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell(cell1);
//
//        PdfPCell cell2 = new PdfPCell(new Phrase(calcule1.getText()));
//        cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell(cell2);
//
//        PdfPCell cell3 = new PdfPCell(new Phrase("Total HT"));
//        cell3.setHorizontalAlignment(Element.ALIGN_LEFT);
//        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell(cell3);
//
//        PdfPCell cell4 = new PdfPCell(new Phrase(calcule2.getText()));
//        cell4.setHorizontalAlignment(Element.ALIGN_RIGHT);
//        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
//        table.addCell(cell4);

        // Ajouter les Paragraphs et la Table au Document
        document.add(nomParagraph);
        document.add(emailParagraph);
        document.add(marqueParagraph);
        document.add(nbrjourParagraph);
        document.add(nbrdislikespara);
        document.add(nbrjcommentairepara);
        
//        document.add(table);

        document.close();
        writer.close();
            System.out.println("PDF has been created successfully!");
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    
}
    
    
    

