/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import entities.Commentaire;
import entities.Publication;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ameni
 */
public class OtherServices {

    CommentaireService cs = new CommentaireService();

    public List<Publication> sortByPopularity(List<Publication> pub) throws SQLException {
        List<Publication> publication = new ArrayList<>();
        int t = 0, i = pub.size();

        Publication testpub = new Publication();
        while (i > 1) {
            for (Publication p : pub) {
                Float test = cs.countFigure(pub.get(0).getIdpublication());
                if (cs.countFigure(p.getIdpublication()) >= test) {
                    t = pub.indexOf(p);
                    testpub = p;
                }
            }

            i = i - 1;
            publication.add(testpub);
            pub.remove(t);
        }
        publication.add(pub.get(0));

        return publication;
    }

    public String DateFilter(Date d) throws SQLException {
        Date date = new Date();
        String test;
        
//        if ((date.getYear() - d.getYear() < 1)&&(date.getMonth() - d.getMonth() < 1)&&(date.getMinutes() - d.getMinutes() < 1)) {
//            test = "il y a quelques secondes";
//        } else if ((date.getYear() - d.getYear() < 1)&&(date.getMonth() - d.getMonth() < 1)&&(date.getMinutes() - d.getMinutes() < 10)) {
//            test = "il y a quelques minutes";
//        
//        } else if ((date.getYear() - d.getYear() < 1)&&(date.getMonth() - d.getMonth() < 1)&&(d.getHours() - date.getHours() < 2) ){
//            test = "il y a une heure";
//        
//        } else if ((date.getYear() - d.getYear() < 1)&&(date.getMonth() - d.getMonth() < 2)) {
//            test = "il y un mois";
//        } else if (date.getYear() - d.getYear() < 1) {
//            test = "il y une année";
//        } else {
            test = String.valueOf("le "+d.getDate()) + "/" + String.valueOf(d.getMonth() + 1) + "/" + String.valueOf(d.getYear() + 1900)+ " à "+
                    String.valueOf(d.getHours())+":"+String.valueOf(d.getMinutes());
//        }

        return test;
    }

}
