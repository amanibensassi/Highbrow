/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Commentaire;
import entities.Publication;
import entities.Reponse;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import services.CommentaireService;
import services.PublicationService;
import services.ReponseService;

/**
 *
 * @author ameni
 */
public class main {
    public static void main(String[] args) {
    
    Date date = new Date("10/11/2000 15:15");
      
 
        try {
            
            
       System.out.println("publication");
           Publication p= new Publication(14,1,date,"a publication");
            PublicationService ps= new PublicationService();
            List<Publication> publication = new ArrayList<>();
          
                 System.out.println(ps.recupererParUtilisateur(1));
                 ps.AddLike(p);
                 ps.AddDislike(p);
                 ps.supprimer(p);
                 ps.ajouter(p);
                 ps.modifier(p);
                 System.out.println(ps.recuperer());
            
             
          
          
          
          
      System.out.println("comentaire");  
             
          Commentaire cc = new Commentaire(9,1,1,date,"a comment");
          CommentaireService cs = new CommentaireService();
           
                 System.out.println( cs.recupererParpublication(cc.getId_publication()));
                 cs.ajouter(cc);
                 cs.modifier(cc);
                 System.out.println(cs.recuperer()); 
                 cs.supprimer(cc);
     
     
     
         System.out.println("Réponse à un commentaire :");
            
            Reponse r = new Reponse(1,1,1,date,"une réponse ");
            ReponseService rs = new ReponseService();
            rs.ajouter(r);
            rs.modifier(r);
            System.out.println(rs.recuperer());
            rs.supprimer(r);
         
           System.out.println( rs.recupererParCommentaire(r.getId_commentaire()));
           System.out.println( rs.recupererParUtilisateur(r.getId_utilisateur()));
        } catch (SQLException ex) {
           ex.getMessage();
    }   
}
}
