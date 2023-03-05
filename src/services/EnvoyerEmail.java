/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Hamma
 */
public class EnvoyerEmail {
    
public static void envoyer(String destinataire, int code) throws MessagingException, AddressException {
            
            String username = "mouhamedtrabelsi.28@gmail.com";
            String password ="vrbbphxzfhhzhxzk";
            System.out.println("Entrain d'envoyer un email de vérification !! ");
            // Etape 1 : Création de la session
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host","smtp.gmail.com");
            props.put("mail.smtp.port","587");
            
            //session de messagerie avec l'authentification de l'utilisateur
    Session session = Session.getInstance(props, new Authenticator() {
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
        }
    });
    

            javax.mail.Message message = null;
         try {
             message = prepareMessage(session,username,destinataire,code);
         } catch (javax.mail.MessagingException ex) {
             Logger.getLogger(EnvoyerEmail.class.getName()).log(Level.SEVERE, null, ex);
         }
         try {
             javax.mail.Transport.send(message);
         } catch (javax.mail.MessagingException ex) {
             Logger.getLogger(EnvoyerEmail.class.getName()).log(Level.SEVERE, null, ex);
         }
            System.out.println("Message envoyé !!");
}
        
    

    private static javax.mail.Message prepareMessage(Session session, String username,String destinataire, int code) throws javax.mail.MessagingException {
        
        try { 
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(javax.mail.Message.RecipientType.TO, new InternetAddress(destinataire));
            message.setSubject("Code de vérification FastRent");
            
            message.setText("Votre code de vérification est le suivant: "+code+"\nVotre compte n'est pas accessible sans ce code de vérification.");
            return message;
        } catch (AddressException ex) {
            Logger.getLogger(EnvoyerEmail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}

