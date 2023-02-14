/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Commentaire;
import entities.Publication;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.CommentaireService;
import services.PublicationService;

/**
 *
 * @author ameni
 */
public class main {
    public static void main(String[] args) {
    
    Date date = new Date("10/11/2000 15:15");
      
 
        try {
            
            
       System.out.println("publication");
           Publication p= new Publication(14,1,date,"a publication is set here");
            PublicationService ps= new PublicationService();
            List<Publication> publication = new ArrayList<>();
                  ps.AddLike(p);
                  ps.AddDislike(p);
                  ps.supprimer(p);
                  ps.ajouter(p);
                  ps.modifier(p);
                  System.out.println(ps.recuperer(p));
            
             
          
          
          
          
       System.out.println("comentaire");  
             
          Commentaire cc = new Commentaire(9,1,1,date,"modify git");
            CommentaireService cs = new CommentaireService();
            
                cs.ajouter(cc);
                cs.modifier(cc);
                System.out.println(cs.recuperer(cc)); 
                cs.supprimer(cc);
       
       
        } catch (SQLException ex) {
           ex.getMessage();
    }   
}
}
