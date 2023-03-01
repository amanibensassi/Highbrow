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
import java.sql.Timestamp;
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
    
    Date date = new Date("02/27/2023 08:18:05");
    Date datec = new Date("02/26/2023 13:47:32");
    Date d = new Date();  
        System.out.println(d);
        try {
            
//            Timestamp t;
//          t = new Timestamp(d.getTime());
//          t.setNanos(0);
//            System.out.println(t);
            
           Publication p= new Publication(44,1,date,"a publication");
            PublicationService ps= new PublicationService();
           // System.out.println(ps.recupererParId(90));
//            System.out.println(ps.recupererParUtilisateur(1));
         //     System.out.println("works"+ps.recupererParUtilisateurDate(p));
//            List<Publication> publication = new ArrayList<>();
//          
//                 System.out.println(ps.recupererParUtilisateur(1));
//                 ps.AddLike(p);
//                 ps.AddDislike(p);
//                 ps.supprimer(p);
//                 ps.ajouter(p);
//                 ps.modifier(p);
//                 System.out.println(ps.recuperer());
//            
             
          
          
          
          Commentaire cc = new Commentaire(10,131,1,datec,"test2");
          CommentaireService cs = new CommentaireService();
          Commentaire like = new Commentaire(131,1,true,false);
//          cs.ajouterLike(like);
//          cs.ajouterDislike(cc);
//       
//            System.out.println(cs.countLikes(131));
//            System.out.println(cs.countDislikes(131));
//            System.out.println(cs.countDislikes(137));
            System.out.println(cs.countCommentaire(131));
            System.out.println(cs.countCommentaire(137));
          //  System.out.println( cs.recupererParUtilisateurDate(cc));
//            System.out.println( cs.recupererParpublication(cc.getId_publication()));
//                 cs.ajouter(cc);
//                 cs.modifier(cc);
//                 System.out.println(cs.recuperer()); 
//                 cs.supprimer(cc);
//     
     
     
//        Reponse r = new Reponse(17,17,1,date,"une réponse à un commentaire 2 ");
//            ReponseService rs = new ReponseService();
//            rs.ajouter(r);
//            rs.modifier(r);
//            System.out.println(rs.recuperer());
//            rs.supprimer(r);
//          
//           System.out.println( rs.recupererParCommentaire(r.getId_commentaire()));
//           System.out.println( rs.recupererParUtilisateur(r.getId_utilisateur()));
        } catch (SQLException ex) {
           ex.getMessage();
    }   
}
}
