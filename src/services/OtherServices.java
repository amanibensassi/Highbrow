/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commentaire;
import entities.Publication;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ameni
 */
public class OtherServices {
    CommentaireService cs = new CommentaireService();
    
    public List<Publication> sortByPopularity(List<Publication> pub) throws SQLException{
    List<Publication> publication = new ArrayList<>();
    int t=0,i=pub.size();
    
    Publication testpub = new Publication();
    while(i>1){
        for(Publication p:pub){
            Float test =cs.countFigure(pub.get(0).getIdpublication());
            if (cs.countFigure(p.getIdpublication())>= test){
                t = pub.indexOf(p);
                testpub=p;}
                }
        
    i=i-1;
    publication.add(testpub);
    pub.remove(t);
    }
    publication.add(pub.get(0));
        
   return publication;
}
    
    
    
    
    
    
    
}
